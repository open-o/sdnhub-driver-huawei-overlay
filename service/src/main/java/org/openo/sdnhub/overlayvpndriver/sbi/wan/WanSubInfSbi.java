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

package org.openo.sdnhub.overlayvpndriver.sbi.wan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.util.config.DeviceCommParamReader;
import org.openo.sdnhub.overlayvpndriver.util.config.DeviceParam;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SBI Class of WanSubInterface CLI Interface.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-07
 */
public class WanSubInfSbi {

    private static final Logger LOGGER = LoggerFactory.getLogger(WanSubInfSbi.class);

    private static final String WAN_SUB_INTERFACE_FILE = "device/wansubinterface.json";

    private static Map<String, WanSubInfConfigData> deviceIdToWanSubInfConfigData =
            new HashMap<String, WanSubInfConfigData>();

    /**
     * Query WanSubInf Data.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @return List of WanSubInterface queried out
     * @throws ServiceException when query failed
     * @since SDNHUB 0.5
     */
    public List<WanSubInterface> query(String ctrlUuid, String deviceId) throws ServiceException {

        List<WanSubInterface> wanSubInfList = new ArrayList<WanSubInterface>();

        DeviceParam deviceParam = DeviceCommParamReader.getDeviceCommParam(deviceId);
        if(null == deviceParam) {
            LOGGER.error("Current device does not exist");
            throw new ServiceException("Current device does not exist");
        }

        String ipAddress = deviceParam.getIpAddress();

        if(deviceIdToWanSubInfConfigData.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                byte[] bytes = Files.readAllBytes(Paths.get(WAN_SUB_INTERFACE_FILE));
                List<WanSubInfConfigData> wanSubInfConfigList =
                        mapper.readValue(bytes, new TypeReference<List<WanSubInfConfigData>>() {});
                for(WanSubInfConfigData wanSubInfConfigData : wanSubInfConfigList) {
                    deviceIdToWanSubInfConfigData.put(wanSubInfConfigData.getIpAddress(), wanSubInfConfigData);
                }
            } catch(IOException e) {
                LOGGER.warn("Failed to query wansubinf data", e);
                throw new ServiceException("Failed to query wansubinf data");
            }
        }

        if(deviceIdToWanSubInfConfigData.containsKey(ipAddress)) {
            return deviceIdToWanSubInfConfigData.get(ipAddress).getWanSubInf();
        }

        return wanSubInfList;
    }

}
