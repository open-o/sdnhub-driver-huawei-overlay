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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.openo.sdnhub.overlayvpndriver.controller.model.Ip;
import org.openo.sdnhub.overlayvpndriver.service.model.NQADeviceModel;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNqa;

import java.util.ArrayList;
import java.util.List;


public class NqaIpSecTranslateTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBuildNqa() {

        SbiNeIpSec snis = new SbiNeIpSec();
        snis.setConnectionServiceId("connectionServiceId");
        snis.setTenantId("tenantId");
        snis.setPeerAddress("peerAddress");
        SbiNqa sn = new SbiNqa();
        sn.setTestType("testType");
        Ip ip = new Ip();
        ip.setIpv4("ipv4");

        NqaIpSecTranslate.buildNqa(snis, sn);

    }

    @Test
    public void testConvertDeviceMode() {

        NQADeviceModel ndm = new NQADeviceModel();
        ndm.setFrequency(12345);
        ndm.setDstIp("192.32.35.3");
        ndm.setId("12345");
        ndm.setTestType("testType");
        ndm.setSrcIp("192.53.62.31");
        ndm.setSrcport("8012");

        SbiNqa sn = new SbiNqa();
        sn.setTestType("testType");

        List<SbiNqa> list = new ArrayList<>();
        list.add(sn);

        List<NQADeviceModel> response = NqaIpSecTranslate.convertDeviceMode(list);

        assertEquals(response.isEmpty(), false);
    }

    @Test
    public void testConvertDeviceModeFailed() {

        NQADeviceModel ndm = new NQADeviceModel();
        ndm.setFrequency(12345);
        ndm.setDstIp("192.32.35.3");
        ndm.setId("12345");
        ndm.setTestType("testType");
        ndm.setSrcIp("192.53.62.31");
        ndm.setSrcport("8012");
        ndm.setFrequency(12345);
        ndm.setProveCount(123);
        ndm.setTtl(123456);
        ndm.setTos(124536);

        SbiNqa sn = new SbiNqa();
        sn.setFrequency("1234");

        List<SbiNqa> list = new ArrayList<>();
        list.add(sn);

        List<NQADeviceModel> response = NqaIpSecTranslate.convertDeviceMode(list);

        assertEquals(response.isEmpty(), false);
    }

    @Test
    public void testConvertDeviceModeFailed1() {

        NQADeviceModel ndm = new NQADeviceModel();
        ndm.setFrequency(12345);
        ndm.setDstIp("192.32.35.3");
        ndm.setId("12345");
        ndm.setTestType("testType");
        ndm.setSrcIp("192.53.62.31");
        ndm.setSrcport("8012");
        ndm.setFrequency(12345);
        ndm.setProveCount(123);
        ndm.setTtl(123456);
        ndm.setTos(124536);

        SbiNqa sn = new SbiNqa();
        sn.setProbeCount("1494564");
        sn.setTtl("41969");

        List<SbiNqa> list = new ArrayList<>();
        list.add(sn);

        List<NQADeviceModel> response = NqaIpSecTranslate.convertDeviceMode(list);

        assertEquals(response.isEmpty(), false);
    }

    @Test
    public void testConvertDeviceModeFailed2() {

        NQADeviceModel ndm = new NQADeviceModel();
        ndm.setFrequency(12345);
        ndm.setDstIp("192.32.35.3");
        ndm.setId("12345");
        ndm.setTestType("testType");
        ndm.setSrcIp("192.53.62.31");
        ndm.setSrcport("8012");
        ndm.setFrequency(12345);
        ndm.setProveCount(123);
        ndm.setTtl(123456);
        ndm.setTos(124536);

        SbiNqa sn = new SbiNqa();

        sn.setTtl("5146826");

        List<SbiNqa> list = new ArrayList<>();
        list.add(sn);

        List<NQADeviceModel> response = NqaIpSecTranslate.convertDeviceMode(list);

        assertEquals(response.isEmpty(), false);
    }

    @Test
    public void testConvertDeviceModeFailed3() {

        NQADeviceModel ndm = new NQADeviceModel();
        ndm.setFrequency(12345);
        ndm.setDstIp("192.32.35.3");
        ndm.setId("12345");
        ndm.setTestType("testType");
        ndm.setSrcIp("192.53.62.31");
        ndm.setSrcport("8012");
        ndm.setFrequency(12345);
        ndm.setProveCount(123);
        ndm.setTtl(123456);
        ndm.setTos(124536);

        SbiNqa sn = new SbiNqa();

        sn.setTos("62569289");

        List<SbiNqa> list = new ArrayList<>();
        list.add(sn);

        List<NQADeviceModel> response = NqaIpSecTranslate.convertDeviceMode(list);

        assertEquals(response.isEmpty(), false);
    }

    @Test
    public void testConvertNqa() {

        NQADeviceModel ndm = new NQADeviceModel();
        ndm.setFrequency(12345);
        ndm.setDstIp("192.32.35.3");
        ndm.setId("12345");
        ndm.setTestType("testType");
        ndm.setSrcIp("192.53.62.31");
        ndm.setSrcport("8012");
        List<NQADeviceModel> list1 = new ArrayList<>();
        list1.add(ndm);
        SbiNqa sn = new SbiNqa();
        sn.setTestType("testType");
        sn.setUuid("12345");

        List<SbiNqa> list = new ArrayList<>();
        list.add(sn);

        List<SbiNqa> response = NqaIpSecTranslate.convertNqa(list1, list);

        assertEquals(response.isEmpty(), false);
    }

    @Test
    public void testConvertNqaFailed() {

        NQADeviceModel ndm = new NQADeviceModel();
        ndm.setFrequency(12345);
        ndm.setDstIp("192.32.35.3");
        ndm.setId("12345");
        ndm.setTestType("testType");
        ndm.setSrcIp("192.53.62.31");
        ndm.setSrcport("8012");
        List<NQADeviceModel> list1 = new ArrayList<>();
        list1.add(ndm);
        SbiNqa sn = new SbiNqa();
        sn.setTestType("testType");
        sn.setUuid("1251646345");

        List<SbiNqa> list = new ArrayList<>();
        list.add(sn);

        List<SbiNqa> response = NqaIpSecTranslate.convertNqa(list1, list);

        assertEquals(response.isEmpty(), false);
    }

}
