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
import static org.junit.Assert.assertTrue;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.VlanServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIfVlan;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VlanROAResourceTest {

    private String putresp =
            "{\"errcode\":\"0\",\"errmsg\":\"\",\"data\":[{\"id\":\"b6cf7eb4038049dfac86391421f37b7f\",\"name\":"
            + "\"GigabitEthernet0/0/0\","
                    + "\"enable\":true,\"ifAttr\":\"1\",\"defaultVlan\":1,\"trunkVlan\":\"2-10,21-35,56,100-110\","
                    + "\"description\":\"Ethernet0/0/0 config\","
                    + "\"autoNegotiationEnable\": false,\"speed\":\"100\",\"duplex\":\"half\",\"nacType\":\"1\"}]}";

    @Test
    public void testCreateVlanNormal() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(putresp);
                msg.setStatus(200);
                return msg;
            }
        };
        VlanROAResource roa = new VlanROAResource();
        VlanServiceImpl impl = new VlanServiceImpl();
        Field field = VlanROAResource.class.getDeclaredField("vlanService");
        field.setAccessible(true);
        field.set(roa, impl);


        SbiIfVlan vlan = new SbiIfVlan();
        vlan.allocateUuid();
        vlan.setIfName("123");
        vlan.setDefaultVlan(123);
        List<SbiIfVlan> ifVlans = new ArrayList<>();
        ifVlans.add(vlan);

        ResultRsp<List<SbiIfVlan>> res = roa.createVlan(null, "111", "123", ifVlans);
        assertTrue(res.isSuccess());
    }

    @Test(expected = ServiceException.class)
    public void testCreateVlanInvalidIp() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        VlanROAResource roa = new VlanROAResource();
        roa.createVlan(null, "111", "123$$", null);
    }

    @Test(expected = ServiceException.class)
    public void testCreateVlanInvalidIP2() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        VlanROAResource roa = new VlanROAResource();
        roa.createVlan(null, "111", "123", new ArrayList<>());
    }

    @Test(expected = ServiceException.class)
    public void testCreateVlanControllerReturnError() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                Map<String, String> op = new HashMap<>();
                op.put("erro", "err");
                msg.setBody(JsonUtil.toJson(op));
                msg.setStatus(500);
                return msg;
            }
        };
        VlanROAResource roa = new VlanROAResource();
        VlanServiceImpl impl = new VlanServiceImpl();
        Field field = VlanROAResource.class.getDeclaredField("vlanService");
        field.setAccessible(true);
        field.set(roa, impl);


        SbiIfVlan vlan = new SbiIfVlan();
        vlan.allocateUuid();
        vlan.setIfName("123");
        vlan.setDefaultVlan(123);
        List<SbiIfVlan> ifVlans = new ArrayList<>();
        ifVlans.add(vlan);

        ResultRsp<List<SbiIfVlan>> res = roa.createVlan(null, "111", "123", ifVlans);
        assertTrue(res.isSuccess());
    }

    @Test
    public void testUpdateVlanNormal() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException, ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(putresp);
                msg.setStatus(200);
                return msg;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(putresp);
                msg.setStatus(200);
                return msg;
            }
        };
        VlanROAResource roa = new VlanROAResource();
        VlanServiceImpl impl = new VlanServiceImpl();
        Field field = VlanROAResource.class.getDeclaredField("vlanService");
        field.setAccessible(true);
        field.set(roa, impl);


        SbiIfVlan vlan = new SbiIfVlan();
        vlan.allocateUuid();
        vlan.setIfName("123");
        vlan.setDefaultVlan(123);
        List<SbiIfVlan> ifVlans = new ArrayList<>();
        ifVlans.add(vlan);
        ResultRsp<List<SbiIfVlan>> res = roa.updateVlan(null, "111", "123", ifVlans);
        assertEquals(res.getData().get(0).getEthInterfaceConfigId(), "b6cf7eb4038049dfac86391421f37b7f");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateVlanFailControllerReturnError() throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException, ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(putresp);
                msg.setStatus(200);
                return msg;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                Map<String, String> op = new HashMap<>();
                op.put("erro", "err");
                msg.setBody(JsonUtil.toJson(op));
                msg.setStatus(500);
                return msg;
            }
        };
        VlanROAResource roa = new VlanROAResource();
        VlanServiceImpl impl = new VlanServiceImpl();
        Field field = VlanROAResource.class.getDeclaredField("vlanService");
        field.setAccessible(true);
        field.set(roa, impl);


        SbiIfVlan vlan = new SbiIfVlan();
        vlan.allocateUuid();
        vlan.setIfName("123");
        vlan.setDefaultVlan(123);
        List<SbiIfVlan> ifVlans = new ArrayList<>();
        ifVlans.add(vlan);
        roa.updateVlan(null, "111", "123", ifVlans);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateVlanInvalidIp() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        VlanROAResource roa = new VlanROAResource();
        roa.updateVlan(null, "111", "123$$", null);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateVlanInvalidIP2s() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        VlanROAResource roa = new VlanROAResource();
        roa.createVlan(null, "111", "123", new ArrayList<>());
    }

    @Test
    public void testQueryVlanSuccess() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(putresp);
                msg.setStatus(200);
                return msg;
            }
        };

        VlanROAResource roa = new VlanROAResource();
        VlanServiceImpl impl = new VlanServiceImpl();
        Field field = VlanROAResource.class.getDeclaredField("vlanService");
        field.setAccessible(true);
        field.set(roa, impl);


        SbiIfVlan vlan = new SbiIfVlan();
        vlan.allocateUuid();
        vlan.setIfName("123");
        vlan.setDefaultVlan(123);
        List<SbiIfVlan> ifVlans = new ArrayList<>();
        ifVlans.add(vlan);

        ResultRsp<List<SbiIfVlan>> res = roa.queryVlan(null, "111", "", "123");
        assertTrue(res.isSuccess()
                && res.getData().get(0).getEthInterfaceConfigId().equals("b6cf7eb4038049dfac86391421f37b7f"));
    }

    @Test(expected = ServiceException.class)
    public void testQueryVlanInvalidIp() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        VlanROAResource roa = new VlanROAResource();
        roa.queryVlan(null, "111", "123$$", "123$$");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateVlanInvalidIP2() throws ServiceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        VlanROAResource roa = new VlanROAResource();
        roa.updateVlan(null, "111", "123", new ArrayList<>());
    }

}
