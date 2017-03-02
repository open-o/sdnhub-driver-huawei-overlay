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

package org.openo.sdnhub.overlayvpndriver.service.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SbiIfVlanTest {

    SbiIfVlan vlan1 = new SbiIfVlan();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {

        vlan1.setServiceVlanUuId("serviceVlanUuId");
        vlan1.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan1.setIfId("ifId");
        vlan1.setIfName("ifName");
        vlan1.setDefaultVlan(123);
        vlan1.setLinkType("linkType");
        vlan1.setVlans("vlans");
    }

    @Test
    public void testHashCode() {

        SbiIfVlan vlan2 = new SbiIfVlan();

        vlan2.setServiceVlanUuId("serviceVlanUuId");
        vlan2.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan2.setIfId("ifId");
        vlan2.setIfName("ifName");
        vlan2.setDefaultVlan(123);
        vlan2.setLinkType("linkType");
        vlan2.setVlans("vlans");

        vlan2.hashCode();
    }

    @Test
    public void testHashCodeNull() {

        SbiIfVlan vlan3 = new SbiIfVlan();

        vlan3.setServiceVlanUuId(null);
        vlan3.setEthInterfaceConfigId(null);
        vlan3.setIfId(null);
        vlan3.setIfName(null);
        vlan3.setDefaultVlan(null);
        vlan3.setLinkType(null);
        vlan3.setVlans(null);

        vlan3.hashCode();
    }

    @Test
    public void testEquals1() {

        SbiIfVlan vlan4 = new SbiIfVlan();

        vlan4.setServiceVlanUuId("serviceVlanUuId");
        vlan4.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan4.setIfId("ifId");
        vlan4.setIfName("ifName");
        vlan4.setDefaultVlan(123);
        vlan4.setLinkType("linkType");
        vlan4.setVlans("vlans");

        assertTrue(vlan1.equals(vlan4));
    }

    @Test
    public void testEquals2() {

        assertTrue(vlan1.equals(vlan1));
    }

    @Test
    public void testEquals3() {

        assertFalse(vlan1.equals(null));
    }

    @Test
    public void testEquals5() {

        SbiIfVlan vlan5 = new SbiIfVlan();

        vlan5.setServiceVlanUuId("serviceVlanUuIdTest");
        vlan5.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan5.setIfId("ifId");
        vlan5.setIfName("ifName");
        vlan5.setDefaultVlan(123);
        vlan5.setLinkType("linkType");
        vlan5.setVlans("vlans");

        assertFalse(vlan1.equals(vlan5));
    }

    @Test
    public void testEquals6() {

        SbiIfVlan vlan6 = new SbiIfVlan();

        vlan6.setServiceVlanUuId("serviceVlanUuId");
        vlan6.setEthInterfaceConfigId("ethInterfaceConfigIdTest");
        vlan6.setIfId("ifId");
        vlan6.setIfName("ifName");
        vlan6.setDefaultVlan(123);
        vlan6.setLinkType("linkType");
        vlan6.setVlans("vlans");

        assertFalse(vlan1.equals(vlan6));
    }

    @Test
    public void testEquals7() {

        SbiIfVlan vlan7 = new SbiIfVlan();

        vlan7.setServiceVlanUuId("serviceVlanUuId");
        vlan7.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan7.setIfId("ifIdTest");
        vlan7.setIfName("ifName");
        vlan7.setDefaultVlan(123);
        vlan7.setLinkType("linkType");
        vlan7.setVlans("vlans");

        assertTrue(vlan1.equals(vlan7));
    }

    @Test
    public void testEquals8() {

        SbiIfVlan vlan8 = new SbiIfVlan();

        vlan8.setServiceVlanUuId("serviceVlanUuId");
        vlan8.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan8.setIfId("ifId");
        vlan8.setIfName("ifNameTest");
        vlan8.setDefaultVlan(123);
        vlan8.setLinkType("linkType");
        vlan8.setVlans("vlans");

        assertFalse(vlan1.equals(vlan8));
    }

    @Test
    public void testEquals9() {

        SbiIfVlan vlan9 = new SbiIfVlan();

        vlan9.setServiceVlanUuId("serviceVlanUuId");
        vlan9.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan9.setIfId("ifId");
        vlan9.setIfName("ifName");
        vlan9.setDefaultVlan(12345);
        vlan9.setLinkType("linkType");
        vlan9.setVlans("vlans");

        assertFalse(vlan1.equals(vlan9));
    }

    @Test
    public void testEquals10() {

        SbiIfVlan vlan10 = new SbiIfVlan();

        vlan10.setServiceVlanUuId("serviceVlanUuId");
        vlan10.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan10.setIfId("ifId");
        vlan10.setIfName("ifName");
        vlan10.setDefaultVlan(123);
        vlan10.setLinkType("linkTypeTest");
        vlan10.setVlans("vlans");

        assertFalse(vlan1.equals(vlan10));
    }

    @Test
    public void testEquals11() {

        SbiIfVlan vlan11 = new SbiIfVlan();

        vlan11.setServiceVlanUuId("serviceVlanUuId");
        vlan11.setEthInterfaceConfigId("ethInterfaceConfigId");
        vlan11.setIfId("ifId");
        vlan11.setIfName("ifName");
        vlan11.setDefaultVlan(123);
        vlan11.setLinkType("linkType");
        vlan11.setVlans("vlansTest");

        assertFalse(vlan1.equals(vlan11));
    }

    @Test
    public void testEqualsObject() {

        assertFalse(vlan1.equals(new Object()));
    }

}
