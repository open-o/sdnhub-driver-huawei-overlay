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
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIke;

public class TestNetIke {

	NetIke netIke = new NetIke(null, null, null, null, null);

	NetIke netIke1 = new NetIke();

	@Test
	public void checkGetAuthAlgorithm() {
		String authAlgorithm = "authAlgorithm";
		netIke.setAuthAlgorithm(authAlgorithm);
		assertEquals(authAlgorithm, netIke.getAuthAlgorithm());
	}

	@Test
	public void checkGetEncryptionAlgorithm() {
		String encryptionAlgorithm = "encryptionAlgorithm";
		netIke.setEncryptionAlgorithm(encryptionAlgorithm);
		assertEquals(encryptionAlgorithm, netIke.getEncryptionAlgorithm());
	}

	@Test
	public void checkGetVersion() {
		String version = "version";
		netIke.setVersion(version);
		assertEquals(version, netIke.getVersion());
	}

	@Test
	public void checkGetLocalAddress() {
		String localAddress = "localAddress";
		netIke.setLocalAddress(localAddress);
		assertEquals(localAddress, netIke.getLocalAddress());
	}

	@Test
	public void checkGetPeerAddress() {
		String peerAddress = "peerAddress";
		netIke.setPeerAddress(peerAddress);
		assertEquals(peerAddress, netIke.getPeerAddress());
	}

	@Test
	public void checkGetPreSharedKey() {
		String preSharedKey = "preSharedKey";
		netIke.setPreSharedKey(preSharedKey);
		assertEquals(preSharedKey, netIke.getPreSharedKey());
	}
}
