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

package org.openo.sdno.overlayvpndriver.sbi.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;

/**
 * Operation API for IpSec CLI.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-8
 */
public class IpSecOperationAPI extends OperationAPI {

    private static final String CREATE_IPSEC_SCRIPT = "scripts/CreateIpSec.script";

    private static final String DELETE_IPSEC_SCRIPT = "scripts/DeleteIpSec.script";

    private static final int MIN_ACL_NUMBER = 3000;

    private static final int MAX_ACL_NUMBER = 4000;

    public IpSecOperationAPI(String deviceId) throws ServiceException {
        super(deviceId);
    }

    /**
     * Create IpSec Connection.<br>
     * 
     * @param ipSecModel NetIpSecModel data
     * @throws ServiceException when create IpSec failed
     * @since SDNO 0.5
     */
    public void createIpSec(NetIpSecModel ipSecModel) throws ServiceException {
        Random rand = new Random();
        int randACLId = rand.nextInt(MAX_ACL_NUMBER - MIN_ACL_NUMBER) + MIN_ACL_NUMBER;
        ipSecModel.setUuid(String.valueOf(randACLId));
        NetIpSecConn ipSecConn = ipSecModel.getIpsecConnection().get(0);
        Map<String, String> replaceParamMap = new HashMap<String, String>();
        replaceParamMap.put("Acl_Number", ipSecModel.getUuid());
        replaceParamMap.put("IpSecName", ipSecModel.getUuid());
        replaceParamMap.put("PeerAddress", ipSecConn.getIke().getPeerAddress());
        replaceParamMap.put("Auth_Algorithm", ipSecConn.getIpSec().getEspAuthAlgorithm());
        replaceParamMap.put("Encrypt_Algorithm", ipSecConn.getIpSec().getEspEncryptionAlgorithm());
        sshProtocol.executeShellScript(CREATE_IPSEC_SCRIPT, replaceParamMap);
    }

    /**
     * Delete IpSec Connection.<br>
     * 
     * @param ipSecModel NetIpSecModel Data
     * @throws ServiceException when create IpSec failed
     * @since SDNO 0.5
     */
    public void deleteIpSec(NetIpSecModel ipSecModel) throws ServiceException {
        Map<String, String> replaceParamMap = new HashMap<String, String>();
        replaceParamMap.put("Acl_Number", ipSecModel.getUuid());
        replaceParamMap.put("IpSecName", ipSecModel.getUuid());
        sshProtocol.executeShellScript(DELETE_IPSEC_SCRIPT, replaceParamMap);
    }

}
