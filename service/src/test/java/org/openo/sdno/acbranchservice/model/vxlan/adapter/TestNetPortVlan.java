package org.openo.sdno.acbranchservice.model.vxlan.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestNetPortVlan {

	NetPortVlan netPortVlan = new NetPortVlan(null, 0);
	
	@Test
	public void checkGetPort(){
		String port = "port";
		netPortVlan.setPort(port);
		assertEquals(port, netPortVlan.getPort());
	}
	
	@Test
	public void checkGetVlan(){
		int vlan = 1;
		netPortVlan.setVlan(vlan);
		assertEquals(vlan, netPortVlan.getVlan());
	}
}
