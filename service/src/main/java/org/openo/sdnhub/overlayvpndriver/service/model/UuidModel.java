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

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.framework.container.util.UuidUtils;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

public class UuidModel {

    @JsonProperty(value = "id")
    @AUuid
    protected String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void copyBasicData(UuidModel data){
        this.uuid = data.getUuid();
    }

    public void allocateUuid(UuidModel data){
        if(!UuidUtil.validate(uuid)){
            setUuid(UuidUtils.createUuid());
        }
    }

    public void checkUuid() throws ServiceException {
        UuidUtils.checkUuid(uuid);
    }

}
