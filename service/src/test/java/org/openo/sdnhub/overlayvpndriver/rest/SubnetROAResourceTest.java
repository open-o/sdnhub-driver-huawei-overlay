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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetNetModel;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInstance;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import junit.framework.TestCase;
import mockit.Mock;
import mockit.MockUp;

public class SubnetROAResourceTest{
    private static final String CTRL_UUID = "extSysID=81244ad0-b4ea-41ed-969e-d5588b32f";
    private static final String DEVICE_ID = "9244ad0-b5ea-47ed-989e-d5588bdvc";
    private static final String NETWORK_ID = "9244-58349-67-890";
    SubnetROAResource subnetROAResource = new SubnetROAResource();
    SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
    @Before
    public void setup() throws Exception{
        sbiSubnetNetModel.setNeId("190");
        sbiSubnetNetModel.setControllerId("v345");
        sbiSubnetNetModel.setNetworkId("7895");
        sbiSubnetNetModel.setServiceSubnetId("67845");
        sbiSubnetNetModel.setVni("1789");
        sbiSubnetNetModel.setVlanId("13444");
        sbiSubnetNetModel.setCidrIpAddress("192.68.7.0");
        sbiSubnetNetModel.setCidrMask("xlkm");
        sbiSubnetNetModel.setGatewayIp("192.68.7.09");
        sbiSubnetNetModel.setEnableDhcp("enable");
        sbiSubnetNetModel.setIpRangeStartIp("192.68.7.87");
        sbiSubnetNetModel.setIpRangeEndIp("192.68.7.56");
        sbiSubnetNetModel.setUseMode("on");
        sbiSubnetNetModel.setChangedMode("false");
        sbiSubnetNetModel.setDhcpMode("server");
        sbiSubnetNetModel.setDnsMode("custom");
        sbiSubnetNetModel.setUnlimit("true");
        sbiSubnetNetModel.setIpv6Address("192.168.4.241");
        sbiSubnetNetModel.setPrefixLength("154");
        sbiSubnetNetModel.setDhcp6Enable("true");
        sbiSubnetNetModel.setDhcp6Mode("relay");
        sbiSubnetNetModel.setPriorDnsServer("high");
        sbiSubnetNetModel.setStandbyDnsServer("ndjd");
        sbiSubnetNetModel.setOperStatus("up");
        sbiSubnetNetModel.setAdminStatus("up");
        sbiSubnetNetModel.setActionState("create");
        sbiSubnetNetModel.setCreateTime("24673");
        sbiSubnetNetModel.setSource("SourceType.NONE.getName()");
        sbiSubnetNetModel.setTenantId("25637");
        sbiSubnetNetModel.setName("subnet");
        sbiSubnetNetModel.setDescription("subnet");
        sbiSubnetNetModel.setUuid("uuid");
        //Setting the impl service obj using reflection
        Field field = subnetROAResource.getClass().getDeclaredField("subnetService");
        field.setAccessible(true);
        field.set(subnetROAResource, new SubnetServiceImpl());
    }
    @Test
    public void testcreateSubnetResultSuccess() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };
        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.createSubnet(null,DEVICE_ID,CTRL_UUID,sbiSubnetNetModel);
        assertEquals("0", acResult.getErrorCode());
        assertEquals("uuid00", acResult.getData().getUuid());
    }
    @Test (expected = ServiceException.class)
    public void testcreateSubnetHttpFail() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.createSubnet(null,DEVICE_ID,CTRL_UUID,sbiSubnetNetModel);
    }
    @Test (expected = ServiceException.class)
    public void testcreateSubnetACResponseFail() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("145");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };
        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.createSubnet(null,DEVICE_ID,CTRL_UUID,sbiSubnetNetModel);
    }
    @Test
    public void testgetSubnet() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.getSubnet(null,DEVICE_ID,NETWORK_ID,CTRL_UUID);
        assertEquals("success", acResult.getErrorCode());
        assertEquals("uuid00", acResult.getData().getUuid());
    }
    @Test (expected = ServiceException.class)
    public void testgetSubnetHttpFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                return msg;
            }
        };

        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.getSubnet(null,DEVICE_ID,NETWORK_ID,CTRL_UUID);
    }
    @Test (expected = ServiceException.class)
    public void testgetSubnetACResponseFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("167");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.getSubnet(null,DEVICE_ID,NETWORK_ID,CTRL_UUID);
    }
    @Test
    public void testupdateSubnetResultSuccess() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };
        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.updateSubnet(null,DEVICE_ID,CTRL_UUID,sbiSubnetNetModel);
        assertEquals("0", acResult.getErrorCode());
        assertEquals("uuid00", acResult.getData().getUuid());
    }
    @Test (expected = ServiceException.class)
    public void testupdateSubnetSHttpFail() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                return msg;
            }
        };
        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.updateSubnet(null,DEVICE_ID,CTRL_UUID,sbiSubnetNetModel);
    }
    @Test (expected = ServiceException.class)
    public void testupdateSubnetACResponseFail() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<SbiSubnetNetModel> acResponse =new ACResponse<SbiSubnetNetModel>();
                SbiSubnetNetModel sbiSubnetNetModel = new SbiSubnetNetModel();
                sbiSubnetNetModel.setUuid("uuid00");
                sbiSubnetNetModel.setName("subnet");
                acResponse.setData(sbiSubnetNetModel);
                acResponse.setErrcode("178");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };
        ResultRsp<SbiSubnetNetModel> acResult = subnetROAResource.updateSubnet(null,DEVICE_ID,CTRL_UUID,sbiSubnetNetModel);
    }
    @Test
    public void testdeleteSubnetResultSuccess() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                ACResponse<String> acResponse =new ACResponse<String>();
                acResponse.setData("data");
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };
        ResultRsp<String> acResult = subnetROAResource.deleteSubnet(null,DEVICE_ID,NETWORK_ID,CTRL_UUID);
       assertEquals("0", acResult.getErrorCode());
    }
    @Test  (expected = ServiceException.class)
    public void testdeleteSubnetHttpFail() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                return msg;
            }
        };
        ResultRsp<String> acResult = subnetROAResource.deleteSubnet(null,DEVICE_ID,NETWORK_ID,CTRL_UUID);
    }
    @Test  (expected = ServiceException.class)
    public void testdeleteSubnetACResponseFail() throws ServiceException {

        new MockUp<ValidationUtil>(){
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
                ACResponse<String> acResponse =new ACResponse<String>();
                acResponse.setData("data");
                acResponse.setErrcode("123");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };
        ResultRsp<String> acResult = subnetROAResource.deleteSubnet(null,DEVICE_ID,NETWORK_ID,CTRL_UUID);
    }
}
