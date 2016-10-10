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

package org.openo.sdno.overlayvpndriver.sbi.wan;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;

/**
 * Data model of WanSubInf Configuration Data.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-9
 */
public class WanSubInfConfigData {

    @JsonProperty("devicetype")
    private String deviceType;

    @JsonProperty("ip")
    private String ipAddress;

    @JsonProperty("wansubinf")
    private List<WanSubInterface> wanSubInf;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<WanSubInterface> getWanSubInf() {
        return wanSubInf;
    }

    public void setWanSubInf(List<WanSubInterface> wanSubInf) {
        this.wanSubInf = wanSubInf;
    }
}
