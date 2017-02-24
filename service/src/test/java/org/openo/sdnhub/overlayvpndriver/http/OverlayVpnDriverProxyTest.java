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

package org.openo.sdnhub.overlayvpndriver.http;

import static org.junit.Assert.assertEquals;


import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class OverlayVpnDriverProxyTest {

    OverlayVpnDriverProxy proxy = OverlayVpnDriverProxy.getInstance();

    @Test
    public void sendGetMsg() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };

        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendGetMsg("http://test", "Body", "test123");
        assertEquals(404, message.getStatus());
    }

    @Test
    public void sendPostMsg() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };

        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendPostMsg("http://test", "Body", "test123");
        assertEquals(404, message.getStatus());
    }

    @Test
    public void sendPutMsg() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };

        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendPutMsg("http://test", "Body", "test123");
        assertEquals(404, message.getStatus());
    }

    @Test
    public void sendDeleteMsg() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };

        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendDeleteMsg("http://test", "Body", "test123");
        assertEquals(404, message.getStatus());
    }

    @Test
    public void sendGetMsgBodyNull() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };

        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendGetMsg("http://test", null, "test123");
        assertEquals(404, message.getStatus());
    }

    @Test
    public void sendPostMsgBodyNull() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };

        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendPostMsg("http://test", null, "test123");
        assertEquals(404, message.getStatus());
    }

    @Test
    public void sendPutMsgBodyNull() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };
        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendPutMsg("http://test", null, "test123");
        assertEquals(404, message.getStatus());
    }

    @Test
    public void sendDeleteMsgBodyNull() throws ServiceException {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse get(String url, RestfulParametes restParametes)
                    throws ServiceException, MalformedURLException {
                RestfulResponse res = new RestfulResponse();
                res.setStatus(200);
                Map<String, Object> driverInfoMap = new HashMap<>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "12.32.30");
                driverInfoMap.put("url", "http://www.google.com:");
                driverInfoMap.put("userName", "Raju");
                driverInfoMap.put("password", "raju");
                res.setResponseJson(JsonUtil.toJson(driverInfoMap));
                return res;
            }
        };

        HTTPReturnMessage message = OverlayVpnDriverProxy.getInstance().sendDeleteMsg("http://test", null, "test123");
        assertEquals(404, message.getStatus());
    }
}
