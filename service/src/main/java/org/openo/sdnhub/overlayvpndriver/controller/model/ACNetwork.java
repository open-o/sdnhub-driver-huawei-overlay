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

package org.openo.sdnhub.overlayvpndriver.controller.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdnhub.overlayvpndriver.service.model.DhcpConfig;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

import java.util.List;

/**
 * Model class for ACNetwork.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class ACNetwork {

    @AString(require = true, min = 1, max = 128)
    private String id;

    @AString(require = true, min = 1, max = 128)
    private String name;

    @AString(require = true, min = 1, max = 128)
    private String use;

    private int vni;

    private long vlanId;

    @AString(require = false, min = 1, max = 128)
    private String ipAddress;

    @AString(require = false, min = 1, max = 128)
    private String maskAddress;

    @AString(require = false, min = 1, max = 128)
    private String ipv6Address;

    private Integer prefixLength;

    @JsonProperty(value = "isChangedMode")
    private boolean changedMode;

    @AString(require = false, min = 1, max = 128)
    private String description;

    @JsonProperty(value = "subnetList")
    private List<ACSubnet> subnetList;

    private boolean dhcpEnable;

    private boolean dhcp6Enable;

    private ACDhcp6Config dhcp6Config;

    private DhcpConfig dhcpConfig;

    public DhcpConfig getDhcpConfig() {
        return dhcpConfig;
    }

    public void setDhcpConfig(DhcpConfig dhcpConfig) {
        this.dhcpConfig = dhcpConfig;
    }

    public boolean isDhcpEnable() {
        return dhcpEnable;
    }

    public void setDhcpEnable(boolean dhcpEnable) {
        this.dhcpEnable = dhcpEnable;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMaskAddress() {
        return maskAddress;
    }

    public void setMaskAddress(String maskAddress) {
        this.maskAddress = maskAddress;
    }

    public ACDhcp6Config getDhcp6Config() {
        return dhcp6Config;
    }

    public void setDhcp6Config(ACDhcp6Config dhcp6Config) {
        this.dhcp6Config = dhcp6Config;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getChangedMode() {
        return changedMode;
    }

    public void setChangedMode(boolean changedMode) {
        this.changedMode = changedMode;
    }

    public List<ACSubnet> getSubnetList() {
        return subnetList;
    }

    public void setSubnetList(List<ACSubnet> subnetList) {
        this.subnetList = subnetList;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public long getVlanId() {
        return vlanId;
    }

    public void setVlanId(long vlanId) {
        this.vlanId = vlanId;
    }

    public int getVni() {
        return vni;
    }

    public void setVni(int vni) {
        this.vni = vni;
    }

    public boolean isDhcp6Enable() {
        return dhcp6Enable;
    }

    public void setDhcp6Enable(boolean dhcp6Enable) {
        this.dhcp6Enable = dhcp6Enable;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public Integer getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(Integer prefixLength) {
        this.prefixLength = prefixLength;
    }

}
