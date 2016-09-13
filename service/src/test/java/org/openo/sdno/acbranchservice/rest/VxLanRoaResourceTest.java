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

package org.openo.sdno.acbranchservice.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.login.AcBranchProxy;
import org.openo.sdno.acbranchservice.login.AcBranchResponse;
import org.openo.sdno.acbranchservice.model.port.NetAcDevicePort;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.acbranchservice.model.vxlan.db.VxLanExternalIdMapping;
import org.openo.sdno.acbranchservice.service.wan.WanInfSvcImpl;
import org.openo.sdno.acbranchservice.util.controller.ControllerUtil;
import org.openo.sdno.acbranchservice.util.db.VxLanDbOper;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.dao.common.InventoryDao;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.inventory.sdk.util.InventoryDaoUtil;
import org.openo.sdno.overlayvpn.model.netmodel.vxlan.NeVtep;
import org.openo.sdno.overlayvpn.model.netmodel.vxlan.NeVxlanInstance;
import org.openo.sdno.overlayvpn.model.netmodel.vxlan.NeVxlanInterface;
import org.openo.sdno.overlayvpn.model.netmodel.vxlan.NeVxlanTunnel;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class VxLanRoaResourceTest {

    @Test
    public void testQueryVtep() throws ServiceException {

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                WanSubInterface mo = new WanSubInterface();
                mo.setCeLowVlan(123);
                List<WanSubInterface> mos = new ArrayList<>();
                mos.add(mo);

                return mos;

            }
        };

        new MockUp<WanInfSvcImpl>() {

            @Mock
            public List<NetAcDevicePort> queryPorts(List<String> interfaceNameList, String deviceId, String ctrlUuid) {
                List<NetAcDevicePort> list = new ArrayList<>();
                NetAcDevicePort netAcDevicePort = new NetAcDevicePort();
                netAcDevicePort.setAlias("alias");
                netAcDevicePort.setIpAddr("1.1.1.1");
                list.add(netAcDevicePort);
                return list;
            }
        };

        VxLanRoaResource resource = new VxLanRoaResource();
        ResultRsp<NeVtep> result = resource.queryVtep("123", "123");

        assertEquals("127.0.0.1", result.getData().getVtepIp());
    }

    @Test(expected = ServiceException.class)
    public void testQueryVtep_invalid() throws ServiceException {
        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                WanSubInterface mo = new WanSubInterface();
                mo.setCeLowVlan(123);
                List<WanSubInterface> mos = new ArrayList<>();
                mos.add(mo);

                return mos;

            }
        };

        new MockUp<WanInfSvcImpl>() {

            @Mock
            public List<NetAcDevicePort> queryPorts(List<String> interfaceNameList, String deviceId, String ctrlUuid) {
                List<NetAcDevicePort> list = new ArrayList<>();
                NetAcDevicePort netAcDevicePort = new NetAcDevicePort();
                netAcDevicePort.setAlias("alias");
                netAcDevicePort.setIpAddr("1.1.1.1");
                list.add(netAcDevicePort);
                return list;
            }
        };
        VxLanRoaResource resource = new VxLanRoaResource();
        ResultRsp<NeVtep> result = resource.queryVtep("123++_", "123");

    }

    @Test
    public void testQueryVtep_case2() throws ServiceException {

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                WanSubInterface mo = new WanSubInterface();
                mo.setCeLowVlan(123);
                List<WanSubInterface> mos = new ArrayList<>();
                mos.add(mo);

                return mos;

            }
        };

        VxLanRoaResource resource = new VxLanRoaResource();
        ResultRsp<NeVtep> result = resource.queryVtep("123", "123");

        assertTrue(!result.isSuccess());
    }

    @Test
    public void testQueryVtep_noresult() throws ServiceException {

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                WanSubInterface mo = new WanSubInterface();
                mo.setCeLowVlan(123);
                List<WanSubInterface> mos = new ArrayList<>();
                mos.add(mo);

                return mos;

            }
        };

        VxLanRoaResource resource = new VxLanRoaResource();
        ResultRsp<NeVtep> result = resource.queryVtep("123", "123");

        assertNull(result.getData());
    }

    @Test
    public void testCreateVxlan() throws ServiceException {

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

        new MockUp<VxLanDbOper>() {

            @Mock
            public void insert(VxLanExternalIdMapping vxLanExternalIdMapping) throws ServiceException {

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

        VxLanRoaResource resource = new VxLanRoaResource();
        List<NeVxlanInstance> vxlanInstances = new ArrayList<NeVxlanInstance>();

        NeVxlanInstance vxlan = new NeVxlanInstance();
        vxlan.setVni("123");
        vxlan.setNeId("123-43");
        vxlan.setName("123");

        List<NeVxlanInterface> vxlani = new ArrayList<NeVxlanInterface>();
        NeVxlanInterface vxlaninterface = new NeVxlanInterface();
        vxlaninterface.setVxlanInstanceId("1223");
        vxlaninterface.setAccessType("port");
        vxlaninterface.setPortId("8080");
        vxlani.add(vxlaninterface);
        vxlan.setVxlanInterfaceList(vxlani);

        List<NeVxlanTunnel> vxlant = new ArrayList<NeVxlanTunnel>();
        NeVxlanTunnel vxtunnel = new NeVxlanTunnel();
        vxtunnel.setVxlanInstanceId("123");
        vxtunnel.setVni("123");
        vxtunnel.setSourceAddress("10.20.30.40");
        vxtunnel.setDestAddress("10.20.30.40");
        vxtunnel.setNeId("123");
        vxtunnel.setPeerNeId("123");
        vxlant.add(vxtunnel);
        vxlan.setVxlanTunnelList(vxlant);

        vxlanInstances.add(vxlan);
        ResultRsp<List<NeVxlanInstance>> result = resource.createVxlan(null, "123", vxlanInstances);

        assertTrue(result.isSuccess());
    }

    @Test
    public void testCreateVxlan_invaliduuid() throws ServiceException {
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

        new MockUp<VxLanDbOper>() {

            @Mock
            public void insert(VxLanExternalIdMapping vxLanExternalIdMapping) throws ServiceException {

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
        try {
            VxLanRoaResource resource = new VxLanRoaResource();
            ResultRsp<List<NeVxlanInstance>> result = resource.createVxlan(null, "123_+_+", null);
        } catch(Exception e) {
            assertTrue(e instanceof ServiceException);
        }

    }

    @Test(expected = ServiceException.class)
    public void testCreateVxlan_emptyvxlans() throws ServiceException {
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

        new MockUp<VxLanDbOper>() {

            @Mock
            public void insert(VxLanExternalIdMapping vxLanExternalIdMapping) throws ServiceException {

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

        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) throws IOException {

                return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();

            }
        };

        new MockUp<Paths>() {

            @Mock
            public Path get(String first, String... more) {
                return null;
            }
        };
        VxLanRoaResource resource = new VxLanRoaResource();
        ResultRsp<List<NeVxlanInstance>> result = resource.createVxlan(null, "123", new ArrayList<NeVxlanInstance>());

    }

    @Test(expected = ServiceException.class)
    public void testCreateVxlan_invalidInputs() throws ServiceException {
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

        new MockUp<VxLanDbOper>() {

            @Mock
            public void insert(VxLanExternalIdMapping vxLanExternalIdMapping) throws ServiceException {

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

        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) throws IOException {

                return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();

            }
        };

        new MockUp<Paths>() {

            @Mock
            public Path get(String first, String... more) {
                return null;
            }
        };
        VxLanRoaResource resource = new VxLanRoaResource();
        List<NeVxlanInstance> vxlanInstances = new ArrayList<NeVxlanInstance>();

        NeVxlanInstance vxlan = new NeVxlanInstance();
        vxlan.setVni("123");
        vxlan.setNeId("123-43");
        vxlan.setName("123");

        vxlanInstances.add(vxlan);
        ResultRsp<List<NeVxlanInstance>> result = resource.createVxlan(null, "123", vxlanInstances);

        assertTrue(result.isSuccess());
    }

    @Test(expected = ServiceException.class)
    public void testCreateVxlan_invalidInputs2() throws ServiceException {
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

        new MockUp<VxLanDbOper>() {

            @Mock
            public void insert(VxLanExternalIdMapping vxLanExternalIdMapping) throws ServiceException {

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
        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) throws IOException {

                return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();

            }
        };

        new MockUp<Paths>() {

            @Mock
            public Path get(String first, String... more) {
                return null;
            }
        };
        VxLanRoaResource resource = new VxLanRoaResource();
        List<NeVxlanInstance> vxlanInstances = new ArrayList<NeVxlanInstance>();

        NeVxlanInstance vxlan = new NeVxlanInstance();
        vxlan.setVni("123");
        vxlan.setNeId("123-43");
        vxlan.setName("123");

        List<NeVxlanInterface> vxlani = new ArrayList<NeVxlanInterface>();
        NeVxlanInterface vxlaninterface = new NeVxlanInterface();
        vxlaninterface.setVxlanInstanceId("1223");
        vxlaninterface.setAccessType("port");
        vxlaninterface.setPortId("8080");
        vxlani.add(vxlaninterface);
        vxlan.setVxlanInterfaceList(vxlani);

        vxlanInstances.add(vxlan);
        ResultRsp<List<NeVxlanInstance>> result = resource.createVxlan(null, "123", vxlanInstances);

        assertTrue(result.isSuccess());
    }

    @Test
    public void testCreateVxlan_fail() throws ServiceException {

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

        new MockUp<VxLanDbOper>() {

            @Mock
            public void insert(VxLanExternalIdMapping vxLanExternalIdMapping) throws ServiceException {

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

        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) throws IOException {

                return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();

            }
        };

        new MockUp<Paths>() {

            @Mock
            public Path get(String first, String... more) {
                return null;
            }
        };

        VxLanRoaResource resource = new VxLanRoaResource();
        List<NeVxlanInstance> vxlanInstances = new ArrayList<NeVxlanInstance>();

        NeVxlanInstance vxlan = new NeVxlanInstance();
        vxlan.setVni("123");
        vxlan.setNeId("123-43");
        vxlan.setName("123");

        List<NeVxlanInterface> vxlani = new ArrayList<NeVxlanInterface>();
        NeVxlanInterface vxlaninterface = new NeVxlanInterface();
        vxlaninterface.setVxlanInstanceId("1223");
        vxlaninterface.setAccessType("port");
        vxlaninterface.setPortId("8080");
        vxlani.add(vxlaninterface);
        vxlan.setVxlanInterfaceList(vxlani);

        List<NeVxlanTunnel> vxlant = new ArrayList<NeVxlanTunnel>();
        NeVxlanTunnel vxtunnel = new NeVxlanTunnel();
        vxtunnel.setVxlanInstanceId("123");
        vxtunnel.setVni("123");
        vxtunnel.setSourceAddress("10.20.30.40");
        vxtunnel.setDestAddress("10.20.30.40");
        vxtunnel.setNeId("123");
        vxtunnel.setPeerNeId("123");
        vxlant.add(vxtunnel);
        vxlan.setVxlanTunnelList(vxlant);

        vxlanInstances.add(vxlan);
        ResultRsp<List<NeVxlanInstance>> result = resource.createVxlan(null, "123", vxlanInstances);

        assertTrue(!result.isSuccess());
    }

    @Test
    public void testDeleteVxLan() throws ServiceException {
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

        VxLanRoaResource resource = new VxLanRoaResource();
        ResultRsp<String> result = resource.deleteVxLan(null, "123", "123");

        assertTrue(result.isSuccess());
    }

    @Test
    public void testDeleteVxLan_uuidinvalid() throws ServiceException {
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

        try {
            VxLanRoaResource resource = new VxLanRoaResource();
            ResultRsp<String> result = resource.deleteVxLan(null, "@$$", "123");
        } catch(Exception e) {
            assertTrue(e instanceof ServiceException);
        }

    }

    @Test
    public void testDeleteVxLan_nodeviceId() throws ServiceException {
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

        try {
            VxLanRoaResource resource = new VxLanRoaResource();
            ResultRsp<String> result = resource.deleteVxLan(null, "123", "");
        } catch(Exception e) {
            assertTrue(e instanceof ServiceException);
        }

    }

}
