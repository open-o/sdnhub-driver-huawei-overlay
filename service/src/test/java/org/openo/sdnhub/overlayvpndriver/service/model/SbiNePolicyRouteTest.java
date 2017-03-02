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

public class SbiNePolicyRouteTest {

    SbiNePolicyRoute route1 = new SbiNePolicyRoute();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */

    @Before
    public void setup() {

        route1.setTrafficPolicyName("trafficPolicyName");
        route1.setInterfaceName("interfaceName");
        route1.setDirection("direction");
        route1.setFilterAction("filterAction");

    }

    @Test
    public void testHashCode() {

        SbiNePolicyRoute route2 = new SbiNePolicyRoute();

        route2.setTrafficPolicyName("trafficPolicyName");
        route2.setInterfaceName("interfaceName");
        route2.setDirection("direction");
        route2.setFilterAction("filterAction");

        route2.hashCode();
    }

    @Test
    public void testHashCodeNull() {

        SbiNePolicyRoute route3 = new SbiNePolicyRoute();

        route3.setTrafficPolicyName(null);
        route3.setInterfaceName(null);
        route3.setDirection(null);
        route3.setFilterAction(null);

        route3.hashCode();
    }
    
    @Test
    public void testEquals1() {

        SbiNePolicyRoute route4 = new SbiNePolicyRoute();

        route4.setTrafficPolicyName("trafficPolicyName");
        route4.setInterfaceName("interfaceName");
        route4.setDirection("direction");
        route4.setFilterAction("filterAction");

        assertTrue(route1.equals(route4));  
    }
    
    @Test
    public void testEquals2() {
        
        assertTrue(route1.equals(route1));  
    }
    
    @Test
    public void testEquals3() {
        
        assertFalse(route1.equals(null));  
    }
    
    @Test
    public void testToString() {

        SbiNePolicyRoute route5 = new SbiNePolicyRoute();

        route5.setTrafficPolicyName("trafficPolicyName");
        route5.setInterfaceName("interfaceName");
        route5.setDirection("direction");
        route5.setFilterAction("filterAction");

        route5.toString();
    }
    
    @Test
    public void testEquals6() {

        SbiNePolicyRoute route6 = new SbiNePolicyRoute();

        route6.setTrafficPolicyName("trafficPolicyNameTest");
        route6.setInterfaceName("interfaceName");
        route6.setDirection("direction");
        route6.setFilterAction("filterAction");

        assertFalse(route1.equals(route6));  
    }
    
    @Test
    public void testEquals7() {

        SbiNePolicyRoute route7 = new SbiNePolicyRoute();

        route7.setTrafficPolicyName("trafficPolicyName");
        route7.setInterfaceName("interfaceNameTest");
        route7.setDirection("direction");
        route7.setFilterAction("filterAction");

        assertFalse(route1.equals(route7));  
    }
    
    @Test
    public void testEquals8() {

        SbiNePolicyRoute route8 = new SbiNePolicyRoute();

        route8.setTrafficPolicyName("trafficPolicyName");
        route8.setInterfaceName("interfaceName");
        route8.setDirection("directionTest");
        route8.setFilterAction("filterAction");

        assertFalse(route1.equals(route8));  
    }
    
    @Test
    public void testEquals9() {

        SbiNePolicyRoute route9 = new SbiNePolicyRoute();

        route9.setTrafficPolicyName("trafficPolicyName");
        route9.setInterfaceName("interfaceName");
        route9.setDirection("direction");
        route9.setFilterAction("filterActionTest");

        assertFalse(route1.equals(route9));  
    }
    
    @Test
    public void testEqualsObject() {
        
        assertFalse(route1.equals(new Object()));  
    }

}
