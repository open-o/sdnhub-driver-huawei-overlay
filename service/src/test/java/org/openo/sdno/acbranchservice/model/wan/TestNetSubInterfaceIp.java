/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.acbranchservice.model.wan;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestNetSubInterfaceIp {

    NetWanSubInterfaceIp netWanSubInterfaceIp = new NetWanSubInterfaceIp();

    @Test
    public void getInterfaceName() {
        String interfaceName = "interfaceName";
        netWanSubInterfaceIp.setInterfaceName(interfaceName);
        assertEquals(interfaceName, netWanSubInterfaceIp.getInterfaceName());
    }

    @Test
    public void getMode() {
        String mode = "mode";
        netWanSubInterfaceIp.setMode(mode);
        assertEquals(mode, netWanSubInterfaceIp.getMode());
    }

    @Test
    public void getIp() {
        String ip = "1.1.1.1";
        netWanSubInterfaceIp.setIp(ip);
        assertEquals(ip, netWanSubInterfaceIp.getIp());
    }

    @Test
    public void getNetmask() {
        String netmask = "netmask";
        netWanSubInterfaceIp.setNetmask(netmask);
        assertEquals(netmask, netWanSubInterfaceIp.getNetmask());
    }

    @Test
    public void getMode6() {
        String mode6 = "mode6";
        netWanSubInterfaceIp.setMode6(mode6);
        assertEquals(mode6, netWanSubInterfaceIp.getMode6());
    }

    @Test
    public void getIpv6address() {
        String ipv6address = "ipv6address";
        netWanSubInterfaceIp.setIpv6address(ipv6address);
        assertEquals(ipv6address, netWanSubInterfaceIp.getIpv6address());
    }

    @Test
    public void getPrifexlength() {
        String prifexlength = "prifexlength";
        netWanSubInterfaceIp.setPrifexlength(prifexlength);
        assertEquals(prifexlength, netWanSubInterfaceIp.getPrifexlength());
    }
}
