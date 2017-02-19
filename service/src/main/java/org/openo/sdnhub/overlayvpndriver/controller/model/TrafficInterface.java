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

import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 *
 * Model class for TrafficInterface.<br/>
 *
 * @author
 * @version     SDNO 0.5  17-Feb-2017
 */
public class TrafficInterface {

    @AString(require = true, min = 1, max = 255)
    private String interfaceName;

    @AString(require = true, scope = "inbound,outbound,all")
    private String direction;


    public String getInterfaceName() {
        return interfaceName;
    }


    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }


    public String getDirection() {
        return direction;
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }


}
