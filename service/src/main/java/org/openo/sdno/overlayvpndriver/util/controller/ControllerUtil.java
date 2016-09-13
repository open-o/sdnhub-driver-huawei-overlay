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

package org.openo.sdno.overlayvpndriver.util.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpndriver.login.OverlayVpnDriverResponse;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class of controller util. <br>
 * 
 * @param <T> Net Model Class
 * @author
 * @version SDNO 0.5 Jul 21, 2016
 */
public class ControllerUtil<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerUtil.class);

    /**
     * It used to check the response and return the data. <br>
     * 
     * @param httpMsg The response of HTTP
     * @return The data
     * @throws ServiceException When controller return failed
     * @since SDNO 0.5
     */
    public List<T> checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
        String body = httpMsg.getBody();

        if(!httpMsg.isSuccess() || StringUtils.isEmpty(body)) {
            String errMsg = "checkRsp AC return error, status: " + httpMsg.getStatus();
            LOGGER.error(errMsg);
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, errMsg, null, null, null);
        }

        OverlayVpnDriverResponse<List<T>> overlayVpnResponse =
                JsonUtil.fromJson(body, new TypeReference<OverlayVpnDriverResponse<List<T>>>() {});
        if(!overlayVpnResponse.isSucess()) {
            String errMsg = "checkRsp AC return error, errCode: " + overlayVpnResponse.getErrcode() + ", errMsg: "
                    + overlayVpnResponse.getErrmsg();
            LOGGER.error(errMsg);
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, errMsg, null, null, null);
        }

        return overlayVpnResponse.getData();
    }
}
