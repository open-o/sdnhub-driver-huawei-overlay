/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.acbranchservice.util.consts;

/**
 * Adapter Service URL Definition Class. <br/>
 * <p>
 * Constants class with the URLs used by the REST services
 * </p>
 * 
 * @author
 * @version SDNO 0.5 Jul 18, 2016
 */
public class ControllerUrlConst {

    /**
     * URL for IpSec
     */
    public static final String CONST_CONFIG_IPSEC = "/controller/cloud/v2/northbound/config/{0}/device/arvpnsipsec";

    /**
     * URL for VxLan
     */
    public static final String CONST_CONFIG_VXLAN = "/controller/cloud/v2/northbound/config/{0}/device/arvpnsvxlan";

    /**
     * URL for query wan subinterface
     */
    public static final String QUERY_WAN_SUBINTERFACE = "/controller/cloud/v2/northbound/devices/{0}/arwansubinterface";

    /**
     * URL for enable wan subinterface
     */
    public static final String ENABLE_WAN_SUBINTERFACE =
            "/controller/cloud/v2/northbound/config/{0}/device/arinterfaces";

    /**
     * URL for query port
     */
    public static final String QUERY_DEVICE_PORT = "/controller/cloud/v2/northbound/devices/{0}/port";

    /**
     * URL for ACBranch redirect
     */
    public static final String EXTERNAL_REDIRECT = "/controller/platform/ui/userSecurity/accounts/current";

    /**
     * Private constructor added to fix sonar issue
     */
    private ControllerUrlConst() {
        // Default private constructor
    }

}
