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

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.ACNetwork;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetNetModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

public class SubnetServiceImplTest {

    @Test(expected = ServiceException.class)
    public void createSubnetCrtlNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();
        ACNetwork network = new ACNetwork();
        network.setId("id");
        impl.createSubnet(network, null, "deviceId");
    }

    @Test(expected = ServiceException.class)
    public void createSubnetDeviceNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();
        ACNetwork network = new ACNetwork();
        network.setId("id");
        impl.createSubnet(network, "CtrlId", null);
    }

    @Test(expected = ServiceException.class)
    public void createSubnetNetworkNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();
        ACNetwork network = new ACNetwork();
        network.setId(null);
        impl.createSubnet(network, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void updateSubnetCrtlNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();
        SbiSubnetNetModel network = new SbiSubnetNetModel();
        network.setNeId("id");
        impl.updateSubnet(network, null, "deviceId");
    }

    @Test(expected = ServiceException.class)
    public void updateSubnetDeviceNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();
        SbiSubnetNetModel network = new SbiSubnetNetModel();
        network.setNeId("id");
        impl.updateSubnet(network, "CtrlId", null);
    }

    @Test(expected = ServiceException.class)
    public void updateSubnetNetworkNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();
        SbiSubnetNetModel network = new SbiSubnetNetModel();
        network.setNeId(null);
        impl.updateSubnet(network, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void deleteSubnetCrtlNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();

        impl.deleteSubnet("NetWork", null, "deviceId");
    }

    @Test(expected = ServiceException.class)
    public void deleteSubnetDeviceNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();

        impl.deleteSubnet("NetWork", "CtrlId", null);
    }

    @Test(expected = ServiceException.class)
    public void deleteSubnetNetworkNull() throws ServiceException {
        SubnetServiceImpl impl = new SubnetServiceImpl();

        impl.deleteSubnet(null, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void queryNetworkCtrlNull() throws ServiceException {
        SubnetServiceImpl.queryNetwork(null, "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void queryNetworkDeviceNull() throws ServiceException {
        SubnetServiceImpl.queryNetwork("CtrlId", null);
    }

    @Test(expected = ServiceException.class)
    public void queryNetworkStatus() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                ACResponse<ACNetwork> acResponse = new ACResponse<>();
                ACNetwork acNetwork = new ACNetwork();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        SubnetServiceImpl.queryNetwork("CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void queryNetworkBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<ACNetwork> acResponse = new ACResponse<>();
                ACNetwork acNetwork = new ACNetwork();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };
        SubnetServiceImpl.queryNetwork("CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void deleteSubnetStatus() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                ACResponse<String> acResponse = new ACResponse<>();
                acResponse.setData("data");
                acResponse.setErrcode("0");
                msg.setBody("Body");
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        impl.deleteSubnet("NetworkId", "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void deleteSubnetBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<String> acResponse = new ACResponse<>();
                acResponse.setData("data");
                acResponse.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        impl.deleteSubnet("NetworkId", "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void updateSubnetStatus() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                ACResponse<ACNetwork> acResponse = new ACResponse<>();
                ACNetwork acNetwork = new ACNetwork();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        SbiSubnetNetModel sbi = new SbiSubnetNetModel();
        sbi.setNeId("Id");
        impl.updateSubnet(sbi, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void updateSubnetBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<ACNetwork> acResponse = new ACResponse<>();
                ACNetwork acNetwork = new ACNetwork();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        SbiSubnetNetModel sbi = new SbiSubnetNetModel();
        sbi.setNeId("Id");
        impl.updateSubnet(sbi, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void updateSubnetBodyErrorCode() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<ACNetwork> acResponse = new ACResponse<>();
                ACNetwork acNetwork = new ACNetwork();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("01");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        SbiSubnetNetModel sbi = new SbiSubnetNetModel();
        sbi.setNeId("Id");
        impl.updateSubnet(sbi, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void createSubnetStatus() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                ACResponse<SbiSubnetNetModel> acResponse = new ACResponse<>();
                SbiSubnetNetModel acNetwork = new SbiSubnetNetModel();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        ACNetwork sbi = new ACNetwork();
        sbi.setId("Id");
        impl.createSubnet(sbi, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void createSubnetBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<SbiSubnetNetModel> acResponse = new ACResponse<>();
                SbiSubnetNetModel acNetwork = new SbiSubnetNetModel();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        ACNetwork sbi = new ACNetwork();
        sbi.setId("Id");
        impl.createSubnet(sbi, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void createSubnetStatusCtrlErrorSubnetSame() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<SbiSubnetNetModel> acResponse = new ACResponse<>();
                SbiSubnetNetModel acNetwork = new SbiSubnetNetModel();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0304390019");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        ACNetwork sbi = new ACNetwork();
        sbi.setId("Id");
        impl.createSubnet(sbi, "CtrlId", "DeviceId");
    }

    @Test(expected = ServiceException.class)
    public void createSubnetStatusCtrlErrorAlreadyExist() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                ACResponse<SbiSubnetNetModel> acResponse = new ACResponse<>();
                SbiSubnetNetModel acNetwork = new SbiSubnetNetModel();
                acNetwork.setDescription("Description");
                acNetwork.setName("subnet");
                acResponse.setData(acNetwork);
                acResponse.setErrcode("0304390110");
                msg.setBody(JsonUtil.toJson(acResponse));
                return msg;
            }
        };

        SubnetServiceImpl impl = new SubnetServiceImpl();
        ACNetwork sbi = new ACNetwork();
        sbi.setId("Id");
        impl.createSubnet(sbi, "CtrlId", "DeviceId");
    }

}
