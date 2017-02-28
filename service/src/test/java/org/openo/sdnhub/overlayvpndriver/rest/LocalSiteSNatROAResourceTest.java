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

import static org.junit.Assert.assertEquals;


import mockit.Mock;
import mockit.MockUp;

import org.junit.Before;
import org.junit.Test;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAclRule;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcSNat;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.AclServiceImpl;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.LocalSiteSNatServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSnatNetModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class LocalSiteSNatROAResourceTest {

    LocalSiteSNatROAResource localSiteSnatRoaResource = new LocalSiteSNatROAResource();


    /**
     * <br/>
     *
     * @throws Exception setup failure exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() throws Exception {
        LocalSiteSNatServiceImpl service = new LocalSiteSNatServiceImpl();
        Class<?> clazz = LocalSiteSNatROAResource.class;
        Object cc = clazz.newInstance();

        Field f1 = cc.getClass().getDeclaredField("localSiteSNatService");
        Field f2 = cc.getClass().getDeclaredField("aclServiceImpl");
        f1.setAccessible(true);
        f1.set(cc, service);
        f2.setAccessible(true);
        AclServiceImpl service1 = new AclServiceImpl();
        f2.set(cc, service1);
        localSiteSnatRoaResource = (LocalSiteSNatROAResource)cc;
    }

    @Test(expected = ServiceException.class)
    public void testCreateSnat_EmptyUuid() throws ServiceException {

        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        localSiteSnatRoaResource.createSnat("123", null, snatNet);
    }

    @Test(expected = ServiceException.class)
    public void testCreateSnat_EmptySbiSnetModel() throws ServiceException {

        localSiteSnatRoaResource.createSnat("123", "extSysID=ctrlid123", null);
    }

    @Test(expected = ServiceException.class)
    public void testCreateSnat_HttpError() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        localSiteSnatRoaResource.createSnat("123", "extSysID=ctrlid123", snatNet);
    }

    @Test(expected = ServiceException.class)
    public void testCreateSnat_HttpError_EmptyBody() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        localSiteSnatRoaResource.createSnat("123", "extSysID=ctrlid123", snatNet);
    }

    @Test(expected = Exception.class)
    public void testCreateSnat() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);


                AcAcl acl = new AcAcl();
                acl.setAclId("133");
                acl.setAclName("acl");
                acl.setAclNumber(123);
                acl.setAclStep(56);
                acl.setId("12346");
                acl.setNetId("5464");

                AcAclRule acRule = new AcAclRule();
                acRule.setDescription("description");
                acRule.setDesIp("10.12.13.01");
                acRule.setDesPort(23);
                acRule.setDesVlan(564);
                acRule.setIcmpName("icmpName");
                acRule.setId(0);
                acRule.setPolicy("policy");
                acRule.setProtocol(23);
                acRule.setSrcIp("12.13.10.10");
                acRule.setSrcPort(12);
                acRule.setTcpSyn("tcpSyn");
                List<AcAclRule> ruleList = new ArrayList<>();
                ruleList.add(acRule);
                acl.setRuleList(ruleList);
                ACResponse<AcAcl> obj = new ACResponse<>();
                obj.setData(acl);
                obj.setErrcode("0");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.createSnat("123", "extSysID=ctrlid123", snatNet);
    }

    @Test(expected = Exception.class)
    public void testCreateSnatException() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);


                AcAcl acl = new AcAcl();
                acl.setAclId("133");
                acl.setAclName("acl");
                acl.setAclNumber(123);
                acl.setAclStep(56);
                acl.setId("12346");
                acl.setNetId("5464");

                AcAclRule acRule = new AcAclRule();
                acRule.setDescription("description");
                acRule.setDesIp("10.12.13.01");
                acRule.setDesPort(23);
                acRule.setDesVlan(564);
                acRule.setIcmpName("icmpName");
                acRule.setId(0);
                acRule.setPolicy("policy");
                acRule.setProtocol(23);
                acRule.setSrcIp("12.13.10.10");
                acRule.setSrcPort(12);
                acRule.setTcpSyn("tcpSyn");
                List<AcAclRule> ruleList = new ArrayList<>();
                ruleList.add(acRule);
                acl.setRuleList(ruleList);
                ACResponse<AcAcl> obj = new ACResponse<>();
                obj.setData(acl);
                obj.setErrcode("1");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.createSnat("123", "extSysID=ctrlid123", snatNet);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSnat_EmptySbiSnetModel() throws ServiceException {

        localSiteSnatRoaResource.updateSnat("123", "extSysID=ctrlid123", null);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSnat_EmptyUuid() throws ServiceException {

        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        localSiteSnatRoaResource.updateSnat("123", null, snatNet);
    }

    @Test
    public void testUpdateSnat() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                AcSNat acSnat = new AcSNat();
                acSnat.setAclId("7db47412f2b44164a16ef18b16a81c0c");
                acSnat.setAclNumber(3501);
                acSnat.setId("2467a068795b41ee9676bc79168da7a6");
                acSnat.setInterfaceName("GigabitEthernet0/0/2");
                acSnat.setQosPreNat("true");
                List<AcSNat> list = new LinkedList<AcSNat>();
                list.add(acSnat);
                ACResponse<List<AcSNat>> obj1 = new ACResponse<>();
                obj1.setData(list);
                obj1.setErrcode("0");
                String retBody = JsonUtil.toJson(obj1);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.updateSnat("123", "extSysID=ctrlid123", snatNet);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSnat_EmptyUuid() throws ServiceException {

        localSiteSnatRoaResource.deleteSnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                null);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSnat_EmptyDeviceId() throws ServiceException {

        localSiteSnatRoaResource.deleteSnat(null, "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSnat_EmptyNatId() throws ServiceException {

        localSiteSnatRoaResource.deleteSnat("123", null, "7db47412f2b44164a16ef18b16a81c0c", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSnat_EmptyAclId() throws ServiceException {

        localSiteSnatRoaResource.deleteSnat("123", "2467a068795b41ee9676bc79168da7a6", null, "extSysID=ctrlid123");
    }

    @Test
    public void testDeleteSnat() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                ACResponse<String> obj = new ACResponse<>();
                obj.setData("data");
                obj.setErrcode("0");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        ResultRsp<String> r2 = localSiteSnatRoaResource.deleteSnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
        assertEquals(r2.getErrorCode().equals("overlayvpn.operation.success"), true);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSnatResponse500() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);

                ACResponse<String> obj = new ACResponse<>();
                obj.setData("data");
                obj.setErrcode("0");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.deleteSnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSnatResponseEmptyBody() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                httpReturnMessage.setBody("");
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.deleteSnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSnatErrorCodeException() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                ACResponse<String> obj = new ACResponse<>();
                obj.setData("data");
                obj.setErrcode("1");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");

        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.deleteSnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnat_EmptyUuid() throws ServiceException {

        localSiteSnatRoaResource.querySnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                null);
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnat_EmptyDeviceId() throws ServiceException {

        localSiteSnatRoaResource.querySnat(null, "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnat_EmptyNatId() throws ServiceException {

        localSiteSnatRoaResource.querySnat("123", null, "7db47412f2b44164a16ef18b16a81c0c", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnat_EmptyAclId() throws ServiceException {

        localSiteSnatRoaResource.querySnat("123", "2467a068795b41ee9676bc79168da7a6", null, "extSysID=ctrlid123");
    }

    @Test
    public void testQuerySnat() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                AcSNat acSnat = new AcSNat();
                acSnat.setAclId("7db47412f2b44164a16ef18b16a81c0c");
                acSnat.setAclNumber(3501);
                acSnat.setId("2467a068795b41ee9676bc79168da7a6");
                acSnat.setInterfaceName("GigabitEthernet0/0/2");
                acSnat.setQosPreNat("true");
                List<AcSNat> list = new LinkedList<AcSNat>();
                list.add(acSnat);
                ACResponse<List<AcSNat>> obj1 = new ACResponse<>();
                obj1.setData(list);
                obj1.setErrcode("0");
                String retBody = JsonUtil.toJson(obj1);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        ResultRsp<String> r2 = localSiteSnatRoaResource.querySnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
        assertEquals(r2.getErrorCode().equals("overlayvpn.operation.success"), true);
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnatResponse500() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);

                AcSNat acSnat = new AcSNat();
                acSnat.setAclId("7db47412f2b44164a16ef18b16a81c0c");
                acSnat.setAclNumber(3501);
                acSnat.setId("2467a068795b41ee9676bc79168da7a6");
                acSnat.setInterfaceName("GigabitEthernet0/0/2");
                acSnat.setQosPreNat("true");
                List<AcSNat> list = new LinkedList<AcSNat>();
                list.add(acSnat);
                ACResponse<List<AcSNat>> obj1 = new ACResponse<>();
                obj1.setData(list);
                obj1.setErrcode("0");
                String retBody = JsonUtil.toJson(obj1);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.querySnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnatResponseEmptyBody() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                httpReturnMessage.setBody("");
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.querySnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnatResponseErrorCode() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                AcSNat acSnat = new AcSNat();
                acSnat.setAclId("7db47412f2b44164a16ef18b16a81c0c");
                acSnat.setAclNumber(3501);
                acSnat.setId("2467a068795b41ee9676bc79168da7a6");
                acSnat.setInterfaceName("GigabitEthernet0/0/2");
                acSnat.setQosPreNat("true");
                List<AcSNat> list = new LinkedList<AcSNat>();
                list.add(acSnat);
                ACResponse<List<AcSNat>> obj1 = new ACResponse<>();
                obj1.setData(list);
                obj1.setErrcode("1");
                String retBody = JsonUtil.toJson(obj1);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.querySnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }

    @Test(expected = Exception.class)
    public void testQuerySnatResponseNotEqualId() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                AcSNat acSnat = new AcSNat();
                acSnat.setAclId("7db47412f2b4416ddgf4a16ef18b16a81c0c");
                acSnat.setAclNumber(3501);
                acSnat.setId("2467a068795bfdgfgfdg41ee9676bc79168da7a6");
                acSnat.setInterfaceName("GigabitEthernet0/0/2");
                acSnat.setQosPreNat("true");
                List<AcSNat> list = new LinkedList<AcSNat>();
                list.add(acSnat);
                ACResponse<List<AcSNat>> obj1 = new ACResponse<>();
                obj1.setData(list);
                obj1.setErrcode("0");
                String retBody = JsonUtil.toJson(obj1);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        SbiSnatNetModel snatNet = new SbiSnatNetModel();
        snatNet.setAclId("7db47412f2b44164a16ef18b16a81c0c");
        snatNet.setAclNumber("3501");
        snatNet.setActionState("active");
        snatNet.setAdminStatus("active");
        snatNet.setControllerId("ctrlid123");
        // snatNet.setCT("aa0");
        snatNet.setCreateTime("a0");
        snatNet.setDescription("description");
        snatNet.setDeviceId("123");
        snatNet.setEndPublicIpAddress("123.34.34.12/24");
        snatNet.setIfName("GigabitEthernet0/0/2");
        snatNet.setInternetGatewayId("a13246");
        snatNet.setName("Sbi nat model");
        snatNet.setNatId("2467a068795b41ee9676bc79168da7a6");
        snatNet.setNeId("a23");
        snatNet.setOperStatus("active");
        snatNet.setPrivateIpAddress("123.34.34.12/22");
        snatNet.setPrivatePrefix("a");
        snatNet.setQosPreNat("true");
        snatNet.setSource("source");
        snatNet.setStartPublicIpAddress("123.34.34.12/23");
        snatNet.setSubnetId("a35");
        snatNet.setTenantId("a12-354");
        snatNet.setType("create");
        snatNet.setUpdatetime("a0");
        snatNet.setUuid("a12-346");
        localSiteSnatRoaResource.querySnat("123", "2467a068795b41ee9676bc79168da7a6", "7db47412f2b44164a16ef18b16a81c0c",
                "extSysID=ctrlid123");
    }
}
