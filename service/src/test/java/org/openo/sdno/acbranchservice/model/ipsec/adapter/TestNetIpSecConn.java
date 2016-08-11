package org.openo.sdno.acbranchservice.model.ipsec.adapter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestNetIpSecConn {

	NetIpSecConn netIpSecConn = new NetIpSecConn();
	
	@Test
	public void checkGetIpSecConnectionId(){
		String ipSecConnectionId = "ipSecConnectionId";
		netIpSecConn.setIpSecConnectionId(ipSecConnectionId);
		assertEquals(ipSecConnectionId, netIpSecConn.getIpSecConnectionId());
	}
	
	@Test
	public void checkGetDeleteMode(){
		boolean deleteMode = true;
		netIpSecConn.setDeleteMode(deleteMode);
		assertEquals(deleteMode, netIpSecConn.isDeleteMode());
	}
	
	@Test
	public void checkGetType(){
		String type = "type";
		netIpSecConn.setType(type);
		assertEquals(type, netIpSecConn.getType());
	}
	
	@Test
	public void checkRouteInject(){
		String routeInject = "routeInject";
		netIpSecConn.setRouteInject(routeInject);
		assertEquals(routeInject, netIpSecConn.getRouteInject());
	}
	
	@Test
	public void checkSeqNumber(){
		int seqNumber = 1;
		netIpSecConn.setSeqNumber(seqNumber);
		assertEquals(seqNumber, netIpSecConn.getSeqNumber());
	}
	
	@Test
	public void checkRuleList(){
		List<NetRule> ruleList = new ArrayList<>();
		netIpSecConn.setRuleList(ruleList);
		assertEquals(ruleList, netIpSecConn.getRuleList());
	}
	
	@Test
	public void checkIpsec(){
		NetIpSec ipSec= new NetIpSec();
		ipSec.setEspAuthAlgorithm("espAuthAlgorithm");
		netIpSecConn.setIpSec(ipSec);
		assertEquals(ipSec.getEspAuthAlgorithm(), netIpSecConn.getIpSec().getEspAuthAlgorithm());
	}
	
	@Test
	public void checkIke(){
		NetIke ike= new NetIke();
		ike.setLocalAddress("1.1.1.1");
		netIpSecConn.setIke(ike);
		assertEquals(ike.getLocalAddress(), netIpSecConn.getIke().getLocalAddress());
	}
}
