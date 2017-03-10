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

public class SbiIkePolicyTest {

    SbiIkePolicy sip = new SbiIkePolicy();

    /**
     * <br/>
     *
     * @throws Exception
     *             setup failure exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() throws Exception {

        sip.setAuthAlgorithm("authAlgorithm");
        sip.setEncryptionAlgorithm("encryptionAlgorithm");
        sip.setIkeVersion("ikeVersion");
        sip.setPsk("psk");
    }

    @Test
    public void testEqualsObject() {

        SbiIkePolicy sip1 = new SbiIkePolicy();

        sip1.setAuthAlgorithm("authAlgorithm");
        sip1.setEncryptionAlgorithm("encryptionAlgorithm");
        sip1.setIkeVersion("ikeVersion");
        sip1.setPsk("psk");

        assertTrue(sip.equals(sip1));
        assertFalse(sip.equals(null));
        assertFalse(sip.equals(new Object()));
        sip1.hashCode();

    }

    @Test
    public void testEqualsObjectSame() {

        SbiIkePolicy sip = new SbiIkePolicy();

        sip.setAuthAlgorithm("authAlgorithm");
        sip.setEncryptionAlgorithm("encryptionAlgorithm");
        sip.setIkeVersion("ikeVersion");
        sip.setPsk("psk");

        assertTrue(sip.equals(sip));
        sip.hashCode();

    }

    @Test
    public void testEqualsObjectAa() {

        SbiIkePolicy sip1 = new SbiIkePolicy();

        sip1.setAuthAlgorithm("normalAlgroitham");
        sip1.setEncryptionAlgorithm("encryptionAlgorithm");
        sip1.setIkeVersion("ikeVersion");
        sip1.setPsk("psk");

        assertFalse(sip.equals(sip1));
        sip1.hashCode();

    }

    @Test
    public void testEqualsObjectEa() {

        SbiIkePolicy sip1 = new SbiIkePolicy();

        sip1.setAuthAlgorithm("authAlgorithm");
        sip1.setEncryptionAlgorithm("noramlencrption");
        sip1.setIkeVersion("ikeVersion");
        sip1.setPsk("psk");

        assertFalse(sip.equals(sip1));
        sip1.hashCode();

    }

    @Test
    public void testEqualsObjectIv() {

        SbiIkePolicy sip1 = new SbiIkePolicy();

        sip1.setAuthAlgorithm("authAlgorithm");
        sip1.setEncryptionAlgorithm("encryptionAlgorithm");
        sip1.setIkeVersion("normalversion");
        sip1.setPsk("psk");

        assertFalse(sip.equals(sip1));
        sip1.hashCode();

    }

    @Test
    public void testEqualsObjectPs() {

        SbiIkePolicy sip1 = new SbiIkePolicy();

        sip1.setAuthAlgorithm("authAlgorithm");
        sip1.setEncryptionAlgorithm("encryptionAlgorithm");
        sip1.setIkeVersion("ikeVersion");
        sip1.setPsk("normal");

        assertFalse(sip.equals(sip1));
        sip1.hashCode();

    }

}
