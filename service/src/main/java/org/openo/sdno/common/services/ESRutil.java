/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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
package org.openo.sdno.common.services;

import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;
import org.openo.sdno.framework.container.util.JsonUtil;

public class ESRutil {
    
    private ESRutil(){}
    
    public static Map getControllerDetails(String ctrlUuid) throws ServiceException {
        String esrurl = "/openoapi/extsys/v1/sdncontrollers/" + ctrlUuid;
        final RestfulParametes restfulParametes = new RestfulParametes();
        RestfulResponse response = RestfulProxy.get(esrurl, restfulParametes);

        return (Map)JsonUtil.fromJson(response.getResponseContent(), Map.class);

    }

}
