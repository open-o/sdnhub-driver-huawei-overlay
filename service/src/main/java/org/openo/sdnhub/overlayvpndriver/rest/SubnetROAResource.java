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

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACNetwork;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetNetModel;
import org.openo.sdnhub.overlayvpndriver.translator.SubnetConvert;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Restful interface for subnet resource.<br/>
 *
 * @author
 * @version SDNHUB 0.5 03-Feb-2017
 */
@Service
@Path("/sbi-localsite/v1")
public class SubnetROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubnetROAResource.class);

    @Autowired
    private SubnetServiceImpl subnetService;

    /**
     * Creates subnet.<br/>
     *
     * @param request       HTTP request
     * @param deviceId      Device Id
     * @param ctrlUuidParam Controller UUID
     * @param subnet Subnet model
     * @return ResultRsp from created subnet
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceuuid}/subnet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiSubnetNetModel> createSubnet(@Context HttpServletRequest request,
            @PathParam("deviceuuid") String deviceId, @HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            SbiSubnetNetModel subnet) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        ValidationUtil.validateModel(subnet);
        ACNetwork network = SubnetConvert.buildAcNetwork(subnet);

        long beginTime = System.currentTimeMillis();
        ResultRsp<SbiSubnetNetModel> acResult = subnetService.createSubnet(network, ctrlUuid, deviceId);
        LOGGER.debug("create subnet cost {} ms.", System.currentTimeMillis() - beginTime);

        if (!acResult.isValid()) {
            LOGGER.error("Create subnet: controller operation error.");
            SvcExcptUtil.throwBadRequestException("Create subnet: controller operation error.");
        }

        subnet.setNetworkId(acResult.getData().getNetworkId());
        return new ResultRsp<SbiSubnetNetModel>(ErrorCode.OVERLAYVPN_SUCCESS, subnet);
    }

    /**
     * Update subnet configuration.<br/>
     *
     * @param request       HTTP request
     * @param deviceId      Device Id
     * @param ctrlUuidParam Controller UUID
     * @param subnet Subnet model
     * @return ResultRsp for updated subnet
     * @throws ServiceException In case of update operation fails
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/device/{deviceuuid}/subnet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiSubnetNetModel> updateSubnet(@Context HttpServletRequest request,
            @PathParam("deviceuuid") String deviceId, @HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            SbiSubnetNetModel subnet) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        ValidationUtil.validateModel(subnet);
        long beginTime = System.currentTimeMillis();
        ACNetwork network = SubnetConvert.buildUpdateAcNetwork(subnet, ctrlUuid, deviceId);

        ResultRsp<ACNetwork> acResult = subnetService.updateSubnet(subnet, ctrlUuid, deviceId);
        LOGGER.debug("Update subnet cost {} ms.", System.currentTimeMillis() - beginTime);

        if (!acResult.isValid())
        {
            LOGGER.error("Update subnet: controller operation error.");
            SvcExcptUtil.throwBadRequestException("Update subnet: controller operation error.");
        }

        return new ResultRsp<SbiSubnetNetModel>(ErrorCode.OVERLAYVPN_SUCCESS, subnet);
    }

    /**
     * Delete subnet.<br/>
     *
     * @param request       HTTP request
     * @param deviceId      Device Id
     * @param networkId
     * @param ctrlUuidParam Controller UUID
     * @return ResultRsp for deleted subnet
     * @throws ServiceException In case of delete operation fails
     * @since SDNHUB 0.5
     */
    @DELETE
    @Path("device/{deviceuuid}/subnet/{networkid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> deleteSubnet(@Context HttpServletRequest request, @PathParam("deviceuuid") String deviceId,
            @PathParam("networkid") String networkId, @HeaderParam("X-Driver-Parameter") String ctrlUuidParam)
            throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        LOGGER.error("AC delete subnet.networkId={}", networkId);
        long beginTime = System.currentTimeMillis();
        ResultRsp<String> acResult = subnetService.deleteSubnet(networkId, ctrlUuid, deviceId);
        LOGGER.debug("Delete subnet cost {} ms.", System.currentTimeMillis() - beginTime);

        if (!acResult.isSuccess()) {
            LOGGER.error("Delete subnet: controller operation error.");
            SvcExcptUtil.throwBadRequestException("Delete subnet: controller operation error.");
        }
        return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
    }

    /**
     * Query available subnets.<br/>
     *
     * @param request       HTTP request
     * @param deviceId      Device Id
     * @param networkId     Nework Id
     * @param ctrlUuidParam Controller UUID
     * @return ResultRsp for queried subnet
     * @throws ServiceException In case of query operation fails
     * @since SDNHUB 0.5
     */
    @GET
    @Path("device/{deviceuuid}/subnet/{networkid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiSubnetNetModel> getSubnet(@Context HttpServletRequest request,
            @PathParam("deviceuuid") String deviceId, @PathParam("networkid") String networkId,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        LOGGER.debug("get subnet.networkId={}", networkId);
        long beginTime = System.currentTimeMillis();

        ACNetwork acNetwork = SubnetConvert.getNetworkById(networkId, ctrlUuid, deviceId);
        SbiSubnetNetModel subnetNetModel = new SbiSubnetNetModel(deviceId, ctrlUuid, networkId);
        if (acNetwork == null)
        {
            LOGGER.info("Query subnet cost {} ms,body is :null.", System.currentTimeMillis() - beginTime);
            return new ResultRsp<SbiSubnetNetModel>(ErrorCode.OVERLAYVPN_SUCCESS, subnetNetModel);
        }
        subnetNetModel.setDescription(acNetwork.getDescription());
        LOGGER.info("Query subnet cost {} ms,body is :{}.", System.currentTimeMillis() - beginTime,
                acNetwork.toString());

        return new ResultRsp<SbiSubnetNetModel>(ErrorCode.OVERLAYVPN_SUCCESS, subnetNetModel);
    }
}
