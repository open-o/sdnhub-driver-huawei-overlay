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

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACBDInfo;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetBdInfoModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.List;

public class SubnetBDIfImplTest {

    String jsonbody =
            "{\"errcode\": \"0\",\"errmsg\": \"\",\"data\": [{\"id\": \"db12e63b259444eda12c96a42fe47e56\","
            + "\"name\": \"ipseconn\",\"interfaceName\": \"GigabitEthernet0/0/1\","
            + "\"ipsecConnection\": [{\"seqNumber\": 1,\"deleteMode\": false,\"type\": true,"
            + "\"routeInject\": \"dynamic\",\"ipsec\": {\"name\": \"proposal1\",\"espAuthAlgorithm\": \"md5\","
            + "\"espEncryptionAlgorithm\": \"3des\"},\"ike\": {\"id\": 1,\"authAlgorithm\": \"sha1\","
            + "\"encryptionAlgorithm\": \"des\",\"version\": \"v2\",\"localAddress\": null,\"peerAddress\": null,"
            + "\"preSharedKey\": \"huawei\"},\"aclNumber\": null,\"aclId\": null,\"ruleList\": null},"
            + "{\"seqNumber\": 2,\"deleteMode\": false,\"type\": false,\"routeInject\": null,"
            + "\"ipsec\": {\"name\": \"proposal2\",\"espAuthAlgorithm\": \"md5\",\"espEncryptionAlgorithm\": \"3des\"},"
            + "\"ike\": {\"id\": 2,\"authAlgorithm\": \"sha1\",\"encryptionAlgorithm\": \"des\",\"version\": \"v2\","
            + "\"localAddress\": null,\"peerAddress\": \"33.33.33.33\",\"preSharedKey\": \"huawei\"},"
            + "\"aclNumber\": 3000,\"aclId\": \"bfd2e7ab23be41fbb9955d4baf3686bc\",\"ruleList\": [{\"id\": 0,"
            + "\"policy\": \"permit\",\"srcIp\": \"1.1.1.1\",\"srcNetMask\": \"255.255.255.0\",\"desIp\": \"2.2.2.2\","
            + "\"desNetMask\": \"255.255.255.0\"}]}]}]}";

    @Test(expected = ServiceException.class)
    public void parseResponseCtrlNull() throws ServiceException {

        String deviceId = "deviceId";
        SubnetBDIfImpl impl = new SubnetBDIfImpl();
        impl.queryBDInfo(null, null, deviceId);

    }

    @Test(expected = ServiceException.class)
    public void parseResponseDeviceIdNull() throws ServiceException {

        String ctrlId = "ctrlId";
        SubnetBDIfImpl impl = new SubnetBDIfImpl();
        impl.queryBDInfo(null, ctrlId, null);

    }

    @Test(expected = ServiceException.class)
    public void parseResponseHttpNull() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                ACResponse<List<ACBDInfo>> response = new ACResponse<List<ACBDInfo>>();

                List<ACBDInfo> list = new ArrayList<ACBDInfo>();

                ACBDInfo route = new ACBDInfo();
                route.setBdId("123");
                list.add(route);
                response.setData(list);
                response.setErrcode("01");
                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };

        String deviceId = "deviceId";
        SubnetBDIfImpl impl = new SubnetBDIfImpl();

        SbiSubnetBdInfoModel model = new SbiSubnetBdInfoModel();
        model.setVni("123");
        String ctrlId = "ctrlId";
        impl.queryBDInfo(model, ctrlId, deviceId);

    }

}
