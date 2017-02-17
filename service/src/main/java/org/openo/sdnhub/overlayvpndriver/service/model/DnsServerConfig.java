/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

import org.openo.sdno.overlayvpn.verify.annotation.AIp;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Class of DNS Server Configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 February 14, 2016
 */
public class DnsServerConfig {

    /**
     * DNS Server Mode: systemsetting Or custom
     */
    @AString(require = true, scope = "systemsetting,custom")
    private String dnsServerMode;

    /**
     * Master DNS Server
     */
    @AIp
    private String priorDnsServer;

    /**
     * Slave DNS Server
     */
    @AIp
    private String standbyDnsServer;

    /**
     * @return Returns the dnsServerMode.
     */
    public String getDnsServerMode() {
        return dnsServerMode;
    }

    /**
     * @param dnsServerMode The dnsServerMode to set.
     */
    public void setDnsServerMode(String dnsServerMode) {
        this.dnsServerMode = dnsServerMode;
    }

    /**
     * @return Returns the priorDnsServer.
     */
    public String getPriorDnsServer() {
        return priorDnsServer;
    }

    /**
     * @param priorDnsServer The priorDnsServer to set.
     */
    public void setPriorDnsServer(String priorDnsServer) {
        this.priorDnsServer = priorDnsServer;
    }

    /**
     * @return Returns the standbyDnsServer.
     */
    public String getStandbyDnsServer() {
        return standbyDnsServer;
    }

    /**
     * @param standbyDnsServer The standbyDnsServer to set.
     */
    public void setStandbyDnsServer(String standbyDnsServer) {
        this.standbyDnsServer = standbyDnsServer;
    }

}
