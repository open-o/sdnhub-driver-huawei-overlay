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

package org.openo.sdno.acbranchservice.util.db;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.model.ipsec.db.IpSecExternalIdMapping;
import org.openo.sdno.overlayvpn.inventory.sdk.util.InventoryDaoUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * It is used to operate IpSecExternalIdMapping table. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jun 16, 2016
 */
public class IpSecDbOper {

    private static final String IPSEC_CONNECTION_ID = "ipSecConnectionId";

    private static final String UUID = "uuid";

    private static final Logger LOGGER = LoggerFactory.getLogger(IpSecDbOper.class);

    /**
     * Constructor<br/>
     * 
     * @since SDNO 0.5
     */
    private IpSecDbOper() {

    }

    /**
     * It is used to insert data. <br/>
     * 
     * @param ipSecExternalIdMapping IpSec external id mapping info
     * @throws ServiceException When insert failed.
     * @since SDNO 0.5
     */
    public static void insert(IpSecExternalIdMapping ipSecExternalIdMapping) throws ServiceException {

        new InventoryDaoUtil<IpSecExternalIdMapping>().getInventoryDao().insert(ipSecExternalIdMapping);
    }

    /**
     * It is used to query data. <br/>
     * 
     * @param ipSecConnectionId The IPSec connection id that passed by server
     * @return The object of IpSecExternalIdMapping
     * @throws ServiceException When query failed
     * @since SDNO 0.5
     */
    public static IpSecExternalIdMapping query(String ipSecConnectionId) throws ServiceException {
        ResultRsp<List<IpSecExternalIdMapping>> queryDbRsp = queryByFilter(ipSecConnectionId, null);
        if(CollectionUtils.isEmpty(queryDbRsp.getData())) {
            LOGGER.warn("query error, ipSecConnectionId (" + ipSecConnectionId + ") is not found");
            return null;
        }

        return queryDbRsp.getData().get(0);
    }

    /**
     * It is used to delete data. <br/>
     * 
     * @param ipSecConnectionId The IPSec connection id that passed by server
     * @throws ServiceException When delete failed.
     * @since SDNO 0.5
     */
    public static void delete(String ipSecConnectionId) throws ServiceException {
        ResultRsp<List<IpSecExternalIdMapping>> queryDbRsp = queryByFilter(ipSecConnectionId, UUID);
        if(CollectionUtils.isEmpty(queryDbRsp.getData())) {
            LOGGER.warn("delete error, ipSecConnectionId (" + ipSecConnectionId + ") is not found");
            return;
        }

        IpSecExternalIdMapping ipSecExternalIdMapping = queryDbRsp.getData().get(0);
        new InventoryDaoUtil<IpSecExternalIdMapping>().getInventoryDao().delete(IpSecExternalIdMapping.class,
                ipSecExternalIdMapping.getUuid());
    }

    private static ResultRsp<List<IpSecExternalIdMapping>> queryByFilter(String ipSecConnectionId,
            String queryResultFields) throws ServiceException {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(ipSecConnectionId)) {
            filterMap.put(IPSEC_CONNECTION_ID, Arrays.asList(ipSecConnectionId));
        }

        String filter = JSONObject.fromObject(filterMap).toString();

        return new InventoryDaoUtil<IpSecExternalIdMapping>().getInventoryDao()
                .queryByFilter(IpSecExternalIdMapping.class, filter, queryResultFields);
    }
}
