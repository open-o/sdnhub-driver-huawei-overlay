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

package org.openo.sdnhub.overlayvpndriver.controller.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NqaTest {

    NQA nqa1 = new NQA();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {
        nqa1.setTtl(123);
        nqa1.setSrcIp("srcIp");
        nqa1.setSrcPortName("srcPortName");
        nqa1.setDstIp("dstIp");
        nqa1.setDstPortName("dstPortName");
        nqa1.setTestType("testType");
        nqa1.setFrequency(456);
        nqa1.setProbeCount(789);
        nqa1.setTimeout(1);
        nqa1.setTos(2);
        nqa1.setIpsecConnectionId("ipsecConnectionId");
        nqa1.setNqaState("nqaState");
    }

    @Test
    public void testEqual() {
        NQA nqa2 = new NQA();
        nqa2.setTtl(123);
        nqa2.setSrcIp("srcIp");
        nqa2.setSrcPortName("srcPortName");
        nqa2.setDstIp("dstIp");
        nqa2.setDstPortName("dstPortName");
        nqa2.setTestType("testType");
        nqa2.setFrequency(456);
        nqa2.setProbeCount(789);
        nqa2.setTimeout(1);
        nqa2.setTos(2);
        nqa2.setIpsecConnectionId("ipsecConnectionId");
        nqa2.setNqaState("nqaState");

        assertTrue(nqa1.equals(nqa2));
    }

    @Test
    public void testhashCode1() {
        NQA nqa2 = new NQA();
        nqa2.setTtl(123);
        nqa2.setSrcIp("srcIp");
        nqa2.setSrcPortName("srcPortName");
        nqa2.setDstIp("dstIp");
        nqa2.setDstPortName("dstPortName");
        nqa2.setTestType("testType");
        nqa2.setFrequency(456);
        nqa2.setProbeCount(789);
        nqa2.setTimeout(1);
        nqa2.setTos(2);
        nqa2.setIpsecConnectionId("ipsecConnectionId");
        nqa2.setNqaState("nqaState");

        nqa2.hashCode();
    }

    @Test
    public void testhashCode2() {
        NQA nqa3 = new NQA();
        nqa3.setTtl(null);
        nqa3.setSrcIp(null);
        nqa3.setSrcPortName(null);
        nqa3.setDstIp(null);
        nqa3.setDstPortName(null);
        nqa3.setTestType(null);
        nqa3.setFrequency(null);
        nqa3.setProbeCount(null);
        nqa3.setTimeout(null);
        nqa3.setTos(null);
        nqa3.setIpsecConnectionId(null);
        nqa3.setNqaState(null);

        nqa3.hashCode();
    }

    @Test
    public void testEqual2() {
        assertTrue(nqa1.equals(nqa1));
    }

    @Test
    public void testEqual3() {
        assertFalse(nqa1.equals(null));
    }

    @Test
    public void testEqual4() {
        NQA nqa4 = new NQA();
        nqa4.setTtl(12);
        nqa4.setSrcIp("srcIp");
        nqa4.setSrcPortName("srcPortName");
        nqa4.setDstIp("dstIp");
        nqa4.setDstPortName("dstPortName");
        nqa4.setTestType("testType");
        nqa4.setFrequency(456);
        nqa4.setProbeCount(789);
        nqa4.setTimeout(1);
        nqa4.setTos(2);
        nqa4.setIpsecConnectionId("ipsecConnectionId");
        nqa4.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa4));
    }


    @Test
    public void testEqual5() {
        NQA nqa5 = new NQA();
        nqa5.setTtl(123);
        nqa5.setSrcIp("srcIpTest");
        nqa5.setSrcPortName("srcPortName");
        nqa5.setDstIp("dstIp");
        nqa5.setDstPortName("dstPortName");
        nqa5.setTestType("testType");
        nqa5.setFrequency(456);
        nqa5.setProbeCount(789);
        nqa5.setTimeout(1);
        nqa5.setTos(2);
        nqa5.setIpsecConnectionId("ipsecConnectionId");
        nqa5.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa5));
    }

    @Test
    public void testEqual6() {
        NQA nqa6 = new NQA();
        nqa6.setTtl(123);
        nqa6.setSrcIp("srcIp");
        nqa6.setSrcPortName("srcPortNameTest");
        nqa6.setDstIp("dstIp");
        nqa6.setDstPortName("dstPortName");
        nqa6.setTestType("testType");
        nqa6.setFrequency(456);
        nqa6.setProbeCount(789);
        nqa6.setTimeout(1);
        nqa6.setTos(2);
        nqa6.setIpsecConnectionId("ipsecConnectionId");
        nqa6.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa6));
    }

    @Test
    public void testEqual7() {
        NQA nqa7 = new NQA();
        nqa7.setTtl(123);
        nqa7.setSrcIp("srcIp");
        nqa7.setSrcPortName("srcPortName");
        nqa7.setDstIp("dstIpTest");
        nqa7.setDstPortName("dstPortName");
        nqa7.setTestType("testType");
        nqa7.setFrequency(456);
        nqa7.setProbeCount(789);
        nqa7.setTimeout(1);
        nqa7.setTos(2);
        nqa7.setIpsecConnectionId("ipsecConnectionId");
        nqa7.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa7));
    }

    @Test
    public void testEqual8() {
        NQA nqa8 = new NQA();
        nqa8.setTtl(123);
        nqa8.setSrcIp("srcIp");
        nqa8.setSrcPortName("srcPortName");
        nqa8.setDstIp("dstIp");
        nqa8.setDstPortName("dstPortNameTest");
        nqa8.setTestType("testType");
        nqa8.setFrequency(456);
        nqa8.setProbeCount(789);
        nqa8.setTimeout(1);
        nqa8.setTos(2);
        nqa8.setIpsecConnectionId("ipsecConnectionId");
        nqa8.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa8));
    }

    @Test
    public void testEqual9() {
        NQA nqa9 = new NQA();
        nqa9.setTtl(123);
        nqa9.setSrcIp("srcIp");
        nqa9.setSrcPortName("srcPortName");
        nqa9.setDstIp("dstIp");
        nqa9.setDstPortName("dstPortName");
        nqa9.setTestType("testTypeTest");
        nqa9.setFrequency(456);
        nqa9.setProbeCount(789);
        nqa9.setTimeout(1);
        nqa9.setTos(2);
        nqa9.setIpsecConnectionId("ipsecConnectionId");
        nqa9.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa9));
    }

    @Test
    public void testEqual10() {
        NQA nqa10 = new NQA();
        nqa10.setTtl(123);
        nqa10.setSrcIp("srcIp");
        nqa10.setSrcPortName("srcPortName");
        nqa10.setDstIp("dstIp");
        nqa10.setDstPortName("dstPortName");
        nqa10.setTestType("testType");
        nqa10.setFrequency(45);
        nqa10.setProbeCount(789);
        nqa10.setTimeout(1);
        nqa10.setTos(2);
        nqa10.setIpsecConnectionId("ipsecConnectionId");
        nqa10.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa10));
    }

    @Test
    public void testEqual11() {
        NQA nqa11 = new NQA();
        nqa11.setTtl(123);
        nqa11.setSrcIp("srcIp");
        nqa11.setSrcPortName("srcPortName");
        nqa11.setDstIp("dstIp");
        nqa11.setDstPortName("dstPortName");
        nqa11.setTestType("testType");
        nqa11.setFrequency(456);
        nqa11.setProbeCount(78);
        nqa11.setTimeout(1);
        nqa11.setTos(2);
        nqa11.setIpsecConnectionId("ipsecConnectionId");
        nqa11.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa11));
    }

    @Test
    public void testEqual12() {
        NQA nqa12 = new NQA();
        nqa12.setTtl(123);
        nqa12.setSrcIp("srcIp");
        nqa12.setSrcPortName("srcPortName");
        nqa12.setDstIp("dstIp");
        nqa12.setDstPortName("dstPortName");
        nqa12.setTestType("testType");
        nqa12.setFrequency(456);
        nqa12.setProbeCount(789);
        nqa12.setTimeout(2);
        nqa12.setTos(2);
        nqa12.setIpsecConnectionId("ipsecConnectionId");
        nqa12.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa12));
    }

    @Test
    public void testEqual13() {
        NQA nqa13 = new NQA();
        nqa13.setTtl(123);
        nqa13.setSrcIp("srcIp");
        nqa13.setSrcPortName("srcPortName");
        nqa13.setDstIp("dstIp");
        nqa13.setDstPortName("dstPortName");
        nqa13.setTestType("testType");
        nqa13.setFrequency(456);
        nqa13.setProbeCount(789);
        nqa13.setTimeout(1);
        nqa13.setTos(3);
        nqa13.setIpsecConnectionId("ipsecConnectionId");
        nqa13.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa13));
    }

    @Test
    public void testEqual14() {
        NQA nqa14 = new NQA();
        nqa14.setTtl(123);
        nqa14.setSrcIp("srcIp");
        nqa14.setSrcPortName("srcPortName");
        nqa14.setDstIp("dstIp");
        nqa14.setDstPortName("dstPortName");
        nqa14.setTestType("testType");
        nqa14.setFrequency(456);
        nqa14.setProbeCount(789);
        nqa14.setTimeout(1);
        nqa14.setTos(2);
        nqa14.setIpsecConnectionId("ipsecConnectionIdTest");
        nqa14.setNqaState("nqaState");

        assertFalse(nqa1.equals(nqa14));
    }

    @Test
    public void testEqual15() {
        NQA nqa15 = new NQA();
        nqa15.setTtl(123);
        nqa15.setSrcIp("srcIp");
        nqa15.setSrcPortName("srcPortName");
        nqa15.setDstIp("dstIp");
        nqa15.setDstPortName("dstPortName");
        nqa15.setTestType("testType");
        nqa15.setFrequency(456);
        nqa15.setProbeCount(789);
        nqa15.setTimeout(1);
        nqa15.setTos(2);
        nqa15.setIpsecConnectionId("ipsecConnectionId");
        nqa15.setNqaState("nqaStateTest");

        assertFalse(nqa1.equals(nqa15));
    }

    @Test
    public void testEqualObject() {
        assertFalse(nqa1.equals(new Object()));
    }


}
