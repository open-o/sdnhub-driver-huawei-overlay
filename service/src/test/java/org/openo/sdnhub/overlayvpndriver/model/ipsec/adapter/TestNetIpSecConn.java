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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIke;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSec;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetRule;

public class TestNetIpSecConn {

	NetIpSecConn netIpSecConn = new NetIpSecConn();

	@Test
	public void checkGetIpSecConnectionId() {
		String ipSecConnectionId = "ipSecConnectionId";
		netIpSecConn.setIpSecConnectionId(ipSecConnectionId);
		assertEquals(ipSecConnectionId, netIpSecConn.getIpSecConnectionId());
	}

	@Test
	public void checkGetDeleteMode() {
		boolean deleteMode = true;
		netIpSecConn.setDeleteMode(deleteMode);
		assertEquals(deleteMode, netIpSecConn.isDeleteMode());
	}

	@Test
	public void checkGetType() {
		String type = "type";
		netIpSecConn.setType(type);
		assertEquals(type, netIpSecConn.getType());
	}

	@Test
	public void checkRouteInject() {
		String routeInject = "routeInject";
		netIpSecConn.setRouteInject(routeInject);
		assertEquals(routeInject, netIpSecConn.getRouteInject());
	}

	@Test
	public void checkSeqNumber() {
		int seqNumber = 1;
		netIpSecConn.setSeqNumber(seqNumber);
		assertEquals(seqNumber, netIpSecConn.getSeqNumber());
	}

	@Test
	public void checkRuleList() {
		List<NetRule> ruleList = new ArrayList<>();
		netIpSecConn.setRuleList(ruleList);
		assertEquals(ruleList, netIpSecConn.getRuleList());
	}

	@Test
	public void checkIpsec() {
		NetIpSec ipSec = new NetIpSec();
		ipSec.setEspAuthAlgorithm("espAuthAlgorithm");
		netIpSecConn.setIpSec(ipSec);
		assertEquals(ipSec.getEspAuthAlgorithm(), netIpSecConn.getIpSec().getEspAuthAlgorithm());
	}

	@Test
	public void checkIke() {
		NetIke ike = new NetIke();
		ike.setLocalAddress("1.1.1.1");
		netIpSecConn.setIke(ike);
		assertEquals(ike.getLocalAddress(), netIpSecConn.getIke().getLocalAddress());
	}
}
