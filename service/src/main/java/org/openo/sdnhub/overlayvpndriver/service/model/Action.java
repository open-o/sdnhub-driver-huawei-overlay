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

import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class Action {

    @AString(require = true, scope = "deny, permit")
    private String policy;

    private Ip nextHopIp;

    private String outInterFace;

    public Action() {
        super();
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Ip getNextHopIp() {
        return nextHopIp;
    }

    public void setNextHopIp(Ip nextHopIp) {
        this.nextHopIp = nextHopIp;
    }

    public String getOutInterFace() {
        return outInterFace;
    }

    public void setOutInterFace(String outInterFace) {
        this.outInterFace = outInterFace;
    }

}
