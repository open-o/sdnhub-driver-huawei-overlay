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

/**
 * Util Class of External System Registration.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-09-19
 */
public class ESRutil {

    private ESRutil() {
    }

    /**
     * Get Controller Detail Information.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @return Controller Information
     * @throws ServiceException throws when query controller failed
     * @since SDNO 0.5
     */
    public static Map getControllerDetails(String ctrlUuid) throws ServiceException {
        String esrurl = "/openoapi/extsys/v1/sdncontrollers/" + ctrlUuid;
        final RestfulParametes restfulParametes = new RestfulParametes();
        RestfulResponse response = RestfulProxy.get(esrurl, restfulParametes);
        return JsonUtil.fromJson(response.getResponseContent(), Map.class);
    }

}
