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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openo.sdnhub.overlayvpndriver.controller.model.Vni;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInstance;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInterface;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanTunnel;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class TranslateVxlanResponse {

    private TranslateVxlanResponse(){
        // private constructor
    }

    /**
     * <br/>
     *
     * @param sbiNeVxlanInstances
     * @param deviceId
     * @param vxLanDeviceModels
     * @since SDNHUB 0.5
     */
    public static void translateVxlanId(List<SbiNeVxlanInstance> sbiNeVxlanInstances, String deviceId,
            List<VxLanDeviceModel> vxLanDeviceModels) {

        for(VxLanDeviceModel vxLanDeviceModel : vxLanDeviceModels) {
            for(SbiNeVxlanInstance vxlanInstance : sbiNeVxlanInstances) {
                if(checkNeVxlan(deviceId, vxLanDeviceModel, vxlanInstance)) {
                    continue;
                }

                vxlanInstance.setExternalId(vxLanDeviceModel.getUuid());
                translateTunnelExternalId(vxlanInstance, vxLanDeviceModel);
                break;

            }
        }
    }

    private static void translateTunnelExternalId(SbiNeVxlanInstance vxlanInstance, VxLanDeviceModel vxLanDeviceModel) {
        if(CollectionUtils.isNotEmpty(vxLanDeviceModel.getVniList())) {
            for(Vni vni : vxLanDeviceModel.getVniList()) {
                if(vxlanInstance.getVni().equals(String.valueOf(vni.getVni()))) {
                    for(SbiNeVxlanTunnel tunnel : vxlanInstance.getVxlanTunnelList()) {
                        tunnel.setExternalId(vni.getUuid());
                    }

                    for(SbiNeVxlanInterface vxlaninf : vxlanInstance.getVxlanInterfaceList()) {
                        vxlaninf.setExternalId(vni.getUuid());
                    }
                }
            }
        }
    }

    private static boolean checkNeVxlan(String deviceId, VxLanDeviceModel vxLanDeviceModel,
            SbiNeVxlanInstance vxlanInstance) {
        return !deviceId.equals(vxlanInstance.getUuid())
                || !vxLanDeviceModel.getName().equals(vxlanInstance.getConnectionId());
    }
}
