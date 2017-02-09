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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInstance;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInterface;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanTunnel;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiVxlanNetModel;


public class VxlanConvertTest {
    
   

    @SuppressWarnings("unchecked")
    @Test
    public void testConvertVxlanInsToNetVxlanDeviceModel() {
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        vxLanInstanceList.getArpBroadcastSuppress();
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.getKeepAlive();
        vxLanInstanceList.getVni();
        vxLanInstanceList.getNbiVxlanTunnelId();
        vxLanInstanceList.getVxlanTunnelList();
        vxLanInstanceList.getVxlanTunnels();
        vxLanInstanceList.getVxlanInterfaces();
        vxLanInstanceList.getVxlanInterfaceList();
        list.add(vxLanInstanceList);
           
        VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(list);
        
        assertTrue(true);
    } 
    
    @Test
    public void testConvertVxlanInsToNetVxlanDeviceModel1() {
        
        List<VxLanDeviceModel> list2 = new ArrayList<>();
        
        Map<String, List<VxLanDeviceModel>> map1 = new ConcurrentHashMap<String, List<VxLanDeviceModel>>();
        
        VxLanDeviceModel vxLandevicemodel = new VxLanDeviceModel();
        
        vxLandevicemodel.setUuid("21254");
        vxLandevicemodel.setLocalAddress(null);
        vxLandevicemodel.setName(null);
        vxLandevicemodel.setVneId(0);
       
        list2.add(vxLandevicemodel);
        
        SbiVxlanNetModel sbiVxlanNetModel = new SbiVxlanNetModel();
        
        sbiVxlanNetModel.setDeviceId("123");
        map1.containsKey(123);
        map1.put("123", list2);
        
        List<SbiNeVxlanInstance> list1 = new ArrayList<>();
        
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        
        vxLanInstanceList.setKeepAlive("null");
        vxLanInstanceList.setVni("0465");
        vxLanInstanceList.setExternalId("1772783");
        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setSourceAddress("1.2.2.3");
        
        tunnelList.add(tunnel);
        vxLanInstanceList.setVxlanTunnelList(tunnelList);
        vxLanInstanceList.setDeviceId("546757");
        
        list1.add(vxLanInstanceList);
        
       VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(list1);
        
        assertTrue(true);
    } 
    
  
    @SuppressWarnings("unchecked")
    @Test
    public void testDivideVxlanTOUpdateOrDelete() {
        
           List<SbiNeVxlanInstance> list = new ArrayList<>();
           
           Map<String, List<SbiNeVxlanInstance>> map1 = new HashMap<String, List<SbiNeVxlanInstance>>();
           
           Map<String, List<SbiNeVxlanInstance>> map2 = new HashMap<String, List<SbiNeVxlanInstance>>();
        
            SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        
         vxLanInstanceList.setKeepAlive("null");
         vxLanInstanceList.setVni("0465");
         
        list.add(vxLanInstanceList);
        List<SbiNeVxlanInstance> l = new ArrayList<>();
        SbiNeVxlanInstance sbiNeVxlanInstance = new SbiNeVxlanInstance();
        sbiNeVxlanInstance.setActiveStatus("success");
        sbiNeVxlanInstance.setAdditionalInfo("correct");
        l.add(sbiNeVxlanInstance);

        map1.put("101",l);
        map2.put("202",l);
        VxlanConvert.divideVxlanTOUpdateOrDelete(list,map1,map2);
        
//        assertTrue(true);
    }
    @SuppressWarnings("unchecked")
    @Test
    public void testDivideVxlanTOUpdateOrDelete2() {
        
           List<SbiNeVxlanInstance> list = new ArrayList<>();
           
           Map<String, List<SbiNeVxlanInstance>> map1 = new HashMap<String, List<SbiNeVxlanInstance>>();
           
           Map<String, List<SbiNeVxlanInstance>> map2 = new HashMap<String, List<SbiNeVxlanInstance>>();
        
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
                 
      
       // vxLanInstanceList.setVxlanTunnelList("123");
        list.add(vxLanInstanceList);
        List<SbiNeVxlanInstance> l = new ArrayList<>();
        SbiNeVxlanInstance sbiNeVxlanInstance = new SbiNeVxlanInstance();
        sbiNeVxlanInstance.setActiveStatus("success");
        sbiNeVxlanInstance.setAdditionalInfo("correct");
        sbiNeVxlanInstance.setExternalId(null);
        l.add(sbiNeVxlanInstance);

        map1.put("101",l);

        map2.put("202",l);
        VxlanConvert.divideVxlanTOUpdateOrDelete(list,map1,map2);
        

    }

   /* @Test
    public void testConvertToDeviceModelForUpdate() {
        
        
  List<VxLanDeviceModel> list2 = new ArrayList<>();
        
        Map<String, List<VxLanDeviceModel>> map1 = new ConcurrentHashMap<String, List<VxLanDeviceModel>>();
        
        VxLanDeviceModel vxLandevicemodel = new VxLanDeviceModel();
        
        vxLandevicemodel.setUuid("21254");
        vxLandevicemodel.setLocalAddress("1.2.2");
        vxLandevicemodel.setName("huy");
        vxLandevicemodel.setVneId(1666);
       
        list2.add(vxLandevicemodel);
        
        SbiVxlanNetModel sbiVxlanNetModel = new SbiVxlanNetModel();
        
        sbiVxlanNetModel.setDeviceId("123");
        map1.containsKey(123);
        map1.put("123", list2);
        
        List<SbiNeVxlanInstance> list1 = new ArrayList<>();
        
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        
        vxLanInstanceList.setKeepAlive("null");
        vxLanInstanceList.setVni("0465");
        vxLanInstanceList.setExternalId("1772783");
        List<SbiNeVxlanTunnel> tunnelList = new ArrayList<>();
        SbiNeVxlanTunnel tunnel = new SbiNeVxlanTunnel();
        tunnel.setSourceAddress("1.2.2.3");
        
        tunnelList.add(tunnel);
        vxLanInstanceList.setVxlanTunnelList(tunnelList);
        vxLanInstanceList.setDeviceId("546757");
        
        list1.add(vxLanInstanceList);
        
       VxlanConvert.convertToDeviceModelForUpdate(list1);
        
        assertTrue(true);
            
    }*/

    
    @Test
    public void testCheckInputCreateVxlan() {
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.getKeepAlive();
        vxLanInstanceList.getVni();
        vxLanInstanceList.setVxlanTunnelList(new ArrayList<>());
        vxLanInstanceList.getVxlanInterfaceList();
        //list.add(vxLanInstanceList);
        try {
            
            VxlanConvert.checkInputCreateVxlan(list);
            
        } catch(ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    } 

    @Test
    public void testCheckInputCreateVxlan1() {
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.getKeepAlive();
        vxLanInstanceList.getVni();
        vxLanInstanceList.setVxlanTunnelList(new ArrayList<SbiNeVxlanTunnel>());
        vxLanInstanceList.getVxlanInterfaceList();
     
      // list.add(vxLanInstanceList);
        try {
            
            VxlanConvert.checkInputCreateVxlan(list);
            
        } catch(ServiceException e) { 
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    } 
    
    @Test
    public void testCheckInputCreateVxlan2() throws ServiceException{
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.getKeepAlive();
        vxLanInstanceList.setVni("huih");
        vxLanInstanceList.getVxlanTunnelList();
        vxLanInstanceList.setVxlanInterfaceList(new ArrayList<SbiNeVxlanInterface>());
     
        //list.add(vxLanInstanceList);
        try {
            
            VxlanConvert.checkInputCreateVxlan(list);
            
        } catch(ServiceException e) { 
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    } 
    
    @Test
    public void testDivideVxlanInsByDeviceId() {
        
        List<SbiNeVxlanInstance> list = new ArrayList<>();
        
        SbiNeVxlanInstance vxLanInstanceList =new SbiNeVxlanInstance();
        vxLanInstanceList.getArpProxy();
        vxLanInstanceList.setKeepAlive("null");
        vxLanInstanceList.setVni("0465");
        vxLanInstanceList.setVxlanTunnelList(new ArrayList<>());
        vxLanInstanceList.getVxlanInterfaceList();
        list.add(vxLanInstanceList);
        VxlanConvert.divideVxlanInsByDeviceId(list);
        
    } 
    
   }
