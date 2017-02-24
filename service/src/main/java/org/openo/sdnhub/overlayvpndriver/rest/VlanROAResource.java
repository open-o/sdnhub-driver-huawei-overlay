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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.EthInterfaceConfig;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.VlanServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIfVlan;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Restful interface for Vlan configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jun 19, 2017
 */
@Service
@Path("/sbi-localsite/v1")
public class VlanROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(VlanROAResource.class);

    @Autowired
    private VlanServiceImpl vlanService;

    /**
     * Adds new interface Vlan configuration using a specific Controller.<br>
     *
     * @param request HTTP request
     * @param deviceId device id
     * @param ctrlUuidParam Controller UUID
     * @param ifVlanList collection of interface Vlan configuration
     * @return ResultRsp object with collection of interface Vlan added configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceuuid}/vlan")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<List<SbiIfVlan>> createVlan(@Context HttpServletRequest request,
            @PathParam(CommonConst.DEVICE_UUID_PATH_PARAM) String deviceId,
            @HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
            List<SbiIfVlan> ifVlanList) throws ServiceException {

        long startTime = System.currentTimeMillis();

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(CollectionUtils.isEmpty(ifVlanList)) {
            LOGGER.error("create vlan input param err.");
            throw new ParameterServiceException("create vlan input param err.");
        }

        final List<EthInterfaceConfig> interfaceConfig =
                vlanService.combineCreateLanEthConfig(ctrlUuid, deviceId, ifVlanList);

        List<EthInterfaceConfig> configRsp = vlanService.configEth(ctrlUuid, deviceId, interfaceConfig);
        List<SbiIfVlan> ifVlans = vlanService.buildCreateIfVlanRsp(configRsp, ifVlanList);
        LOGGER.debug("create IfVlan cost {} ms.", System.currentTimeMillis() - startTime);

        return new ResultRsp<List<SbiIfVlan>>(ErrorCode.OVERLAYVPN_SUCCESS, ifVlans);

    }

    /**
     * Updates interface Vlan configuration using a specific Controller.<br>
     *
     * @param request HTTP request
     * @param deviceId device id
     * @param ctrlUuidParam Controller UUID
     * @param ifVlanList collection of interface Vlan configuration
     * @return ResultRsp object with collection of interface Vlan updated configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/device/{deviceuuid}/vlan")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<List<SbiIfVlan>> updateVlan(@Context HttpServletRequest request,
            @PathParam(CommonConst.DEVICE_UUID_PATH_PARAM) String deviceId,
            @HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
            List<SbiIfVlan> ifVlanList) throws ServiceException {

        long startTime = System.currentTimeMillis();
        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(CollectionUtils.isEmpty(ifVlanList)) {
            LOGGER.error("update vlan input param err.");
            throw new ParameterServiceException("update vlan input param err.");
        }

        final List<EthInterfaceConfig> interfaceConfig =
                vlanService.combineLanEthConfig(ctrlUuid, deviceId, ifVlanList);
        List<EthInterfaceConfig> configRsp = vlanService.configEth(ctrlUuid, deviceId, interfaceConfig);
        List<SbiIfVlan> ifVlans = vlanService.buildIfVlanRsp(configRsp);
        LOGGER.debug("Vlan update cost {} ms.", System.currentTimeMillis() - startTime);
        return new ResultRsp<List<SbiIfVlan>>(ErrorCode.OVERLAYVPN_SUCCESS, ifVlans);
    }

    /**
     * Queries interface Vlan configuration using a specific Controller.<br>
     *
     * @param request HTTP request
     * @param deviceId device id
     * @param ids vlan ids
     * @param ctrlUuidParam Controller UUID
     * @return ResultRsp object with collection of interface Vlan queried configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/device/{deviceuuid}/vlan/{ids}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<List<SbiIfVlan>> queryVlan(@Context HttpServletRequest request,
            @PathParam(CommonConst.DEVICE_UUID_PATH_PARAM) String deviceId,
            @PathParam(CommonConst.IDS_PATH_PARAM) String ids,
            @HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam) throws ServiceException {

        long startTime = System.currentTimeMillis();
        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }
        List<EthInterfaceConfig> interfaceConfig = vlanService.queryEthByIds(ctrlUuid, deviceId, ids);
        List<SbiIfVlan> ifVlans = vlanService.buildIfVlanRsp(interfaceConfig);
        LOGGER.debug("Vlan query cost {} ms.", System.currentTimeMillis() - startTime);
        return new ResultRsp<List<SbiIfVlan>>(ErrorCode.OVERLAYVPN_SUCCESS, ifVlans);
    }
}
