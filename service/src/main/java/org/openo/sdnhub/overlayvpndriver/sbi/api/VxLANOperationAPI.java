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

package org.openo.sdnhub.overlayvpndriver.sbi.api;

import java.util.HashMap;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.model.vxlan.adapter.NetVni;
import org.openo.sdnhub.overlayvpndriver.model.vxlan.adapter.NetVxLanDeviceModel;

/**
 * Operation API for VxLAN CLI.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-8
 */
public class VxLANOperationAPI extends OperationAPI {

    private static final String CREATE_VXLAN_SCRIPT = "scripts/CreateVxLAN.script";

    private static final String DELETE_VXLAN_SCRIPT = "scripts/DeleteVxLAN.script";

    public VxLANOperationAPI(String deviceId) throws ServiceException {
        super(deviceId);
    }

    public NetVxLanDeviceModel createVxLAN(NetVxLanDeviceModel deviceModel) throws ServiceException {
        String localAddress = deviceModel.getLocalAddress();
        NetVni netVni = deviceModel.getVniilist().get(0);
        String peerAddress = netVni.getPeerAddresslist().get(0);
        int vlan = netVni.getVlanlist().get(0);

        Map<String, String> replaceParamMap = new HashMap<String, String>();
        replaceParamMap.put("Bd_Id", String.valueOf(netVni.getVni()));
        replaceParamMap.put("Vni", String.valueOf(netVni.getVni()));
        replaceParamMap.put("Vlan", String.valueOf(vlan));
        replaceParamMap.put("LocalAddress", localAddress);
        replaceParamMap.put("PeerAddress", peerAddress);
        sshProtocol.executeShellScript(CREATE_VXLAN_SCRIPT, replaceParamMap);

        deviceModel.setUuid(String.valueOf(netVni.getVni()));

        return deviceModel;
    }

    public void deleteVxLAN(String vxLanId) throws ServiceException {
        Map<String, String> replaceParamMap = new HashMap<String, String>();
        replaceParamMap.put("Bd_Id", vxLanId);
        replaceParamMap.put("Vni", vxLanId);
        sshProtocol.executeShellScript(DELETE_VXLAN_SCRIPT, replaceParamMap);
    }
}
