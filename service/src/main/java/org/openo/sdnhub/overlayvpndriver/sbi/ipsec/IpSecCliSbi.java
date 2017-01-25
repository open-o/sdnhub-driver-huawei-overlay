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

package org.openo.sdnhub.overlayvpndriver.sbi.ipsec;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdnhub.overlayvpndriver.sbi.api.IpSecOperationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SBI Class of IpSec CLI Interface.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-07
 */
public class IpSecCliSbi {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpSecCliSbi.class);

    /**
     * Create IpSec Instance.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @param netIpSecModel NetIpSecModel data
     * @return NetIpSecModel Created
     * @throws ServiceException when update failed
     * @since SDNHUB 0.5
     */
    public List<NetIpSecModel> update(String ctrlUuid, String deviceId, NetIpSecModel netIpSecModel)
            throws ServiceException {

        List<NetIpSecModel> ipsecModelList = new ArrayList<NetIpSecModel>(1);
        ipsecModelList.add(netIpSecModel);

        List<NetIpSecConn> ipSecConnList = netIpSecModel.getIpsecConnection();
        if(CollectionUtils.isEmpty(ipSecConnList)) {
            LOGGER.warn("No IpSec Connection in IpSec Model Data");
            return ipsecModelList;
        }

        IpSecOperationAPI operationAPI = new IpSecOperationAPI(deviceId);
        for(NetIpSecConn ipSecConn : ipSecConnList) {
            if(!ipSecConn.isDeleteMode()) {
                operationAPI.createIpSec(netIpSecModel);
            } else {
                operationAPI.deleteIpSec(netIpSecModel);
            }
        }

        return ipsecModelList;
    }
}
