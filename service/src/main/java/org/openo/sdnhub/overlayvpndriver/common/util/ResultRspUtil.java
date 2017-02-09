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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * <p>
 * </p>
 * 
 * @param <T>
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class ResultRspUtil<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultRspUtil.class);

    Class<T> tClass;

    /**
     * <br/>
     * 
     * @param dataObj
     * @param t
     * @return
     * @since SDNHUB 0.5
     */
    public List<T> parserRspDataList(Object dataObj, T t) {
        List<T> greRspDataList = new ArrayList<T>();
        if(dataObj instanceof List) {
            List dataList = (List)dataObj;
            for(int i = 0; i < dataList.size(); i++) {
                if(dataList.get(i) instanceof Map) {
                    Map dataObjMap = (Map)dataList.get(i);
                    T greTunnel = parser(dataObjMap, t.getClass());
                    greRspDataList.add(greTunnel);
                }
            }
        }
        return greRspDataList;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private T parser(Map fieldMap, Class class1) {
        Field[] fields = class1.getDeclaredFields();

        T objt;
        try {
            objt = (T)class1.newInstance();
            for(Field field : fields) {
                if(!fieldMap.containsKey(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                Object filedObj = fieldMap.get(field.getName());

                if(filedObj instanceof Map) {
                    field.set(objt, parser((Map)filedObj, field.getType()));
                }

                else {
                    field.set(objt, fieldMap.get(field.getName()));
                }
            }
            return objt;
        } catch(InstantiationException e) {
            LOGGER.error("parser " + class1.getName() + " from " + fieldMap
                    + " failed for field is wrong or new instance failed.", e);
        } catch(IllegalAccessException e) {
            LOGGER.error("parser " + class1.getName() + " from " + fieldMap
                    + " failed for field is wrong or new instance failed.", e);
        }
        return null;
    }

}
