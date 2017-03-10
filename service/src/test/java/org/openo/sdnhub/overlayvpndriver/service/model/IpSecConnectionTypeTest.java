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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IpSecConnectionTypeTest {

    @Test
    public void testValidateName() {

        boolean responce = IpSecConnectionType.validateName("jui");

        assertEquals(responce, false);
    }

    @Test
    public void testValidateNameSame() {

        boolean responce = IpSecConnectionType.validateName("work");

        assertEquals(responce, true);
    }

    @Test
    public void testGetName() {

        String str = "";
        IpSecConnectionType[] values = IpSecConnectionType.values();
        for (IpSecConnectionType ist : values) {
            if (ist.getName().equals("work")) {
                str = ist.getName();
            }
        }
        assertEquals(str, "work");
    }

    @Test
    public void testGetNameadd() {

        String str = "";
        IpSecConnectionType[] values = IpSecConnectionType.values();
        for (IpSecConnectionType ist : values) {
            if (ist.getName().equals("project")) {
                str = ist.getName();
            }
        }
        assertEquals(str, "project");
    }

    @Test
    public void testGetNameDefault() {

        String str = "";
        IpSecConnectionType[] values = IpSecConnectionType.values();
        for (IpSecConnectionType ist : values) {
            if (ist.getName().equals("")) {
                str = ist.getName();
            }
        }
        assertEquals(str, "");
    }


}
