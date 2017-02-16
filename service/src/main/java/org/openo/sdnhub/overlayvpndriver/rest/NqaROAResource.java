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
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.NqaConfigImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.ControllerNbiNqa;
import org.openo.sdnhub.overlayvpndriver.translator.NqaConvert;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNqa;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.CheckStrUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Restful interface for NQA configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 February 02, 2017
 */
@Service
@Path("/sbi-route/v1")
public class NqaROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(NqaROAResource.class);

    @Autowired
    private NqaConfigImpl nqaConfigService = null;

    private static final String NQA_LIST = "nqaList";

    private static final String NQA_ID_LIST = "ids";

    private String INVALID_CONTROLLER_UUID = "invalid controller UUID.";

    /**
     * Retrieves a NQA configuration using a specific Controller.<br>
     *
     * @param request       HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId      Device Id
     * @param sbiNqaList    List of SbiNqa instances
     * @return ResultRsp Response object
     * @throws ServiceException In case of query operation fails for NQA
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceId}/batch-query-nqa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNqa> queryNQA(@Context HttpServletRequest request,
                                      @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("deviceId") String deviceId,
                                      List<SbiNqa> sbiNqaList) throws ServiceException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("invalid controller UUID");
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        CheckStrUtil.checkUuidStr(deviceId);

        final String queryUrl = MessageFormat.format(ControllerUrlConst.NQA_CONFIG_URL, deviceId);
        return nqaConfigService.queryNqaConfig(ctrlUuid, queryUrl);
    }

    /**
     * Creates a NQA configuration using a specific Controller.<br>
     *
     * @param request       HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId      Device Id
     * @param sbiNqaList    List of SbiNqa instances
     * @return ResultRsp Response object
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceId}/batch-create-nqa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNqa> createNQA(@Context HttpServletRequest request,
                                       @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("deviceId") String deviceId,
                                       List<SbiNqa> sbiNqaList) throws ServiceException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("createNQA param error");
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        CheckStrUtil.checkUuidStr(deviceId);

        for (final SbiNqa sbiNqa : sbiNqaList) {
            if ((null == sbiNqa) || StringUtils.isEmpty(sbiNqa.getTestType())
                    || StringUtils.isEmpty(sbiNqa.getDstIp())) {
                LOGGER.error("invalid controller UUID");
                throw new ParameterServiceException("create NQA nqaList param error");
            }
        }

        final List<ControllerNbiNqa> controllerNbiNqaList = NqaConvert.convert2Sbi(sbiNqaList);

        final Map<String, List<ControllerNbiNqa>> crtInfoMap = new HashMap<>();
        crtInfoMap.put(NQA_LIST, controllerNbiNqaList);
        final String createUrl = MessageFormat.format(ControllerUrlConst.NQA_CONFIG_URL, deviceId);

        ResultRsp<SbiNqa> response = nqaConfigService.createNqaConfig(ctrlUuid, createUrl, JsonUtil.toJson(crtInfoMap));

        // If response is successful then we should update successlist with
        // request NQA List.
        if (response.isSuccess()) {
            response.setSuccessed(sbiNqaList);
        }
        return response;
    }

    /**
     * Modifies a NQA configuration using a specific Controller.<br>
     *
     * @param request       HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId      Device Id
     * @param sbiNqaList    List of SbiNqa instances
     * @return ResultRsp Response object
     * @throws ServiceException In case of update operation fails
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/device/{deviceId}/batch-update-nqa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNqa> updateNQA(@Context HttpServletRequest request,
                                       @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("deviceId") String deviceId,
                                       List<SbiNqa> sbiNqaList) throws ServiceException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("invalid controller UUID");
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        CheckStrUtil.checkUuidStr(deviceId);

        for (final SbiNqa nqa : sbiNqaList) {
            if ((null == nqa) || StringUtils.isEmpty(nqa.getTestType()) || StringUtils.isEmpty(nqa.getDstIp())) {
                LOGGER.error("updateNQA nqaList param error");
                throw new ParameterServiceException("update NQA nqaList param error");
            }
        }
        final List<ControllerNbiNqa> controllerNbiNqaList = NqaConvert.convert2Sbi(sbiNqaList);

        final Map<String, List<ControllerNbiNqa>> crtInfoMap = new HashMap<>();
        crtInfoMap.put(NQA_LIST, controllerNbiNqaList);
        final String updateUrl = MessageFormat.format(ControllerUrlConst.NQA_CONFIG_URL, deviceId);

        ResultRsp<SbiNqa> response = nqaConfigService.updateNqaConfig(ctrlUuid, updateUrl, JsonUtil.toJson(crtInfoMap));

        // If response is successful then we should update successlist with
        // request NQA List.
        if (response.isSuccess()) {
            response.setSuccessed(sbiNqaList);
        }
        return response;
    }

    /**
     * Deletes a NQA configuration using a specific Controller.<br>
     *
     * @param request       HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId      Device Id
     * @param nqaIdList     List of nqaList ids
     * @return ResultRsp Response object
     * @throws ServiceException In case of delete operation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceId}/batch-delete-nqa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> deleteNQA(@Context HttpServletRequest request,
                                       @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("deviceId") String deviceId,
                                       List<String> nqaIdList) throws ServiceException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        if (!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("invalid controller UUID");
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        CheckStrUtil.checkUuidStr(deviceId);

        if (CollectionUtils.isEmpty(nqaIdList)) {
            LOGGER.error("deleteNQA: null idList");
            throw new ParameterServiceException("delete NQA nqaList param error");
        }

        final Map<String, List<String>> crtInfoMap = new HashMap<>();
        crtInfoMap.put(NQA_ID_LIST, nqaIdList);
        final String deletUrl = MessageFormat.format(ControllerUrlConst.NQA_CONFIG_URL, deviceId);

        return nqaConfigService.deleteNqaConfig(ctrlUuid, deletUrl, JsonUtil.toJson(crtInfoMap));
    }
}
