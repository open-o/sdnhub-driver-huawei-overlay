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

package org.openo.sdno.acbranchservice.model.ipsec.db;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestIpSecExternalIdMapping {

    IpSecExternalIdMapping ipSecExternalIdMapping = new IpSecExternalIdMapping(null, null, null, null);

    @Test
    public void checkGetIpSecConnectionId() {
        String ipSecConnectionId = "ipSecConnectionId";
        ipSecExternalIdMapping.setIpSecConnectionId(ipSecConnectionId);
        assertEquals(ipSecConnectionId, ipSecExternalIdMapping.getIpSecConnectionId());
    }

    @Test
    public void checkGetExternalId() {
        String externalId = "externalId";
        ipSecExternalIdMapping.setExternalId(externalId);
        assertEquals(externalId, ipSecExternalIdMapping.getExternalId());
    }

    @Test
    public void checkGetSeqNumber() {
        String seqNumber = "seqNumber";
        ipSecExternalIdMapping.setSeqNumber(seqNumber);
        assertEquals(seqNumber, ipSecExternalIdMapping.getSeqNumber());
    }

    @Test
    public void checkGetDeviceId() {
        String deviceId = "deviceId";
        ipSecExternalIdMapping.setDeviceId(deviceId);
        assertEquals(deviceId, ipSecExternalIdMapping.getDeviceId());
    }
}
