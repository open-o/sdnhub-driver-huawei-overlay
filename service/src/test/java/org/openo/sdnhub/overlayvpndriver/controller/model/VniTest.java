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

package org.openo.sdnhub.overlayvpndriver.controller.model;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class VniTest {

    Vni vni = new Vni();
    List<VniFilter> listvf = new LinkedList<>();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {

        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        List<String> listsr1 = new LinkedList<>();
        listsr1.add("8080");
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("54564594");
        vni.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
    }

    @Test
    public void testEqualsObjectSame() {

        Vni vniMac = new Vni();

        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String st = new String();
        st.isEmpty();
        List<String> lsr = new LinkedList<>();
        lsr.add(st);
        vniMac.setPeerAddresslist(lsr);
        String st2 = new String();
        st2.isEmpty();
        List<String> lsr1 = new LinkedList<>();
        lsr1.add(st2);
        vniMac.setPortlist(lsr1);
        PortVlan pv1 = new PortVlan();
        pv1.setPort("104420");
        pv1.setVlan(12552);
        List<PortVlan> lpv = new LinkedList<>();
        lpv.add(pv1);
        vniMac.setPortvlanlist(lpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in1 = new Integer(1);
        in1.intValue();
        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vniMac.setVlanlist(listin1);


        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectMac() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("bookmacbook");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);


        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectDm() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(false);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectBcm() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(false);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectEre() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("yugubuhu");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setName("RajuVF");
        listvf.add(filterList1);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectEri() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("hbyugyug");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectErm() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("ghhvvy");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectFilter() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF1");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectPeer() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String("hgyu");
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectPortList() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        List<String> listsr1 = new LinkedList<>();
        listsr1.add("8090");
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectPortVanList() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("8090");
        pv.setVlan(51549656);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectQpc() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(false);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectUid() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("9846595");
        vniMac.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectVi() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setName("RajuVF");
        listvf.add(filterList);
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(9656164);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectVlanList() {

        Vni vniMac = new Vni();
        vniMac.setMacLearingMode("macLearingMode");
        vniMac.setDeleteMode(true);
        vniMac.setBroadCastManager(true);
        vniMac.setEvpnRtExport("evpnRtExport");
        vniMac.setEvpnRtImport("evpnRtImport");
        vniMac.setEvpnRtMode("evpnRtMode");
        vniMac.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vniMac.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vniMac.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("104420");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vniMac.setPortvlanlist(listpv);
        vniMac.setQosPreClassify(true);
        vniMac.setUuid("54564594");
        vniMac.setVni(215454564);
        Integer in = new Integer(9166);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vniMac.setVlanlist(listin);

        vniMac.hashCode();

        assertFalse(vni.equals(vniMac));
    }

    @Test
    public void testEqualsObjectNull() {

        Vni vni1 = new Vni();
        vni1.setMacLearingMode(null);
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport(null);
        vni1.setEvpnRtImport(null);
        vni1.setEvpnRtMode(null);
        VniFilter filterList = new VniFilter();
        filterList.setName(null);
        listvf.add(filterList);
        vni1.setFilterList(listvf);
        String str2 = new String();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        String str4 = new String();
        str4.isEmpty();
        List<String> listsr4 = new LinkedList<>();
        listsr4.add(str4);
        vni1.setPortlist(listsr4);
        PortVlan pv1 = new PortVlan();
        pv1.setPort(null);
        List<PortVlan> listpv1 = new LinkedList<>();
        listpv1.add(pv1);
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid(null);
        vni1.setVni(215454564);
        Integer in1 = new Integer(1);
        in1.intValue();
        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vni1.setVlanlist(listin1);

        vni1.hashCode();
        assertFalse(vni.equals(new Object()));
        assertFalse(vni.equals(null));
        assertFalse(vni.equals(vni1));
    }

}
