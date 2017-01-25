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

package org.openo.sdnhub.overlayvpndriver.util.config;

/**
 * Class of Device Type.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-11
 */
public enum DeviceType {
    THINCPE(0), VCPE(1);

    private int value;

    /**
     * Constructor<br>
     * 
     * @since SDNHUB 0.5
     * @param value device type
     */
    DeviceType(int value) {
        this.value = value;
    }

    /**
     * Get name of device type.<br>
     * 
     * @return name of device type
     * @since SDNHUB 0.5
     */
    public String getName() {
        switch(value) {
            case 0:
                return "ThinCPE";
            case 1:
                return "vCPE";
            default:
                return "";
        }
    }
}
