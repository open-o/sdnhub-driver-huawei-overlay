/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdnhub.overlayvpndriver.sbi.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jackson.type.TypeReference;
import org.apache.commons.collections.CollectionUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.PortVlan;
import org.openo.sdnhub.overlayvpndriver.controller.model.Vni;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInstance;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ws.rs.WebApplicationException;

/**
 * VxLan service implementation.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jan 16, 2017
 */
@Service
public class VxLanSvcImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(VxLanSvcImpl.class);

    private static final String DELETE_VXLN_PARAMETER = "ids";

    /**
     * Adds new Vxlan configuration using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param netVxLanDeviceModelList collection of controller vxlan device model structure
     * @return ResultRsp object with collection of VxLan added configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static ResultRsp<List<VxLanDeviceModel>> createVxLanByDevice(String ctrlUuid, String deviceId,
            List<VxLanDeviceModel> netVxLanDeviceModelList) throws ServiceException {

        ResultRsp<List<VxLanDeviceModel>> resultRsp =
                new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);

        String createVxlanUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);

        Map<String, List<VxLanDeviceModel>> ctrlInfoMap = new ConcurrentHashMap<>();
        ctrlInfoMap.put(CommConst.VXLAN_LIST, netVxLanDeviceModelList);
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(createVxlanUrl, JsonUtil.toJson(ctrlInfoMap), ctrlUuid);

        String body = httpMsg.getBody();

        if(httpMsg.isSuccess() || StringUtils.hasLength(body)) {
            OverlayVpnDriverResponse<List<VxLanDeviceModel>> overlayVpnResponse =
                    JsonUtil.fromJson(body, new TypeReference<OverlayVpnDriverResponse<List<VxLanDeviceModel>>>() {});
            if(overlayVpnResponse.isSucess()) {
                resultRsp.setData(overlayVpnResponse.getData());
                return resultRsp;
            } else {

                return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED, overlayVpnResponse.getData());
            }
        } else {
            return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED);
        }
    }

    /**
     * Queries device for Vxlan configuration using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @return ResultRsp object with collection of VxLan queried configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static ResultRsp<List<VxLanDeviceModel>> queryVxlanByDevice(String ctrlUuid, String deviceId)
            throws ServiceException {
        ResultRsp<List<VxLanDeviceModel>> resultRsp =
                new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);

        String queryUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);
        String body = httpMsg.getBody();
        LOGGER.debug("queryVxlanByDevice return body: " + body);
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("queryVxlanByDevice: httpMsg return error.");
            throw new ServiceException(ErrorCode.ADAPTER_CONNECTOR_RESPONSE_FAIL,
                    "queryVxlanByDevice: httpMsg return error.");
        }

        OverlayVpnDriverResponse<List<VxLanDeviceModel>> acresponse =
                JsonUtil.fromJson(body, new TypeReference<OverlayVpnDriverResponse<List<VxLanDeviceModel>>>() {});

        if(!acresponse.isSucess()) {
            LOGGER.error("createTunnelByDevice: acresponse return error, errMsg: " + acresponse.getErrmsg());
            throw new ServiceException(ErrorCode.ADAPTER_CONNECTOR_RESPONSE_FAIL, acresponse.getErrmsg());
        }

        resultRsp.setData(acresponse.getData());
        return resultRsp;
    }

    /**
     * Deletes Vxlan configuration using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param idList collection of vxlan id
     * @return ResultRsp object with VxLan deleted configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static ResultRsp<ACDelResponse> deleteVxlanByDevice(String ctrlUuid, String deviceId, List<String> idList)
            throws ServiceException {

        ResultRsp<ACDelResponse> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);

        if(CollectionUtils.isEmpty(idList)) {
            LOGGER.debug("id list is null");
            return resultRsp;
        }

        String deleteVxlanUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);

        Map<String, List<String>> ctrlInfoMap = new HashMap<>();
        ctrlInfoMap.put(DELETE_VXLN_PARAMETER, idList);

        HTTPReturnMessage httpmsg = OverlayVpnDriverProxy.getInstance().sendDeleteMsg(deleteVxlanUrl,
                JsonUtil.toJson(ctrlInfoMap), ctrlUuid);

        String body = httpmsg.getBody();

        if(httpmsg.isSuccess() && StringUtils.hasLength(body)) {
            ACDelResponse acresponse = JsonUtil.fromJson(body, new TypeReference<ACDelResponse>() {});

            if(!acresponse.isSucess()) {
                LOGGER.error("Delete vxlanByDevice:acresponse return error " + acresponse.getAllErrmsg());
                resultRsp.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
                resultRsp.setMessage(acresponse.getAllErrmsg());

                return resultRsp;
            }

            LOGGER.error("deletevxlanBydevice: httpmsg return error.");
            resultRsp.setErrorCode(ErrorCode.OVERLAYVPN_SUCCESS);
            resultRsp.setMessage(acresponse.getAllErrmsg());
            resultRsp.setData(acresponse);
        }
        return resultRsp;
    }

    /**
     * Updates Vxlan configuration using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param vxlanDeviceModelList collection of controller vxlan device model structure
     * @return ResultRsp object with collection of VxLan updated configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static ResultRsp<List<VxLanDeviceModel>> updateVxlanByDevice(String ctrlUuid, String deviceId,
            List<VxLanDeviceModel> vxlanDeviceModelList) throws ServiceException {
        return createVxLanByDevice(ctrlUuid, deviceId, vxlanDeviceModelList);
    }

    /**
     * Searches succeeded and failed result in controller response and fill those information
     * in response to service SBI.
     *
     * @param totalResult result response to service SBI
     * @param vxlanInstanceElement request element map from service SBI
     * @param acExistVxlanModels result response collection from controoler
     * @since SDNHUB 0.5
     */
    public static void findData(ResultRsp<SbiNeVxlanInstance> totalResult,
                          Map.Entry<String, List<SbiNeVxlanInstance>> vxlanInstanceElement,
                          List<VxLanDeviceModel> acExistVxlanModels) {
        for(SbiNeVxlanInstance sbiNeVxlanInstance : vxlanInstanceElement.getValue()) {
            boolean isFind = false;
            for(VxLanDeviceModel vxlanDeviceModel : acExistVxlanModels) {
                if(vxlanDeviceModel.getUuid().equals(sbiNeVxlanInstance.getExternalId())) {
                    for(Vni vni : vxlanDeviceModel.getVniList()) {
                        if(sbiNeVxlanInstance.getVni().equals(String.valueOf(vni.getVni()))) {
                            if(totalResult.getSuccessed() == null) {
                                totalResult.setSuccessed(new ArrayList<>());
                            }
                            totalResult.getSuccessed().add(sbiNeVxlanInstance);
                            isFind = true;
                            break;
                        }
                    }
                }
                if(isFind) {
                    break;
                }
            }
            if(!isFind) {
                FailData<SbiNeVxlanInstance> failData = new FailData<>(ErrorCode.OVERLAYVPN_FAILED,
                        ErrorCode.COMMON_CONFIG_NOT_EXIST, sbiNeVxlanInstance);
                if(totalResult.getFail() == null) {
                    totalResult.setFail(new ArrayList<>());
                }
                totalResult.getFail().add(failData);
            }
        }
    }

    /**
     * Groups service SBI NeVxlanInstance based on device id.
     *
     * @param vxlanInstanceList service SBI request with collection of Ne Vxlan instance
     * @return grouped service SBI NeVxlanInstance based on device id
     * @since SDNHUB 0.5
     */
    public static Map<String, List<SbiNeVxlanInstance>> groupByDeviceId(List<SbiNeVxlanInstance> vxlanInstanceList) {
        Map<String, List<SbiNeVxlanInstance>> map = new HashMap<>();
        for(SbiNeVxlanInstance sbiNeVxlanInstance : vxlanInstanceList) {
            if(null == map.get(sbiNeVxlanInstance.getDeviceId())) {
                map.put(sbiNeVxlanInstance.getDeviceId(), new ArrayList<SbiNeVxlanInstance>());
            }
            map.get(sbiNeVxlanInstance.getDeviceId()).add(sbiNeVxlanInstance);
        }
        return map;
    }

    public static VxLanDeviceModel mergeVxlanDeviceModels(List<VxLanDeviceModel> createVxlanDeviceModels,
                                                          List<VxLanDeviceModel> acVxlanDeviceModels)
    {
        VxLanDeviceModel retDeviceModel = createVxlanDeviceModels.get(0);

        for(int i=1; i<createVxlanDeviceModels.size(); i++)
        {
            VxLanDeviceModel createVxlanDeviceModel = createVxlanDeviceModels.get(i);
            mergeVxlanDeviceModel(retDeviceModel, createVxlanDeviceModel);
        }

        if(CollectionUtils.isNotEmpty(acVxlanDeviceModels))
        {
            mergeVxlanDeviceModel(retDeviceModel, acVxlanDeviceModels.get(0));
        }

        return retDeviceModel;
    }

    private static void mergeVxlanDeviceModel(VxLanDeviceModel createVxlanDeviceModel, VxLanDeviceModel mergeVxlanDeviceModel)
    {
        if(null == mergeVxlanDeviceModel || CollectionUtils.isEmpty(mergeVxlanDeviceModel.getVniList()))
        {
            return;
        }

        List<Vni> createVniList = new ArrayList<Vni>();
        createVniList.addAll(createVxlanDeviceModel.getVniList());
        for(Vni acVni : mergeVxlanDeviceModel.getVniList())
        {
            for(Vni createVni : createVniList)
            {
                if(createVni.getVni() == acVni.getVni())
                {
                    if(CollectionUtils.isNotEmpty(acVni.getPeerAddresslist()))
                    {
                        createVni.getPeerAddresslist().addAll(acVni.getPeerAddresslist());
                    }
                    if(CollectionUtils.isNotEmpty(acVni.getPortlist()))
                    {
                        createVni.getPortlist().addAll(acVni.getPortlist());
                    }
                    if(CollectionUtils.isNotEmpty(acVni.getVlanlist()))
                    {
                        createVni.getVlanlist().addAll(acVni.getVlanlist());
                    }
                    if(CollectionUtils.isNotEmpty(acVni.getPortvlanlist()))
                    {
                        createVni.getPortvlanlist().addAll(acVni.getPortvlanlist());
                    }
                }
            }
        }

        createVxlanDeviceModel.setVniList(createVniList);
    }

    public static void mergeDelVxlanDeviceModel(VxLanDeviceModel acExistVxlanModel, VxLanDeviceModel delVxLanDeviceModel) {
        for (Vni acExistVni : acExistVxlanModel.getVniList()) {
            for (Vni delVni : delVxLanDeviceModel.getVniList()) {
                if (acExistVni.getVni() == delVni.getVni()) {
                    mergeDelVni(acExistVni, delVni);
                    break;
                }
            }
        }
    }

    private static void mergeDelVni(Vni acExistVni, Vni delVni) {
        List<String> acExistPeerAddrList = acExistVni.getPeerAddresslist();
        if (CollectionUtils.isNotEmpty(acExistPeerAddrList)) {
            acExistPeerAddrList.removeAll(delVni.getPeerAddresslist());
        }

        if (CollectionUtils.isEmpty(acExistPeerAddrList)) {
            acExistVni.setDeleteMode(true);
        } else {
            List<String> acExistPortList = acExistVni.getPortlist();
            List<String> delPortList = delVni.getPortlist();
            if (CollectionUtils.isNotEmpty(acExistPortList) && CollectionUtils.isNotEmpty(delPortList)) {
                acExistPortList.removeAll(delPortList);
            }

            List<Integer> acExistVlanList = acExistVni.getVlanlist();
            List<Integer> delVlanList = delVni.getVlanlist();
            if (CollectionUtils.isNotEmpty(acExistVlanList) && CollectionUtils.isNotEmpty(delVlanList)) {
                acExistVlanList.removeAll(delVlanList);
            }

            List<PortVlan> acExistPortVlanList = acExistVni.getPortvlanlist();
            List<PortVlan> delPortVlanList = delVni.getPortvlanlist();
            if (CollectionUtils.isNotEmpty(acExistPortVlanList) && CollectionUtils.isNotEmpty(delPortVlanList)) {
                acExistPortVlanList.removeAll(delPortVlanList);
            }
        }
    }
}
