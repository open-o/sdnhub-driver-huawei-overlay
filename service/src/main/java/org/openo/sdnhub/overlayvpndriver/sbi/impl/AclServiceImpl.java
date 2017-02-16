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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *
 * Implementation class for AclService<br/>
 *
 * @author
 * @version     SDNHUB 0.5  Feb 14, 2017
 */
public class AclServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AclServiceImpl.class);

    private static final String ADAPTER_SITE_ACL_DELETE_ERROR = "adapter.site.acl.delete.error";

    private static final String ADAPTER_SITE_ACL_DELETE_TUNNEL_ERROR = "adapter.site.acl.delete.tunnel.error";

    private static final String ADAPTER_SITE_ACL_CREATE_TUNNEL_ERROR = "adapter.site.acl.create.tunnel.error";

    private static final String ADAPTER_SITE_ACL_CREATE_ERROR = "adapter.site.acl.create.error";

    private static final String ADAPTER_SITE_ACL_QUERY_ERROR = "adapter.site.acl.query.error";

    private static final String ADAPTER_SITE_ACL_UPDATE_TUNNEL_ERROR = "adapter.site.acl.update.tunnel.error";

    private static final String ADAPTER_SITE_ACL_UPDATE_ERROR = "adapter.site.acl.update.error";

    private static final String ADAPTER_SITE_ACL_QUERY_TUNNEL_ERROR = "adapter.site.acl.query.tunnel.error";

    /**
     *
     * ACL create operation implementation.<br/>
     *
     * @param acl ACL model to be configured on device
     * @param ctrlUuid Controller UUID Controller UUID
     * @param deviceId Device ID
     * @return ResultRsp for ACL create operation
     * @throws ServiceException In case of query operation fails
     * @since  SDNHUB 0.5
     */
    public ResultRsp<AcAcl> createAcl(AcAcl acl, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId)) {
            LOGGER.error("AcAcl create: parameter error.");
            SvcExcptUtil.throwBadRequestException("AcAcl create: parameter error.");
        }

        ResultRsp<AcAcl> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        String createUrl = MessageFormat.format(ControllerUrlConst.ACL_URL, deviceId);
        Map<String, Object> bodyMap = new HashMap<>();
        List<AcAcl> bodyList = new ArrayList<>();
        bodyList.add(acl);
        bodyMap.put("aclList", bodyList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(createUrl, JsonUtil.toJson(bodyMap), ctrlUuid);
        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("createUrl : httpMsg return  error ");
            throw new ServiceException(ADAPTER_SITE_ACL_CREATE_ERROR, "AcBranch AcAcl create:  httpMsg return error.");

        }
        ACResponse<AcAcl> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<AcAcl>>() {});

        if(!acResponse.isSucceed()) {
            LOGGER.error("AcBranch AcAcl create :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(ADAPTER_SITE_ACL_CREATE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        resultRsp.setData(acResponse.getData());
        return resultRsp;

    }

    /**
     *
     * Implementation for delete ACL.<br/>
     *
     * @param aclId
     * @param ctrlUuid Controller UUID Controller UUID
     * @param deviceId Device ID
     * @return ResultRsp for deletion for deletion of ACL
     * @throws ServiceException In case of query operation fails
     * @since  SDNHUB 0.5
     */
    public ResultRsp<String> deleteAcl(String aclId, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId) || null == aclId) {
            LOGGER.error("AcBranch AcAcl delete : parameter error.");
            SvcExcptUtil.throwBadRequestException("AcBranch AcAcl delete: parameter error.");
        }

        ResultRsp<String> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);

        String deleteUrl = MessageFormat.format(ControllerUrlConst.ACL_URL, deviceId);
        Map<String, Object> params = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add(aclId);
        params.put("ids", ids);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(deleteUrl, JsonUtil.toJson(params), ctrlUuid);

        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("delete Acl : httpMsg return  error ");
            throw new ServiceException(ADAPTER_SITE_ACL_DELETE_ERROR, "AcAcl delete:  httpMsg return error.");
        }
        ACResponse<String> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<String>>() {});

        if(!acResponse.isSucceed()) {
            LOGGER.error("AcAcl delete :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(ADAPTER_SITE_ACL_DELETE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        return resultRsp;
    }

    /**
     *
     * Implementation for ACL Query.<br/>
     *
     * @param aclId
     * @param ctrlUuid Controller UUID Controller UUID
     * @param deviceId Device ID
     * @return ResultRsp for query ACL
     * @throws ServiceException In case of query operation fails
     * @since  SDNHUB 0.5
     */
    public ResultRsp<AcAcl> queryAcl(String aclId, String ctrlUuid, String deviceId) throws ServiceException {
        if(!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId)) {

            LOGGER.error("AcAcl query: parameter error.");
            SvcExcptUtil.throwBadRequestException("AcAcl query: parameter error.");
        }

        String queryUrl = MessageFormat.format(ControllerUrlConst.ACL_URL, deviceId);
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);
        String body = httpMsg.getBody();

        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("query SNat : httpMsg return  error ");
            throw new ServiceException(ADAPTER_SITE_ACL_QUERY_ERROR, "AcAcl query:  httpMsg return error.");
        }
        ACResponse<List<AcAcl>> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<List<AcAcl>>>() {});

        if(!acResponse.isSucceed()) {
            LOGGER.error("AcAcl query :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(ADAPTER_SITE_ACL_QUERY_TUNNEL_ERROR, acResponse.getErrmsg());
        }

        List<AcAcl> acAcls = acResponse.getData();
        for(AcAcl acl : acAcls) {
            if(aclId.equals(acl.getId())) {
                return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, acl);
            }
        }
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED);
    }

    /**
     *
     * Implementation of update ACL.<br/>
     *
     * @param acl
     * @param ctrlUuid Controller UUID
     * @param deviceId Device ID
     * @return ResultRsp for updated ACL
     * @throws ServiceException In case of query operation fails
     * @since  SDNHUB 0.5
     */
    public ResultRsp<AcAcl> updateAcl(AcAcl acl, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId)) {
            LOGGER.error("AcBranch AcAcl update: parameter error.");
            SvcExcptUtil.throwBadRequestException("AcBranch AcAcl update: parameter error.");
        }

        ResultRsp<AcAcl> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        String updateUrl = MessageFormat.format(ControllerUrlConst.ACL_URL, deviceId);
        Map<String, Object> bodyMap = new HashMap<>();
        List<AcAcl> bodyList = new ArrayList<>();
        bodyList.add(acl);
        bodyMap.put("aclList", bodyList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(updateUrl, JsonUtil.toJson(bodyMap), ctrlUuid);
        String body = httpMsg.getBody();

        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("update AcAcl : httpMsg return  error ");
            throw new ServiceException(ADAPTER_SITE_ACL_UPDATE_ERROR, "AcBranch AcAcl update:  httpMsg return error.");

        }
        ACResponse<List<AcAcl>> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<List<AcAcl>>>() {});
        if(!acResponse.isSucceed()) {
            LOGGER.error("AcBranch AcAcl update :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(ADAPTER_SITE_ACL_UPDATE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        resultRsp.setData(acResponse.getData().get(0));
        return resultRsp;
    }
}
