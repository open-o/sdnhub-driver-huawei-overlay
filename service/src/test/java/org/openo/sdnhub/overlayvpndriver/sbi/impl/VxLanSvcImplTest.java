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

package org.openo.sdnhub.overlayvpndriver.sbi.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.PortVlan;
import org.openo.sdnhub.overlayvpndriver.controller.model.Vni;
import org.openo.sdnhub.overlayvpndriver.controller.model.VniFilter;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeVxlanInstance;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;


public class VxLanSvcImplTest {

    VxLanSvcImpl vsi = new VxLanSvcImpl();

    @Test
    public void testCreateVxLanByDevice() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();

                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();

                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);

                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);
        List<VxLanDeviceModel> list2 = new ArrayList<>();
        list2.add(vldm);
        ResultRsp<List<VxLanDeviceModel>> response = VxLanSvcImpl.createVxLanByDevice("12354652", "64516925", list2);
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));
    }

    @Test
    public void testCreateVxLanByDeviceFailed() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();

                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("123456456");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);
        List<VxLanDeviceModel> list2 = new ArrayList<>();
        list2.add(vldm);
        ResultRsp<List<VxLanDeviceModel>> response = VxLanSvcImpl.createVxLanByDevice("12354652", "64516925", list2);
        assertTrue("overlayvpn.operation.failed".equals(response.getErrorCode()));
    }

    @Test
    public void testCreateVxLanByDeviceFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();

                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("123456456");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);
        List<VxLanDeviceModel> list2 = new ArrayList<>();
        list2.add(vldm);
        ResultRsp<List<VxLanDeviceModel>> response = VxLanSvcImpl.createVxLanByDevice("12354652", "64516925", list2);
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));
    }

    @Test
    public void testCreateVxLanByDeviceFail200() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();

                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("123456456");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                response.setData(vxLanDeviceModelList);
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);
        List<VxLanDeviceModel> list2 = new ArrayList<>();
        list2.add(vldm);
        ResultRsp<List<VxLanDeviceModel>> response = VxLanSvcImpl.createVxLanByDevice("12354652", "64516925", list2);
        assertTrue("overlayvpn.operation.failed".equals(response.getErrorCode()));
    }

    @Test
    public void testQueryVxlanByDevice() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();

                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();

                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);

                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        ResultRsp<List<VxLanDeviceModel>> response = VxLanSvcImpl.queryVxlanByDevice("1564", "154535");
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));
    }

    @Test(expected = ServiceException.class)
    public void testQueryVxlanByDeviceFail() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);

                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("uuid");
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();

                vxLanDeviceModelList.add(vxLanDeviceModel);
                response.setData(vxLanDeviceModelList);
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        VxLanSvcImpl.queryVxlanByDevice("1564", "154535");

    }

    @Test(expected = ServiceException.class)
    public void testQueryVxlanByDeviceFailed() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        ResultRsp<List<VxLanDeviceModel>> response = VxLanSvcImpl.queryVxlanByDevice("1564", "154535");
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));

    }

    @Test
    public void testDeleteVxlanByDevice() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        String str = new String("abc");
        str.isEmpty();
        List<String> idList = new LinkedList<>();
        idList.add(str);
        ResultRsp<ACDelResponse> response = VxLanSvcImpl.deleteVxlanByDevice("12543", "24656", idList);
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));
    }

    @Test
    public void testDeleteVxlanByDeviceSuccess() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        String str = new String("abc");
        str.isEmpty();
        List<String> idList = new LinkedList<>();
        idList.add(str);
        ResultRsp<ACDelResponse> response = VxLanSvcImpl.deleteVxlanByDevice("12543", "24656", idList);
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));
    }

    @Test
    public void testDeleteVxlanByDeviceFailure() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                response.setData(vxLanDeviceModelList);
                response.setErrcode("invalid");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        String str = new String("abc");
        str.isEmpty();
        List<String> idList = new LinkedList<>();
        idList.add(str);
        ResultRsp<ACDelResponse> response = VxLanSvcImpl.deleteVxlanByDevice("12543", "24656", idList);
        assertTrue("overlayvpn.operation.failed".equals(response.getErrorCode()));
    }

    @Test
    public void testDeleteVxlanByDeviceFailed() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(null);
                return msg;
            }
        };

        String str = new String("abc");
        str.isEmpty();
        List<String> idList = new LinkedList<>();
        idList.add(str);
        ResultRsp<ACDelResponse> response = VxLanSvcImpl.deleteVxlanByDevice("12543", "24656", idList);
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));
    }

    @Test
    public void testDeleteVxlanByDeviceFailed1() throws ServiceException {

        String str = new String("abc");
        str.isEmpty();
        List<String> idList = new LinkedList<>();

        ResultRsp<ACDelResponse> response = VxLanSvcImpl.deleteVxlanByDevice("12543", "24656", idList);
        assertTrue("overlayvpn.operation.success".equals(response.getErrorCode()));
    }

    @Test
    public void testUpdateVxlanByDevice() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);

                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response =
                        new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();

                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel();

                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);

                response.setData(vxLanDeviceModelList);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);
        List<VxLanDeviceModel> list2 = new ArrayList<>();
        list2.add(vldm);

        ResultRsp<List<VxLanDeviceModel>> response = VxLanSvcImpl.createVxLanByDevice("12354652", "12354652", list2);

        ResultRsp<List<VxLanDeviceModel>> resp = VxLanSvcImpl.updateVxlanByDevice("12354652", "12354652", list2);

        assertNotNull(response.equals(resp));
    }

    @Test
    public void testMergeDelVxlanDeviceModel() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String("Raju");
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str2 = new String();
        str2.isEmpty();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        String str3 = new String();
        str3.isEmpty();
        List<String> listsr3 = new LinkedList<>();
        listsr3.add(str3);
        vni1.setPortlist(listsr3);
        PortVlan pv1 = new PortVlan();
        pv1.setPort("1020");
        pv1.setVlan(12552);
        List<PortVlan> listpv1 = new LinkedList<>();
        listpv1.add(pv1);
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(215454564);
        Integer in1 = new Integer(1);
        in1.intValue();
        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        VxLanSvcImpl.mergeDelVxlanDeviceModel(vldm, vldm2);
    }

    @Test
    public void testMergeDelVxlanDeviceModelSame() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String("Raju546");
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        List<String> listsr1 = new LinkedList<>();
        vni.setPortlist(listsr1);
        List<PortVlan> listpv = new LinkedList<>();
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(215454564);
        List<Integer> listin = new LinkedList<>();
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str23 = new String("Raju");
        str23.isEmpty();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str23);
        vni1.setPeerAddresslist(listsr2);
        List<String> listsr3 = new LinkedList<>();
        vni1.setPortlist(listsr3);
        List<PortVlan> listpv1 = new LinkedList<>();
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(215454564);
        List<Integer> listin1 = new LinkedList<>();
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        VxLanSvcImpl.mergeDelVxlanDeviceModel(vldm, vldm2);
    }

    @Test
    public void testMergeDelVxlanDeviceModelNotSame() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();

        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();

        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        VxLanSvcImpl.mergeDelVxlanDeviceModel(vldm, vldm2);
    }

    @Test
    public void testMergeDelVxlanDeviceModelOnlyFirst() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();

        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str2 = new String();
        str2.isEmpty();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        String str3 = new String();
        str3.isEmpty();
        List<String> listsr3 = new LinkedList<>();
        listsr3.add(str3);
        vni1.setPortlist(listsr3);
        PortVlan pv1 = new PortVlan();
        pv1.setPort("1020");
        pv1.setVlan(12552);
        List<PortVlan> listpv1 = new LinkedList<>();
        listpv1.add(pv1);
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(215454564);
        Integer in1 = new Integer(1);
        in1.intValue();
        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        VxLanSvcImpl.mergeDelVxlanDeviceModel(vldm, vldm2);
    }

    @Test
    public void testMergeDelVxlanDeviceModelOnlyFirst1() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String("Raju546");
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        List<String> listsr1 = new LinkedList<>();
        vni.setPortlist(listsr1);
        List<PortVlan> listpv = new LinkedList<>();
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(215454564);
        List<Integer> listin = new LinkedList<>();
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str2 = new String("huhrei");
        str2.isEmpty();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        String str3 = new String();
        str3.isEmpty();
        List<String> listsr3 = new LinkedList<>();
        listsr3.add(str3);
        vni1.setPortlist(listsr3);
        PortVlan pv1 = new PortVlan();
        pv1.setPort("1020");
        pv1.setVlan(12552);
        List<PortVlan> listpv1 = new LinkedList<>();
        listpv1.add(pv1);
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(215454564);
        Integer in1 = new Integer(1);
        in1.intValue();
        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        VxLanSvcImpl.mergeDelVxlanDeviceModel(vldm, vldm2);
    }

    @Test
    public void testMergeDelVxlanDeviceModelOnlyFirst2() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String("Raju");
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str2 = new String("huhrei");
        str2.isEmpty();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        List<String> listsr3 = new LinkedList<>();
        vni1.setPortlist(listsr3);
        List<PortVlan> listpv1 = new LinkedList<>();
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(215454564);
        List<Integer> listin1 = new LinkedList<>();
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        VxLanSvcImpl.mergeDelVxlanDeviceModel(vldm, vldm2);
    }

    @Test
    public void testMergeVxlanDeviceModels() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String("raju");
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String("some");
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(215454564);
        Integer in = new Integer(10);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);
        list12.add(vldm);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str2 = new String();
        str2.isEmpty();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        String str3 = new String();
        str3.isEmpty();
        List<String> listsr3 = new LinkedList<>();
        listsr3.add(str3);
        vni1.setPortlist(listsr3);
        PortVlan pv1 = new PortVlan();
        pv1.setPort("1020");
        pv1.setVlan(12552);
        List<PortVlan> listpv1 = new LinkedList<>();
        listpv1.add(pv1);
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(215454564);
        Integer in1 = new Integer(1);
        in1.intValue();
        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        List<VxLanDeviceModel> list123 = new LinkedList<>();
        list123.add(vldm2);

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, list123);
        assertEquals(response.getName(), "Raju");
    }

    @Test
    public void testMergeVxlanDeviceModelsNotZero() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        List<VniFilter> listvf = new LinkedList<>();
        vni.setFilterList(listvf);
        List<String> listsr = new LinkedList<>();
        vni.setPeerAddresslist(listsr);
        List<String> listsr1 = new LinkedList<>();
        vni.setPortlist(listsr1);
        List<PortVlan> listpv = new LinkedList<>();
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(215454564);
        List<Integer> listin = new LinkedList<>();
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str2 = new String();
        str2.isEmpty();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        String str3 = new String();
        str3.isEmpty();
        List<String> listsr3 = new LinkedList<>();
        listsr3.add(str3);
        vni1.setPortlist(listsr3);
        PortVlan pv1 = new PortVlan();
        pv1.setPort("1020");
        pv1.setVlan(12552);
        List<PortVlan> listpv1 = new LinkedList<>();
        listpv1.add(pv1);
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(215454564);
        Integer in1 = new Integer(1);
        in1.intValue();
        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        List<VxLanDeviceModel> list123 = new LinkedList<>();
        list123.add(vldm2);

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, list123);
        assertEquals(response.getName(), "Raju");
    }

    @Test
    public void testMergeVxlanDeviceModelsEqu() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(1234568213);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        List<String> listsr2 = new LinkedList<>();
        vni1.setPeerAddresslist(listsr2);
        List<String> listsr3 = new LinkedList<>();
        vni1.setPortlist(listsr3);
        List<PortVlan> listpv1 = new LinkedList<>();
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(1234568213);
        List<Integer> listin1 = new LinkedList<>();
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        List<VxLanDeviceModel> list123 = new LinkedList<>();
        list123.add(vldm2);

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, list123);
        assertEquals(response.getName(), "Raju");
    }

    @Test
    public void testMergeVxlanDeviceModelsNotEqu() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(1234568213);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("localAddressBNGL");
        vldm2.setName("Raju");
        vldm2.setUuid("123456456");
        vldm2.setVneId(1234568213);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode("macLearingMode");
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        List<String> listsr2 = new LinkedList<>();
        vni1.setPeerAddresslist(listsr2);
        List<String> listsr3 = new LinkedList<>();
        vni1.setPortlist(listsr3);
        List<PortVlan> listpv1 = new LinkedList<>();
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");
        vni1.setVni(97465989);
        List<Integer> listin1 = new LinkedList<>();
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        List<VxLanDeviceModel> list123 = new LinkedList<>();
        list123.add(vldm2);

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, list123);
        assertEquals(response.getName(), "Raju");
    }

    @Test
    public void testMergeVxlanDeviceModelsNullpass() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(1234568213);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();

        List<VxLanDeviceModel> list123 = new LinkedList<>();
        list123.add(vldm2);

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, list123);
        assertEquals(response.getName(), "Raju");
    }


    @Test
    public void testMergeVxlanDeviceModelsEmpty() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(215454564);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);

        List<VxLanDeviceModel> list123 = new LinkedList<>();

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, list123);
        assertEquals(response.getName(), "Raju");
    }

    @Test
    public void testMergeVxlanDeviceModelsNull() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        str1.isEmpty();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort("1020");
        pv.setVlan(12552);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid("51616");
        vni.setVni(1234568213);
        Integer in = new Integer(1);
        in.intValue();
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);

        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, null);
        assertEquals(response.getName(), "Raju");
    }

    @Test
    public void testMergeVxlanDeviceModelsFailed() {

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("BNGL");
        vldm.setName(null);
        vldm.setUuid(null);
        Vni vni = new Vni();
        vni.setMacLearingMode(null);
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport(null);
        vni.setEvpnRtImport(null);
        vni.setEvpnRtMode("evpnRtMode");
        VniFilter filterList = new VniFilter();
        filterList.setAction(true);
        filterList.setName("RajuVF");
        List<VniFilter> listvf = new LinkedList<>();
        listvf.add(filterList);
        vni.setFilterList(listvf);
        String str = new String();
        str.isEmpty();
        List<String> listsr = new LinkedList<>();
        listsr.add(str);
        vni.setPeerAddresslist(listsr);
        String str1 = new String();
        List<String> listsr1 = new LinkedList<>();
        listsr1.add(str1);
        vni.setPortlist(listsr1);
        PortVlan pv = new PortVlan();
        pv.setPort(null);
        List<PortVlan> listpv = new LinkedList<>();
        listpv.add(pv);
        vni.setPortvlanlist(listpv);
        vni.setQosPreClassify(true);
        vni.setUuid(null);
        Integer in = new Integer(1);
        List<Integer> listin = new LinkedList<>();
        listin.add(in);
        vni.setVlanlist(listin);
        List<Vni> list = new ArrayList<>();
        list.add(vni);
        vldm.setVniList(list);
        List<VxLanDeviceModel> list12 = new LinkedList<>();
        list12.add(vldm);

        VxLanDeviceModel vldm2 = new VxLanDeviceModel();
        vldm2.setLocalAddress("BNGL");
        vldm2.setName(null);
        vldm2.setUuid(null);
        Vni vni1 = new Vni();
        vni1.setMacLearingMode(null);
        vni1.setDeleteMode(true);
        vni1.setBroadCastManager(true);
        vni1.setEvpnRtExport("evpnRtExport");
        vni1.setEvpnRtImport("evpnRtImport");
        vni1.setEvpnRtMode("evpnRtMode");
        VniFilter filterList1 = new VniFilter();
        filterList1.setAction(true);
        filterList1.setName("RajuVF");
        List<VniFilter> listvf1 = new LinkedList<>();
        listvf1.add(filterList1);
        vni1.setFilterList(listvf1);
        String str2 = new String();
        List<String> listsr2 = new LinkedList<>();
        listsr2.add(str2);
        vni1.setPeerAddresslist(listsr2);
        String str3 = new String();
        str3.isEmpty();
        List<String> listsr3 = new LinkedList<>();
        listsr3.add(str3);
        vni1.setPortlist(listsr3);
        PortVlan pv1 = new PortVlan();
        pv1.setPort(null);
        List<PortVlan> listpv1 = new LinkedList<>();
        listpv1.add(pv1);
        vni1.setPortvlanlist(listpv1);
        vni1.setQosPreClassify(true);
        vni1.setUuid("51616");

        Integer in1 = new Integer(1);

        List<Integer> listin1 = new LinkedList<>();
        listin1.add(in1);
        vni1.setVlanlist(listin1);
        List<Vni> list3 = new ArrayList<>();
        list3.add(vni1);
        vldm2.setVniList(list3);

        List<VxLanDeviceModel> list123 = new LinkedList<>();
        list123.add(vldm2);

        VxLanDeviceModel response = VxLanSvcImpl.mergeVxlanDeviceModels(list12, list123);
        assertEquals(response.getLocalAddress(), "BNGL");
    }

    @Test(expected = NullPointerException.class)
    public void testFindData() {

        SbiNeVxlanInstance snvi = new SbiNeVxlanInstance();
        snvi.setArpBroadcastSuppress("arpBroadcastSuppress");
        snvi.setArpProxy("arpProxy");

        List<SbiNeVxlanInstance> list1 = new LinkedList<>();
        list1.add(snvi);

        VxLanDeviceModel vldm = new VxLanDeviceModel();
        vldm.setLocalAddress("localAddressBNGL");
        vldm.setName("Raju");
        vldm.setUuid("123456456");
        vldm.setVneId(1234568213);
        Vni vni = new Vni();
        vni.setMacLearingMode("macLearingMode");
        vni.setDeleteMode(true);
        vni.setBroadCastManager(true);
        vni.setEvpnRtExport("evpnRtExport");
        vni.setEvpnRtImport("evpnRtImport");
        vni.setEvpnRtMode("evpnRtMode");
        List<Vni> vniList = new LinkedList<>();
        vldm.setVniList(vniList);
        List<VxLanDeviceModel> list = new LinkedList<>();
        list.add(vldm);
        ResultRsp<SbiNeVxlanInstance> resp = new ResultRsp<>();

        VxLanSvcImpl.findData(resp, null, list);
    }

    @Test
    public void testFindDataOne() {

        SbiNeVxlanInstance snvi = new SbiNeVxlanInstance();
        snvi.setArpBroadcastSuppress("arpBroadcastSuppress");
        snvi.setArpProxy("arpProxy");

        List<SbiNeVxlanInstance> list1 = new LinkedList<>();
        list1.add(snvi);

        SbiNeVxlanInstance snvi1 = new SbiNeVxlanInstance();
        snvi1.setArpBroadcastSuppress("arpBroadcastSuppress");
        snvi1.setArpProxy("arpProxy");
        List<SbiNeVxlanInstance> list2 = new LinkedList<>();
        list2.add(snvi1);
        Map<String, List<SbiNeVxlanInstance>> deviceIdToDeviceModelMap =
                new HashMap<String, List<SbiNeVxlanInstance>>();

        deviceIdToDeviceModelMap.put("list1", list2);
        for (Map.Entry<String, List<SbiNeVxlanInstance>> entry :
                                            deviceIdToDeviceModelMap.entrySet()) {
            VxLanDeviceModel vldm = new VxLanDeviceModel();
            vldm.setLocalAddress("localAddressBNGL");
            vldm.setName("Raju");
            vldm.setUuid("123456456");
            vldm.setVneId(1234568213);
            Vni vni = new Vni();
            vni.setMacLearingMode("macLearingMode");
            vni.setDeleteMode(true);
            vni.setBroadCastManager(true);
            vni.setEvpnRtExport("evpnRtExport");
            vni.setEvpnRtImport("evpnRtImport");
            vni.setEvpnRtMode("evpnRtMode");
            List<Vni> vniList = new LinkedList<>();
            vldm.setVniList(vniList);
            List<VxLanDeviceModel> list = new LinkedList<>();
            list.add(vldm);
            ResultRsp<SbiNeVxlanInstance> resp = new ResultRsp<>();
            VxLanSvcImpl.findData(resp, entry, list);
        }
    }

    @Test
    public void testGroupByDeviceId() {

        SbiNeVxlanInstance snvi = new SbiNeVxlanInstance();
        snvi.setArpBroadcastSuppress("arpBroadcastSuppress");
        snvi.setArpProxy("arpProxy");
        List<SbiNeVxlanInstance> list = new LinkedList<>();
        list.add(snvi);
        Map<String, List<SbiNeVxlanInstance>> response = VxLanSvcImpl.groupByDeviceId(list);
        assertEquals(response.get(0), null);
    }

    @Test
    public void testGroupByDeviceIdNull() {

        SbiNeVxlanInstance snvi = new SbiNeVxlanInstance();
        snvi.setArpBroadcastSuppress("arpBroadcastSuppress");
        snvi.setArpProxy("arpProxy");
        snvi.setDeviceId(null);
        List<SbiNeVxlanInstance> list = new LinkedList<>();
        list.add(snvi);
        Map<String, List<SbiNeVxlanInstance>> response = VxLanSvcImpl.groupByDeviceId(list);
        assertEquals(response.get(0), null);
    }

}
