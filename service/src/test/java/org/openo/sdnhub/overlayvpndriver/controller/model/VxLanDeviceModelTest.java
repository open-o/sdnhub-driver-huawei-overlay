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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VxLanDeviceModelTest {

    VxLanDeviceModel model1 = new VxLanDeviceModel();
    List<Vni> list1 = new ArrayList<>();
    Vni vni1 = new Vni();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {
        model1.setVneId(123);
        model1.setName("setName");
        model1.setLocalAddress("Address");
        vni1.setEvpnRtExport("evpnRtExport");
        list1.add(vni1);
        model1.setVniList(list1);
    }

    @Test
    public void testHashCode1() {
        VxLanDeviceModel model2 = new VxLanDeviceModel();
        model2.setVneId(123);
        model2.setName("setName");
        model2.setLocalAddress("Address");
        List<Vni> list2 = new ArrayList<>();
        Vni vni2 = new Vni();
        vni2.setEvpnRtExport("evpnRtExport");
        list2.add(vni2);
        model2.setVniList(list2);
        
        model2.hashCode();
    }
    
    @Test
    public void testHashCode2() {
        VxLanDeviceModel model3 = new VxLanDeviceModel();
        model3.setName(null);
        model3.setLocalAddress(null);
        model3.setVniList(null);
        
        model3.hashCode();
    }
    
    @Test
    public void testEqual2() {
        VxLanDeviceModel model2 = new VxLanDeviceModel();
        model2.setVneId(123);
        model2.setName("setName");
        model2.setLocalAddress("Address");
        List<Vni> list2 = new ArrayList<>();
        Vni vni2 = new Vni();
        vni2.setEvpnRtExport("evpnRtExport");
        list2.add(vni2);
        model2.setVniList(list2);
        
        //assertTrue(model1.equals(model2));
    }
    
    @Test
    public void testEqual3() {
        assertTrue(model1.equals(model1));
    }
    
    @Test
    public void testEqualNUll() {
        assertFalse(model1.equals(null));
    }
    
    @Test
    public void testEqual4() {
        VxLanDeviceModel model4 = new VxLanDeviceModel();
        model4.setVneId(12);
        model4.setName("setName");
        model4.setLocalAddress("Address");
        List<Vni> list4 = new ArrayList<>();
        Vni vni4 = new Vni();
        vni4.setEvpnRtExport("evpnRtExport");
        list4.add(vni4);
        model4.setVniList(list4);
        
        assertFalse(model1.equals(model4));
    }
    
    @Test
    public void testEqual5() {
        VxLanDeviceModel model5 = new VxLanDeviceModel();
        model5.setVneId(123);
        model5.setName("setNameTest");
        model5.setLocalAddress("Address");
        List<Vni> list5 = new ArrayList<>();
        Vni vni5 = new Vni();
        vni5.setEvpnRtExport("evpnRtExport");
        list5.add(vni5);
        model5.setVniList(list5);
        
        assertFalse(model1.equals(model5));
    }
    
    @Test
    public void testEqual6() {
        VxLanDeviceModel model6 = new VxLanDeviceModel();
        model6.setVneId(123);
        model6.setName("setName");
        model6.setLocalAddress("AddressTest");
        List<Vni> list6 = new ArrayList<>();
        Vni vni6 = new Vni();
        vni6.setEvpnRtExport("evpnRtExport");
        list6.add(vni6);
        model6.setVniList(list6);
        
        assertFalse(model1.equals(model6));
    }
    
    
    @Test
    public void testEqual7() {
        VxLanDeviceModel model7 = new VxLanDeviceModel();
        model7.setVneId(123);
        model7.setName("setName");
        model7.setLocalAddress("AddressTest");
        List<Vni> list7 = new ArrayList<>();
        Vni vni7 = new Vni();
        vni7.setEvpnRtExport("evpnRtExportTest");
        list7.add(vni7);
        model7.setVniList(list7);
        
        assertFalse(model1.equals(model7));
    }
    
    @Test
    public void testEqualObject() {
        assertFalse(model1.equals(new Object()));
    }
    
}
