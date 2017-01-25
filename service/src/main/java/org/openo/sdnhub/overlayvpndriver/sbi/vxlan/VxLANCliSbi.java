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

package org.openo.sdnhub.overlayvpndriver.sbi.vxlan;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.sbi.api.VxLANOperationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SBI Class of VxLAN CLI Interface.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-07
 */
public class VxLANCliSbi {

    private static final Logger LOGGER = LoggerFactory.getLogger(VxLANCliSbi.class);

    /**
     * Create VxLAN Instance.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @param netVxLanDeviceModelList List of NetVxLanDeviceModel data
     * @return NetVxLanDeviceModel Created
     * @throws ServiceException when create VxLAN failed
     * @since SDNHUB 0.5
     */
    public List<NetVxLanDeviceModel> create(String ctrlUuid, String deviceId,
            List<NetVxLanDeviceModel> netVxLanDeviceModelList) throws ServiceException {

        if(CollectionUtils.isEmpty(netVxLanDeviceModelList)) {
            LOGGER.warn("netVxLanDeviceModelList is empty.");
            return netVxLanDeviceModelList;
        }

        VxLANOperationAPI operationAPI = new VxLANOperationAPI(deviceId);
        for(NetVxLanDeviceModel deviceModel : netVxLanDeviceModelList) {
            operationAPI.createVxLAN(deviceModel);
        }

        return netVxLanDeviceModelList;
    }

    /**
     * Delete VxLAN Instance.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @param vxLanId VxLAN Id
     * @throws ServiceException when delete VxLAN failed
     * @since SDNHUB 0.5
     */
    public void delete(String ctrlUuid, String deviceId, String vxLanId) throws ServiceException {
        VxLANOperationAPI operationAPI = new VxLANOperationAPI(deviceId);
        operationAPI.deleteVxLAN(vxLanId);
    }

}
