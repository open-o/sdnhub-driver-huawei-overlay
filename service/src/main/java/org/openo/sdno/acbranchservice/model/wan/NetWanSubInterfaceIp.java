/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.acbranchservice.model.wan;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model converting class, converting SDNO model to adapter model. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jul 21, 2016
 */
public class NetWanSubInterfaceIp {

    private String interfaceName;

    private String mode;

    private String ip;

    private String netmask;

    @JsonProperty("Mode6")
    private String mode6;

    private String ipv6address;

    private String prifexlength;

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

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getMode6() {
        return mode6;
    }

    public void setMode6(String mode6) {
        this.mode6 = mode6;
    }

    public String getIpv6address() {
        return ipv6address;
    }

    public void setIpv6address(String ipv6address) {
        this.ipv6address = ipv6address;
    }

    public String getPrifexlength() {
        return prifexlength;
    }

    public void setPrifexlength(String prifexlength) {
        this.prifexlength = prifexlength;
    }
}
