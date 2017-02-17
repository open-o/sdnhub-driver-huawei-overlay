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

/**
 * Model class for Option of DHCP Server.<br>
 *
 * @author
 * @version SDNHUB 0.5 February 14, 2017
 */
public class DhcpServerOption {

    /**
     * DHCP Option Code, range 1-254, but 1,3,6,15,44,46,50,51,52,53,54,55,57,58,59,61,82,121,184
     * are excluded
     */
    private int code;

    /**
     * Type:String,Integer,IpAddress
     */
    private String type;

    /**
     * value
     */
    private String value;

    /**
     * @return Returns the code.
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

}
