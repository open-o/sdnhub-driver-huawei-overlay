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
import org.codehaus.jackson.type.TypeReference;
import org.openo.sdnhub.overlayvpndriver.controller.model.EthInterfaceConfig;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * EthInterfaceConfig utility class.<br/>
 *
 * @author
 * @version     SDNHUB 0.5  06-Feb-2017
 */
public final class EthInterfaceConfigUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EthInterfaceConfigUtil.class);

    private EthInterfaceConfigUtil()
    {

    }

    /**
     * Parse Response.<br/>
     *
     * @param httpMsg Http message
     * @param retBody Body of message
     * @param actionDesc Action description
     * @return ResultRsp object
     * @since  SDNHUB 0.5
     */
    public static ResultRsp<List<EthInterfaceConfig>> parseResponse(final HTTPReturnMessage httpMsg,
            final String retBody, final String actionDesc) {
        if(httpMsg.isSuccess() && StringUtils.hasLength(retBody)) {
            final ACResponse<List<EthInterfaceConfig>> response =
                    JsonUtil.fromJson(retBody, new TypeReference<ACResponse<List<EthInterfaceConfig>>>() {});
            if(response.isSucceed()) {
                return new ResultRsp<>(org.openo.sdno.overlayvpn.errorcode.ErrorCode.OVERLAYVPN_SUCCESS,
                        response.getData());
            } else {
                return new ResultRsp<>(org.openo.sdno.overlayvpn.errorcode.ErrorCode.OVERLAYVPN_SUCCESS,response.getData());
            }
        }
        if(StringUtils.hasLength(retBody)) {
            final Map<String, String> errorMap = JsonUtil.fromJson(retBody, new TypeReference<Map<String,String>>() {});
            return new ResultRsp<List<EthInterfaceConfig>>(ErrorCode.OVERLAYVPN_FAILED, errorMap.get("errmsg"), null, null, null);
        }
        LOGGER.error(actionDesc + ": parser msg to ACResponse error, msg:" + retBody);
        return new ResultRsp<List<EthInterfaceConfig>>(ErrorCode.OVERLAYVPN_FAILED, retBody, null, null, null);
    }
}
