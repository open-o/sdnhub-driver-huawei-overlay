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
import org.openo.sdnhub.overlayvpndriver.sbi.impl.InterfaceIpServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiInterfaceIpConfig;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Restful interface for InterfaceIpROAResource.<br>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
@Service
@Path("/sbi-localsite/v1")
public class InterfaceIpROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceIpROAResource.class);

    @Autowired
    private InterfaceIpServiceImpl interfaceIpService;

    /**
     * Update IP related configuration for interface.<br/>
     *
     * @param request Http request context
     * @param ctrlUuidParam Controller UUID
     * @param deviceId Device ID to be updated
     * @param interfaceIpConfigList List of IP configuration for the interfaces
     * @return ResultRsp for updated interface
     * @throws ServiceException In case of update operation fails
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/deviceid/{deviceid}/interfaces")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<List<SbiInterfaceIpConfig>> updateInterfaceIp(@Context HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("deviceid") String deviceId,
            List<SbiInterfaceIpConfig> interfaceIpConfigList) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(!StringUtils.hasLength(deviceId)) {
            LOGGER.error("deviceId is null");
            throw new ParameterServiceException("deviceId is null");
        }

        if(CollectionUtils.isEmpty(interfaceIpConfigList)) {
            LOGGER.error("interfaces is null");
            throw new ParameterServiceException("interface is null");
        }
        for(SbiInterfaceIpConfig model : interfaceIpConfigList) {
            ValidationUtil.validateModel(model);
        }

        ResultRsp<List<SbiInterfaceIpConfig>> result =
                interfaceIpService.updateInterfaceIpList(ctrlUuid, deviceId, interfaceIpConfigList);
        return result;
    }

    /**
     * Query IP related configuration for interface.<br/>
     *
     * @param request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId Device ID to be queried
     * @return ResultRsp for queried interface
     * @throws ServiceException In case of query operation fails
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/deviceid/{deviceuuid}/interfaces")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<List<SbiInterfaceIpConfig>> queryInterfaceIp(@Context HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("deviceuuid") String deviceId)
            throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(!StringUtils.hasLength(deviceId)) {
            LOGGER.error("deviceId is null");
            throw new ParameterServiceException("deviceId is null");
        }

        ResultRsp<List<SbiInterfaceIpConfig>> response = interfaceIpService.queryInterfaceIps(ctrlUuid, deviceId);
        return response;
    }
}
