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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiStaticRoute;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.Ip;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeStaticRoute;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;





public class StaticRouteImplTest {

    String queryResJson =
            "{\"errcode\":\"0\",\"errmsg\":null,\"pageIndex\":0,\"pageSize\":0,\"totalRecords\":0,"
            + "\"data\":{\"ipv4\":\"192.168.1.2\",\"ipv6\":\"\",\"ipMask\":\"\","
            + "\"prefixLength\":\"\",\"id\":\"\"},\"success\":[],\"fail\":[],\"sucess\":true}";

    StaticRouteImpl staticRouteImpl = new StaticRouteImpl();
    SbiNeStaticRoute route = new SbiNeStaticRoute();

    /**
     * <br/>
     *
     * @throws Exception setup failure exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() throws Exception {

        route.setUuid("123");
        route.setEnableDhcp("true");
        Ip ip = new Ip();
        ip.setIpv4("");
        ip.setIpMask("3 4");
        //route.setDestIp(JsonUtil.toJson(ip));
        //route.setNextHop(JsonUtil.toJson(ip));
        route.setOutInterface("123");
        route.setDeviceId("111");

        ip.setTypeV4(true);
        ip.setIpMask("24");
        route.setDestIpData(ip);
        //route.setNextHopData(ip);
        route.setExternalId("8206aae22e294b19b08032e1c38b244f");
    }

    @Test
    public void queryRouteByDevice() throws ServiceException {
        String ctrlUuid = "123";

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ControllerNbiStaticRoute nbiStaticRoute = new ControllerNbiStaticRoute();
                nbiStaticRoute.setUuid("123");
                nbiStaticRoute.setNextHop("123");
                nbiStaticRoute.setDhcp(true);
                OverlayVpnDriverResponse<ControllerNbiStaticRoute> response = new OverlayVpnDriverResponse<>();
                response.setErrcode("0");
                response.setData(nbiStaticRoute);
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        String deviceId = "111";
        String destIp = "10.20.10.30";
        String staticRouteId = "staticRouteId";
        ResultRsp<List<ControllerNbiStaticRoute>> rsp =
                staticRouteImpl.queryRouteByDevice(ctrlUuid, deviceId, destIp, staticRouteId);
        assertTrue("overlayvpn.operation.success".equals(rsp.getErrorCode()));

    }

    @Test
    public void configStaticRoute() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ControllerNbiStaticRoute nbiStaticRoute = new ControllerNbiStaticRoute();
                nbiStaticRoute.setUuid("123");
                nbiStaticRoute.setNextHop("123");
                nbiStaticRoute.setDhcp(true);
                OverlayVpnDriverResponse<ControllerNbiStaticRoute> response = new OverlayVpnDriverResponse<>();
                response.setErrcode("0");
                response.setData(nbiStaticRoute);
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        ControllerNbiStaticRoute route = new ControllerNbiStaticRoute();
        route.setUuid("123");
        list.add(route);
        String ctrlUuid = "123";
        String deviceId = "111";
        ResultRsp<List<ControllerNbiStaticRoute>> response =
                staticRouteImpl.configStaticRoute(ctrlUuid, deviceId, list, true);
        assertTrue("overlay.vpn.apater.router.response.fail".equals(response.getErrorCode()));

    }

    @Test(expected = ServiceException.class)
    public void queryRouteByDeviceFailure() throws ServiceException {
        String ctrlUuid = "123";
        String deviceId = "111";
        String destIp = "10.20.10.30";
        String staticRouteId = "staticRouteId";

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>> response =
                        new OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>>();

                List<ControllerNbiStaticRoute> list = new ArrayList<ControllerNbiStaticRoute>();

                ControllerNbiStaticRoute route = new ControllerNbiStaticRoute();
                route.setUuid("123");
                list.add(route);
                response.setData(null);
                response.setErrcode("01");

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        staticRouteImpl.queryRouteByDevice(ctrlUuid, deviceId, destIp, staticRouteId);

    }

    @Test(expected = Exception.class)
    public void checkInputDataIp4MaskException() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        Ip ip = new Ip();
        ip.setIpv4("10.12.13.12");
        ip.setIpMask("");
        ip.setTypeV4(true);
        route.setDestIpData(ip);

        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        staticRouteImpl.checkInputData(routes, null, null);
    }

    @Test(expected = Exception.class)
    public void checkInputDataIp4Exception() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        Ip ip = new Ip();
        ip.setIpv4("");
        ip.setIpMask("24");
        ip.setTypeV4(true);
        route.setDestIpData(ip);

        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        staticRouteImpl.checkInputData(routes, null, null);
    }

    @Test(expected = Exception.class)
    public void checkInputDataIp6Exception() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        Ip ip = new Ip();
        ip.setIpv4("10.12.13.12");
        ip.setIpMask("24");
        ip.setIpv6("");
        ip.setPrefixLength("12");
        ip.setTypeV4(true);
        route.setDestIpData(ip);

        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        staticRouteImpl.checkInputData(routes, null, null);
    }

    @Test(expected = Exception.class)
    public void checkInputDataIp6LengthException() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        Ip ip = new Ip();
        ip.setIpv4("10.12.13.14");
        ip.setIpMask("24");
        ip.setIpv6("::1000");
        ip.setPrefixLength("");
        ip.setTypeV4(true);
        route.setDestIpData(ip);

        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        staticRouteImpl.checkInputData(routes, null, null);
    }

    @Test(expected = Exception.class)
    public void checkInputDataEmptyIpv4AndIpv4MaskException() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        Ip ip = new Ip();
        ip.setIpv4("");
        ip.setIpMask("");
        route.setDestIpData(ip);

        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        staticRouteImpl.checkInputData(routes, null, null);
    }

    @Test(expected = Exception.class)
    public void checkInputDataBothIpv4AndIpv6PresentException() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        Ip ip = new Ip();
        ip.setIpv4("10.12.13.12");
        ip.setIpMask("24");
        ip.setIpv6("::0000");
        ip.setPrefixLength("4");
        ip.setTypeV4(true);
        route.setOutInterface(null);
        route.setDestIpData(ip);

        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        staticRouteImpl.checkInputData(routes, null, null);
    }

    @Test(expected = Exception.class)
    public void checkInputDataNullOutInteraceAndNextHopException() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        Ip ip = new Ip();
        ip.setIpv4("");
        ip.setIpMask("");
        ip.setIpv6("::0000");
        ip.setPrefixLength("6");
        route.setOutInterface(null);
        route.setDestIpData(ip);

        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        staticRouteImpl.checkInputData(routes, null, null);
    }
}
