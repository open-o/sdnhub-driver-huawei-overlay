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

package org.openo.sdnhub.overlayvpndriver.controller.consts;

/**
 * 
 * <br/>
 * <p>
 * </p>
 * 
 * @author
 * @version     SDNHUB 0.5  02-Feb-2017
 */
public class ControllerUrlConst {
 
	public static final String QUERY_DEVICES_URL = "/controller/cloud/v2/northbound/devices/list";
	
	public static final String CREATE_DEVICES_URL = "/controller/cloud/v2/northbound/devices";
	
	public static final String MODIFY_DEVICES_URL = "/controller/cloud/v2/northbound/devices/{0}";
	
	public static final String DELETE_DEVICES_URL = "/controller/cloud/v2/northbound/devices";
	
	public static final String REBOOT_DEVICES_URL = "/controller/cloud/v2/northbound/devices/{0}/reboot?parameter={1}";
	
	public static final String REPLACE_DEVICES_URL = "/controller/cloud/v2/northbound/devices/replace/{0}";
	
	public static final String QUERY_DHCPADDRESSINFO_URL = "/controller/cloud/v2/northbound/devices/{0}/ipamdhcp";
	
	public static final String QUERY_NETWORK_BY_DEVICEID_URL = "/controller/cloud/v2/northbound/config/{0}/device/arnetwork";
	
	public static final String CREATE_NETWORK_BY_DEVICEID_URL = "/controller/cloud/v2/northbound/config/{0}/device/arnetwork";
	
	public static final String DELETE_NETWORK_BY_DEVICEID_URL = "/controller/cloud/v2/northbound/config/{0}/device/arnetwork";
	
	public static final String QUERY_DEVICE_PORT_URL = "/controller/cloud/v2/northbound/devices/{0}/port";
	
	public static final String QUERY_WAN_SUBINTERFACE_URL = "/controller/cloud/v2/northbound/devices/{0}/arwansubinterface";
	
	public static final String DHCP_ENABLE_URL = "/controller/cloud/v2/northbound/config/{0}/device/arinterfaces";
	
	public static final String CONST_CONFIG_STATICROUTE = "/controller/cloud/v2/northbound/config/{0}/device/staticroutes";
	
	public static final String EXTERNAL_REDIRECT_URL = "/controller/plateform/ui/userSecurity/accounts/current";
	
	public static final String CONST_CONFIG_QOS_MQC = "/controller/cloud/v2/northbound/config/{0}/device/arqos";
	
	public static final String CONST_CONFIG_QOS_CAR = "/controller/cloud/v2/northbound/config/{0}/device/arqoscar";
	
	public static final String CREATE_DEVICEGROUP_URL="/controller/cloud/v2/northbound/deviceGroups";
	
	public static final String DELETE_DEVICEGROUP_URL = "/controller/cloud/v2/northbound/deviceGroups";
	
	public static final String ADD_DEVICE_INTO_DEVICEGROUP_URL = "/controller/cloud/v2/northbound/deviceGroups/{0}/devices";
	
	public static final String QUERY_DEVICEGROUP_BY_ID_URL = "/controller/cloud/v2/northbound/deviceGroups/{0}";
	
	public static final String QUERY_DEVICE_BY_PARAM_URL = "/controller/cloud/v2/northbound/deviceGroups/list";
	
	public static final String QUERY_DEVICES_LOG_URL = "/controller/cloud/v2/northbound/devices/systemLog/query?.pageIndex={0}&.pageSize={1}";
	
	public static final String CONFIG_L2FILTERMQC_BY_ID_URL = "/controller/cloud/v2/northbound/config/{0}/device/arqosmqc";
	
	public static final String DELETE_L2FILTERMQC_BY_ID_URL = "/controller/cloud/v2/northbound/config/{0}/device/arqosmqc";
	
	public static final String CREATE_POLICYROUTE_URL = "/controller/cloud/v2/northbound/config/{0}device/ploicyroute";
	
	public static final String DELETE_POLICYROUTE_URL = "/controller/cloud/v2/northbound/config/{0}device/ploicyroute";
	
	public static final String UPDATE_POLICYROUTE_URL = "/controller/cloud/v2/northbound/config/{0}device/ploicyroute";
	
	public static final String QUERY_POLICYROUTE_URL = "/controller/cloud/v2/northbound/config/{0}device/ploicyroute";
	
	public static final String SUBINTERFACE_URL = "/controller/cloud/v2/northbound/config/{0}/device/subinterface";
	
	public static final String ETH_CONFIG_URL = "/controller/cloud/v2/northbound/config/{0}/device/ethinterfaces";
	
	public static final String INTERFACE_IP_CONTROLLER_URL = "/controller/cloud/v2/northbound/config/{0}/device/interfacesip";
	
	public static final String SUBNET_BDINFO_CONTROLLER_URL = "/controller/cloud/v2/northbound/config/{0}/device/bdinfo?vni={1}";
	
	public static final String QUERY_LOOP_BACK_URL = "/controller/cloud/v2/northbound/config/{0}/device/loopbackinterface";
	
	/**
     * URL for IpSec
     */
    public static final String CONST_CONFIG_IPSEC = "/controller/cloud/v2/northbound/config/{0}/device/vpnsipsec";

    /**
     * URL for VxLan
     */
    public static final String CONST_CONFIG_VXLAN = "/controller/cloud/v2/northbound/config/{0}/device/vpnsvxlan";

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
     * URL for OverlayVpnDriver redirect
     */
    public static final String EXTERNAL_REDIRECT = "/controller/platform/ui/userSecurity/accounts/current";

	public static final String CONFIG_QOS_MQC = "/controller/cloud/v2/northbound/config/{0}/device/qosmqc";

	public static final String CONST_QUERY_VXLAN_STATUS = "/controller/cloud/v2/northbound/devices/{0}/vxlantunnelstate";
	
	public static final String   QUERY_IPSEC_STATUS="/controller/cloud/v2/northbound/devices/{0}/ipsecstate";
	public static final String NQA_CONFIG_URL = "/controller/cloud/v2/northbound/config/{0}/device/nqa";
	
	public static final String ACL_URL = "/controller/cloud/v2/northbound/config/{0}/device/acl";
	
	public static final String DEVICE_NAT_URL = "/controller/cloud/v2/northbound/config/{0}/device/nat";
	
	public static final String DEVICE_NETWORK_URL = "/controller/cloud/v2/northbound/config/{0}/device/network";

}
