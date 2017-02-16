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

package org.openo.sdnhub.overlayvpndriver.sbi.impl;

import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcSNat;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation class for LocalSiteSNatService.<br/>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class LocalSiteSNatServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalSiteSNatServiceImpl.class);

    private static final String INVALID_DEVICE_ID_OR_NAT_ID = "Invalid device id or NAT id.";

    /**
     * Create SNAT configuration.<br/>
     *
     * @param snat SNAT Model to be created
     * @param ctrlUuid Controller ID
     * @param deviceId Device ID
     * @return ResultRsp for created SNAT configuration
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<AcSNat> createSNat(AcSNat snat, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(deviceId)) {
            LOGGER.error("Invalid device UUID.");
            throw new ParameterServiceException("Invalid device UUID.");
        }

        ResultRsp<AcSNat> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        String createUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NAT_URL, deviceId);
        Map<String, Object> bodyMap = new HashMap<>();
        List<AcSNat> bodyList = new ArrayList<>();
        bodyList.add(snat);
        bodyMap.put("natList", bodyList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(createUrl, JsonUtil.toJson(bodyMap), ctrlUuid);

        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("createUrl : httpMsg return  error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_CREATE_ERROR,
                    "AcSNat create:  httpMsg return error.");
        }
        ACResponse<AcSNat> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<AcSNat>>() {});
        if(!acResponse.isSucceed()) {
            LOGGER.error("AcSNat create :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        resultRsp.setData(acResponse.getData());
        return resultRsp;
    }

    /**
     * <br/>
     *
     * @param natId
     * @param ctrlUuid Controller ID
     * @param deviceId Device ID
     * @return ResultRsp for deleted SNAT
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> deleteSNat(String natId, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(deviceId) || null == natId) {
            LOGGER.error(INVALID_DEVICE_ID_OR_NAT_ID);
            throw new ParameterServiceException(INVALID_DEVICE_ID_OR_NAT_ID);
        }

        ResultRsp<String> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        String deleteUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NAT_URL, deviceId);

        Map<String, Object> params = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add(natId);
        params.put("ids", ids);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(deleteUrl, JsonUtil.toJson(params), ctrlUuid);
        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("delete SNat : httpMsg return  error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_DELETE_ERROR,
                    "AcSNat delete:  httpMsg return error.");
        }
        ACResponse<String> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<String>>() {});
        if(!acResponse.isSucceed()) {
            LOGGER.error("AcSNat delete :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_DELETE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        return resultRsp;
    }

    /**
     * <br/>
     *
     * @param acSnat
     * @param ctrlUuid Controller ID
     * @param deviceId Device ID
     * @return ResultRsp for updated SNAT
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<AcSNat> updateSNat(AcSNat acSnat, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(deviceId) || null == acSnat.getId()) {
            LOGGER.error(INVALID_DEVICE_ID_OR_NAT_ID);
            throw new ParameterServiceException(INVALID_DEVICE_ID_OR_NAT_ID);
        }

        ResultRsp<AcSNat> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        String updateUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NAT_URL, deviceId);
        Map<String, Object> bodyMap = new HashMap<>();
        List<AcSNat> bodyList = new ArrayList<>();
        bodyList.add(acSnat);
        bodyMap.put("natList", bodyList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(updateUrl, JsonUtil.toJson(bodyMap), ctrlUuid);

        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("update SNat : httpMsg return  error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_UPDATE_ERROR,
                    "AcSNat update:  httpMsg return error.");
        }
        ACResponse<AcSNat> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<AcSNat>>() {});
        if(!acResponse.isSucceed()) {
            LOGGER.error("AcSNat update :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_UPDATE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        resultRsp.setData(acResponse.getData());
        return resultRsp;
    }

    /**
     * <br/>
     *
     * @param natId
     * @param ctrlUuid Controller ID
     * @param deviceId Device ID
     * @return ResultRsp for queried SNAT
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<AcSNat> querySNat(String natId, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(deviceId) || null == natId) {
            LOGGER.error(INVALID_DEVICE_ID_OR_NAT_ID);
            throw new ParameterServiceException(INVALID_DEVICE_ID_OR_NAT_ID);
        }

        String queryUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NAT_URL, deviceId);
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);

        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("query SNat : httpMsg return  error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_UPDATE_ERROR,
                    "AcSNat query:  httpMsg return error.");

        }
        ACResponse<AcSNat> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<AcSNat>>() {});
        if(!acResponse.isSucceed()) {
            LOGGER.error("AcSNat query :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SNAT_UPDATE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        AcSNat acSNats = acResponse.getData();
        if(natId.equals(acSNats.getId())) {
            return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, acSNats);
        }
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED);
    }
}
