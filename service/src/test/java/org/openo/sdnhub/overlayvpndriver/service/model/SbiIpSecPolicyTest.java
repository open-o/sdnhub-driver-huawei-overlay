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

public class SbiIpSecPolicyTest {

    SbiIpSecPolicy sisp = new SbiIpSecPolicy();

    /**
     * <br/>
     *
     * @throws Exception
     *             setup failure exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() throws Exception {

        sisp.setAuthAlgorithm("algoritham");
        sisp.setEncapsulationMode("encapsulationMode");
        sisp.setEncryptionAlgorithm("encryptionAlgorithm");
        sisp.setTransformProtocol("transformProtocol");

    }

    @Test
    public void testEqualsObject() {

        SbiIpSecPolicy sisp1 = new SbiIpSecPolicy();

        sisp1.setAuthAlgorithm("algoritham");
        sisp1.setEncapsulationMode("encapsulationMode");
        sisp1.setEncryptionAlgorithm("encryptionAlgorithm");
        sisp1.setTransformProtocol("transformProtocol");

        assertTrue(sisp.equals(sisp1));
        assertFalse(sisp.equals(null));
        assertFalse(sisp.equals(new Object()));
        sisp1.hashCode();

    }

    @Test
    public void testEqualsObjectSame() {

        SbiIpSecPolicy sisp = new SbiIpSecPolicy();

        sisp.setAuthAlgorithm("algoritham");
        sisp.setEncapsulationMode("encapsulationMode");
        sisp.setEncryptionAlgorithm("encryptionAlgorithm");
        sisp.setTransformProtocol("transformProtocol");

        assertTrue(sisp.equals(sisp));
        sisp.hashCode();

    }

    @Test
    public void testEqualsObjectAa() {

        SbiIpSecPolicy sisp1 = new SbiIpSecPolicy();

        sisp1.setAuthAlgorithm("randomalgoritham");
        sisp1.setEncapsulationMode("encapsulationMode");
        sisp1.setEncryptionAlgorithm("encryptionAlgorithm");
        sisp1.setTransformProtocol("transformProtocol");

        assertFalse(sisp.equals(sisp1));
        sisp1.hashCode();

    }

    @Test
    public void testEqualsObjectEm() {

        SbiIpSecPolicy sisp1 = new SbiIpSecPolicy();

        sisp1.setAuthAlgorithm("algoritham");
        sisp1.setEncapsulationMode("Emmode");
        sisp1.setEncryptionAlgorithm("encryptionAlgorithm");
        sisp1.setTransformProtocol("transformProtocol");

        assertFalse(sisp.equals(sisp1));
        sisp1.hashCode();

    }

    @Test
    public void testEqualsObjectEa() {

        SbiIpSecPolicy sisp1 = new SbiIpSecPolicy();

        sisp1.setAuthAlgorithm("algoritham");
        sisp1.setEncapsulationMode("encapsulationMode");
        sisp1.setEncryptionAlgorithm("EmodeAlgoritham");
        sisp1.setTransformProtocol("transformProtocol");

        assertFalse(sisp.equals(sisp1));
        sisp1.hashCode();

    }

    @Test
    public void testEqualsObjectTp() {

        SbiIpSecPolicy sisp1 = new SbiIpSecPolicy();

        sisp1.setAuthAlgorithm("algoritham");
        sisp1.setEncapsulationMode("encapsulationMode");
        sisp1.setEncryptionAlgorithm("encryptionAlgorithm");
        sisp1.setTransformProtocol("normalprotocal");

        assertFalse(sisp.equals(sisp1));
        sisp1.hashCode();

    }

}
