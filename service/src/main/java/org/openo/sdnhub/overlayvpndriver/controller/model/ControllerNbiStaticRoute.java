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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openo.sdnhub.overlayvpndriver.service.model.UuidModel;
import org.openo.sdno.util.ip.IpUtils;

/**
 * Model class from ControllerNbiStaticRoute<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class ControllerNbiStaticRoute extends UuidModel{

    private String description;

    private Long priority;

    private String vpnName;

    private String outInterface;

    private String nextHop;

    private boolean dhcp;

    private String vpnId;

    private String mask;

    private String nqaId;

    private String ip;

    private String ipv6Address;

    private Integer prefixLength;

    @JsonIgnore
    private String nbiRouteId;


    public ControllerNbiStaticRoute(String ip, String mask, String nextHop, String outInterface,
            String enableDhcp) {
        super();
        this.ip = ip;
        this.mask = IpUtils.prefixToMask(Integer.valueOf(mask));
        this.nextHop = nextHop;
        this.outInterface = outInterface;
        this.setDhcp("true".equalsIgnoreCase(enableDhcp));
    }

    public ControllerNbiStaticRoute(String ipv6, int prefixLength, String nextHop, String outInterface,
            String enableDhcp) {
        super();
        this.ipv6Address = ipv6;
        this.prefixLength = prefixLength;
        this.nextHop = nextHop;
        this.outInterface = outInterface;
        this.setDhcp("true".equalsIgnoreCase(enableDhcp));
    }

    public ControllerNbiStaticRoute() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getVpnName() {
        return vpnName;
    }

    public void setVpnName(String vpnName) {
        this.vpnName = vpnName;
    }

    public String getOutInterface() {
        return outInterface;
    }

    public void setOutInterface(String outInterface) {
        this.outInterface = outInterface;
    }

    public String getNextHop() {
        return nextHop;
    }

    public void setNextHop(String nextHop) {
        this.nextHop = nextHop;
    }

    public boolean getDhcp() {
        return dhcp;
    }

    public void setDhcp(boolean dhcp) {
        this.dhcp = dhcp;
    }

    public String getVpnId() {
        return vpnId;
    }

    public void setVpnId(String vpnId) {
        this.vpnId = vpnId;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getNqaId() {
        return nqaId;
    }

    public void setNqaId(String nqaId) {
        this.nqaId = nqaId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6address) {
        this.ipv6Address = ipv6address;
    }

    public String getNbiRouteId() {
        return nbiRouteId;
    }

    public void setNbiRouteId(String nbiRouteId) {
        this.nbiRouteId = nbiRouteId;
    }

    public Integer getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(Integer prefixLength) {
        this.prefixLength = prefixLength;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + this.getUuid() + ", description = " + description + ", priority = "
                + priority + ", vpnName = " + vpnName + ", outInterface = " + outInterface + ", nextHop = " + nextHop
                + ", dhcp = " + dhcp + ", vpnId = " + vpnId + ", mask = " + mask + ", nqaId = " + nqaId + ", ip = " + ip
                + "]";
    }
}
