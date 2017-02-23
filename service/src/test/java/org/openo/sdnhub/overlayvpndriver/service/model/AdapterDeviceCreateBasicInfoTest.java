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

public class AdapterDeviceCreateBasicInfoTest {

    @Test
    public void testEqual() {
        AdapterDeviceCreateBasicInfo adapterDeviceCreateBasicInfo = new AdapterDeviceCreateBasicInfo();
        adapterDeviceCreateBasicInfo.setDescription("description");
        adapterDeviceCreateBasicInfo.setEsn("esn");
        adapterDeviceCreateBasicInfo.setName("name");
        adapterDeviceCreateBasicInfo.setOrgnizationName("orgnizationName");

        AdapterDeviceInfo adapterDeviceCreateBasicInfo1 = new AdapterDeviceInfo();
        adapterDeviceCreateBasicInfo1.setDescription("description");
        adapterDeviceCreateBasicInfo1.setEsn("esn");
        adapterDeviceCreateBasicInfo1.setName("name");
        adapterDeviceCreateBasicInfo1.setOrgnizationName("orgnizationName");
        adapterDeviceCreateBasicInfo.hashCode();

        AdapterDeviceInfo adapterDeviceCreateBasicInfo2 = new AdapterDeviceInfo();
        adapterDeviceCreateBasicInfo2.setDescription("description");
        adapterDeviceCreateBasicInfo2.setEsn("esn");
        adapterDeviceCreateBasicInfo2.setName("name");
        adapterDeviceCreateBasicInfo2.setOrgnizationName("orgnizationName");

        AdapterDeviceInfo adapterDeviceCreateBasicInfo3 = new AdapterDeviceInfo();
        adapterDeviceCreateBasicInfo3.setDescription("description");
        adapterDeviceCreateBasicInfo3.setEsn(null);
        adapterDeviceCreateBasicInfo3.setName("name");
        adapterDeviceCreateBasicInfo3.setOrgnizationName("orgnizationName");
        adapterDeviceCreateBasicInfo3.hashCode();

        AdapterDeviceInfo adapterDeviceCreateBasicInfo4 = new AdapterDeviceInfo();
        adapterDeviceCreateBasicInfo4.setDescription("description");
        adapterDeviceCreateBasicInfo4.setEsn(null);
        adapterDeviceCreateBasicInfo4.setName("name");
        adapterDeviceCreateBasicInfo4.setOrgnizationName("orgnizationName");

        AdapterDeviceInfo adapterDeviceCreateBasicInfo5 = new AdapterDeviceInfo();
        adapterDeviceCreateBasicInfo5.setDescription("description");
        adapterDeviceCreateBasicInfo5.setEsn("sdsds");
        adapterDeviceCreateBasicInfo5.setName("name");
        adapterDeviceCreateBasicInfo5.setOrgnizationName("orgnizationName");

        assertTrue(adapterDeviceCreateBasicInfo.equals(adapterDeviceCreateBasicInfo));
        assertFalse(adapterDeviceCreateBasicInfo.equals(adapterDeviceCreateBasicInfo2));
        assertFalse(adapterDeviceCreateBasicInfo.equals(null));
        assertTrue(adapterDeviceCreateBasicInfo1.equals(adapterDeviceCreateBasicInfo2));
        assertTrue(adapterDeviceCreateBasicInfo3.equals(adapterDeviceCreateBasicInfo4));
        assertFalse(adapterDeviceCreateBasicInfo3.equals(adapterDeviceCreateBasicInfo2));
        assertFalse(adapterDeviceCreateBasicInfo5.equals(adapterDeviceCreateBasicInfo2));
    }

}
