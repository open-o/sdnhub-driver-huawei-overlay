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

import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAclRule;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSnatNetModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for ACL conversion.<br/>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class AclConvert {

    // RFC 1918 IP
    private static final String DENY_PRIVATE_3 = "192.168.0.0/16";

    // RFC 1918 IP
    private static final String DENY_PRIVATE_2 = "172.16.0.0/12";

    // RFC 1918 IP
    private static final String DENY_PRIVATE_1 = "10.0.0.0/8";

    private static final String POLICY_PERMIT = "permit";

    private static final String POLICY_DENY = "deny";

    private static final Logger logger = LoggerFactory.getLogger(AclConvert.class);

    private AclConvert() {

    }

    /**
     * This method builds ACL from SNAT model.</br>
     *
     * @param snatNetModel SBI SNAT model
     * @return
     */
    public static AcAcl buildAcAcl(SbiSnatNetModel snatNetModel) {
        AcAcl acl = new AcAcl();
        acl.setAclId(snatNetModel.getAclId());
        acl.setAclNumber(Integer.valueOf(snatNetModel.getAclNumber()));
        acl.setAclName("aclName_" + snatNetModel.getAclNumber());

        List<AcAclRule> rules = new ArrayList<>();
        AcAclRule denyRule1 = new AcAclRule();
        denyRule1.setId(10);
        denyRule1.setPolicy(POLICY_DENY);
        denyRule1.setDesIp(DENY_PRIVATE_1);
        denyRule1.setDescription(snatNetModel.getDescription());
        rules.add(denyRule1);

        AcAclRule denyRule2 = new AcAclRule();
        denyRule2.setId(50);
        denyRule2.setPolicy(POLICY_DENY);
        denyRule2.setDesIp(DENY_PRIVATE_2);
        denyRule2.setDescription(snatNetModel.getDescription());
        rules.add(denyRule2);
        AcAclRule denyRule3 = new AcAclRule();
        denyRule3.setId(100);
        denyRule3.setPolicy(POLICY_DENY);
        denyRule3.setDesIp(DENY_PRIVATE_3);
        denyRule3.setDescription(snatNetModel.getDescription());
        rules.add(denyRule3);

        // WAN IP
        // snatNetModel ifName WAN
        String wanIp = snatNetModel.getStartPublicIpAddress();
        if(StringUtils.hasLength(wanIp)) {
            logger.warn("snat wan interface ip is null");
        } else {

            // deny WAN ip
            AcAclRule denyRule4 = new AcAclRule();
            denyRule4.setId(150);
            denyRule4.setPolicy(POLICY_DENY);
            denyRule4.setSrcIp(wanIp);
            denyRule4.setDescription(snatNetModel.getDescription());
            rules.add(denyRule4);
        }
        AcAclRule rule = new AcAclRule();
        rule.setId(200);
        rule.setPolicy(POLICY_PERMIT);
        if(isPrivateIpUsefull(snatNetModel)) {
        }

        rule.setDescription(snatNetModel.getDescription());
        rules.add(rule);

        acl.setRuleList(rules);
        return acl;
    }

    private static boolean isPrivateIpUsefull(SbiSnatNetModel snatNetModel) {
        return isEmpty(snatNetModel.getStartPublicIpAddress()) && isEmpty(snatNetModel.getPrivatePrefix());
    }

    private static boolean isEmpty(String str) {
        return null != str && !str.trim().isEmpty();
    }
}
