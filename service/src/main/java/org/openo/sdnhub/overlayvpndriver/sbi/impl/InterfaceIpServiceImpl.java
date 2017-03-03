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
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiInterfaceIpConfig;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class InterfaceIpServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceIpServiceImpl.class);

    /**
     * Interface IP updated implementation.<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId Device ID to be updated
     * @param interfaceIpConfig
     * @return ResultRsp for updated interface
     * @throws ServiceException In case of update operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<SbiInterfaceIpConfig>> updateInterfaceIpList(String ctrlUuid, String deviceId,
            List<SbiInterfaceIpConfig> interfaceIpConfig) throws ServiceException {

        String url = MessageFormat.format(ControllerUrlConst.INTERFACE_IP_CONTROLLER_URL, deviceId);
        HTTPReturnMessage modifyRsp =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(url, JsonUtil.toJson(interfaceIpConfig), ctrlUuid);
        LOGGER.debug("body:{}", modifyRsp);
        if(!modifyRsp.isSuccess()) {
            LOGGER.error("update interface ip error,status:{}", modifyRsp.getStatus());
            return new ResultRsp<>(DriverErrorCode.OVERLAYVPN_FAIL, null, null,  modifyRsp.toString(), null);
        }
        ACResponse<List<SbiInterfaceIpConfig>> response =
                JsonUtil.fromJson(modifyRsp.getBody(), new TypeReference<ACResponse<List<SbiInterfaceIpConfig>>>() {});
        if(null != response) {
            return new ResultRsp<>(DriverErrorCode.OVERLAYVPN_SUCCESS, response.getData());
        }
        LOGGER.error("update interface error:" + modifyRsp.getBody());
        return new ResultRsp<>(DriverErrorCode.OVERLAYVPN_FAIL);
    }

    /**
     * <br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId Device ID to be queried
     * @return ResultRsp for queried interface
     * @throws ServiceException In case of query operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<SbiInterfaceIpConfig>> queryInterfaceIps(String ctrlUuid, String deviceId)
            throws ServiceException {
        String url = MessageFormat.format(ControllerUrlConst.INTERFACE_IP_CONTROLLER_URL, deviceId);

        HTTPReturnMessage queryRsp = OverlayVpnDriverProxy.getInstance().sendGetMsg(url, null, ctrlUuid);
        LOGGER.debug("body:{}", queryRsp);
        if(!queryRsp.isSuccess()) {
            LOGGER.error("query interface ip error,status:{}", queryRsp.getStatus());
            return new ResultRsp<>(DriverErrorCode.OVERLAYVPN_FAIL);
        }
        ACResponse<List<SbiInterfaceIpConfig>> response =
                JsonUtil.fromJson(queryRsp.getBody(), new TypeReference<ACResponse<List<SbiInterfaceIpConfig>>>() {});
        if(null != response) {
            return new ResultRsp<>(DriverErrorCode.OVERLAYVPN_SUCCESS, response.getData());
        }
        LOGGER.error("query interface ip failed:" + queryRsp.getBody());
        return new ResultRsp<>(DriverErrorCode.OVERLAYVPN_FAIL);
    }
}
