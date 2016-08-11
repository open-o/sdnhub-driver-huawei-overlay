/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.acbranchservice.util.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Get WanInterface configuration. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jul 21, 2016
 */
public class WanInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(WanInterface.class);

    private static final String CFG_KEY = "cfgkey";

    private static final String CFG_VALUE = "cfgvalue";

    private static final String DOMAIN = "config";

    /**
     * Constructor<br/>
     * 
     * @since SDNO 0.5
     */
    private WanInterface() {
    }

    /**
     * It is used to get configuration. <br/>
     * 
     * @param cfgKey The configuration key that want to get
     * @return The configuration value
     * @throws ServiceException when get failed
     * @since SDNO 0.5
     */
    public static String getConfig(String cfgKey) throws ServiceException {
        List<Map<String, String>> values = getJsonFileData(DOMAIN);
        return getCfgValue(values, cfgKey);
    }

    private static String getCfgValue(List<Map<String, String>> values, String cfgKey) {
        String result = null;
        for(Map<String, String> value : values) {
            String cfgKeyValue = value.get(CFG_KEY);
            if((cfgKeyValue != null) && cfgKeyValue.equals(cfgKey)) {
                result = value.get(CFG_VALUE);
                break;
            }
        }

        return result;
    }

    private static List<Map<String, String>> getJsonFileData(String domain) throws ServiceException {
        try {
            String path = "generalconfig/" + domain + ".json";
            ObjectMapper mapper = new ObjectMapper();
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            return mapper.readValue(bytes, List.class);
        } catch(IOException e) {
            LOGGER.warn("Get json file failed!"+e);
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, "Get json file failed", null,
                    null, null);
        }
        return Collections.EMPTY_LIST;
    }
}
