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

import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;

/**
 * Model class for FilterActionList.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class FilterActionList extends UuidModel {

    private org.openo.sdnhub.overlayvpndriver.service.model.Action action;

    private List<AclRule> ruleList;

    private Filter filter;

    public org.openo.sdnhub.overlayvpndriver.service.model.Action getAction() {
        return action;
    }

    public void setAction(org.openo.sdnhub.overlayvpndriver.service.model.Action action) {
        this.action = action;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public List<AclRule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<AclRule> ruleList) {
        this.ruleList = ruleList;
    }

    @Override
    public String toString() {
        return "ClassPojo [action = " + action + ", filter = " + filter + "]";
    }
    /**
     * overriding super class equals method
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

        FilterActionList other = (FilterActionList) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }
}
