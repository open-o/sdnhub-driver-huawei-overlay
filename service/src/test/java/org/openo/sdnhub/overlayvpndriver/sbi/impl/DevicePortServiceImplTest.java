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
import org.openo.sdnhub.overlayvpndriver.controller.model.AcDevicePort;
import org.openo.sdnhub.overlayvpndriver.controller.model.LoopBackPort;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIp;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.List;

public class DevicePortServiceImplTest {

    @Test
    public void queryLoopBackFromControllerSuccess() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                LoopBackPort port = new LoopBackPort();
                port.setId("123");
                port.setLoopbackName("port");
                port.setIpv4Mask("ipv4Mask");
                port.setDhcpMode(true);
                port.setIpv4Address("ipv4Address");
                port.setIpv6Address("ipv6Address");
                port.setPrefixLength("prefixLength");
                List<LoopBackPort> list = new ArrayList<>();
                list.add(port);
                ACResponse<List<LoopBackPort>> response = new ACResponse<>();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<SbiIp> rsp = DevicePortServiceImpl.queryLoopBack("ctrlUuid", "deviceId", "port");
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void traslateDevicePortToIpPortNull() throws ServiceException {

        ResultRsp<SbiIp> rsp = DevicePortServiceImpl.traslateDevicePortToIp(null, "ctrlUuid", "deviceId", "port");
        assertEquals(null, rsp.getData());
    }

    @Test(expected = ServiceException.class)
    public void traslateDevicePortToIpAddressNull() throws ServiceException {

        AcDevicePort port = new AcDevicePort();
        port.setId("123");

        DevicePortServiceImpl.traslateDevicePortToIp(port, "ctrlUuid", "deviceId", "port");
    }

    @Test(expected = ServiceException.class)
    public void queryPortsFailure() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                List<AcDevicePort> list = new ArrayList<>();
                AcDevicePort port = new AcDevicePort();
                port.setId("123");
                port.setPrefixLength("prefixLength");
                list.add(port);
                OverlayVpnDriverResponse<List<AcDevicePort>> response = new OverlayVpnDriverResponse<>();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        List<String> list = new ArrayList<>();
        DevicePortServiceImpl.queryPorts("ctrlUuid", "deviceId", list);
    }

    @Test(expected = ServiceException.class)
    public void queryPortsBodyNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                List<AcDevicePort> list = new ArrayList<>();
                AcDevicePort port = new AcDevicePort();
                port.setId("123");
                port.setPrefixLength("prefixLength");
                list.add(port);
                OverlayVpnDriverResponse<List<AcDevicePort>> response = new OverlayVpnDriverResponse<>();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        List<String> list = new ArrayList<>();
        DevicePortServiceImpl.queryPorts("ctrlUuid", "deviceId", list);
    }

    @Test(expected = ServiceException.class)
    public void queryPortsResponseFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                List<AcDevicePort> list = new ArrayList<>();
                AcDevicePort port = new AcDevicePort();
                port.setId("123");
                port.setPrefixLength("prefixLength");
                list.add(port);
                OverlayVpnDriverResponse<List<AcDevicePort>> response = new OverlayVpnDriverResponse<>();
                response.setData(list);
                response.setErrcode("01");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        List<String> list = new ArrayList<>();
        DevicePortServiceImpl.queryPorts("ctrlUuid", "deviceId", list);
    }

    @Test(expected = ServiceException.class)
    public void queryLoopBackFromPortNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                LoopBackPort port = new LoopBackPort();
                port.setId("123");
                port.setLoopbackName("port");
                port.setIpv4Mask("ipv4Mask");
                port.setDhcpMode(true);
                port.setIpv4Address("ipv4Address");
                port.setIpv6Address("ipv6Address");
                port.setPrefixLength("prefixLength");
                List<LoopBackPort> list = new ArrayList<>();
                list.add(port);
                ACResponse<List<LoopBackPort>> response = new ACResponse<>();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        DevicePortServiceImpl.queryLoopBack("ctrlUuid", "deviceId", null);
    }

    @Test(expected = ServiceException.class)
    public void queryLoopBackFromGetDataFails() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                LoopBackPort port = new LoopBackPort();
                port.setId("123");
                port.setLoopbackName("port");
                port.setIpv4Mask("ipv4Mask");
                port.setDhcpMode(true);
                port.setIpv4Address("ipv4Address");
                port.setIpv6Address("ipv6Address");
                port.setPrefixLength("prefixLength");
                List<LoopBackPort> list = new ArrayList<>();
                list.add(port);
                ACResponse<List<LoopBackPort>> response = new ACResponse<>();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        DevicePortServiceImpl.queryLoopBack("ctrlUuid", "deviceId", "port");
    }

    @Test(expected = ServiceException.class)
    public void queryLoopBackFromIsSuccessFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                LoopBackPort port = new LoopBackPort();
                port.setId("123");
                port.setLoopbackName("port");
                port.setIpv4Mask("ipv4Mask");
                port.setDhcpMode(true);
                port.setIpv4Address("ipv4Address");
                port.setIpv6Address("ipv6Address");
                port.setPrefixLength("prefixLength");
                List<LoopBackPort> list = new ArrayList<>();
                list.add(port);
                ACResponse<List<LoopBackPort>> response = new ACResponse<>();
                response.setData(list);
                response.setErrcode("01");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        DevicePortServiceImpl.queryLoopBack("ctrlUuid", "deviceId", "port");
    }

    @Test
    public void queryLoopBackFromPortNameNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                LoopBackPort port = new LoopBackPort();
                port.setId("123");
                port.setLoopbackName("port");
                port.setIpv4Mask("ipv4Mask");
                port.setDhcpMode(true);
                port.setIpv4Address("ipv4Address");
                port.setIpv6Address("ipv6Address");
                port.setPrefixLength("prefixLength");
                List<LoopBackPort> list = new ArrayList<>();
                list.add(port);
                ACResponse<List<LoopBackPort>> response = new ACResponse<>();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<SbiIp> rsp = DevicePortServiceImpl.queryLoopBack("ctrlUuid", "deviceId", null);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test(expected=ServiceException.class)
    public void queryLoopBackFromDataNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                LoopBackPort port = new LoopBackPort();
                port.setId("123");
                port.setLoopbackName("port");
                port.setIpv4Mask("ipv4Mask");
                port.setDhcpMode(true);
                port.setIpv4Address("ipv4Address");
                port.setIpv6Address("ipv6Address");
                port.setPrefixLength("prefixLength");
                List<LoopBackPort> list = new ArrayList<>();
                list.add(port);
                ACResponse<List<LoopBackPort>> response = new ACResponse<>();
                response.setData(null);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<SbiIp> rsp = DevicePortServiceImpl.queryLoopBack("ctrlUuid", "deviceId", "Port");
       // assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

}
