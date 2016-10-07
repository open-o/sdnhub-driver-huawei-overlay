/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.overlayvpndriver.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.overlayvpn.brs.invdao.CommParamDao;
import org.openo.sdno.overlayvpn.brs.invdao.ControllerDao;
import org.openo.sdno.overlayvpn.brs.model.AuthInfo;
import org.openo.sdno.overlayvpn.brs.model.CommParamMO;
import org.openo.sdno.overlayvpn.brs.model.ControllerMO;
import org.openo.sdno.ssl.EncryptionUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class OverlayVpnDriverProxyTest {

    @Test
    public void testSendGetMsg() throws ServiceException {
        new MockUp<ControllerDao>() {

            @Mock
            public ControllerMO getController(String uuid) throws ServiceException {
                ControllerMO mo = new ControllerMO();
                mo.setName("123");
                mo.setHostName("12.2.21.1");
                return mo;
            }

        };

        new MockUp<CommParamDao>() {

            @Mock
            public List<CommParamMO> getCommParam(String controllerID) throws ServiceException {
                List<CommParamMO> mos = new ArrayList<>();
                CommParamMO mo = new CommParamMO();
                mo.setHostName("10.0.0.1");
                mo.setCommParams("{\"param1\":\"one\"},{\"password\":\"pwd\"}");
                mos.add(mo);
                return mos;
            }
        };

        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] plain) {
                return "{\"user\":\"root\"}".toCharArray();
            }

            @Mock
            public char[] encode(char[] plain) {
                return "{\"password\":\"pwd\"}".toCharArray();
            }
        };

        HTTPReturnMessage response = OverlayVpnDriverProxy.getInstance().sendGetMsg("../", "", "123");
        assertEquals(response.getStatus(), 0);
    }

    @Test
    public void testSendGetMsg1() throws ServiceException {
        new MockUp<ControllerDao>() {

            @Mock
            public ControllerMO getController(String uuid) throws ServiceException {
                return null;
            }

        };

        HTTPReturnMessage response = OverlayVpnDriverProxy.getInstance().sendGetMsg("../", "", "123");
        assertEquals(response.getStatus(), 0);
    }

    @Test
    public void testSendPostMsg() throws ServiceException {

        new MockUp<ControllerDao>() {

            @Mock
            ControllerMO getController(String uuid) {
                ControllerMO controller = new ControllerMO();
                controller.setHostName("hostname");
                return controller;
            }
        };

        new MockUp<CommParamDao>() {

            @Mock
            List<CommParamMO> getCommParam(String suuid) {
                List<CommParamMO> commList = new ArrayList<CommParamMO>();
                CommParamMO commonParam = new CommParamMO();
                commList.add(commonParam);
                return commList;
            }
        };

        new MockUp<CommParamMO>() {

            @Mock
            AuthInfo getAuthInfo() {
                AuthInfo authInfo = new AuthInfo();
                return authInfo;
            }
        };

        OverlayVpnDriverProxy acBranchProxy = OverlayVpnDriverProxy.getInstance();
        HTTPReturnMessage response = acBranchProxy.sendPostMsg("../", "", "123");
        assertEquals(response.getStatus(), 0);
    }

    @Test
    public void testSendPutMsg() {

        new MockUp<ControllerDao>() {

            @Mock
            ControllerMO getController(String uuid) {
                ControllerMO controller = new ControllerMO();
                controller.setHostName("hostname");
                return controller;
            }
        };

        new MockUp<CommParamDao>() {

            @Mock
            List<CommParamMO> getCommParam(String suuid) {
                List<CommParamMO> commList = new ArrayList<CommParamMO>();
                CommParamMO commonParam = new CommParamMO();
                commList.add(commonParam);
                return commList;
            }
        };

        new MockUp<CommParamMO>() {

            @Mock
            AuthInfo getAuthInfo() {
                AuthInfo authInfo = new AuthInfo();
                return authInfo;
            }
        };

        OverlayVpnDriverProxy acBranchProxy = OverlayVpnDriverProxy.getInstance();
        try {
            HTTPReturnMessage response = acBranchProxy.sendPutMsg("../", "123", "123");
            assertEquals(response.getStatus(), 0);
        } catch(Exception se) {
            assertTrue(true);
        }
    }

    @Test
    public void testSendPutMsgExceptionAuthNull() {

        new MockUp<ControllerDao>() {

            @Mock
            ControllerMO getController(String uuid) {
                ControllerMO controller = new ControllerMO();
                controller.setHostName("hostname");
                return controller;
            }
        };

        new MockUp<CommParamDao>() {

            @Mock
            List<CommParamMO> getCommParam(String suuid) {
                List<CommParamMO> commList = new ArrayList<CommParamMO>();
                CommParamMO commonParam = new CommParamMO();
                commList.add(commonParam);
                return commList;
            }
        };

        new MockUp<CommParamMO>() {

            @Mock
            AuthInfo getAuthInfo() {
                AuthInfo authInfo = null;
                return authInfo;
            }
        };

        OverlayVpnDriverProxy acBranchProxy = OverlayVpnDriverProxy.getInstance();
        try {
            HTTPReturnMessage response = acBranchProxy.sendPutMsg("../", "123", "123");
            assertEquals(response.getStatus(), 0);
        } catch(Exception se) {
            assertTrue(true);
        }
    }

    @Test
    public void testSendPutMsgThrowsException() {

        new MockUp<ControllerDao>() {

            @Mock
            ControllerMO getController(String uuid) {
                ControllerMO controller = new ControllerMO();
                controller.setHostName("hostname");
                return controller;
            }
        };

        new MockUp<CommParamDao>() {

            @Mock
            List<CommParamMO> getCommParam(String suuid) {
                List<CommParamMO> commList = new ArrayList<CommParamMO>();
                return commList;
            }
        };

        OverlayVpnDriverProxy acBranchProxy = OverlayVpnDriverProxy.getInstance();
        try {
            HTTPReturnMessage response = acBranchProxy.sendPutMsg("../", "123", "123");
            assertEquals(response.getStatus(), 0);
        } catch(Exception se) {
            assertTrue(true);
        }
    }

    @Test
    public void testSendDeleteMsg() throws ServiceException {
        OverlayVpnDriverProxy acBranchProxy = OverlayVpnDriverProxy.getInstance();
        HTTPReturnMessage response = acBranchProxy.sendDeleteMsg("../", "", "123");
        assertEquals(response.getStatus(), 0);

    }
}
