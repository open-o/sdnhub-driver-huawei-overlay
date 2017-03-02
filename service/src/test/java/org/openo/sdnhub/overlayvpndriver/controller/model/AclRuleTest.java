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

import org.junit.Before;
import org.junit.Test;

public class AclRuleTest {

    AclRule acl1 = new AclRule();

    Ip ip1 = new Ip();

    Ip ip2 = new Ip();
    
    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {

        acl1.setPolicy("setPolicy");
        ip1.setIpMask("ipMask");
        ip1.setIpv4("ipv4");
        acl1.setSrcIp(ip1);
        ip2.setIpMask("ipMask");
        acl1.setDesIp(ip2);
    }

    @Test
    public void testEqual1() {

        AclRule acl2 = new AclRule();
        acl2.setPolicy("setPolicy");
        Ip ip3 = new Ip();
        ip3.setIpMask("ipMask");
        ip3.setIpv4("ipv4");
        acl2.setSrcIp(ip3);
        Ip ip4 = new Ip();
        ip4.setIpMask("ipMask");
        acl2.setDesIp(ip4);

        assertTrue(acl1.equals(acl2));
    }

    @Test
    public void testhashCodeNull() {
        AclRule acl3 = new AclRule();
        acl3.setPolicy(null);
        acl3.setSrcIp(null);
        acl3.setDesIp(null);

        acl3.hashCode();
    }

    @Test
    public void testhashCode() {

        AclRule acl2 = new AclRule();
        acl2.setPolicy("setPolicy");
        Ip ip3 = new Ip();
        ip3.setIpMask("ipMask");
        ip3.setIpv4("ipv4");
        acl2.setSrcIp(ip3);
        Ip ip4 = new Ip();
        ip4.setIpMask("ipMask");
        acl2.setDesIp(ip4);

        acl2.hashCode();
    }

    @Test
    public void testEqual2() {

        AclRule acl4 = new AclRule();
        acl4.setPolicy("setPolicyTest");
        Ip ip5 = new Ip();
        ip5.setIpMask("ipMask");
        ip5.setIpv4("ipv4");
        acl4.setSrcIp(ip5);
        Ip ip6 = new Ip();
        ip6.setIpMask("ipMask");
        acl4.setDesIp(ip6);

        assertFalse(acl1.equals(acl4));
    }

    @Test
    public void testEqual3() {

        AclRule acl5 = new AclRule();
        acl5.setPolicy("setPolicy");
        Ip ip7 = new Ip();
        ip7.setIpMask("ipMaskTest");
        ip7.setIpv4("ipv4Test");
        acl5.setSrcIp(ip7);
        Ip ip8 = new Ip();
        ip8.setIpMask("ipMask");
        acl5.setDesIp(ip8);

        assertFalse(acl1.equals(acl5));
    }

    @Test
    public void testEqual4() {

        AclRule acl6 = new AclRule();
        acl6.setPolicy("setPolicy");
        Ip ip9 = new Ip();
        ip9.setIpMask("ipMask");
        ip9.setIpv4("ipv4");
        acl6.setSrcIp(ip9);
        Ip ip10 = new Ip();
        ip10.setIpMask("ipMaskTest");
        acl6.setDesIp(ip10);

        assertFalse(acl1.equals(acl6));
    }

    @Test
    public void testEqual5() {
        assertTrue(acl1.equals(acl1));
    }

    @Test
    public void testEqualNUll() {
        assertFalse(acl1.equals(null));
    }

    @Test
    public void testEqualObject() {
        assertFalse(acl1.equals(new Object()));
    }

}
