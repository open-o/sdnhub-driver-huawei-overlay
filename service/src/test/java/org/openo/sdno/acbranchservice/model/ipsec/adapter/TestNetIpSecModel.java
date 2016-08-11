package org.openo.sdno.acbranchservice.model.ipsec.adapter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestNetIpSecModel {

	
	NetIpSecModel netIpSecModel = new NetIpSecModel(null);
	NetIpSecModel netIpSecModel1 = new NetIpSecModel();
	
	@Test
	public void checkGetCreateFlag(){
		boolean createFlag = true;
		netIpSecModel.setCreateFlag(createFlag);
		assertEquals(createFlag, netIpSecModel.isCreateFlag());
	}
	
	@Test
	public void checkGetName(){
		String name = "name";
		netIpSecModel.setName(name);
		assertEquals(name, netIpSecModel.getName());
	}
	
	@Test
	public void checkGetInterfaceName(){
		String interfaceName = "interfaceName";
		netIpSecModel.setInterfaceName(interfaceName);
		assertEquals(interfaceName, netIpSecModel.getInterfaceName());
	}
	
	@Test
	public void checkGetIpsecConnection(){
		List<NetIpSecConn> ipsecConnection = new ArrayList<>();
		netIpSecModel.setIpsecConnection(ipsecConnection);
		assertEquals(ipsecConnection, netIpSecModel.getIpsecConnection());
	}
}
