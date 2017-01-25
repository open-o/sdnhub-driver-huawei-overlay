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

package org.openo.sdnhub.overlayvpndriver.model.port;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.model.port.NetAcDevicePort;

public class TestNetAcDevicePort {

	NetAcDevicePort netAcDevicePort = new NetAcDevicePort();

	@Test
	public void checkGetId() {
		String id = "test";
		netAcDevicePort.setId(id);
		assertEquals(id, netAcDevicePort.getId());
	}

	@Test
	public void checkName() {
		String name = "test";
		netAcDevicePort.setName(name);
		assertEquals(name, netAcDevicePort.getName());
	}

	@Test
	public void checkGetRunStatus() {
		int runStatus = 1;
		netAcDevicePort.setRunStatus(runStatus);
		assertEquals(runStatus, netAcDevicePort.getRunStatus());
	}

	@Test
	public void checkGetMgrStatus() {
		int mgrStatus = 1;
		netAcDevicePort.setMgrStatus(mgrStatus);
		assertEquals(mgrStatus, netAcDevicePort.getMgrStatus());
	}

	@Test
	public void checkGetIndex() {
		String index = "index";
		netAcDevicePort.setIndex(index);
		assertEquals(index, netAcDevicePort.getIndex());
	}

	@Test
	public void checkGetAlias() {
		String alias = "alias";
		netAcDevicePort.setAlias(alias);
		assertEquals(alias, netAcDevicePort.getAlias());
	}

	@Test
	public void checkGetIPAddress() {
		String ipAddress = "1.1.1.1";
		netAcDevicePort.setIpAddr(ipAddress);
		assertEquals(ipAddress, netAcDevicePort.getIpAddr());
	}

	@Test
	public void checkGetBandWith() {
		String bandWith = "1kbps";
		netAcDevicePort.setBandWith(bandWith);
		assertEquals(bandWith, netAcDevicePort.getBandWith());
	}

	@Test
	public void checkGetMtu() {
		int mtu = 1;
		netAcDevicePort.setMtu(mtu);
		assertEquals(mtu, netAcDevicePort.getMtu());
	}

	@Test
	public void checkGetReport() {
		boolean report = true;
		netAcDevicePort.setReport(report);
		assertEquals(report, netAcDevicePort.isReport());
	}

	@Test
	public void checkGetMode() {
		int mode = 1;
		netAcDevicePort.setMode(mode);
		assertEquals(mode, netAcDevicePort.getMode());
	}

	@Test
	public void checkGetIccid() {
		String iccid = "iccid";
		netAcDevicePort.setIccid(iccid);
		assertEquals(iccid, netAcDevicePort.getIccid());
	}

	@Test
	public void checkGetMac() {
		String mac = "00:00:00:00:00:00";
		netAcDevicePort.setMac(mac);
		assertEquals(mac, netAcDevicePort.getMac());
	}

	@Test
	public void checkGetIpv6Addr() {
		String ipv6Addr = "1.1.1.1.1.1";
		netAcDevicePort.setIpv6Addr(ipv6Addr);
		assertEquals(ipv6Addr, netAcDevicePort.getIpv6Addr());
	}

	@Test
	public void checkGetTerminationMode() {
		String terminationMode = "terminationMode";
		netAcDevicePort.setTerminationMode(terminationMode);
		assertEquals(terminationMode, netAcDevicePort.getTerminationMode());
	}

	@Test
	public void checkGetCeLowVlan() {
		String ceLowVlan = "ceLowVlan";
		netAcDevicePort.setCeLowVlan(ceLowVlan);
		assertEquals(ceLowVlan, netAcDevicePort.getCeLowVlan());
	}

	@Test
	public void checkGetCeHighVlan() {
		String ceHighVlan = "ceHighVlan";
		netAcDevicePort.setCeHighVlan(ceHighVlan);
		assertEquals(ceHighVlan, netAcDevicePort.getCeHighVlan());
	}

	@Test
	public void checkGetPeVlan() {
		String peVlan = "peVlan";
		netAcDevicePort.setPeVlan(peVlan);
		assertEquals(peVlan, netAcDevicePort.getPeVlan());
	}

	@Test
	public void checkGetMask() {
		String mask = "mask";
		netAcDevicePort.setMask(mask);
		assertEquals(mask, netAcDevicePort.getMask());
	}

	@Test
	public void checkGetPrefixLength() {
		String prefixLength = "prefixLength";
		netAcDevicePort.setPrefixLength(prefixLength);
		assertEquals(prefixLength, netAcDevicePort.getPrefixLength());
	}

}
