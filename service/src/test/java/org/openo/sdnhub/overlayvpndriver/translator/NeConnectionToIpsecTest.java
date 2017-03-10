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

package org.openo.sdnhub.overlayvpndriver.translator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnList;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIkePolicy;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIpSecPolicy;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NeConnectionToIpsecTest {

    private static final String CTRL_UUID = "1234567";

    Map<String, List<SbiNeIpSec>> deviceIdToTpsecConnListMap = new ConcurrentHashMap<String, List<SbiNeIpSec>>();

    List<SbiNeIpSec> sbiNeIpSecList = new LinkedList<SbiNeIpSec>();

    @Test (expected = StringIndexOutOfBoundsException.class )
    public void testConvert2Model_workIpSecConnections() {
        sbiNeIpSecList = new LinkedList<SbiNeIpSec>();

        SbiNeIpSec sbiNeIpSec = new SbiNeIpSec();
        sbiNeIpSec.setActiveStatus("active");
        sbiNeIpSec.setAdditionalInfo("additionalInfo");
        sbiNeIpSec.setCreatetime((long)1236549);
        sbiNeIpSec.setControllerId("controllerId");
        sbiNeIpSec.setDescription("description");
        sbiNeIpSec.setDeployStatus("deployStatus");
        sbiNeIpSec.setDeviceId("device12345");
        sbiNeIpSec.setExternalId("ExternalId1111");
        sbiNeIpSec.setExternalIpSecId("Racoon1");
        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setIkeVersion("1.0.0");
        ikePolicy.setPfs("14");
        ikePolicy.setPsk("1");
        ikePolicy.setSbiServiceId("11");
        ikePolicy.setEncryptionAlgorithm("3DES - Triple Data Encryption Standard");
        ikePolicy.setAuthAlgorithm("Secure Hash Standard");
        sbiNeIpSec.setIkePolicy(ikePolicy);
        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("sha384");
        ipSecPolicy.setEncryptionAlgorithm("3DES - Triple Data Encryption Standard");
        ipSecPolicy.setPfs("15");
        sbiNeIpSec.setIpSecPolicy(ipSecPolicy);
        sbiNeIpSec.setWorkType("project");
        sbiNeIpSec.setName("IPSec VPN Tunnel1");
        sbiNeIpSec.setOperationStatus("Active");
        sbiNeIpSec.setPeerAddress("192.168.1.11");
        sbiNeIpSec.setRunningStatus("Running");
        sbiNeIpSec.setTenantId("token-1234-6789");
        sbiNeIpSec.setUpdatetime((long)13548);
        sbiNeIpSec.setUuid("uid-1234");
        sbiNeIpSecList.add(sbiNeIpSec);
        deviceIdToTpsecConnListMap.put("device12345", sbiNeIpSecList);
        Map<String, List<IpsecConnList>> convert2Model =
                NeConnectionToIpsec.convert2Model(deviceIdToTpsecConnListMap, CTRL_UUID);
        List<IpsecConnList> list = convert2Model.get("device12345");
        assertTrue(!list.isEmpty());
    }

    @Test (expected = StringIndexOutOfBoundsException.class )
    public void testConvert2Model_projectIpSecConnections() {

        sbiNeIpSecList = new LinkedList<SbiNeIpSec>();

        SbiNeIpSec sbiNeIpSec2 = new SbiNeIpSec();
        sbiNeIpSec2.setActiveStatus("active");
        sbiNeIpSec2.setAdditionalInfo("additionalInfo");
        sbiNeIpSec2.setCreatetime((long)1236549);
        sbiNeIpSec2.setControllerId("controllerId");
        sbiNeIpSec2.setDescription("description");
        sbiNeIpSec2.setDeployStatus("deployStatus");
        sbiNeIpSec2.setDeviceId("device12345");
        sbiNeIpSec2.setExternalId("ExternalId1111");
        sbiNeIpSec2.setExternalIpSecId("Racoon1");
        SbiIkePolicy ikePolicy1 = new SbiIkePolicy();
        ikePolicy1.setIkeVersion("2.0.0");
        ikePolicy1.setPfs("15");
        ikePolicy1.setPsk("2");
        ikePolicy1.setSbiServiceId("12");
        ikePolicy1.setEncryptionAlgorithm("DES - Data Encryption Standard");
        ikePolicy1.setAuthAlgorithm("Hash Standard");
        sbiNeIpSec2.setIkePolicy(ikePolicy1);
        SbiIpSecPolicy ipSecPolicy1 = new SbiIpSecPolicy();
        ipSecPolicy1.setAuthAlgorithm("sha386");
        ipSecPolicy1.setEncryptionAlgorithm("DES - Data Encryption Standard");
        ipSecPolicy1.setPfs("14");
        sbiNeIpSec2.setIpSecPolicy(ipSecPolicy1);
        sbiNeIpSec2.setWorkType("work");
        sbiNeIpSec2.setName("IPSec VPN Tunnel1");
        sbiNeIpSec2.setOperationStatus("Active");
        sbiNeIpSec2.setPeerAddress("192.168.1.13");
        sbiNeIpSec2.setRunningStatus("Running");
        sbiNeIpSec2.setTenantId("token-1234-6789");
        sbiNeIpSec2.setUpdatetime((long)13548);
        sbiNeIpSec2.setUuid("uid-1234");
        sbiNeIpSec2.setActiveStatus("active");
        sbiNeIpSec2.setAdditionalInfo("additionalInfo");
        sbiNeIpSec2.setCreatetime((long)1236549);
        sbiNeIpSec2.setControllerId("controllerId");
        sbiNeIpSec2.setDescription("description");
        sbiNeIpSec2.setDeployStatus("deployStatus");
        sbiNeIpSec2.setDeviceId("device6789");
        sbiNeIpSec2.setExternalId("ExternalId2222");
        sbiNeIpSec2.setExternalIpSecId("Racoon2");
        sbiNeIpSec2.setPeerAddress("192.168.1.14");
        sbiNeIpSec2.setName("IPSec VPN Tunnel2");
        sbiNeIpSec2.setOperationStatus("Active");
        sbiNeIpSec2.setRunningStatus("Running");
        sbiNeIpSec2.setTenantId("token-1234-9876");
        sbiNeIpSec2.setUpdatetime((long)13548);
        sbiNeIpSec2.setUuid("uid-4567");
        sbiNeIpSecList.add(sbiNeIpSec2);
        deviceIdToTpsecConnListMap.put("device12345", sbiNeIpSecList);

        Map<String, List<IpsecConnList>> convert2Model =
                NeConnectionToIpsec.convert2Model(deviceIdToTpsecConnListMap, CTRL_UUID);
        List<IpsecConnList> list = convert2Model.get("device12345");
        assertTrue(list.isEmpty());
    }
}
