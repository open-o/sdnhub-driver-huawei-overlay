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

public class SbiSubnetNetModelTest {

    SbiSubnetNetModel model1 = new SbiSubnetNetModel();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {

        model1.setNeId("neId");
        model1.setControllerId("controllerId");
        model1.setNetworkId("networkId");
        model1.setServiceSubnetId("serviceSubnetId");
        model1.setVni("vni");
        model1.setVlanId("vlanId");
        model1.setCidrIpAddress("cidrIpAddress");
        model1.setCidrMask("cidrMask");
        model1.setGatewayIp("gatewayIp");
        model1.setEnableDhcp("enableDhcp");
        model1.setIpRangeStartIp("ipRangeStartIp");
        model1.setIpRangeEndIp("ipRangeEndIp");
        model1.setUseMode("useMode");
        model1.setChangedMode("changedMode");
        model1.setDhcpMode("dhcpMode");
        model1.setDnsMode("dnsMode");
        model1.setUnlimit("unlimit");
        model1.setIpv6Address("ipv6Address");
        model1.setPrefixLength("prefixLength");
        model1.setDhcp6Enable("dhcp6Enable");
        model1.setDhcp6Mode("dhcp6Mode");
        model1.setPriorDnsServer("priorDnsServer");
        model1.setStandbyDnsServer("standbyDnsServer");

    }

    @Test
    public void testHashCode() {

        SbiSubnetNetModel model2 = new SbiSubnetNetModel();

        model2.setNeId("neId");
        model2.setControllerId("controllerId");
        model2.setNetworkId("networkId");
        model2.setServiceSubnetId("serviceSubnetId");
        model2.setVni("vni");
        model2.setVlanId("vlanId");
        model2.setCidrIpAddress("cidrIpAddress");
        model2.setCidrMask("cidrMask");
        model2.setGatewayIp("gatewayIp");
        model2.setEnableDhcp("enableDhcp");
        model2.setIpRangeStartIp("ipRangeStartIp");
        model2.setIpRangeEndIp("ipRangeEndIp");
        model2.setUseMode("useMode");
        model2.setChangedMode("changedMode");
        model2.setDhcpMode("dhcpMode");
        model2.setDnsMode("dnsMode");
        model2.setUnlimit("unlimit");
        model2.setIpv6Address("ipv6Address");
        model2.setPrefixLength("prefixLength");
        model2.setDhcp6Enable("dhcp6Enable");
        model2.setDhcp6Mode("dhcp6Mode");
        model2.setPriorDnsServer("priorDnsServer");
        model2.setStandbyDnsServer("standbyDnsServer");

        model2.hashCode();
    }

    @Test
    public void testHashCodeNull() {

        SbiSubnetNetModel model3 = new SbiSubnetNetModel();

        model3.setNeId(null);
        model3.setControllerId(null);
        model3.setNetworkId(null);
        model3.setServiceSubnetId(null);
        model3.setVni(null);
        model3.setVlanId(null);
        model3.setCidrIpAddress(null);
        model3.setCidrMask(null);
        model3.setGatewayIp(null);
        model3.setEnableDhcp(null);
        model3.setIpRangeStartIp(null);
        model3.setIpRangeEndIp(null);
        model3.setUseMode(null);
        model3.setChangedMode(null);
        model3.setDhcpMode(null);
        model3.setDnsMode(null);
        model3.setUnlimit(null);
        model3.setIpv6Address(null);
        model3.setPrefixLength(null);
        model3.setDhcp6Enable(null);
        model3.setDhcp6Mode(null);
        model3.setPriorDnsServer(null);
        model3.setStandbyDnsServer(null);

        model3.hashCode();
    }

    @Test
    public void testEquals1() {

        SbiSubnetNetModel model4 = new SbiSubnetNetModel();

        model4.setNeId("neId");
        model4.setControllerId("controllerId");
        model4.setNetworkId("networkId");
        model4.setServiceSubnetId("serviceSubnetId");
        model4.setVni("vni");
        model4.setVlanId("vlanId");
        model4.setCidrIpAddress("cidrIpAddress");
        model4.setCidrMask("cidrMask");
        model4.setGatewayIp("gatewayIp");
        model4.setEnableDhcp("enableDhcp");
        model4.setIpRangeStartIp("ipRangeStartIp");
        model4.setIpRangeEndIp("ipRangeEndIp");
        model4.setUseMode("useMode");
        model4.setChangedMode("changedMode");
        model4.setDhcpMode("dhcpMode");
        model4.setDnsMode("dnsMode");
        model4.setUnlimit("unlimit");
        model4.setIpv6Address("ipv6Address");
        model4.setPrefixLength("prefixLength");
        model4.setDhcp6Enable("dhcp6Enable");
        model4.setDhcp6Mode("dhcp6Mode");
        model4.setPriorDnsServer("priorDnsServer");
        model4.setStandbyDnsServer("standbyDnsServer");

        assertTrue(model1.equals(model4));
    }

    @Test
    public void testEquals2() {

        assertTrue(model1.equals(model1));
    }

    @Test
    public void testEquals3() {

        assertFalse(model1.equals(null));
    }

    @Test
    public void testEquals5() {

        SbiSubnetNetModel model5 = new SbiSubnetNetModel();

        model5.setNeId("neIdTest");
        model5.setControllerId("controllerId");
        model5.setNetworkId("networkId");
        model5.setServiceSubnetId("serviceSubnetId");
        model5.setVni("vni");
        model5.setVlanId("vlanId");
        model5.setCidrIpAddress("cidrIpAddress");
        model5.setCidrMask("cidrMask");
        model5.setGatewayIp("gatewayIp");
        model5.setEnableDhcp("enableDhcp");
        model5.setIpRangeStartIp("ipRangeStartIp");
        model5.setIpRangeEndIp("ipRangeEndIp");
        model5.setUseMode("useMode");
        model5.setChangedMode("changedMode");
        model5.setDhcpMode("dhcpMode");
        model5.setDnsMode("dnsMode");
        model5.setUnlimit("unlimit");
        model5.setIpv6Address("ipv6Address");
        model5.setPrefixLength("prefixLength");
        model5.setDhcp6Enable("dhcp6Enable");
        model5.setDhcp6Mode("dhcp6Mode");
        model5.setPriorDnsServer("priorDnsServer");
        model5.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model5));
    }

    @Test
    public void testEquals6() {

        SbiSubnetNetModel model6 = new SbiSubnetNetModel();

        model6.setNeId("neId");
        model6.setControllerId("controllerIdTest");
        model6.setNetworkId("networkId");
        model6.setServiceSubnetId("serviceSubnetId");
        model6.setVni("vni");
        model6.setVlanId("vlanId");
        model6.setCidrIpAddress("cidrIpAddress");
        model6.setCidrMask("cidrMask");
        model6.setGatewayIp("gatewayIp");
        model6.setEnableDhcp("enableDhcp");
        model6.setIpRangeStartIp("ipRangeStartIp");
        model6.setIpRangeEndIp("ipRangeEndIp");
        model6.setUseMode("useMode");
        model6.setChangedMode("changedMode");
        model6.setDhcpMode("dhcpMode");
        model6.setDnsMode("dnsMode");
        model6.setUnlimit("unlimit");
        model6.setIpv6Address("ipv6Address");
        model6.setPrefixLength("prefixLength");
        model6.setDhcp6Enable("dhcp6Enable");
        model6.setDhcp6Mode("dhcp6Mode");
        model6.setPriorDnsServer("priorDnsServer");
        model6.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model6));
    }

    @Test
    public void testEquals7() {

        SbiSubnetNetModel model7 = new SbiSubnetNetModel();

        model7.setNeId("neId");
        model7.setControllerId("controllerId");
        model7.setNetworkId("networkIdTest");
        model7.setServiceSubnetId("serviceSubnetId");
        model7.setVni("vni");
        model7.setVlanId("vlanId");
        model7.setCidrIpAddress("cidrIpAddress");
        model7.setCidrMask("cidrMask");
        model7.setGatewayIp("gatewayIp");
        model7.setEnableDhcp("enableDhcp");
        model7.setIpRangeStartIp("ipRangeStartIp");
        model7.setIpRangeEndIp("ipRangeEndIp");
        model7.setUseMode("useMode");
        model7.setChangedMode("changedMode");
        model7.setDhcpMode("dhcpMode");
        model7.setDnsMode("dnsMode");
        model7.setUnlimit("unlimit");
        model7.setIpv6Address("ipv6Address");
        model7.setPrefixLength("prefixLength");
        model7.setDhcp6Enable("dhcp6Enable");
        model7.setDhcp6Mode("dhcp6Mode");
        model7.setPriorDnsServer("priorDnsServer");
        model7.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model7));
    }

    @Test
    public void testEquals8() {

        SbiSubnetNetModel model8 = new SbiSubnetNetModel();

        model8.setNeId("neId");
        model8.setControllerId("controllerId");
        model8.setNetworkId("networkId");
        model8.setServiceSubnetId("serviceSubnetIdTest");
        model8.setVni("vni");
        model8.setVlanId("vlanId");
        model8.setCidrIpAddress("cidrIpAddress");
        model8.setCidrMask("cidrMask");
        model8.setGatewayIp("gatewayIp");
        model8.setEnableDhcp("enableDhcp");
        model8.setIpRangeStartIp("ipRangeStartIp");
        model8.setIpRangeEndIp("ipRangeEndIp");
        model8.setUseMode("useMode");
        model8.setChangedMode("changedMode");
        model8.setDhcpMode("dhcpMode");
        model8.setDnsMode("dnsMode");
        model8.setUnlimit("unlimit");
        model8.setIpv6Address("ipv6Address");
        model8.setPrefixLength("prefixLength");
        model8.setDhcp6Enable("dhcp6Enable");
        model8.setDhcp6Mode("dhcp6Mode");
        model8.setPriorDnsServer("priorDnsServer");
        model8.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model8));
    }

    @Test
    public void testEquals9() {

        SbiSubnetNetModel model9 = new SbiSubnetNetModel();

        model9.setNeId("neId");
        model9.setControllerId("controllerId");
        model9.setNetworkId("networkId");
        model9.setServiceSubnetId("serviceSubnetId");
        model9.setVni("vniTest");
        model9.setVlanId("vlanId");
        model9.setCidrIpAddress("cidrIpAddress");
        model9.setCidrMask("cidrMask");
        model9.setGatewayIp("gatewayIp");
        model9.setEnableDhcp("enableDhcp");
        model9.setIpRangeStartIp("ipRangeStartIp");
        model9.setIpRangeEndIp("ipRangeEndIp");
        model9.setUseMode("useMode");
        model9.setChangedMode("changedMode");
        model9.setDhcpMode("dhcpMode");
        model9.setDnsMode("dnsMode");
        model9.setUnlimit("unlimit");
        model9.setIpv6Address("ipv6Address");
        model9.setPrefixLength("prefixLength");
        model9.setDhcp6Enable("dhcp6Enable");
        model9.setDhcp6Mode("dhcp6Mode");
        model9.setPriorDnsServer("priorDnsServer");
        model9.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model9));
    }

    @Test
    public void testEquals10() {

        SbiSubnetNetModel model10 = new SbiSubnetNetModel();

        model10.setNeId("neId");
        model10.setControllerId("controllerId");
        model10.setNetworkId("networkId");
        model10.setServiceSubnetId("serviceSubnetId");
        model10.setVni("vni");
        model10.setVlanId("vlanIdTest");
        model10.setCidrIpAddress("cidrIpAddress");
        model10.setCidrMask("cidrMask");
        model10.setGatewayIp("gatewayIp");
        model10.setEnableDhcp("enableDhcp");
        model10.setIpRangeStartIp("ipRangeStartIp");
        model10.setIpRangeEndIp("ipRangeEndIp");
        model10.setUseMode("useMode");
        model10.setChangedMode("changedMode");
        model10.setDhcpMode("dhcpMode");
        model10.setDnsMode("dnsMode");
        model10.setUnlimit("unlimit");
        model10.setIpv6Address("ipv6Address");
        model10.setPrefixLength("prefixLength");
        model10.setDhcp6Enable("dhcp6Enable");
        model10.setDhcp6Mode("dhcp6Mode");
        model10.setPriorDnsServer("priorDnsServer");
        model10.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model10));
    }

    @Test
    public void testEquals11() {

        SbiSubnetNetModel model11 = new SbiSubnetNetModel();

        model11.setNeId("neId");
        model11.setControllerId("controllerId");
        model11.setNetworkId("networkId");
        model11.setServiceSubnetId("serviceSubnetId");
        model11.setVni("vni");
        model11.setVlanId("vlanId");
        model11.setCidrIpAddress("cidrIpAddressTest");
        model11.setCidrMask("cidrMask");
        model11.setGatewayIp("gatewayIp");
        model11.setEnableDhcp("enableDhcp");
        model11.setIpRangeStartIp("ipRangeStartIp");
        model11.setIpRangeEndIp("ipRangeEndIp");
        model11.setUseMode("useMode");
        model11.setChangedMode("changedMode");
        model11.setDhcpMode("dhcpMode");
        model11.setDnsMode("dnsMode");
        model11.setUnlimit("unlimit");
        model11.setIpv6Address("ipv6Address");
        model11.setPrefixLength("prefixLength");
        model11.setDhcp6Enable("dhcp6Enable");
        model11.setDhcp6Mode("dhcp6Mode");
        model11.setPriorDnsServer("priorDnsServer");
        model11.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model11));
    }

    @Test
    public void testEquals12() {

        SbiSubnetNetModel model12 = new SbiSubnetNetModel();

        model12.setNeId("neId");
        model12.setControllerId("controllerId");
        model12.setNetworkId("networkId");
        model12.setServiceSubnetId("serviceSubnetId");
        model12.setVni("vni");
        model12.setVlanId("vlanId");
        model12.setCidrIpAddress("cidrIpAddress");
        model12.setCidrMask("cidrMaskTest");
        model12.setGatewayIp("gatewayIp");
        model12.setEnableDhcp("enableDhcp");
        model12.setIpRangeStartIp("ipRangeStartIp");
        model12.setIpRangeEndIp("ipRangeEndIp");
        model12.setUseMode("useMode");
        model12.setChangedMode("changedMode");
        model12.setDhcpMode("dhcpMode");
        model12.setDnsMode("dnsMode");
        model12.setUnlimit("unlimit");
        model12.setIpv6Address("ipv6Address");
        model12.setPrefixLength("prefixLength");
        model12.setDhcp6Enable("dhcp6Enable");
        model12.setDhcp6Mode("dhcp6Mode");
        model12.setPriorDnsServer("priorDnsServer");
        model12.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model12));
    }

    @Test
    public void testEquals13() {

        SbiSubnetNetModel model13 = new SbiSubnetNetModel();

        model13.setNeId("neId");
        model13.setControllerId("controllerId");
        model13.setNetworkId("networkId");
        model13.setServiceSubnetId("serviceSubnetId");
        model13.setVni("vni");
        model13.setVlanId("vlanId");
        model13.setCidrIpAddress("cidrIpAddress");
        model13.setCidrMask("cidrMask");
        model13.setGatewayIp("gatewayIpTest");
        model13.setEnableDhcp("enableDhcp");
        model13.setIpRangeStartIp("ipRangeStartIp");
        model13.setIpRangeEndIp("ipRangeEndIp");
        model13.setUseMode("useMode");
        model13.setChangedMode("changedMode");
        model13.setDhcpMode("dhcpMode");
        model13.setDnsMode("dnsMode");
        model13.setUnlimit("unlimit");
        model13.setIpv6Address("ipv6Address");
        model13.setPrefixLength("prefixLength");
        model13.setDhcp6Enable("dhcp6Enable");
        model13.setDhcp6Mode("dhcp6Mode");
        model13.setPriorDnsServer("priorDnsServer");
        model13.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model13));
    }

    @Test
    public void testEquals14() {

        SbiSubnetNetModel model14 = new SbiSubnetNetModel();

        model14.setNeId("neId");
        model14.setControllerId("controllerId");
        model14.setNetworkId("networkId");
        model14.setServiceSubnetId("serviceSubnetId");
        model14.setVni("vni");
        model14.setVlanId("vlanId");
        model14.setCidrIpAddress("cidrIpAddress");
        model14.setCidrMask("cidrMask");
        model14.setGatewayIp("gatewayIp");
        model14.setEnableDhcp("enableDhcpTest");
        model14.setIpRangeStartIp("ipRangeStartIp");
        model14.setIpRangeEndIp("ipRangeEndIp");
        model14.setUseMode("useMode");
        model14.setChangedMode("changedMode");
        model14.setDhcpMode("dhcpMode");
        model14.setDnsMode("dnsMode");
        model14.setUnlimit("unlimit");
        model14.setIpv6Address("ipv6Address");
        model14.setPrefixLength("prefixLength");
        model14.setDhcp6Enable("dhcp6Enable");
        model14.setDhcp6Mode("dhcp6Mode");
        model14.setPriorDnsServer("priorDnsServer");
        model14.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model14));
    }

    @Test
    public void testEquals15() {

        SbiSubnetNetModel model15 = new SbiSubnetNetModel();

        model15.setNeId("neId");
        model15.setControllerId("controllerId");
        model15.setNetworkId("networkId");
        model15.setServiceSubnetId("serviceSubnetId");
        model15.setVni("vni");
        model15.setVlanId("vlanId");
        model15.setCidrIpAddress("cidrIpAddress");
        model15.setCidrMask("cidrMask");
        model15.setGatewayIp("gatewayIp");
        model15.setEnableDhcp("enableDhcp");
        model15.setIpRangeStartIp("ipRangeStartIpTest");
        model15.setIpRangeEndIp("ipRangeEndIp");
        model15.setUseMode("useMode");
        model15.setChangedMode("changedMode");
        model15.setDhcpMode("dhcpMode");
        model15.setDnsMode("dnsMode");
        model15.setUnlimit("unlimit");
        model15.setIpv6Address("ipv6Address");
        model15.setPrefixLength("prefixLength");
        model15.setDhcp6Enable("dhcp6Enable");
        model15.setDhcp6Mode("dhcp6Mode");
        model15.setPriorDnsServer("priorDnsServer");
        model15.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model15));
    }

    @Test
    public void testEquals16() {

        SbiSubnetNetModel model16 = new SbiSubnetNetModel();

        model16.setNeId("neId");
        model16.setControllerId("controllerId");
        model16.setNetworkId("networkId");
        model16.setServiceSubnetId("serviceSubnetId");
        model16.setVni("vni");
        model16.setVlanId("vlanId");
        model16.setCidrIpAddress("cidrIpAddress");
        model16.setCidrMask("cidrMask");
        model16.setGatewayIp("gatewayIp");
        model16.setEnableDhcp("enableDhcp");
        model16.setIpRangeStartIp("ipRangeStartIp");
        model16.setIpRangeEndIp("ipRangeEndIpTest");
        model16.setUseMode("useMode");
        model16.setChangedMode("changedMode");
        model16.setDhcpMode("dhcpMode");
        model16.setDnsMode("dnsMode");
        model16.setUnlimit("unlimit");
        model16.setIpv6Address("ipv6Address");
        model16.setPrefixLength("prefixLength");
        model16.setDhcp6Enable("dhcp6Enable");
        model16.setDhcp6Mode("dhcp6Mode");
        model16.setPriorDnsServer("priorDnsServer");
        model16.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model16));
    }

    @Test
    public void testEquals17() {

        SbiSubnetNetModel model17 = new SbiSubnetNetModel();

        model17.setNeId("neId");
        model17.setControllerId("controllerId");
        model17.setNetworkId("networkId");
        model17.setServiceSubnetId("serviceSubnetId");
        model17.setVni("vni");
        model17.setVlanId("vlanId");
        model17.setCidrIpAddress("cidrIpAddress");
        model17.setCidrMask("cidrMask");
        model17.setGatewayIp("gatewayIp");
        model17.setEnableDhcp("enableDhcp");
        model17.setIpRangeStartIp("ipRangeStartIp");
        model17.setIpRangeEndIp("ipRangeEndIp");
        model17.setUseMode("useModeTest");
        model17.setChangedMode("changedMode");
        model17.setDhcpMode("dhcpMode");
        model17.setDnsMode("dnsMode");
        model17.setUnlimit("unlimit");
        model17.setIpv6Address("ipv6Address");
        model17.setPrefixLength("prefixLength");
        model17.setDhcp6Enable("dhcp6Enable");
        model17.setDhcp6Mode("dhcp6Mode");
        model17.setPriorDnsServer("priorDnsServer");
        model17.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model17));
    }

    @Test
    public void testEquals18() {

        SbiSubnetNetModel model18 = new SbiSubnetNetModel();

        model18.setNeId("neId");
        model18.setControllerId("controllerId");
        model18.setNetworkId("networkId");
        model18.setServiceSubnetId("serviceSubnetId");
        model18.setVni("vni");
        model18.setVlanId("vlanId");
        model18.setCidrIpAddress("cidrIpAddress");
        model18.setCidrMask("cidrMask");
        model18.setGatewayIp("gatewayIp");
        model18.setEnableDhcp("enableDhcp");
        model18.setIpRangeStartIp("ipRangeStartIp");
        model18.setIpRangeEndIp("ipRangeEndIp");
        model18.setUseMode("useMode");
        model18.setChangedMode("changedModeTest");
        model18.setDhcpMode("dhcpMode");
        model18.setDnsMode("dnsMode");
        model18.setUnlimit("unlimit");
        model18.setIpv6Address("ipv6Address");
        model18.setPrefixLength("prefixLength");
        model18.setDhcp6Enable("dhcp6Enable");
        model18.setDhcp6Mode("dhcp6Mode");
        model18.setPriorDnsServer("priorDnsServer");
        model18.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model18));
    }

    @Test
    public void testEquals19() {

        SbiSubnetNetModel model19 = new SbiSubnetNetModel();

        model19.setNeId("neId");
        model19.setControllerId("controllerId");
        model19.setNetworkId("networkId");
        model19.setServiceSubnetId("serviceSubnetId");
        model19.setVni("vni");
        model19.setVlanId("vlanId");
        model19.setCidrIpAddress("cidrIpAddress");
        model19.setCidrMask("cidrMask");
        model19.setGatewayIp("gatewayIp");
        model19.setEnableDhcp("enableDhcp");
        model19.setIpRangeStartIp("ipRangeStartIp");
        model19.setIpRangeEndIp("ipRangeEndIp");
        model19.setUseMode("useMode");
        model19.setChangedMode("changedMode");
        model19.setDhcpMode("dhcpModeTest");
        model19.setDnsMode("dnsMode");
        model19.setUnlimit("unlimit");
        model19.setIpv6Address("ipv6Address");
        model19.setPrefixLength("prefixLength");
        model19.setDhcp6Enable("dhcp6Enable");
        model19.setDhcp6Mode("dhcp6Mode");
        model19.setPriorDnsServer("priorDnsServer");
        model19.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model19));
    }

    @Test
    public void testEquals20() {

        SbiSubnetNetModel model20 = new SbiSubnetNetModel();

        model20.setNeId("neId");
        model20.setControllerId("controllerId");
        model20.setNetworkId("networkId");
        model20.setServiceSubnetId("serviceSubnetId");
        model20.setVni("vni");
        model20.setVlanId("vlanId");
        model20.setCidrIpAddress("cidrIpAddress");
        model20.setCidrMask("cidrMask");
        model20.setGatewayIp("gatewayIp");
        model20.setEnableDhcp("enableDhcp");
        model20.setIpRangeStartIp("ipRangeStartIp");
        model20.setIpRangeEndIp("ipRangeEndIp");
        model20.setUseMode("useMode");
        model20.setChangedMode("changedMode");
        model20.setDhcpMode("dhcpMode");
        model20.setDnsMode("dnsModeTest");
        model20.setUnlimit("unlimit");
        model20.setIpv6Address("ipv6Address");
        model20.setPrefixLength("prefixLength");
        model20.setDhcp6Enable("dhcp6Enable");
        model20.setDhcp6Mode("dhcp6Mode");
        model20.setPriorDnsServer("priorDnsServer");
        model20.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model20));
    }

    @Test
    public void testEquals21() {

        SbiSubnetNetModel model21 = new SbiSubnetNetModel();

        model21.setNeId("neId");
        model21.setControllerId("controllerId");
        model21.setNetworkId("networkId");
        model21.setServiceSubnetId("serviceSubnetId");
        model21.setVni("vni");
        model21.setVlanId("vlanId");
        model21.setCidrIpAddress("cidrIpAddress");
        model21.setCidrMask("cidrMask");
        model21.setGatewayIp("gatewayIp");
        model21.setEnableDhcp("enableDhcp");
        model21.setIpRangeStartIp("ipRangeStartIp");
        model21.setIpRangeEndIp("ipRangeEndIp");
        model21.setUseMode("useMode");
        model21.setChangedMode("changedMode");
        model21.setDhcpMode("dhcpMode");
        model21.setDnsMode("dnsMode");
        model21.setUnlimit("unlimitTest");
        model21.setIpv6Address("ipv6Address");
        model21.setPrefixLength("prefixLength");
        model21.setDhcp6Enable("dhcp6Enable");
        model21.setDhcp6Mode("dhcp6Mode");
        model21.setPriorDnsServer("priorDnsServer");
        model21.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model21));
    }

    @Test
    public void testEquals22() {

        SbiSubnetNetModel model22 = new SbiSubnetNetModel();

        model22.setNeId("neId");
        model22.setControllerId("controllerId");
        model22.setNetworkId("networkId");
        model22.setServiceSubnetId("serviceSubnetId");
        model22.setVni("vni");
        model22.setVlanId("vlanId");
        model22.setCidrIpAddress("cidrIpAddress");
        model22.setCidrMask("cidrMask");
        model22.setGatewayIp("gatewayIp");
        model22.setEnableDhcp("enableDhcp");
        model22.setIpRangeStartIp("ipRangeStartIp");
        model22.setIpRangeEndIp("ipRangeEndIp");
        model22.setUseMode("useMode");
        model22.setChangedMode("changedMode");
        model22.setDhcpMode("dhcpMode");
        model22.setDnsMode("dnsMode");
        model22.setUnlimit("unlimit");
        model22.setIpv6Address("ipv6AddressTest");
        model22.setPrefixLength("prefixLength");
        model22.setDhcp6Enable("dhcp6Enable");
        model22.setDhcp6Mode("dhcp6Mode");
        model22.setPriorDnsServer("priorDnsServer");
        model22.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model22));
    }

    @Test
    public void testEquals23() {

        SbiSubnetNetModel model23 = new SbiSubnetNetModel();

        model23.setNeId("neId");
        model23.setControllerId("controllerId");
        model23.setNetworkId("networkId");
        model23.setServiceSubnetId("serviceSubnetId");
        model23.setVni("vni");
        model23.setVlanId("vlanId");
        model23.setCidrIpAddress("cidrIpAddress");
        model23.setCidrMask("cidrMask");
        model23.setGatewayIp("gatewayIp");
        model23.setEnableDhcp("enableDhcp");
        model23.setIpRangeStartIp("ipRangeStartIp");
        model23.setIpRangeEndIp("ipRangeEndIp");
        model23.setUseMode("useMode");
        model23.setChangedMode("changedMode");
        model23.setDhcpMode("dhcpMode");
        model23.setDnsMode("dnsMode");
        model23.setUnlimit("unlimit");
        model23.setIpv6Address("ipv6Address");
        model23.setPrefixLength("prefixLengthTest");
        model23.setDhcp6Enable("dhcp6Enable");
        model23.setDhcp6Mode("dhcp6Mode");
        model23.setPriorDnsServer("priorDnsServer");
        model23.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model23));
    }

    @Test
    public void testEquals24() {

        SbiSubnetNetModel model24 = new SbiSubnetNetModel();

        model24.setNeId("neId");
        model24.setControllerId("controllerId");
        model24.setNetworkId("networkId");
        model24.setServiceSubnetId("serviceSubnetId");
        model24.setVni("vni");
        model24.setVlanId("vlanId");
        model24.setCidrIpAddress("cidrIpAddress");
        model24.setCidrMask("cidrMask");
        model24.setGatewayIp("gatewayIp");
        model24.setEnableDhcp("enableDhcp");
        model24.setIpRangeStartIp("ipRangeStartIp");
        model24.setIpRangeEndIp("ipRangeEndIp");
        model24.setUseMode("useMode");
        model24.setChangedMode("changedMode");
        model24.setDhcpMode("dhcpMode");
        model24.setDnsMode("dnsMode");
        model24.setUnlimit("unlimit");
        model24.setIpv6Address("ipv6Address");
        model24.setPrefixLength("prefixLength");
        model24.setDhcp6Enable("dhcp6EnableTest");
        model24.setDhcp6Mode("dhcp6Mode");
        model24.setPriorDnsServer("priorDnsServer");
        model24.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model24));
    }

    @Test
    public void testEquals25() {

        SbiSubnetNetModel model25 = new SbiSubnetNetModel();

        model25.setNeId("neId");
        model25.setControllerId("controllerId");
        model25.setNetworkId("networkId");
        model25.setServiceSubnetId("serviceSubnetId");
        model25.setVni("vni");
        model25.setVlanId("vlanId");
        model25.setCidrIpAddress("cidrIpAddress");
        model25.setCidrMask("cidrMask");
        model25.setGatewayIp("gatewayIp");
        model25.setEnableDhcp("enableDhcp");
        model25.setIpRangeStartIp("ipRangeStartIp");
        model25.setIpRangeEndIp("ipRangeEndIp");
        model25.setUseMode("useMode");
        model25.setChangedMode("changedMode");
        model25.setDhcpMode("dhcpMode");
        model25.setDnsMode("dnsMode");
        model25.setUnlimit("unlimit");
        model25.setIpv6Address("ipv6Address");
        model25.setPrefixLength("prefixLength");
        model25.setDhcp6Enable("dhcp6Enable");
        model25.setDhcp6Mode("dhcp6ModeTest");
        model25.setPriorDnsServer("priorDnsServer");
        model25.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model25));
    }

    @Test
    public void testEquals26() {

        SbiSubnetNetModel model26 = new SbiSubnetNetModel();

        model26.setNeId("neId");
        model26.setControllerId("controllerId");
        model26.setNetworkId("networkId");
        model26.setServiceSubnetId("serviceSubnetId");
        model26.setVni("vni");
        model26.setVlanId("vlanId");
        model26.setCidrIpAddress("cidrIpAddress");
        model26.setCidrMask("cidrMask");
        model26.setGatewayIp("gatewayIp");
        model26.setEnableDhcp("enableDhcp");
        model26.setIpRangeStartIp("ipRangeStartIp");
        model26.setIpRangeEndIp("ipRangeEndIp");
        model26.setUseMode("useMode");
        model26.setChangedMode("changedMode");
        model26.setDhcpMode("dhcpMode");
        model26.setDnsMode("dnsMode");
        model26.setUnlimit("unlimit");
        model26.setIpv6Address("ipv6Address");
        model26.setPrefixLength("prefixLength");
        model26.setDhcp6Enable("dhcp6Enable");
        model26.setDhcp6Mode("dhcp6Mode");
        model26.setPriorDnsServer("priorDnsServerTest");
        model26.setStandbyDnsServer("standbyDnsServer");

        assertFalse(model1.equals(model26));
    }

    @Test
    public void testEquals27() {

        SbiSubnetNetModel model27 = new SbiSubnetNetModel();

        model27.setNeId("neId");
        model27.setControllerId("controllerId");
        model27.setNetworkId("networkId");
        model27.setServiceSubnetId("serviceSubnetId");
        model27.setVni("vni");
        model27.setVlanId("vlanId");
        model27.setCidrIpAddress("cidrIpAddress");
        model27.setCidrMask("cidrMask");
        model27.setGatewayIp("gatewayIp");
        model27.setEnableDhcp("enableDhcp");
        model27.setIpRangeStartIp("ipRangeStartIp");
        model27.setIpRangeEndIp("ipRangeEndIp");
        model27.setUseMode("useMode");
        model27.setChangedMode("changedMode");
        model27.setDhcpMode("dhcpMode");
        model27.setDnsMode("dnsMode");
        model27.setUnlimit("unlimit");
        model27.setIpv6Address("ipv6Address");
        model27.setPrefixLength("prefixLength");
        model27.setDhcp6Enable("dhcp6Enable");
        model27.setDhcp6Mode("dhcp6Mode");
        model27.setPriorDnsServer("priorDnsServer");
        model27.setStandbyDnsServer("standbyDnsServerTest");

        assertFalse(model1.equals(model27));
    }

    @Test
    public void testEquals28() {
        assertFalse(model1.equals(new Object()));
    }

}
