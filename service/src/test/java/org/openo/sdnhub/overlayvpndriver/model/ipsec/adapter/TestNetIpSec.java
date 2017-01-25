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

package org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSec;

public class TestNetIpSec {

	NetIpSec netIpSec = new NetIpSec(null, null);

	NetIpSec netIpSec2 = new NetIpSec();

	@Test
	public void checkGetEspAuthAlgorithm() {
		String espAuthAlgorithm = "espAuthAlgorithm";
		netIpSec.setEspAuthAlgorithm(espAuthAlgorithm);
		assertEquals(espAuthAlgorithm, netIpSec.getEspAuthAlgorithm());
	}

	@Test
	public void checkGetEspEncryptionAlgorithm() {
		String espEncryptionAlgorithm = "espEncryptionAlgorithm";
		netIpSec.setEspEncryptionAlgorithm(espEncryptionAlgorithm);
		assertEquals(espEncryptionAlgorithm, netIpSec.getEspEncryptionAlgorithm());
	}
}
