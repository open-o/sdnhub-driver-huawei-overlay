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
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.InterfaceIpServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiInterfaceIpConfig;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterfaceIpROAResourceTest {

    InterfaceIpROAResource interfaceIpRoaResource = new InterfaceIpROAResource();

    private static final String ctrlUuidParam = "extSysID=81244ad0-b4ea-41ed-969e-d5588b32fd4c";

    private static final String deviceId = "81244ad0-b4ea-41ed-969e-d5588b32fd4a";

    /**
     * <br/>
     *
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {
        try {
            Field field = interfaceIpRoaResource.getClass().getDeclaredField("interfaceIpService");
            field.setAccessible(true);
            InterfaceIpServiceImpl interfaceIpService = new InterfaceIpServiceImpl();
            field.set(interfaceIpRoaResource, interfaceIpService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateInterfaceIpTestNormal() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
                interfaceIpConfig.setInterfaceName("InterfaceOne");
                interfaceIpConfig.setMode("manual");
                interfaceIpConfig.setIpv6Address("manual");
                interfaceIpConfig.setMode6("manual");

                List<SbiInterfaceIpConfig> dataList = new ArrayList<>();
                dataList.add(interfaceIpConfig);
                ACResponse<List<SbiInterfaceIpConfig>> response = new ACResponse<>();
                response.setData(dataList);
                response.setErrcode(ErrorCode.OVERLAYVPN_SUCCESS);
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };
        SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
        interfaceIpConfig.setInterfaceName("InterfaceOne");
        interfaceIpConfig.setMode("manual");
        interfaceIpConfig.setIpv6Address("manual");
        interfaceIpConfig.setMode6("manual");
        List<SbiInterfaceIpConfig> interfaceIpList = Arrays.asList(interfaceIpConfig);
        ResultRsp<List<SbiInterfaceIpConfig>> expected =
                interfaceIpRoaResource.updateInterfaceIp(null, ctrlUuidParam, deviceId, interfaceIpList);
        assertEquals(expected.getErrorCode(), ErrorCode.OVERLAYVPN_SUCCESS);
    }

    @Test(expected = ServiceException.class)
    public void updateInterfaceIpTestNullUuid() throws ServiceException {
        SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
        interfaceIpConfig.setInterfaceName("InterfaceOne");
        interfaceIpConfig.setMode("manual");
        interfaceIpConfig.setIpv6Address("manual");
        List<SbiInterfaceIpConfig> interfaceIpList = Arrays.asList(interfaceIpConfig);
        interfaceIpRoaResource.updateInterfaceIp(null, null, deviceId, interfaceIpList);
    }

    @Test(expected = ServiceException.class)
    public void updateInterfaceIpTestNullDeviceId() throws ServiceException {
        SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
        interfaceIpConfig.setInterfaceName("InterfaceOne");
        interfaceIpConfig.setMode("manual");
        interfaceIpConfig.setMode6("manual");
        interfaceIpConfig.setIpv6Address("manual");
        List<SbiInterfaceIpConfig> interfaceIpList = Arrays.asList(interfaceIpConfig);
        interfaceIpRoaResource.updateInterfaceIp(null, ctrlUuidParam, null, interfaceIpList);
    }

    @Test(expected = ServiceException.class)
    public void updateInterfaceIpTestEmptyBody() throws ServiceException {
        interfaceIpRoaResource.updateInterfaceIp(null, ctrlUuidParam, deviceId, null);
    }

    @Test
    public void updateInterfaceIpTestEmptyResponse() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
                interfaceIpConfig.setInterfaceName("InterfaceOne");
                interfaceIpConfig.setMode("manual");
                interfaceIpConfig.setIpv6Address("manual");

                List<SbiInterfaceIpConfig> dataList = new ArrayList<>();
                dataList.add(interfaceIpConfig);
                ACResponse<List<SbiInterfaceIpConfig>> response = new ACResponse<>();
                response.setData(dataList);
                response.setErrcode(ErrorCode.OVERLAYVPN_SUCCESS);
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(500);
                return msg;
            }
        };
        SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
        interfaceIpConfig.setInterfaceName("InterfaceOne");
        interfaceIpConfig.setMode("manual");
        interfaceIpConfig.setMode6("manual");
        interfaceIpConfig.setIpv6Address("manual");
        List<SbiInterfaceIpConfig> interfaceIpList = Arrays.asList(interfaceIpConfig);
        ResultRsp<List<SbiInterfaceIpConfig>> expected =
                interfaceIpRoaResource.updateInterfaceIp(null, ctrlUuidParam, deviceId, interfaceIpList);
        assertEquals(expected.getErrorCode(), "cloudvpn.failed");
    }

    @Test
    public void queryInterfaceIpTestNormal() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
                interfaceIpConfig.setInterfaceName("InterfaceOne");
                interfaceIpConfig.setMode("manual");
                interfaceIpConfig.setIpv6Address("manual");

                List<SbiInterfaceIpConfig> dataList = new ArrayList<>();
                dataList.add(interfaceIpConfig);
                ACResponse<List<SbiInterfaceIpConfig>> response = new ACResponse<>();
                response.setData(dataList);
                response.setErrcode(ErrorCode.OVERLAYVPN_SUCCESS);
                httpReturnMessage.setBody(JsonUtil.toJson(response));

                return httpReturnMessage;
            }
        };

        ResultRsp<List<SbiInterfaceIpConfig>> result =
                interfaceIpRoaResource.queryInterfaceIp(null, ctrlUuidParam, deviceId);
        assertEquals(result.getErrorCode(), ErrorCode.OVERLAYVPN_SUCCESS);
    }

    @Test(expected = ServiceException.class)
    public void queryInterfaceIpTestNullCtrlUuid() throws ServiceException {

        interfaceIpRoaResource.queryInterfaceIp(null, null, deviceId);
    }

    @Test(expected = ServiceException.class)
    public void queryInterfaceIpTestNullDeviceId() throws ServiceException {

        interfaceIpRoaResource.queryInterfaceIp(null, ctrlUuidParam, null);
    }

    @Test
    public void queryInterfaceIpTestHttp500() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);

                SbiInterfaceIpConfig interfaceIpConfig = new SbiInterfaceIpConfig();
                interfaceIpConfig.setInterfaceName("InterfaceOne");
                interfaceIpConfig.setMode("manual");
                interfaceIpConfig.setIpv6Address("manual");

                List<SbiInterfaceIpConfig> dataList = new ArrayList<>();
                dataList.add(interfaceIpConfig);
                ACResponse<List<SbiInterfaceIpConfig>> response = new ACResponse<>();
                response.setData(dataList);
                response.setErrcode(ErrorCode.OVERLAYVPN_SUCCESS);
                httpReturnMessage.setBody(JsonUtil.toJson(response));

                return httpReturnMessage;
            }
        };

        ResultRsp<List<SbiInterfaceIpConfig>> result =
                interfaceIpRoaResource.queryInterfaceIp(null, ctrlUuidParam, deviceId);
        assertEquals(result.getErrorCode(), "cloudvpn.failed");
    }

}
