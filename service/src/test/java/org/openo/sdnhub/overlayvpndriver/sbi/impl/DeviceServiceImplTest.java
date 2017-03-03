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
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.AdapterDeviceInfo;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.List;

public class DeviceServiceImplTest {

    DeviceServiceImpl dsi = new DeviceServiceImpl();

    @Test
    public void testQueryDevices() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<List<AdapterDeviceInfo>> resp = dsi.queryDevices("12345ctrluuid", "jkbfa");
        assertEquals("overlayvpn.operation.success", resp.getErrorCode());
    }

    @Test
    public void testQueryDevicesFailed() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<List<AdapterDeviceInfo>> resp = dsi.queryDevices("12345ctrluuid", "jkbfa");

        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());
    }

    @Test
    public void testQueryDevicesFailed1() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        ResultRsp<List<AdapterDeviceInfo>> resp = dsi.queryDevices("12345ctrluuid", "jkbfa");
        resp.getSuccessed();
        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());
    }

    @Test
    public void testQueryDevicesFailed12() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(null));
                return msg;
            }
        };

        ResultRsp<List<AdapterDeviceInfo>> resp = dsi.queryDevices("12345ctrluuid", "jkbfa");
        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDevices() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        dsi.createDevices("12345ctrluuid", "jkbfa");

    }

    @Test
    public void testCreateDevices12() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(null));
                return msg;
            }
        };

        ResultRsp<AdapterDeviceInfo> resp = dsi.createDevices("12345ctrluuid", "jkbfa");
        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());

    }

    @Test
    public void testCreateDevicesFailed() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("Success");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<AdapterDeviceInfo> resp = dsi.createDevices("12345ctrluuid", "jkbfa");
        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());
    }

    @Test
    public void testCreateDevicesfailed1() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        ResultRsp<AdapterDeviceInfo> resp = dsi.createDevices("12345ctrluuid", "jkbfa");
        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());
    }

    @Test
    public void testModifyDevice() throws ServiceException {

        AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<String> resp = dsi.modifyDevice("1234kowed", "1234deviceId", deviceInfo);
        assertEquals("overlayvpn.operation.success", resp.getErrorCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testModifyDeviceFailed() throws ServiceException {

        AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        dsi.modifyDevice("1234kowed", "1234deviceId", deviceInfo);

    }

    @Test(expected = NullPointerException.class)
    public void testModifyDeviceFailed1() throws ServiceException {

        AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();

        deviceInfo.setCreator("Raju");

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("1");
                msg.setBody(null);
                return msg;
            }
        };

        dsi.modifyDevice("1234kowed", "1234deviceId", deviceInfo);
    }

    @Test
    public void testModifyDeviceFailed12() throws ServiceException {

        AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();

        deviceInfo.setCreator("Raju");

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("-1");
                msg.setBody(JsonUtil.toJson(null));
                return msg;
            }
        };

        ResultRsp<String> resp = dsi.modifyDevice("1234kowed", "1234deviceId", deviceInfo);
        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());
    }

    @Test
    public void testDeleteDevice() throws ServiceException {

        List<String> deviceIdList = new ArrayList<>();

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId(null);

                deviceInfoList.add(deviceInfo);
                response.setData(null);
                response.setErrcode(null);
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<String> resp = dsi.deleteDevice("1235jnfiv", deviceIdList);
        assertEquals("overlayvpn.operation.success", resp.getErrorCode());
    }

    @Test
    public void testDeleteDeviceFailed() throws ServiceException {

        List<String> deviceIdList = new ArrayList<>();

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<String> resp = dsi.deleteDevice("1235jnfiv", deviceIdList);

        assertEquals("overlayvpn.operation.fail", resp.getErrorCode());

    }

    @Test
    public void testDeleteDeviceFailed1() throws ServiceException {

        List<String> deviceIdList = new ArrayList<>();

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        ResultRsp<String> response = dsi.deleteDevice("1235jnfiv", deviceIdList);

        assertEquals("overlayvpn.operation.fail", response.getErrorCode());
    }

    @Test
    public void testDeleteDeviceFailed12() throws ServiceException {
        List<String> deviceIdList = new ArrayList<>();
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<AdapterDeviceInfo>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(null));
                return msg;
            }
        };
        ResultRsp<String> response = dsi.deleteDevice("1235jnfiv", deviceIdList);
        assertEquals("overlayvpn.operation.fail", response.getErrorCode());
    }

}
