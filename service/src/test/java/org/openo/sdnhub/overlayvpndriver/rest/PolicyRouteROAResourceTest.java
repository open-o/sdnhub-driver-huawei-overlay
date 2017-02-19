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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiPolicyRoute;
import org.openo.sdnhub.overlayvpndriver.controller.model.InterfaceList;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficInterface;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficPolicyList;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.ConfigCommonResult;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.PolicyRouteImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNePolicyRoute;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class PolicyRouteROAResourceTest {

    private static final String CTRL_UUID = "1234567";

    PolicyRouteROAResource policyRouteRoaResource = new PolicyRouteROAResource();

    List<SbiNePolicyRoute> sbiNePolicyRouteList = new LinkedList<SbiNePolicyRoute>();

    SbiNePolicyRoute sbiNePolicyRoute = new SbiNePolicyRoute();

    @Before
    public void setup() throws Exception {

        sbiNePolicyRoute.setActiveStatus("active");
        sbiNePolicyRoute.setAdditionalInfo("additionalInfo");
        sbiNePolicyRoute.setCreatetime((long)1236549);
        sbiNePolicyRoute.setControllerId("controllerId");
        sbiNePolicyRoute.setDescription("description");
        ;
        sbiNePolicyRoute.setDeployStatus("deployStatus");
        sbiNePolicyRoute.setDeviceId("device12345");
        sbiNePolicyRoute.setExternalId("ExternalId1111");

        sbiNePolicyRoute.setFilterAction("self-traffic-filter");
        sbiNePolicyRoute.setInterfaceName("eth1");
        sbiNePolicyRoute.setName("self-Policy-Route");
        sbiNePolicyRoute.setNbiNeRouteId("Route12345");
        sbiNePolicyRoute.setOperationStatus("Active");
        sbiNePolicyRoute.setRunningStatus("Running");
        sbiNePolicyRoute.setTenantId("token-1234-6789");
        sbiNePolicyRoute.setTrafficPolicyName("self-traffic-policy-1");
        sbiNePolicyRoute.setUpdatetime((long)13548);
        sbiNePolicyRoute.setUuid("uid-1234");

        sbiNePolicyRouteList.add(sbiNePolicyRoute);

        // Setting the impl service obj using reflection
        Field field = policyRouteRoaResource.getClass().getDeclaredField("policyRouteService");
        field.setAccessible(true);
        field.set(policyRouteRoaResource, new PolicyRouteImpl());
    }

    @Test
    public void testRouteCreate() throws WebApplicationException, ServiceException {

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

                OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>> response =
                        new OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>>();
                Map<String, List<TrafficPolicyList>> trafficPolicyMap = new HashMap<String, List<TrafficPolicyList>>();

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                trafficPolicyList.add(trafficPolicy);
                trafficPolicyMap.put("trafficPolicyList", trafficPolicyList);
                response.setData(trafficPolicyMap);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeCreate =
                policyRouteRoaResource.routeCreate(null, CTRL_UUID, sbiNePolicyRouteList);
        List<SbiNePolicyRoute> successed = routeCreate.getSuccessed();
        assertEquals("success", routeCreate.getErrorCode());
        assertEquals("self-traffic-policy-1", successed.get(0).getTrafficPolicyName());
    }

    @Test
    public void testRouteCreate_FailWithSuccessHTTPStatus() throws WebApplicationException, ServiceException {

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

                OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>> response =
                        new OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>>();
                Map<String, List<TrafficPolicyList>> trafficPolicyMap = new HashMap<String, List<TrafficPolicyList>>();

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                trafficPolicyList.add(trafficPolicy);
                trafficPolicyMap.put("trafficPolicyList", trafficPolicyList);
                response.setData(trafficPolicyMap);
                response.setErrcode("failure");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeCreate =
                policyRouteRoaResource.routeCreate(null, CTRL_UUID, sbiNePolicyRouteList);
        List<FailData<SbiNePolicyRoute>> failData = routeCreate.getFail();
        assertEquals("success", routeCreate.getErrorCode()); // Source code needs to be modified/
        assertEquals("self-traffic-policy-1", failData.get(0).getData().getTrafficPolicyName());
    }

    @Test
    public void testRouteCreate_FailWithFailureHTTPStatus() throws WebApplicationException, ServiceException {

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
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeCreate =
                policyRouteRoaResource.routeCreate(null, CTRL_UUID, sbiNePolicyRouteList);
        List<FailData<SbiNePolicyRoute>> failData = routeCreate.getFail();
        assertEquals("success", routeCreate.getErrorCode()); // Source code needs to be modified/
    }

    @Test(expected = ServiceException.class)
    public void testRouteCreate_EmptyInput() throws ServiceException {

        policyRouteRoaResource.routeCreate(null, CTRL_UUID, null);

    }

    @Test
    public void testRouteUpdate() throws WebApplicationException, ServiceException {

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

                OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>> response =
                        new OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>>();
                Map<String, List<TrafficPolicyList>> trafficPolicyMap = new HashMap<String, List<TrafficPolicyList>>();

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                trafficPolicyList.add(trafficPolicy);
                trafficPolicyMap.put("trafficPolicyList", trafficPolicyList);
                response.setData(trafficPolicyMap);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeUpdate =
                policyRouteRoaResource.routeUpdate(null, CTRL_UUID, sbiNePolicyRouteList);
        List<SbiNePolicyRoute> successed = routeUpdate.getSuccessed();
        assertEquals("success", routeUpdate.getErrorCode());
        assertEquals("self-traffic-policy-1", successed.get(0).getTrafficPolicyName());
    }

    @Test
    public void testRouteUpdate_FailWithSuccessHTTPStatus() throws WebApplicationException, ServiceException {

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

                OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>> response =
                        new OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>>();
                Map<String, List<TrafficPolicyList>> trafficPolicyMap = new HashMap<String, List<TrafficPolicyList>>();

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                trafficPolicyList.add(trafficPolicy);
                trafficPolicyMap.put("trafficPolicyList", trafficPolicyList);
                response.setData(trafficPolicyMap);
                response.setErrcode("failure");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeUpdate =
                policyRouteRoaResource.routeUpdate(null, CTRL_UUID, sbiNePolicyRouteList);
        List<FailData<SbiNePolicyRoute>> failData = routeUpdate.getFail();
        assertEquals("success", routeUpdate.getErrorCode()); // Source code needs to be modified/
        assertEquals("self-traffic-policy-1", failData.get(0).getData().getTrafficPolicyName());
    }
    

    @Test
    public void testRouteQuery() throws WebApplicationException, ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                OverlayVpnDriverResponse<ControllerNbiPolicyRoute> response =
                        new OverlayVpnDriverResponse<>();
                
                ControllerNbiPolicyRoute ControllerNbiPolicyRoute = new ControllerNbiPolicyRoute();
                

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                trafficPolicyList.add(trafficPolicy);
                ControllerNbiPolicyRoute.setTrafficPolicyList(trafficPolicyList);
                response.setData(ControllerNbiPolicyRoute);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeQuery =
                policyRouteRoaResource.routeQuery(null, CTRL_UUID, sbiNePolicyRouteList);
        assertEquals("success", routeQuery.getErrorCode());       
    }

    @Test(expected = Exception.class)
    public void testRouteQuery_FailWithIllegalArgument() throws WebApplicationException, ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                OverlayVpnDriverResponse<List<TrafficPolicyList>> response =
                        new OverlayVpnDriverResponse<List<TrafficPolicyList>>();

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                trafficPolicyList.add(trafficPolicy);
                response.setData(trafficPolicyList);
                response.setErrcode("failure");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeQuery =
                policyRouteRoaResource.routeQuery(null, CTRL_UUID, sbiNePolicyRouteList);
        routeQuery.getFail();
    }

    @Test(expected = Exception.class)
    public void testRouteQueryExceptionInParameter() throws WebApplicationException, ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                OverlayVpnDriverResponse<List<TrafficPolicyList>> response =
                        new OverlayVpnDriverResponse<List<TrafficPolicyList>>();

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                trafficPolicyList.add(trafficPolicy);
                response.setData(trafficPolicyList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeQuery =
                policyRouteRoaResource.routeQuery(null, CTRL_UUID, sbiNePolicyRouteList);
        routeQuery.getSuccessed();
    }

    @Test
    public void testRouteQuery_FailWithFailureHTTPStatus() throws WebApplicationException, ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                return msg;
            }
        };
        ResultRsp<SbiNePolicyRoute> routeQuery =
                policyRouteRoaResource.routeQuery(null, CTRL_UUID, sbiNePolicyRouteList);
        
        assertEquals("success", routeQuery.getErrorCode()); // Source code needs to be modified/
    }

    @Test
    public void testRouteBatchDelete() throws WebApplicationException, ServiceException {

        List<String> routeIds = new ArrayList<String>();
        routeIds.add("routeId1");
        routeIds.add("routeId2");
        routeIds.add("routeId3");

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

                ACDelResponse acDelResponse = new ACDelResponse();
                List<ConfigCommonResult> configCommonResultList = new LinkedList<ConfigCommonResult>();
                ConfigCommonResult configCommonResult = new ConfigCommonResult();
                configCommonResult.setId("ExternalId1111");
                configCommonResultList.add(configCommonResult);
                acDelResponse.setSuccess(configCommonResultList);
                acDelResponse.setErrocode("0");
                msg.setBody(JsonUtil.toJson(acDelResponse));

                return msg;
            }
        };

        ResultRsp<String> resultRsp = policyRouteRoaResource.routeBatchDelete(null, CTRL_UUID, "device12345", routeIds);
        assertEquals("overlayvpn.operation.success", resultRsp.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void testRouteBatchDelete_EmptyDeviceInput() throws ServiceException {

        policyRouteRoaResource.routeBatchDelete(null, CTRL_UUID, "device6789", null);
    }

    @Test(expected = ServiceException.class)
    public void testRouteBatchDelete_EmptyRouteInput() throws ServiceException {

        List<String> routeIds = new ArrayList<String>();

        policyRouteRoaResource.routeBatchDelete(null, CTRL_UUID, null, routeIds);
    }

    @Test(expected = ServiceException.class)
    public void testRouteBatchDelete_EmptyUUIDInput() throws ServiceException {

        List<String> routeIds = new ArrayList<String>();
        routeIds.add("routeId1");
        routeIds.add("routeId2");
        routeIds.add("routeId3");

        policyRouteRoaResource.routeBatchDelete(null, null, "device12345", routeIds);
    }

    @Test
    public void testRouteBatchDelete_getFail() throws WebApplicationException, ServiceException {

        List<String> routeIds = new ArrayList<String>();
        routeIds.add("routeId1");
        routeIds.add("routeId2");
        routeIds.add("routeId3");

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
                return msg;
            }
        };

        ResultRsp<String> resultRsp = policyRouteRoaResource.routeBatchDelete(null, CTRL_UUID, "device6789", routeIds);
        assertEquals("delete qos policy: httpMsg return error", resultRsp.getMessage());
    }

    @Test
    public void testRouteBatchDeleteFailure() throws WebApplicationException, ServiceException {

        List<String> routeIds = new ArrayList<String>();
        routeIds.add("routeId1");
        routeIds.add("routeId2");
        routeIds.add("routeId3");

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

                ACDelResponse acDelResponse = new ACDelResponse();
                List<ConfigCommonResult> configCommonResultList = new LinkedList<ConfigCommonResult>();
                ConfigCommonResult configCommonResult = new ConfigCommonResult();
                configCommonResult.setId("ExternalId1111");
                configCommonResultList.add(configCommonResult);
                acDelResponse.setSuccess(configCommonResultList);
                acDelResponse.setErrmsg("Unable to delete route.");
                msg.setBody(JsonUtil.toJson(acDelResponse));

                return msg;
            }
        };

        ResultRsp<String> resultRsp = policyRouteRoaResource.routeBatchDelete(null, CTRL_UUID, "device6789", routeIds);
        assertEquals("Unable to delete route.", resultRsp.getMessage());
    }

}
