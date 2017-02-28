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

package org.openo.sdnhub.overlayvpndriver.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.CollectionUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficPolicyList;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.PolicyRouteImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNePolicyRoute;
import org.openo.sdnhub.overlayvpndriver.translator.PolicyRouteConvert;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.CheckStrUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Restful interface for Policy Route configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jun 19, 2017
 */
@Service
@Path("/sbi-route/v1")
public class PolicyRouteROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyRouteROAResource.class);

    @Autowired
    private PolicyRouteImpl policyRouteService = null;

    private static final String INVALIDCTRLUUID = "Invalid controller UUID.";

    /**
     * Adds new QOS MQC Configuration using a specific Controller.<br>
     * @param ctrlUuidParam Controller UUID
     * @param sbiNePolicyRouteList collection of Policy Route configuration
     *
     * @return ResultRsp object with added Policy Route configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/batch-create-policy-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNePolicyRoute> routeCreate(@HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
            List<SbiNePolicyRoute> sbiNePolicyRouteList) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        if(CollectionUtils.isEmpty(sbiNePolicyRouteList)) {
            LOGGER.error("Policy route create, input param has empty body.");
            throw new ParameterServiceException("Policy route create, input param has empty body.");
        }

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Route create begin time = " + beginTime);

        ResultRsp<SbiNePolicyRoute> totalResult = new ResultRsp<>(DriverErrorCode.CLOUDVPN_SUCCESS);
        List<SbiNePolicyRoute> successedDatas = new ArrayList<>();

        List<FailData<SbiNePolicyRoute>> failedDatas = new ArrayList<>();
        List<SbiNePolicyRoute> checkOkroutelist = new ArrayList<>();
        PolicyRouteImpl.checkInputData(sbiNePolicyRouteList, failedDatas, checkOkroutelist);
        Map<String, List<TrafficPolicyList>> deviceIdToMqcMap =
                PolicyRouteConvert.convert2Route(checkOkroutelist);

        for(Entry<String, List<TrafficPolicyList>> entry : deviceIdToMqcMap.entrySet()) {
            ResultRsp<List<TrafficPolicyList>> resultRsp =
                    policyRouteService.configMqc(ctrlUuid, entry.getKey(), entry.getValue());

            if(resultRsp.isSuccess()) {
                PolicyRouteConvert.backWriteId2NePolicyRoute(resultRsp.getData(), checkOkroutelist, successedDatas);
            } else {
                resultRsp.setData(entry.getValue());
                PolicyRouteImpl.fillFailDataInfo(failedDatas, checkOkroutelist, resultRsp);
            }
        }
        LOGGER.debug("mqc policy create cost time = " + (System.currentTimeMillis() - beginTime));
        totalResult.setSuccessed(successedDatas);
        totalResult.setFail(failedDatas);

        if(CollectionUtils.isNotEmpty(failedDatas)) {
            totalResult.setErrorCode(DriverErrorCode.CLOUDVPN_FAILED);
        }
        return totalResult;
    }

    /**
     * Updates QOS MQC Configuration using a specific Controller.<br>
     * @param ctrlUuidParam Controller UUID
     * @param SbiNePolicyRouteList collection of Policy Route configuration
     *
     * @return ResultRsp object with updated Policy Route configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/batch-update-policy-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNePolicyRoute> routeUpdate(@HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
            List<SbiNePolicyRoute> sbiNePolicyRouteList) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Route update begin time = " + beginTime);

        ResultRsp<SbiNePolicyRoute> totalResult = new ResultRsp<>(DriverErrorCode.CLOUDVPN_SUCCESS  );
        List<SbiNePolicyRoute> successedDatas = new ArrayList<>();
        List<FailData<SbiNePolicyRoute>> failedDatas = new ArrayList<>();
        List<SbiNePolicyRoute> checkOkroutelist = new ArrayList<>();
        PolicyRouteImpl.checkInputData(sbiNePolicyRouteList, failedDatas, checkOkroutelist);
        Map<String, List<TrafficPolicyList>> deviceIdToMqcMap =
                PolicyRouteConvert.convert2Route(checkOkroutelist);

        for(Map.Entry<String, List<TrafficPolicyList>> entry : deviceIdToMqcMap.entrySet()) {
            ResultRsp<List<TrafficPolicyList>> resultRsp =
                    policyRouteService.configMqc(ctrlUuid, entry.getKey(), entry.getValue());
            if(resultRsp.isSuccess()) {
                PolicyRouteConvert.backWriteId2NePolicyRoute(resultRsp.getData(), checkOkroutelist, successedDatas);
            } else {
                resultRsp.setData(entry.getValue());
                PolicyRouteImpl.fillFailDataInfo(failedDatas, checkOkroutelist, resultRsp);
            }
        }

        totalResult.setSuccessed(successedDatas);
        totalResult.setFail(failedDatas);

        if(CollectionUtils.isNotEmpty(failedDatas)) {
            totalResult.setErrorCode(DriverErrorCode.CLOUDVPN_FAILED);
        }

        return totalResult;
    }

    /**
     * Deletes QOS MQC Configuration using a specific Controller.<br>
     * @param ctrlUuidParam Controller UUID
     * @param deviceId device id
     * @param routeIds collection of Policy Route Id
     *
     * @return ResultRsp object with updated Policy Route information status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceid}/batch-delete-policy-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> routeBatchDelete(@HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
            @PathParam("deviceid") String deviceId, List<String> routeIds) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        LOGGER.debug(" routeBatchDelete cltuuid:" + ctrlUuidParam);
        CheckStrUtil.checkUuidStr(deviceId);
        CheckStrUtil.checkUuidStr(routeIds);
        if(CollectionUtils.isEmpty(routeIds)) {
            LOGGER.error("policy route delete, input param router id is empty");
            throw new ParameterServiceException("route delete, input param router id is empty");

        }

        LOGGER.debug("begin delete route: ctrlUuid = " + ctrlUuid + ", deviceId = " + deviceId + "routeIds ="
                + JsonUtil.toJson(routeIds));
        long beginTime = System.currentTimeMillis();
        LOGGER.debug("delete qos policy begin time = " + beginTime);
        ResultRsp<String> result = policyRouteService.deleteMqc(ctrlUuid, deviceId, routeIds);
        LOGGER.debug("delete qos policy cost time = " + (System.currentTimeMillis() - beginTime));
        return result;
    }

    /**
     * Queries QOS MQC Configuration using a specific Controller.<br>
     * @param ctrlUuidParam Controller UUID
     * @param SbiNePolicyRouteList collection of Policy Route configuration
     *
     * @return ResultRsp object with updated Policy Route configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/batch-query-policy-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNePolicyRoute> routeQuery(@HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
            List<SbiNePolicyRoute> sbiNePolicyRouteList) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("route query begin time = " + beginTime);

        ResultRsp<SbiNePolicyRoute> totalResult = new ResultRsp<>(DriverErrorCode.CLOUDVPN_SUCCESS);
        List<SbiNePolicyRoute> successedDatas = new ArrayList<>();
        List<FailData<SbiNePolicyRoute>> failedDatas = new ArrayList<>();
        List<SbiNePolicyRoute> checkOkRouteList = new ArrayList<>();

        PolicyRouteImpl.checkInputData(sbiNePolicyRouteList, failedDatas, checkOkRouteList);
        Map<String, List<SbiNePolicyRoute>> deviceIdToRouteListMap =
                PolicyRouteImpl.deriveByDeviceId(ctrlUuid, checkOkRouteList);
        for(Map.Entry<String, List<SbiNePolicyRoute>> entry : deviceIdToRouteListMap.entrySet()) {
            ResultRsp<List<TrafficPolicyList>> queryRsp =
                    policyRouteService.queryRouteByDevice(ctrlUuid, entry.getKey());

            if(CollectionUtils.isNotEmpty(queryRsp.getData())) {

                Set<String> queryIdSet = new HashSet<>();
                for(TrafficPolicyList trafficPolicy : queryRsp.getData()) {
                    queryIdSet.add(trafficPolicy.getId());
                }

                for(SbiNePolicyRoute nbiNeTunnel : entry.getValue()) {
                    if(queryIdSet.contains(nbiNeTunnel.getExternalId())) {
                        successedDatas.add(nbiNeTunnel);
                    } else {
                        FailData<SbiNePolicyRoute> failData = new FailData<>(
                                DriverErrorCode.CLOUDVPN_FAILED, "can't find", nbiNeTunnel);
                        failedDatas.add(failData);
                    }
                }
            } else {
                for(SbiNePolicyRoute nbiNeTunnel : entry.getValue()) {
                    FailData<SbiNePolicyRoute> failData =
                            new FailData<>(DriverErrorCode.CLOUDVPN_FAILED, "can't find", nbiNeTunnel);
                    failedDatas.add(failData);
                }
            }
        }
        LOGGER.debug("mqc policy query cost time = " + (System.currentTimeMillis() - beginTime));

        totalResult.setSuccessed(successedDatas);
        totalResult.setFail(failedDatas);
        return totalResult;
    }
}
