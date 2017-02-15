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
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

import java.util.List;

/**
 * Model class for AcAcl.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class AcAcl {

    private String id;

    @AUuid
    @AString(require = true)
    private String aclId;

    private String netId;

    private Integer aclNumber;

    @AString(require = true)
    private String aclName;

    private Integer aclStep;

    private List<AcAclRule> ruleList = null;

    public String getAclId() {
        return aclId;
    }

    public void setAclId(String aclId) {
        this.aclId = aclId;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAclNumber() {
        return aclNumber;
    }

    public void setAclNumber(Integer aclNumber) {
        this.aclNumber = aclNumber;
    }

    public String getAclName() {
        return aclName;
    }

    public void setAclName(String aclName) {
        this.aclName = aclName;
    }

    public List<AcAclRule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<AcAclRule> ruleList) {
        this.ruleList = ruleList;
    }

    public Integer getAclStep() {
        return aclStep;
    }

    public void setAclStep(Integer aclStep) {
        this.aclStep = aclStep;
    }

}
