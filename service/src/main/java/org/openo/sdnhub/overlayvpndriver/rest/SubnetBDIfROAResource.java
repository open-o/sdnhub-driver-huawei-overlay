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
import org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetBDIfImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetBdInfoModel;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst.CTRL_HEADER_PARAM;
import static org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst.DEVICE_ID_PATH_PARAM;
import static org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst.VNI_PATH_PARAM;

/**
 * Restful interface for Vni-related BD information.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jun 19, 2017
 */
@Service
@Path("/sbi-localsite/v1")
public class SubnetBDIfROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubnetBDIfROAResource.class);

    @Autowired
    private SubnetBDIfImpl subnetBDIf;

    /**
     * Queries Vni-related BD information using a specific Controller.<br/>
     *
     * @param request HTTP request
     * @param deviceId device id
     * @param vni vni information
     * @param ctrlUuidParam Controller UUID
     * @return ResultRsp object with Vni-related BD queried information status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/device/{deviceid}/bdinfo/vni/{vni}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiSubnetBdInfoModel> queryBDIf(@Context HttpServletRequest request,
            @PathParam(DEVICE_ID_PATH_PARAM) String deviceId, @PathParam(VNI_PATH_PARAM) String vni,
            @HeaderParam(CTRL_HEADER_PARAM) String ctrlUuidParam) throws ServiceException {

        long startTime = System.currentTimeMillis();

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        SbiSubnetBdInfoModel model = new SbiSubnetBdInfoModel();
        model.setControllerId(ctrlUuid);
        model.setDeviceId(deviceId);
        model.setVni(vni);

        ResultRsp<SbiSubnetBdInfoModel> rsp = subnetBDIf.queryBDInfo(model, ctrlUuid, deviceId);
        LOGGER.debug("Query bdInfo error cost {} ms.", System.currentTimeMillis() - startTime);

        if(!rsp.isValid()) {
            LOGGER.error("Query bdInfo operation err.");
            SvcExcptUtil.throwBadRequestException("Query bdInfo operation err.");
        }
        return rsp;
    }
}
