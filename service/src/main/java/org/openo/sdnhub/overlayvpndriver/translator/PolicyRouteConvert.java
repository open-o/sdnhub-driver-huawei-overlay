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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.TrafficPolicyList;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNePolicyRoute;
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
     * @param createOrUpdate
     * @return
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static Map<String, List<TrafficPolicyList>> convert2Route(List<SbiNePolicyRoute> checkOkroutelist,
            boolean createOrUpdate) throws ServiceException {

        Map<String, List<TrafficPolicyList>> deviceIdToMqcMap = new ConcurrentHashMap<>();
        for(SbiNePolicyRoute tempSbiNePolicyRoute : checkOkroutelist) {
            TrafficPolicyList trafficPolicy = new TrafficPolicyList();
            trafficPolicy.setTrafficpolicyName(tempSbiNePolicyRoute.getTrafficPolicyName());
            //TODO: need to set direction and interface-name
            if(createOrUpdate) {
                trafficPolicy.setUuid(tempSbiNePolicyRoute.getUuid());
            } else {
                trafficPolicy.setUuid(tempSbiNePolicyRoute.getUuid());
            }

            if(tempSbiNePolicyRoute.getFilterAction() != null) {
               //TODO:
            }

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
            if(StringUtils.isEmpty(tempRoute.getUuid())) {
                LOGGER.warn("do not get static id, outInfer = " + tempRoute.getTrafficpolicyName());
                continue;
            }
            for(SbiNePolicyRoute tempNeRouter : checkOkroutelist) {
                if(tempRoute.getUuid().equals(tempNeRouter.getUuid())
                        && tempRoute.getTrafficpolicyName().equals(tempNeRouter.getTrafficPolicyName())) {
                    tempNeRouter.setUuid(tempRoute.getUuid());
                    successedDatas.add(tempNeRouter);
                }
            }
        }
    }
}
