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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcSNat;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.AclServiceImpl;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.LocalSiteSNatServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSnatNetModel;
import org.openo.sdnhub.overlayvpndriver.translator.AclConvert;
import org.openo.sdnhub.overlayvpndriver.translator.SNatNetConvert;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Restful interface for LocalSiteSNatROAResource.<br/>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
@Service
@Path("/sbi-localsite/v1")
public class LocalSiteSNatROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalSiteSNatROAResource.class);

    @Autowired
    private LocalSiteSNatServiceImpl localSiteSNatService;

    @Autowired
    private AclServiceImpl aclServiceImpl;

    private static final String INVALID_CONTROLLER_UUID = "Invalid controller UUID.";

    private static final String NETMODEL_AC_OPER_ERROR = "SNatNetModel ac oper err.";
    /**
     * Create SNAT configuration.<br/>
     *
     * @param request Http request context
     * @param deviceId Device ID
     * @param ctrlUuidParam Controller UUID
     * @param snatNet SNAT configuration for device
     * @return RestultRsp object for created SNAT cofigurations
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceid}/snat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiSnatNetModel> createSnat(@Context HttpServletRequest request,
            @PathParam("deviceid") String deviceId, @HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            SbiSnatNetModel snatNet) throws ServiceException {

        long startTime = System.currentTimeMillis();

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        if(null == snatNet) {
            LOGGER.error("Local site SNat create: request body is null");
            throw new ParameterServiceException("Local site SNat create: request body is null.");
        }

        ValidationUtil.validateModel(snatNet);
        ResultRsp<AcAcl> aclResult = createAcl(ctrlUuid, deviceId, snatNet);
        if(!aclResult.isValid()) {
            LOGGER.error("Acl ac oper err.");
            throw new ServiceException("ACL operation error.");
        }
        AcSNat snat = SNatNetConvert.buildAcSNat(aclResult.getData(), snatNet);
        ResultRsp<AcSNat> snatResult = localSiteSNatService.createSNat(snat, ctrlUuid, deviceId);
        LOGGER.debug("create SNatNetModel cost {} ms.", System.currentTimeMillis() - startTime);

        if(!snatResult.isValid()) {
            LOGGER.error(NETMODEL_AC_OPER_ERROR);
            throw new ParameterServiceException(NETMODEL_AC_OPER_ERROR);
        }
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, snatNet);
    }

    private ResultRsp<AcAcl> createAcl(String ctrId, String deviceId, SbiSnatNetModel snatNetModel)
            throws ServiceException {
        AcAcl acl = AclConvert.buildAcAcl(snatNetModel);
        return aclServiceImpl.createAcl(acl, ctrId, deviceId);
    }

    /**
     * Delete SNAT configuration from device.<br/>
     *
     * @param request Http request context
     * @param deviceId Device ID
     * @param natId NAT ID
     * @param aclId ACL ID
     * @param ctrlUuidParam Controller UUID
     * @return ResutlRsp for deleted SNAT
     * @throws ServiceException In case of create operation fails.
     * @since SDNHUB 0.5
     */
    @DELETE
    @Path("/device/{deviceid}/snat/{natid}/acl/{aclid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> deleteSnat(@Context HttpServletRequest request, @PathParam("deviceid") String deviceId,
            @PathParam("natid") String natId, @PathParam("aclid") String aclId,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws ServiceException {

        long startTime = System.currentTimeMillis();
        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }
        if(!StringUtils.hasLength(deviceId) || !StringUtils.hasLength(natId) || !StringUtils.hasLength(aclId)) {
            LOGGER.error("delete snat, param err.invalid path parameters");
            throw new ParameterServiceException("delete snat, param err.invalid path parameters");
        }

        LOGGER.error("AC delete Snat.natId={}", natId);
        ResultRsp<String> natResult = localSiteSNatService.deleteSNat(natId, ctrlUuid, deviceId);
        if(!natResult.isSuccess()) {
            LOGGER.error("SNat oper err.");
            throw new ParameterServiceException("SNat oper err.");
        }

        ResultRsp<String> aclResult = aclServiceImpl.deleteAcl(aclId, ctrlUuid, deviceId);
        LOGGER.debug("delete Snat cost {} ms.", System.currentTimeMillis() - startTime);
        if(!aclResult.isSuccess()) {
            LOGGER.error("Acl oper err.");
            throw new ParameterServiceException("Acl oper err.");
        }
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
    }

    /**
     * Query SNAT configuration for device.<br/>
     *
     * @param request Http request context
     * @param deviceId Device ID
     * @param natId NAT ID
     * @param aclId ACL ID
     * @param ctrlUuidParam Controller UUID
     * @return ResultRes for queried SNAT
     * @throws ServiceException In case of create operation fails.
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/device/{deviceid}/snat/{natid}/acl/{aclid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> querySnat(@Context HttpServletRequest request, @PathParam("deviceid") String deviceId,
            @PathParam("natid") String natId, @PathParam("aclid") String aclId,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws ServiceException {

        long startTime = System.currentTimeMillis();
        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }
        if(!StringUtils.hasLength(deviceId) || !StringUtils.hasLength(natId) || !StringUtils.hasLength(aclId)) {
            LOGGER.error("query snat, param err.invalid path parameters");
            throw new ParameterServiceException("query snat, param err.invalid path parameters");
        }

        LOGGER.error("AC Query Snat.natId={}", natId);
        ResultRsp<AcSNat> natResult = localSiteSNatService.querySNat(natId, ctrlUuid, deviceId);
        if(!natResult.isSuccess()) {
            LOGGER.error("SNat ac oper err.");
            throw new ParameterServiceException("SNat ac oper err.");
        }

        LOGGER.debug("query Snat cost {} ms.", System.currentTimeMillis() - startTime);
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
    }

    /**
     * Update SNAT configuration for device.<br/>
     *
     * @param request Http request context
     * @param deviceId Device ID
     * @param ctrlUuidParam Controller UUID
     * @param snatNet
     * @return ResultRsp for updated SNAT
     * @throws ServiceException In case of create operation fails.
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/device/{deviceid}/snat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiSnatNetModel> updateSnat(@Context HttpServletRequest request,
            @PathParam("deviceid") String deviceId, @HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            SbiSnatNetModel snatNet) throws ServiceException {

        long startTime = System.currentTimeMillis();

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }
        if(null == snatNet) {
            LOGGER.error("Local site SNat create: request body is null");
            throw new ParameterServiceException("Local site SNat create: request body is null.");
        }

        ValidationUtil.validateModel(snatNet);
        if("updateIp".equals(snatNet.getType())) {
            updateIp(snatNet, ctrlUuid, deviceId);
            return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, snatNet);
        }
        AcSNat acSnat = SNatNetConvert.buildAcSNat(snatNet);

        ResultRsp<AcSNat> acResult = localSiteSNatService.updateSNat(acSnat, ctrlUuid, deviceId);
        LOGGER.debug("update SNatNetModel cost {} ms.", System.currentTimeMillis() - startTime);

        if(!acResult.isValid()) {
            LOGGER.error("SNatNetModel oper err.");
            throw new ParameterServiceException(NETMODEL_AC_OPER_ERROR);
        }
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, snatNet);
    }

    private void updateIp(SbiSnatNetModel snat, String ctrId, String deviceId) throws ServiceException {
        AcAcl acl = AclConvert.buildAcAcl(snat);

        aclServiceImpl.updateAcl(acl, ctrId, deviceId);
    }
}
