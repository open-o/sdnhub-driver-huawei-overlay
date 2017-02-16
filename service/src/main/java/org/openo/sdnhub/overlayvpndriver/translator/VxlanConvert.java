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

package org.openo.sdnhub.overlayvpndriver.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.PortVlan;
import org.openo.sdnhub.overlayvpndriver.controller.model.Vni;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.rest.VxLanRoaResource;
import org.openo.sdno.overlayvpn.model.common.enums.vxlan.VxlanAccessType;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInstance;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInterface;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanTunnel;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * Translator class for service SBI VxLan device model conversion to controller specific model.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jun 19, 2017
 */
public class VxlanConvert {

    private static final Logger LOGGER = LoggerFactory.getLogger(VxLanRoaResource.class);

    private static final String CONTROL = "control";

    private VxlanConvert(){
    }

    /**
     * Translate service SBI VxLan device model structure to controller specific structure.<br>
     *
     * @param vxLanInstanceList collection of VxLan configuration from service SBI
     * @return mapping of neId with collection of controller specific VxLan device model structure
     * @since SDNHUB 0.5
     */
    public static Map<String, List<VxLanDeviceModel>>
            convertVxlanInsToNetVxlanDeviceModel(List<SbiNeVxlanInstance> vxLanInstanceList) {
        Map<String, List<VxLanDeviceModel>> vxlanDeviceModelMap =
                new ConcurrentHashMap<>();

        Iterator<SbiNeVxlanInstance> iterator = vxLanInstanceList.iterator();
        while(iterator.hasNext()) {
            SbiNeVxlanInstance vxLanInstance = iterator.next();

            boolean isNeedDeploy = false;
            if(!StringUtils.isEmpty(vxLanInstance.getExternalId())) {
                isNeedDeploy = true;
            } else {
                for(SbiNeVxlanTunnel tunnel : vxLanInstance.getVxlanTunnelList()) {
                    if(StringUtils.isEmpty(tunnel.getExternalId())) {
                        isNeedDeploy = true;
                        break;
                    }
                }
            }

            if(!isNeedDeploy) {
                LOGGER.debug("removing vxlan :" + vxLanInstance.toString());
                iterator.remove();
                continue;
            }

            String neId = vxLanInstance.getDeviceId();
            if(!vxlanDeviceModelMap.containsKey(neId)) {
                vxlanDeviceModelMap.put(neId, new ArrayList<VxLanDeviceModel>());
            }

            vxlanDeviceModelMap.get(neId).add(convertToVxlanDeviceModel(vxLanInstance));
        }
        return vxlanDeviceModelMap;
    }

    private static VxLanDeviceModel convertToVxlanDeviceModel(SbiNeVxlanInstance vxLanInstance) {
        VxLanDeviceModel netVxLanDeviceModel = new VxLanDeviceModel();
        if(StringUtils.isNotEmpty(vxLanInstance.getExternalId())) {
            netVxLanDeviceModel.setUuid(vxLanInstance.getExternalId());
        }
        netVxLanDeviceModel.setName(vxLanInstance.getConnectionId());
        netVxLanDeviceModel.setLocalAddress(vxLanInstance.getVxlanTunnelList().get(0).getSourceAddress());
        List<Vni> vniList = new ArrayList<>();
        vniList.add(createVni(vxLanInstance));
        netVxLanDeviceModel.setVniList(vniList);
        return netVxLanDeviceModel;
    }

    private static Vni createVni(SbiNeVxlanInstance vxLanInstance) {
        Vni netVni = new Vni();
        netVni.setVni(Integer.parseInt(vxLanInstance.getVni()));
        netVni.setMacLearingMode(CONTROL);
        List<String> peerAddressList = new ArrayList<>();

        for(SbiNeVxlanTunnel vxlanTunnel : vxLanInstance.getVxlanTunnelList()) {
            peerAddressList.add(vxlanTunnel.getDestAddress());
        }
        netVni.setPeerAddresslist(peerAddressList);

        List<String> portList = new ArrayList<>();
        List<Integer> vlanList = new ArrayList<>();
        List<PortVlan> portvlanlist = new ArrayList<>();

        for(SbiNeVxlanInterface vxlanInterface : vxLanInstance.getVxlanInterfaceList()) {
            if(vxlanInterface.getAccessType().equals(VxlanAccessType.PORT.getName())) {
                portList.add(vxlanInterface.getPortNativeId());
            } else if(vxlanInterface.getAccessType().equals(VxlanAccessType.DOT1Q.getName())) {
                int vlan = Integer.parseInt(vxlanInterface.getDot1qVlanBitmap());
                if(StringUtils.isEmpty(vxlanInterface.getPortNativeId())) {
                    vlanList.add(vlan);
                } else {
                    PortVlan netPortVlan = new PortVlan(vxlanInterface.getPortNativeId(), vlan);
                    portvlanlist.add(netPortVlan);
                }
            }
        }

        netVni.setPortlist(portList);
        netVni.setVlanlist(vlanList);
        netVni.setPortvlanlist(portvlanlist);

        return netVni;
    }

    /**
     * Validates VxLan configuration from service SBI.<br>
     *
     * @param vxLanInstanceList collection of VxLan configuration from service SBI
     * @return collection of VxLan configuration
     * @since SDNHUB 0.5
     */
    public static List<SbiNeVxlanInstance> checkInputCreateVxlan(List<SbiNeVxlanInstance> vxLanInstanceList)
            throws ServiceException {

        if(CollectionUtils.isEmpty(vxLanInstanceList)) {
            LOGGER.error("createVxlan failed, vxLanInstanceList is null");
            SvcExcptUtil.throwBadRequestException("createVxlan failed, vxlanInstanceList is null");
        }

        for(SbiNeVxlanInstance tempVxlan : vxLanInstanceList) {
            ValidationUtil.validateModel(tempVxlan);
            if(CollectionUtils.isEmpty(tempVxlan.getVxlanInterfaceList())) {
                LOGGER.error("createVxlan failed, vxlanInterfaceList is null");
                SvcExcptUtil.throwBadRequestException("createVxlan failed, vxlanInterfaceList is null");
            }

            if(CollectionUtils.isEmpty(tempVxlan.getVxlanTunnelList())) {
                LOGGER.error("createVxlan failed, vxlanTunnelList is null");
                SvcExcptUtil.throwBadRequestException("createVxlan failed, vxlanTunnelList is null");
            }
        }
        return vxLanInstanceList;
    }

    /**
     * Prepares mapping of VxLan configuration based on device id.<br/>
     *
     * @param vxlanInstanceList collection of VxLan configuration
     * @return mapping of VxLan configuration based on device id.
     * @since SDNHUB 0.5
     */
    public static Map<String, List<SbiNeVxlanInstance>>
            divideVxlanInsByDeviceId(List<SbiNeVxlanInstance> vxlanInstanceList) {
        Map<String, List<SbiNeVxlanInstance>> deviceIdToVxlanInsMap = new HashMap<>();

        for(SbiNeVxlanInstance sbiNeVxlanInstance : vxlanInstanceList) {
            String deviceId = sbiNeVxlanInstance.getDeviceId();
            if(!deviceIdToVxlanInsMap.containsKey(deviceId)) {
                deviceIdToVxlanInsMap.put(deviceId, new ArrayList<SbiNeVxlanInstance>());
            }

            deviceIdToVxlanInsMap.get(deviceId).add(sbiNeVxlanInstance);
        }

        return deviceIdToVxlanInsMap;
    }
}
