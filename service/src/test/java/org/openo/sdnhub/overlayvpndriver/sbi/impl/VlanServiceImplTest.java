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
import org.openo.sdnhub.overlayvpndriver.controller.model.EthInterfaceConfig;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIfVlan;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VlanServiceImplTest {

    private VlanServiceImpl impl = new VlanServiceImpl();

    private static final String CTRL_UUID = "uid1234";

    private static final String DEVICE_ID = "device1234";

    @Test
    public void testBuildIfVlanRsp() throws ServiceException {

        List<SbiIfVlan> res = impl.buildIfVlanRsp(null);
        assertEquals(true, res.isEmpty());

    }

    @Test(expected = ServiceException.class)
    public void testQueryEthByName_InvalidInterface() throws ServiceException {

        impl.queryEthByName(CTRL_UUID, DEVICE_ID, "");
    }

    @Test
    public void testCombineCreateLanEthConfig() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();

                ACResponse<List<EthInterfaceConfig>> response = new ACResponse<>();
                List<EthInterfaceConfig> list = new ArrayList<>();

                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        List<SbiIfVlan> ifVlan = new LinkedList<>();
        SbiIfVlan sbiIfVlan = new SbiIfVlan();
        sbiIfVlan.setIfName("eth1");
        sbiIfVlan.setDefaultVlan(1);
        ifVlan.add(sbiIfVlan);

        List<EthInterfaceConfig> combineCreateLanEthConfig =
                impl.combineCreateLanEthConfig(CTRL_UUID, DEVICE_ID, ifVlan);
        assertEquals("eth1", combineCreateLanEthConfig.get(0).getName());
    }

    @Test(expected = ServiceException.class)
    public void testCombineLanEthConfig_getSingleLanConfig_Empty() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();

                ACResponse<List<EthInterfaceConfig>> response = new ACResponse<>();
                List<EthInterfaceConfig> list = new ArrayList<>();

                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        List<SbiIfVlan> ifVlan = new LinkedList<>();
        SbiIfVlan sbiIfVlan = new SbiIfVlan();
        sbiIfVlan.setIfName("eth1");
        sbiIfVlan.setDefaultVlan(1);
        ifVlan.add(sbiIfVlan);
        impl.combineLanEthConfig(CTRL_UUID, DEVICE_ID, ifVlan);
    }

    @Test
    public void testCombineCreateLanEthConfig_success() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                List<EthInterfaceConfig> list = new ArrayList<>();
                EthInterfaceConfig eth = new EthInterfaceConfig();
                eth.setDescription("des");
                eth.setId("eth1");
                list.add(eth);

                ACResponse<List<EthInterfaceConfig>> response = new ACResponse<>();
                response.setData(list);
                response.setErrcode("0");
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        List<SbiIfVlan> ifVlan = new LinkedList<>();
        SbiIfVlan sbiIfVlan = new SbiIfVlan();
        sbiIfVlan.setIfName("eth1");
        sbiIfVlan.setDefaultVlan(1);
        ifVlan.add(sbiIfVlan);
        List<EthInterfaceConfig> combineLanEthConfig = impl.combineCreateLanEthConfig(CTRL_UUID, DEVICE_ID, ifVlan);
        assertEquals("eth1", combineLanEthConfig.get(0).getId());
    }

    @Test(expected = ServiceException.class)
    public void testQueryEthByIds_ResponseInvalid() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                return msg;
            }
        };

        impl.queryEthByIds(CTRL_UUID, DEVICE_ID, "");
    }

    @Test
    public void testQueryEthByIds_ValidIds() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                List<EthInterfaceConfig> list = new ArrayList<>();
                EthInterfaceConfig eth = new EthInterfaceConfig();
                eth.setDescription("des");
                eth.setId("eth1");
                list.add(eth);

                ACResponse<List<EthInterfaceConfig>> response = new ACResponse<>();
                response.setData(list);
                response.setErrcode("0");

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        List<EthInterfaceConfig> queryEthByIds = impl.queryEthByIds(CTRL_UUID, DEVICE_ID, "id 123");
        assertEquals("eth1", queryEthByIds.get(0).getId());
    }

    @Test
    public void testBuildCreateIfVlanRsp() throws ServiceException {

        List<SbiIfVlan> res = impl.buildCreateIfVlanRsp(null, null);
        assertEquals(true, res.isEmpty());
    }

    @Test
    public void testBuildCreateIfVlanRspSuccess() throws ServiceException {

        List<EthInterfaceConfig> list = new ArrayList<>();
        EthInterfaceConfig ethConfig = new EthInterfaceConfig();
        ethConfig.setName("name");
        ethConfig.setId("id");
        list.add(ethConfig);

        List<SbiIfVlan> listSbi = new ArrayList<>();
        SbiIfVlan sbi = new SbiIfVlan();
        sbi.setIfName("name");
        listSbi.add(sbi);

        List<SbiIfVlan> res = impl.buildCreateIfVlanRsp(list, listSbi);
        assertEquals("id", res.get(0).getEthInterfaceConfigId());
    }
}
