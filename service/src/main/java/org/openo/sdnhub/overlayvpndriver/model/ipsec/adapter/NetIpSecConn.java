/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model class for connection in adapter layer. <br>
 * 
 * @author
 * @version SDNO 0.5 Jul 18, 2016
 */
public class NetIpSecConn {

    @JsonIgnore
    private String ipSecConnectionId;

    private int seqNumber = 0;

    private boolean deleteMode = false;

    private String type;

    private List<NetRule> ruleList;

    private String routeInject;

    @JsonProperty(value = "ipsec")
    private NetIpSec ipSec;

    private NetIke ike;

    /**
     * Constructor<br>
     * 
     * @since SDNHUB 0.5
     */
    public NetIpSecConn() {
        super();
    }

    public String getIpSecConnectionId() {
        return ipSecConnectionId;
    }

    public void setIpSecConnectionId(String ipSecConnectionId) {
        this.ipSecConnectionId = ipSecConnectionId;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NetRule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<NetRule> ruleList) {
        this.ruleList = ruleList;
    }

    public String getRouteInject() {
        return routeInject;
    }

    public void setRouteInject(String routeInject) {
        this.routeInject = routeInject;
    }

    public NetIpSec getIpSec() {
        return ipSec;
    }

    public void setIpSec(NetIpSec ipSec) {
        this.ipSec = ipSec;
    }

    public NetIke getIke() {
        return ike;
    }

    public void setIke(NetIke ike) {
        this.ike = ike;
    }
}
