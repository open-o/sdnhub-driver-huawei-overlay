/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.controller.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class IpsecConnListTest {

    @Test
    public void testEqual() {

        IpsecConnList ipSecList = new IpsecConnList();
        ipSecList.setCreateFlag(true);
        ipSecList.setInterfaceName("interfaceName");
        ipSecList.setName("name");
        ipSecList.setServiceId("serviceId");
        ipSecList.setUuid("uuid");
        List<IpsecConnection> ipsecConnection = new LinkedList<>();
        IpsecConnection ipSecCon = new IpsecConnection();
        ipSecCon.setType("type");
        ipsecConnection.add(ipSecCon);
        ipSecList.setIpsecConnection(ipsecConnection);

        IpsecConnList ipSecList1 = new IpsecConnList();
        ipSecList1.setCreateFlag(true);
        ipSecList1.setInterfaceName("interfaceName");
        ipSecList1.setName("name");
        ipSecList1.setServiceId("serviceId");
        ipSecList1.setUuid("uuid");
        List<IpsecConnection> ipsecConnection1 = new LinkedList<>();
        IpsecConnection ipSecCon1 = new IpsecConnection();
        ipSecCon1.setType("type");
        ipsecConnection1.add(ipSecCon1);
        ipSecList1.setIpsecConnection(ipsecConnection1);

        IpsecConnList ipSecList2 = new IpsecConnList();
        ipSecList2.setCreateFlag(false);
        ipSecList2.setInterfaceName(null);
        ipSecList2.setName(null);
        ipSecList2.setServiceId(null);
        ipSecList2.setUuid(null);
        List<IpsecConnection> ipsecConnection2 = new LinkedList<>();
        IpsecConnection ipSecCon2 = new IpsecConnection();
        ipSecCon2.setType(null);
        ipsecConnection2.add(ipSecCon2);
        ipSecList2.setIpsecConnection(ipsecConnection2);

        IpsecConnList ipSecList3 = new IpsecConnList();
        ipSecList3.setCreateFlag(false);
        ipSecList3.setInterfaceName("interfaceName");
        ipSecList3.setName("name");
        ipSecList3.setServiceId("serviceId");
        ipSecList3.setUuid("uuid");
        List<IpsecConnection> ipsecConnection3 = new LinkedList<>();
        IpsecConnection ipSecCon3 = new IpsecConnection();
        ipSecCon3.setType("type");
        ipsecConnection3.add(ipSecCon3);
        ipSecList3.setIpsecConnection(ipsecConnection3);

        IpsecConnList ipSecList4 = new IpsecConnList();
        ipSecList4.setCreateFlag(true);
        ipSecList4.setInterfaceName("interfaceNames");
        ipSecList4.setName("name");
        ipSecList4.setServiceId("serviceId");
        ipSecList4.setUuid("uuid");
        List<IpsecConnection> ipsecConnection4 = new LinkedList<>();
        IpsecConnection ipSecCon4 = new IpsecConnection();
        ipSecCon4.setType("type");
        ipsecConnection4.add(ipSecCon4);
        ipSecList4.setIpsecConnection(ipsecConnection4);

        IpsecConnList ipSecList5 = new IpsecConnList();
        ipSecList5.setCreateFlag(true);
        ipSecList5.setInterfaceName("interfaceName");
        ipSecList5.setName("names");
        ipSecList5.setServiceId("serviceId");
        ipSecList5.setUuid("uuid");
        List<IpsecConnection> ipsecConnection5 = new LinkedList<>();
        IpsecConnection ipSecCon5 = new IpsecConnection();
        ipSecCon5.setType("type");
        ipsecConnection5.add(ipSecCon5);
        ipSecList5.setIpsecConnection(ipsecConnection5);

        IpsecConnList ipSecList6 = new IpsecConnList();
        ipSecList6.setCreateFlag(true);
        ipSecList6.setInterfaceName("interfaceName");
        ipSecList6.setName("name");
        ipSecList6.setServiceId("serviceIds");
        ipSecList6.setUuid("uuid");
        List<IpsecConnection> ipsecConnection6 = new LinkedList<>();
        IpsecConnection ipSecCon6 = new IpsecConnection();
        ipSecCon6.setType("type");
        ipsecConnection6.add(ipSecCon6);
        ipSecList6.setIpsecConnection(ipsecConnection6);

        IpsecConnList ipSecList7 = new IpsecConnList();
        ipSecList7.setCreateFlag(true);
        ipSecList7.setInterfaceName("interfaceName");
        ipSecList7.setName("name");
        ipSecList7.setServiceId("serviceId");
        ipSecList7.setUuid("uuids");
        List<IpsecConnection> ipsecConnection7 = new LinkedList<>();
        IpsecConnection ipSecCon7 = new IpsecConnection();
        ipSecCon7.setType("type");
        ipsecConnection7.add(ipSecCon7);
        ipSecList7.setIpsecConnection(ipsecConnection7);

        IpsecConnList ipSecList8 = new IpsecConnList();
        ipSecList8.setCreateFlag(true);
        ipSecList8.setInterfaceName("interfaceName");
        ipSecList8.setName("name");
        ipSecList8.setServiceId("serviceId");
        ipSecList8.setUuid("uuid");
        List<IpsecConnection> ipsecConnection8 = new LinkedList<>();
        IpsecConnection ipSecCon8 = new IpsecConnection();
        ipSecCon8.setType("types");
        ipsecConnection8.add(ipSecCon8);
        ipSecList8.setIpsecConnection(ipsecConnection8);

        ipSecList1.hashCode();
        ipSecList2.hashCode();

        ipSecList1.toString();
        ipSecList2.toString();

        assertFalse(ipSecList.equals(ipSecList1));
        assertTrue(ipSecList.equals(ipSecList));
        assertFalse(ipSecList.equals(null));

        assertFalse(ipSecList.equals(ipSecList3));
        assertFalse(ipSecList.equals(ipSecList4));
        assertFalse(ipSecList.equals(ipSecList5));
        assertFalse(ipSecList.equals(ipSecList6));
        assertFalse(ipSecList.equals(ipSecList7));
        assertFalse(ipSecList.equals(ipSecList8));

        assertFalse(ipSecList.equals(new Object()));

    }

}
