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

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIp;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Port service implementation.<br>
 *
 * @author Mahesh
 * @version SDNHUB 0.5 Jan 16, 2017
 */
public class DevicePortServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevicePortServiceImpl.class);

    /**
     * Query interface information of the current device using a specific controller.<br>
     *
     * @param deviceId device id
     * @param ctrlUuid controller UUID
     * @return ResultRsp object with IP configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static ResultRsp<SbiIp> queryPorts(String deviceId, String ctrlUuid) throws ServiceException {

        String queryUrl = MessageFormat.format(ControllerUrlConst.QUERY_DEVICE_PORT_URL, deviceId);
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);
        String body = httpMsg.getBody();

        LOGGER.debug("Query ports return body : " + body);
        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(body)) {
            OverlayVpnDriverResponse<SbiIp> acresponse =
                    JsonUtil.fromJson(body, new TypeReference<OverlayVpnDriverResponse<SbiIp>>() {});
            if(acresponse.isSucess()) {
                ResultRsp<SbiIp> result = new ResultRsp<SbiIp>();
                result.setErrorCode(acresponse.getErrcode());
                result.setData(acresponse.getData());
                return result;
            }
            LOGGER.error("Query ports: controller response return error" + acresponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_GRE_QUERY_WAN_INTERFACE_ERROR, acresponse.getErrmsg());
        }
        LOGGER.error("Query ports: httpMsg return error");
        throw new ServiceException(DriverErrorCode.ADAPTER_GRE_QUERY_WAN_INTERFACE_ERROR,
                "Query ports: httpMsg return error");
    }
}
