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

package org.openo.sdnhub.overlayvpndriver.nqa;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.rest.NqaROAResource;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.NqaConfigImpl;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNqa;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NqaROAResourceTest {

    NqaROAResource nqaRoaResource = new NqaROAResource();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() throws Exception {
        NqaConfigImpl service = new NqaConfigImpl();
        Class<?> clazz = NqaROAResource.class;

        Object cc = clazz.newInstance();

        Field f1 = cc.getClass().getDeclaredField("nqaConfigService");
        f1.setAccessible(true);
        f1.set(cc, service);
        nqaRoaResource = (NqaROAResource)cc;
    }

    @Test
    public void testQueryNqa() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setNeId("12345");
        sbiNqa.setDstIp("10.12.13.7");
        sbiNqaList.add(sbiNqa);
        String deviceId = "123";
        ResultRsp<SbiNqa> result = nqaRoaResource.queryNQA("extSysID=ctrlid1024", deviceId, sbiNqaList);
        assertEquals(result.getHttpCode(), 200);
    }

    @Test
    public void testQueryNqa5() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setNeId("12345");
        sbiNqa.setDstIp("10.12.13.7");
        sbiNqaList.add(sbiNqa);
        String deviceId = "123";
        ResultRsp<SbiNqa> result = nqaRoaResource.queryNQA("extSysID=ctrlid1024", deviceId, sbiNqaList);
        assertEquals(result.getHttpCode(), 200);
    }

    @Test(expected = ServiceException.class)
    public void testQueryNqa1() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setNeId("12345");
        sbiNqa.setDstIp("10.12.13.7");
        sbiNqaList.add(sbiNqa);
        String deviceId = "123";
        nqaRoaResource.queryNQA("", deviceId, sbiNqaList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNqaException() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                SbiNqa sbiNqa = new SbiNqa();
                sbiNqa.setActiveStatus("active");
                sbiNqa.setControllerId("controllerId");
                sbiNqa.setTestType("ping");
                sbiNqa.setDstIp("dstIp");
                sbiNqa.setSrcIp("10.10.10.10");
                SbiNqa sbiNqa1 = new SbiNqa();
                sbiNqa1.setActiveStatus("active");
                sbiNqa1.setControllerId("controllerId");
                sbiNqa1.setTestType("ping");
                sbiNqa1.setDstIp("dstIp");
                sbiNqa1.setSrcIp("10.10.10.10");
                List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
                sbiNqaList.add(sbiNqa);
                sbiNqaList.add(sbiNqa1);
                OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();
                obj.setData(sbiNqa);
                obj.setErrcode("0");
                String retBody = JsonUtil.toJson(obj);
                httpReturnMessage.setBody(retBody);
                return httpReturnMessage;
            }
        };
        HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
        httpReturnMessage.setStatus(200);
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("ping");
        sbiNqa.setDstIp("dstIp");
        sbiNqa.setSrcIp("10.10.10.10");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("ping");
        sbiNqa1.setDstIp("dstIp");
        sbiNqa1.setSrcIp("10.10.10.10");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();
        obj.setData(sbiNqa);
        nqaRoaResource.createNQA("extSysID=ctrlid1024", "123", sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testCreateNqaException01() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("testType");
        sbiNqa1.setDstIp("");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.createNQA("extSysID=ctrlid1024", "123", sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testCreateNqaException02() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("");
        sbiNqa1.setDstIp("dstIp");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.createNQA("extSysID=ctrlid1024", "123", sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testCreateNqaException04() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        SbiNqa sbiNqa = null;
        SbiNqa sbiNqa1 = null;

        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.createNQA("extSysID=ctrlid1024", "123", sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testCreateNqa1() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("testType");
        sbiNqa1.setDstIp("");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.createNQA("extSysID=ctrlid1024", "deviceid", sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testCreateNqa2() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("");
        sbiNqa1.setDstIp("dstIp");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.createNQA("extSysID=ctrlid1024", "deviceid", sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testCreateNqa3() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        SbiNqa sbiNqa = null;
        SbiNqa sbiNqa1 = null;

        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.createNQA("extSysID=ctrlid1024", "deviceid", sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testCreateNqa4() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("testType");
        sbiNqa1.setDstIp("dstIp");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.createNQA("", "deviceid", sbiNqaList);
    }

    @Test
    public void testUpdateNqa() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("testType");
        sbiNqa1.setDstIp("DstType");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        String deviceId = "123";
        ResultRsp<SbiNqa> result = nqaRoaResource.updateNQA("extSysID=ctrlid1024", deviceId, sbiNqaList);
        assertEquals(result.getData(), null);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateNqa1() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("testType");
        sbiNqa1.setDstIp("DstType");

        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        String deviceId = "123";
        nqaRoaResource.updateNQA("", deviceId, sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateNqa2() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("dstIp");
        SbiNqa sbiNqa1 = new SbiNqa();
        sbiNqa1.setActiveStatus("active");
        // sbiNqa1.setConnectionId("connectionId");
        sbiNqa1.setControllerId("controllerId");
        sbiNqa1.setTestType("");
        sbiNqa1.setDstIp("DstType");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        String deviceId = "123";
        nqaRoaResource.updateNQA("extSysID=ctrlid1024", deviceId, sbiNqaList);

    }

    @Test(expected = ServiceException.class)
    public void testUpdateNqa3() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setActiveStatus("active");
        // sbiNqa.setConnectionId("connectionId");
        sbiNqa.setControllerId("controllerId");
        sbiNqa.setTestType("testType");
        sbiNqa.setDstIp("");
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        sbiNqaList.add(sbiNqa);
        String deviceId = "123";
        nqaRoaResource.updateNQA("extSysID=ctrlid1024", deviceId, sbiNqaList);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateNqa4() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        String deviceId = "123";
        List<SbiNqa> sbiNqaList = new ArrayList<SbiNqa>();
        SbiNqa sbiNqa = null;

        SbiNqa sbiNqa1 = null;

        sbiNqaList.add(sbiNqa);
        sbiNqaList.add(sbiNqa1);
        nqaRoaResource.updateNQA("extSysID=ctrlid1024", deviceId, sbiNqaList);
    }

    @Test
    public void testDeleteNqa() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        List<String> sbiNqaIdList = Arrays.asList("externalId1, externalId2");
        String deviceId = "123";
        ResultRsp<String> result = nqaRoaResource.deleteNQA("extSysID=ctrlid1024", deviceId, sbiNqaIdList);
        assertEquals(result.getHttpCode(), 200);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteNqa1() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };

        String deviceId = "123";
        nqaRoaResource.deleteNQA("", deviceId, Arrays.asList("externalID1", "externalId2"));
    }

    @Test
    public void testDeleteNqaNullResponse() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        String deviceId = "123";
        ResultRsp<String> result = nqaRoaResource.deleteNQA("extSysID=ctrlid1024", deviceId,
                Arrays.asList("externalID1", "externalId2"));
        assertEquals(result.getData(), null);
    }
}
