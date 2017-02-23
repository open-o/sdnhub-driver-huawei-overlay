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

package org.openo.sdnhub.overlayvpndriver.result;


import mockit.Mock;
import mockit.MockUp;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ACDelResponseTest {

    ACDelResponse acDelResponse = new ACDelResponse();

    @Test
    public void testGetAllErrmsg() {
        new MockUp<StringUtils>() {

            @Mock
            public boolean isNotEmpty(String str) {
                return false;

            }
        };
        acDelResponse.getAllErrmsg();
    }

    @Test
    public void testGetAllErrmsg1() {
        new MockUp<StringUtils>() {

            @Mock
            public boolean isNotEmpty(String str) {
                return true;

            }
        };
        acDelResponse.getAllErrmsg();
    }

    @Test
    public void testGetAllErrmsg11() {
        new MockUp<CollectionUtils>() {

            @Mock
            public boolean isNotEmpty(Collection<?> coll) {
                return true;

            }
        };
        List<DataDto> fail = new ArrayList<>();
        DataDto dataDto = new DataDto();
        dataDto.setErrmsg("success");
        fail.add(dataDto);
        acDelResponse.setFail(fail);
        acDelResponse.getAllErrmsg();
    }

    @Test
    public void testGetAllErrmsg111() {
        new MockUp<CollectionUtils>() {

            @Mock
            public boolean isNotEmpty(Collection<?> coll) {
                return true;

            }
        };
        List<DataDto> fail = new ArrayList<>();
        DataDto dataDto = new DataDto();
        dataDto.setErrmsg("");
        fail.add(dataDto);
        acDelResponse.setFail(fail);
        acDelResponse.getAllErrmsg();
    }

    @Test
    public void testGetAllErrmsg12() {
        new MockUp<CollectionUtils>() {

            @Mock
            public boolean isNotEmpty(Collection<?> coll) {
                return false;

            }
        };
        acDelResponse.getAllErrmsg();
    }
}
