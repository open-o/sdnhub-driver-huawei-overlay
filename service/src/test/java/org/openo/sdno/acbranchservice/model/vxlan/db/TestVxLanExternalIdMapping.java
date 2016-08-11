package org.openo.sdno.acbranchservice.model.vxlan.db;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestVxLanExternalIdMapping {
	VxLanExternalIdMapping vxLanExternalIdMapping = new VxLanExternalIdMapping(null, null, null);
	
	
	@Test
	public void checkGetVxLanInstanceId(){
		String vxLanInstanceId = "vxLanInstanceId";
		vxLanExternalIdMapping.setVxLanInstanceId(vxLanInstanceId);
		assertEquals(vxLanInstanceId, vxLanExternalIdMapping.getVxLanInstanceId());
	}
	
	@Test
	public void checkGetExternalId(){
		String externalId = "externalId";
		vxLanExternalIdMapping.setExternalId(externalId);
		assertEquals(externalId, vxLanExternalIdMapping.getExternalId());
	}
	
	@Test
	public void checkGetDeviceId(){
		String deviceId = "deviceId";
		vxLanExternalIdMapping.setDeviceId(deviceId);
		assertEquals(deviceId, vxLanExternalIdMapping.getDeviceId());
	}
}
