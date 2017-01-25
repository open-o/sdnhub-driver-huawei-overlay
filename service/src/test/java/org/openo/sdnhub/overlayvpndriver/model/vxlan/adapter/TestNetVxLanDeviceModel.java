/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.model.vxlan.adapter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.model.vxlan.adapter.NetVni;
import org.openo.sdnhub.overlayvpndriver.model.vxlan.adapter.NetVxLanDeviceModel;

public class TestNetVxLanDeviceModel {

	NetVxLanDeviceModel netVxLanDeviceModel = new NetVxLanDeviceModel();

	@Test
	public void checkGetVneId() {
		int vneId = 1;
		netVxLanDeviceModel.setVneId(vneId);
		assertEquals(vneId, netVxLanDeviceModel.getVneId());
	}

	@Test
	public void checkGetName() {
		String name = "name";
		netVxLanDeviceModel.setName(name);
		assertEquals(name, netVxLanDeviceModel.getName());
	}

	@Test
	public void checkGetLocalAddress() {
		String localAddress = "localAddress";
		netVxLanDeviceModel.setLocalAddress(localAddress);
		assertEquals(localAddress, netVxLanDeviceModel.getLocalAddress());
	}

	@Test
	public void checkGetVniilist() {
		List<NetVni> vniilist = new ArrayList<>();
		netVxLanDeviceModel.setVniilist(vniilist);
		assertEquals(vniilist, netVxLanDeviceModel.getVniilist());
	}
}
