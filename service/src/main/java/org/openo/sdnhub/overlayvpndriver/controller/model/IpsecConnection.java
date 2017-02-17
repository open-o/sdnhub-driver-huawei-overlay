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
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class IpsecConnection {

    private String nqaState;

    private IpSec ipsec;

    private RuleList[] ruleList;

    private Ike ike;

    private String type;

    private String nqaId;

    private String seqNumber;

    private String qosPreClassify;

    public String getNqaState() {
        return nqaState;
    }

    public void setNqaState(String nqaState) {
        this.nqaState = nqaState;
    }

    public IpSec getIpsec() {
        return ipsec;
    }

    public void setIpsec(IpSec ipsec) {
        this.ipsec = ipsec;
    }

    public RuleList[] getRuleList() {
        return ruleList;
    }

    public void setRuleList(RuleList[] ruleList) {
        this.ruleList = ruleList;
    }

    public Ike getIke() {
        return ike;
    }

    public void setIke(Ike ike) {
        this.ike = ike;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNqaId() {
        return nqaId;
    }

    public void setNqaId(String nqaId) {
        this.nqaId = nqaId;
    }

    public String getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(String seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getQosPreClassify() {
        return qosPreClassify;
    }

    public void setQosPreClassify(String qosPreClassify) {
        this.qosPreClassify = qosPreClassify;
    }

    @Override
    public String toString() {
        return "ClassPojo [nqaState = " + nqaState + ", ipsec = " + ipsec + ", ruleList = " + ruleList + ", ike = "
                + ike + ", type = " + type + ", nqaId = " + nqaId + ", seqNumber = " + seqNumber + ", qosPreClassify = "
                + qosPreClassify + "]";
    }
}
