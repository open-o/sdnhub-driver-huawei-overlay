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
 *
 * Model class for Redirect.<br/>
 *
 * @author
 * @version     SDNHUB 0.5  17-Feb-2017
 */
public class Redirect {

    private String nextHopIp;

    private String nqaId;

    private String ipRoute;

    private String mask;

    public Redirect() {
        // no argument constructor
    }


    public String getNextHopIp() {
        return nextHopIp;
    }


    public void setNextHopIp(String nextHopIp) {
        this.nextHopIp = nextHopIp;
    }


    public String getNqaId() {
        return nqaId;
    }


    public void setNqaId(String nqaId) {
        this.nqaId = nqaId;
    }


    public String getIpRoute() {
        return ipRoute;
    }


    public void setIpRoute(String ipRoute) {
        this.ipRoute = ipRoute;
    }


    public String getMask() {
        return mask;
    }


    public void setMask(String mask) {
        this.mask = mask;
    }
}
