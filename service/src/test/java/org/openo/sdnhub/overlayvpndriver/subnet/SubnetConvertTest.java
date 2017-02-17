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
package org.openo.sdnhub.overlayvpndriver.subnet;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACNetwork;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetNetModel;

import static org.junit.Assert.assertEquals;

public class SubnetConvertTest {

    @Test
    public void testBuildAcNetwork() {
        SbiSubnetNetModel subnet = new SbiSubnetNetModel();
        subnet.setNeId("1234");
        subnet.setControllerId("123546");
        subnet.setServiceSubnetId("15426");
        subnet.setCidrIpAddress("10.10.21.13");
        subnet.setCidrMask("123");
        subnet.setUseMode("terminal");
        subnet.setDhcpMode("server");
        subnet.setDnsMode("custom");
        subnet.setVlanId(null);
        subnet.setVni("54321");
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        ACNetwork r = org.openo.sdnhub.overlayvpndriver.translator.SubnetConvert.buildAcNetwork(subnet);
        assertEquals("456",r.getId());
    }
    @Test
    public void testBuildAcNetwork1() {
        SbiSubnetNetModel subnet = new SbiSubnetNetModel();
        subnet.setNeId("1234");
        subnet.setControllerId("123546");
        subnet.setServiceSubnetId("15426");
        subnet.setCidrIpAddress("10.10.21.13");
        subnet.setCidrMask("123");
        subnet.setUseMode("terminal");
        subnet.setDhcpMode("server");
        subnet.setDnsMode("custom");
        subnet.setVlanId("12345");
        subnet.setVni(null);
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        ACNetwork r = org.openo.sdnhub.overlayvpndriver.translator.SubnetConvert.buildAcNetwork(subnet);
        assertEquals("456",r.getId());
    }
    @Test
    public void testBuildAcNetwork2() {
        SbiSubnetNetModel subnet = new SbiSubnetNetModel();
        subnet.setNeId("1234");
        subnet.setControllerId("123546");
        subnet.setServiceSubnetId("15426");
        subnet.setCidrIpAddress("10.10.21.13");
        subnet.setCidrMask("123");
        subnet.setUseMode("terminal");
        subnet.setDhcpMode("server");
        subnet.setDnsMode("custom");
        subnet.setVlanId("12345");
        subnet.setVni("54321");
        subnet.setIpv6Address(null);
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        ACNetwork r = org.openo.sdnhub.overlayvpndriver.translator.SubnetConvert.buildAcNetwork(subnet);
        assertEquals("456",r.getId());
    }

    @Test(expected = ServiceException.class)
    public void testBuildUpdateAcNetwork() throws ServiceException {
        SbiSubnetNetModel subnet = new SbiSubnetNetModel();
        SbiSubnetNetModel prevNetwork = new SbiSubnetNetModel();
        prevNetwork.setNetworkId(null);
        SbiSubnetNetModel r = org.openo.sdnhub.overlayvpndriver.translator.SubnetConvert.buildUpdateAcNetwork(subnet, prevNetwork);
        assertEquals(null,r.getNetworkId());
    }
    @Test(expected = ServiceException.class)
    public void testBuildUpdateAcNetwork1() throws ServiceException {
        SbiSubnetNetModel subnet = new SbiSubnetNetModel();
        subnet.setNetworkId("12");
        SbiSubnetNetModel r = org.openo.sdnhub.overlayvpndriver.translator.SubnetConvert.buildUpdateAcNetwork(subnet, null);
        assertEquals("12",r.getNetworkId());
    }
}
