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

package org.openo.sdnhub.overlayvpndriver.common.consts;

/**
 * The class of IKE version. <br>
 *
 * @author
 * @version SDNHUB 0.5 Feb-14-2017
 */
public enum IKEVersion {
    V1(0), V2(1);

    private int value = 0;

    /**
     * Constructor<br>
     *
     * @since SDNHUB 0.5
     * @param value The IKE version
     */
    IKEVersion(int value) {
        this.value = value;
    }

    /**
     * It is used to get IKE version name. <br>
     *
     * @return The IKE version name.
     * @since SDNHUB 0.5
     */
    public String getName() {
        switch(value) {
            case 0:
                return "v1";
            case 1:
                return "v2";
            default:
                return "";
        }
    }

    /**
     * It is used to check the IKE version is valid or not. <br>
     *
     * @param name The IKE version name
     * @return true if the IKE version name is valid.
     * @since SDNHUB 0.5
     */
    public static boolean validateName(String name) {
        for(IKEVersion ikeVersion : IKEVersion.values()) {
            if(ikeVersion.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
