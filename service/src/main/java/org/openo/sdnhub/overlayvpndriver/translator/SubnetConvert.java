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

package org.openo.sdnhub.overlayvpndriver.translator;

import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACDhcp6Config;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACNetwork;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACSubnet;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.*;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility converter class for subnet model.<br/>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class SubnetConvert {

    private static final String ADAPTER_SITE_SUBNET_PARAM_ERROR = "adapter.site.subnet.param.error";

    private static final Logger LOGGER = LoggerFactory.getLogger(SubnetConvert.class);

    private SubnetConvert() {
    }

    /**
     * Convert SbiSubnetNetModel to ACNetwork model.<br/>
     *
     * @param subnet SbiSubnetNet Model
     * @return Translated ACNetwork model
     * @since SDNHUB 0.5
     */
    public static ACNetwork buildAcNetwork(SbiSubnetNetModel subnet) {
        ACNetwork network = new ACNetwork();

        network.setChangedMode(Boolean.parseBoolean(subnet.getChangedMode()));
        network.setDescription(subnet.getDescription());
        network.setId(subnet.getNetworkId());

        network.setName(subnet.getName());
        network.setSubnetList(new ArrayList<ACSubnet>());
        network.setUse(subnet.getUseMode());

        if(null != subnet.getVlanId()) {
            network.setVlanId(Long.valueOf(subnet.getVlanId()));
        }
        if(null != subnet.getVni()) {
            network.setVni(Integer.valueOf(subnet.getVni()));
        }
        if(isIpv6(subnet)) {
            network.setDhcp6Enable(Boolean.valueOf(subnet.getDhcp6Enable()));
            network.setIpv6Address(subnet.getIpv6Address());
            network.setPrefixLength(Integer.valueOf(subnet.getPrefixLength()));
            network.setDhcp6Config(buildDhcp6Config(subnet));
        } else {
            network.setDhcpEnable(Boolean.valueOf(subnet.getEnableDhcp()));
            network.setIpAddress(subnet.getCidrIpAddress());
            network.setMaskAddress(subnet.getCidrMask());
            if(network.isDhcpEnable()) {
                network.setDhcpConfig(buildDhcpConfig(subnet));
            }

        }
        return network;
    }

    private static ACDhcp6Config buildDhcp6Config(SbiSubnetNetModel subnet) {
        ACDhcp6Config dhcp6Config = new ACDhcp6Config();
        dhcp6Config.setDhcpMode(subnet.getDhcp6Mode());
        dhcp6Config.setDnsServer(subnet.getGatewayIp());

        return dhcp6Config;
    }

    private static boolean isIpv6(SbiSubnetNetModel subnet) {
        return subnet.getIpv6Address() != null;
    }

    private static DhcpConfig buildDhcpConfig(SbiSubnetNetModel subnet) {
        DhcpConfig dhcpConfig = new DhcpConfig();
        dhcpConfig.setDhcpMode(subnet.getDhcpMode());
        dhcpConfig.setDnsConfig(buildDnsConfig(subnet));
        dhcpConfig.setTimeConfig(buildTimeConfig(subnet));
        return dhcpConfig;
    }

    private static TimeConfig buildTimeConfig(SbiSubnetNetModel subnet) {
        TimeConfig timeConfig = new TimeConfig();
        timeConfig.setUnlimit(Boolean.parseBoolean(subnet.getUnlimit()));
        return timeConfig;
    }

    private static DnsServerConfig buildDnsConfig(SbiSubnetNetModel subnet) {
        DnsServerConfig dnsConfig = new DnsServerConfig();

        // dnsConfig.setDnsServerMode(subnet.getdnsMode());
        dnsConfig.setPriorDnsServer(subnet.getPriorDnsServer());
        dnsConfig.setStandbyDnsServer(subnet.getStandbyDnsServer());

        return dnsConfig;
    }

    /**
     * Update SbiSubnetNet model information.<br/>
     *
     * @param subnet information to update in previous model
     * @param ctrId Controller UUID
     * @param deviceId Device Id
     * @return Updated model from the information of subnet
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static ACNetwork buildUpdateAcNetwork(SbiSubnetNetModel subnet, String ctrId, String deviceId)
            throws ServiceException
    {
        if (null == subnet.getNetworkId())
        {
            LOGGER.error("update Subnet: param invalid.");
            throw new ServiceException(ADAPTER_SITE_SUBNET_PARAM_ERROR, "update Subnet: param invalid.");
        }
        ACNetwork network = getNetworkById(subnet.getNetworkId(), ctrId, deviceId);

        if (null == network)
        {
            LOGGER.error("update Subnet: network isn`t exist.");
            throw new ServiceException(ADAPTER_SITE_SUBNET_PARAM_ERROR, "update Subnet: network isn`t exist.");
        }

        network.setDescription(subnet.getDescription());
        network.setName(subnet.getName());

        return network;
    }

    public static ACNetwork getNetworkById(String networkId, String ctrlUuid, String deviceId)
            throws ServiceException
    {
        ResultRsp<List<ACNetwork>> result = SubnetServiceImpl.queryNetwork(ctrlUuid, deviceId);

        List<ACNetwork> acNetworks = result.getData();

        for (ACNetwork acNetwork : acNetworks)
        {
            if (acNetwork.getId().equals(networkId))
            {
                return acNetwork;
            }
        }
        return null;
    }
}
