package org.openo.sdno.acbranchservice.model.wan;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNetSubInterfaceIp {

	NetWanSubInterfaceIp netWanSubInterfaceIp = new NetWanSubInterfaceIp();
	
	@Test
	public void getInterfaceName(){
		String interfaceName = "interfaceName";
		netWanSubInterfaceIp.setInterfaceName(interfaceName);
		assertEquals(interfaceName, netWanSubInterfaceIp.getInterfaceName());
	}
	
	@Test
	public void getMode(){
		String mode = "mode";
		netWanSubInterfaceIp.setMode(mode);
		assertEquals(mode, netWanSubInterfaceIp.getMode());
	}
	
	@Test
	public void getIp(){
		String ip = "1.1.1.1";
		netWanSubInterfaceIp.setIp(ip);
		assertEquals(ip, netWanSubInterfaceIp.getIp());
	}
	
	@Test
	public void getNetmask(){
		String netmask = "netmask";
		netWanSubInterfaceIp.setNetmask(netmask);
		assertEquals(netmask, netWanSubInterfaceIp.getNetmask());
	}
	
	@Test
	public void getMode6(){
		String mode6 = "mode6";
		netWanSubInterfaceIp.setMode6(mode6);
		assertEquals(mode6, netWanSubInterfaceIp.getMode6());
	}
	
	@Test
	public void getIpv6address(){
		String ipv6address = "ipv6address";
		netWanSubInterfaceIp.setIpv6address(ipv6address);
		assertEquals(ipv6address, netWanSubInterfaceIp.getIpv6address());
	}
	
	@Test
	public void getPrifexlength(){
		String prifexlength = "prifexlength";
		netWanSubInterfaceIp.setPrifexlength(prifexlength);
		assertEquals(prifexlength, netWanSubInterfaceIp.getPrifexlength());
	}
}
