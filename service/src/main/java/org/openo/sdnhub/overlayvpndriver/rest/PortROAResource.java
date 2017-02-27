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
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcDevicePort;
import org.openo.sdnhub.overlayvpndriver.controller.model.Ip;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.DevicePortServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIp;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.CheckStrUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst.CTRL_HEADER_PARAM;

/**
 * Restful interface for interface information.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jun 19, 2017
 */
@Service
@Path("/sbi-ipsec/v1")
public class PortROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortROAResource.class);

    private static final String INVALIDCTRLUUID = "Invalid controller UUID.";

    /**
     * Query interface information of the current device using a specific controller.<br>
     *
     * @param request HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId device id
     * @param portName port name for query
     * @return ResultRsp object with IP configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/device/{deviceId}/ports")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiIp> queryPortIpByPortName(@Context HttpServletRequest request,
            @HeaderParam(CTRL_HEADER_PARAM) String ctrlUuidParam, @PathParam("deviceId") String deviceId,
            @QueryParam("portName") String portName)
            throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALIDCTRLUUID);
            throw new ParameterServiceException(INVALIDCTRLUUID);
        }

        if(null == deviceId || deviceId.isEmpty()) {
            throw new ParameterServiceException("device-id is null or empty");
        }

        CheckStrUtil.checkUuidStr(deviceId);

        if(null == portName || portName.isEmpty()) {
            throw new ParameterServiceException("Port Name is null or empty in query parameter");
        }

        if (portName.contains(CommonConst.LOOP_BACK_PORT)) {
            return DevicePortServiceImpl.queryLoopBack(ctrlUuid, deviceId, portName);
        }

        List<AcDevicePort> acDevicePorts = DevicePortServiceImpl.queryPorts(deviceId, ctrlUuid, Arrays.asList(portName));
        return DevicePortServiceImpl.traslateDevicePortToIp(acDevicePorts.get(0), portName, deviceId, ctrlUuid);
    }
}
