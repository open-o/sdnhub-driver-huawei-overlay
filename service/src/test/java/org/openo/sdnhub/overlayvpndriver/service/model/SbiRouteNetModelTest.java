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


public class SbiRouteNetModelTest {

    SbiRouteNetModel srnm = new SbiRouteNetModel();


    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {

        srnm.setControllerId("646644534");
        srnm.setDeviceId("95446563");
        srnm.setExternalId("674668553");
        srnm.setNbiNeRouteId("664656956");

    }

    @Test
    public void testEqualsObject() {

        SbiRouteNetModel srnm1 = new SbiRouteNetModel();

        srnm1.setControllerId("646644534");
        srnm1.setDeviceId("95446563");
        srnm1.setExternalId("674668553");
        srnm1.setNbiNeRouteId("664656956");

        assertTrue(srnm.equals(srnm1));
        assertFalse(srnm.equals(null));
        assertFalse(srnm.equals(new Object()));
        srnm1.hashCode();

    }

    @Test
    public void testEqualsObjectSame() {

        SbiRouteNetModel srnm1 = new SbiRouteNetModel();

        srnm1.setControllerId("646644534");
        srnm1.setDeviceId("95446563");
        srnm1.setExternalId("674668553");
        srnm1.setNbiNeRouteId("664656956");

        assertTrue(srnm.equals(srnm));

    }

    @Test
    public void testEqualsObjectCid() {

        SbiRouteNetModel srnm1 = new SbiRouteNetModel();

        srnm1.setControllerId("946532");
        srnm1.setDeviceId("95446563");
        srnm1.setExternalId("674668553");
        srnm1.setNbiNeRouteId("664656956");

        assertFalse(srnm.equals(srnm1));
        srnm1.hashCode();

    }

    @Test
    public void testEqualsObjectDid() {

        SbiRouteNetModel srnm1 = new SbiRouteNetModel();

        srnm1.setControllerId("646644534");
        srnm1.setDeviceId("75688566");
        srnm1.setExternalId("674668553");
        srnm1.setNbiNeRouteId("664656956");

        assertFalse(srnm.equals(srnm1));
        srnm1.hashCode();

    }

    @Test
    public void testEqualsObjectEid() {

        SbiRouteNetModel srnm1 = new SbiRouteNetModel();

        srnm1.setControllerId("646644534");
        srnm1.setDeviceId("95446563");
        srnm1.setExternalId("46842256");
        srnm1.setNbiNeRouteId("664656956");

        assertFalse(srnm.equals(srnm1));
        srnm1.hashCode();

    }

    @Test
    public void testEqualsObjectNrid() {

        SbiRouteNetModel srnm1 = new SbiRouteNetModel();

        srnm1.setControllerId("646644534");
        srnm1.setDeviceId("95446563");
        srnm1.setExternalId("674668553");
        srnm1.setNbiNeRouteId("87565645");

        assertFalse(srnm.equals(srnm1));
        srnm1.hashCode();

    }

    @Test
    public void testToString() {

        SbiRouteNetModel srnm1 = new SbiRouteNetModel();

        srnm1.toString();

    }

}
