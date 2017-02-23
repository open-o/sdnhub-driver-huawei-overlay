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

import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.common.util.CheckIpV6Util;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiStaticRoute;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNeStaticRoute;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class StaticRouteConvertTest {

    @Test
    public void testFilterCreatedStaticRouteList() {

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("id1234");
        cnsr.setIp("ip1234");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(true);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.filterCreatedStaticRouteList("Ctrluuid1234", "deviceid1234", list);
    }

    @Test
    public void testFilterCreatedStaticRouteList1() {

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("id1234");
        cnsr.setIp("192.12.11.12");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("");
        cnsr.setMask("24");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setId("1234");
        cnsr1.setIp("192.12.11.14");
        cnsr1.setBfdName("bfdName123");
        cnsr1.setDescription("description123");
        cnsr1.setDhcp(false);
        cnsr1.setIpv6Address("::0000");
        cnsr1.setMask("24");
        cnsr1.setNextHop("nextHop");
        cnsr1.setNqaId("nqaId");
        cnsr1.setOutInterface("outInterfaceforcnsr");
        cnsr1.setPriority("priorityfirst123");
        cnsr1.setVpnId("vpnid123456");
        cnsr1.setVpnName("vpnName123");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);
        list.add(cnsr1);

        CheckIpV6Util.isValidIpV6("2001:2001:2001:2001:2001:2001:2001:2001");

        StaticRouteConvert.filterCreatedStaticRouteList("Ctrluuid1234", "deviceid1234", list);
    }

    @Test
    public void testGetSameTunnelFromAc() {

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("id1234");
        cnsr.setIp("ip1234");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(true);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr);
    }

    @Test
    public void testGetSameTunnelFromAc11() {

        CheckIpV6Util.isValidIpV6("192.32.12");

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setId("");
        cnsr1.setIp("192.32.12");
        cnsr1.setNextHop("jhbub");
        cnsr1.setIpv6Address("");
        cnsr1.setOutInterface("jhbiughfui");

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("123id");
        cnsr.setIp("192.32.12");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr1);
    }

    @Test
    public void testGetSameTunnelFromAc111() {

        CheckIpV6Util.isValidIpV6("ipv6addressforcnsr");

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setIpv6Address("ipv6addressforcnsr");

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("123id");
        cnsr.setIp("192.32.12");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr1);
    }

    @Test
    public void testGetSameTunnelFromAc123() {

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setId("");
        cnsr1.setIp("");
        cnsr1.setNextHop("jhbub");
        cnsr1.setIpv6Address("ipv6addressforcnsr");
        cnsr1.setOutInterface("jhbiughfui");
        cnsr1.setMask("");

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("123id");
        cnsr.setIp("");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr1);
    }

    @Test
    public void testGetSameTunnelFromAc112() {

        CheckIpV6Util.isValidIpV6("ipv6addressforcnsr");

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setId("");
        cnsr1.setNextHop("jhbub");
        cnsr1.setIpv6Address("ipv6addressforcnsr");
        cnsr1.setOutInterface("jhbiughfui");

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("123id");
        cnsr.setIp("ip1234");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr1);
    }

    @Test
    public void testGetSameTunnelFromAc_1() {

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setId("");
        cnsr1.setNextHop("1234nextHop");

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("123id");
        cnsr.setIp("ip1234");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr1);
    }

    @Test
    public void testGetSameTunnelFromAc1() {

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setId("");

        cnsr1.setOutInterface("1234outInterfaceforcnsr");

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("123id");
        cnsr.setIp("ip1234");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr1);
    }

    @Test
    public void testGetSameTunnelFromAc2() {

        ControllerNbiStaticRoute cnsr1 = new ControllerNbiStaticRoute();

        cnsr1.setId("");

        cnsr1.setIpv6Address("ipv6addressforcnsr");

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("123id");
        cnsr.setIp("ip1234");
        cnsr.setBfdName("bfdName");
        cnsr.setDescription("description");
        cnsr.setDhcp(false);
        cnsr.setIpv6Address("ipv6addressforcnsr");
        cnsr.setMask("mask");
        cnsr.setNextHop("1234nextHop");
        cnsr.setNqaId("nqaId1234");
        cnsr.setOutInterface("1234outInterfaceforcnsr");
        cnsr.setPriority("priorityfirst");
        cnsr.setVpnId("vpnid1234");
        cnsr.setVpnName("vpnName");
        List<ControllerNbiStaticRoute> list = new ArrayList<>();
        list.add(cnsr);

        StaticRouteConvert.getSameTunnelFromAc(list, cnsr1);
    }

    @Test
    public void testConvert2Route() {

        SbiNeStaticRoute snsr = new SbiNeStaticRoute();

        snsr.setActiveStatus("activeStatus");
        snsr.setControllerId("controllerId123");
        snsr.setAdditionalInfo("additionalInfontg");
        snsr.setCreatetime((long)12345);
        snsr.setDeployStatus("deployStatusissuccess");
        snsr.setDestIp("destIp1234");
        snsr.setDeviceId("deviceId1234");
        snsr.setEnableDhcp("enableDhcp");
        snsr.setExternalId("externalId1234");
        snsr.setName("name:Raju");
        snsr.setNbiNeRouteId("nbiNeRouteId1234");
        snsr.setNextHop("1234nextHop");
        snsr.setNqa("nqa");
        snsr.setOperationStatus("operationStatusissuccess");
        snsr.setOutInterface("1234outInterface");
        snsr.setPriority("priorityisfirst");
        snsr.setRunningStatus("runningStatusissuccess");
        snsr.setTenantId("tenantId1234");
        snsr.setUpdatetime((long)123456789);
        snsr.setUuid("uuid1234");
        List<SbiNeStaticRoute> list = new ArrayList<>();
        list.add(snsr);

        StaticRouteConvert.convert2Route(list, false);
    }

    @Test
    public void testConvert2Route_1() {

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();
        cnsr.setId(null);

        SbiNeStaticRoute snsr = new SbiNeStaticRoute();

        snsr.setActiveStatus("activeStatus");
        snsr.setControllerId("controllerId123");
        snsr.setAdditionalInfo("additionalInfontg");
        snsr.setCreatetime((long)12345);
        snsr.setDeployStatus("deployStatusissuccess");
        snsr.setDestIp("destIp1234");
        snsr.setDeviceId("deviceId1234");
        snsr.setEnableDhcp("enableDhcp");
        snsr.setExternalId("");
        snsr.setName("name:Raju");
        snsr.setNbiNeRouteId("nbiNeRouteId1234");
        snsr.setNextHop(null);
        snsr.setNqa("nqa");
        snsr.setOperationStatus("operationStatusissuccess");
        snsr.setOutInterface("1234outInterface");
        snsr.setRunningStatus("runningStatusissuccess");
        snsr.setTenantId("tenantId1234");
        snsr.setUpdatetime((long)123456789);
        snsr.setUuid("uuid1234");
        List<SbiNeStaticRoute> list = new ArrayList<>();
        list.add(snsr);

        StaticRouteConvert.convert2Route(list, true);
    }

    @Test
    public void testConvert2Route11() {

        ControllerNbiStaticRoute cnsr = new ControllerNbiStaticRoute();

        cnsr.setId("12k-45khuih-0548jh");
        cnsr.setVpnId("1234id");
        List<ControllerNbiStaticRoute> list1 = new ArrayList<>();
        list1.add(cnsr);

        Map<String, List<ControllerNbiStaticRoute>> neId2Tunnels = new ConcurrentHashMap<>();
        neId2Tunnels.put("setId", list1);
        neId2Tunnels.put("entrySet", list1);

        SbiNeStaticRoute snsr = new SbiNeStaticRoute();

        snsr.setActiveStatus("activeStatus");
        snsr.setControllerId("controllerId123");
        snsr.setAdditionalInfo("additionalInfontg");
        snsr.setCreatetime((long)12345);
        snsr.setDeployStatus("deployStatusissuccess");
        snsr.setDestIp("destIp1234");
        snsr.setDeviceId("12k-45khuih-0548jh");
        snsr.setEnableDhcp("enableDhcp");
        snsr.setExternalId("");
        snsr.setName("name:Raju");
        snsr.setNbiNeRouteId("nbiNeRouteId1234");
        snsr.setNextHop(null);
        snsr.setNqa("nqa");
        snsr.setOperationStatus("operationStatusissuccess");
        snsr.setOutInterface("1234outInterface");
        snsr.setRunningStatus("runningStatusissuccess");
        snsr.setTenantId("tenantId1234");
        snsr.setUpdatetime((long)123456789);
        snsr.setUuid("uuid1234");
        List<SbiNeStaticRoute> list = new ArrayList<>();
        list.add(snsr);

        StaticRouteConvert.convert2Route(list, false);

    }
}
