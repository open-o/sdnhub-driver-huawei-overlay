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

package org.openo.sdnhub.overlayvpndriver.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.NQADeviceModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NqaConfigUtilTest {

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<NqaConfigUtil> constructor = NqaConfigUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
    }

    @Test
    public void testParseResponse() throws ServiceException {
        List<NQADeviceModel> list = new ArrayList<>();
        NQADeviceModel nqaModel = new NQADeviceModel();
        nqaModel.setTestType("Type");
        nqaModel.setDstIp("123");
        list.add(nqaModel);

        HTTPReturnMessage msg = new HTTPReturnMessage();
        OverlayVpnDriverResponse<List<NQADeviceModel>> acresponse = new OverlayVpnDriverResponse<>();
        acresponse.setData(list);
        acresponse.setErrcode("1");
        msg.setBody(JsonUtil.toJson(acresponse));
        msg.setStatus(200);
        String actionDesc = "actionDesc";

        ResultRsp<List<NQADeviceModel>> rsp = NqaConfigUtil.parseResponse(msg, msg.getBody(), actionDesc);
        assertEquals("overlayvpn.operation.failednull", rsp.getErrorCode());

    }

    @Test
    public void testParseResponseHttpError() throws ServiceException {

        List<NQADeviceModel> list = new ArrayList<>();
        NQADeviceModel nqaModel = new NQADeviceModel();
        nqaModel.setTestType("Type");
        nqaModel.setDstIp("123");
        list.add(nqaModel);

        HTTPReturnMessage msg = new HTTPReturnMessage();
        Map<String, String> acresponse = new HashMap<>();
        acresponse.put("errcode", "1");
        acresponse.put("errmsg", "No Data");
        msg.setBody(JsonUtil.toJson(acresponse));
        msg.setStatus(500);
        String actionDesc = "actionDesc";

        ResultRsp<List<NQADeviceModel>> rsp = NqaConfigUtil.parseResponse(msg, msg.getBody(), actionDesc);
        String errorCode = rsp.getErrorCode();
        assertEquals("1No Data", errorCode);
    }

    @Test
    public void testParseResponseHttpErrorEmptyBody() throws ServiceException {

        String actionDesc = "actionDesc";

        HTTPReturnMessage msg = new HTTPReturnMessage();
        msg.setStatus(500);

        ResultRsp<List<NQADeviceModel>> rsp = NqaConfigUtil.parseResponse(msg, msg.getBody(), actionDesc);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }

    @Test
    public void testParseDeleteResponseFail() throws ServiceException {

        ACDelResponse acresponse = new ACDelResponse();
        HTTPReturnMessage msg = new HTTPReturnMessage();
        acresponse.setErrmsg("ERROR");
        acresponse.setErrcode("01");
        msg.setBody(JsonUtil.toJson(acresponse));
        msg.setStatus(200);

        String actionDesc = "actionDesc";
        ResultRsp<String> rsp = NqaConfigUtil.parseDeleteResponse(msg, msg.getBody(), actionDesc);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());

    }

    @Test
    public void testParseDeleteResponse() throws ServiceException {

        ACDelResponse acresponse = new ACDelResponse();
        HTTPReturnMessage msg = new HTTPReturnMessage();
        acresponse.setErrmsg("ERROR");
        acresponse.setErrcode("0");
        msg.setBody(JsonUtil.toJson(acresponse));
        msg.setStatus(200);

        String actionDesc = "actionDesc";
        ResultRsp<String> rsp = NqaConfigUtil.parseDeleteResponse(msg, msg.getBody(), actionDesc);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());

    }

    @Test
    public void testParseDeleteResponseHttpError() throws ServiceException {

        ACDelResponse acresponse = new ACDelResponse();
        HTTPReturnMessage msg = new HTTPReturnMessage();
        acresponse.setErrmsg("ERROR");
        acresponse.setErrcode("0");
        msg.setBody(JsonUtil.toJson(acresponse));
        msg.setStatus(500);

        String actionDesc = "actionDesc";
        ResultRsp<String> rsp = NqaConfigUtil.parseDeleteResponse(msg, msg.getBody(), actionDesc);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());

    }

    @Test
    public void testParseDeleteResponseHttpErrorEmptyBody() throws ServiceException {

        ACDelResponse acresponse = new ACDelResponse();
        HTTPReturnMessage msg = new HTTPReturnMessage();
        acresponse.setErrmsg("ERROR");
        acresponse.setErrcode("0");
        msg.setStatus(200);

        String actionDesc = "actionDesc";
        ResultRsp<String> rsp = NqaConfigUtil.parseDeleteResponse(msg, msg.getBody(), actionDesc);
        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }
}
