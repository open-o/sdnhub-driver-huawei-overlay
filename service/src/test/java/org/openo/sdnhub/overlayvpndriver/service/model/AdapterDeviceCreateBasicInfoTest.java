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

public class AdapterDeviceCreateBasicInfoTest {

    AdapterDeviceCreateBasicInfo info1 = new AdapterDeviceCreateBasicInfo();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {
        info1.setName("name");
        info1.setEsn("esn");
        info1.setOrgnizationName("orgnizationName");
        info1.setDescription("description");

    }

    @Test
    public void testHashCode() {
        AdapterDeviceCreateBasicInfo info2 = new AdapterDeviceCreateBasicInfo();

        info2.setName("name");
        info2.setEsn("esn");
        info2.setOrgnizationName("orgnizationName");
        info2.setDescription("description");

        info2.hashCode();
    }

    @Test
    public void testHashCodeNull() {
        AdapterDeviceCreateBasicInfo info3 = new AdapterDeviceCreateBasicInfo();

        info3.setName(null);
        info3.setEsn(null);
        info3.setOrgnizationName(null);
        info3.setDescription(null);

        info3.hashCode();
    }

    @Test
    public void testEquals1() {
        AdapterDeviceCreateBasicInfo info4 = new AdapterDeviceCreateBasicInfo();

        info4.setName("name");
        info4.setEsn("esn");
        info4.setOrgnizationName("orgnizationName");
        info4.setDescription("description");

        assertTrue(info1.equals(info4));
    }

    @Test
    public void testEquals2() {

        assertTrue(info1.equals(info1));
    }

    @Test
    public void testEquals3() {

        assertFalse(info1.equals(null));
    }

    @Test
    public void testEquals5() {
        AdapterDeviceCreateBasicInfo info5 = new AdapterDeviceCreateBasicInfo();

        info5.setName("nameTest");
        info5.setEsn("esn");
        info5.setOrgnizationName("orgnizationName");
        info5.setDescription("description");

        assertFalse(info1.equals(info5));
    }

    @Test
    public void testEquals6() {
        AdapterDeviceCreateBasicInfo info6 = new AdapterDeviceCreateBasicInfo();

        info6.setName("name");
        info6.setEsn("esnTest");
        info6.setOrgnizationName("orgnizationName");
        info6.setDescription("description");

        assertFalse(info1.equals(info6));
    }

    @Test
    public void testEquals7() {
        AdapterDeviceCreateBasicInfo info7 = new AdapterDeviceCreateBasicInfo();

        info7.setName("name");
        info7.setEsn("esn");
        info7.setOrgnizationName("orgnizationNameTest");
        info7.setDescription("description");

        assertFalse(info1.equals(info7));
    }

    @Test
    public void testEquals8() {
        AdapterDeviceCreateBasicInfo info8 = new AdapterDeviceCreateBasicInfo();

        info8.setName("name");
        info8.setEsn("esn");
        info8.setOrgnizationName("orgnizationName");
        info8.setDescription("descriptionTest");

        assertFalse(info1.equals(info8));
    }

    @Test
    public void testEquals9() {
        assertFalse(info1.equals(new Object()));
    }

}
