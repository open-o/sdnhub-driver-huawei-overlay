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

package org.openo.sdno.acbranchservice.model.ipsec.adapter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestNetIpSecModel {

    NetIpSecModel netIpSecModel = new NetIpSecModel(null);

    NetIpSecModel netIpSecModel1 = new NetIpSecModel();

    @Test
    public void checkGetCreateFlag() {
        boolean createFlag = true;
        netIpSecModel.setCreateFlag(createFlag);
        assertEquals(createFlag, netIpSecModel.isCreateFlag());
    }

    @Test
    public void checkGetName() {
        String name = "name";
        netIpSecModel.setName(name);
        assertEquals(name, netIpSecModel.getName());
    }

    @Test
    public void checkGetInterfaceName() {
        String interfaceName = "interfaceName";
        netIpSecModel.setInterfaceName(interfaceName);
        assertEquals(interfaceName, netIpSecModel.getInterfaceName());
    }

    @Test
    public void checkGetIpsecConnection() {
        List<NetIpSecConn> ipsecConnection = new ArrayList<>();
        netIpSecModel.setIpsecConnection(ipsecConnection);
        assertEquals(ipsecConnection, netIpSecModel.getIpsecConnection());
    }
}
