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

package org.openo.sdno.acbranchservice.model.vxlan.adapter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestNetVni {

    NetVni netVni = new NetVni();

    @Test
    public void checkGetVni() {
        int vni = 1;
        netVni.setVni(vni);
        assertEquals(vni, netVni.getVni());
    }

    @Test
    public void checkGetDeleteMode() {
        boolean deleteMode = true;
        netVni.setDeleteMode(deleteMode);
        assertEquals(deleteMode, netVni.isDeleteMode());
    }

    @Test
    public void checkGetEvpnRtMode() {
        String evpnRtMode = "evpnRtMode";
        netVni.setEvpnRtMode(evpnRtMode);
        assertEquals(evpnRtMode, netVni.getEvpnRtMode());
    }

    @Test
    public void checkGetMacLearingMode() {
        String macLearingMode = "macLearingMode";
        netVni.setMacLearingMode(macLearingMode);
        assertEquals(macLearingMode, netVni.getMacLearingMode());
    }

    @Test
    public void checkGetEvpnRtExport() {
        String evpnRtExport = "evpnRtExport";
        netVni.setEvpnRtExport(evpnRtExport);
        assertEquals(evpnRtExport, netVni.getEvpnRtExport());
    }

    @Test
    public void checkGetEvpnRtImport() {
        String evpnRtImport = "evpnRtImport";
        netVni.setEvpnRtImport(evpnRtImport);
        assertEquals(evpnRtImport, netVni.getEvpnRtImport());
    }

    @Test
    public void checkGetPeerAddresslist() {
        List<String> peerAddresslist = new ArrayList();
        netVni.setPeerAddresslist(peerAddresslist);
        assertEquals(peerAddresslist, netVni.getPeerAddresslist());
    }

    @Test
    public void checkGetPortlist() {
        List<String> portlist = new ArrayList();
        netVni.setPortlist(portlist);
        assertEquals(portlist, netVni.getPortlist());
    }

    @Test
    public void checkGetVlanlist() {
        List<Integer> vlanlist = new ArrayList();
        netVni.setVlanlist(vlanlist);
        assertEquals(vlanlist, netVni.getVlanlist());
    }

    @Test
    public void checkGetPortvlanlist() {
        List<NetPortVlan> portvlanlist = new ArrayList();
        netVni.setPortvlanlist(portvlanlist);
        assertEquals(portvlanlist, netVni.getPortvlanlist());
    }
}
