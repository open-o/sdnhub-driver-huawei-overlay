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
import mockit.integration.junit4.JMockit;

import org.apache.commons.httpclient.HttpException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiStaticRoute;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.StaticRouteImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.Ip;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeStaticRoute;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.testframework.util.file.JsonUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RunWith(JMockit.class)
public class StaticRouteROAResourceTest {

    StaticRouteROAResource roa = new StaticRouteROAResource();

    String queryResJson = "{\"errcode\": \"0\",\"errmsg\":" + " \"\",\"data\": " + "[{\"id\":"
            + "\"8206aae22e294b19b08032e1c38b244f\",\"ip\":\"119.131.141.12\","
            + "\"mask\": \"255.255.255.254\",\"vpnId\": null,\"vpnName\":"
            + " null, \"nextHop\": \"4.5.6.6\",\"outInterface\": \"sdf\","
            + "\"priority\": 0,\"description\": \"description1\","
            + "\"dhcp\": false,\"bfdName\": null,\"nqaId\": null},{\"id\": "
            + "\"c85daf135a7d45f59392bf0440799dd5\",\"ip\": \"119.131.141.12\"," + "\"mask\": \"255.255.255.254\","
            + "\"vpnId\": null,\"vpnName\": null,\"nextHop\": \"4.5.6.6\","
            + "\"outInterface\": \"s\",\"priority\": 0,\"description\": \"description1\",\"dhcp\": false,"
            + "\"bfdName\": null,\"nqaId\": null}]}";

    /**
     * <br/>
     *
     * @throws Exception setup failure exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() throws Exception {
        StaticRouteImpl service = new StaticRouteImpl();
        Class<?> clazz = StaticRouteROAResource.class;

        Object cc = clazz.newInstance();

        Field f1 = cc.getClass().getDeclaredField("staticRouteService");
        f1.setAccessible(true);
        f1.set(cc, service);
        roa = (StaticRouteROAResource)cc;
    }

    @Test
    public void testQueryRoutes() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

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

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        routes.add(route);
        ResultRsp<SbiNeStaticRoute> result = roa.queryRoutes("123", routes);

        assertEquals(200, result.getHttpCode());
        assertEquals("123", result.getSuccessed().get(0).getNextHop());

    }

    @Test(expected = ServiceException.class)
    public void testQueryRoutesFailed() throws ServiceException, HttpException {

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("362656");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("213546");
        route.setExternalId("154654");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        roa.queryRoutes(null, routes);

    }

    @Test(expected = Exception.class)
    public void testQueryRoutesEmptyController() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("");
                msg.setStatus(200);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        routes.add(route);
        ResultRsp<SbiNeStaticRoute> result = roa.queryRoutes("123", routes);

        assertEquals(200, result.getHttpCode());
        assertEquals("4.5.6.6", result.getData().getNextHop());

    }

    @Test
    public void testQueryRoutesInvalidresponse() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("");
                msg.setStatus(200);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        routes.add(route);
        ResultRsp<SbiNeStaticRoute> result = roa.queryRoutes("123", routes);
        assertEquals("overlayvpn.operation.failed", result.getErrorCode());
    }

    @Test(expected = Exception.class)
    public void testQueryRoutesInvalidresponsefromcontroller() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("invalid");
                msg.setStatus(200);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        routes.add(route);
        roa.queryRoutes("123", routes);
    }

    @Test(expected = ServiceException.class)
    public void testQueryRoutes_emptylist() throws ServiceException {
        roa.queryRoutes("123", null);
    }

    @Test
    public void testCreateRoutes() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(null);
                msg.setStatus(500);
                return msg;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(queryResJson);
                msg.setStatus(200);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        Ip ip = new Ip();
        ip.setTypeV4(true);
        ip.setIpMask("24");
        route.setDestIpData(ip);
        route.setNextHopData(ip);
        route.setExternalId("123");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        ResultRsp<SbiNeStaticRoute> result = roa.createRoute("123", routes);
        assertEquals(200, result.getHttpCode());
    }

    @Test
    public void testCreateRoutesEmptybody() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(null);
                msg.setStatus(500);
                return msg;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("");
                msg.setStatus(200);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        Ip ip = new Ip();
        ip.setTypeV4(true);
        ip.setIpMask("24");
        route.setDestIpData(ip);
        route.setNextHopData(ip);
        route.setExternalId("123");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        ResultRsp<SbiNeStaticRoute> result = roa.createRoute("123", routes);
        assertEquals("overlayvpn.operation.failed", result.getErrorCode());
    }

    @Test(expected = Exception.class)
    public void testCreateRoutesInvalidjsonbody() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(null);
                msg.setStatus(500);
                return msg;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("invalid");
                msg.setStatus(200);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        roa.createRoute("123", routes);
    }

    @Test(expected = ServiceException.class)
    public void testCreateRoutesInvalidInput2() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        StaticRouteROAResource roa = new StaticRouteROAResource();
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        roa.createRoute("123", routes);
    }

    @Test(expected = ServiceException.class)
    public void testCreateRoutes_invalidInput() throws ServiceException, HttpException {

        StaticRouteROAResource roa = new StaticRouteROAResource();
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        roa.createRoute("123", routes);
    }

    @Test(expected = ServiceException.class)
    public void testCreateRoutesFailed() throws ServiceException, HttpException {

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("362656");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("213546");
        route.setExternalId("154654");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        roa.createRoute(null, routes);

    }

    @Test
    public void testDeleteRoutes() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(queryResJson);
                msg.setStatus(200);
                return msg;
            }
        };

        List<String> routes = new ArrayList<>();
        routes.add("1");
        List<SbiNeStaticRoute> routeList = new ArrayList<SbiNeStaticRoute>();
        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("12345");
        routeList.add(route);
        ResultRsp<String> result = roa.deleteRoute("123", "111", routeList);
        assertEquals(200, result.getHttpCode());
    }

    @Test
    public void testDeleteRoutesEmptyjsonbody() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("");
                msg.setStatus(200);
                return msg;
            }
        };

        List<String> routes = new ArrayList<>();
        routes.add("1");
        List<SbiNeStaticRoute> routeList = new ArrayList<SbiNeStaticRoute>();
        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("12345");
        routeList.add(route);
        ResultRsp<String> result = roa.deleteRoute("123", "111", routeList);
        assertEquals("overlayvpn.operation.failed", result.getErrorCode());
    }

    @Test(expected = Exception.class)
    public void testDeleteRoutesInvalidjsonbody() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("invalid");
                msg.setStatus(200);
                return msg;
            }
        };

        List<String> routes = new ArrayList<>();
        routes.add("1");
        List<SbiNeStaticRoute> routeList = new ArrayList<SbiNeStaticRoute>();
        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("12345");
        routeList.add(route);
        roa.deleteRoute("123", "111", routeList);

    }

    @Test(expected = ServiceException.class)
    public void testDeleteRoutesEmptylist() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        List<SbiNeStaticRoute> routeList = new ArrayList<SbiNeStaticRoute>();
        roa.deleteRoute("123", "111", routeList);

    }

    @Test(expected = ServiceException.class)
    public void testDeleteRoutesFailed() throws ServiceException, HttpException {

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("362656");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("213546");
        route.setExternalId("154654");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);

        roa.deleteRoute(null, "111", routes);

    }

    @Test
    public void testUpdateRoutes() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(queryResJson);
                msg.setStatus(200);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        Ip ip = new Ip();
        ip.setTypeV4(true);
        ip.setIpMask("24");
        route.setDestIpData(ip);
        route.setNextHopData(ip);
        route.setExternalId("123");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        ResultRsp<SbiNeStaticRoute> result = roa.updateRoute("123", routes);

        assertEquals(200, result.getHttpCode());
        assertEquals("123", result.getSuccessed().get(0).getNextHop());

    }

    @Test(expected = ServiceException.class)
    public void testUpdateRoutesFailed() throws ServiceException {

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("362656");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("213546");
        route.setExternalId("154654");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);

        roa.updateRoute(null, routes);

    }

    @Test
    public void testUpdateRoutesFail() throws ServiceException, HttpException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {

            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(null);
                msg.setStatus(500);
                return msg;
            }
        };

        SbiNeStaticRoute route = new SbiNeStaticRoute();
        route.setUuid("123");
        route.setEnableDhcp("true");
        route.setDestIp("10.20.10.30");
        route.setNextHop("123");
        route.setOutInterface("123");
        route.setDeviceId("111");
        Ip ip = new Ip();
        ip.setTypeV4(true);
        ip.setIpMask("24");
        route.setDestIpData(ip);
        route.setNextHopData(ip);
        route.setExternalId("123");
        List<SbiNeStaticRoute> routes = new ArrayList<>();
        routes.add(route);
        ResultRsp<SbiNeStaticRoute> result = roa.updateRoute("123", routes);
        assertEquals("overlayvpn.operation.success", result.getErrorCode());
    }

}
