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

import mockit.Mock;
import mockit.MockUp;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIp;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 03-Feb-2017
 */
public class PortROAResourceTest {

    private PortROAResource portRoAResource = new PortROAResource();

    String queryResJson =
            "{\"errcode\":\"0\",\"errmsg\":null,\"pageIndex\":0," + "\"pageSize\":0,\"totalRecords\":0,\"data\":"
                    + "[{\"ipAddr\":\"192.168.1.2\",\"ipv6Addr\":\"\",\"ipMask\":\"\",\"prefixLength\":" + "\"\",\"id\":\"\"}],"
                    + "\"success\":[],\"fail\":[],\"sucess\":true}";

    @Test
    public void testQueryPort() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(queryResJson);
                msg.setStatus(200);
                return msg;
            }
        };

        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "[{\"cfgkey\": \"wan-default-ip\",\"cfgvalue\":\"0.0.0.0\",\"validator\": \"\",\"encrypt\": \"false\"}]";
            }
        };

        ResultRsp<SbiIp> result = portRoAResource.queryPortIpByPortName("extSysID=ctrlid1024", "123", "test_port");
        assertEquals(result.getErrorCode(), "overlayvpn.operation.success");
    }

    @Test(expected = ServiceException.class)
    public void testQueryPortInvalidResponse() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(queryResJson);
                msg.setStatus(500);
                return msg;
            }
        };
        portRoAResource.queryPortIpByPortName("extSysID=ctrlid1024", "123", null);
    }

    @Test(expected = ServiceException.class)
    public void testQueryPortInvalidResponseJson() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("{}");
                msg.setStatus(200);
                return msg;
            }
        };

        portRoAResource.queryPortIpByPortName("extSysID=ctrlid1024", "123", null);
    }

    @Test(expected = ServiceException.class)
    public void testQueryPortInvalidBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody("");
                msg.setStatus(200);
                return msg;
            }
        };

        portRoAResource.queryPortIpByPortName("extSysID=ctrlid1024", "123", null);
    }

    @Test(expected = ServiceException.class)
    public void testQueryPortInvalidCtrlUuid() throws ServiceException {
        portRoAResource.queryPortIpByPortName("extSysID=!@#$", "123", null);
    }

    @Test(expected = ServiceException.class)
    public void testQueryPortInvalidDeviceId() throws ServiceException {
        portRoAResource.queryPortIpByPortName("extSysID=ctrlid1024", null, null);
    }
}
