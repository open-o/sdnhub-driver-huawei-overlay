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

import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.service.model.Action;

import java.util.LinkedList;
import java.util.List;

public class FilterActionListTest {

    @Test
    public void testEqual() {

        FilterActionList actionList = new FilterActionList();
        actionList.setUuid("uuid");
        Filter filter = new Filter();
        filter.setVlanId("123");
        actionList.setFilter(filter);
        Action action = new Action();
        action.setPolicy("policy");
        actionList.setAction(action);
        List<AclRule> ruleList = new LinkedList<>();
        AclRule rule = new AclRule();
        rule.setPolicy("rulepolicy");
        ruleList.add(rule);
        actionList.setRuleList(ruleList);

        FilterActionList actionList1 = new FilterActionList();
        actionList1.setUuid("uuid");
        Filter filter1 = new Filter();
        filter1.setVlanId("123");
        actionList1.setFilter(filter1);
        Action action1 = new Action();
        action1.setPolicy("policy");
        actionList1.setAction(action);
        List<AclRule> ruleList1 = new LinkedList<>();
        AclRule rule1 = new AclRule();
        rule1.setPolicy("rulepolicy");
        ruleList1.add(rule1);
        actionList1.setRuleList(ruleList1);

        FilterActionList actionList2 = new FilterActionList();
        actionList2.setUuid(null);
        Filter filter2 = new Filter();
        filter2.setVlanId(null);
        actionList2.setFilter(filter2);
        Action action2 = new Action();
        action2.setPolicy(null);
        actionList2.setAction(action2);
        List<AclRule> ruleList2 = new LinkedList<>();
        AclRule rule2 = new AclRule();
        rule2.setPolicy(null);
        ruleList2.add(rule2);
        actionList2.setRuleList(ruleList2);

        FilterActionList actionList3 = new FilterActionList();
        actionList3.setUuid("uuids");
        Filter filter3 = new Filter();
        filter3.setVlanId("123");
        actionList3.setFilter(filter3);
        Action action3 = new Action();
        action3.setPolicy("policy");
        actionList3.setAction(action3);
        List<AclRule> ruleList3 = new LinkedList<>();
        AclRule rule3 = new AclRule();
        rule3.setPolicy("rulepolicy");
        ruleList3.add(rule3);
        actionList3.setRuleList(ruleList3);

        FilterActionList actionList4 = new FilterActionList();
        actionList4.setUuid("uuid");
        Filter filter4 = new Filter();
        filter4.setVlanId("1234");
        actionList4.setFilter(filter4);
        Action action4 = new Action();
        action4.setPolicy("policy");
        actionList4.setAction(action4);
        List<AclRule> ruleList4 = new LinkedList<>();
        AclRule rule4 = new AclRule();
        rule4.setPolicy("rulepolicy");
        ruleList4.add(rule4);
        actionList4.setRuleList(ruleList4);

        FilterActionList actionList5 = new FilterActionList();
        actionList5.setUuid("uuid");
        Filter filter5 = new Filter();
        filter5.setVlanId("123");
        actionList5.setFilter(filter5);
        Action action5 = new Action();
        action5.setPolicy("policys");
        actionList5.setAction(action5);
        List<AclRule> ruleList5 = new LinkedList<>();
        AclRule rule5 = new AclRule();
        rule5.setPolicy("rulepolicy");
        ruleList5.add(rule5);
        actionList5.setRuleList(ruleList5);

        FilterActionList actionList6 = new FilterActionList();
        actionList6.setUuid("uuid");
        Filter filter6 = new Filter();
        filter6.setVlanId("123");
        actionList6.setFilter(filter6);
        Action action6 = new Action();
        action6.setPolicy("policy");
        actionList6.setAction(action6);
        List<AclRule> ruleList6 = new LinkedList<>();
        AclRule rule6 = new AclRule();
        rule6.setPolicy("rulepolicys");
        ruleList6.add(rule6);
        actionList6.setRuleList(ruleList6);

        actionList1.hashCode();
        actionList2.hashCode();

        actionList1.toString();
        actionList2.toString();

        assertFalse(actionList.equals(null));
        assertFalse(actionList.equals(new Object()));

        assertFalse(actionList.equals(actionList1));
        assertTrue(actionList.equals(actionList));
        assertFalse(actionList.equals(actionList3));
        assertFalse(actionList.equals(actionList4));
        assertFalse(actionList.equals(actionList5));
        assertFalse(actionList.equals(actionList6));
    }
}
