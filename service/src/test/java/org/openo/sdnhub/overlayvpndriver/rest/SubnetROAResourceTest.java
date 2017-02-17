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

package org.openo.sdnhub.overlayvpndriver.rest;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetNetModel;
import org.openo.sdno.overlayvpn.result.ResultRsp;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class SubnetROAResourceTest {

    SubnetROAResource subnetROAResource = new SubnetROAResource();

    @Before
    public void setup() throws Exception {
        SubnetServiceImpl service = new SubnetServiceImpl();
        Class<?> clazz = SubnetROAResource.class;

        Object cc = clazz.newInstance();

        Field f1 = cc.getClass().getDeclaredField("subnetService");
        f1.setAccessible(true);
        f1.set(cc, service);
        subnetROAResource = (SubnetROAResource)cc;
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetInvalidData() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnet.setEnableDhcp("true");
        subnet.setAdminStatus("none");
        ResultRsp<SbiSubnetNetModel> r = subnetROAResource.createSubnet(null, "123", "extSysID=ctrlid1024", subnet);
        assertEquals((r.getErrorCode().equals("0")), true);
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.createSubnet(null, "123", "", subnet);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSubnetEmptyDEviceIdException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.updateSubnet(null, "123", "", subnet);
    }

    @Test(expected = ServiceException.class)
    public void testGetSubnetException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.getSubnet(null, "123", "456", "");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSubnetException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.deleteSubnet(null, "123", "456", "");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetNullDeviceIdException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.createSubnet(null, "123", null, subnet);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSubnetNullDeviceIdException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.updateSubnet(null, "123", null, subnet);
    }

    @Test(expected = ServiceException.class)
    public void testGetSubnetNullUuidException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.getSubnet(null, "123", "456", null);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSubnetNullUuidException() throws ServiceException {
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
        subnet.setIpv6Address("10.12.13.11.14.15");
        subnet.setPrefixLength("12");
        subnet.setNetworkId("456");
        subnetROAResource.deleteSubnet(null, "123", "456", null);
    }
}
