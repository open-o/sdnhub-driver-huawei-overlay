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
public class RuleList {

    private String desIp;

    private String desNetMask;

    private String srcNetMask;

    private String policy;

    private String srcIp;

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

    public String getSrcNetMask() {
        return srcNetMask;
    }

    public void setSrcNetMask(String srcNetMask) {
        this.srcNetMask = srcNetMask;
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

    @Override
    public String toString() {
        return "ClassPojo [desIp = " + desIp + ", desNetMask = " + desNetMask + ", srcNetMask = " + srcNetMask
                + ", policy = " + policy + ", srcIp = " + srcIp + "]";
    }
}
