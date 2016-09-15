/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.overlayvpndriver.model.vxlan.db;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openo.sdno.overlayvpndriver.model.vxlan.db.VxLanExternalIdMapping;

public class TestVxLanExternalIdMapping {

	VxLanExternalIdMapping vxLanExternalIdMapping = new VxLanExternalIdMapping(null, null, null);

	@Test
	public void checkGetVxLanInstanceId() {
		String vxLanInstanceId = "vxLanInstanceId";
		vxLanExternalIdMapping.setVxLanInstanceId(vxLanInstanceId);
		assertEquals(vxLanInstanceId, vxLanExternalIdMapping.getVxLanInstanceId());
	}

	@Test
	public void checkGetExternalId() {
		String externalId = "externalId";
		vxLanExternalIdMapping.setExternalId(externalId);
		assertEquals(externalId, vxLanExternalIdMapping.getExternalId());
	}

	@Test
	public void checkGetDeviceId() {
		String deviceId = "deviceId";
		vxLanExternalIdMapping.setDeviceId(deviceId);
		assertEquals(deviceId, vxLanExternalIdMapping.getDeviceId());
	}
}
