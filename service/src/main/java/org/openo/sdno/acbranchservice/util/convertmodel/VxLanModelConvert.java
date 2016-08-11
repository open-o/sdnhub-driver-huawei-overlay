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

package org.openo.sdno.acbranchservice.util.convertmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetPortVlan;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVni;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.overlayvpn.model.common.enums.vxlan.VxlanAccessType;
import org.openo.sdno.overlayvpn.model.netmodel.vxlan.NeVxlanInstance;
import org.openo.sdno.overlayvpn.model.netmodel.vxlan.NeVxlanInterface;
import org.openo.sdno.overlayvpn.model.netmodel.vxlan.NeVxlanTunnel;
import org.openo.sdno.overlayvpn.util.objreflectoper.UuidAllocUtil;

/**
 * Convert VxLan model from service to adapter. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jul 19, 2016
 */
public class VxLanModelConvert {

    private static final String CONTROL = "control";

    /**
     * Constructor<br/>
     * 
     * @since SDNO 0.5
     */
    private VxLanModelConvert() {

    }

    /**
     * Convert VxLan model from service to adapter. <br/>
     * 
     * @param vxLanInstanceList The list of NeVxlanInstance
     * @return The map of NeVxlanInstance list
     * @throws ServiceException When convert failed
     * @since SDNO 0.5
     */
    public static Map<String, List<NetVxLanDeviceModel>> convertModel(List<NeVxlanInstance> vxLanInstanceList)
            throws ServiceException {
        Map<String, List<NetVxLanDeviceModel>> vxlanDeviceModelMap =
                new ConcurrentHashMap<String, List<NetVxLanDeviceModel>>();

        Iterator<NeVxlanInstance> iterator = vxLanInstanceList.iterator();
        while(iterator.hasNext()) {
            NeVxlanInstance vxLanInstance = iterator.next();

            String neId = vxLanInstance.getNeId();
            if(!vxlanDeviceModelMap.containsKey(neId)) {
                vxlanDeviceModelMap.put(neId, new ArrayList<NetVxLanDeviceModel>());
            }

            vxlanDeviceModelMap.get(neId).add(convertToVxlanDeviceModel(vxLanInstance));
        }

        return vxlanDeviceModelMap;
    }

    private static NetVxLanDeviceModel convertToVxlanDeviceModel(NeVxlanInstance vxLanInstance) {
        NetVxLanDeviceModel netVxLanDeviceModel = new NetVxLanDeviceModel();

        UuidAllocUtil.allocUuid(netVxLanDeviceModel);
        netVxLanDeviceModel.setName(vxLanInstance.getUuid());
        netVxLanDeviceModel.setLocalAddress(vxLanInstance.getVxlanTunnelList().get(0).getSourceAddress());
        List<NetVni> vniList = new ArrayList<NetVni>();
        vniList.add(createVni(vxLanInstance));
        netVxLanDeviceModel.setVniilist(vniList);
        return netVxLanDeviceModel;
    }

    private static NetVni createVni(NeVxlanInstance neVxLanInstance) {
        NetVni netVni = new NetVni();
        netVni.setVni(Integer.parseInt(neVxLanInstance.getVni()));
        netVni.setMacLearingMode(CONTROL);

        List<String> peerAddressList = new ArrayList<String>();

        for(NeVxlanTunnel vxlanTunnel : neVxLanInstance.getVxlanTunnelList()) {
            peerAddressList.add(vxlanTunnel.getDestAddress());
        }
        netVni.setPeerAddresslist(peerAddressList);

        List<String> portList = new ArrayList<String>();
        List<Integer> vlanList = new ArrayList<Integer>();
        List<NetPortVlan> portvlanlist = new ArrayList<NetPortVlan>();

        for(NeVxlanInterface vxlanInterface : neVxLanInstance.getVxlanInterfaceList()) {
            if(vxlanInterface.getAccessType().equals(VxlanAccessType.PORT.getName())) {
                portList.add(vxlanInterface.getPortId());
            } else if(vxlanInterface.getAccessType().equals(VxlanAccessType.DOT1Q.getName())) {
                int vlan = Integer.parseInt(vxlanInterface.getDot1qVlanBitmap());
                if(StringUtils.isEmpty(vxlanInterface.getPortId())) {
                    vlanList.add(vlan);
                } else {
                    NetPortVlan netPortVlan = new NetPortVlan(vxlanInterface.getPortId(), vlan);
                    portvlanlist.add(netPortVlan);
                }
            }
        }

        netVni.setPortlist(portList);
        netVni.setVlanlist(vlanList);
        netVni.setPortvlanlist(portvlanlist);

        return netVni;
    }
}
