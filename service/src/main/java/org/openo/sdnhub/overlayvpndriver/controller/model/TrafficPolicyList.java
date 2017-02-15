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

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class TrafficPolicyList {

    private String uuid;

    private List<FilterActionList> filterActionList;

    private List<InterfaceList> interfaceList;

    private String trafficpolicyName;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<FilterActionList> getFilterActionList() {
        return filterActionList;
    }

    public void setFilterActionList(List<FilterActionList> filterActionList) {
        this.filterActionList = filterActionList;
    }

    public List<InterfaceList> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<InterfaceList> interfaceList) {
        this.interfaceList = interfaceList;
    }

    public String getTrafficpolicyName() {
        return trafficpolicyName;
    }

    public void setTrafficpolicyName(String trafficpolicyName) {
        this.trafficpolicyName = trafficpolicyName;
    }

    @Override
    public String toString() {
        return "ClassPojo [filterActionList = " + filterActionList + ", interfaceList = " + interfaceList
                + ", trafficpolicyName = " + trafficpolicyName + "]";
    }
}
