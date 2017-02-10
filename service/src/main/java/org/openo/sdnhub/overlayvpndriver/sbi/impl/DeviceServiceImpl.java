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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.common.util.ResultUtil;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.AdapterDeviceInfo;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Restful device configuration service implementation.<br>
 *
 * @author Mahesh
 * @version SDNHUB 0.5 Jun 19, 2017
 */
public class DeviceServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceServiceImpl.class);

    /**
     * Queries specific controller for device configuration.<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param body json object
     * @return ResultRsp collection of queried device configuration
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<AdapterDeviceInfo>> queryDevices(String ctrlUuid, String body) throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Device query begin time = " + beginTime);

        HTTPReturnMessage msg =
                OverlayVpnDriverProxy.getInstance().sendPostMsg(ControllerUrlConst.QUERY_DEVICES_URL, body, ctrlUuid);
        LOGGER.debug("Device query end time = " + (System.currentTimeMillis() - beginTime));

        if(msg.isSuccess() && (null != msg.getBody())) {
            ACResponse<List<AdapterDeviceInfo>> response =
                    JsonUtil.fromJson(msg.getBody(), new TypeReference<ACResponse<List<AdapterDeviceInfo>>>() {});
            if((null == response) || (!response.isSucceed())) {
                LOGGER.error("query devices : parser msg to ACResponse error, msg : " + msg.getBody());
                return new ResultRsp<List<AdapterDeviceInfo>>(DriverErrorCode.CLOUDVPN_FAILED,
                        "query device httpReturnMessage return error", null, null, null);
            }
            return new ResultRsp<List<AdapterDeviceInfo>>(DriverErrorCode.CLOUDVPN_SUCCESS, response.getData());
        }
        return new ResultRsp<List<AdapterDeviceInfo>>(DriverErrorCode.CLOUDVPN_FAILED, "failed to get device details ",
                null, null, null);
    }

    /**
     * Adds new device configuration using a specific Controller.<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param body json object
     * @return ResultRsp object with added device configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<AdapterDeviceInfo> createDevices(String ctrlUuid, String body) throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Device create begin time = " + beginTime);

        HTTPReturnMessage createRsp =
                OverlayVpnDriverProxy.getInstance().sendPostMsg(ControllerUrlConst.CREATE_DEVICES_URL, body, ctrlUuid);
        LOGGER.debug("Device create end time = " + (System.currentTimeMillis() - beginTime));
        LOGGER.debug("body :{}", createRsp);

        if(createRsp.isSuccess() && (null != createRsp.getBody())) {
            ACResponse<AdapterDeviceInfo> response =
                    JsonUtil.fromJson(createRsp.getBody(), new TypeReference<ACResponse<AdapterDeviceInfo>>() {});
            if(null != response) {
                return getResultData(response);
            } else {
                LOGGER.error("create devices: parser msg to AcResponse error");
                return new ResultRsp<AdapterDeviceInfo>(DriverErrorCode.CLOUDVPN_FAILED);
            }
        }
        return new ResultRsp<AdapterDeviceInfo>(DriverErrorCode.CLOUDVPN_FAILED,
                "create device httpReturnMsg return error", null, null, null);
    }

    /**
     * Retrieve successed and fail information from controller response.<br/>
     *
     * @param response controller response with device information
     * @return ResultRsp object with device configuration status data
     * @since SDNHUB 0.5
     */
    private ResultRsp<AdapterDeviceInfo> getResultData(final ACResponse<AdapterDeviceInfo> response) {

        List<AdapterDeviceInfo> successed = JsonUtil.fromJson(JsonUtil.toJson(response.getSuccess()), new TypeReference<List<AdapterDeviceInfo>>() {
        });

        List<FailData<AdapterDeviceInfo>> failData = JsonUtil.fromJson(JsonUtil.toJson(response.getFail()), new TypeReference<List<FailData<AdapterDeviceInfo>>>() {
        });

        ResultRsp result = new ResultRsp<AdapterDeviceInfo>();
        if (response.isSucceed()) {
            result.setErrorCode(DriverErrorCode.CLOUDVPN_SUCCESS);
        } else {
            result.setErrorCode(DriverErrorCode.CLOUDVPN_FAILED);
        }
        result.setMessage(response.getErrmsg());
        result.setSuccessed(successed);
        result.setFail(failData);

        return result;
    }

    /**
     * Updates device configuration using a specific Controller.<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId specific device id
     * @param aDeviceInfo device information needs to be updated
     * @return ResultRsp object with updated device configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> modifyDevice(String ctrlUuid, String deviceId, AdapterDeviceInfo aDeviceInfo)
            throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Device modify begin time = " + beginTime);

        String url = MessageFormat.format(ControllerUrlConst.MODIFY_DEVICES_URL, deviceId);
        HTTPReturnMessage modifyRsp =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(url, JsonUtil.toJson(aDeviceInfo), ctrlUuid);
        LOGGER.debug("Device modify end time = " + (System.currentTimeMillis() - beginTime));

        ResultRsp<String> rsp = new ResultRsp<String>();
        if(!modifyRsp.isSuccess() || (!StringUtils.hasLength(modifyRsp.getBody()))) {
            LOGGER.error("modify device HTTPReturn message error");
            Map<String, String> errorMap =
                    JsonUtil.fromJson(modifyRsp.getBody(), new TypeReference<Map<String, String>>() {

                    });
            FailData<String> fail = new FailData<String>();
            fail.setErrcode(errorMap.get("errorcode"));
            fail.setErrmsg(errorMap.get("errmsg"));
            rsp.setFail(Arrays.asList(fail));
            return rsp;
        }
        ACResponse response = ResultUtil.parserACResponse(modifyRsp.getBody());
        if(null != response) {
            rsp.setErrorCode(response.getErrcode());
            rsp.setMessage(response.getErrmsg());
            if(!"0".equals(response.getErrcode())) {
                FailData<String> fail = new FailData<String>();
                fail.setErrcode(response.getErrcode());
                fail.setErrmsg(response.getErrmsg());
                rsp.setFail(Arrays.asList(fail));
            }
        } else {
            LOGGER.error("modify device parserACResponse error : " + modifyRsp.getBody());
            rsp.setErrorCode(DriverErrorCode.CLOUDVPN_FAILED);
        }
        return rsp;
    }

    /**
     * Deletes device configuration using a specific Controller.<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceIdList collection of device id
     * @return ResultRsp object with deleted device configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> deleteDevice(String ctrlUuid, List<String> deviceIdList) throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Device delete begin time = " + beginTime);
        Map<String, List<String>> ctrlInfoMap = new HashMap<String, List<String>>();
        ctrlInfoMap.put(CommConst.DELETE_DEVICE_PARAMETER, deviceIdList);
        HTTPReturnMessage deleteRsp = OverlayVpnDriverProxy.getInstance()
                .sendDeleteMsg(ControllerUrlConst.DELETE_DEVICES_URL, JsonUtil.toJson(ctrlInfoMap), ctrlUuid);

        LOGGER.debug("Device delete end time = " + (System.currentTimeMillis() - beginTime));

        ResultRsp<String> rsp = new ResultRsp<String>();

        if(!deleteRsp.isSuccess() || (!StringUtils.hasLength(deleteRsp.getBody()))) {
            LOGGER.error("delete device error");
            rsp.setErrorCode(String.valueOf(deleteRsp.getStatus()));
            rsp.setMessage(deleteRsp.getBody());
            return rsp;
        }
        ACResponse response = ResultUtil.parserACResponse(deleteRsp.getBody());
        if(null != response) {
            rsp.setErrorCode(response.getErrcode());
            rsp.setMessage(response.getErrmsg());

        } else {
            LOGGER.error("delete device error : " + deleteRsp.getBody());
            rsp.setErrorCode(DriverErrorCode.CLOUDVPN_FAILED);
        }
        return rsp;
    }
}
