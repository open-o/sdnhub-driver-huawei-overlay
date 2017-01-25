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

/**
 * Model class for rule in adapter layer. <br>
 * 
 * @author
 * @version SDNO 0.5 Jul 18, 2016
 */
public class NetRule {

    private String policy;

    private String srcIp;

    private String srcNetMask;

    private String desIp;

    private String desNetMask;

    /**
     * Constructor<br>
     * 
     * @since SDNHUB 0.5
     */
    public NetRule() {
        super();
    }

    /**
     * Constructor<br>
     * 
     * @param policy The policy
     * @param srcIp The source IP
     * @param srcNetMask The source IP netmask
     * @param desIp The destination IP
     * @param desNetMask The destination IP netmask
     * @since SDNHUB 0.5
     */
    public NetRule(String policy, String srcIp, String srcNetMask, String desIp, String desNetMask) {
        super();
        this.setPolicy(policy);
        this.setSrcIp(srcIp);
        this.setSrcNetMask(srcNetMask);
        this.setDesIp(desIp);
        this.setDesNetMask(desNetMask);
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getSrcNetMask() {
        return srcNetMask;
    }

    public void setSrcNetMask(String srcNetMask) {
        this.srcNetMask = srcNetMask;
    }

    public String getDesIp() {
        return desIp;
    }

    public void setDesIp(String desIp) {
        this.desIp = desIp;
    }

    public String getDesNetMask() {
        return desNetMask;
    }

    public void setDesNetMask(String desNetMask) {
        this.desNetMask = desNetMask;
    }
}
