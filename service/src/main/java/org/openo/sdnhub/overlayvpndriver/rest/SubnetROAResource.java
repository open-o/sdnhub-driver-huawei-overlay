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
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
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

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    private static final String INVALIDCTRLUUID = "Invalid controller UUID.";
    /**
     * Creates subnet.<br/>
     *
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
    public ResultRsp<SbiSubnetNetModel> createSubnet(@PathParam("deviceuuid") String deviceId,
                                                     @HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
                                                     SbiSubnetNetModel subnet) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        ValidationUtil.validateModel(subnet);
        ACNetwork network = SubnetConvert.buildAcNetwork(subnet);

        long beginTime = System.currentTimeMillis();
        ResultRsp<SbiSubnetNetModel> acResult = subnetService.createSubnet(network, ctrlUuid, deviceId);
        LOGGER.debug("create subnet cost {} ms.", System.currentTimeMillis() - beginTime);

        // Failed case already checked in SubnetServiceImpl.createSubnet() and thrown exception.
        subnet.setNetworkId(acResult.getData().getNetworkId());
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, subnet);
    }

    /**
     * Update subnet configuration.<br/>
     *
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
    public ResultRsp<SbiSubnetNetModel> updateSubnet(@PathParam("deviceuuid") String deviceId,
                                                     @HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
                                                     SbiSubnetNetModel subnet) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        ValidationUtil.validateModel(subnet);
        long beginTime = System.currentTimeMillis();
        ACNetwork network = SubnetConvert.buildUpdateAcNetwork(subnet, ctrlUuid, deviceId);

        ResultRsp<ACNetwork> acResult = subnetService.updateSubnet(network, ctrlUuid, deviceId);
        LOGGER.debug("Update subnet cost {} ms.", System.currentTimeMillis() - beginTime);

        if (!acResult.isValid())
        {
            LOGGER.error("Update subnet: controller operation error.");
            SvcExcptUtil.throwBadRequestException("Update subnet: controller operation error.");
        }

        return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, subnet);
    }

    /**
     * Delete subnet.<br/>
     *
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
    public ResultRsp<String> deleteSubnet(@PathParam("deviceuuid") String deviceId,
            @PathParam("networkid") String networkId, @HeaderParam("X-Driver-Parameter") String ctrlUuidParam)
            throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        LOGGER.error("AC delete subnet.networkId={}", networkId);
        long beginTime = System.currentTimeMillis();
        ResultRsp<String> acResult = subnetService.deleteSubnet(networkId, ctrlUuid, deviceId);
        LOGGER.debug("Delete subnet cost {} ms.", System.currentTimeMillis() - beginTime);

        if (!acResult.isSuccess()) {
            LOGGER.error("Delete subnet: controller operation error.");
            SvcExcptUtil.throwBadRequestException("Delete subnet: controller operation error.");
        }
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
    }

    /**
     * Query available subnets.<br/>
     *
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
    public ResultRsp<SbiSubnetNetModel> getSubnet(@PathParam("deviceuuid") String deviceId,
                                                  @PathParam("networkid") String networkId,
                                                  @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        LOGGER.debug("get subnet.networkId={}", networkId);
        long beginTime = System.currentTimeMillis();

        ACNetwork acNetwork = SubnetConvert.getNetworkById(networkId, ctrlUuid, deviceId);
        SbiSubnetNetModel subnetNetModel = new SbiSubnetNetModel(deviceId, ctrlUuid, networkId);
        if (acNetwork == null)
        {
            LOGGER.info("Query subnet cost {} ms,body is :null.", System.currentTimeMillis() - beginTime);
            return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, subnetNetModel);
        }
        subnetNetModel.setDescription(acNetwork.getDescription());
        LOGGER.info("Query subnet cost {} ms,body is :{}.", System.currentTimeMillis() - beginTime,
                acNetwork.toString());

        return new ResultRsp<>(DriverErrorCode.OVERLAYVPN_SUCCESS, subnetNetModel);
    }
}
