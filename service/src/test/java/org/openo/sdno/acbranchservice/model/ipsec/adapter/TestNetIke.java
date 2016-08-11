package org.openo.sdno.acbranchservice.model.ipsec.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestNetIke {

	NetIke netIke = new NetIke(null, null, null, null, null);
	NetIke netIke1 = new NetIke();
	
	@Test
	public void checkGetAuthAlgorithm(){
		String authAlgorithm = "authAlgorithm";
		netIke.setAuthAlgorithm(authAlgorithm);
		assertEquals(authAlgorithm, netIke.getAuthAlgorithm());
	}
	
	@Test
	public void checkGetEncryptionAlgorithm(){
		String encryptionAlgorithm = "encryptionAlgorithm";
		netIke.setEncryptionAlgorithm(encryptionAlgorithm);
		assertEquals(encryptionAlgorithm, netIke.getEncryptionAlgorithm());
	}
	
	@Test
	public void checkGetVersion(){
		String version = "version";
		netIke.setVersion(version);
		assertEquals(version, netIke.getVersion());
	}
	
	@Test
	public void checkGetLocalAddress(){
		String localAddress = "localAddress";
		netIke.setLocalAddress(localAddress);
		assertEquals(localAddress, netIke.getLocalAddress());
	}
	
	@Test
	public void checkGetPeerAddress(){
		String peerAddress = "peerAddress";
		netIke.setPeerAddress(peerAddress);
		assertEquals(peerAddress, netIke.getPeerAddress());
	}
	
	@Test
	public void checkGetPreSharedKey(){
		String preSharedKey = "preSharedKey";
		netIke.setPreSharedKey(preSharedKey);
		assertEquals(preSharedKey, netIke.getPreSharedKey());
	}
}
