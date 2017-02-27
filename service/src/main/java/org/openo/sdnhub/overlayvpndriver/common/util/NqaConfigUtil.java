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

package org.openo.sdnhub.overlayvpndriver.common.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.NQADeviceModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NqaConfigUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(NqaConfigUtil.class);

    private NqaConfigUtil() {

    }

    public static ResultRsp<List<NQADeviceModel>> parseResponse(HTTPReturnMessage httpMsg, String retBody,
            String actionDesc) {
        if (httpMsg.isSuccess() && StringUtils.isNotEmpty(retBody)) {
            OverlayVpnDriverResponse<List<NQADeviceModel>> acresponse = JsonUtil.fromJson(retBody,
                    new TypeReference<OverlayVpnDriverResponse<List<NQADeviceModel>>>() {
            });
            if (acresponse.isSucess()) {

                return new ResultRsp<>(acresponse.getErrcode() + acresponse.getErrmsg() +
                        acresponse.getData());
            }

            LOGGER.error("createIpSecByDevice: asresponse return error");
            return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED + acresponse.getErrmsg());
        }

        if (StringUtils.isNotEmpty(retBody)) {
            final Map<String, String> errorMap = JsonUtil.fromJson(retBody, new TypeReference<Map<String, String>>() {
            });
            return new ResultRsp<>(errorMap.get("errcode") + errorMap.get("errmsg"));
        }

        LOGGER.error(actionDesc + ": parser msg to ACResponse error, msg : " + retBody);

        return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED);

    }

    public static ResultRsp<String> parseDeleteResponse(HTTPReturnMessage httpMsg, String retBody,
            String actionDesc) {
        final ResultRsp<String> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        if (httpMsg.isSuccess() && StringUtils.isNotEmpty(retBody)) {
            ACDelResponse acresponse = JsonUtil.fromJson(retBody,
                    new TypeReference<ACDelResponse>() {
                    });
            if (!acresponse.isSucess()) {
                LOGGER.error(actionDesc + ": response return error, eerMsg : " + acresponse.getAllErrmsg());
                resultRsp.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
                resultRsp.setMessage(acresponse.getAllErrmsg());
            }

            return resultRsp;

        }

        LOGGER.error(actionDesc + "parse msg to  ACDelResponse error, msg: " + retBody);
        resultRsp.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
        resultRsp.setMessage(actionDesc);

        return resultRsp;
    }
}
