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

import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Model class for AclRule.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */

public class AclRule extends UuidModel {

    @AString(require = true, scope = "deny,permit")
    private String policy;

    private Ip srcIp;

    private Ip desIp;

    public AclRule()
    {
        super();
    }


    public String getPolicy() {
        return policy;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (policy != null ? policy.hashCode() : 0);
        result = 31 * result + (srcIp != null ? srcIp.hashCode() : 0);
        result = 31 * result + (desIp != null ? desIp.hashCode() : 0);
        return result;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }


    public Ip getSrcIp() {
        return srcIp;
    }


    public void setSrcIp(Ip srcIp) {
        this.srcIp = srcIp;
    }


    public Ip getDesIp() {
        return desIp;
    }


    public void setDesIp(Ip desIp) {
        this.desIp = desIp;
    }
    /**
     * overriding super class equals method
     */
    @Override
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

        AclRule other = (AclRule) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }

}
