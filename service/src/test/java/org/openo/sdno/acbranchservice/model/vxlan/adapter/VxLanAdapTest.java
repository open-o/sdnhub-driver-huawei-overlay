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
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VxLanAdapTest {

    @Test
    public void NetVniTest() {
        NetVni obj = new NetVni();

        obj.setVni(5);
        assertEquals(5, obj.getVni());

        boolean val;
        val = false;
        obj.setDeleteMode(false);
        assertFalse(obj.isDeleteMode());

        obj.setMacLearingMode("control");
        assertEquals("control", obj.getMacLearingMode());

        obj.setEvpnRtMode("route");
        assertEquals("route", obj.getEvpnRtMode());

        obj.setEvpnRtExport("RTExport");
        assertEquals("RTExport", obj.getEvpnRtExport());

        obj.setEvpnRtImport("RTImport");
        assertEquals("RTImport", obj.getEvpnRtImport());

        List<String> peerAddresslist = new ArrayList();
        peerAddresslist.add("huawei");
        obj.setPeerAddresslist(peerAddresslist);

        // List<String> peerAddresslistRsp;
        // peerAddresslistRsp = obj.getPeerAddresslist();

        assertEquals("huawei", obj.getPeerAddresslist().get(0));

        List<String> portList = new ArrayList();
        portList.add("10");
        obj.setPortlist(portList);
        assertEquals("10", obj.getPortlist().get(0));

        List<Integer> vlanList = new ArrayList();
        vlanList.add(10);
        obj.setVlanlist(vlanList);
        assertEquals(10, (int)obj.getVlanlist().get(0));
        /*
         * List<NetPortVlan> portvlanlist = new ArrayList();
         * NetPortVlan netPortVlan = new NetPortVlan();
         * portvlanlist.add(10);
         * obj.setPortvlanlist(vlanList);
         * assertEquals("RTImport", obj.getPortvlanlist());
         */
    }

    @Test
    public void NetPortVlanTest() {
        NetPortVlan obj = new NetPortVlan("1", 10);

        obj.setPort("5");
        assertEquals("5", obj.getPort());

        obj.setVlan(5);
        assertEquals(5, obj.getVlan());
    }

    @Test
    public void NetVxLanDeviceModelTest() {
        NetVxLanDeviceModel obj = new NetVxLanDeviceModel();

        obj.setVneId(5);
        assertEquals(5, obj.getVneId());
    }
}
