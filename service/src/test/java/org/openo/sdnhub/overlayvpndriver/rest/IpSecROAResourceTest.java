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
import org.openo.sdnhub.overlayvpndriver.controller.model.Ike;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpSec;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnList;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnection;
import org.openo.sdnhub.overlayvpndriver.controller.model.LocalId;
import org.openo.sdnhub.overlayvpndriver.controller.model.PeerId;
import org.openo.sdnhub.overlayvpndriver.controller.model.RuleList;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.IpsecImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.Ip;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIkePolicy;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIpSecPolicy;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IpSecROAResourceTest {

    IpSecROAResource ipSecROAResource = new IpSecROAResource();

    List<SbiNeIpSec> ipSecNeConnectionList = null;

    /**
     * <br/>
     *
     * @throws Exception setup failure exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() throws Exception {
        IpsecImpl service = new IpsecImpl();
        Class<?> clazz = IpSecROAResource.class;

        Object cc = clazz.newInstance();

        Field f1 = cc.getClass().getDeclaredField("ipsecService");
        f1.setAccessible(true);
        f1.set(cc, service);
        ipSecROAResource = (IpSecROAResource)cc;

        ipSecNeConnectionList = new ArrayList<>();
        SbiNeIpSec sbiNeIpSec = new SbiNeIpSec();
        sbiNeIpSec.setActiveStatus("active");
        String additionalInfo = "information";
        sbiNeIpSec.setAdditionalInfo(additionalInfo);
        sbiNeIpSec.setConnectionServiceId("123");
        sbiNeIpSec.setControllerId("ctrlid1024");
        sbiNeIpSec.setCreatetime((long)1111111110);
        sbiNeIpSec.setDeployStatus("deploy");
        sbiNeIpSec.setDescription("description");
        sbiNeIpSec.setDestIfName("destIfName");
        sbiNeIpSec.setDeviceId("123");
        sbiNeIpSec.setExternalId("111");
        sbiNeIpSec.setExternalIpSecId("92345728-2345678");
        Ip ip = new Ip();
        ip.setIpv4("10.172.31.12");
        ip.setUuid("12345678");
        sbiNeIpSec.setPeerAddress(JsonUtil.toJson(ip));
        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("md5");
        ikePolicy.setEncryptionAlgorithm("des");
        ikePolicy.setExternalId("888");
        ikePolicy.setIkeVersion("v1");
        ikePolicy.setLifeTime("temporary");
        ikePolicy.setPfs("Group2");
        ikePolicy.setPsk("psk");
        ikePolicy.setSbiServiceId("999");
        ikePolicy.setUuid("6548");
        sbiNeIpSec.setIkePolicy(ikePolicy);
        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("md5");
        ipSecPolicy.setEncapsulationMode("tunnel");
        ipSecPolicy.setEncryptionAlgorithm("3des");
        ipSecPolicy.setExternalId("1564");
        ipSecPolicy.setLifeTime("permanent");
        ipSecPolicy.setPfs("Group2");
        ipSecPolicy.setSbiServiceId("546");
        ipSecPolicy.setTransformProtocol("esp");
        ipSecPolicy.setUuid("652348");
        sbiNeIpSec.setIpSecPolicy(ipSecPolicy);
        sbiNeIpSec.setIsTemplateType("true");
        sbiNeIpSec.setLocalNeRole("thincpe");
        sbiNeIpSec.setName("name");
        sbiNeIpSec.setNeId("333");
        sbiNeIpSec.setNqa("nqa");
        sbiNeIpSec.setOperationStatus("None");
        //sbiNeIpSec.setPeerAddress("10.10.2.3");
        sbiNeIpSec.setPeerDeviceId("444");
        sbiNeIpSec.setPeerLanCidrs("10.21.54.6");
        sbiNeIpSec.setPeerNeId("555");
        sbiNeIpSec.setProtectionPolicy("nqa");
        sbiNeIpSec.setQosPreClassify("true");
        sbiNeIpSec.setRegionId("666");
        sbiNeIpSec.setRunningStatus("none");
        sbiNeIpSec.setSourceAddress("10.21.54.61");
        sbiNeIpSec.setSoureIfName("sourceIfName");
        sbiNeIpSec.setSourceLanCidrs("10.10.2.13");
        sbiNeIpSec.setTenantId("777");
        sbiNeIpSec.setTenantName("tenantName");
        sbiNeIpSec.setUpdatetime((long)1021346);
        sbiNeIpSec.setUuid("654");
        sbiNeIpSec.setWorkType("work");
        ipSecNeConnectionList.add(sbiNeIpSec);

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };
    }

    @Test
    public void testIpsecCreate() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            private List<RuleList> ruleList;

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                IpsecConnList element = new IpsecConnList();
                element.setUuid("123");
                element.setInterfaceName("interfaceName");
                Ike ike = new Ike();
                ike.setAuthAlgorithm("authAlgorithm");
                ike.setEncryptionAlgorithm("encryptionAlgorithm");
                ike.setLocalAddress("10.12.13.6");
                LocalId localId = new LocalId();
                localId.setType("local type");
                localId.setValue("value");
                ike.setLocalId(localId);
                ike.setPeerAddress("10.12.13.45");
                PeerId peerId = new PeerId();
                ike.setPeerId(peerId);
                peerId.setType("peer Type");
                peerId.setValue("value");
                ike.setPreSharedKey("preSharedKey");
                ike.setVersion("v1");

                IpsecConnection ipsecConn = new IpsecConnection();
                ipsecConn.setIke(ike);
                IpSec ipsec = new IpSec();
                ipsec.setEspAuthAlgorithm("espAuthAlgorithm");
                ipsec.setEspEncryptionAlgorithm("espEncryptionAlgorithm");
                ipsecConn.setIpSec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                ipsecConn.setRuleList(ruleList);
                ipsecConn.setSeqNumber(123);
                ipsecConn.setType("type");

                List<IpsecConnection> ipsecConnection = new ArrayList<>();
                ipsecConnection.add(ipsecConn);
                element.setIpsecConnection(ipsecConnection);
                element.setName("name");

                List<IpsecConnList> data = new ArrayList<>();
                data.add(0, element);

                OverlayVpnDriverResponse<List<IpsecConnList>> response =
                        new OverlayVpnDriverResponse<List<IpsecConnList>>();
                response.setData(data);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                IpsecConnList element = new IpsecConnList();
                element.setUuid("123");
                element.setInterfaceName("interfaceName");
                Ike ike = new Ike();
                ike.setAuthAlgorithm("authAlgorithm");
                ike.setEncryptionAlgorithm("encryptionAlgorithm");
                ike.setLocalAddress("10.12.13.6");
                LocalId localId = new LocalId();
                localId.setType("local type");
                localId.setValue("value");
                ike.setLocalId(localId);
                ike.setPeerAddress("10.12.13.45");
                PeerId peerId = new PeerId();
                ike.setPeerId(peerId);
                peerId.setType("peer Type");
                peerId.setValue("value");
                ike.setPreSharedKey("preSharedKey");
                ike.setVersion("v1");

                IpsecConnection ipsecConn = new IpsecConnection();
                ipsecConn.setIke(ike);
                IpSec ipsec = new IpSec();
                ipsec.setEspAuthAlgorithm("espAuthAlgorithm");
                ipsec.setEspEncryptionAlgorithm("espEncryptionAlgorithm");
                ipsecConn.setIpSec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                ipsecConn.setRuleList(ruleList);
                ipsecConn.setSeqNumber(123);
                ipsecConn.setType("type");

                List<IpsecConnection> ipsecConnection = new ArrayList<>();
                ipsecConnection.add(ipsecConn);
                element.setIpsecConnection(ipsecConnection);
                element.setName("name");

                List<IpsecConnList> data = new ArrayList<>();
                data.add(0, element);

                OverlayVpnDriverResponse<List<IpsecConnList>> response =
                        new OverlayVpnDriverResponse<List<IpsecConnList>>();
                response.setData(data);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ResultRsp<SbiNeIpSec> res = ipSecROAResource.ipsecCreate(null, "extSysID=ctrlid1024", ipSecNeConnectionList);
        assertEquals("overlayvpn.operation.success", res.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void testIpsecCreateInvalidCtrlUuid() throws ServiceException {
        ipSecROAResource.ipsecCreate(null, "extSysID=", ipSecNeConnectionList);
    }

    @Test(expected = ServiceException.class)
    public void testIpsecCreateInvalidEmptyBody() throws ServiceException {
        ipSecROAResource.ipsecCreate(null, "extSysID=18294", null);
    }

    @Test
    public void testIpsecUpdate() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            private List<RuleList> ruleList;

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                IpsecConnList element = new IpsecConnList();
                element.setUuid("123");
                element.setInterfaceName("interfaceName");
                Ike ike = new Ike();
                ike.setAuthAlgorithm("authAlgorithm");
                ike.setEncryptionAlgorithm("encryptionAlgorithm");
                ike.setLocalAddress("10.12.13.6");
                LocalId localId = new LocalId();
                localId.setType("local type");
                localId.setValue("value");
                ike.setLocalId(localId);
                ike.setPeerAddress("10.12.13.45");
                PeerId peerId = new PeerId();
                ike.setPeerId(peerId);
                peerId.setType("peer Type");
                peerId.setValue("value");
                ike.setPreSharedKey("preSharedKey");
                ike.setVersion("v1");

                IpsecConnection ipsecConn = new IpsecConnection();
                ipsecConn.setIke(ike);
                IpSec ipsec = new IpSec();
                ipsec.setEspAuthAlgorithm("espAuthAlgorithm");
                ipsec.setEspEncryptionAlgorithm("espEncryptionAlgorithm");
                ipsecConn.setIpSec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                ipsecConn.setRuleList(ruleList);
                ipsecConn.setSeqNumber(123);
                ipsecConn.setType("type");

                List<IpsecConnection> ipsecConnection = new ArrayList<>();
                ipsecConnection.add(ipsecConn);
                element.setIpsecConnection(ipsecConnection);
                element.setName("name");

                List<IpsecConnList> data = new ArrayList<>();
                data.add(0, element);

                OverlayVpnDriverResponse<List<IpsecConnList>> response =
                        new OverlayVpnDriverResponse<List<IpsecConnList>>();
                response.setData(data);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                IpsecConnList element = new IpsecConnList();
                element.setUuid("123");
                element.setInterfaceName("interfaceName");
                Ike ike = new Ike();
                ike.setAuthAlgorithm("authAlgorithm");
                ike.setEncryptionAlgorithm("encryptionAlgorithm");
                ike.setLocalAddress("10.12.13.6");
                LocalId localId = new LocalId();
                localId.setType("local type");
                localId.setValue("value");
                ike.setLocalId(localId);
                ike.setPeerAddress("10.12.13.45");
                PeerId peerId = new PeerId();
                ike.setPeerId(peerId);
                peerId.setType("peer Type");
                peerId.setValue("value");
                ike.setPreSharedKey("preSharedKey");
                ike.setVersion("v1");

                IpsecConnection ipsecConn = new IpsecConnection();
                ipsecConn.setIke(ike);
                IpSec ipsec = new IpSec();
                ipsec.setEspAuthAlgorithm("espAuthAlgorithm");
                ipsec.setEspEncryptionAlgorithm("espEncryptionAlgorithm");
                ipsecConn.setIpSec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                ipsecConn.setRuleList(ruleList);
                ipsecConn.setSeqNumber(123);
                ipsecConn.setType("type");

                List<IpsecConnection> ipsecConnection = new ArrayList<>();
                ipsecConnection.add(ipsecConn);
                element.setIpsecConnection(ipsecConnection);
                element.setName("name");
                List<IpsecConnList> data = new ArrayList<>();
                data.add(0, element);
                OverlayVpnDriverResponse<List<IpsecConnList>> response =
                        new OverlayVpnDriverResponse<List<IpsecConnList>>();
                response.setData(data);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ResultRsp<SbiNeIpSec> resp = ipSecROAResource.ipsecUpdate(null, "extSysID=ctrlid1024", ipSecNeConnectionList);
        assertEquals("overlayvpn.operation.success", resp.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void testIpsecUpdateInvalidUuid() throws ServiceException {
        ipSecROAResource.ipsecUpdate(null, "extSysID=", ipSecNeConnectionList);
    }

    @Test(expected = ServiceException.class)
    public void testIpsecUpdateNullBody() throws ServiceException {
        ipSecROAResource.ipsecUpdate(null, "extSysID=ctrlid1024", null);
    }

    @Test
    public void testDeleteIpSec() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            private List<RuleList> ruleList;

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                ACDelResponse obj = new ACDelResponse();
                obj.setErrocode("0");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                IpsecConnList element = new IpsecConnList();
                element.setUuid("123");
                element.setInterfaceName("interfaceName");
                Ike ike = new Ike();
                ike.setAuthAlgorithm("authAlgorithm");
                ike.setEncryptionAlgorithm("encryptionAlgorithm");
                ike.setLocalAddress("10.12.13.6");
                LocalId localId = new LocalId();
                localId.setType("local type");
                localId.setValue("value");
                ike.setLocalId(localId);
                ike.setPeerAddress("10.12.13.45");
                PeerId peerId = new PeerId();
                ike.setPeerId(peerId);
                peerId.setType("peer Type");
                peerId.setValue("value");
                ike.setPreSharedKey("preSharedKey");
                ike.setVersion("v1");

                IpsecConnection ipsecConn = new IpsecConnection();
                ipsecConn.setIke(ike);
                IpSec ipsec = new IpSec();
                ipsec.setEspAuthAlgorithm("espAuthAlgorithm");
                ipsec.setEspEncryptionAlgorithm("espEncryptionAlgorithm");
                ipsecConn.setIpSec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                ipsecConn.setRuleList(ruleList);
                ipsecConn.setSeqNumber(123);
                ipsecConn.setType("type");

                List<IpsecConnection> ipsecConnection = new ArrayList<>();
                ipsecConnection.add(ipsecConn);
                element.setIpsecConnection(ipsecConnection);
                element.setName("name");

                List<IpsecConnList> data = new ArrayList<>();
                data.add(0, element);

                OverlayVpnDriverResponse<List<IpsecConnList>> response =
                        new OverlayVpnDriverResponse<List<IpsecConnList>>();
                response.setData(data);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ResultRsp<SbiNeIpSec> resp =
                ipSecROAResource.deleteIpSec(null, "extSysID=ctrlid1024", "123", ipSecNeConnectionList);
        assertEquals("overlayvpn.operation.success", resp.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void testDeleteIpSecInvalidUuid() throws ServiceException {
        ipSecROAResource.deleteIpSec(null, "extSysID=", "123", ipSecNeConnectionList);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteIpSecNullBody() throws ServiceException {
        ipSecROAResource.deleteIpSec(null, "extSysID=ctrlid1024", "123", null);
    }

    @Test
    public void testQuery() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            private List<RuleList> ruleList;

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                IpsecConnList element = new IpsecConnList();
                element.setUuid("123");
                element.setInterfaceName("interfaceName");
                Ike ike = new Ike();
                ike.setAuthAlgorithm("authAlgorithm");
                ike.setEncryptionAlgorithm("encryptionAlgorithm");
                ike.setLocalAddress("10.12.13.6");
                LocalId localId = new LocalId();
                localId.setType("local type");
                localId.setValue("value");
                ike.setLocalId(localId);
                ike.setPeerAddress("10.12.13.45");
                PeerId peerId = new PeerId();
                ike.setPeerId(peerId);
                peerId.setType("peer Type");
                peerId.setValue("value");
                ike.setPreSharedKey("preSharedKey");
                ike.setVersion("v1");

                IpsecConnection ipsecConn = new IpsecConnection();
                ipsecConn.setIke(ike);
                IpSec ipsec = new IpSec();
                ipsec.setEspAuthAlgorithm("espAuthAlgorithm");
                ipsec.setEspEncryptionAlgorithm("espEncryptionAlgorithm");
                ipsecConn.setIpSec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                ipsecConn.setRuleList(ruleList);
                ipsecConn.setSeqNumber(123);
                ipsecConn.setType("type");

                List<IpsecConnection> ipsecConnection = new ArrayList<>();
                ipsecConnection.add(ipsecConn);
                element.setIpsecConnection(ipsecConnection);
                element.setName("name");

                List<IpsecConnList> data = new ArrayList<>();
                data.add(0, element);
                OverlayVpnDriverResponse<List<IpsecConnList>> response =
                        new OverlayVpnDriverResponse<List<IpsecConnList>>();
                response.setData(data);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        ResultRsp<SbiNeIpSec> resp = ipSecROAResource.query(null, "extSysID=ctrlid1024", ipSecNeConnectionList);
        assertEquals("overlayvpn.operation.success", resp.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void testQueryInvalidUuid() throws ServiceException {
        ipSecROAResource.query(null, "extSysID=", ipSecNeConnectionList);
    }

    @Test(expected = ServiceException.class)
    public void testQueryNullBody() throws ServiceException {
        ipSecROAResource.query(null, "extSysID=ctrlid1024", null);
    }

}
