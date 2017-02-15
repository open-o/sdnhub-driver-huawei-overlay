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

import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * SBI model class of Interface Ip Address Configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 2017-1-4
 */
public class SbiInterfaceIpConfig {

    /**
     * Interface name
     */
    private String interfaceName;

    /**
     * Ipv4 address allocation mode
     */
    @AString(require=true, scope="dhcp,manual")
    private String mode;

    /**
     * Ipv4 address
     */
    private String ip;

    /**
     * Ipv4 netMask
     */
    private String netMask;

    /**
     * Ipv6 address allocation mode
     */
    @AString(require=true, scope="dhcp,manual")
    private String mode6;

    /**
     * Ipv6 address
     */
    private String ipv6Address;

    /**
     * Ipv6 prefixLength
     */
    private Integer prefixLength;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNetMask() {
        return netMask;
    }

    public void setNetMask(String netMask) {
        this.netMask = netMask;
    }

    public String getMode6() {
        return mode6;
    }

    public void setMode6(String mode6) {
        this.mode6 = mode6;
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
