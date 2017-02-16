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

package org.openo.sdnhub.overlayvpndriver.common.consts;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class DriverErrorCode {

    public static final String SUCCESS = "0";

    public static final String ADAPTER_VXLAN_PLUGIN_WORKING_ERROR = "ccadapter.vxlan.plugin_working_error";

    public static final String ADAPTER_VXLAN_CTRL_RETURN_ERROR = "ccadapter.vxlan.controller_return_error";

    public static final String ADAPTER_VXLAN_COMM_CTRL_NOT_EXIST = "ccadapter.vxlan.controller_not_exist";

    public static final String ADAPTER_VXLAN_COMM_PORT_NOT_EXIST = "ccadapter.vxlan.port_not_exist";

    public static final String ADAPTER_VXLAN_COMM_TYPR_ERROT = "ccadapter.vxlan.protocol_type_error";

    public static final String ADAPTER_VXLAN_COMM_PARAM_NOT_EXIST = "ccadapter.vxlan.parameter_not_exist";

    public static final String ADAPTER_VXLAN_COMM_FAILED = "ccadapter.vxlan.communication_failed";

    public static final String ADAPTER_VXLAN_COMM_TIMEOUT = "ccadapter.vxlan.communication_timeout";

    public static final String ADAPTER_COMM_FAILED = "ccadapter.communication_failed";

    public static final String ADAPTER_SUCCESS = "cloudvpn.adapter.sucess";

    public static final String ADAPTER_PART_SUCCESS = "cloudvpn.adapter.part_success";

    public static final String ADAPTER_FAILED = "cloudvpn.adapter.failed";

    public static final String ADAPTER_PARAMETER_INVALID = "cloudvpn.adapter.parameter_invalid";

    public static final String CLOUDVPN_DATABASE_INSERT_FAILED = "cloudvpn.database.insert_fail";

    public static final String CLOUDVPN_DATABASE_DELETE_FAILED = "cloudvpn.database.delete_fail";

    public static final String CLOUDVPN_DATABASE_UPDATET_FAILED = "cloudvpn.database.update_fail";

    public static final String CLOUDVPN_DATABASE_QUERYT_FAILED = "cloudvpn.database.query_fail";

    public static final String CLOUDVPN_SUCCESS = "success";

    public static final String ADAPTER_GRE_CREATE_IPSEC_ERROR = "ipsec.creat.errror";

    public static final String CLOUDVPN_PARAMETER_INVALID = "cloudvpn.parameter.invalid";

    public static final String CLOUDVPN_FAILED = "cloudvpn.failed";

    public static final String ADAPTER_QOS_CREATE_ERROR = null;

    public static final String ADAPTER_QOS_DELETE_ERROR = null;

    public static final String ADAPTER_GRE_QUERY_ROUTE_ERROR = null;

    public static final String ADAPTER_GRE_QUERY_WAN_INTERFACE_ERROR = null;

    public static final String ADAPTER_GRE_QUEY_QUERY_WAN_INTERFACE_ERROR = null;

    public static final String ADAPTER_IPSEC_CTRL_RETURN_ERROR = null;

    public static final String ADAPTER_VXLAN_COMM_TYPE_ERROR = "ccadapter.vxlan.protocol_type_error";

    public static final String ADAPTER_ACRESPONSE_PARSE_FAILED = "acadapter.acresponse.parse.failed";

    public static final String ADAPTER_ENABLE_WANSUBINTERFACE_DHCP_FAILED =
            "acadapter.enable.wansubinterface.dhcp.failed";

    public static final String CLOUDVPN_DATABASE_UPDATE_FAILED = "cloudvpn.database.update_failed";

    public static final String CLOUDVPN_DATABASE_QUERY_FAILED = "cloudvpn.database.query_failed";

    public static final String SUBNET_SUBNET_SAME = "cloudvpn.subnet.same";

    public static final String SUBNET_VNI_ALREADY_EXIST = "cloudvpn.vni.allready.exist";

    public static final String ADAPTER_VXLAN_CREATE_ERROR = "ADAPTER_VXLAN_CREATE_ERROR";

    public static final String ADAPTER_SITE_SUBNET_CREATE_ERROR = "adapter.site.subnet.create.error";

    public static final String ADAPTER_SITE_SUBNET_CREATE_TUNNEL_ERROR = "adapter.site.subnet.create.tunnel.error";

    public static final String ADAPTER_SITE_SUBNET_UPDATE_TUNNEL_ERROR = "adapter.site.subnet.update.tunnel.error";

    public static final String ADAPTER_SITE_SUBNET_UPDATE_ERROR = "adapter.site.subnet.update.error";

    public static final String ADAPTER_SITE_SUBNET_DELETE_ERROR = "adapter.site.subnet.delete.error";

    public static final String ADAPTER_SITE_SUBNET_DELETE_TUNNEL_ERROR = "adapter.site.subnet.delete.tunnel.error";

    public static final String ADAPTER_SITE_SUBNET_QUERY_ERROR = "adapter.site.subnet.query.error";

    public static final String ADAPTER_SITE_SUBNET_NOT_EXIST_ON_CONTROLLER = "no.subnet.on.controller";

    public static final String ADAPTER_SITE_SNAT_DELETE_ERROR = "adapter.site.snat.delete.error";
    public static final String ADAPTER_SITE_SNAT_DELETE_TUNNEL_ERROR = "adapter.site.snat.delete.tunnel.error";
    public static final String ADAPTER_SITE_SNAT_CREATE_ERROR = "adapter.site.snat.create.error";
    public static final String ADAPTER_SITE_SNAT_TUNNEL_ERROR = "adapter.site.snat.tunnel.error";
    public static final String ADAPTER_SITE_SNAT_UPDATE_ERROR = "adapter.site.snat.update.error";
    public static final String ADAPTER_SITE_SNAT_UPDATE_TUNNEL_ERROR = "adapter.site.snat.update.tunnel.error";

    private DriverErrorCode() {
    }

    public static boolean isSucess(String code) {
        return SUCCESS.equals(code);
    }
};
