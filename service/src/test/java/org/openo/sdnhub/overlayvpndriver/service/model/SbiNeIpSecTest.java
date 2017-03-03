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

import org.junit.Before;
import org.junit.Test;

public class SbiNeIpSecTest {

    SbiNeIpSec sbiNeIpsec = new SbiNeIpSec();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {
        sbiNeIpsec.setExternalIpSecId("1234");
        sbiNeIpsec.setSoureIfName("source1");
        sbiNeIpsec.setDestIfName("destination1");
        sbiNeIpsec.setSourceAddress("10.130.11.11");
        sbiNeIpsec.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec.setWorkType("work1");
        sbiNeIpsec.setSourceLanCidrs("123,123");
        sbiNeIpsec.setPeerLanCidrs("234,234");
        sbiNeIpsec.setIsTemplateType("template1");

        sbiNeIpsec.setNqa("nqa1");
        sbiNeIpsec.setLocalNeRole("nerole1");
        sbiNeIpsec.setTenantName("tenant1");
        sbiNeIpsec.setProtectionPolicy("protection1");
        sbiNeIpsec.setQosPreClassify("classify1");
        sbiNeIpsec.setRegionId("region1");
    }

    @Test
    public void testEqualExternalIpSecId() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("12345");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualSoureIfName() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source2");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualDestIfName() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination2");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualSourceAddress() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.12");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualPeerAddress() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.12");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualIkePolicy() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm2");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualIpSecPolicy() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm2");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualWorkType() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work2");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualSourceLanCidrs() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("1234,1234");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualPeerLanCidrs() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("2345,2345");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualIsTemplateType() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template2");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualNqa() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa2");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualLocalNeRole() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole2");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualTenantName() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant2");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualProtectionPolicy() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection2");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualQosPreClassify() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify2");
        sbiNeIpsec1.setRegionId("region1");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqualRegionId() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region2");

        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
    }

    @Test
    public void testEqual() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId("1234");
        sbiNeIpsec1.setSoureIfName("source1");
        sbiNeIpsec1.setDestIfName("destination1");
        sbiNeIpsec1.setSourceAddress("10.130.11.11");
        sbiNeIpsec1.setPeerAddress("10.131.11.11");

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType("work1");
        sbiNeIpsec1.setSourceLanCidrs("123,123");
        sbiNeIpsec1.setPeerLanCidrs("234,234");
        sbiNeIpsec1.setIsTemplateType("template1");

        sbiNeIpsec1.setNqa("nqa1");
        sbiNeIpsec1.setLocalNeRole("nerole1");
        sbiNeIpsec1.setTenantName("tenant1");
        sbiNeIpsec1.setProtectionPolicy("protection1");
        sbiNeIpsec1.setQosPreClassify("classify1");
        sbiNeIpsec1.setRegionId("region1");

        assertTrue(sbiNeIpsec.equals(sbiNeIpsec1));
        assertTrue(sbiNeIpsec.equals(sbiNeIpsec));
    }

    @Test
    public void testEqualAllNull() {
        SbiNeIpSec sbiNeIpsec1 = new SbiNeIpSec();
        sbiNeIpsec1.setExternalIpSecId(null);
        sbiNeIpsec1.setSoureIfName(null);
        sbiNeIpsec1.setDestIfName(null);
        sbiNeIpsec1.setSourceAddress(null);
        sbiNeIpsec1.setPeerAddress(null);

        SbiIkePolicy ikePolicy = new SbiIkePolicy();
        ikePolicy.setAuthAlgorithm("autoalgorithm1");
        sbiNeIpsec1.setIkePolicy(ikePolicy);

        SbiIpSecPolicy ipSecPolicy = new SbiIpSecPolicy();
        ipSecPolicy.setAuthAlgorithm("policyautoalgorithm1");
        sbiNeIpsec1.setIpSecPolicy(ipSecPolicy);

        sbiNeIpsec1.setWorkType(null);
        sbiNeIpsec1.setSourceLanCidrs(null);
        sbiNeIpsec1.setPeerLanCidrs(null);
        sbiNeIpsec1.setIsTemplateType(null);

        sbiNeIpsec1.setNqa(null);
        sbiNeIpsec1.setLocalNeRole(null);
        sbiNeIpsec1.setTenantName(null);
        sbiNeIpsec1.setProtectionPolicy(null);
        sbiNeIpsec1.setQosPreClassify(null);
        sbiNeIpsec1.setRegionId(null);

        sbiNeIpsec1.hashCode();
        sbiNeIpsec.hashCode();
        assertFalse(sbiNeIpsec.equals(sbiNeIpsec1));
        assertFalse(sbiNeIpsec.equals(null));
        assertFalse(sbiNeIpsec.equals(new Object()));
    }
}
