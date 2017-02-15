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

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * Model class fro AcSNat.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class AcSNat {

    @AUuid(require = true)
    @JsonProperty(value = "id")
    private String id;

    @AUuid(require = true)
    @JsonProperty(value = "aclId")
    private String aclId;

    @AString(require = true, min = 1, max = 128)
    @JsonProperty(value = "interfaceName")
    private String interfaceName;

    @AString(require = false, min = 1, max = 128)
    @JsonProperty(value = "qosPreNat")
    private String qosPreNat;

    @JsonProperty(value = "aclNumber")
    private Integer aclNumber;

    public Integer getAclNumber() {
        return aclNumber;
    }

    public void setAclNumber(Integer aclNumber) {
        this.aclNumber = aclNumber;
    }

    public String getAclId() {
        return aclId;
    }

    public void setAclId(String aclId) {
        this.aclId = aclId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getQosPreNat() {
        return qosPreNat;
    }

    public void setQosPreNat(String qosPreNat) {
        this.qosPreNat = qosPreNat;
    }

    @Override
    public String toString() {
        return "AcSNat [id=" + id + ", aclId=" + aclId + ", interfaceName=" + interfaceName + ", qosPreNat=" + qosPreNat
                + ", aclNumber=" + aclNumber + "]";
    }

}
