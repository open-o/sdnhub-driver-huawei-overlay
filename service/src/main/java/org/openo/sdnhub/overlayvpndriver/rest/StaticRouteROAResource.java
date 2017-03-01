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

import org.apache.commons.collections.CollectionUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiStaticRoute;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.StaticRouteImpl;
import org.openo.sdnhub.overlayvpndriver.translator.StaticRouteConvert;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNeStaticRoute;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Restful interface class for StaticRoute service.<br>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
@Service
@Path("/sbi-route/v1")
public class StaticRouteROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticRouteROAResource.class);

    @Autowired
    private StaticRouteImpl staticRouteService = null;

    private static final String INVALIDCTRLUUID = "invalid controller UUID.";

    /**
     * Query static routes<br/>
     *
     * @param ctrlUuidParam Controller UUID
     * @param staticRouteList Static route list to be queried
     * @return ResultRsp object with static route list data.
     * @throws ServiceException In case of any query exception
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/batch-query-static-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNeStaticRoute> queryRoutes(@HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            List<SbiNeStaticRoute> staticRouteList) throws ServiceException {

        LOGGER.debug("create static route");

        ResultRsp<SbiNeStaticRoute> totalResult = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        List<SbiNeStaticRoute> successedDatas = new ArrayList<>();
        List<FailData<SbiNeStaticRoute>> failedDatas = new ArrayList<>();

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }
        if(CollectionUtils.isEmpty(staticRouteList)) {
            throw new ParameterServiceException("query static route : request body is null or empty");
        }

        for (SbiNeStaticRoute sbiNeStaticRoute : staticRouteList) {
            ResultRsp<List<ControllerNbiStaticRoute>> resultRsp =
                    staticRouteService.queryRouteByDevice(ctrlUuid, sbiNeStaticRoute.getDeviceId(),
                                                          sbiNeStaticRoute.getDestIp(),
                                                          sbiNeStaticRoute.getExternalId());

            if (resultRsp.isValid()) {
                successedDatas.add(sbiNeStaticRoute);
            } else {
                FailData<SbiNeStaticRoute> failData = new FailData<>(resultRsp.getErrorCode(),
                        resultRsp.getMessage(),sbiNeStaticRoute);
                failedDatas.add(failData);
            }
        }

        totalResult.getSuccessed().addAll(successedDatas);
        totalResult.setFail(failedDatas);

        if(CollectionUtils.isNotEmpty(failedDatas)) {
            totalResult.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
        }
        return totalResult;
    }

    /**
     * Create static routes.<br/>
     *
     * @param ctrlUuidParam Controller UUID
     * @param neStaticRoutes
     * @return ResultRsp Object for list of created static routes.
     * @throws ServiceException In case of any query exception
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/batch-create-static-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNeStaticRoute> createRoute(@HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            List<SbiNeStaticRoute> neStaticRoutes) throws ServiceException {
        long beginTime = System.currentTimeMillis();
        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }
        ResultRsp<SbiNeStaticRoute> totalResult = new ResultRsp<>();
        if(CollectionUtils.isEmpty(neStaticRoutes)) {
            throw new ParameterServiceException("Empty list of static routes recieved");
        }

        List<SbiNeStaticRoute> successData = new ArrayList<>();
        List<FailData<SbiNeStaticRoute>> failDatas = new ArrayList<>();

        for(SbiNeStaticRoute neStaticRoute : neStaticRoutes) {
            ValidationUtil.validateModel(neStaticRoute);
        }

        Map<String, List<ControllerNbiStaticRoute>> neIdTunnelsMap =
                StaticRouteConvert.convert2Route(neStaticRoutes, true);
        for(Entry<String, List<ControllerNbiStaticRoute>> entry : neIdTunnelsMap.entrySet()) {
            ResultRsp<List<ControllerNbiStaticRoute>> resultRsp =
                    staticRouteService.configStaticRoute(ctrlUuid, entry.getKey(), entry.getValue(), true);

            if(resultRsp.isSuccess()) {
                StaticRouteConvert.backWriteId2NeStaticRoute(resultRsp.getData(), neStaticRoutes, entry.getKey(), successData);
            } else {
                resultRsp.setData(entry.getValue());
                StaticRouteConvert.fillFailDataInfo(failDatas, neStaticRoutes, resultRsp);
            }
        }
        totalResult.setSuccessed(successData);
        totalResult.setFail(failDatas);
        if (!failDatas.isEmpty()) {
            totalResult.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
        }

        LOGGER.info("Static route create end. cost:{}", System.currentTimeMillis() - beginTime);
        return totalResult;
    }

    /**
     * Delete static routes.<br/>
     *
     * @param ctrlUuidParam Controller UUID
     * @param deviceId
     * @param neStaticRoutes
     * @return ResultRsp Object for deleted static routes.
     * @throws ServiceException In case of any query exception
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceid}/batch-delete-static-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> deleteRoute(@HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            @PathParam("deviceid") String deviceId, List<SbiNeStaticRoute> neStaticRoutes) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        UuidUtil.validate(deviceId);
        if(CollectionUtils.isEmpty(neStaticRoutes)) {
            throw new ParameterServiceException("delete static route : body is null or empty");
        }

        List<String> routerIds = new ArrayList<>();
        for(SbiNeStaticRoute router : neStaticRoutes) {
            String routerId = router.getExternalId();
            UuidUtil.validate(routerId);
            routerIds.add(routerId);
        }
        UuidUtil.validate(routerIds);

        return staticRouteService.deleteRouteByDevice(ctrlUuid, deviceId, routerIds);
    }

    /**
     * Update static routes.<br/>
     *
     * @param ctrlUuidParam Controller UUID
     * @param staticRouteList
     * @return ResultRsp Object for list of updated static routes.
     * @throws ServiceException In case of any query exception
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/batch-update-static-routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNeStaticRoute> updateRoute(@HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
                                                   List<SbiNeStaticRoute> staticRouteList) throws ServiceException {

        long beginTime = System.currentTimeMillis();
        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        ResultRsp<SbiNeStaticRoute> totalResult = new ResultRsp<>();
        List<SbiNeStaticRoute> successData = new ArrayList<>();
        List<FailData<SbiNeStaticRoute>> failedData = new ArrayList<>();

        Map<String, List<ControllerNbiStaticRoute>> deviceIdToRouteMap =
                StaticRouteConvert.convert2Route(staticRouteList, false);

        for(Entry<String, List<ControllerNbiStaticRoute>> entry : deviceIdToRouteMap.entrySet()) {
            ResultRsp<List<ControllerNbiStaticRoute>> resultRsp =
                    staticRouteService.configStaticRoute(ctrlUuid, entry.getKey(), entry.getValue(), false);

            if(resultRsp.isSuccess()) {
                // If List<ControllerNbiStaticRoute processed correctly
                // then fill the successData with corresponding SbiNeStaticRoute.
                // Else populate failedData.
                for(SbiNeStaticRoute route : staticRouteList) {
                    for(ControllerNbiStaticRoute cNbiStaticRoute : entry.getValue()) {
                        if(route.getExternalId().equals(cNbiStaticRoute.getId())) {
                            successData.add(route);
                        }
                    }
                }
            } else {
                for(SbiNeStaticRoute route : staticRouteList) {
                    for(ControllerNbiStaticRoute cNbiStaticRoute : entry.getValue()) {
                        if(route.getExternalId().equals(cNbiStaticRoute.getId())) {
                            FailData<SbiNeStaticRoute> failData = new FailData<>();
                            failData.setData(route);
                        }
                    }
                }
            }
        }

        totalResult.setSuccessed(successData);
        totalResult.setFail(failedData);

        if(CollectionUtils.isNotEmpty(failedData)) {
            totalResult.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
        }

        LOGGER.debug("static route update end. cost:{}", System.currentTimeMillis() - beginTime);

        return totalResult;
    }
}
