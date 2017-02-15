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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LocalSiteSNatROAResourceTest {

    LocalSiteSNatROAResource localSiteSNatROAResource = new LocalSiteSNatROAResource();

    @Before
    public void setup() throws Exception {
        LocalSiteSNatServiceImpl service = new LocalSiteSNatServiceImpl();
        AclServiceImpl service1 = new AclServiceImpl();
        Class<?> clazz = LocalSiteSNatROAResource.class;

        Object cc = clazz.newInstance();

        Field f1 = cc.getClass().getDeclaredField("localSiteSNatService");
        Field f2 = cc.getClass().getDeclaredField("aclServiceImpl");
        f1.setAccessible(true);
        f1.set(cc, service);
        f2.setAccessible(true);
        f2.set(cc, service1);
        localSiteSNatROAResource = (LocalSiteSNatROAResource)cc;
    }

    @Test
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

                ACResponse<AcAcl> obj = new ACResponse<>();
                AcAcl acl = new AcAcl();
                acl.setAclId("133");
                acl.setAclName("acl");
                acl.setAclNumber(123);
                acl.setAclStep(56);
                acl.setId("12346");
                acl.setNetId("5464");
                List<AcAclRule> ruleList = new ArrayList<>();
                AcAclRule e = new AcAclRule();
                e.setDescription("description");
                e.setDesIp("10.12.13.01");
                e.setDesPort(23);
                e.setDesVlan(564);
                e.setIcmpName("icmpName");
                e.setId(0);
                e.setPolicy("policy");
                e.setProtocol(23);
                e.setSrcIp("12.13.10.10");
                e.setSrcPort(12);
                e.setTcpSyn("tcpSyn");
                ruleList.add(e);
                acl.setRuleList(ruleList);
                obj.setData(acl);
                obj.setErrcode("0");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }

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

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                ACResponse<AcSNat> obj = new ACResponse<>();
                AcSNat acSnat = new AcSNat();
                acSnat.setAclId("7db47412f2b44164a16ef18b16a81c0c");
                acSnat.setAclNumber(3501);
                acSnat.setId("2467a068795b41ee9676bc79168da7a6");
                acSnat.setInterfaceName("GigabitEthernet0/0/2");
                acSnat.setQosPreNat("true");
                obj.setData(acSnat);
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
        snatNet.setCreatetime("aa0");
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
        ResultRsp<SbiSnatNetModel> r = localSiteSNatROAResource.createSnat(null, "123", "extSysID=ctrlid123", snatNet);
        assertTrue(r.getErrorCode().equals("overlayvpn.operation.success"));
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

                ACResponse<AcAcl> obj = new ACResponse<>();
                AcAcl acl = new AcAcl();
                acl.setAclId("133");
                acl.setAclName("acl");
                acl.setAclNumber(123);
                acl.setAclStep(56);
                acl.setId("12346");
                acl.setNetId("5464");
                List<AcAclRule> ruleList = new ArrayList<>();
                AcAclRule e = new AcAclRule();
                e.setDescription("description");
                e.setDesIp("10.12.13.01");
                e.setDesPort(23);
                e.setDesVlan(564);
                e.setIcmpName("icmpName");
                e.setId(0);
                e.setPolicy("policy");
                e.setProtocol(23);
                e.setSrcIp("12.13.10.10");
                e.setSrcPort(12);
                e.setTcpSyn("tcpSyn");
                ruleList.add(e);
                acl.setRuleList(ruleList);
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
        snatNet.setCreatetime("aa0");
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
        ResultRsp<SbiSnatNetModel> r1 = localSiteSNatROAResource.updateSnat(null, "123", "extSysID=ctrlid123", snatNet);
        assertEquals(r1.getErrorCode().equals("overlayvpn.operation.success"), true);
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
        snatNet.setCreatetime("aa0");
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
        ResultRsp<String> r2 = localSiteSNatROAResource.deleteSnat(null, "123", "2467a068795b41ee9676bc79168da7a6",
                "7db47412f2b44164a16ef18b16a81c0c", "extSysID=ctrlid123");
        assertEquals(r2.getErrorCode().equals("overlayvpn.operation.success"), true);
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

                ACResponse<AcSNat> obj = new ACResponse<>();
                AcSNat acSnat = new AcSNat();
                acSnat.setAclId("7db47412f2b44164a16ef18b16a81c0c");
                acSnat.setAclNumber(3501);
                acSnat.setId("2467a068795b41ee9676bc79168da7a6");
                acSnat.setInterfaceName("GigabitEthernet0/0/2");
                acSnat.setQosPreNat("true");
                obj.setData(acSnat);
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
        snatNet.setCreatetime("aa0");
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
        ResultRsp<String> r2 = localSiteSNatROAResource.querySnat(null, "123", "2467a068795b41ee9676bc79168da7a6",
                "7db47412f2b44164a16ef18b16a81c0c", "extSysID=ctrlid123");
        assertEquals(r2.getErrorCode().equals("overlayvpn.operation.success"), true);
    }
}
