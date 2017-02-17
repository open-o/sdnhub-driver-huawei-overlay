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
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;

public class ResultUtilTest {

    @Test
    public void testGetErrorCode() throws ServiceException {
        Map<String, String> configInfoMap = new HashMap<>();
        configInfoMap.put("errorcode", "200");
        ResultUtil.getErrorCode(configInfoMap);
        assertEquals(ResultUtil.getErrorCode(configInfoMap), 0);
    }

    @Test
    public void testGetErrorCode_1() throws ServiceException {
        Map<String, String> configInfoMap = new HashMap<>();
        configInfoMap.put("errcode", "200");
        assertEquals(ResultUtil.getErrorCode(configInfoMap), 200);
    }

    @Test
    public void testGetErrorCode_2() throws ServiceException {
        Map<String, String> configInfoMap = new HashMap<>();
        configInfoMap.put("errcode", null);
        assertEquals(ResultUtil.getErrorCode(configInfoMap), 0);
    }

    @Test
    public void testGetErrorCode_3() throws ServiceException {
        Map<String, Integer> configInfoMap = new HashMap<>();
        configInfoMap.put("errcode", 123);
        assertEquals(ResultUtil.getErrorCode(configInfoMap), 0);
    }

    @Test
    public void testGetErrorCodebk() throws ServiceException {
        Map<String, Integer> configInfoMap = new HashMap<>();
        configInfoMap.put("errcode", 123);
        assertEquals(ResultUtil.getErrorCodebk(configInfoMap), 0);
    }

    @Test
    public void testGetErrorCodebk_1() throws ServiceException {
        Map<String, Integer> configInfoMap = new HashMap<>();
        configInfoMap.put("errcode", null);
        assertEquals(ResultUtil.getErrorCodebk(configInfoMap), 0);
    }

   @Test
    public void testGetErrorMsg() throws ServiceException {
        Map<String, Integer> configInfoMap = new HashMap<>();
        configInfoMap.put("errcodes", null);
        assertEquals(ResultUtil.getErrorMsg(configInfoMap), "");
    }

    @Test
    public void testGetErrorMsg_1() throws ServiceException {
        Map<String, String> configInfoMap = new HashMap<>();
        configInfoMap.put("errmsg", "string");
        assertEquals(ResultUtil.getErrorMsg(configInfoMap), "string");
    }

    @Test
    public void testGetErrorMsg_2() throws ServiceException {
        Map<String, Integer> configInfoMap = new HashMap<>();
        configInfoMap.put("errmsg", 123);
        assertEquals(ResultUtil.getErrorMsg(configInfoMap), "");
    }

    
    @Test
    public void testGetErrorMsg_3() throws ServiceException {
        Map<String, String> configInfoMap = new HashMap<>();
        configInfoMap.put("errmsg", null);
        assertEquals(ResultUtil.getErrorMsg(configInfoMap), "");
    }
    
    @Test
    public void parserACResponse() throws ServiceException {
        assertEquals(ResultUtil.parserACResponse(""), null);
    }
    
    @Test
    public void parserACResponse_1() throws ServiceException {
        assertTrue(ResultUtil.parserACResponse("{ip:123}") != null);
    }
    
}
