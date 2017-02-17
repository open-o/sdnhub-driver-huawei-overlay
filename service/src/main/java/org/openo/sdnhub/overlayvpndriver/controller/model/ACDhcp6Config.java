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

import org.openo.sdno.overlayvpn.verify.annotation.AString;

import java.util.List;

/**
 * Model class for ACDhcp6Config.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class ACDhcp6Config {

    @AString(require = true, min = 1, max = 128)
    private String dhcpMode;

    @AString(require = true, min = 1, max = 128)
    private String dnsServer;

    List<ExcludedIpV6Address> excludedAddresses6;

    public String getDhcpMode() {
        return dhcpMode;
    }

    public void setDhcpMode(String dhcpMode) {
        this.dhcpMode = dhcpMode;
    }

    public String getDnsServer() {
        return dnsServer;
    }

    public void setDnsServer(String dnsServer) {
        this.dnsServer = dnsServer;
    }

    public List<ExcludedIpV6Address> getExcludedAddresses6() {
        return excludedAddresses6;
    }

    public void setExcludedAddresses6(List<ExcludedIpV6Address> excludedAddresses6) {
        this.excludedAddresses6 = excludedAddresses6;
    }

}
