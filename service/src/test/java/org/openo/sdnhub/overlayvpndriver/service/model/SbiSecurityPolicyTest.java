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

public class SbiSecurityPolicyTest {

    SbiSecurityPolicy ssp = new SbiSecurityPolicy();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {

        ssp.setExternalId("6235663");
        ssp.setLifeTime("LifeTime");
        ssp.setPfs("pfs");
        ssp.setSbiServiceId("644566666");
        ssp.setUuid("64683455");

    }

    @Test
    public void testEqualsObject() {

        SbiSecurityPolicy ssp1 = new SbiSecurityPolicy();

        ssp1.setExternalId("6235663");
        ssp1.setLifeTime("LifeTime");
        ssp1.setPfs("pfs");
        ssp1.setSbiServiceId("644566666");
        ssp1.setUuid("64683455");

        assertTrue(ssp.equals(ssp1));
        assertTrue(ssp.equals(ssp));
        assertFalse(ssp.equals(null));
        assertFalse(ssp.equals(new Object()));
        ssp1.hashCode();

    }
    
   
    @Test
    public void testEqualsObjectEid() {

        SbiSecurityPolicy ssp1 = new SbiSecurityPolicy();

        ssp1.setExternalId("8465653");
        ssp1.setLifeTime("LifeTime");
        ssp1.setPfs("pfs");
        ssp1.setSbiServiceId("644566666");
        ssp1.setUuid("64683455");

        assertFalse(ssp.equals(ssp1));
        ssp1.hashCode();

    }

    @Test
    public void testEqualsObjectLt() {

        SbiSecurityPolicy ssp1 = new SbiSecurityPolicy();

        ssp1.setExternalId("6235663");
        ssp1.setLifeTime("ihafjkae");
        ssp1.setPfs("pfs");
        ssp1.setSbiServiceId("644566666");
        ssp1.setUuid("64683455");

        assertFalse(ssp.equals(ssp1));
        ssp1.hashCode();

    }

    @Test
    public void testEqualsObjectPf() {

        SbiSecurityPolicy ssp1 = new SbiSecurityPolicy();

        ssp1.setExternalId("6235663");
        ssp1.setLifeTime("LifeTime");
        ssp1.setPfs("ihkhkl");
        ssp1.setSbiServiceId("644566666");
        ssp1.setUuid("64683455");

        assertFalse(ssp.equals(ssp1));
        ssp1.hashCode();

    }

    @Test
    public void testEqualsObjectSsi() {

        SbiSecurityPolicy ssp1 = new SbiSecurityPolicy();

        ssp1.setExternalId("6235663");
        ssp1.setLifeTime("LifeTime");
        ssp1.setPfs("pfs");
        ssp1.setSbiServiceId("6476564685");
        ssp1.setUuid("64683455");

        assertFalse(ssp.equals(ssp1));
        ssp1.hashCode();

    }

    @Test
    public void testEqualsObjectUid() {

        SbiSecurityPolicy ssp1 = new SbiSecurityPolicy();

        ssp1.setExternalId("6235663");
        ssp1.setLifeTime("LifeTime");
        ssp1.setPfs("pfs");
        ssp1.setSbiServiceId("644566666");
        ssp1.setUuid("976565665");

        assertFalse(ssp.equals(ssp1));
        ssp1.hashCode();

    }

}
