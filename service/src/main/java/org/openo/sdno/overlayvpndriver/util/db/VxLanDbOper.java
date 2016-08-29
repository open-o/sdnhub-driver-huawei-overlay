/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.overlayvpndriver.util.db;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.inventory.sdk.util.InventoryDaoUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpndriver.model.vxlan.db.VxLanExternalIdMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * It is used to operate VxLanExternalIdMapping table. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jun 16, 2016
 */
public class VxLanDbOper {

    private static final String VXLAN_INSTANCE_ID = "vxLanInstanceId";

    private static final String UUID = "uuid";

    private static final Logger LOGGER = LoggerFactory.getLogger(VxLanDbOper.class);

    /**
     * Constructor<br/>
     * 
     * @since SDNO 0.5
     */
    private VxLanDbOper() {

    }

    /**
     * It is used to insert data. <br/>
     * 
     * @param vxLanExternalIdMapping VxLan external id mapping info
     * @throws ServiceException When insert failed.
     * @since SDNO 0.5
     */
    public static void insert(VxLanExternalIdMapping vxLanExternalIdMapping) throws ServiceException {

        new InventoryDaoUtil<VxLanExternalIdMapping>().getInventoryDao().insert(vxLanExternalIdMapping);
    }

    /**
     * It is used to query data. <br/>
     * 
     * @param vxLanInstanceId The VxLan instance id that passed by server
     * @return The object of VxLanExternalIdMapping
     * @throws ServiceException When query failed
     * @since SDNO 0.5
     */
    public static VxLanExternalIdMapping query(String vxLanInstanceId) throws ServiceException {
        ResultRsp<List<VxLanExternalIdMapping>> queryDbRsp = queryByFilter(vxLanInstanceId, null);
        if(CollectionUtils.isEmpty(queryDbRsp.getData())) {
            LOGGER.warn("query error, vxLanInstanceId (" + vxLanInstanceId + ") is not found");
            return null;
        }

        return JsonUtil.fromJson(JsonUtil.toJson(queryDbRsp.getData().get(0)), VxLanExternalIdMapping.class);
    }

    /**
     * It is used to delete data. <br/>
     * 
     * @param vxLanInstanceId The VxLan instance id that passed by server
     * @throws ServiceException When delete failed.
     * @since SDNO 0.5
     */
    public static void delete(String vxLanInstanceId) throws ServiceException {
        ResultRsp<List<VxLanExternalIdMapping>> queryDbRsp = queryByFilter(vxLanInstanceId, UUID);
        if(CollectionUtils.isEmpty(queryDbRsp.getData())) {
            LOGGER.warn("delete error, vxLanInstanceId (" + vxLanInstanceId + ") is not found");
            return;
        }

        VxLanExternalIdMapping vxLanExternalIdMapping = queryDbRsp.getData().get(0);
        new InventoryDaoUtil<VxLanExternalIdMapping>().getInventoryDao().delete(VxLanExternalIdMapping.class,
                vxLanExternalIdMapping.getUuid());
    }

    private static ResultRsp<List<VxLanExternalIdMapping>> queryByFilter(String vxLanInstanceId,
            String queryResultFields) throws ServiceException {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(vxLanInstanceId)) {
            filterMap.put(VXLAN_INSTANCE_ID, Arrays.asList(vxLanInstanceId));
        }

        String filter = JSONObject.fromObject(filterMap).toString();

        return new InventoryDaoUtil<VxLanExternalIdMapping>().getInventoryDao()
                .queryByFilter(VxLanExternalIdMapping.class, filter, queryResultFields);
    }
}
