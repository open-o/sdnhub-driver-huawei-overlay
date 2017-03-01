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

package org.openo.sdnhub.overlayvpndriver.config;

/**
 * The configuration key constants. <br>
 *
 * @author
 * @version SDNO 0.5 June 20, 2016
 */
public class ConfigKeyConst {

    public static final String WAN_DEFAULT_IP = "wan-default-ip";

    // RFC 1918 IP
    public static final String DENY_PRIVATE_3 = "deny-private-3";

    // RFC 1918 IP
    public static final String DENY_PRIVATE_2 = "deny-private-2";

    // RFC 1918 IP
    public static final String DENY_PRIVATE_1 = "deny-private-1";

    /**
     * Constructor<br>
     *
     * @since SDNHUB 0.5
     */
    private ConfigKeyConst() {

    }
}
