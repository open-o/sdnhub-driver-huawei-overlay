/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

import org.openo.sdno.overlayvpn.verify.annotation.AIp;

/**
 * Class of StaticBinding IpAddress.<br>
 *
 * @author
 * @version SDNHUB 0.5 February 14, 2017
 */
public class StaticBinding {

    /**
     * Static IpAddress
     */
    @AIp
    private String staticIpAddress;

    /**
     * Static MacAddress
     */
    private String staticMacAddress;

    /**
     * @return Returns the staticIpAddress.
     */
    public String getStaticIpAddress() {
        return staticIpAddress;
    }

    /**
     * @param staticIpAddress The staticIpAddress to set.
     */
    public void setStaticIpAddress(String staticIpAddress) {
        this.staticIpAddress = staticIpAddress;
    }

    /**
     * @return Returns the staticMacAddress.
     */
    public String getStaticMacAddress() {
        return staticMacAddress;
    }

    /**
     * @param staticMacAddress The staticMacAddress to set.
     */
    public void setStaticMacAddress(String staticMacAddress) {
        this.staticMacAddress = staticMacAddress;
    }

}
