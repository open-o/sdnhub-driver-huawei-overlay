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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAclRule;

public class AclConvertTest {

	
	/*@Test
	public void testBuildAcAcl() {
		
		
		
		SNatNetModel snatNetModel;
		AclConvert.buildAcAcl(null);
	}
*/
	@Test
	public void testBuildUpdateAcAcl() {
	
	
		AcAcl acl = new AcAcl();
		
		
		List<AcAclRule> newRules = new ArrayList<AcAclRule>();
		AcAclRule rule=new AcAclRule();
		acl.setAclId("aclId");
		acl.setAclName("aclName");
		acl.setAclNumber(101);
		acl.setAclStep(102);
		acl.setId("id");
		acl.setNetId("netId");
		acl.setRuleList(newRules);
		
		newRules.add(rule);
		
		rule.setDescription("description");
		rule.setDesIp("desIp");
		rule.setDesPort(201);
		rule.setDesVlan(202);
		rule.setIcmpName("icmpName");
		rule.setId(150);
		rule.setPolicy("policy");
		rule.setProtocol(203);
		rule.setSrcIp("srcIp"); 
		rule.setSrcPort(204);
		rule.setTcpSyn("tcpSyn");
		//AclConvert.buildUpdateAcAcl(acl , "startPublicIpAddress");
	}

	@Test
	public void testBuildUpdateAcAcl1() {
	
	
		AcAcl acl = new AcAcl();
		
		
		List<AcAclRule> newRules = new ArrayList<AcAclRule>();
		AcAclRule rule=new AcAclRule();
		acl.setAclId("aclId");
		acl.setAclName("aclName");
		acl.setAclNumber(101);
		acl.setAclStep(102);
		acl.setId("id");
		acl.setNetId("netId");
		acl.setRuleList(newRules);
		
		newRules.add(rule);
		
		rule.setDescription("description");
		rule.setDesIp("desIp");
		rule.setDesPort(201);
		rule.setDesVlan(202);
		rule.setIcmpName("icmpName");
		rule.setId(200);
		rule.setPolicy("policy");
		rule.setProtocol(203);
		rule.setSrcIp("srcIp"); 
		rule.setSrcPort(204);
		rule.setTcpSyn("tcpSyn");
		//AclConvert.buildUpdateAcAcl(acl , "startPublicIpAddress");
	}

	
}
