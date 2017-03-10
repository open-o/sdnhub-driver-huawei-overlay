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

public class AdapterDeviceInfoTest {

    AdapterDeviceInfo info1 = new AdapterDeviceInfo();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {
        info1.setId("id");
        info1.setShowTenant("showTenant");
        info1.setServiceIp("serviceIp");
        info1.setNeType("neType");
        info1.setVersion("version");
        info1.setStatus("status");
        info1.setGisLon(0.00);
        info1.setGisLat(0.01);
        info1.setVendor("vendor");
        info1.setTenantId("tenantId");
        info1.setTenantName("tenantName");
        info1.setOrgnizationId("orgnizationId");
        info1.setCreator("creator");
        info1.setCreateTime("createTime");
        info1.setRegisterTime("registerTime");
        info1.setModifier("modifier");
        info1.setModifyTime("modifyTime");
    }

    @Test
    public void testHashCode() {
        AdapterDeviceInfo info2 = new AdapterDeviceInfo();

        info2.setId("id");
        info2.setShowTenant("showTenant");
        info2.setServiceIp("serviceIp");
        info2.setNeType("neType");
        info2.setVersion("version");
        info2.setStatus("status");
        info2.setGisLon(0.00);
        info2.setGisLat(0.01);
        info2.setVendor("vendor");
        info2.setTenantId("tenantId");
        info2.setTenantName("tenantName");
        info2.setOrgnizationId("orgnizationId");
        info2.setCreator("creator");
        info2.setCreateTime("createTime");
        info2.setRegisterTime("registerTime");
        info2.setModifier("modifier");
        info2.setModifyTime("modifyTime");

        info2.hashCode();
    }

    @Test
    public void testHashCodeNull() {
        AdapterDeviceInfo info3 = new AdapterDeviceInfo();

        info3.setId(null);
        info3.setShowTenant(null);
        info3.setServiceIp(null);
        info3.setNeType(null);
        info3.setVersion(null);
        info3.setStatus(null);
        info3.setGisLon(null);
        info3.setGisLat(null);
        info3.setVendor(null);
        info3.setTenantId(null);
        info3.setTenantName(null);
        info3.setOrgnizationId(null);
        info3.setCreator(null);
        info3.setCreateTime(null);
        info3.setRegisterTime(null);
        info3.setModifier(null);
        info3.setModifyTime(null);

        info3.hashCode();
    }

    @Test
    public void testEquals1() {
        AdapterDeviceInfo info4 = new AdapterDeviceInfo();

        info4.setId("id");
        info4.setShowTenant("showTenant");
        info4.setServiceIp("serviceIp");
        info4.setNeType("neType");
        info4.setVersion("version");
        info4.setStatus("status");
        info4.setGisLon(0.00);
        info4.setGisLat(0.01);
        info4.setVendor("vendor");
        info4.setTenantId("tenantId");
        info4.setTenantName("tenantName");
        info4.setOrgnizationId("orgnizationId");
        info4.setCreator("creator");
        info4.setCreateTime("createTime");
        info4.setRegisterTime("registerTime");
        info4.setModifier("modifier");
        info4.setModifyTime("modifyTime");

        assertTrue(info1.equals(info4));

    }

    @Test
    public void testEquals2() {

        assertTrue(info1.equals(info1));
    }

    @Test
    public void testEquals3() {

        assertFalse(info1.equals(null));
    }

    @Test
    public void testEquals5() {
        AdapterDeviceInfo info5 = new AdapterDeviceInfo();

        info5.setId("idTest");
        info5.setShowTenant("showTenant");
        info5.setServiceIp("serviceIp");
        info5.setNeType("neType");
        info5.setVersion("version");
        info5.setStatus("status");
        info5.setGisLon(0.00);
        info5.setGisLat(0.01);
        info5.setVendor("vendor");
        info5.setTenantId("tenantId");
        info5.setTenantName("tenantName");
        info5.setOrgnizationId("orgnizationId");
        info5.setCreator("creator");
        info5.setCreateTime("createTime");
        info5.setRegisterTime("registerTime");
        info5.setModifier("modifier");
        info5.setModifyTime("modifyTime");

        assertFalse(info1.equals(info5));
    }

    @Test
    public void testEquals6() {
        AdapterDeviceInfo info6 = new AdapterDeviceInfo();

        info6.setId("id");
        info6.setShowTenant("showTenantTest");
        info6.setServiceIp("serviceIp");
        info6.setNeType("neType");
        info6.setVersion("version");
        info6.setStatus("status");
        info6.setGisLon(0.00);
        info6.setGisLat(0.01);
        info6.setVendor("vendor");
        info6.setTenantId("tenantId");
        info6.setTenantName("tenantName");
        info6.setOrgnizationId("orgnizationId");
        info6.setCreator("creator");
        info6.setCreateTime("createTime");
        info6.setRegisterTime("registerTime");
        info6.setModifier("modifier");
        info6.setModifyTime("modifyTime");

        assertFalse(info1.equals(info6));
    }

    @Test
    public void testEquals7() {
        AdapterDeviceInfo info7 = new AdapterDeviceInfo();

        info7.setId("id");
        info7.setShowTenant("showTenant");
        info7.setServiceIp("serviceIpTest");
        info7.setNeType("neType");
        info7.setVersion("version");
        info7.setStatus("status");
        info7.setGisLon(0.00);
        info7.setGisLat(0.01);
        info7.setVendor("vendor");
        info7.setTenantId("tenantId");
        info7.setTenantName("tenantName");
        info7.setOrgnizationId("orgnizationId");
        info7.setCreator("creator");
        info7.setCreateTime("createTime");
        info7.setRegisterTime("registerTime");
        info7.setModifier("modifier");
        info7.setModifyTime("modifyTime");

        assertFalse(info1.equals(info7));
    }

    @Test
    public void testEquals8() {
        AdapterDeviceInfo info8 = new AdapterDeviceInfo();

        info8.setId("id");
        info8.setShowTenant("showTenant");
        info8.setServiceIp("serviceIp");
        info8.setNeType("neTypeTest");
        info8.setVersion("version");
        info8.setStatus("status");
        info8.setGisLon(0.00);
        info8.setGisLat(0.01);
        info8.setVendor("vendor");
        info8.setTenantId("tenantId");
        info8.setTenantName("tenantName");
        info8.setOrgnizationId("orgnizationId");
        info8.setCreator("creator");
        info8.setCreateTime("createTime");
        info8.setRegisterTime("registerTime");
        info8.setModifier("modifier");
        info8.setModifyTime("modifyTime");

        assertFalse(info1.equals(info8));
    }

    @Test
    public void testEquals9() {
        AdapterDeviceInfo info9 = new AdapterDeviceInfo();

        info9.setId("id");
        info9.setShowTenant("showTenant");
        info9.setServiceIp("serviceIp");
        info9.setNeType("neType");
        info9.setVersion("versionTest");
        info9.setStatus("status");
        info9.setGisLon(0.00);
        info9.setGisLat(0.01);
        info9.setVendor("vendor");
        info9.setTenantId("tenantId");
        info9.setTenantName("tenantName");
        info9.setOrgnizationId("orgnizationId");
        info9.setCreator("creator");
        info9.setCreateTime("createTime");
        info9.setRegisterTime("registerTime");
        info9.setModifier("modifier");
        info9.setModifyTime("modifyTime");

        assertFalse(info1.equals(info9));
    }

    @Test
    public void testEquals10() {
        AdapterDeviceInfo info10 = new AdapterDeviceInfo();

        info10.setId("id");
        info10.setShowTenant("showTenant");
        info10.setServiceIp("serviceIp");
        info10.setNeType("neType");
        info10.setVersion("version");
        info10.setStatus("statusTest");
        info10.setGisLon(0.00);
        info10.setGisLat(0.01);
        info10.setVendor("vendor");
        info10.setTenantId("tenantId");
        info10.setTenantName("tenantName");
        info10.setOrgnizationId("orgnizationId");
        info10.setCreator("creator");
        info10.setCreateTime("createTime");
        info10.setRegisterTime("registerTime");
        info10.setModifier("modifier");
        info10.setModifyTime("modifyTime");

        assertFalse(info1.equals(info10));
    }

    @Test
    public void testEquals11() {
        AdapterDeviceInfo info11 = new AdapterDeviceInfo();

        info11.setId("id");
        info11.setShowTenant("showTenant");
        info11.setServiceIp("serviceIp");
        info11.setNeType("neType");
        info11.setVersion("version");
        info11.setStatus("status");
        info11.setGisLon(0.00123);
        info11.setGisLat(0.01);
        info11.setVendor("vendor");
        info11.setTenantId("tenantId");
        info11.setTenantName("tenantName");
        info11.setOrgnizationId("orgnizationId");
        info11.setCreator("creator");
        info11.setCreateTime("createTime");
        info11.setRegisterTime("registerTime");
        info11.setModifier("modifier");
        info11.setModifyTime("modifyTime");

        assertFalse(info1.equals(info11));
    }

    @Test
    public void testEquals12() {
        AdapterDeviceInfo info12 = new AdapterDeviceInfo();

        info12.setId("id");
        info12.setShowTenant("showTenant");
        info12.setServiceIp("serviceIp");
        info12.setNeType("neType");
        info12.setVersion("version");
        info12.setStatus("status");
        info12.setGisLon(0.00);
        info12.setGisLat(0.01123);
        info12.setVendor("vendor");
        info12.setTenantId("tenantId");
        info12.setTenantName("tenantName");
        info12.setOrgnizationId("orgnizationId");
        info12.setCreator("creator");
        info12.setCreateTime("createTime");
        info12.setRegisterTime("registerTime");
        info12.setModifier("modifier");
        info12.setModifyTime("modifyTime");

        assertFalse(info1.equals(info12));
    }

    @Test
    public void testEquals13() {
        AdapterDeviceInfo info13 = new AdapterDeviceInfo();

        info13.setId("id");
        info13.setShowTenant("showTenant");
        info13.setServiceIp("serviceIp");
        info13.setNeType("neType");
        info13.setVersion("version");
        info13.setStatus("status");
        info13.setGisLon(0.00);
        info13.setGisLat(0.01);
        info13.setVendor("vendorTest");
        info13.setTenantId("tenantId");
        info13.setTenantName("tenantName");
        info13.setOrgnizationId("orgnizationId");
        info13.setCreator("creator");
        info13.setCreateTime("createTime");
        info13.setRegisterTime("registerTime");
        info13.setModifier("modifier");
        info13.setModifyTime("modifyTime");

        assertFalse(info1.equals(info13));
    }

    @Test
    public void testEquals14() {
        AdapterDeviceInfo info14 = new AdapterDeviceInfo();

        info14.setId("id");
        info14.setShowTenant("showTenant");
        info14.setServiceIp("serviceIp");
        info14.setNeType("neType");
        info14.setVersion("version");
        info14.setStatus("status");
        info14.setGisLon(0.00);
        info14.setGisLat(0.01);
        info14.setVendor("vendor");
        info14.setTenantId("tenantIdTest");
        info14.setTenantName("tenantName");
        info14.setOrgnizationId("orgnizationId");
        info14.setCreator("creator");
        info14.setCreateTime("createTime");
        info14.setRegisterTime("registerTime");
        info14.setModifier("modifier");
        info14.setModifyTime("modifyTime");

        assertFalse(info1.equals(info14));
    }

    @Test
    public void testEquals15() {
        AdapterDeviceInfo info15 = new AdapterDeviceInfo();

        info15.setId("id");
        info15.setShowTenant("showTenant");
        info15.setServiceIp("serviceIp");
        info15.setNeType("neType");
        info15.setVersion("version");
        info15.setStatus("status");
        info15.setGisLon(0.00);
        info15.setGisLat(0.01);
        info15.setVendor("vendor");
        info15.setTenantId("tenantId");
        info15.setTenantName("tenantNameTest");
        info15.setOrgnizationId("orgnizationId");
        info15.setCreator("creator");
        info15.setCreateTime("createTime");
        info15.setRegisterTime("registerTime");
        info15.setModifier("modifier");
        info15.setModifyTime("modifyTime");

        assertFalse(info1.equals(info15));
    }

    @Test
    public void testEquals16() {
        AdapterDeviceInfo info16 = new AdapterDeviceInfo();

        info16.setId("id");
        info16.setShowTenant("showTenant");
        info16.setServiceIp("serviceIp");
        info16.setNeType("neType");
        info16.setVersion("version");
        info16.setStatus("status");
        info16.setGisLon(0.00);
        info16.setGisLat(0.01);
        info16.setVendor("vendor");
        info16.setTenantId("tenantId");
        info16.setTenantName("tenantName");
        info16.setOrgnizationId("orgnizationIdTest");
        info16.setCreator("creator");
        info16.setCreateTime("createTime");
        info16.setRegisterTime("registerTime");
        info16.setModifier("modifier");
        info16.setModifyTime("modifyTime");

        assertFalse(info1.equals(info16));
    }

    @Test
    public void testEquals17() {
        AdapterDeviceInfo info17 = new AdapterDeviceInfo();

        info17.setId("id");
        info17.setShowTenant("showTenant");
        info17.setServiceIp("serviceIp");
        info17.setNeType("neType");
        info17.setVersion("version");
        info17.setStatus("status");
        info17.setGisLon(0.00);
        info17.setGisLat(0.01);
        info17.setVendor("vendor");
        info17.setTenantId("tenantId");
        info17.setTenantName("tenantName");
        info17.setOrgnizationId("orgnizationId");
        info17.setCreator("creatorTest");
        info17.setCreateTime("createTime");
        info17.setRegisterTime("registerTime");
        info17.setModifier("modifier");
        info17.setModifyTime("modifyTime");

        assertFalse(info1.equals(info17));
    }

    @Test
    public void testEquals18() {
        AdapterDeviceInfo info18 = new AdapterDeviceInfo();

        info18.setId("id");
        info18.setShowTenant("showTenant");
        info18.setServiceIp("serviceIp");
        info18.setNeType("neType");
        info18.setVersion("version");
        info18.setStatus("status");
        info18.setGisLon(0.00);
        info18.setGisLat(0.01);
        info18.setVendor("vendor");
        info18.setTenantId("tenantId");
        info18.setTenantName("tenantName");
        info18.setOrgnizationId("orgnizationId");
        info18.setCreator("creator");
        info18.setCreateTime("createTimeTest");
        info18.setRegisterTime("registerTime");
        info18.setModifier("modifier");
        info18.setModifyTime("modifyTime");

        assertFalse(info1.equals(info18));
    }

    @Test
    public void testEquals19() {
        AdapterDeviceInfo info19 = new AdapterDeviceInfo();

        info19.setId("id");
        info19.setShowTenant("showTenant");
        info19.setServiceIp("serviceIp");
        info19.setNeType("neType");
        info19.setVersion("version");
        info19.setStatus("status");
        info19.setGisLon(0.00);
        info19.setGisLat(0.01);
        info19.setVendor("vendor");
        info19.setTenantId("tenantId");
        info19.setTenantName("tenantName");
        info19.setOrgnizationId("orgnizationId");
        info19.setCreator("creator");
        info19.setCreateTime("createTime");
        info19.setRegisterTime("registerTimeTest");
        info19.setModifier("modifier");
        info19.setModifyTime("modifyTime");

        assertFalse(info1.equals(info19));
    }

    @Test
    public void testEquals20() {
        AdapterDeviceInfo info20 = new AdapterDeviceInfo();

        info20.setId("id");
        info20.setShowTenant("showTenant");
        info20.setServiceIp("serviceIp");
        info20.setNeType("neType");
        info20.setVersion("version");
        info20.setStatus("status");
        info20.setGisLon(0.00);
        info20.setGisLat(0.01);
        info20.setVendor("vendor");
        info20.setTenantId("tenantId");
        info20.setTenantName("tenantName");
        info20.setOrgnizationId("orgnizationId");
        info20.setCreator("creator");
        info20.setCreateTime("createTime");
        info20.setRegisterTime("registerTime");
        info20.setModifier("modifierTest");
        info20.setModifyTime("modifyTime");

        assertFalse(info1.equals(info20));
    }

    @Test
    public void testEquals21() {
        AdapterDeviceInfo info21 = new AdapterDeviceInfo();

        info21.setId("id");
        info21.setShowTenant("showTenant");
        info21.setServiceIp("serviceIp");
        info21.setNeType("neType");
        info21.setVersion("version");
        info21.setStatus("status");
        info21.setGisLon(0.00);
        info21.setGisLat(0.01);
        info21.setVendor("vendor");
        info21.setTenantId("tenantId");
        info21.setTenantName("tenantName");
        info21.setOrgnizationId("orgnizationId");
        info21.setCreator("creator");
        info21.setCreateTime("createTime");
        info21.setRegisterTime("registerTime");
        info21.setModifier("modifier");
        info21.setModifyTime("modifyTimeTest");

        assertFalse(info1.equals(info21));
    }

    @Test
    public void testEquals22() {
        assertFalse(info1.equals(new Object()));
    }

}
