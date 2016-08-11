package org.openo.sdno.acbranchservice.model.ipsec.db;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestIpSecExternalIdMapping {
	IpSecExternalIdMapping ipSecExternalIdMapping = new IpSecExternalIdMapping(null, null, null, null);

	@Test
	public void checkGetIpSecConnectionId(){
		String ipSecConnectionId = "ipSecConnectionId";
		ipSecExternalIdMapping.setIpSecConnectionId(ipSecConnectionId);
		assertEquals(ipSecConnectionId, ipSecExternalIdMapping.getIpSecConnectionId());
	}
	
	@Test
	public void checkGetExternalId(){
		String externalId = "externalId";
		ipSecExternalIdMapping.setExternalId(externalId);
		assertEquals(externalId, ipSecExternalIdMapping.getExternalId());
	}
	
	@Test
	public void checkGetSeqNumber(){
		String seqNumber = "seqNumber";
		ipSecExternalIdMapping.setSeqNumber(seqNumber);
		assertEquals(seqNumber, ipSecExternalIdMapping.getSeqNumber());
	}
	
	@Test
	public void checkGetDeviceId(){
		String deviceId = "deviceId";
		ipSecExternalIdMapping.setDeviceId(deviceId);
		assertEquals(deviceId, ipSecExternalIdMapping.getDeviceId());
	}
}
