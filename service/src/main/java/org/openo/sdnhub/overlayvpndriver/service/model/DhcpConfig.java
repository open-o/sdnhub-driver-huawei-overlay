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

import java.util.List;

/**
 * Model class of DHCP Configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 February 14, 2017
 */
public class DhcpConfig {

    /**
     * DHCP Mode:sever or relay
     */
    @AString(require = true, scope = "sever,relay")
    private String dhcpMode;

    /**
     * Master Wins
     */
    @AIp
    private String masterWins;

    /**
     * Slave Wins
     */
    @AIp
    private String slaveWins;

    /**
     * Configuration of DNS Service
     */
    @AString(require = true)
    private DnsServerConfig dnsConfig;

    @AString(require = true)
    private TimeConfig timeConfig;

    private List<ExcludedAddress> excludedAddresses;

    private List<StaticBinding> staticBindings;

    private List<String> relayServerIps;

    private List<DhcpServerOption> dhcpServerOptions;

    /**
     * @return Returns the dhcpMode.
     */
    public String getDhcpMode() {
        return dhcpMode;
    }

    /**
     * @param dhcpMode The dhcpMode to set.
     */
    public void setDhcpMode(String dhcpMode) {
        this.dhcpMode = dhcpMode;
    }

    /**
     * @return Returns the masterWins.
     */
    public String getMasterWins() {
        return masterWins;
    }

    /**
     * @param masterWins The masterWins to set.
     */
    public void setMasterWins(String masterWins) {
        this.masterWins = masterWins;
    }

    /**
     * @return Returns the slaveWins.
     */
    public String getSlaveWins() {
        return slaveWins;
    }

    /**
     * @param slaveWins The slaveWins to set.
     */
    public void setSlaveWins(String slaveWins) {
        this.slaveWins = slaveWins;
    }

    /**
     * @return Returns the dnsConfig.
     */
    public DnsServerConfig getDnsConfig() {
        return dnsConfig;
    }

    /**
     * @param dnsConfig The dnsConfig to set.
     */
    public void setDnsConfig(DnsServerConfig dnsConfig) {
        this.dnsConfig = dnsConfig;
    }

    /**
     * @return Returns the timeConfig.
     */
    public TimeConfig getTimeConfig() {
        return timeConfig;
    }

    /**
     * @param timeConfig The timeConfig to set.
     */
    public void setTimeConfig(TimeConfig timeConfig) {
        this.timeConfig = timeConfig;
    }

    /**
     * @return Returns the excludedAddresses.
     */
    public List<ExcludedAddress> getExcludedAddresses() {
        return excludedAddresses;
    }

    /**
     * @param excludedAddresses The excludedAddresses to set.
     */
    public void setExcludedAddresses(List<ExcludedAddress> excludedAddresses) {
        this.excludedAddresses = excludedAddresses;
    }

    /**
     * @return Returns the staticBindings.
     */
    public List<StaticBinding> getStaticBindings() {
        return staticBindings;
    }

    /**
     * @param staticBindings The staticBindings to set.
     */
    public void setStaticBindings(List<StaticBinding> staticBindings) {
        this.staticBindings = staticBindings;
    }

    /**
     * @return Returns the relayServerIps.
     */
    public List<String> getRelayServerIps() {
        return relayServerIps;
    }

    /**
     * @param relayServerIps The relayServerIps to set.
     */
    public void setRelayServerIps(List<String> relayServerIps) {
        this.relayServerIps = relayServerIps;
    }

    /**
     * @return Returns the dhcpServerOptions.
     */
    public List<DhcpServerOption> getDhcpServerOptions() {
        return dhcpServerOptions;
    }

    /**
     * @param dhcpServerOptions The dhcpServerOptions to set.
     */
    public void setDhcpServerOptions(List<DhcpServerOption> dhcpServerOptions) {
        this.dhcpServerOptions = dhcpServerOptions;
    }

}
