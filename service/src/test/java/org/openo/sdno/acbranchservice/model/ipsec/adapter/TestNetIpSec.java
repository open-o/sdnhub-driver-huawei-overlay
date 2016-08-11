package org.openo.sdno.acbranchservice.model.ipsec.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestNetIpSec {

	NetIpSec netIpSec = new NetIpSec(null, null);
	NetIpSec netIpSec2 = new NetIpSec();
	
	@Test
	public void checkGetEspAuthAlgorithm(){
		String espAuthAlgorithm = "espAuthAlgorithm";
		netIpSec.setEspAuthAlgorithm(espAuthAlgorithm);
		assertEquals(espAuthAlgorithm, netIpSec.getEspAuthAlgorithm());
	}
	
	@Test
	public void checkGetEspEncryptionAlgorithm(){
		String espEncryptionAlgorithm = "espEncryptionAlgorithm";
		netIpSec.setEspEncryptionAlgorithm(espEncryptionAlgorithm);
		assertEquals(espEncryptionAlgorithm, netIpSec.getEspEncryptionAlgorithm());
	}
}
