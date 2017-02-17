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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;

import mockit.Mock;
import mockit.MockUp;

public class VxlanConvertTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testConvertVxlanInsToNetVxlanDeviceModel() {
		List<SbiNeVxlanInstance> list = new ArrayList<>();
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
		list.add(vxLanInstanceList);

		Map<String, List<VxLanDeviceModel>> convertVxlanInsToNetVxlanDeviceModel = VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(list);

		assertEquals(true,convertVxlanInsToNetVxlanDeviceModel.isEmpty());
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

		SbiNeVxlanInstance vxLanInstanceList = new SbiNeVxlanInstance();

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

		Map<String, List<VxLanDeviceModel>> convertVxlanInsToNetVxlanDeviceModel = VxlanConvert.convertVxlanInsToNetVxlanDeviceModel(list1);		
		assertEquals("1772783",convertVxlanInsToNetVxlanDeviceModel.get("546757").get(0).getUuid());
	}

	@Test(expected = ServiceException.class)
	public void testCheckInputCreateVxlan_EmptyList() throws ServiceException {
		List<SbiNeVxlanInstance> list = new ArrayList<>();

		SbiNeVxlanInstance vxLanInstance = new SbiNeVxlanInstance();
		vxLanInstance.getArpProxy();
		vxLanInstance.getKeepAlive();
		vxLanInstance.getVni();
		vxLanInstance.setVxlanTunnelList(new ArrayList<>());
		vxLanInstance.getVxlanInterfaceList();

		VxlanConvert.checkInputCreateVxlan(list);

	}

	@Test
	public void testCheckInputCreateVxlan2() throws ServiceException {
		
        new MockUp<ValidationUtil>(){
            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };
        
		List<SbiNeVxlanInstance> list = new ArrayList<>();
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
		
		list.add(vxLanInstance);

		List<SbiNeVxlanInstance> checkInputCreateVxlan = VxlanConvert.checkInputCreateVxlan(list);		
		assertEquals("huih",checkInputCreateVxlan.get(0).getVni());

	}

	@Test
	public void testDivideVxlanInsByDeviceId() {

		List<SbiNeVxlanInstance> list = new ArrayList<>();

		SbiNeVxlanInstance vxLanInstanceList = new SbiNeVxlanInstance();
		vxLanInstanceList.getArpProxy();
		vxLanInstanceList.setKeepAlive("123");
		vxLanInstanceList.setVni("0465");
		vxLanInstanceList.setVxlanTunnelList(new ArrayList<>());
		vxLanInstanceList.getVxlanInterfaceList();
		list.add(vxLanInstanceList);
		Map<String, List<SbiNeVxlanInstance>> divideVxlanInsByDeviceId = VxlanConvert.divideVxlanInsByDeviceId(list);		
		assertEquals("0465",divideVxlanInsByDeviceId.get(null).get(0).getVni());
	}

}
