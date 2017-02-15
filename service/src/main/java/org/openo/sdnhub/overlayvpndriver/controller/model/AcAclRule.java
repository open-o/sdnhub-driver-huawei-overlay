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

/**
 * Model class for AcAclRule.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class AcAclRule {

    private long id;

    @AString(require = true)
    private String policy;

    private Integer protocol;

    private String icmpName;

    private String tcpSyn;

    private String desIp;

    private Integer desPort;

    private Integer desVlan;

    private String srcIp;

    private Integer srcPort;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getDesIp() {
        return desIp;
    }

    public void setDesIp(String desIp) {
        this.desIp = desIp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public Integer getProtocol() {
        return protocol;
    }

    public void setProtocol(Integer protocol) {
        this.protocol = protocol;
    }

    public String getIcmpName() {
        return icmpName;
    }

    public void setIcmpName(String icmpName) {
        this.icmpName = icmpName;
    }

    public String getTcpSyn() {
        return tcpSyn;
    }

    public void setTcpSyn(String tcpSyn) {
        this.tcpSyn = tcpSyn;
    }

    public Integer getDesPort() {
        return desPort;
    }

    public void setDesPort(Integer desPort) {
        this.desPort = desPort;
    }

    public Integer getDesVlan() {
        return desVlan;
    }

    public void setDesVlan(Integer desVlan) {
        this.desVlan = desVlan;
    }

    public Integer getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Integer srcPort) {
        this.srcPort = srcPort;
    }

}
