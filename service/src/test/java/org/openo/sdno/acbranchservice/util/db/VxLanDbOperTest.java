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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.model.vxlan.db.VxLanExternalIdMapping;
import org.openo.sdno.overlayvpn.dao.common.InventoryDao;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.inventory.sdk.util.InventoryDaoUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;

import mockit.Mock;
import mockit.MockUp;

public class VxLanDbOperTest {

    @Test
    public <T> void testInsert() throws ServiceException {
        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<List<T>> batchInsert(List<T> dataList) throws ServiceException {
                return new ResultRsp(ErrorCode.OVERLAYVPN_SUCCESS);
            }
        };

        new MockUp<InventoryDaoUtil<T>>() {

            @Mock
            public InventoryDao<T> getInventoryDao() {
                return new InventoryDao<>();
            }
        };

        VxLanDbOper.insert(new VxLanExternalIdMapping("123", "123", "123"));
    }

    @Test
    public <T> void testQuery() throws ServiceException {

        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<List<T>> queryByFilter(Class clazz, String filter, String queryResultFields)
                    throws ServiceException {
                return new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
            }
        };

        new MockUp<InventoryDaoUtil<T>>() {

            @Mock
            public InventoryDao<T> getInventoryDao() {
                return new InventoryDao<>();
            }
        };

        VxLanDbOper.query("123");
    }

    @Test
    public <T> void testQuery_1() throws ServiceException {

        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<List<T>> queryByFilter(Class clazz, String filter, String queryResultFields)
                    throws ServiceException {
                VxLanExternalIdMapping mo = new VxLanExternalIdMapping("123", "123", "123");
                List<VxLanExternalIdMapping> mos = new ArrayList<>();
                mos.add(mo);
                ResultRsp rs = new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
                rs.setData(mos);
                return rs;
            }
        };

        new MockUp<InventoryDaoUtil<T>>() {

            @Mock
            public InventoryDao<T> getInventoryDao() {
                return new InventoryDao<>();
            }
        };

        VxLanDbOper.query("123");
    }

    @Test
    public <T> void testQuery_2() throws ServiceException {

        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<List<T>> queryByFilter(Class clazz, String filter, String queryResultFields)
                    throws ServiceException {
                VxLanExternalIdMapping mo = new VxLanExternalIdMapping("123", "123", "123");
                List<VxLanExternalIdMapping> mos = new ArrayList<>();
                mos.add(mo);
                ResultRsp rs = new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
                rs.setData(mos);
                return rs;
            }
        };

        new MockUp<InventoryDaoUtil<T>>() {

            @Mock
            public InventoryDao<T> getInventoryDao() {
                return new InventoryDao<>();
            }
        };

        VxLanDbOper.query("");
    }

    @Test
    public <T> void testDelete() throws ServiceException {
        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<List<T>> queryByFilter(Class clazz, String filter, String queryResultFields)
                    throws ServiceException {
                List<VxLanExternalIdMapping> mos = new ArrayList<>();
                VxLanExternalIdMapping mo = new VxLanExternalIdMapping("123", "123", "123");
                mos.add(mo);

                ResultRsp rs = new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
                rs.setData(mos);
                return rs;
            }

            @Mock
            public ResultRsp<String> delete(Class clazz, String uuid) throws ServiceException {
                return new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
            }
        };

        new MockUp<InventoryDaoUtil<T>>() {

            @Mock
            public InventoryDao<T> getInventoryDao() {
                return new InventoryDao<>();
            }
        };

        VxLanDbOper.delete("123");
    }

    @Test
    public <T> void testDelete_2() throws ServiceException {
        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<List<T>> queryByFilter(Class clazz, String filter, String queryResultFields)
                    throws ServiceException {
                List<VxLanExternalIdMapping> mos = new ArrayList<>();
                VxLanExternalIdMapping mo = new VxLanExternalIdMapping("123", "123", "123");
                mos.add(mo);

                return new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
            }

            @Mock
            public ResultRsp<String> delete(Class clazz, String uuid) throws ServiceException {
                return new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
            }
        };

        new MockUp<InventoryDaoUtil<T>>() {

            @Mock
            public InventoryDao<T> getInventoryDao() {
                return new InventoryDao<>();
            }
        };

        VxLanDbOper.delete("123");
    }

}
