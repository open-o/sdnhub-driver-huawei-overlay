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

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.DeviceServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.AdapterDeviceCreateBasicInfo;
import org.openo.sdnhub.overlayvpndriver.service.model.AdapterDeviceInfo;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceROAResourceTest {

    private static final String CTRL_UUID = "extSysID=81244ad0-b4ea-41ed-969e-d5588b32fd4c";

    DeviceROAResource resource = null;

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() throws Exception {
        DeviceServiceImpl service = new DeviceServiceImpl();
        Class<?> clazz = DeviceROAResource.class;

        Object cc = clazz.newInstance();

        Field f1 = cc.getClass().getDeclaredField("deviceService");
        f1.setAccessible(true);
        f1.set(cc, service);
        resource = (DeviceROAResource)cc;
    }

    @Test
    public void queryDeviceByEsnTestSuccess() throws ServiceException {
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
        ResultRsp<List<AdapterDeviceInfo>> resp = resource.queryDeviceByEsn(CTRL_UUID, "123");
        assertTrue("success".equals(resp.getErrorCode())
                && resp.getData().get(0).getId().equals("81244ad0-b4ea-41ed-969e-d5588b32fd4c"));
    }

    @Test
    public void queryDeviceByEsnTestFailHttp500() throws ServiceException {
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
                response.setErrcode("overlayvpn.operation.success");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<List<AdapterDeviceInfo>> resp = resource.queryDeviceByEsn(CTRL_UUID, "123");
        assertTrue("cloudvpn.failed".equals(resp.getErrorCode()));
    }

    @Test
    public void queryDeviceByEsnTestFailNullBody() throws ServiceException {
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
                response.setErrcode("overlayvpn.operation.success");
                msg.setBody(null);
                return msg;
            }
        };
        ResultRsp<List<AdapterDeviceInfo>> resp = resource.queryDeviceByEsn(CTRL_UUID, "123");
        assertTrue("cloudvpn.failed".equals(resp.getErrorCode()));
    }

    @Test
    public void queryDeviceByEsnTestFailResponse() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<List<AdapterDeviceInfo>> response = new ACResponse<List<AdapterDeviceInfo>>();

                List<AdapterDeviceInfo> deviceInfoList = new ArrayList<>();
                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                deviceInfoList.add(deviceInfo);
                response.setData(deviceInfoList);
                response.setErrcode("-1");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<List<AdapterDeviceInfo>> resp = resource.queryDeviceByEsn(CTRL_UUID, "123");
        assertTrue("cloudvpn.failed".equals(resp.getErrorCode()));

    }

    @Test(expected = ServiceException.class)
    public void queryDeviceByEsnTestNullUuid() throws ServiceException {
        resource.queryDeviceByEsn(null, "123");
    }

    @Test
    public void createDevicesTestSuccess() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<AdapterDeviceInfo> response = new ACResponse<AdapterDeviceInfo>();

                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");

                List<AdapterDeviceInfo> adapterDeviceList = new ArrayList<>();
                adapterDeviceList.add(deviceInfo);
                response.setSuccess(adapterDeviceList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        AdapterDeviceCreateBasicInfo adapterCreateInfo = new AdapterDeviceCreateBasicInfo();
        adapterCreateInfo.setName("testdevice");
        adapterCreateInfo.setEsn("aaaaaaaaaabbbbbbbbbb");
        adapterCreateInfo.setOrgnizationName("huawei");
        adapterCreateInfo.setDescription("test device");

        List<AdapterDeviceCreateBasicInfo> adevCrtInfos = new ArrayList<>();
        adevCrtInfos.add(adapterCreateInfo);
        ResultRsp<AdapterDeviceInfo> resp = resource.createDevices(CTRL_UUID, adevCrtInfos);

        assertEquals("success", resp.getErrorCode());
        assertEquals(resp.getSuccessed().get(0).getId(), "81244ad0-b4ea-41ed-969e-d5588b32fd4c");
    }

    @Test(expected = ServiceException.class)
    public void createDevicesTestEmptyDeviceList() throws ServiceException {
        List<AdapterDeviceCreateBasicInfo> adevCrtInfos = new ArrayList<AdapterDeviceCreateBasicInfo>();
        resource.createDevices(CTRL_UUID, adevCrtInfos);
    }

    @Test(expected = ServiceException.class)
    public void createDevicesTestSuccessNullUuid() throws ServiceException {
        AdapterDeviceCreateBasicInfo adapterCreateInfo = new AdapterDeviceCreateBasicInfo();
        adapterCreateInfo.setName("testdevice");
        adapterCreateInfo.setEsn("aaaaaaaaaabbbbbbbbbb");
        adapterCreateInfo.setOrgnizationName("huawei");
        adapterCreateInfo.setDescription("test device");
        List<AdapterDeviceCreateBasicInfo> adevCrtInfos = new ArrayList<>();
        adevCrtInfos.add(adapterCreateInfo);
        resource.createDevices(null, adevCrtInfos);
    }

    @Test
    public void modifyDeviceTestSuccess() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<AdapterDeviceInfo> response = new ACResponse<AdapterDeviceInfo>();

                AdapterDeviceInfo deviceInfo = new AdapterDeviceInfo();
                deviceInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");
                response.setData(deviceInfo);
                response.setErrcode("overlayvpn.operation.success");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        AdapterDeviceInfo adapterDevInfo = new AdapterDeviceInfo();

        adapterDevInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");
        ResultRsp<String> resp = resource.modifyDevice(CTRL_UUID, "12345", adapterDevInfo);

        assertTrue("overlayvpn.operation.success".equals(resp.getErrorCode()));
    }

    @Test
    public void modifyDeviceTestFailControllerResponse() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                Map<String, String> errMap = new HashMap<>();
                errMap.put("errorcode", DriverErrorCode.ADAPTER_FAILED);
                msg.setBody(JsonUtil.toJson(errMap));
                return msg;
            }
        };
        AdapterDeviceInfo adapterDevInfo = new AdapterDeviceInfo();

        adapterDevInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");
        ResultRsp<String> resp = resource.modifyDevice(CTRL_UUID, "12345", adapterDevInfo);

        String failData = resp.getFail().get(0).getErrcode();

        assertTrue("cloudvpn.adapter.failed".equals(failData));
    }

    @Test(expected = ServiceException.class)
    public void modifyDeviceTestNullDeviceInfo() throws ServiceException {
        resource.modifyDevice(CTRL_UUID, "12345", null);
    }

    @Test(expected = ServiceException.class)
    public void modifyDeviceTestNullDeviceId() throws ServiceException {
        AdapterDeviceInfo adapterDevInfo = new AdapterDeviceInfo();

        adapterDevInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");
        resource.modifyDevice(CTRL_UUID, null, adapterDevInfo);
    }

    @Test(expected = ServiceException.class)
    public void modifyDeviceTestNullUuid() throws ServiceException {
        AdapterDeviceInfo adapterDevInfo = new AdapterDeviceInfo();
        adapterDevInfo.setId("81244ad0-b4ea-41ed-969e-d5588b32fd4c");
        resource.modifyDevice(null, "12345", adapterDevInfo);
    }

    @Test
    public void deleteDevicesTestSuccess() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                ACResponse<String> response = new ACResponse<String>();
                Map<String, String> resultMap = new HashMap<String, String>();
                resultMap.put("result", "success");
                response.setErrcode("0");
                response.setData(JsonUtil.toJson(resultMap));
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        List<String> idList = new ArrayList<>();
        idList.add("12345");
        ResultRsp<String> resp = resource.deleteDevices(idList, CTRL_UUID);
        assertTrue("0".equals(resp.getErrorCode()));
    }

    @Test
    public void deleteDevicesTestControllerResponseFail() throws ServiceException {
        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                Map<String, String> errMap = new HashMap<>();
                errMap.put("errorcode", DriverErrorCode.ADAPTER_FAILED);
                msg.setBody(JsonUtil.toJson(errMap));
                return msg;
            }
        };
        List<String> idList = new ArrayList<>();
        idList.add("12345");
        ResultRsp<String> resp = resource.deleteDevices(idList, CTRL_UUID);
        assertTrue("500".equals(resp.getErrorCode()));
    }

    @Test(expected = ServiceException.class)
    public void deleteDevicesTestNullDeviceId() throws ServiceException {
        resource.deleteDevices(null, CTRL_UUID);
    }

    @Test(expected = ServiceException.class)
    public void deleteDevicesTestNullUuid() throws ServiceException {
        resource.deleteDevices(null, "12345");
    }
}
