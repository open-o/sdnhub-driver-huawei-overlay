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

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACNetwork;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetNetModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import static org.junit.Assert.assertTrue;

public class SubnetServiceImplTest {

    org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetServiceImpl subnetServiceImpl =
            new org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetServiceImpl();

    @Test(expected = ServiceException.class)
    public void testCreateSubnet() throws ServiceException {
        ACNetwork network = new ACNetwork();
        network.setId(null);
        subnetServiceImpl.createSubnet(network, "extSysID=ctrlid1024", "1234");

    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetHttp() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
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
                obj.setData(subnet);
                obj.setErrcode("123");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
        obj.setErrcode("123");

        ACNetwork network = new ACNetwork();
        network.setId("12346");
        subnetServiceImpl.createSubnet(network, "extSysID=ctrlid1024", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetHttpQueryException() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
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
                obj.setData(subnet);
                obj.setErrcode("123");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
        obj.setErrcode("123");
        subnetServiceImpl.queryNetwork("extSysID=ctrlid1024", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetHttpDeleteException() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                ACResponse<String> obj = new ACResponse<>();
                obj.setErrcode("123");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ACResponse<String> obj = new ACResponse<>();
        obj.setErrcode("123");

        String network = "12346";
        subnetServiceImpl.deleteSubnet(network, "extSysID=ctrlid1024", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetHttpUpdate() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
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
                obj.setData(subnet);
                obj.setErrcode("123");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
        obj.setErrcode("123");

        SbiSubnetNetModel network = new SbiSubnetNetModel();
        network.setNeId("12346");
        subnetServiceImpl.updateSubnet(network, "extSysID=ctrlid1024", "1234");
        assertTrue(true);
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetHttp0304390019() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
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
                obj.setData(subnet);
                obj.setErrcode("0304390019");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
        obj.setErrcode("0304390019");

        ACNetwork network = new ACNetwork();
        network.setId("12346");
        subnetServiceImpl.createSubnet(network, "extSysID=ctrlid1024", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnetHttp0304390110() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
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
                obj.setData(subnet);
                obj.setErrcode("0304390110");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ACResponse<SbiSubnetNetModel> obj = new ACResponse<>();
        obj.setErrcode("0304390019");

        ACNetwork network = new ACNetwork();
        network.setId("12346");
        subnetServiceImpl.createSubnet(network, "extSysID=ctrlid1024", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet12() throws ServiceException {
        ACNetwork network = new ACNetwork();
        network.setId("123");
        subnetServiceImpl.createSubnet(network, "extSysID=ctrlid1024", "");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet23() throws ServiceException {
        ACNetwork network = new ACNetwork();
        network.setId("123");
        subnetServiceImpl.createSubnet(network, "", "1324");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet1() throws ServiceException {

        SbiSubnetNetModel sb = new SbiSubnetNetModel();
        sb.setNeId(null);
        subnetServiceImpl.updateSubnet(sb, "extSysID=ctrlid1024", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet2() throws ServiceException {

        subnetServiceImpl.queryNetwork("extSysID=ctrlid1024", "");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet3() throws ServiceException {
        subnetServiceImpl.deleteSubnet("123", "", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet16() throws ServiceException {

        SbiSubnetNetModel sb = new SbiSubnetNetModel();
        sb.setNeId("123");
        subnetServiceImpl.updateSubnet(sb, "extSysID=ctrlid1024", "");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet26() throws ServiceException {

        subnetServiceImpl.queryNetwork("", "1234");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet36() throws ServiceException {
        subnetServiceImpl.deleteSubnet("123", "extSysID=ctrlid1024", "");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet15() throws ServiceException {

        SbiSubnetNetModel sb = new SbiSubnetNetModel();
        sb.setNeId("123");
        subnetServiceImpl.updateSubnet(sb, "", "123");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet25() throws ServiceException {

        subnetServiceImpl.queryNetwork("extSysID=ctrlid1024", "12324");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSubnet35() throws ServiceException {
        subnetServiceImpl.deleteSubnet(null, "extSysID=ctrlid1024", "1234");
    }
}
