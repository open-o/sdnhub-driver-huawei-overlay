/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.service.ipsec;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.db.IpSecExternalIdMapping;
import org.openo.sdnhub.overlayvpndriver.service.ipsec.IpSecSvcImpl;
import org.openo.sdnhub.overlayvpndriver.util.controller.ControllerUtil;
import org.openo.sdnhub.overlayvpndriver.util.db.IpSecDbOper;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class IpSecSvcImplTest {

    @Mocked
    private IpSecSvcImpl ipsecSvc;

    @Test
    public void testCreateIpSec() {

        new MockUp<IpSecDbOper>() {

            @Mock
            public IpSecExternalIdMapping query(String ipSecConnectionId) {
                IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", "1234", "234", "123");
                return ipSecExtIdMapping;
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

        try {
            List<org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel> netIpSecModelList =
                    new ArrayList<>();
            org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel ipSecModel =
                    new org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel();
            ipSecModel.setInterfaceName("interfaceName");
            netIpSecModelList.add(ipSecModel);
            ResultRsp<List<NetIpSecModel>> result = ipsecSvc.createIpSec("123", "1234", netIpSecModelList);
            assertTrue(result.isSuccess());
        } catch(Exception e) {
        }

    }

    @Test
    public void testCreateIpSecThrowsException() {

        try {
            List<org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel> netIpSecModelList =
                    new ArrayList<>();
            org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel ipSecModel =
                    new org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel();
            netIpSecModelList.add(ipSecModel);
            ipsecSvc.createIpSec("123", null, netIpSecModelList);
        } catch(ServiceException e) {
            assertTrue(true);
        }

    }

    @Test
    public void testDeleteIpSecNullSequenceNummber() {

        new MockUp<IpSecDbOper>() {

            @Mock
            public IpSecExternalIdMapping query(String ipSecConnectionId) {
                IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", "1234", "234", "123");
                return ipSecExtIdMapping;
            }
        };

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
                ipSecConnection.add(new NetIpSecConn());
                NetIpSecModel mo = new NetIpSecModel();
                mo.setIpsecConnection(ipSecConnection);
                List<NetIpSecModel> mos = new ArrayList<>();
                mos.add(mo);
                return mos;
            }
        };

        try {
            ResultRsp<String> result = ipsecSvc.deleteIpSec("123", "12345");
            assertTrue(result.isSuccess());
        } catch(Exception e) {
        }

    }

    @Test
    public void testDeleteIpSecNullResult() {

        new MockUp<IpSecDbOper>() {

            @Mock
            public IpSecExternalIdMapping query(String ipSecConnectionId) {
                IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", "1234", "234", "123");
                return ipSecExtIdMapping;
            }
        };

        new MockUp<ControllerUtil>() {

            @Mock
            public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
                List<NetIpSecModel> mos = null;
                return mos;
            }
        };

        try {
            ResultRsp<String> result = ipsecSvc.deleteIpSec("123", "12345");
            assertTrue(result.isSuccess());
        } catch(Exception e) {
        }

    }

    @Test
    public void testDeleteIpSecThrowsNull() {

        new MockUp<IpSecDbOper>() {

            @Mock
            public IpSecExternalIdMapping query(String ipSecConnectionId) {
                IpSecExternalIdMapping ipSecExtIdMapping = null;
                return ipSecExtIdMapping;
            }
        };

        try {
            ipsecSvc.deleteIpSec("123", "12345");
        } catch(ServiceException e) {
            assertTrue(true);
        }

    }

}
