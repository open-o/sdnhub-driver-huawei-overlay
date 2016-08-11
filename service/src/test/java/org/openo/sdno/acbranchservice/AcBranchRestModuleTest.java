package org.openo.sdno.acbranchservice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openo.sdno.overlayvpn.inventory.sdk.DbOwerInfo;

import mockit.MockUp;

public class AcBranchRestModuleTest {

	@Test
	public void testInit() {
		new MockUp<DbOwerInfo>() {
			public void init(String ower, String dbName) {
				
			}
		};
		new AcBranchRestModule().init();
	}

	@Test
	public void testDestroy() {
		new AcBranchRestModule().destroy();		
	}

}
