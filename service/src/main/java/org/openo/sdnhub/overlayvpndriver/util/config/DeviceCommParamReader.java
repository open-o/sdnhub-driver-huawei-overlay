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

package org.openo.sdnhub.overlayvpndriver.util.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.overlayvpn.brs.invdao.NetworkElementInvDao;
import org.openo.sdno.overlayvpn.brs.model.NetworkElementMO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Get device communication parameter configuration. <br>
 * 
 * @author
 * @version SDNO 0.5 Jul 21, 2016
 */
public class DeviceCommParamReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceCommParamReader.class);

    private static final String CFG_FILE_PATH = "device/deviceparam.json";

    private static Map<String, DeviceParam> deviceIdToCommParamMap = new HashMap<String, DeviceParam>();

    /**
     * Constructor<br>
     * 
     * @since SDNHUB 0.5
     */
    private DeviceCommParamReader() {
    }

    /**
     * Get Device Communication Parameter by device id.<br>
     * 
     * @param ipAddress device ipAddress
     * @return device communication parameter
     * @throws ServiceException when get communication parameter
     * @since SDNHUB 0.5
     */
    public static synchronized DeviceParam getDeviceCommParam(String deviceId) throws ServiceException {

        // Query device ipAddress by native id
        NetworkElementInvDao neInvDao = new NetworkElementInvDao();
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("nativeID", deviceId);
        List<NetworkElementMO> networkElementList = neInvDao.query(queryMap);

        if(CollectionUtils.isEmpty(networkElementList)) {
            LOGGER.error("This device does not exist");
            throw new ServiceException("ServiceException");
        }

        String ipAddress = networkElementList.get(0).getIpAddress();
        return getDeviceCommParamByIpAddress(ipAddress);
    }

    private static synchronized DeviceParam getDeviceCommParamByIpAddress(String ipAddress) {

        if(deviceIdToCommParamMap.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                byte[] bytes = Files.readAllBytes(Paths.get(CFG_FILE_PATH));
                List<DeviceParam> deviceCommParamList =
                        mapper.readValue(bytes, new TypeReference<List<DeviceParam>>() {});
                for(DeviceParam deviceCommParam : deviceCommParamList) {
                    deviceIdToCommParamMap.put(deviceCommParam.getIpAddress(), deviceCommParam);
                }
            } catch(IOException e) {
                LOGGER.error("Read device comm param failed.");
                return null;
            }
        }

        return deviceIdToCommParamMap.get(ipAddress);
    }

}
