/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.overlayvpndriver.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.CollectionUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.netmodel.ipsec.NeIpSecConnection;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdno.overlayvpndriver.service.ipsec.IpSecSvcImpl;
import org.openo.sdno.overlayvpndriver.util.convertmodel.IpSecModelConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Restful interface class for IpSec.<br>
 *
 * @author
 * @version SDNO 0.5 Jul 14, 2016
 */
@Service
@Path("/sbi-ipsec/v1")
public class IpSecRoaResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpSecRoaResource.class);

    @Autowired
    private IpSecSvcImpl ipSecSvc;

    /**
     * Create IPSec connection. <br>
     *
     * @param request HTTP request
     * @param ctrlUuid Controller UUID
     * @param neIpSecConnectionList Collection list of IpSec
     * @return ResultRsp object with IpSec connection list data
     * @throws ServiceException When create failed
     * @since SDNO 0.5
     */
    @POST
    @Path("/overlay/batch-create-ipsecs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<List<NeIpSecConnection>> createIpSec(@Context HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, List<NeIpSecConnection> neIpSecConnectionList)
            throws ServiceException {
        long beginTime = System.currentTimeMillis();
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);

        // check parameters
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("createIpSec failed, ctrlUuid is invalid.");
            SvcExcptUtil.throwBadRequestException("createIpSec failed, ctrlUuid is invalid");
        }

        if(CollectionUtils.isEmpty(neIpSecConnectionList)) {
            LOGGER.error("createIpSec failed, neIpSecConnectionList is null");
            SvcExcptUtil.throwBadRequestException("createIpSec failed, neIpSecConnectionList is null");
        }

        for(NeIpSecConnection neIpSecConnection : neIpSecConnectionList) {
            ValidationUtil.validateModel(neIpSecConnection);
        }

        // convert model from IpSecService to adapter
        Map<String, List<NetIpSecModel>> neIdToNetIpSecModelMap = IpSecModelConvert.convertModel(neIpSecConnectionList);

        // call the service method to perform create operation
        for(Map.Entry<String, List<NetIpSecModel>> entry : neIdToNetIpSecModelMap.entrySet()) {
            ResultRsp<List<NetIpSecModel>> resultRsp = ipSecSvc.createIpSec(ctrlUuid, entry.getKey(), entry.getValue());
            if(!resultRsp.isSuccess()) {
                LOGGER.error("createIpSec failed in service");
                return new ResultRsp<List<NeIpSecConnection>>(resultRsp, neIpSecConnectionList);
            }
        }

        LOGGER.info("createIpSec cost time = " + (System.currentTimeMillis() - beginTime));

        return new ResultRsp<List<NeIpSecConnection>>(ErrorCode.OVERLAYVPN_SUCCESS, neIpSecConnectionList);
    }

    /**
     * Delete IPSec connection. <br>
     * 
     * @param request HTTP request
     * @param ctrlUuid Controller UUID
     * @param ipSecConnectionId The UUID of IPSec connection
     * @return The object of ResultRsp
     * @throws ServiceException When delete failed
     * @since SDNO 0.5
     */
    @DELETE
    @Path("/overlay/ipsec/{ipsecconnectionid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> deleteIpSec(@Context HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            @PathParam("ipsecconnectionid") String ipSecConnectionId) throws ServiceException {
        long beginTime = System.currentTimeMillis();
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);

        // check parameters
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("deleteIpSec falied, ctrlUuid is invalid.");
            SvcExcptUtil.throwBadRequestException("deleteIpSec falied, ctrlUuid is invalid");
        }

        // call the service method to perform delete operation
        ResultRsp<String> resultRsp = ipSecSvc.deleteIpSec(ctrlUuid, ipSecConnectionId);

        LOGGER.info("deleteIpSec cost time = " + (System.currentTimeMillis() - beginTime));

        return resultRsp;
    }
}
