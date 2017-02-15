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

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.DevicePortServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIp;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.CheckStrUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst.CTRL_HEADER_PARAM;

/**
 * Restful interface for interface information.<br>
 *
 * @author Mahesh
 * @version SDNHUB 0.5 Jun 19, 2017
 */
@Service
@Path("/sbi-ipsec/v1")
public class PortROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortROAResource.class);

    /**
     * Query interface information of the current device using a specific controller.<br>
     *
     * @param request HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId device id
     * @return ResultRsp object with IP configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/device/{deviceId}/ports")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiIp> queryPortIpByPortName(@Context HttpServletRequest request,
            @HeaderParam(CTRL_HEADER_PARAM) String ctrlUuidParam, @PathParam("deviceId") String deviceId)
            throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Query ports begin time = " + beginTime + ", deviceId = " + deviceId);

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(null == deviceId || deviceId.isEmpty()) {
            throw new ParameterServiceException("device-id is null or empty");
        }

        CheckStrUtil.checkUuidStr(deviceId);
        ResultRsp<SbiIp> result =  DevicePortServiceImpl.queryPorts(deviceId, ctrlUuid);
        LOGGER.debug("Query ports end time = " + (System.currentTimeMillis() - beginTime));
        return result;
    }
}
