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

package org.openo.sdnhub.overlayvpndriver.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultRspUtilTest {

    @Test
    public void parserRspDataListTestNormal() {
        ResultRspUtil<TestObj> util = new ResultRspUtil<>();

        List<Map<String, String>> dataObj = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("id", "12345");
        dataObj.add(map);

        TestObj info = new TestObj();
        List<TestObj> adptList = util.parserRspDataList(dataObj, info);
        assertEquals(adptList.get(0).getId(), "12345");
    }

    public static class TestObj {

        private String id = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
}
