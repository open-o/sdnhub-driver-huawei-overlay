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

package org.openo.sdnhub.overlayvpndriver.sbi.impl;

import static org.junit.Assert.assertEquals;

import mockit.Mock;
import mockit.MockUp;

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
import org.openo.sdnhub.overlayvpndriver.service.model.Ip;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IpsecImplTest {

    @Test
    public void testIpsecUpdate() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                IpsecConnList element = new IpsecConnList();
                // element.setId("123");
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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("123");
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
                // element.setId("123");
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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("123");
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
        SbiNeIpSec spec = new SbiNeIpSec();
        spec.setSoureIfName("interfaceName");
        spec.setExternalId("0");
        spec.setSourceAddress("sourceAddress");
        spec.setPeerAddress("peerAddress");

        ResultRsp<SbiNeIpSec> rsp = new ResultRsp<>();
        rsp.setData(spec);
        IpsecImpl.update("CtrlId", "DeviceId", spec, rsp);
    }

    @Test
    public void testDeleteIpSec() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
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

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("123");
        sbi.setExternalId("456");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        impl.batchDeleteIpsecConn("Ctrl", list);
    }

    @Test
    public void testDeleteIpSecSuccess() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
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

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("01");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("123");
        sbi.setExternalId("456");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<SbiNeIpSec> rsp = impl.batchDeleteIpsecConn("Ctrl", list);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void testDeleteIpSecHttpSuccess() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
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
                httpReturnMessage.setStatus(300);

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("123");
        sbi.setExternalId("456");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<SbiNeIpSec> rsp = impl.batchDeleteIpsecConn("Ctrl", list);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void testDeleteIpSecHttpSuccessFail() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
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

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("12345");
        sbi.setExternalId("456");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<SbiNeIpSec> rsp = impl.batchDeleteIpsecConn("Ctrl", list);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void testDeleteIpSecHttpExternalIdFail() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
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

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("123");
        sbi.setExternalId("456789");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<SbiNeIpSec> rsp = impl.batchDeleteIpsecConn("Ctrl", list);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void testDeleteIpSecBodyNull() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
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

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("0");
                httpReturnMessage.setBody(null);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("123");
        sbi.setExternalId("456");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<SbiNeIpSec> rsp = impl.batchDeleteIpsecConn("Ctrl", list);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void testDeleteIpSecIpSecEmpty() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
                ipsecConn.setType("type");

                List<IpsecConnection> ipsecConnection = new ArrayList<>();
                //ipsecConnection.add(ipsecConn);
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

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("0");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("123");
        sbi.setExternalId("456");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<SbiNeIpSec> rsp = impl.batchDeleteIpsecConn("Ctrl", list);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void testDeleteIpSecTimeOut() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

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
                // ipsecConn.setIpsec(ipsec);
                ipsecConn.setNqaId("123");
                ipsecConn.setNqaState("nqaState");
                ipsecConn.setQosPreClassify("qosPreClassify");
                ipsecConn.setSeqNumber(456);

                // ipsecConn.setRuleList(ruleList);
                // ipsecConn.setSeqNumber("SeqNo");
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

                OverlayVpnDriverResponse<List<IpsecConnList>> response = new OverlayVpnDriverResponse<>();
                List<IpsecConnList> list = new ArrayList<>();
                IpsecConnList ipList = new IpsecConnList();
                ipList.setName("name");
                list.add(ipList);
                response.setData(list);
                response.setErrcode("0");
                httpReturnMessage.setBody(null);
                httpReturnMessage.setStatus(408);
                return httpReturnMessage;
            }
        };

        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setActiveStatus("123");
        sbi.setDeviceId("123");
        sbi.setExternalIpSecId("123");
        sbi.setExternalId("456");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<SbiNeIpSec> rsp = impl.batchDeleteIpsecConn("Ctrl", list);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }


    @Test
    public void configIpsec() throws ServiceException {
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

        IpsecConnList conn = new IpsecConnList();
        conn.setUuid("123");
        List<IpsecConnection> ipsecConnectionList = new LinkedList<>();
        IpsecConnection ipSecConnection = new IpsecConnection();
        ipSecConnection.setDeviceId("DeviceId");
        ipsecConnectionList.add(ipSecConnection);
        conn.setIpsecConnection(ipsecConnectionList);
        List<IpsecConnList> list = new ArrayList<>();
        list.add(conn);
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<List<IpsecConnList>> res = impl.configIpsec("Ctrl", "DeviceId", list);
        assertEquals("overlayvpn.operation.success", res.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void configIpsecDeviceIdNull() throws ServiceException {

        IpsecImpl impl = new IpsecImpl();
        List<IpsecConnList> list = new ArrayList<>();
        IpsecConnList conn = new IpsecConnList();
        conn.setUuid("123");
        list.add(conn);
        impl.configIpsec("Ctrl", null, list);
    }

    @Test
    public void backWriteId() throws ServiceException {

        List<SbiNeIpSec> list = new ArrayList<>();
        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setDeviceId("123");
        list.add(sbi);

        List<IpsecConnList> connList = new ArrayList<>();
        IpsecConnList conn = new IpsecConnList();
        connList.add(conn);

        IpsecImpl impl = new IpsecImpl();
        impl.backWriteId(list, connList, "123");
    }

    @Test
    public void backWriteIdFalse() throws ServiceException {

        List<SbiNeIpSec> list = new ArrayList<>();
        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setDeviceId("1234");
        list.add(sbi);

        List<IpsecConnList> connList = new ArrayList<>();
        IpsecConnList conn = new IpsecConnList();
        connList.add(conn);

        IpsecImpl impl = new IpsecImpl();
        impl.backWriteId(list, connList, "123");
    }

    @Test(expected = ServiceException.class)
    public void queryIpsecByDeviceCtrlNull() throws ServiceException {

        IpsecImpl.queryIpsecByDevice(null, "DeviceId", "name");
    }

    @Test(expected = ServiceException.class)
    public void queryIpsecByDeviceNull() throws ServiceException {

        IpsecImpl.queryIpsecByDevice("ctrl",null, "name");
    }

    @Test(expected = NullPointerException.class)
    public void createNaA() throws ServiceException {

        SbiNeIpSec sbi = new SbiNeIpSec();

        Ip ip = new Ip();
        ip.setIpv4("10.172.31.12");
        ip.setUuid("12345678");
        sbi.setPeerAddress(JsonUtil.toJson(ip));

        sbi.setDeviceId("123");
        sbi.setProtectionPolicy("nqa");
        sbi.setLocalNeRole("localcpe");
        List<SbiNeIpSec> list = new ArrayList<>();
        list.add(sbi);
        Map<String, List<SbiNeIpSec>> map = new HashMap<>();
        map.put("key", list);

        IpsecImpl impl = new IpsecImpl();
        impl.createNQA(map, "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void checkRuleDataForLteFail() throws ServiceException {
        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setWorkType("project");
        IpsecImpl.checkRuleDataForLte(sbi);
    }

    @Test(expected = ServiceException.class)
    public void checkRuleDataForLteFailure() throws ServiceException {
        SbiNeIpSec sbi = new SbiNeIpSec();
        sbi.setWorkType("project");
        sbi.setSourceLanCidrs("source");
        IpsecImpl.checkRuleDataForLte(sbi);
    }

    @Test(expected = ServiceException.class)
    public void checkSeqNumber() throws ServiceException {
        IpsecImpl impl = new IpsecImpl();
        List<String> list = new ArrayList<>();
        list.add("value");
        impl.checkSeqNumber(list);
    }

    @Test(expected = ServiceException.class)
    public void checkSeqNumberZero() throws ServiceException {
        IpsecImpl impl = new IpsecImpl();
        List<String> list = new ArrayList<>();
        list.add("0");
        impl.checkSeqNumber(list);
    }

    @Test(expected = ServiceException.class)
    public void queryIpsecByDeviceSuccessFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            private List<RuleList> ruleList;

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
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
        IpsecImpl.queryIpsecByDevice("Ctrl", "Deviceid", "name");
    }

    @Test(expected = ServiceException.class)
    public void queryIpsecByDeviceBosyNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                httpReturnMessage.setBody(null);
                return httpReturnMessage;
            }
        };
        IpsecImpl.queryIpsecByDevice("Ctrl", "Deviceid", "name");
    }

    @Test(expected = ServiceException.class)
    public void queryIpsecByDeviceResponseFail() throws ServiceException {

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
                response.setErrcode("01");
                String retBody = JsonUtil.toJson(response);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        IpsecImpl.queryIpsecByDevice("Ctrl", "Deviceid", "name");
    }

    @Test
    public void deleteNqaConfigForIpSec() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

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
        };
        IpsecImpl impl = new IpsecImpl();
        ResultRsp<String> rsp = impl.deleteNqaConfigForIpSec("ctrl", "delete", "json");
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

}
