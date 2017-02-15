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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiPolicyRoute;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficPolicyList;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNePolicyRoute;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Policy Route service implementation.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jan 16, 2017
 */
public class PolicyRouteImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyRouteImpl.class);

    private static final String DELETE_ROUTE_PARAMETER = "ids";

    private static final String TRAFIC_POLICY_LIST = "trafficPolicyList";

    /**
     * Configure QOS MQC Configuration using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param list collection of Policy route configuration specific to controller structure
     * @return ResultRsp object with collection of configured Policy Route status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<TrafficPolicyList>> configMqc(String ctrlUuid, String deviceId, List<TrafficPolicyList> list)
            throws ServiceException {

        ResultRsp<List<TrafficPolicyList>> resultRsp =
                new ResultRsp<List<TrafficPolicyList>>(ErrorCode.OVERLAYVPN_SUCCESS);
        if((StringUtils.isEmpty(ctrlUuid)) || (StringUtils.isEmpty(deviceId)) || CollectionUtils.isEmpty(list)) {
            LOGGER.error("configMqc : parameter error");
            return new ResultRsp<List<TrafficPolicyList>>(ErrorCode.OVERLAYVPN_FAILED);
        }

        String mqccUrl = MessageFormat.format(ControllerUrlConst.CONFIG_QOS_MQC, deviceId);
        Map<String, List<TrafficPolicyList>> crtInfoMap = new HashMap<>();
        crtInfoMap.put(TRAFIC_POLICY_LIST, list);
        LOGGER.debug("mqc policy create requestUtil = " + JsonUtil.toJson(crtInfoMap));
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(mqccUrl, JsonUtil.toJson(crtInfoMap), ctrlUuid);
        String body = httpMsg.getBody();
        LOGGER.debug("mqc policy create return body : " + body);
        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(body)) {
            OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>> acresponse = JsonUtil.fromJson(body,
                    new TypeReference<OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>>>() {});

            if(acresponse.isSucess()) {
                return new ResultRsp<List<TrafficPolicyList>>(ErrorCode.OVERLAYVPN_SUCCESS,
                        acresponse.getData().get(TRAFIC_POLICY_LIST));
            }
            LOGGER.error("qos policy create: acresponse return error: " + acresponse.getErrmsg());
            return new ResultRsp<List<TrafficPolicyList>>(DriverErrorCode.ADAPTER_QOS_CREATE_ERROR);
        }

        LOGGER.error("createIpSecByDevice: httpMsg return error" + httpMsg.getStatus());
        resultRsp.setErrorCode(DriverErrorCode.ADAPTER_QOS_CREATE_ERROR);
        resultRsp.setHttpCode(httpMsg.getStatus());
        return resultRsp;
    }

    /**
     * Deletes QOS MQC Configuration using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param idList collection of Policy Route id
     * @return ResultRsp object with deleted Policy Route status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> deleteMqc(String ctrlUuid, String deviceId, List<String> idList) throws ServiceException {

        ResultRsp<String> resultRsp = new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
        if((StringUtils.isEmpty(ctrlUuid)) || (StringUtils.isEmpty(deviceId))) {
            LOGGER.error("Invalid controller UUID or deviceId.");
            throw new ParameterServiceException("Invalid controller UUID  or deviceId.");
        }
        if(CollectionUtils.isEmpty(idList)) {
            LOGGER.error("delete qos policy ipList empty");
            return resultRsp;
        }
        String greTunnelcUrl = MessageFormat.format(ControllerUrlConst.CONFIG_QOS_MQC, deviceId);
        Map<String, List<String>> crtInfoMap = new HashMap<>();
        crtInfoMap.put(DELETE_ROUTE_PARAMETER, idList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(greTunnelcUrl, JsonUtil.toJson(crtInfoMap), ctrlUuid);
        String body = httpMsg.getBody();
        LOGGER.debug("delete qos policy return body : " + body);
        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(body)) {
            ACDelResponse acresponse = JsonUtil.fromJson(body, new TypeReference<ACDelResponse>() {});
            if(!acresponse.isSucess()) {
                LOGGER.error("delete qos policy acresponse return error" + acresponse.getErrmsg());
                resultRsp.setErrorCode(DriverErrorCode.ADAPTER_QOS_DELETE_ERROR);
                resultRsp.setMessage(acresponse.getErrmsg());
            }
            return resultRsp;
        }
        LOGGER.error("delete qos policy: httpMsg return error");
        resultRsp.setErrorCode(DriverErrorCode.ADAPTER_QOS_DELETE_ERROR);
        resultRsp.setMessage("delete qos policy: httpMsg return error");
        return resultRsp;
    }

    /**
     * Queries QOS MQC Configuration using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @return ResultRsp object with collection of queried Policy Route configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<TrafficPolicyList>> queryRouteByDevice(String ctrlUuid, String deviceId)
            throws ServiceException {
        ResultRsp<List<TrafficPolicyList>> resultRsp =
                new ResultRsp<List<TrafficPolicyList>>(ErrorCode.OVERLAYVPN_SUCCESS);

        if((StringUtils.isEmpty(ctrlUuid)) || (StringUtils.isEmpty(deviceId))) {
            LOGGER.error("configMqc: parameter error.");
            return new ResultRsp<List<TrafficPolicyList>>(ErrorCode.OVERLAYVPN_FAILED);
        }

        String mqcUrl = MessageFormat.format(ControllerUrlConst.CONFIG_QOS_MQC, deviceId);
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(mqcUrl, null, ctrlUuid);
        String body = httpMsg.getBody();
        LOGGER.debug("mqc policy query return body: " + body);

        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(body)) {
            OverlayVpnDriverResponse<ControllerNbiPolicyRoute> acresponse =
                    JsonUtil.fromJson(body, new TypeReference<OverlayVpnDriverResponse<ControllerNbiPolicyRoute>>() {});
            if(acresponse.isSucess()) {
                return new ResultRsp<List<TrafficPolicyList>>(ErrorCode.OVERLAYVPN_SUCCESS,
                        ((ControllerNbiPolicyRoute)acresponse.getData()).getTrafficPolicyList());
            }
            LOGGER.error("qos policy create: acresponse return error: " + acresponse.getErrmsg());
            return new ResultRsp<List<TrafficPolicyList>>(DriverErrorCode.ADAPTER_QOS_CREATE_ERROR);
        }

        LOGGER.error("qos policy create: httpMsg return error, http status: " + httpMsg.getStatus());
        resultRsp.setErrorCode(DriverErrorCode.ADAPTER_QOS_CREATE_ERROR);
        resultRsp.setHttpCode(httpMsg.getStatus());
        return resultRsp;
    }

    /**
     * Validates Policy route configuration.<br>
     *
     * @param sbiNePolicyRouteList collection of Policy route configuration needs to be validated
     * @param cltuuid controller UUID
     * @param failedDatas collection of failed Policy route configuration during validation
     * @param checkOkRouteList validated collection of Policy route configuration
     * @return controller UUID
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static String checkInputData(List<SbiNePolicyRoute> sbiNePolicyRouteList, String cltuuid,
                                  List<FailData<SbiNePolicyRoute>> failedDatas, List<SbiNePolicyRoute> checkOkRouteList)
            throws ServiceException {

        LOGGER.debug("create policy route input body :" + sbiNePolicyRouteList.toString());
        if(CollectionUtils.isEmpty(sbiNePolicyRouteList)) {
            LOGGER.error("route create, null neRouteList");
            SvcExcptUtil.throwBadRequestException("route create, null neRouteList");
        }

        for(SbiNePolicyRoute route : sbiNePolicyRouteList) {
            try {
                ValidationUtil.validateModel(route);
                checkOkRouteList.add(route);
            } catch(ServiceException e) {
                FailData<SbiNePolicyRoute> failData = new FailData<SbiNePolicyRoute>(
                        DriverErrorCode.CLOUDVPN_PARAMETER_INVALID, e.getMessage(), route);
                failedDatas.add(failData);
            }
        }
        return cltuuid;
    }

    /**
     * Prepares failed Policy route configuration data.<br>
     *
     * @param failedNbiDatas collection of failed Policy route configuration needs to be prepared
     * @param totalNbiRoutes Policy route configuration from service SBI
     * @param resultRsp collection of result response from controller
     * @since SDNHUB 0.5
     */
    public static void fillFailDataInfo(List<FailData<SbiNePolicyRoute>> failedNbiDatas,
                                  List<SbiNePolicyRoute> totalNbiRoutes, ResultRsp<List<TrafficPolicyList>> resultRsp) {
        for(TrafficPolicyList staticRoute : resultRsp.getData()) {
            SbiNePolicyRoute nbiRoute = findCorrespondNbiModel(staticRoute, totalNbiRoutes);
            if(null != nbiRoute) {
                FailData<SbiNePolicyRoute> failData =
                        new FailData<SbiNePolicyRoute>(resultRsp.getErrorCode(), resultRsp.getMessage(), nbiRoute);
                failedNbiDatas.add(failData);
            }
        }
    }

    /**
     * Compares Policy route configuration from service SBI to controller response.<br>
     *
     * @param staticRoute collection of result response from controller
     * @param nbiRoutes Policy route configuration from service SBI
     * @return nbiRoute valid policy route configuration from service SBI otherwise returns null
     * @since SDNHUB 0.5
     */
    private static SbiNePolicyRoute findCorrespondNbiModel(TrafficPolicyList staticRoute, List<SbiNePolicyRoute> nbiRoutes) {
        for(SbiNePolicyRoute nbiRoute : nbiRoutes) {
            if(nbiRoute.getUuid().equals(staticRoute.getUuid())) {
                return nbiRoute;
            }
        }
        return null;
    }

    /**
     * Groups Policy route configuration based on device id.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param nbiNeTunnelList collection of Policy route configuration
     * @return grouped Policy route configuration based on device id
     * @since SDNHUB 0.5
     */
    public static Map<String, List<SbiNePolicyRoute>> deriveByDeviceId(final String ctrlUuid,
                                                                        final List<SbiNePolicyRoute> nbiNeTunnelList) {
        Map<String, List<SbiNePolicyRoute>> deviceIdToTunnelListMap =
                new ConcurrentHashMap<String, List<SbiNePolicyRoute>>();
        if(CollectionUtils.isEmpty(nbiNeTunnelList)) {
            return deviceIdToTunnelListMap;
        }
        for(SbiNePolicyRoute tempNbiNeTunnel : nbiNeTunnelList) {
            if(!deviceIdToTunnelListMap.containsKey(tempNbiNeTunnel.getInterfaceName())) {
                deviceIdToTunnelListMap.put(tempNbiNeTunnel.getInterfaceName(), new ArrayList<SbiNePolicyRoute>());
            }
            deviceIdToTunnelListMap.get(tempNbiNeTunnel.getInterfaceName()).add(tempNbiNeTunnel);
        }
        return deviceIdToTunnelListMap;
    }
}
