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

/**
 *
 * Model class for LoopBackPort.<br/>
 *
 * @author
 * @version     SDNHUB 0.5  Feb 19, 2017
 */
public class LoopBackPort {

    private String id;

    private String loopbackName;

    private Boolean dhcpMode;

    private String ipv4Address;

    private String ipv4Mask;

    private String ipv6Address;

    private String prefixLength;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getLoopbackName() {
        return loopbackName;
    }


    public void setLoopbackName(String loopbackName) {
        this.loopbackName = loopbackName;
    }


    public Boolean getDhcpMode() {
        return dhcpMode;
    }


    public void setDhcpMode(Boolean dhcpMode) {
        this.dhcpMode = dhcpMode;
    }


    public String getIpv4Address() {
        return ipv4Address;
    }


    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }


    public String getIpv4Mask() {
        return ipv4Mask;
    }


    public void setIpv4Mask(String ipv4Mask) {
        this.ipv4Mask = ipv4Mask;
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

}
