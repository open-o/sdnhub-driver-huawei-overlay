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

package org.openo.sdnhub.overlayvpndriver.translator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeVxlanInstance;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeVxlanInterface;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeVxlanTunnel;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;


public class VxlanConvertTest {

    List<SbiNeVxlanInstance> sbiVxLanInstanceList = new ArrayList<SbiNeVxlanInstance>();

    SbiNeVxlanInstance sbiNeVxlanInstance = new SbiNeVxlanInstance();


    /**
     * <br/>
     *
     * @throws Exception setup failure exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() throws Exception {

        List<String> vxlanInterfaces = new ArrayList<>();
        vxlanInterfaces.add("interface1");
        vxlanInterfaces.add("interface2");
        List<SbiNeVxlanTunnel> vxlanTunnelList = new ArrayList<>();
        SbiNeVxlanTunnel sbiNeVxlanTunnel = new SbiNeVxlanTunnel();
        vxlanTunnelList.add(sbiNeVxlanTunnel);

        SbiNeVxlanInterface sbiNeVxlanInterface = new SbiNeVxlanInterface();
        sbiNeVxlanInterface.setConnectionId("connectionId");
        sbiNeVxlanInterface.setAccessType("port");
        List<SbiNeVxlanInterface> vxlanInterfaceList = new ArrayList<>();
        vxlanInterfaceList.add(sbiNeVxlanInterface);

        sbiNeVxlanInstance.setActiveStatus("active");
        sbiNeVxlanInstance.setAdditionalInfo("additionalInfo");
        sbiNeVxlanInstance.setArpBroadcastSuppress("arpBroadcastSuppress");
        sbiNeVxlanInstance.setArpProxy("arpProxy");
        sbiNeVxlanInstance.setConnectionId("connectionId");
        sbiNeVxlanInstance.setControllerId("controllerId");
        sbiNeVxlanInstance.setCreatetime((long)1236549);
        sbiNeVxlanInstance.setDescription("description");
        ;
        sbiNeVxlanInstance.setDeployStatus("deployStatus");
        sbiNeVxlanInstance.setDeviceId("device12345");
        sbiNeVxlanInstance.setKeepAlive("keepAlive");
        sbiNeVxlanInstance.setName("name");
        sbiNeVxlanInstance.setNbiVxlanTunnelId("nbiVxlanTunnelId");
        sbiNeVxlanInstance.setOperationStatus("operationStatus");
        sbiNeVxlanInstance.setPeerDeviceId("peerDeviceId");
        sbiNeVxlanInstance.setRunningStatus("runningStatus");
        sbiNeVxlanInstance.setTenantId("tenantId");
        sbiNeVxlanInstance.setUpdatetime((long)13548);
        sbiNeVxlanInstance.setUuid("uuid");
        sbiNeVxlanInstance.setVni("1");
        sbiNeVxlanInstance.setVxlanInterfaces(vxlanInterfaces);
        sbiNeVxlanInstance.setVxlanTunnelList(vxlanTunnelList);
        List<String> vxlanTunnels = new ArrayList<>();
        sbiNeVxlanInstance.setVxlanTunnels(vxlanTunnels);
        sbiNeVxlanInstance.setVxlanInterfaceList(vxlanInterfaceList);

        sbiVxLanInstanceList.add(sbiNeVxlanInstance);
    }

    @Test
    public void testConvertVxlanInsToNetVxlanDeviceModel() {

        SbiNeVxlanInstance vxLanInstanceList = new SbiNeVxlanInstance();
        vxLanInstanceList.getArpBroadcastSuppress();
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.getKeepAlive();
        vxLanInstanceList.getVni();
        vxLanInstanceList.getNbiVxlanTunnelId();
        vxLanInstanceList.getVxlanTunnelList();
        vxLanInstanceList.getVxlanTunnels();
        vxLanInstanceList.getVxlanInterfaces();
        vxLanInstanceList.getVxlanInterfaceList();
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        list.add(vxLanInstanceList);

        Map<String, List<VxLanDeviceModel>> convertVxlanInsToNetVxlanDeviceModel =
                VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(list);

        assertEquals(true, convertVxlanInsToNetVxlanDeviceModel.isEmpty());

    }

    @Test
    public void testConvertVxlanInsToNetVxlanDeviceModel_Interface_port() {

        sbiNeVxlanInstance.setKeepAlive("null");
        sbiNeVxlanInstance.setVni("0465");
        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setSourceAddress("1.2.2.3");

        tunnelList.add(tunnel);
        sbiNeVxlanInstance.setVxlanTunnelList(tunnelList);
        sbiNeVxlanInstance.setDeviceId("546757");
        List<SbiNeVxlanInstance> sbiNeVxLanList = new ArrayList<>();
        sbiNeVxLanList.add(sbiNeVxlanInstance);

        Map<String, List<VxLanDeviceModel>> convertVxlanInsToNetVxlanDeviceModel =
                VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(sbiNeVxLanList);
        assertEquals("1.2.2.3", convertVxlanInsToNetVxlanDeviceModel.get("546757").get(0).getLocalAddress());
    }

    @Test
    public void testConvertVxlanInsToNetVxlanDeviceModelList_Interface_port() {

        sbiNeVxlanInstance.setKeepAlive("null");
        sbiNeVxlanInstance.setVni("0465");
        sbiNeVxlanInstance.setExternalId("1772783");
        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setSourceAddress("1.2.2.3");
        tunnel.setExternalId("tunnel123");

        tunnelList.add(tunnel);
        sbiNeVxlanInstance.setVxlanTunnelList(tunnelList);
        sbiNeVxlanInstance.setDeviceId("546757");
        List<SbiNeVxlanInstance> sbiNeVxLanList = new ArrayList<>();
        sbiNeVxLanList.add(sbiNeVxlanInstance);
        sbiNeVxlanInstance.setDeviceId("546758");
        sbiNeVxLanList.add(sbiNeVxlanInstance);

        Map<String, List<VxLanDeviceModel>> convertVxlanInsToNetVxlanDeviceModel =
                VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(sbiNeVxLanList);
        assertEquals("1.2.2.3", convertVxlanInsToNetVxlanDeviceModel.get("546758").get(0).getLocalAddress());
    }

    @Test
    public void testConvertVxlanInsToNetVxlanDeviceModel_Interface_dot1q_EmptyPortId() {

        SbiNeVxlanInterface sbiNeVxlanInterface = new SbiNeVxlanInterface();
        sbiNeVxlanInterface.setConnectionId("connectionId");
        sbiNeVxlanInterface.setAccessType("dot1q");
        sbiNeVxlanInterface.setDot1qVlanBitmap("1");
        List<SbiNeVxlanInterface> vxlanInterfaceList = new ArrayList<>();
        vxlanInterfaceList.add(sbiNeVxlanInterface);
        sbiNeVxlanInstance.setKeepAlive("null");
        sbiNeVxlanInstance.setVni("0465");
        sbiNeVxlanInstance.setExternalId("1772783");
        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setSourceAddress("1.2.2.3");

        tunnelList.add(tunnel);
        sbiNeVxlanInstance.setVxlanTunnelList(tunnelList);
        sbiNeVxlanInstance.setDeviceId("546757");
        sbiNeVxlanInstance.setVxlanInterfaceList(vxlanInterfaceList);
        List<SbiNeVxlanInstance> sbiNeVxLanList = new ArrayList<>();
        sbiNeVxLanList.add(sbiNeVxlanInstance);

        Map<String, List<VxLanDeviceModel>> convertVxlanInsToNetVxlanDeviceModel =
                VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(sbiNeVxLanList);
        assertEquals("1.2.2.3", convertVxlanInsToNetVxlanDeviceModel.get("546757").get(0).getLocalAddress());
    }

    @Test
    public void testConvertVxlanInsToNetVxlanDeviceModel_Interface_dot1q() {

        SbiNeVxlanInterface sbiNeVxlanInterface = new SbiNeVxlanInterface();
        sbiNeVxlanInterface.setConnectionId("connectionId");
        sbiNeVxlanInterface.setAccessType("dot1q");
        sbiNeVxlanInterface.setDot1qVlanBitmap("1");
        sbiNeVxlanInterface.setPortNativeId("1");
        List<SbiNeVxlanInterface> vxlanInterfaceList = new ArrayList<>();
        vxlanInterfaceList.add(sbiNeVxlanInterface);
        sbiNeVxlanInstance.setKeepAlive("null");
        sbiNeVxlanInstance.setVni("0465");
        sbiNeVxlanInstance.setExternalId("1772783");
        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setSourceAddress("1.2.2.3");

        tunnelList.add(tunnel);
        sbiNeVxlanInstance.setVxlanTunnelList(tunnelList);
        sbiNeVxlanInstance.setDeviceId("546757");
        sbiNeVxlanInstance.setVxlanInterfaceList(vxlanInterfaceList);
        List<SbiNeVxlanInstance> sbiNeVxLanList = new ArrayList<>();
        sbiNeVxLanList.add(sbiNeVxlanInstance);

        Map<String, List<VxLanDeviceModel>> convertVxlanInsToNetVxlanDeviceModel =
                VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(sbiNeVxLanList);
        assertEquals("1.2.2.3", convertVxlanInsToNetVxlanDeviceModel.get("546757").get(0).getLocalAddress());
    }

    @Test
    public void testCheckInputCreateVxlan_EmptyInterfaceList() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };

        List<SbiNeVxlanInstance> list = new ArrayList<>();
        List<SbiNeVxlanInterface> vxlanInterfaceList = new ArrayList<>();
        sbiNeVxlanInstance.setVxlanInterfaceList(vxlanInterfaceList);
        list.add(sbiNeVxlanInstance);

        SbiNeVxlanInstance vxLanInstance = new SbiNeVxlanInstance();
        vxLanInstance.getArpProxy();
        vxLanInstance.getKeepAlive();
        vxLanInstance.getVni();

        List<SbiNeVxlanInstance> response = VxlanConvert.checkInputCreateVxlan(list);
        assertEquals("1", response.get(0).getVni());
    }

    @Test(expected = Exception.class)
    public void testCheckInputCreateVxlan_EmptyTunnelList() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };

        List<SbiNeVxlanInstance> list = new ArrayList<>();
        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        sbiNeVxlanInstance.setVxlanTunnelList(tunnelList);
        list.add(sbiNeVxlanInstance);

        VxlanConvert.checkInputCreateVxlan(list);
    }

    @Test(expected = Exception.class)
    public void testCheckInputCreateVxlan_EmptyList() throws ServiceException {

        SbiNeVxlanInstance vxLanInstance = new SbiNeVxlanInstance();
        vxLanInstance.getArpProxy();
        vxLanInstance.getKeepAlive();
        vxLanInstance.getVni();
        vxLanInstance.setVxlanTunnelList(new ArrayList<>());
        vxLanInstance.getVxlanInterfaceList();

        List<SbiNeVxlanInstance> list = new ArrayList<>();
        VxlanConvert.checkInputCreateVxlan(list);
    }

    @Test
    public void testCheckInputCreateVxlan2() throws ServiceException {

        new MockUp<ValidationUtil>() {

            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };

        SbiNeVxlanInstance vxLanInstance = new SbiNeVxlanInstance();

        vxLanInstance.getArpProxy();
        vxLanInstance.getKeepAlive();
        vxLanInstance.setVni("huih");
        List<SbiNeVxlanInterface> vxlanInterfaceList = new LinkedList<>();
        SbiNeVxlanInterface sbiNeVxlanInterface = new SbiNeVxlanInterface();
        sbiNeVxlanInterface.setControllerId("Controller123");
        vxlanInterfaceList.add(sbiNeVxlanInterface);

        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setSourceAddress("1.2.2.3");
        tunnelList.add(tunnel);

        vxLanInstance.setVxlanTunnelList(tunnelList);
        vxLanInstance.setDeviceId("546757");
        vxLanInstance.setVxlanInterfaceList(vxlanInterfaceList);
        vxLanInstance.getVxlanTunnelList();
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        list.add(vxLanInstance);

        List<SbiNeVxlanInstance> checkInputCreateVxlan = VxlanConvert.checkInputCreateVxlan(list);
        assertEquals("huih", checkInputCreateVxlan.get(0).getVni());
    }

    @Test
    public void testDivideVxlanInsByDeviceId() {

        SbiNeVxlanInstance vxLanInstanceList = new SbiNeVxlanInstance();
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.setKeepAlive("123");
        vxLanInstanceList.setVni("0465");
        vxLanInstanceList.setVxlanTunnelList(new ArrayList<>());
        vxLanInstanceList.getVxlanInterfaceList();
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        list.add(vxLanInstanceList);
        Map<String, List<SbiNeVxlanInstance>> divideVxlanInsByDeviceId = VxlanConvert.divideVxlanInsByDeviceId(list);
        assertEquals("0465", divideVxlanInsByDeviceId.get(null).get(0).getVni());
    }

    @Test
    public void testDivideVxlanInsByDeviceIdList() {

        SbiNeVxlanInstance vxLanInstanceList = new SbiNeVxlanInstance();
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.setKeepAlive("123");
        vxLanInstanceList.setVni("0465");
        vxLanInstanceList.setVxlanTunnelList(new ArrayList<>());
        vxLanInstanceList.getVxlanInterfaceList();
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        list.add(vxLanInstanceList);
        list.add(sbiNeVxlanInstance);
        Map<String, List<SbiNeVxlanInstance>> divideVxlanInsByDeviceId = VxlanConvert.divideVxlanInsByDeviceId(list);
        assertEquals("0465", divideVxlanInsByDeviceId.get(null).get(0).getVni());
    }

}
