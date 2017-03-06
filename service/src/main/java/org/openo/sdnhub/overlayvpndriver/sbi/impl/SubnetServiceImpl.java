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
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerErrorRsp;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACNetwork;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACNetworkResponse;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetNetModel;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
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
 * This class is implementation for subnet CRUD operation.<br/>
 *
 * @author
 * @version SDNHUB 0.5 03-Feb-2017
 */
public class SubnetServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubnetServiceImpl.class);
    private static final String SINVALIDPARAM="Invalid parameters.";

    /**
     * Subnet create operation implementation.<br/>
     *
     * @param network Network details
     * @param ctrlUuid Controller UUID
     * @param deviceId      Device Id
     * @return Result for create operation
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<ACNetwork> createSubnet(ACNetwork network, String ctrlUuid, String deviceId)
            throws ServiceException {

        if(!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId) || null == network.getId()) {
            LOGGER.error(SINVALIDPARAM);
            throw new ParameterServiceException(SINVALIDPARAM);
        }

        ResultRsp<ACNetwork> resultRsp = new ResultRsp<>(DriverErrorCode.OVERLAYVPN_SUCCESS);
        String createUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NETWORK_URL, deviceId);
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(createUrl, JsonUtil.toJson(network), ctrlUuid);
        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("createUrl : httpMsg return  error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SUBNET_CREATE_ERROR,
                    "Subnet create:  httpMsg return error.");
        }
        ACResponse<ACNetwork> acResponse =
                JsonUtil.fromJson(body, new TypeReference<ACResponse<ACNetwork>>() {});
        if(!acResponse.isSucceed()) {
            LOGGER.error("Subnet create :acresponse return error :" + acResponse.getErrmsg());
            String errorCode = DriverErrorCode.ADAPTER_SITE_SUBNET_CREATE_TUNNEL_ERROR;

            if(ControllerErrorRsp.CTRL_ERR_SUBNET_SAME.getErrCode()
                    .equals(acResponse.getErrcode())) {
                errorCode = DriverErrorCode.SUBNET_SUBNET_SAME;
            }

            if(ControllerErrorRsp.CTRL_ERR_SUBNET_VNI_ALREADY_EXIST.getErrCode()
                    .equals(acResponse.getErrcode())) {
                errorCode = DriverErrorCode.SUBNET_VNI_ALREADY_EXIST;
            }

            throw new ServiceException(errorCode, acResponse.getErrmsg());
        }
        resultRsp.setData(acResponse.getData());
        return resultRsp;
    }

    /**
     * Subnet update operation implementation.<br/>
     *
     * @param network Network details
     * @param ctrlUuid Controller UUID
     * @param deviceId      Device Id
     * @return Result for update operation
     * @throws ServiceException In case of update operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<ACNetwork> updateSubnet(ACNetwork network, String ctrlUuid, String deviceId)
            throws ServiceException {

        if(!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId) || null == network.getId()) {
            LOGGER.error(SINVALIDPARAM);
            throw new ParameterServiceException(SINVALIDPARAM);
        }

        ResultRsp<ACNetwork> resultRsp = new ResultRsp<>(DriverErrorCode.OVERLAYVPN_SUCCESS);
        String updateUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NETWORK_URL, deviceId);
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(updateUrl, JsonUtil.toJson(network), ctrlUuid);
        String body = httpMsg.getBody();

        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("createUrl : httpMsg return  error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SUBNET_UPDATE_ERROR,
                    "Subnet update:  httpMsg return error.");
        }
        ACResponse<ACNetwork> acResponse =
                JsonUtil.fromJson(body, new TypeReference<ACResponse<ACNetwork>>() {
                });
        if(!acResponse.isSucceed()) {
            LOGGER.error("Subnet update :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SUBNET_UPDATE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        resultRsp.setData(acResponse.getData());
        return resultRsp;
    }

    /**
     * Subnet delete operation implementation.<br/>
     *
     * @param networkId Network Id
     * @param ctrlUuid Controller UUID
     * @param deviceId      Device Id
     * @return Result for delete operation
     * @throws ServiceException In case of delete operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> deleteSubnet(String networkId, String ctrlUuid, String deviceId) throws ServiceException {

        if(!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId) || null == networkId) {
            LOGGER.error(SINVALIDPARAM);
            throw new ParameterServiceException(SINVALIDPARAM);
        }

        ResultRsp<String> resultRsp = new ResultRsp<>(DriverErrorCode.OVERLAYVPN_SUCCESS);
        String deleteUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NETWORK_URL, deviceId);
        List<String> ids = new ArrayList<>();
        ids.add(networkId);
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(deleteUrl, JsonUtil.toJson(params), ctrlUuid);
        String body = httpMsg.getBody();
        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("delete subnet : httpMsg return  error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SUBNET_DELETE_ERROR,
                    "Subnet delete:  httpMsg return error.");
        }
        ACResponse<String> acResponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<String>>() {
        });
        if(!acResponse.isSucceed()) {
            LOGGER.error("Subnet delete :acresponse return error :" + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SUBNET_DELETE_TUNNEL_ERROR, acResponse.getErrmsg());
        }
        resultRsp.setData(acResponse.getData());
        return resultRsp;
    }

    /**
     * Subnet query operation implementation.<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId      Device Id
     * @return Result for query operation
     * @throws ServiceException In case of query operation fails
     * @since SDNHUB 0.5
     */
    public static ResultRsp<List<ACNetwork>> queryNetwork(String ctrlUuid, String deviceId)
            throws ServiceException
    {
        if (!StringUtils.hasLength(ctrlUuid) || !StringUtils.hasLength(deviceId))
        {
            LOGGER.error(SINVALIDPARAM);
            throw new ParameterServiceException(SINVALIDPARAM);
        }

        ResultRsp<List<ACNetwork>> resultRsp = new ResultRsp<>(DriverErrorCode.OVERLAYVPN_SUCCESS);

        String getUrl = MessageFormat.format(ControllerUrlConst.DEVICE_NETWORK_URL, deviceId);

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Subnet query begin time = " + beginTime + ", deviceId: " + deviceId);

        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(getUrl, null, ctrlUuid);

        LOGGER.debug("Subnet query cost time = " + (System.currentTimeMillis() - beginTime));

        String body = httpMsg.getBody();

        LOGGER.error("AcBranch subnet query. Return Body={} ", body);

        if ((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("Query Subnet: httpMsg return error.");
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SUBNET_QUERY_ERROR, "Subnet query: httpMsg return error.");
        }

        ACNetworkResponse acResponse = JsonUtil.fromJson(body, new TypeReference<ACNetworkResponse>() {});

        if (!acResponse.isSucess() || (acResponse.getData() == null)) {
            LOGGER.error("AcBranch subnet query: acresponse return error, errMsg: " + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_SITE_SUBNET_NOT_EXIST_ON_CONTROLLER,
                    acResponse.getErrcode() + "," + acResponse.getErrmsg());
        }

        resultRsp.setData(acResponse.getData().getNetworkConfigList());
        return resultRsp;
    }
}
