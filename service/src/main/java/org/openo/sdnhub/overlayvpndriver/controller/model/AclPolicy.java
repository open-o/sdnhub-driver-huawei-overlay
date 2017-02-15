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

import org.openo.sdno.overlayvpn.verify.annotation.AIp;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class AclPolicy {

    @AString(require = true, scope = "deny,permit")
    private String policy;

    private Integer protocol;

    @AIp
    private String srcIp;

    @AIp
    private String srcNetMask;

    private Integer srcPort;

    @AIp
    private String descIp;

    @AIp
    private String desNetMask;

    private Integer desPort;

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Integer getProtocol() {
        return protocol;
    }

    public void setProtocol(Integer protocol) {
        this.protocol = protocol;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getSrcNetMask() {
        return srcNetMask;
    }

    public void setSrcNetMask(String srcNetMask) {
        this.srcNetMask = srcNetMask;
    }

    public String getDescIp() {
        return descIp;
    }

    public void setDescIp(String descIp) {
        this.descIp = descIp;
    }

    public String getDesNetMask() {
        return desNetMask;
    }

    public void setDesNetMask(String desNetMask) {
        this.desNetMask = desNetMask;
    }

    public Integer getDesPort() {
        return desPort;
    }

    public void setDesPort(Integer desPort) {
        this.desPort = desPort;
    }

    public Integer getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Integer srcPort) {
        this.srcPort = srcPort;
    }

}
