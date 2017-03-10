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

public class SbiIpSecNetModelTest {

    SbiIpSecNetModel sbiIpSecNetModel = new SbiNeIpSec();

    /**
     * <br/>
     *
     * @throws Exception
     *             setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {

        sbiIpSecNetModel.setControllerId("controller1");
        sbiIpSecNetModel.setConnectionServiceId("service1");
        sbiIpSecNetModel.setExternalId("ext1234");
        sbiIpSecNetModel.setNeId("ne123456");
        sbiIpSecNetModel.setPeerNeId("peer123456");
        sbiIpSecNetModel.setDeviceId("device12345");
        sbiIpSecNetModel.setPeerDeviceId("peer12345");
    }

    @Test
    public void testEqualControllerId() {

        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();
        //Class<?> enclosingClass = sbiIpSecNetModelInstance2.getClass().getEnclosingClass();
        //enclosingClass.equals(null);

        sbiIpSecNetModelInstance2.setControllerId("controller2");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service1");
        sbiIpSecNetModelInstance2.setExternalId("ext1234");
        sbiIpSecNetModelInstance2.setNeId("ne123456");
        sbiIpSecNetModelInstance2.setPeerNeId("peer123456");
        sbiIpSecNetModelInstance2.setDeviceId("device12345");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer12345");

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
    }

    @Test
    public void testEqualConnectionServiceId() {

        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId("controller1");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service2");
        sbiIpSecNetModelInstance2.setExternalId("ext1234");
        sbiIpSecNetModelInstance2.setNeId("ne123456");
        sbiIpSecNetModelInstance2.setPeerNeId("peer123456");
        sbiIpSecNetModelInstance2.setDeviceId("device12345");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer12345");

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
    }

    @Test
    public void testEqualExternalId() {

        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId("controller1");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service1");
        sbiIpSecNetModelInstance2.setExternalId("ext12345");
        sbiIpSecNetModelInstance2.setNeId("ne123456");
        sbiIpSecNetModelInstance2.setPeerNeId("peer123456");
        sbiIpSecNetModelInstance2.setDeviceId("device12345");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer12345");

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
    }

    @Test
    public void testEqualNeId() {
        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId("controller1");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service1");
        sbiIpSecNetModelInstance2.setExternalId("ext1234");
        sbiIpSecNetModelInstance2.setNeId("ne1234567");
        sbiIpSecNetModelInstance2.setPeerNeId("peer123456");
        sbiIpSecNetModelInstance2.setDeviceId("device12345");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer12345");

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
    }

    @Test
    public void testEqualPeerNeId() {
        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId("controller1");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service1");
        sbiIpSecNetModelInstance2.setExternalId("ext1234");
        sbiIpSecNetModelInstance2.setNeId("ne123456");
        sbiIpSecNetModelInstance2.setPeerNeId("peer1234567");
        sbiIpSecNetModelInstance2.setDeviceId("device12345");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer12345");

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
    }

    @Test
    public void testEqualDeviceId() {
        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId("controller1");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service1");
        sbiIpSecNetModelInstance2.setExternalId("ext1234");
        sbiIpSecNetModelInstance2.setNeId("ne123456");
        sbiIpSecNetModelInstance2.setPeerNeId("peer123456");
        sbiIpSecNetModelInstance2.setDeviceId("device123456");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer12345");

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
    }

    @Test
    public void testEqualPeerDeviceId() {
        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId("controller1");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service1");
        sbiIpSecNetModelInstance2.setExternalId("ext1234");
        sbiIpSecNetModelInstance2.setNeId("ne123456");
        sbiIpSecNetModelInstance2.setPeerNeId("peer123456");
        sbiIpSecNetModelInstance2.setDeviceId("device12345");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer123456");

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
    }

    @Test
    public void testEqual() {

        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId("controller1");
        sbiIpSecNetModelInstance2.setConnectionServiceId("service1");
        sbiIpSecNetModelInstance2.setExternalId("ext1234");
        sbiIpSecNetModelInstance2.setNeId("ne123456");
        sbiIpSecNetModelInstance2.setPeerNeId("peer123456");
        sbiIpSecNetModelInstance2.setDeviceId("device12345");
        sbiIpSecNetModelInstance2.setPeerDeviceId("peer12345");

        assertTrue(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
        assertTrue(sbiIpSecNetModel.equals(sbiIpSecNetModel));
    }

    @Test
    public void testEqualAllNull() {

        SbiIpSecNetModel sbiIpSecNetModelInstance2 = new SbiNeIpSec();

        sbiIpSecNetModelInstance2.setControllerId(null);
        sbiIpSecNetModelInstance2.setConnectionServiceId(null);
        sbiIpSecNetModelInstance2.setExternalId(null);
        sbiIpSecNetModelInstance2.setNeId(null);
        sbiIpSecNetModelInstance2.setPeerNeId(null);
        sbiIpSecNetModelInstance2.setDeviceId(null);
        sbiIpSecNetModelInstance2.setPeerDeviceId(null);

        sbiIpSecNetModelInstance2.hashCode();
        sbiIpSecNetModelInstance2.hashCode();

        assertFalse(sbiIpSecNetModel.equals(sbiIpSecNetModelInstance2));
        assertFalse(sbiIpSecNetModel.equals(null));
        assertFalse(sbiIpSecNetModel.equals(new Object()));
    }
}
