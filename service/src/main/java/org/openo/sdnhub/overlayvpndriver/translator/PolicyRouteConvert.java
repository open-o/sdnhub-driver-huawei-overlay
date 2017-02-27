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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.type.TypeReference;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.FilterAction;
import org.openo.sdnhub.overlayvpndriver.controller.model.FilterActionList;
import org.openo.sdnhub.overlayvpndriver.controller.model.Redirect;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficInterface;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficPolicyList;
import org.openo.sdnhub.overlayvpndriver.controller.model.AclPolicy;
import org.openo.sdnhub.overlayvpndriver.controller.model.AclRule;
import org.openo.sdnhub.overlayvpndriver.controller.model.Action;
import org.openo.sdnhub.overlayvpndriver.controller.model.Filter;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNePolicyRoute;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.util.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class PolicyRouteConvert {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyRouteConvert.class);

    private PolicyRouteConvert() {

    }

    /**
     * <br/>
     *
     * @param checkOkroutelist
     * @return
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static Map<String, List<TrafficPolicyList>> convert2Route(List<SbiNePolicyRoute> checkOkroutelist) throws ServiceException {

        Map<String, List<TrafficPolicyList>> deviceIdToMqcMap = new ConcurrentHashMap<>();
        for(SbiNePolicyRoute tempSbiNePolicyRoute : checkOkroutelist) {
            TrafficPolicyList trafficPolicy = new TrafficPolicyList();
            trafficPolicy.setTrafficpolicyName(tempSbiNePolicyRoute.getTrafficPolicyName());

            trafficPolicy.setId(tempSbiNePolicyRoute.getExternalId());
            trafficPolicy.setExtend(true);
            FilterActionList nbiFilterAction = new FilterActionList();
            try {
                nbiFilterAction = JsonUtil.fromJson(tempSbiNePolicyRoute.getFilterAction(),
                        new TypeReference<FilterActionList>() {});
            } catch(IllegalArgumentException e) {
                LOGGER.error("json type error.", e);
                throw new ServiceException("convert2Route: json type error.");
            }

            FilterAction filterAction = new FilterAction();
            Filter filter = new Filter();
            List<AclPolicy> aclPolicies = new ArrayList<>();
            Iterator<AclRule> iterator = nbiFilterAction.getRuleList().iterator();

            while(iterator.hasNext()) {
                AclRule rule = iterator.next();
                AclPolicy aclPolicy = new AclPolicy();
                aclPolicy.setPolicy(rule.getPolicy());
                if(null != rule.getSrcIp()) {
                    aclPolicy.setSrcIp(rule.getSrcIp().getIpv4());
                    aclPolicy.setSrcNetMask(IpUtils.prefixToMask(Integer.valueOf(rule.getSrcIp().getIpMask())));
                }
                if(null != rule.getDesIp()) {
                    aclPolicy.setDesIp(rule.getDesIp().getIpv4());
                    aclPolicy.setDesNetMask(IpUtils.prefixToMask(Integer.valueOf(rule.getDesIp().getIpMask())));
                }

                aclPolicies.add(aclPolicy);
            }

            filter.setAclPolicy(aclPolicies);

            Action action = new Action();
            Redirect rediret = new Redirect();

            org.openo.sdnhub.overlayvpndriver.service.model.Action tempAction = nbiFilterAction.getAction();

            if(tempAction!=null && null != tempAction.getNextHopIp()) {
                rediret.setNextHopIp(tempAction.getNextHopIp().getIpv4());
            }
            action.setRedirect(rediret);

            filterAction.setAction(action);
            filterAction.setFilter(filter);

            trafficPolicy.setFilterActionList(Arrays.asList(filterAction));

            TrafficInterface trafficInterface = new TrafficInterface();
            trafficInterface.setInterfaceName(tempSbiNePolicyRoute.getInterfaceName());
            trafficInterface.setDirection(tempSbiNePolicyRoute.getDirection());

            trafficPolicy.setInterfaceList(Arrays.asList(trafficInterface));

            if(!deviceIdToMqcMap.containsKey(tempSbiNePolicyRoute.getDeviceId())) {
                deviceIdToMqcMap.put(tempSbiNePolicyRoute.getDeviceId(), new ArrayList<TrafficPolicyList>());
            }
            deviceIdToMqcMap.get(tempSbiNePolicyRoute.getDeviceId()).add(trafficPolicy);
        }
        return deviceIdToMqcMap;
    }

    /**
     * <br/>
     *
     * @param list
     * @param checkOkroutelist
     * @param successedDatas
     * @since SDNHUB 0.5
     */
    public static void backWriteId2NePolicyRoute(List<TrafficPolicyList> list, List<SbiNePolicyRoute> checkOkroutelist,
            List<SbiNePolicyRoute> successedDatas) {
        if(CollectionUtils.isEmpty(list)) {
            return;
        }
        for(TrafficPolicyList tempRoute : list) {
            if(StringUtils.isEmpty(tempRoute.getId())) {
                LOGGER.warn("do not get static id, outInfer = " + tempRoute.getTrafficpolicyName());
                continue;
            }
            for(SbiNePolicyRoute tempNeRouter : checkOkroutelist) {
                if(tempRoute.getId().equals(tempNeRouter.getExternalId())) {
                    successedDatas.add(tempNeRouter);
                }
            }
        }
    }
}
