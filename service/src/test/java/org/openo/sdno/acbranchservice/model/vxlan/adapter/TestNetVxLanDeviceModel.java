package org.openo.sdno.acbranchservice.model.vxlan.adapter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestNetVxLanDeviceModel {
	NetVxLanDeviceModel netVxLanDeviceModel = new NetVxLanDeviceModel();
	
	@Test
	public void checkGetVneId(){
		int vneId = 1;
		netVxLanDeviceModel.setVneId(vneId);
		assertEquals(vneId, netVxLanDeviceModel.getVneId());
	}
	
	@Test
	public void checkGetName(){
		String name = "name";
		netVxLanDeviceModel.setName(name);
		assertEquals(name, netVxLanDeviceModel.getName());
	}
	
	@Test
	public void checkGetLocalAddress(){
		String localAddress = "localAddress";
		netVxLanDeviceModel.setLocalAddress(localAddress);
		assertEquals(localAddress, netVxLanDeviceModel.getLocalAddress());
	}
	
	@Test
	public void checkGetVniilist(){
		List<NetVni> vniilist = new ArrayList<>();
		netVxLanDeviceModel.setVniilist(vniilist);
		assertEquals(vniilist, netVxLanDeviceModel.getVniilist());
	}
}
