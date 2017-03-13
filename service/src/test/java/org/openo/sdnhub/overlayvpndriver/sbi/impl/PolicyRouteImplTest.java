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
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficInterface;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficPolicyList;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.ConfigCommonResult;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNePolicyRoute;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PolicyRouteImplTest {

    @Test
    public void configMqcCrtlNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        List<TrafficPolicyList> list = new ArrayList<>();
        TrafficPolicyList policy = new TrafficPolicyList();
        policy.setId("123");
        list.add(policy);
        ResultRsp<List<TrafficPolicyList>> rsp = impl.configMqc(null, "deviceid", list);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }

    @Test
    public void configMqcDeviceIdNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        List<TrafficPolicyList> list = new ArrayList<>();
        TrafficPolicyList policy = new TrafficPolicyList();
        policy.setId("123");
        list.add(policy);
        ResultRsp<List<TrafficPolicyList>> rsp = impl.configMqc("CtrlId", null, list);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }

    @Test
    public void configMqcListNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        ResultRsp<List<TrafficPolicyList>> rsp = impl.configMqc("CtrlId", "DeviceId", null);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }

    @Test
    public void configMqcStatusFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("ExternalId1111");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);

                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                trafficPolicyList.add(trafficPolicy);

                Map<String, List<TrafficPolicyList>> trafficPolicyMap = new HashMap<String, List<TrafficPolicyList>>();
                trafficPolicyMap.put("trafficPolicyList", trafficPolicyList);
                OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>> response =
                        new OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>>();
                response.setData(trafficPolicyMap);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        PolicyRouteImpl impl = new PolicyRouteImpl();
        List<TrafficPolicyList> list = new ArrayList<>();
        TrafficPolicyList policy = new TrafficPolicyList();
        policy.setId("123");
        list.add(policy);
        ResultRsp<List<TrafficPolicyList>> rsp = impl.configMqc("CtrlId", "DeviceId", list);
        assertEquals(null, rsp.getErrorCode());
    }

    @Test
    public void configMqcBodyNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("ExternalId1111");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                trafficPolicyList.add(trafficPolicy);

                Map<String, List<TrafficPolicyList>> trafficPolicyMap = new HashMap<String, List<TrafficPolicyList>>();
                trafficPolicyMap.put("trafficPolicyList", trafficPolicyList);

                OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>> response =
                        new OverlayVpnDriverResponse<Map<String, List<TrafficPolicyList>>>();
                response.setData(trafficPolicyMap);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        PolicyRouteImpl impl = new PolicyRouteImpl();
        List<TrafficPolicyList> list = new ArrayList<>();
        TrafficPolicyList policy = new TrafficPolicyList();
        policy.setId("123");
        list.add(policy);
        ResultRsp<List<TrafficPolicyList>> rsp = impl.configMqc("CtrlId", "DeviceId", list);
        assertEquals(null, rsp.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void deleteMqcCrtlNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        List<String> list = new ArrayList<>();
        list.add("Value");
        impl.deleteMqc(null, "deviceid", list);
    }

    @Test(expected = ServiceException.class)
    public void deleteMqcDeviceNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        List<String> list = new ArrayList<>();
        list.add("Value");
        impl.deleteMqc("CtrlId", null, list);
    }

    @Test
    public void deleteMqcListNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        ResultRsp<String> rsp = impl.deleteMqc("CtrlId", "DeviceId", null);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test
    public void deleteMqcTimeOut() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(408);

                ACDelResponse acDelResponse = new ACDelResponse();
                List<ConfigCommonResult> configCommonResultList = new LinkedList<ConfigCommonResult>();
                ConfigCommonResult configCommonResult = new ConfigCommonResult();
                configCommonResultList.add(configCommonResult);
                acDelResponse.setSuccess(configCommonResultList);
                acDelResponse.setErrcode("0");
                acDelResponse.setErrmsg("ExternalId1111");
                msg.setBody(JsonUtil.toJson(acDelResponse));

                return msg;
            }
        };

        PolicyRouteImpl impl = new PolicyRouteImpl();
        List<String> list = new ArrayList<>();
        list.add("Value");
        ResultRsp<String> rsp = impl.deleteMqc("CtrlId", "DeviceId", list);
        assertEquals("adapter.controller.timeout", rsp.getErrorCode());
    }

    @Test
    public void queryRouteByDeviceCtrlNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        ResultRsp<List<TrafficPolicyList>> rsp = impl.queryRouteByDevice(null, "DeviceId");
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }

    @Test
    public void queryRouteByDeviceDeviceIdNull() throws ServiceException {
        PolicyRouteImpl impl = new PolicyRouteImpl();
        ResultRsp<List<TrafficPolicyList>> rsp = impl.queryRouteByDevice("CtrlId", null);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }

    @Test
    public void queryRouteByDeviceHttpMsgFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                trafficPolicyList.add(trafficPolicy);

                ACResponse<List<TrafficPolicyList>> response = new ACResponse<List<TrafficPolicyList>>();
                response.setData(trafficPolicyList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        PolicyRouteImpl impl = new PolicyRouteImpl();
        ResultRsp<List<TrafficPolicyList>> rsp = impl.queryRouteByDevice("CtrlId", "DeviceId");
        assertEquals(null, rsp.getErrorCode());
    }

    @Test
    public void queryRouteByDeviceBodyNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                trafficPolicyList.add(trafficPolicy);

                ACResponse<List<TrafficPolicyList>> response = new ACResponse<List<TrafficPolicyList>>();
                response.setData(trafficPolicyList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        PolicyRouteImpl impl = new PolicyRouteImpl();
        ResultRsp<List<TrafficPolicyList>> rsp = impl.queryRouteByDevice("CtrlId", "DeviceId");
        assertEquals(null, rsp.getErrorCode());
    }

    @Test
    public void queryRouteByDeviceResponseFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                TrafficPolicyList trafficPolicy = new TrafficPolicyList();
                TrafficInterface interface1 = new TrafficInterface();
                interface1.setInterfaceName("eth0");
                List<TrafficInterface> interfaceList = Arrays.asList(interface1);
                trafficPolicy.setId("uid-1234");
                trafficPolicy.setTrafficpolicyName("self-traffic-policy-1");
                trafficPolicy.setInterfaceList(interfaceList);
                List<TrafficPolicyList> trafficPolicyList = new LinkedList<TrafficPolicyList>();
                trafficPolicyList.add(trafficPolicy);

                ACResponse<List<TrafficPolicyList>> response = new ACResponse<List<TrafficPolicyList>>();
                response.setData(trafficPolicyList);
                response.setErrcode("01");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        PolicyRouteImpl impl = new PolicyRouteImpl();
        ResultRsp<List<TrafficPolicyList>> rsp = impl.queryRouteByDevice("CtrlId", "DeviceId");
        assertEquals(null, rsp.getErrorCode());
    }

    @Test
    public void deriveByDeviceIdListNull() throws ServiceException {
        Map<String, List<SbiNePolicyRoute>> rsp = PolicyRouteImpl.deriveByDeviceId("CtrlId", null);
        assertEquals(true, rsp.isEmpty());
    }

    @Test
    public void findCorrespondNbiModel() throws ServiceException {
        List<FailData<SbiNePolicyRoute>> list = new ArrayList<>();
        FailData<SbiNePolicyRoute> failData = new FailData<>();
        SbiNePolicyRoute abi = new SbiNePolicyRoute();
        abi.setUuid("id");
        failData.setData(abi);
        list.add(failData);

        List<SbiNePolicyRoute> list1 = new ArrayList<>();
        list1.add(abi);

        ResultRsp<List<TrafficPolicyList>> rsp1 = new ResultRsp<>();
        List<TrafficPolicyList> list2 = new ArrayList<>();
        TrafficPolicyList traffic = new TrafficPolicyList();
        traffic.setId("id");
        list2.add(traffic);
        rsp1.setData(list2);
        PolicyRouteImpl.fillFailDataInfo(list, list1, rsp1);
    }

}
