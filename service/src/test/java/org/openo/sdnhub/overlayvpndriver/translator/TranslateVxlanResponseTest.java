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


import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.controller.model.Vni;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInstance;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInterface;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanTunnel;

import java.util.ArrayList;
import java.util.List;


public class TranslateVxlanResponseTest {

    @Test
    public void translateTunnelExternalId() {

        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<SbiNeVxlanTunnel>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setVni("Vni");
        tunnelList.add(tunnel);

        List<SbiNeVxlanInterface> interfaceList = new ArrayList<SbiNeVxlanInterface>();
        SbiNeVxlanInterface interfaceLan = new SbiNeVxlanInterface();
        interfaceLan.setLocalName("LocalName");
        interfaceList.add(interfaceLan);

        SbiNeVxlanInstance sbiVxlan = new SbiNeVxlanInstance();
        sbiVxlan.setNbiVxlanTunnelId("Nbi");
        sbiVxlan.setVni("1234");
        sbiVxlan.setUuid("device");
        sbiVxlan.setConnectionId("name");
        sbiVxlan.setVxlanTunnelList(tunnelList);
        sbiVxlan.setVxlanInterfaceList(interfaceList);
        List<SbiNeVxlanInstance> sbiList = new ArrayList<SbiNeVxlanInstance>();
        sbiList.add(sbiVxlan);
        Vni vni = new Vni();
        vni.setVni(1234);
        vni.setDeleteMode(true);
        vni.setMacLearingMode("Mac");
        vni.setEvpnRtMode("Evpn");
        vni.setEvpnRtExport("Evpn");
        vni.setEvpnRtImport("evpnRtImport");
        List<Vni> vniList = new ArrayList<Vni>();
        vniList.add(vni);
        VxLanDeviceModel deviceVxlan = new VxLanDeviceModel();
        deviceVxlan.setVneId(1234);
        deviceVxlan.setName("name");
        deviceVxlan.setLocalAddress("address");
        deviceVxlan.setVniList(vniList);

        List<VxLanDeviceModel> vxLanList = new ArrayList<VxLanDeviceModel>();
        vxLanList.add(deviceVxlan);
        String deviceId = "device";
        TranslateVxlanResponse.translateVxlanId(sbiList, deviceId, vxLanList);
    }

    @Test
    public void translateTunnelExternalIdcheckNeVxlan() {

        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<SbiNeVxlanTunnel>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setVni("Vni");
        tunnelList.add(tunnel);

        List<SbiNeVxlanInterface> interfaceList = new ArrayList<SbiNeVxlanInterface>();
        SbiNeVxlanInterface interfaceLan = new SbiNeVxlanInterface();
        interfaceLan.setLocalName("LocalName");
        interfaceList.add(interfaceLan);

        SbiNeVxlanInstance sbiVxlan = new SbiNeVxlanInstance();
        sbiVxlan.setNbiVxlanTunnelId("Nbi");
        sbiVxlan.setVni("1234");
        sbiVxlan.setUuid("device");
        sbiVxlan.setConnectionId("connection");
        sbiVxlan.setVxlanTunnelList(tunnelList);
        sbiVxlan.setVxlanInterfaceList(interfaceList);
        List<SbiNeVxlanInstance> sbiList = new ArrayList<SbiNeVxlanInstance>();
        sbiList.add(sbiVxlan);
        Vni vni = new Vni();
        vni.setVni(1234);
        vni.setDeleteMode(true);
        vni.setMacLearingMode("Mac");
        vni.setEvpnRtMode("Evpn");
        vni.setEvpnRtExport("Evpn");
        vni.setEvpnRtImport("evpnRtImport");
        List<Vni> vniList = new ArrayList<Vni>();
        vniList.add(vni);
        VxLanDeviceModel deviceVxlan = new VxLanDeviceModel();
        deviceVxlan.setVneId(1234);
        deviceVxlan.setName("name");
        deviceVxlan.setLocalAddress("address");
        deviceVxlan.setVniList(vniList);

        List<VxLanDeviceModel> vxLanList = new ArrayList<VxLanDeviceModel>();
        vxLanList.add(deviceVxlan);
        String deviceId = "device";
        TranslateVxlanResponse.translateVxlanId(sbiList, deviceId, vxLanList);
    }

    @Test
    public void translateTunnelExternalIdVniListNull() {

        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<SbiNeVxlanTunnel>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setVni("Vni");
        tunnelList.add(tunnel);

        List<SbiNeVxlanInterface> interfaceList = new ArrayList<SbiNeVxlanInterface>();
        SbiNeVxlanInterface interfaceLan = new SbiNeVxlanInterface();
        interfaceLan.setLocalName("LocalName");
        interfaceList.add(interfaceLan);

        SbiNeVxlanInstance sbiVxlan = new SbiNeVxlanInstance();
        sbiVxlan.setNbiVxlanTunnelId("Nbi");
        sbiVxlan.setVni("1234");
        sbiVxlan.setUuid("device");
        sbiVxlan.setConnectionId("name");
        sbiVxlan.setVxlanTunnelList(tunnelList);
        sbiVxlan.setVxlanInterfaceList(interfaceList);
        List<SbiNeVxlanInstance> sbiList = new ArrayList<SbiNeVxlanInstance>();
        sbiList.add(sbiVxlan);
        Vni vni = new Vni();
        vni.setVni(1234);
        vni.setDeleteMode(true);
        vni.setMacLearingMode("Mac");
        vni.setEvpnRtMode("Evpn");
        vni.setEvpnRtExport("Evpn");
        vni.setEvpnRtImport("evpnRtImport");
        List<Vni> vniList = new ArrayList<Vni>();
        vniList.add(vni);
        VxLanDeviceModel deviceVxlan = new VxLanDeviceModel();
        deviceVxlan.setVneId(1234);
        deviceVxlan.setName("name");
        deviceVxlan.setLocalAddress("address");
        deviceVxlan.setVniList(null);

        List<VxLanDeviceModel> vxLanList = new ArrayList<VxLanDeviceModel>();
        vxLanList.add(deviceVxlan);
        String deviceId = "device";
        TranslateVxlanResponse.translateVxlanId(sbiList, deviceId, vxLanList);
    }

    @Test
    public void translateTunnelExternalIdVniNotEqual() {

        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<SbiNeVxlanTunnel>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setVni("Vni");
        tunnelList.add(tunnel);

        List<SbiNeVxlanInterface> interfaceList = new ArrayList<SbiNeVxlanInterface>();
        SbiNeVxlanInterface interfaceLan = new SbiNeVxlanInterface();
        interfaceLan.setLocalName("LocalName");
        interfaceList.add(interfaceLan);

        SbiNeVxlanInstance sbiVxlan = new SbiNeVxlanInstance();
        sbiVxlan.setNbiVxlanTunnelId("Nbi");
        sbiVxlan.setVni("12");
        sbiVxlan.setUuid("device");
        sbiVxlan.setConnectionId("name");
        sbiVxlan.setVxlanTunnelList(tunnelList);
        sbiVxlan.setVxlanInterfaceList(interfaceList);
        List<SbiNeVxlanInstance> sbiList = new ArrayList<SbiNeVxlanInstance>();
        sbiList.add(sbiVxlan);
        Vni vni = new Vni();
        vni.setVni(1234);
        vni.setDeleteMode(true);
        vni.setMacLearingMode("Mac");
        vni.setEvpnRtMode("Evpn");
        vni.setEvpnRtExport("Evpn");
        vni.setEvpnRtImport("evpnRtImport");
        List<Vni> vniList = new ArrayList<Vni>();
        vniList.add(vni);
        VxLanDeviceModel deviceVxlan = new VxLanDeviceModel();
        deviceVxlan.setVneId(1234);
        deviceVxlan.setName("name");
        deviceVxlan.setLocalAddress("address");
        deviceVxlan.setVniList(vniList);

        List<VxLanDeviceModel> vxLanList = new ArrayList<VxLanDeviceModel>();
        vxLanList.add(deviceVxlan);
        String deviceId = "device";
        TranslateVxlanResponse.translateVxlanId(sbiList, deviceId, vxLanList);
    }
}
