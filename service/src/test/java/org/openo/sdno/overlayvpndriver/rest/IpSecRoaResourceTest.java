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

package org.openo.sdno.overlayvpndriver.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.overlayvpn.brs.invdao.ControllerDao;
import org.openo.sdno.overlayvpn.brs.model.ControllerMO;
import org.openo.sdno.overlayvpn.dao.common.InventoryDao;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.inventory.sdk.util.InventoryDaoUtil;
import org.openo.sdno.overlayvpn.model.common.enums.AdminStatus;
import org.openo.sdno.overlayvpn.model.common.enums.ipsec.AuthModeType;
import org.openo.sdno.overlayvpn.model.ipsec.IkePolicy;
import org.openo.sdno.overlayvpn.model.ipsec.IpSecPolicy;
import org.openo.sdno.overlayvpn.model.netmodel.ipsec.NeIpSecConnection;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdno.overlayvpndriver.model.ipsec.db.IpSecExternalIdMapping;
import org.openo.sdno.overlayvpndriver.util.controller.ControllerUtil;
import org.openo.sdno.ssl.EncryptionUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.openo.sdno.util.ip.IpUtils;

import mockit.Mock;
import mockit.MockUp;

public class IpSecRoaResourceTest {

    @Test(expected = NullPointerException.class)
    public void createIpSecTest() {
        new MockUp<ControllerDao>() {

            @Mock
            public ControllerMO getController(String uuid) throws ServiceException {
                return new ControllerMO();
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
        new MockUp<IpUtils>() {

            @Mock
            public String getIPFromCIDR(String address) {
                return "10.10.12.12/23";
            }
        };

        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] character) {
                char[] value = {'P', 'S', 'K'};
                return value;
            }
        };

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
                NetIpSecConn netIpSecConn = new NetIpSecConn();
                netIpSecConn.setSeqNumber(234);
                ipSecConnection.add(netIpSecConn);
                NetIpSecModel mo = new NetIpSecModel();
                mo.setIpsecConnection(ipSecConnection);
                List<NetIpSecModel> mos = new ArrayList<>();
                mos.add(mo);
                return mos;

            }
        };

        IpSecRoaResource ipSecRoa = new IpSecRoaResource();
        List<NeIpSecConnection> neIpSecConnectionList = new ArrayList<NeIpSecConnection>();
        NeIpSecConnection neIpSecConnection = new NeIpSecConnection();
        neIpSecConnection.setAdminStatus(AdminStatus.ACTIVE.getName());
        neIpSecConnection.setNeId("123");
        neIpSecConnection.setPsk(AuthModeType.PSK.getName());
        neIpSecConnection.setPeerAddress("10.10.12.12/23");
        neIpSecConnection.setSourceAddress("10.10.12.32/23");
        neIpSecConnection.setSoureIfName("sourceIfNameTest");
        neIpSecConnection.setName("test");
        neIpSecConnection.setTopoRole("spoke");
        IpSecPolicy ipSecPolicy = new IpSecPolicy();
        ipSecPolicy.setTransformProtocol("esp");
        ipSecPolicy.setEncapsulationMode("tunnel");
        neIpSecConnection.setIpSecPolicy(ipSecPolicy);
        neIpSecConnection.setIkePolicy(new IkePolicy());
        neIpSecConnectionList.add(neIpSecConnection);
        try {
            ipSecRoa.createIpSec(null, "12345-67898", neIpSecConnectionList);
        } catch(ServiceException e) {

        }

    }

    @Test
    public void createIpSecTestBranch() {
        new MockUp<ControllerDao>() {

            @Mock
            public ControllerMO getController(String uuid) throws ServiceException {
                return new ControllerMO();
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
        new MockUp<IpUtils>() {

            @Mock
            public String getIPFromCIDR(String address) {
                return "10.10.12.12/23";
            }
        };

        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] character) {
                char[] value = {'P', 'S', 'K'};
                return value;
            }
        };

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
                NetIpSecConn netIpSecConn = new NetIpSecConn();
                netIpSecConn.setSeqNumber(234);
                ipSecConnection.add(netIpSecConn);
                NetIpSecModel mo = new NetIpSecModel();
                mo.setIpsecConnection(ipSecConnection);
                List<NetIpSecModel> mos = new ArrayList<>();
                mos.add(mo);
                return null;

            }
        };

        IpSecRoaResource ipSecRoa = new IpSecRoaResource();
        List<NeIpSecConnection> neIpSecConnectionList = new ArrayList<NeIpSecConnection>();
        /*
         * NeIpSecConnection neIpSecConnection = new NeIpSecConnection();
         * neIpSecConnection.setAdminStatus(AdminStatus.ACTIVE.getName());
         * neIpSecConnection.setNeId("123");
         * neIpSecConnection.setPsk(AuthModeType.PSK.getName());
         * neIpSecConnection.setPeerAddress("10.10.12.12/23");
         * neIpSecConnection.setSourceAddress("10.10.12.32/23");
         * neIpSecConnection.setSoureIfName("sourceIfNameTest");
         * neIpSecConnection.setName("test");
         * neIpSecConnection.setTopoRole("spoke"); IpSecPolicy ipSecPolicy = new
         * IpSecPolicy(); ipSecPolicy.setTransformProtocol("esp");
         * ipSecPolicy.setEncapsulationMode("tunnel");
         * neIpSecConnection.setIpSecPolicy(ipSecPolicy);
         * neIpSecConnection.setIkePolicy(new IkePolicy());
         * neIpSecConnectionList.add(neIpSecConnection);
         */
        try {
            ipSecRoa.createIpSec(null, "12345-67898", neIpSecConnectionList);
        } catch(ServiceException e) {

        }

    }

    @Test(expected = Exception.class)
    public void createIpSecTestThrowsExceptionForNullUuid() throws ServiceException {
        new MockUp<ControllerDao>() {

            @Mock
            public ControllerMO getController(String uuid) throws ServiceException {
                return new ControllerMO();
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
                return null;
            }
        };
        new MockUp<IpUtils>() {

            @Mock
            public String getIPFromCIDR(String address) {
                return "10.10.12.12/23";
            }
        };

        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] character) {
                char[] value = {'P', 'S', 'K'};
                return value;
            }
        };

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
                NetIpSecConn netIpSecConn = new NetIpSecConn();
                netIpSecConn.setSeqNumber(234);
                ipSecConnection.add(netIpSecConn);
                NetIpSecModel mo = new NetIpSecModel();
                mo.setIpsecConnection(ipSecConnection);
                List<NetIpSecModel> mos = new ArrayList<>();
                // mos.add(mo);
                return mos;

            }
        };
        IpSecRoaResource ipSecRoa = new IpSecRoaResource();
        List<NeIpSecConnection> neIpSecConnectionList = new ArrayList<NeIpSecConnection>();
        NeIpSecConnection neIpSecConnection = new NeIpSecConnection();
        neIpSecConnection.setAdminStatus(AdminStatus.ACTIVE.getName());
        neIpSecConnection.setNeId("123");
        neIpSecConnection.setPsk(AuthModeType.PSK.getName());
        neIpSecConnection.setPeerAddress("10.10.12.12/23");
        neIpSecConnection.setSourceAddress("10.10.12.32/23");
        neIpSecConnection.setSoureIfName("sourceIfNameTest");
        neIpSecConnection.setName("test");
        neIpSecConnection.setTopoRole("spoke");
        IpSecPolicy ipSecPolicy = new IpSecPolicy();
        ipSecPolicy.setTransformProtocol("esp");
        ipSecPolicy.setEncapsulationMode("tunnel");
        neIpSecConnection.setIpSecPolicy(ipSecPolicy);
        neIpSecConnection.setIkePolicy(new IkePolicy());
        neIpSecConnectionList.add(neIpSecConnection);
        ipSecRoa.createIpSec(null, "123", neIpSecConnectionList);

    }

    @Test(expected = Exception.class)
    public void createIpSecTestThrowsExceptionForEmptyNeIpSecConnectionList() throws ServiceException {
        IpSecRoaResource ipSecRoa = new IpSecRoaResource();
        NeIpSecConnection neIpSecConnection = new NeIpSecConnection();
        ipSecRoa.createIpSec(null, "1234@@@@", null);
    }

    @Test(expected = NullPointerException.class)
    public void deleteIpSecTest() throws ServiceException {
        new MockUp<InventoryDao<T>>() {

            @Mock
            public ResultRsp<List<IpSecExternalIdMapping>> queryByFilter(Class clazz, String filter,
                    String queryResultFields) throws ServiceException {

                ResultRsp<List<IpSecExternalIdMapping>> rs = new ResultRsp<List<IpSecExternalIdMapping>>();
                List<IpSecExternalIdMapping> list = new ArrayList<>();
                list.add(new IpSecExternalIdMapping("test", "test", "234", "test"));
                rs.setData(list);
                return rs;
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
                List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
                NetIpSecConn netIpSecConn = new NetIpSecConn();
                netIpSecConn.setSeqNumber(234);
                ipSecConnection.add(netIpSecConn);
                NetIpSecModel mo = new NetIpSecModel();
                mo.setIpsecConnection(ipSecConnection);
                List<NetIpSecModel> mos = new ArrayList<>();
                mos.add(mo);
                return mos;
            }
        };
        IpSecRoaResource ipSecRoa = new IpSecRoaResource();
        ipSecRoa.deleteIpSec(null, "extSysID=12345-67898", "12345");
    }

    @Test(expected = Exception.class)
    public void deleteIpSecTestThrowsExceptionForNullUuid() throws ServiceException {
        IpSecRoaResource ipSecRoa = new IpSecRoaResource();
        ipSecRoa.deleteIpSec(null, null, null);
    }

}
