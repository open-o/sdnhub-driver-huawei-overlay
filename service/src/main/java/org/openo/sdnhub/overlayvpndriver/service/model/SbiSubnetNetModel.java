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

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.NONInvField;
import org.openo.sdno.overlayvpn.model.v2.basemodel.BaseServiceModel;
import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * Model class of SBI Subnet.<br>
 *
 * @author
 * @version SDNHUB 0.5 2017-1-4
 */
@MOResType(infoModelName = "localsite_subnetnetmodel")
public class SbiSubnetNetModel extends BaseServiceModel {

    /**
     * Device UUid
     */
    @AUuid(require = true)
    private String neId;

    /**
     * Controller Uuid
     */
    @AUuid(require = true)
    private String controllerId;

    /**
     * NetworkId returned by AC
     */
    @AUuid(require = false)
    private String networkId;

    /**
     * Subnet Model Uuid
     */
    @AUuid(require = true)
    private String serviceSubnetId;

    /**
     * Vni
     */
    @AString(require = false)
    private String vni;

    /**
     * Vlan Id
     */
    @AString(require = false)
    private String vlanId;

    /**
     * Subetnet IpAddress
     */
    @AString(require = true)
    private String cidrIpAddress;

    /**
     * Subnet Ip Mask
     */
    @AString(require = true)
    private String cidrMask;

    /**
     * Gateway Ip Address
     */
    private String gatewayIp;

    /**
     * Enable Dhcp or not
     */
    private String enableDhcp;

    /**
     * Start of Subnet IpAddress
     */
    @AString(require = false)
    private String ipRangeStartIp;

    /**
     * End of Subnet IpAddress
     */
    @AString(require = false)
    private String ipRangeEndIp;

    /**
     * Use of subnet
     */
    @AString(require = true, scope = "terminal,vm")
    private String useMode;

    /**
     * Enable mode change or not
     */
    private String changedMode = "false";

    /**
     * Mode of Dhcp
     */
    @AString(require = true, scope = "server,relay")
    private String dhcpMode = "server";

    /**
     * Mode of Dns
     */
    @JsonProperty(value = "dnsServerMode")
    @AString(require = true, scope = "systemsetting,custom")
    private String dnsMode = "custom";

    /**
     * Whether tenant time is unlimited or not
     */
    @AString(require = false, scope = "true,false")
    private String unlimit = "true";

    /**
     * Ipv6 Address
     */
    @AString(require = false)
    private String ipv6Address;

    /**
     * Prefix length of Ipv6 Address
     */
    @AString(require = false)
    private String prefixLength;

    /**
     * Enable Ipv6 Dhcp or not
     */
    @AString(require = false, scope = "true,false")
    private String dhcp6Enable = "false";

    /**
     * Mode of Ipv6 Address
     */
    @AString(require = false, scope = "server,relay")
    private String dhcp6Mode = "server";

    /**
     * Prior Dns Server
     */
    @NONInvField
    @AString(require = false)
    private String priorDnsServer;

    /**
     * Standby Dns Server
     */
    @NONInvField
    @AString(require = false)
    private String standbyDnsServer;

    public SbiSubnetNetModel() { super(); }

    public SbiSubnetNetModel(String neId, String controllerId, String networkId) {
        this();
        this.neId = neId;
        this.controllerId = controllerId;
        this.networkId = networkId;
    }

    public String getNeId() {
        return neId;
    }

    public void setNeId(String neId) {
        this.neId = neId;
    }

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getServiceSubnetId() {
        return serviceSubnetId;
    }

    public void setServiceSubnetId(String serviceSubnetId) {
        this.serviceSubnetId = serviceSubnetId;
    }

    public String getVni() {
        return vni;
    }

    public void setVni(String vni) {
        this.vni = vni;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

    public String getCidrIpAddress() {
        return cidrIpAddress;
    }

    public void setCidrIpAddress(String cidrIpAddress) {
        this.cidrIpAddress = cidrIpAddress;
    }

    public String getCidrMask() {
        return cidrMask;
    }

    public void setCidrMask(String cidrMask) {
        this.cidrMask = cidrMask;
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp;
    }

    public String getEnableDhcp() {
        return enableDhcp;
    }

    public void setEnableDhcp(String enableDhcp) {
        this.enableDhcp = enableDhcp;
    }

    public String getIpRangeStartIp() {
        return ipRangeStartIp;
    }

    public void setIpRangeStartIp(String ipRangeStartIp) {
        this.ipRangeStartIp = ipRangeStartIp;
    }

    public String getIpRangeEndIp() {
        return ipRangeEndIp;
    }

    public void setIpRangeEndIp(String ipRangeEndIp) {
        this.ipRangeEndIp = ipRangeEndIp;
    }

    public String getUseMode() {
        return useMode;
    }

    public void setUseMode(String useMode) {
        this.useMode = useMode;
    }

    public String getChangedMode() {
        return changedMode;
    }

    public void setChangedMode(String changedMode) {
        this.changedMode = changedMode;
    }

    public String getDhcpMode() {
        return dhcpMode;
    }

    public void setDhcpMode(String dhcpMode) {
        this.dhcpMode = dhcpMode;
    }

    public String getDnsMode() {
        return dnsMode;
    }

    public void setDnsMode(String dnsMode) {
        this.dnsMode = dnsMode;
    }

    public String getUnlimit() {
        return unlimit;
    }

    public void setUnlimit(String unlimit) {
        this.unlimit = unlimit;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public String getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(String prefixLength) {
        this.prefixLength = prefixLength;
    }

    public String getDhcp6Enable() {
        return dhcp6Enable;
    }

    public void setDhcp6Enable(String dhcp6Enable) {
        this.dhcp6Enable = dhcp6Enable;
    }

    public String getDhcp6Mode() {
        return dhcp6Mode;
    }

    public void setDhcp6Mode(String dhcp6Mode) {
        this.dhcp6Mode = dhcp6Mode;
    }

    public String getPriorDnsServer() {
        return priorDnsServer;
    }

    public void setPriorDnsServer(String priorDnsServer) {
        this.priorDnsServer = priorDnsServer;
    }

    public String getStandbyDnsServer() {
        return standbyDnsServer;
    }

    public void setStandbyDnsServer(String standbyDnsServer) {
        this.standbyDnsServer = standbyDnsServer;
    }
    /**
     * Override equals Function.<br>
     *
     * @param obj other Object
     * @return true if this object equals to other object
     * @since SDNO 0.5
     */
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

        SbiSubnetNetModel other = (SbiSubnetNetModel) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }
}
