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

import java.util.List;

import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Model class for Filter.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class Filter {

    @AInt(min = 1, max = 4094)
    private String vlanId;

    @AInt(min = 1, max = 7)
    private String vlanPriority;

    @AString
    private String inboundInterface;

    private List<AclPolicy> aclPolicy;

    private String l2Protocol;

    private String sourceMac;

    private String destinationMac;

    private String sourceMacMask;

    private String destMacMask;

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

    public String getInboundInterface() {
        return inboundInterface;
    }

    public void setInboundInterface(String inboundInterface) {
        this.inboundInterface = inboundInterface;
    }

    public List<AclPolicy> getAclPolicy() {
        return aclPolicy;
    }

    public void setAclPolicy(List<AclPolicy> aclPolicy) {
        this.aclPolicy = aclPolicy;
    }

    public String getL2Protocol() {
        return l2Protocol;
    }

    public void setL2Protocol(String l2Protocol) {
        this.l2Protocol = l2Protocol;
    }

    public String getSourceMac() {
        return sourceMac;
    }

    public void setSourceMac(String sourceMac) {
        this.sourceMac = sourceMac;
    }

    public String getDestinationMac() {
        return destinationMac;
    }

    public void setDestinationMac(String destinationMac) {
        this.destinationMac = destinationMac;
    }

    public String getSourceMacMask() {
        return sourceMacMask;
    }

    public void setSourceMacMask(String sourceMacMask) {
        this.sourceMacMask = sourceMacMask;
    }

    public String getDestMacMask() {
        return destMacMask;
    }

    public void setDestMacMask(String destMacMask) {
        this.destMacMask = destMacMask;
    }

}
