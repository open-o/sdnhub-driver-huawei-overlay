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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
     * Create VxLan operation. <br>
     * 
     * @param ctrlUuid
     *            The controller UUID
     * @param deviceId
     *            The device id
     * @param netVxLanDeviceModelList
     *            The data that want to crate
     * @return The ResultRsp with the list of NetVxLanDeviceModel
     * @throws ServiceException
     *             When create failed
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<VxLanDeviceModel>> createVxLanByDevice(String ctrlUuid, String deviceId,
            List<VxLanDeviceModel> netVxLanDeviceModelList) throws ServiceException {

        ResultRsp<List<VxLanDeviceModel>> resultRsp =
                new ResultRsp<List<VxLanDeviceModel>>(ErrorCode.OVERLAYVPN_SUCCESS);

        String createVxlanUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);

        Map<String, List<VxLanDeviceModel>> ctrlInfoMap = new ConcurrentHashMap<String, List<VxLanDeviceModel>>();
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

                return new ResultRsp<List<VxLanDeviceModel>>(ErrorCode.OVERLAYVPN_FAILED, overlayVpnResponse.getData());
            }
        } else {
            return new ResultRsp<List<VxLanDeviceModel>>(ErrorCode.OVERLAYVPN_FAILED);
        }
    }

    public ResultRsp<ACDelResponse> deleteVxlanByDevice(String ctrlUuid, String deviceId, List<String> idList)
            throws ServiceException {

        ResultRsp<ACDelResponse> resultRsp = new ResultRsp<ACDelResponse>(ErrorCode.OVERLAYVPN_SUCCESS);

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

    public ResultRsp<List<VxLanDeviceModel>> updateVxlanByDevice(String ctrlUuid, String deviceId,
            List<VxLanDeviceModel> vxlanDeviceModelList) throws ServiceException {
        return createVxLanByDevice(ctrlUuid, deviceId, vxlanDeviceModelList);
    }

    public ResultRsp<List<VxLanDeviceModel>> queryVxlanByDevice(String ctrlUuid, String deviceId)
            throws ServiceException {
        ResultRsp<List<VxLanDeviceModel>> resultRsp =
                new ResultRsp<List<VxLanDeviceModel>>(ErrorCode.OVERLAYVPN_SUCCESS);

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
}
