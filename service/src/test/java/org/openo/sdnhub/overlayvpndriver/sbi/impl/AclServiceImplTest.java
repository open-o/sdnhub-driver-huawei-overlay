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
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.List;

public class AclServiceImplTest {

    AclServiceImpl impl = new AclServiceImpl();

    String queryResJson =
            "{\"errcode\":\"0\",\"errmsg\":null,\"pageIndex\":0,\"pageSize\":0,"
            + "\"totalRecords\":0,\"data\":{\"ipv4\":\"192.168.1.2\","
            + "\"ipv6\":\"\",\"ipMask\":\"\",\"prefixLength\":\"\","
            + "\"id\":\"\"},\"success\":[],\"fail\":[],\"sucess\":true}";

    @Test(expected = ServiceException.class)
    public void createAclCtrlUuidNull() throws ServiceException {
        String deviceId = "deviceId";
        impl.createAcl(null, null, deviceId);
    }

    @Test(expected = ServiceException.class)
    public void createAclDeviceNull() throws ServiceException {
        String ctrlUuid = "CtrlUuid";
        impl.createAcl(null, ctrlUuid, null);
    }

    @Test(expected = ServiceException.class)
    public void deleteAclCtrlUuidNull() throws ServiceException {
        String deviceId = "deviceId";
        impl.deleteAcl(null, null, deviceId);
    }

    @Test(expected = ServiceException.class)
    public void deleteAclDeviceNull() throws ServiceException {
        String ctrlUuid = "CtrlUuid";
        impl.deleteAcl(null, ctrlUuid, null);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteAclHttpError() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        impl.deleteAcl("acl123", "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteAclHttpErrorEmptyBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        impl.deleteAcl("acl123", "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteAclAcResponseFailure() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ACResponse<String> response = new ACResponse<>();
                HTTPReturnMessage msg = new HTTPReturnMessage();
                response.setData("acldelete");
                response.setErrcode("1");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };
        impl.deleteAcl("acl123", "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQueryAclHttpError() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        impl.queryAcl("acl123", "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQueryAclHttpErrorEmptyBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        impl.queryAcl("acl123", "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testQueryAclAcResponseFailure() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ACResponse<List<AcAcl>> response = new ACResponse<>();
                List<AcAcl> list = new ArrayList<>();
                AcAcl acl = new AcAcl();
                acl.setAclId("123");
                list.add(acl);

                HTTPReturnMessage msg = new HTTPReturnMessage();
                response.setData(list);
                response.setErrcode("1");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };
        impl.queryAcl("acl123", "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void queryAclCtrlNull() throws ServiceException {

        String deviceId = "deviceId";
        impl.queryAcl(null, null, deviceId);
    }

    @Test(expected = ServiceException.class)
    public void queryAclDeviceNull() throws ServiceException {

        String ctrlUuid = "CtrlUuid";
        impl.queryAcl(null, ctrlUuid, null);
    }

    @Test
    public void queryAcl() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ACResponse<List<AcAcl>> response = new ACResponse<>();
                List<AcAcl> list = new ArrayList<>();
                AcAcl acl = new AcAcl();
                acl.setAclId("123");
                list.add(acl);

                HTTPReturnMessage msg = new HTTPReturnMessage();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        String aclId = "aclId";
        String ctrlUuid = "CtrlUuid";
        String deviceId = "deviceId";
        ResultRsp<AcAcl> rsp = impl.queryAcl(aclId, ctrlUuid, deviceId);

        assertEquals("overlayvpn.operation.failed", rsp.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateAclHttpError() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        AcAcl acl = new AcAcl();
        acl.setAclName("name");
        impl.updateAcl(acl, "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateAclHttpErrorEmptyBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        AcAcl acl = new AcAcl();
        acl.setAclName("name");
        impl.updateAcl(acl, "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateAclAcResponseFailure() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ACResponse<List<AcAcl>> response = new ACResponse<>();
                List<AcAcl> list = new ArrayList<>();
                AcAcl acl = new AcAcl();
                acl.setAclId("123");
                list.add(acl);

                HTTPReturnMessage msg = new HTTPReturnMessage();
                response.setData(list);
                response.setErrcode("1");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };
        AcAcl acl = new AcAcl();
        acl.setAclName("name");
        impl.updateAcl(acl, "123", "extSysID=ctrlid123");
    }

    @Test
    public void updateAcl() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ACResponse<List<AcAcl>> response = new ACResponse<>();
                List<AcAcl> list = new ArrayList<>();
                AcAcl acl = new AcAcl();
                acl.setAclId("123");
                list.add(acl);

                HTTPReturnMessage msg = new HTTPReturnMessage();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };

        String ctrlUuid = "CtrlUuid";
        String deviceId = "deviceId";
        AcAcl acl = new AcAcl();
        acl.setAclName("name");
        ResultRsp<AcAcl> rsp = impl.updateAcl(acl, ctrlUuid, deviceId);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void updateAclCtrluuid() throws ServiceException {

        String deviceId = "deviceId";
        AcAcl acl = new AcAcl();
        acl.setAclName("name");
        impl.updateAcl(acl, "", deviceId);
    }

    @Test(expected = ServiceException.class)
    public void updateAclDeviceId() throws ServiceException {

        String ctrlUuid = "CtrlUuid";
        AcAcl acl = new AcAcl();
        acl.setAclName("name");
        impl.updateAcl(acl, ctrlUuid, "");
    }

}
