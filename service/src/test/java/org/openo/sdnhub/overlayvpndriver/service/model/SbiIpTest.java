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

import org.junit.Test;

public class SbiIpTest {

    @Test
    public void testEqual() {
        SbiIp sbiIp = new SbiIp();
        sbiIp.setIpMask("23");
        sbiIp.setIpv4("12.12.12.12");
        sbiIp.setPrefixLength("23");
        sbiIp.setIpv6("dsdfd");

        SbiIp sbiIp1 = new SbiIp();
        sbiIp1.setIpMask("23");
        sbiIp1.setIpv4("12.12.12.12");
        sbiIp1.setPrefixLength("23");
        sbiIp1.setIpv6("dsdfd");

        SbiIp sbiIp2 = new SbiIp();
        sbiIp2.setIpMask(null);
        sbiIp2.setIpv4(null);
        sbiIp2.setPrefixLength(null);
        sbiIp2.setIpv6(null);

        SbiIp sbiIp3 = new SbiIp();
        sbiIp3.setIpMask("234");
        sbiIp3.setIpv4("12.12.12.12");
        sbiIp3.setPrefixLength("23");
        sbiIp3.setIpv6("dsdfdty");

        SbiIp sbiIp4 = new SbiIp();
        sbiIp4.setIpMask("23");
        sbiIp4.setIpv4("12.12.12.13");
        sbiIp4.setPrefixLength("235");
        sbiIp4.setIpv6("dsdfd");

        SbiIp sbiIp6 = new SbiIp();
        sbiIp6.setIpMask("23");
        sbiIp6.setIpv4("12.12.12.12");
        sbiIp6.setPrefixLength("23");
        sbiIp6.setIpv6("dsdfd345");

        SbiIp sbiIp7 = new SbiIp();
        sbiIp7.setIpMask("23");
        sbiIp7.setIpv4("12.12.12.12");
        sbiIp7.setPrefixLength("236");
        sbiIp7.setIpv6("dsdfd");

        sbiIp2.hashCode();
        sbiIp.hashCode();

        assertTrue(sbiIp.equals(sbiIp1));

        assertTrue(sbiIp.equals(sbiIp));
        assertFalse(sbiIp.equals(null));
        assertFalse(sbiIp.equals(sbiIp3));
        assertFalse(sbiIp.equals(sbiIp4));
        assertFalse(sbiIp.equals(sbiIp6));
        assertFalse(sbiIp.equals(sbiIp7));
        assertFalse(sbiIp.equals(new Object()));

    }

}
