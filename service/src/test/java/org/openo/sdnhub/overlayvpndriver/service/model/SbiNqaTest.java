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

package org.openo.sdnhub.overlayvpndriver.service.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SbiNqaTest {

    @Test
    public void testEqual() {

        SbiNqa nqa1 = new SbiNqa();
        nqa1.setNeId("setNeId");
        nqa1.setNeRole("neRole");
        nqa1.setSrcIp("srcIp");
        nqa1.setSrcPortName("srcPortName");
        nqa1.setDstIp("dstIp");
        nqa1.setDstPortName("dstPortName");
        nqa1.setTestType("testType");
        nqa1.setFrequency("frequency");
        nqa1.setProbeCount("probeCount");
        nqa1.setTimeout("timeout");
        nqa1.setTtl("ttl");
        nqa1.setTos("tos");
        nqa1.setInterval("interval");

        SbiNqa nqa2 = new SbiNqa();
        nqa2.setNeId("setNeId");
        nqa2.setNeRole("neRole");
        nqa2.setSrcIp("srcIp");
        nqa2.setSrcPortName("srcPortName");
        nqa2.setDstIp("dstIp");
        nqa2.setDstPortName("dstPortName");
        nqa2.setTestType("testType");
        nqa2.setFrequency("frequency");
        nqa2.setProbeCount("probeCount");
        nqa2.setTimeout("timeout");
        nqa2.setTtl("ttl");
        nqa2.setTos("tos");
        nqa2.setInterval("interval");

        SbiNqa nqa3 = new SbiNqa();
        nqa3.setNeId(null);
        nqa3.setNeRole(null);
        nqa3.setSrcIp(null);
        nqa3.setSrcPortName(null);
        nqa3.setDstIp(null);
        nqa3.setDstPortName(null);
        nqa3.setTestType(null);
        nqa3.setFrequency(null);
        nqa3.setProbeCount(null);
        nqa3.setTimeout(null);
        nqa3.setTtl(null);
        nqa3.setTos(null);
        nqa3.setInterval(null);

        SbiNqa nqa4 = new SbiNqa();
        nqa4.setNeId("setNeIdTest");
        nqa4.setNeRole("neRole");
        nqa4.setSrcIp("srcIp");
        nqa4.setSrcPortName("srcPortName");
        nqa4.setDstIp("dstIp");
        nqa4.setDstPortName("dstPortName");
        nqa4.setTestType("testType");
        nqa4.setFrequency("frequency");
        nqa4.setProbeCount("probeCount");
        nqa4.setTimeout("timeout");
        nqa4.setTtl("ttl");
        nqa4.setTos("tos");
        nqa4.setInterval("interval");

        SbiNqa nqa5 = new SbiNqa();
        nqa5.setNeId("setNeId");
        nqa5.setNeRole("neRoleTest");
        nqa5.setSrcIp("srcIp");
        nqa5.setSrcPortName("srcPortName");
        nqa5.setDstIp("dstIp");
        nqa5.setDstPortName("dstPortName");
        nqa5.setTestType("testType");
        nqa5.setFrequency("frequency");
        nqa5.setProbeCount("probeCount");
        nqa5.setTimeout("timeout");
        nqa5.setTtl("ttl");
        nqa5.setTos("tos");
        nqa5.setInterval("interval");

        SbiNqa nqa6 = new SbiNqa();
        nqa6.setNeId("setNeId");
        nqa6.setNeRole("neRole");
        nqa6.setSrcIp("srcIpTest");
        nqa6.setSrcPortName("srcPortName");
        nqa6.setDstIp("dstIp");
        nqa6.setDstPortName("dstPortName");
        nqa6.setTestType("testType");
        nqa6.setFrequency("frequency");
        nqa6.setProbeCount("probeCount");
        nqa6.setTimeout("timeout");
        nqa6.setTtl("ttl");
        nqa6.setTos("tos");
        nqa6.setInterval("interval");

        SbiNqa nqa7 = new SbiNqa();
        nqa7.setNeId("setNeId");
        nqa7.setNeRole("neRole");
        nqa7.setSrcIp("srcIp");
        nqa7.setSrcPortName("srcPortNameTest");
        nqa7.setDstIp("dstIp");
        nqa7.setDstPortName("dstPortName");
        nqa7.setTestType("testType");
        nqa7.setFrequency("frequency");
        nqa7.setProbeCount("probeCount");
        nqa7.setTimeout("timeout");
        nqa7.setTtl("ttl");
        nqa7.setTos("tos");
        nqa7.setInterval("interval");

        SbiNqa nqa8 = new SbiNqa();
        nqa8.setNeId("setNeId");
        nqa8.setNeRole("neRole");
        nqa8.setSrcIp("srcIp");
        nqa8.setSrcPortName("srcPortName");
        nqa8.setDstIp("dstIpTest");
        nqa8.setDstPortName("dstPortName");
        nqa8.setTestType("testType");
        nqa8.setFrequency("frequency");
        nqa8.setProbeCount("probeCount");
        nqa8.setTimeout("timeout");
        nqa8.setTtl("ttl");
        nqa8.setTos("tos");
        nqa8.setInterval("interval");

        SbiNqa nqa9 = new SbiNqa();
        nqa9.setNeId("setNeId");
        nqa9.setNeRole("neRole");
        nqa9.setSrcIp("srcIp");
        nqa9.setSrcPortName("srcPortName");
        nqa9.setDstIp("dstIp");
        nqa9.setDstPortName("dstPortNameTest");
        nqa9.setTestType("testType");
        nqa9.setFrequency("frequency");
        nqa9.setProbeCount("probeCount");
        nqa9.setTimeout("timeout");
        nqa9.setTtl("ttl");
        nqa9.setTos("tos");
        nqa9.setInterval("interval");

        SbiNqa nqa10 = new SbiNqa();
        nqa10.setNeId("setNeId");
        nqa10.setNeRole("neRole");
        nqa10.setSrcIp("srcIp");
        nqa10.setSrcPortName("srcPortName");
        nqa10.setDstIp("dstIp");
        nqa10.setDstPortName("dstPortName");
        nqa10.setTestType("testTypeTest");
        nqa10.setFrequency("frequency");
        nqa10.setProbeCount("probeCount");
        nqa10.setTimeout("timeout");
        nqa10.setTtl("ttl");
        nqa10.setTos("tos");
        nqa10.setInterval("interval");

        SbiNqa nqa11 = new SbiNqa();
        nqa11.setNeId("setNeId");
        nqa11.setNeRole("neRole");
        nqa11.setSrcIp("srcIp");
        nqa11.setSrcPortName("srcPortName");
        nqa11.setDstIp("dstIp");
        nqa11.setDstPortName("dstPortName");
        nqa11.setTestType("testType");
        nqa11.setFrequency("frequencyTest");
        nqa11.setProbeCount("probeCount");
        nqa11.setTimeout("timeout");
        nqa11.setTtl("ttl");
        nqa11.setTos("tos");
        nqa11.setInterval("interval");

        SbiNqa nqa12 = new SbiNqa();
        nqa12.setNeId("setNeId");
        nqa12.setNeRole("neRole");
        nqa12.setSrcIp("srcIp");
        nqa12.setSrcPortName("srcPortName");
        nqa12.setDstIp("dstIp");
        nqa12.setDstPortName("dstPortName");
        nqa12.setTestType("testType");
        nqa12.setFrequency("frequency");
        nqa12.setProbeCount("probeCountTest");
        nqa12.setTimeout("timeout");
        nqa12.setTtl("ttl");
        nqa12.setTos("tos");
        nqa12.setInterval("interval");

        SbiNqa nqa13 = new SbiNqa();
        nqa13.setNeId("setNeId");
        nqa13.setNeRole("neRole");
        nqa13.setSrcIp("srcIp");
        nqa13.setSrcPortName("srcPortName");
        nqa13.setDstIp("dstIp");
        nqa13.setDstPortName("dstPortName");
        nqa13.setTestType("testType");
        nqa13.setFrequency("frequency");
        nqa13.setProbeCount("probeCount");
        nqa13.setTimeout("timeoutTest");
        nqa13.setTtl("ttl");
        nqa13.setTos("tos");
        nqa13.setInterval("interval");

        SbiNqa nqa14 = new SbiNqa();
        nqa14.setNeId("setNeId");
        nqa14.setNeRole("neRole");
        nqa14.setSrcIp("srcIp");
        nqa14.setSrcPortName("srcPortName");
        nqa14.setDstIp("dstIp");
        nqa14.setDstPortName("dstPortName");
        nqa14.setTestType("testType");
        nqa14.setFrequency("frequency");
        nqa14.setProbeCount("probeCount");
        nqa14.setTimeout("timeout");
        nqa14.setTtl("ttlTest");
        nqa14.setTos("tos");
        nqa14.setInterval("interval");

        SbiNqa nqa15 = new SbiNqa();
        nqa15.setNeId("setNeId");
        nqa15.setNeRole("neRole");
        nqa15.setSrcIp("srcIp");
        nqa15.setSrcPortName("srcPortName");
        nqa15.setDstIp("dstIp");
        nqa15.setDstPortName("dstPortName");
        nqa15.setTestType("testType");
        nqa15.setFrequency("frequency");
        nqa15.setProbeCount("probeCount");
        nqa15.setTimeout("timeout");
        nqa15.setTtl("ttl");
        nqa15.setTos("tosTest");
        nqa15.setInterval("interval");

        SbiNqa nqa16 = new SbiNqa();
        nqa16.setNeId("setNeId");
        nqa16.setNeRole("neRole");
        nqa16.setSrcIp("srcIp");
        nqa16.setSrcPortName("srcPortName");
        nqa16.setDstIp("dstIp");
        nqa16.setDstPortName("dstPortName");
        nqa16.setTestType("testType");
        nqa16.setFrequency("frequency");
        nqa16.setProbeCount("probeCount");
        nqa16.setTimeout("timeout");
        nqa16.setTtl("ttl");
        nqa16.setTos("tos");
        nqa16.setInterval("intervalTest");

        nqa2.hashCode();
        nqa3.hashCode();
        nqa2.toString();

        assertTrue(nqa1.equals(nqa2));

        assertTrue(nqa1.equals(nqa1));
        assertFalse(nqa1.equals(null));
        assertFalse(nqa1.equals(nqa4));
        assertFalse(nqa1.equals(nqa5));
        assertFalse(nqa1.equals(nqa6));
        assertFalse(nqa1.equals(nqa7));
        assertFalse(nqa1.equals(nqa8));
        assertFalse(nqa1.equals(nqa9));
        assertFalse(nqa1.equals(nqa10));
        assertFalse(nqa1.equals(nqa11));
        assertFalse(nqa1.equals(nqa12));
        assertFalse(nqa1.equals(nqa13));
        assertFalse(nqa1.equals(nqa14));
        assertFalse(nqa1.equals(nqa15));
        assertFalse(nqa1.equals(nqa16));
        assertFalse(nqa1.equals(new Object()));

    }

}
