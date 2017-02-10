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

package org.openo.sdnhub.overlayvpndriver.nqa;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.ControllerNbiNqa;
import org.openo.sdnhub.overlayvpndriver.translator.NqaConvert;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNqa;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NqaConvertTest {

    @Test
    public void testParseResponse() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(200);
        OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();

        SbiNqa nQADeviceModel = new SbiNqa();
        nQADeviceModel.setDstIp("10.10.12.12");
        nQADeviceModel.setNeId("123");
        nQADeviceModel.setSrcIp("10.10.12.11");
        nQADeviceModel.setTestType("trunk");
        obj.setErrcode("0");
        obj.setData(nQADeviceModel);
        String actionDesc = "actionDesc";
        ResultRsp<SbiNqa> result = NqaConvert.parseResponse(httpMsg, actionDesc);
        assertEquals(result.getErrorCode(), "cloudvpn.failed");
    }

    @Test
    public void testParseResponse1() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(200);
        OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();
        SbiNqa nQADeviceModel = new SbiNqa();
        nQADeviceModel.setDstIp("10.10.12.12");
        nQADeviceModel.setNeId("123");
        nQADeviceModel.setSrcIp("10.10.12.11");
        nQADeviceModel.setTestType("trunk");
        obj.setData(nQADeviceModel);
        String actionDesc = "actionDesc";
        obj.setErrcode("123");
        ResultRsp<SbiNqa> result = NqaConvert.parseResponse(httpMsg, actionDesc);
        assertEquals(result.getHttpCode(), 200);
    }

    @Test
    public void testParseResponse2() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(200);
        OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();

        SbiNqa nQADeviceModel = new SbiNqa();
        nQADeviceModel.setDstIp("10.10.12.12");
        nQADeviceModel.setNeId("123");
        nQADeviceModel.setSrcIp("10.10.12.11");
        nQADeviceModel.setTestType("trunk");

        obj.setData(nQADeviceModel);

        obj.setErrcode("123");

        String actionDesc = "actionDesc";
        ResultRsp<SbiNqa> result = NqaConvert.parseResponse(httpMsg, actionDesc);
        assertEquals(result.getHttpCode(), 200);
    }

    @Test
    public void testParseResponse3() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(500);
        Map<String, String> obj = new HashMap<>();
        obj.put("12", "value");
        String actionDesc = "actionDesc";
        ResultRsp<SbiNqa> result = NqaConvert.parseResponse(httpMsg, actionDesc);
        assertEquals(result.getHttpCode(), 200);
    }

    @Test
    public void testParseResponse4() {
        new MockUp<HTTPReturnMessage>() {

            @Mock
            public boolean isSuccess() {
                return true;
            }
        };

        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        ResultRsp<SbiNqa> result = NqaConvert.parseResponse(httpMsg, "actionDesc");
        assertEquals(result.getErrorCode(),"cloudvpn.failed");
    }

    @Test
    public void testParseDeleteResponse5() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(200);
        OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();

        SbiNqa nQADeviceModel = new SbiNqa();
        nQADeviceModel.setDstIp("10.10.12.12");
        nQADeviceModel.setNeId("123");
        nQADeviceModel.setSrcIp("10.10.12.11");
        nQADeviceModel.setTestType("trunk");
        obj.setErrcode("0");
        obj.setData(nQADeviceModel);
        String actionDesc = "actionDesc";
        ResultRsp<String> response = NqaConvert.parseDeleteResponse(httpMsg, actionDesc);
        assertEquals(response.getMessage(),"actionDesc");
    }

    @Test
    public void testParseDeleteResponse1() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(200);
        OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();

        SbiNqa nQADeviceModel = new SbiNqa();
        nQADeviceModel.setDstIp("10.10.12.12");
        nQADeviceModel.setNeId("123");
        nQADeviceModel.setSrcIp("10.10.12.11");
        nQADeviceModel.setTestType("trunk");

        obj.setData(nQADeviceModel);
        String actionDesc = "actionDesc";
        obj.setErrcode("123");

        ResultRsp<String> response = NqaConvert.parseDeleteResponse(httpMsg, actionDesc);
        assertEquals(response.getHttpCode(),200);
    }

    @Test
    public void testParseDeleteResponse2() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(200);
        OverlayVpnDriverResponse<SbiNqa> obj = new OverlayVpnDriverResponse<SbiNqa>();

        SbiNqa nQADeviceModel = new SbiNqa();
        nQADeviceModel.setDstIp("10.10.12.12");
        nQADeviceModel.setNeId("123");
        nQADeviceModel.setSrcIp("10.10.12.11");
        nQADeviceModel.setTestType("trunk");

        obj.setData(nQADeviceModel);
        String actionDesc = "actionDesc";
        obj.setErrcode("123");
        httpMsg.setBody(JsonUtil.toJson(obj));

        ResultRsp<String> response = NqaConvert.parseDeleteResponse(httpMsg, actionDesc);
        assertEquals(response.getSuccessed().size(),0);
    }

    @Test
    public void testParseDeleteResponse3() {
        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        httpMsg.setStatus(500);
        Map<String, String> obj = new HashMap<>();
        obj.put("12", "value");
        String actionDesc = "actionDesc";
        ResultRsp<String> response =  NqaConvert.parseDeleteResponse(httpMsg, actionDesc);
        assertEquals(response.getErrorCode(),"cloudvpn.failed");
    }

    @Test
    public void testParseDeleteResponse4() {
        new MockUp<HTTPReturnMessage>() {

            @Mock
            public boolean isSuccess() {
                return true;
            }
        };

        HTTPReturnMessage httpMsg = new HTTPReturnMessage();
        NqaConvert.parseResponse(httpMsg, "actionDesc");
        ResultRsp<String> response =  NqaConvert.parseDeleteResponse(httpMsg, "actionDesc");
        assertEquals(response.isSuccess(),false);
    }

    @Test
    public void testConvert2Sbi() {
        List<SbiNqa> sbiNqaIdList = new ArrayList<>();
        SbiNqa sbiNqa = new SbiNqa();
        sbiNqa.setNeId("1234");
        sbiNqaIdList.add(sbiNqa);
        List<ControllerNbiNqa> result = NqaConvert.convert2Sbi(sbiNqaIdList);
        assertEquals(result.get(0).toString(),"ClassPojo [testType = null, srcPort = null, sequency = null," +
                " frequency = null, ttl = null, id = null, tos = null, srcIp6 = null, dstIp6 = null," +
                " probeCount = null, srcIp = null, timeout = null, dstIp = null]");
    }

    @Test
    public void testConvert2Sbi1() {
        List<SbiNqa> sbiNqaIdList = new ArrayList<>();
        List<ControllerNbiNqa> result = NqaConvert.convert2Sbi(sbiNqaIdList);
        assertEquals(result.size(), 0);
    }

}
