package org.openo.sdno.acbranchservice.model.ipsec.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestNetRule {
	NetRule netRule = new NetRule(null, null, null, null, null);
	NetRule netRule1 = new NetRule();
	
	@Test
	public void checkGetPolicy(){
		String policy = "policy";
		netRule.setPolicy(policy);
		assertEquals(policy, netRule.getPolicy());
	}
	
	@Test
	public void checkGetSrcIp(){
		String srcIp = "1.1.1.1";
		netRule.setSrcIp(srcIp);
		assertEquals(srcIp, netRule.getSrcIp());
	}
	
	@Test
	public void checkGetSrcNetMask(){
		String srcNetMask = "srcNetMask";
		netRule.setSrcNetMask(srcNetMask);
		assertEquals(srcNetMask, netRule.getSrcNetMask());
	}
	
	@Test
	public void checkGetDesIp(){
		String desIp = "1.1.1.2";
		netRule.setDesIp(desIp);
		assertEquals(desIp, netRule.getDesIp());
	}
	
	@Test
	public void checkGetDesNetMask(){
		String desNetMask = "1.1.1.2";
		netRule.setDesNetMask(desNetMask);
		assertEquals(desNetMask, netRule.getDesNetMask());
	}
}
