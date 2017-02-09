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
 * <br/>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class VxlanConvert {

    private static final Logger LOGGER = LoggerFactory.getLogger(VxLanRoaResource.class);

    private static final String CONTROL = "control";

    /**
     * <br/>
     * 
     * @param vxLanInstanceList
     * @return
     * @since SDNHUB 0.5
     */
    public static Map<String, List<VxLanDeviceModel>>
            convertVxlanInsToNetVxlanDeviceModel(List<SbiNeVxlanInstance> vxLanInstanceList) {
        Map<String, List<VxLanDeviceModel>> vxlanDeviceModelMap =
                new ConcurrentHashMap<String, List<VxLanDeviceModel>>();

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
        List<Vni> vniList = new ArrayList<Vni>();
        vniList.add(createVni(vxLanInstance));
        netVxLanDeviceModel.setVniList(vniList);
        return netVxLanDeviceModel;
    }

    private static Vni createVni(SbiNeVxlanInstance vxLanInstance) {
        Vni netVni = new Vni();
        netVni.setVni(Integer.parseInt(vxLanInstance.getVni()));
        netVni.setMacLearingMode(CONTROL);
        List<String> peerAddressList = new ArrayList<String>();

        for(SbiNeVxlanTunnel vxlanTunnel : vxLanInstance.getVxlanTunnelList()) {
            peerAddressList.add(vxlanTunnel.getDestAddress());
        }
        netVni.setPeerAddresslist(peerAddressList);

        List<String> portList = new ArrayList<String>();
        List<Integer> vlanList = new ArrayList<Integer>();
        List<PortVlan> portvlanlist = new ArrayList<PortVlan>();

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
     * <br/>
     * 
     * @param vxLanInstanceList
     * @param toBeUpdated
     * @param toBeDeleted
     * @since SDNHUB 0.5
     */
    public static void divideVxlanTOUpdateOrDelete(List<SbiNeVxlanInstance> vxLanInstanceList,
            Map<String, List<SbiNeVxlanInstance>> toBeUpdated, Map<String, List<SbiNeVxlanInstance>> toBeDeleted) {

        for(SbiNeVxlanInstance sbiNeVxlanInstance : vxLanInstanceList) {
            // TODO getModifyMask is not there in SbiNeVxlanInstance.java
            // if(!ModifyMaskType.DELETE.getName().equals(sbiNeVxlanInstance.getModifyMask())) {
            List<SbiNeVxlanTunnel> vxlanTunnelList = sbiNeVxlanInstance.getVxlanTunnelList();
            Iterator<SbiNeVxlanTunnel> tunnelIterator = vxlanTunnelList.iterator();

            while(tunnelIterator.hasNext()) {
                SbiNeVxlanTunnel nevxlanTunnel = tunnelIterator.next();
                // TODO getModifyMask is not there in SbiNeVxlanInstance.java
                // if(ModifyMaskType.DELETE.getName().equals(nevxlanTunnel.getModifyMask()) ||
                // StringUtils.isEmpty((nevxlanTunnel.getExternalId()))){
                tunnelIterator.remove();
                // }
            }
            // }
        }

        for(SbiNeVxlanInstance sbiNeVxlanInstance : vxLanInstanceList) {
            if(StringUtils.isEmpty(sbiNeVxlanInstance.getExternalId())) {
                continue;
            }

            String currDeviceId = sbiNeVxlanInstance.getUuid();

            if(CollectionUtils.isEmpty(sbiNeVxlanInstance.getVxlanTunnelList())) {

                if(!toBeDeleted.containsKey(currDeviceId)) {
                    toBeDeleted.put(currDeviceId, new ArrayList<SbiNeVxlanInstance>());
                }

                toBeDeleted.get(currDeviceId).add(sbiNeVxlanInstance);
                continue;
            }
            // TODO getModifyMask is not there in SbiNeVxlanInstance.java
            // if(!ModifyMaskType.DELETE.getName().equals(sbiNeVxlanInstance.getModifyMask())) {
            if(!toBeDeleted.containsKey(currDeviceId)) {
                toBeDeleted.put(currDeviceId, new ArrayList<SbiNeVxlanInstance>());
            }

            toBeDeleted.get(currDeviceId).add(sbiNeVxlanInstance);
            // } else {
            if(!toBeUpdated.containsKey(currDeviceId)) {
                toBeUpdated.put(currDeviceId, new ArrayList<SbiNeVxlanInstance>());
            }

            toBeUpdated.get(currDeviceId).add(sbiNeVxlanInstance);
        }
    }

    /**
     * <br/>
     * 
     * @param sbiNeVxlanInstances
     * @return
     * @since SDNHUB 0.5
     */
    public static Map<String, List<VxLanDeviceModel>>
            convertToDeviceModelForUpdate(List<SbiNeVxlanInstance> sbiNeVxlanInstances) {
        Map<String, List<VxLanDeviceModel>> vxlanDeviceModelMap =
                new ConcurrentHashMap<String, List<VxLanDeviceModel>>();

        for(SbiNeVxlanInstance vxlanInstance : sbiNeVxlanInstances) {
            String id = vxlanInstance.getUuid();

            if(!vxlanDeviceModelMap.containsKey(id)) {
                vxlanDeviceModelMap.put(id, new ArrayList<VxLanDeviceModel>());
            }

            vxlanDeviceModelMap.get(id).add(convertToVxlanDeviceModel(vxlanInstance));
        }

        return vxlanDeviceModelMap;
    }

    /**
     * <br/>
     * 
     * @param vxLanInstanceList
     * @return
     * @throws ServiceException
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
     * <br/>
     * 
     * @param vxlanInstanceList
     * @return
     * @since SDNHUB 0.5
     */
    public static Map<String, List<SbiNeVxlanInstance>>
            divideVxlanInsByDeviceId(List<SbiNeVxlanInstance> vxlanInstanceList) {
        Map<String, List<SbiNeVxlanInstance>> deviceIdToVxlanInsMap = new HashMap<String, List<SbiNeVxlanInstance>>();

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
