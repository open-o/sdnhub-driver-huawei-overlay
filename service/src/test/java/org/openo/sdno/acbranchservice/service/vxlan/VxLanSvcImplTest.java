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

package org.openo.sdno.acbranchservice.service.vxlan;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.login.AcBranchProxy;
import org.openo.sdno.acbranchservice.login.AcBranchResponse;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.acbranchservice.model.vxlan.db.VxLanExternalIdMapping;
import org.openo.sdno.acbranchservice.util.controller.ControllerUtil;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.dao.common.InventoryDao;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.inventory.sdk.util.InventoryDaoUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class VxLanSvcImplTest {

    @Test
    public void testCreateVxLan() throws ServiceException {
        new MockUp<AcBranchProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctlrUuid) throws IOException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                AcBranchResponse<List<NetVxLanDeviceModel>> res = new AcBranchResponse<List<NetVxLanDeviceModel>>();
                NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
                List<NetVxLanDeviceModel> mos = new ArrayList<>();
                mos.add(mo);
                res.setData(mos);

                res.setErrcode("0");
                msg.setBody(JsonUtil.toJson(res));
                return msg;
            }

        };

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

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
                List<NetVxLanDeviceModel> mos = new ArrayList<>();
                mos.add(mo);

                return mos;

            }
        };
        NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
        List<NetVxLanDeviceModel> mos = new ArrayList<>();
        mos.add(mo);
        ResultRsp<List<NetVxLanDeviceModel>> result = VxLanSvcImpl.createVxLan("123", "123", mos);

        assertTrue(result.isSuccess());
    }

    @Test
    public void testCreateVxLan_noUUId() throws ServiceException {
        NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
        List<NetVxLanDeviceModel> mos = new ArrayList<>();
        mos.add(mo);

        try {
            ResultRsp<List<NetVxLanDeviceModel>> result = VxLanSvcImpl.createVxLan("", "", mos);
        } catch(Exception e) {
            assertTrue(e instanceof ServiceException);
        }

    }

    @Test
    public void testCreateVxLan_noDeviceId() throws ServiceException {
        NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
        List<NetVxLanDeviceModel> mos = new ArrayList<>();
        mos.add(mo);
        try {
            ResultRsp<List<NetVxLanDeviceModel>> result = VxLanSvcImpl.createVxLan("123", "", mos);
        } catch(Exception e) {
            assertTrue(e instanceof ServiceException);
        }

    }

    @Test
    public void testDeleteVxLan() throws ServiceException {
        new MockUp<InventoryDao<T>>() {

            public ResultRsp<List<T>> queryByFilter(Class clazz, String filter, String queryResultFields)
                    throws ServiceException {
                List<VxLanExternalIdMapping> mos = new ArrayList<>();
                VxLanExternalIdMapping mo = new VxLanExternalIdMapping("123", "123", "123");
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

        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<String> delete(Class clazz, String uuid) throws ServiceException {
                return new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
            }
        };

        new MockUp<AcBranchProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctlrUuid) throws IOException {
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                AcBranchResponse<List<String>> res = new AcBranchResponse<List<String>>();
                res.setData(new ArrayList<String>());
                res.setErrcode("0");
                msg.setBody(JsonUtil.toJson(res));
                return msg;
            }

        };
        VxLanSvcImpl.deleteVxLan("123", "123");
    }

    @Test
    public void testDeleteVxLan_Invalid() throws ServiceException {
        new MockUp<InventoryDao<T>>() {

            public ResultRsp<List<T>> queryByFilter(Class clazz, String filter, String queryResultFields)
                    throws ServiceException {
                List<VxLanExternalIdMapping> mos = new ArrayList<>();
                VxLanExternalIdMapping mo = new VxLanExternalIdMapping("123", "123", "123");
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

        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<String> delete(Class clazz, String uuid) throws ServiceException {
                return new ResultRsp<List<VxLanExternalIdMapping>>().SUCCESS;
            }
        };

        VxLanSvcImpl.deleteVxLan("123", "123");
    }

}
