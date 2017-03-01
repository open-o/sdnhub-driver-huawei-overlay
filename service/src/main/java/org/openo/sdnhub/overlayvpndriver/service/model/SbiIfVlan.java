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

package org.openo.sdnhub.overlayvpndriver.service.model;

import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * Model class of Interface Vlan.<br>
 *
 * @author
 * @version SDNHUB 0.5 2017-1-4
 */
@MOResType(infoModelName = "localsite_ifvlan")
public class SbiIfVlan extends UuidModel {

    /**
     * Uuid of VlanModel
     */
    @AUuid(require = true)
    private String serviceVlanUuId;

    /**
     * Config Id return by AC
     */
    @AUuid(require = false)
    private String ethInterfaceConfigId;

    /**
     * Interface Id
     */
    private String ifId;

    /**
     * Interface Name
     */
    private String ifName;

    /**
     * Default Vlan
     */
    private Integer defaultVlan;

    /**
     * Link type
     */
    @AString(require = true, scope = "access,trunk,hybird,dot1q,qinq")
    private String linkType = "trunk";

    /**
     * Vlan Id
     */
    private String vlans;

    public String getServiceVlanUuId() {
        return serviceVlanUuId;
    }

    public void setServiceVlanUuId(String serviceVlanUuId) {
        this.serviceVlanUuId = serviceVlanUuId;
    }

    public String getEthInterfaceConfigId() {
        return ethInterfaceConfigId;
    }

    public void setEthInterfaceConfigId(String ethInterfaceConfigId) {
        this.ethInterfaceConfigId = ethInterfaceConfigId;
    }

    public String getIfId() {
        return ifId;
    }

    public void setIfId(String ifId) {
        this.ifId = ifId;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public Integer getDefaultVlan() {
        return defaultVlan;
    }

    public void setDefaultVlan(Integer defaultVlan) {
        this.defaultVlan = defaultVlan;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getVlans() {
        return vlans;
    }

    public void setVlans(String vlans) {
        this.vlans = vlans;
    }

    /**
     * Override equals Function.<br>
     *
     * @param obj other Object
     * @return true if this object equals to other object
     * @since SDNO 0.5
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (super.getClass() != obj.getClass()) {
            return false;
        }

        SbiIfVlan other = (SbiIfVlan) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (serviceVlanUuId != null ? serviceVlanUuId.hashCode() : 0);
        result = 31 * result + (ethInterfaceConfigId != null ? ethInterfaceConfigId.hashCode() : 0);
        result = 31 * result + (ifId != null ? ifId.hashCode() : 0);
        result = 31 * result + (ifName != null ? ifName.hashCode() : 0);
        result = 31 * result + (defaultVlan != null ? defaultVlan.hashCode() : 0);
        result = 31 * result + (linkType != null ? linkType.hashCode() : 0);
        result = 31 * result + (vlans != null ? vlans.hashCode() : 0);
        return result;
    }
}
