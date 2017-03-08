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

package org.openo.sdnhub.overlayvpndriver.service.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

public class SbiNeStaticRoute extends SbiBaseNetModel {

    @AString(require = true, min = 0, max = 255)
    private String destIp;

    @AString(require = true, scope = "true,false")
    private String enableDhcp;

    @AString(min = 0, max = 255)
    private String nextHop;

    @AString(min = 0, max = 255)
    private String outInterface;

    @AInt(min = 1, max = 255)
    private String priority = "60";

    private String nqa;

    private Ip destIpData;

    private Ip nextHopData;

    public SbiNeStaticRoute(){
        super();
    }

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }

    public String getEnableDhcp() {
        return enableDhcp;
    }

    public void setEnableDhcp(String enableDhcp) {
        this.enableDhcp = enableDhcp;
    }

    public String getNextHop() {
        return nextHop;
    }

    public void setNextHop(String nextHop) {
        this.nextHop = nextHop;
    }

    public String getOutInterface() {
        return outInterface;
    }

    public void setOutInterface(String outInterface) {
        this.outInterface = outInterface;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getNqa() {
        return nqa;
    }

    public void setNqa(String nqa) {
        this.nqa = nqa;
    }

    public Ip getDestIpData() {
        return destIpData;
    }

    public void setDestIpData(Ip destIpData) {
        this.destIpData = destIpData;
    }

    public Ip getNextHopData() {
        return nextHopData;
    }

    public void setNextHopData(Ip nextHopData) {
        this.nextHopData = nextHopData;
    }

}
