/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class<br>
 *
 * @author
 * @version SDNO 0.5 August 22, 2016
 */
public class Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    private static final String CF_KEY = "cfgkey";

    private static final String CF_VALUE = "cfgvalue";

    private Configuration() {
    }

    /**
     * Get label values.<br>
     *
     * @param label is a key
     * @return AC WAN configured value
     * @since SDNHUB 0.5
     */
    public static String getValues(String label) {
        List<Map<String, String>> values = null;
        try {
            values = getJsonFileData();
        } catch(ServiceException e) {
            LOGGER.warn("ServiceException generated" + e);
        }
        return getValue(values, label);

    }

    /**
     * Get label values.<br>
     *
     * @param values is a key value map
     * @param key is a configuration key
     * @return the configuration value for a given key
     * @since SDNHUB 0.5
     */
    private static String getValue(List<Map<String, String>> values, String key) {
        String result = null;
        for(Map<String, String> value : values) {
            String keyValue = value.get(CF_KEY);
            if((keyValue != null) && keyValue.equals(key)) {
                result = value.get(CF_VALUE);
                break;
            }
        }

        return result;
    }

    /**
     * JSON file parser.<br>
     *
     * @param domain AC WAN configuration domain
     * @return the values which is a key value map
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    private static List<Map<String, String>> getJsonFileData() throws ServiceException {
        try {
            String content = IOUtils.toString(
                    Configuration.class.getClassLoader().getResourceAsStream("/generalconfig/overlaydriverConf.json"));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content.getBytes(), List.class);
        } catch(IOException e) {
            LOGGER.warn("Get json file failed!", e);
        }
        return Collections.emptyList();
    }
}
