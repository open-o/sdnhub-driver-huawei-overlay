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


public class SbiSnatNetModelTest {

    SbiSnatNetModel ssnm = new SbiSnatNetModel();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {

        ssnm.setAclId("1234aclId");
        ssnm.setAclNumber("5464665654");
        ssnm.setControllerId("1254362");
        ssnm.setCreatetime("CT");
        ssnm.setDeviceId("123456213");
        ssnm.setEndPublicIpAddress("endPublicIpAddress");
        ssnm.setIfName("Rlang");
        ssnm.setUpdatetime("UTIME");
        ssnm.setType("byg");
        ssnm.setSubnetId("25158794");
        ssnm.setStartPublicIpAddress("startPublicIpAddress");
        ssnm.setQosPreNat("qosPreNat");
        ssnm.setPrivatePrefix("privatePrefix");
        ssnm.setPrivateIpAddress("198.12.23");
        ssnm.setNeId("51358465");
        ssnm.setNatId("87465462");
        ssnm.setInternetGatewayId("4545412165452");

    }

    @Test
    public void testEqualsObject() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertTrue(ssnm.equals(ssnm1));
        assertFalse(ssnm.equals(null));
        assertFalse(ssnm.equals(new Object()));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectId() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("9456594564");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");
        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectNum() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("98465656");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");
        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectCid() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("8464984");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");
        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectCt() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("yugugi");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectDi() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("9465532656");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectEpia() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("aderss");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectIn() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("uihij");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectUpt() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("huihk");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectTy() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("vjhgui");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectUpSid() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("945194464");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectSpi() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("jhuihuiu");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectQp() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("uhuihk");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectPp() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("uuhuisu");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectPia() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("192.32.53");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectNid() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("94568965");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectNaid() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("945465974");
        ssnm1.setInternetGatewayId("4545412165452");

        assertFalse(ssnm.equals(ssnm1));
        ssnm1.hashCode();

    }

    @Test
    public void testEqualsObjectIgi() {

        SbiSnatNetModel ssnm1 = new SbiSnatNetModel();

        ssnm1.setAclId("1234aclId");
        ssnm1.setAclNumber("5464665654");
        ssnm1.setControllerId("1254362");
        ssnm1.setCreatetime("CT");
        ssnm1.setDeviceId("123456213");
        ssnm1.setEndPublicIpAddress("endPublicIpAddress");
        ssnm1.setIfName("Rlang");
        ssnm1.setUpdatetime("UTIME");
        ssnm1.setType("byg");
        ssnm1.setSubnetId("25158794");
        ssnm1.setStartPublicIpAddress("startPublicIpAddress");
        ssnm1.setQosPreNat("qosPreNat");
        ssnm1.setPrivatePrefix("privatePrefix");
        ssnm1.setPrivateIpAddress("198.12.23");
        ssnm1.setNeId("51358465");
        ssnm1.setNatId("87465462");
        ssnm1.setInternetGatewayId("3654556526");

        assertFalse(ssnm.equals(ssnm1));
        assertTrue(ssnm.equals(ssnm));
        ssnm1.hashCode();

    }

}
