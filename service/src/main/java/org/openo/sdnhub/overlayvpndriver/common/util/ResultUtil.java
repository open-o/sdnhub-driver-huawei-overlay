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

import java.util.Map;

import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.springframework.util.StringUtils;

import net.sf.json.JSONObject;

/**
 * Utility class to retrieve response identifiers from Controller response.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class ResultUtil {

    private ResultUtil() {

    }

    /**
     * Retrieve error code.<br/>
     *
     * @param configInfoMap configuration information map
     * @return error code if error code exists otherwise returns 0, but if exception occurred returns 1
     * @since SDNHUB 0.5
     */
    public static int getErrorCode(Map configInfoMap) {
        if(!configInfoMap.containsKey("errcode")) {
            return 0;
        }
        Object conInfoObject = configInfoMap.get("errcode");
        if((null != conInfoObject) && (conInfoObject instanceof String)) {
            try {
                return Integer.parseInt((String)conInfoObject);
            } catch(NumberFormatException e) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Retrieve error code object.<br/>
     *
     * @param configInfoMap configuration information map
     * @return error object if error code exists otherwise return 0
     * @since SDNHUB 0.5
     */
    public static int getErrorCodebk(Map configInfoMap) {
        if(!configInfoMap.containsKey("errCode")) {
            return 0;
        }
        Object connInfoObj = configInfoMap.get("errCode");
        if(null != connInfoObj) {
            return (int)connInfoObj;
        }
        return 0;
    }

    /**
     * Retrieve error message.<br/>
     *
     * @param configInfoMap configuration information map
     * @return error message if exists otherwise empty string
     * @since SDNHUB 0.5
     */
    public static String getErrorMsg(Map configInfoMap) {
        if(!configInfoMap.containsKey("errmsg")) {
            return "";
        }
        Object conInfoObj = configInfoMap.get("errmsg");
        if((null != conInfoObj) && (conInfoObj instanceof String)) {
            return (String)conInfoObj;
        }
        return "";
    }

    /**
     * Parse and retrieve ACResponse object from specific jason content.<br/>
     *
     * @param content jason content
     * @return ACResponse object
     * @since SDNHUB 0.5
     */
    public static ACResponse parserACResponse(String content) {
        if(!StringUtils.hasLength(content)) {
            return null;
        }
        JSONObject responseJO = JSONObject.fromObject(content);

        if(null == responseJO) {
            return null;
        }

        return JsonUtil.fromJson(responseJO.toString(), ACResponse.class);
    }

    /**
     * Packing AC information.<br/>
     *
     * @param info AC information
     * @return AC packed information
     * @since SDNHUB 0.5
     */
    public static String packAcInfo(String info) {
        return "AC[" + info + "] ";
    }
}
