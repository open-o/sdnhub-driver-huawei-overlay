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
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Model class file for NQA.<br>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
@MOResType(infoModelName = "ipsec_nbi_nqa")
public class NQA extends UuidModel {

    private String srcIp;

    private String srcPortName;

    @AString(require = true)
    private String dstIp;

    private String dstPortName;

    private String testType;

    @AInt(min = 1, max = 604800)
    private Integer frequency;

    @AInt(min = 1, max = 15)
    private Integer probeCount;

    @AInt(min = 1, max = 60)
    private Integer timeout;

    @AInt(min = 1, max = 225)
    private Integer ttl;

    @AInt(min = 0, max = 255)
    private Integer tos;

    @JsonIgnore
    private String ipsecConnectionId;

    private String nqaState;

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getSrcPortName() {
        return srcPortName;
    }

    public void setSrcPortName(String srcPortName) {
        this.srcPortName = srcPortName;
    }

    public String getDstIp() {
        return dstIp;
    }

    public void setDstIp(String dstIp) {
        this.dstIp = dstIp;
    }

    public String getDstPortName() {
        return dstPortName;
    }

    public void setDstPortName(String dstPortName) {
        this.dstPortName = dstPortName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getProbeCount() {
        return probeCount;
    }

    public void setProbeCount(Integer probeCount) {
        this.probeCount = probeCount;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getTos() {
        return tos;
    }

    public void setTos(Integer tos) {
        this.tos = tos;
    }

    public String getIpsecConnectionId() {
        return ipsecConnectionId;
    }

    public void setIpsecConnectionId(String ipsecConnectionId) {
        this.ipsecConnectionId = ipsecConnectionId;
    }

    public String getNqaState() {
        return nqaState;
    }

    public void setNqaState(String nqaState) {
        this.nqaState = nqaState;
    }
    /**
     * Override equals Function.<br>
     *
     * @param obj other Object
     * @return true if this object equals to other object
     * @since SDNO 0.5
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (super.getClass() != obj.getClass()) {
            return false;
        }

        NQA other = (NQA) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }
}
