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

package org.openo.sdnhub.overlayvpndriver.rest;

import static org.junit.Assert.assertEquals;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.SubnetBDIfImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetBdInfoModel;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;



public class SubnetBdIfRoAResourceTest {

    @Mocked
    HttpServletRequest request;

    @Mocked
    HttpResponse response;

    @Mocked
    HttpEntity entity;

    /**
     * <br/>
     *
     * @throws Exception setup failure exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() throws Exception {
        SubnetBDIfImpl service = new SubnetBDIfImpl();
        Class<?> clazz = SubnetBDIfROAResource.class;

        Object cc = clazz.newInstance();
        Field f1 = cc.getClass().getDeclaredField("subnetBDIf");
        f1.setAccessible(true);
        f1.set(cc, service);
        subnetbdiroaresource = (SubnetBDIfROAResource)cc;
    }

    SubnetBDIfROAResource subnetbdiroaresource = new SubnetBDIfROAResource();

    String jsonbody = "{\"errcode\": \"0\",\"errmsg\": \"\",\"data\": [{\"id\": "
            + "\"db12e63b259444eda12c96a42fe47e56\",\"name\": \"ipseconn\","
            + "\"interfaceName\": \"GigabitEthernet0/0/1\",\"ipsecConnection\":"
            + " [{\"seqNumber\": 1,\"deleteMode\": false,\"type\": true,\"routeInject\": "
            + "\"dynamic\",\"ipsec\": {\"name\": \"proposal1\",\"espAuthAlgorithm\": "
            + "\"md5\",\"espEncryptionAlgorithm\": \"3des\"},\"ike\": {\"id\": 1,\"authAlgorithm\": "
            + "\"sha1\",\"encryptionAlgorithm\": \"des\",\"version\": \"v2\",\"localAddress\": null,"
            + "\"peerAddress\": null,\"preSharedKey\": \"huawei\"},\"aclNumber\": null,\"aclId\": null,"
            + "\"ruleList\": null},{\"seqNumber\": 2,\"deleteMode\": false,\"type\": false,\"routeInject\": null,"
            + "\"ipsec\": {\"name\": \"proposal2\",\"espAuthAlgorithm\": \"md5\",\"espEncryptionAlgorithm\": \"3des\"},"
            + "\"ike\": {\"id\": 2,\"authAlgorithm\": \"sha1\",\"encryptionAlgorithm\": \"des\",\"version\":"
            + " \"v2\",\"localAddress\": null,\"peerAddress\": \"33.33.33.33\",\"preSharedKey\": \"huawei\"},"
            + "\"aclNumber\": 3000,\"aclId\": \"bfd2e7ab23be41fbb9955d4baf3686bc\",\"ruleList\":"
            + " [{\"id\": 0,\"policy\": \"permit\",\"srcIp\": \"1.1.1.1\",\"srcNetMask\": "
            + "\"255.255.255.0\",\"desIp\": \"2.2.2.2\",\"desNetMask\": \"255.255.255.0\"}]}]}]}";

    @Test
    public void testQueryBdIfSuccess() throws ServiceException {

        String deviceId = "deviceid";
        String vni = "vni";

        SbiSubnetBdInfoModel sbisubnetinfomodel = new SbiSubnetBdInfoModel();
        sbisubnetinfomodel.setBdId("bdId");
        sbisubnetinfomodel.setControllerId("controllerId");
        sbisubnetinfomodel.setDeviceId(deviceId);
        sbisubnetinfomodel.setNeId("neId");
        sbisubnetinfomodel.setSubnetId("subnetId");
        sbisubnetinfomodel.setVbdifName("vbdifName");
        sbisubnetinfomodel.setVni(vni);
        sbisubnetinfomodel.getSubnetId();
        new MockUp<StringUtils>() {

        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                httpReturnMessage.setBody(jsonbody);
                return httpReturnMessage;

            }
        };
        new MockUp<UuidUtil>() {

            @Mock
            public boolean validate(java.lang.String uuid) {
                return true;
            }
        };
        new MockUp<ResultRsp<?>>() {

            @Mock
            public boolean isValid() {
                return true;
            }
        };
        new MockUp<SvcExcptUtil>() {

            @Mock
            public void throwBadRequestException(java.lang.String msg)
                    throws org.openo.baseservice.remoteservice.exception.ServiceException {

            }
        };

        String ctrlUuidParam = "X-Driver-Parameter";
        ResultRsp<SbiSubnetBdInfoModel> expected =
                subnetbdiroaresource.queryBDIf(request, deviceId, vni, ctrlUuidParam);
        assertEquals("overlayvpn.operation.success", expected.getErrorCode());
    }

    @Test(expected = ServiceException.class)
    public void testQueryBdIfFailureUtil() throws ServiceException {
        String deviceId = "deviceid";
        String vni = "vni";
        SbiSubnetBdInfoModel sbisubnetinfomodel = new SbiSubnetBdInfoModel();
        sbisubnetinfomodel.setBdId("bdId");
        sbisubnetinfomodel.setControllerId("controllerId");
        sbisubnetinfomodel.setDeviceId(deviceId);
        sbisubnetinfomodel.setNeId("neId");
        sbisubnetinfomodel.setSubnetId("subnetId");
        sbisubnetinfomodel.setVbdifName("vbdifName");
        sbisubnetinfomodel.setVni(vni);
        sbisubnetinfomodel.getSubnetId();
        new MockUp<StringUtils>() {

        };
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                httpReturnMessage.setBody(jsonbody);
                return httpReturnMessage;
            }
        };
        new MockUp<UuidUtil>() {

            @Mock
            public boolean validate(java.lang.String uuid) {
                return false;
            }
        };
        String ctrlUuidParam = null;
        subnetbdiroaresource.queryBDIf(request, deviceId, vni, ctrlUuidParam);
    }

    @Test(expected = ServiceException.class)
    public void testQueryBdIfFailureValid() throws ServiceException {
        String deviceId = "deviceid";
        String vni = "vni";

        SbiSubnetBdInfoModel sbisubnetinfomodel = new SbiSubnetBdInfoModel();
        sbisubnetinfomodel.setBdId("bdId");
        sbisubnetinfomodel.setControllerId("controllerId");
        sbisubnetinfomodel.setDeviceId(deviceId);
        sbisubnetinfomodel.setNeId("neId");
        sbisubnetinfomodel.setSubnetId("subnetId");
        sbisubnetinfomodel.setVbdifName("vbdifName");
        sbisubnetinfomodel.setVni(vni);
        sbisubnetinfomodel.getSubnetId();
        new MockUp<StringUtils>() {};
        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                httpReturnMessage.setBody(jsonbody);
                return httpReturnMessage;
            }
        };
        new MockUp<UuidUtil>() {

            @Mock
            public boolean validate(java.lang.String uuid) {
                return true;
            }
        };
        new MockUp<ResultRsp<?>>() {

            @Mock
            public boolean isValid() {
                return false;
            }
        };
        String ctrlUuidParam = "X-Driver-Parameter";
        subnetbdiroaresource.queryBDIf(request, deviceId, vni, ctrlUuidParam);
    }
}
