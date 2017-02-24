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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.openo.sdnhub.overlayvpndriver.common.util.CheckIpV6Util;
import org.openo.sdnhub.overlayvpndriver.common.util.IpAddressUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiStaticRoute;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNeStaticRoute;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Converter class for StaticRoute.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class StaticRouteConvert {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticRouteConvert.class);

    /**
     * Utility method to filter created unique static routes. <br/>
     *
     * @param ctrlUuid Controller uuid
     * @param deviceId Device id
     * @param list List of static route
     * @since SDNHUB 0.5
     */
    public static void filterCreatedStaticRouteList(String ctrlUuid, String deviceId,
            List<ControllerNbiStaticRoute> list) {

        Map<String, List<ControllerNbiStaticRoute>> sameInfo2RouteMap =
                new HashMap<String, List<ControllerNbiStaticRoute>>();
        Iterator<ControllerNbiStaticRoute> iterator = list.iterator();

        while(iterator.hasNext()) {
            ControllerNbiStaticRoute route = iterator.next();
            if(CheckIpV6Util.isValidIpV6(route.getIpv6Address())) {
                continue;
            }

            ControllerNbiStaticRoute sameRoute = getSameTunnelFromAc(list, route);
            if(null != sameRoute) {
                String key = sameRoute.getOutInterface();
                if(CollectionUtils.isEmpty(sameInfo2RouteMap.get(key))) {
                    sameInfo2RouteMap.put(key, new ArrayList<ControllerNbiStaticRoute>());
                }

                sameInfo2RouteMap.get(key).add(sameRoute);
            }
        }

        if(MapUtils.isEmpty(sameInfo2RouteMap)) {
            return;
        }

        for(Map.Entry<String, List<ControllerNbiStaticRoute>> entry : sameInfo2RouteMap.entrySet()) {
            ControllerNbiStaticRoute temp = entry.getValue().get(0);
            List<ControllerNbiStaticRoute> ignoreList = new ArrayList<ControllerNbiStaticRoute>();

            for(ControllerNbiStaticRoute route : list) {
                String key = IpAddressUtil.calcSubnet(route.getIp(), IpUtils.maskToPrefix(route.getMask()))
                        + route.getMask();
                if(entry.getKey().equals(key)) {
                    ignoreList.add(route);
                }
            }
            list.removeAll(ignoreList);
            list.add(temp);
        }
    }

    /**
     * Get same tunnel from Ac matching with all given fields.
     *
     * @param existingAllRoutingList list of ControllerNbiStaticRoute
     * @param tempCreateStaticRoute ControllerNbiStaticRoute
     * @return ControllerNbiStaticRoute
     */
    public static ControllerNbiStaticRoute getSameTunnelFromAc(List<ControllerNbiStaticRoute> existingAllRoutingList,
                                                               ControllerNbiStaticRoute tempCreateStaticRoute) {

        for(ControllerNbiStaticRoute existing : existingAllRoutingList) {
            if(existing.getId().equals(tempCreateStaticRoute.getId())) {
                continue;
            }

            if(StringUtils.hasLength(tempCreateStaticRoute.getNextHop())
                    && tempCreateStaticRoute.getNextHop().equals(existing.getNextHop())) {
                continue;
            }

            if(StringUtils.hasLength(tempCreateStaticRoute.getOutInterface())
                    && tempCreateStaticRoute.getOutInterface().equals(existing.getOutInterface())) {
                continue;
            }

            if(StringUtils.hasLength(tempCreateStaticRoute.getIpv6Address())) {

                String ipv6 = existing.getIpv6Address();
                if(CheckIpV6Util.isValidIpV6(ipv6) && (ipv6.equals(tempCreateStaticRoute.getIpv6Address()))) {
                    continue;
                }
            }

            if(StringUtils.hasLength(tempCreateStaticRoute.getIp())) {
                if(!isRouteDestIPExisted(tempCreateStaticRoute.getIp(), tempCreateStaticRoute.getMask(),
                        existing.getIp(), existing.getMask())) {
                    continue;
                }
            }

            return existing;
        }

        return null;
    }

    private static boolean isRouteDestIPExisted(String destIpForCreate, String destMaskForCreate, String destIpInAc,
            String destMaskInACc) {
        int destIpMaskForCreate = -1;
        if(StringUtils.hasLength(destMaskForCreate)) {
            destIpMaskForCreate = Integer.valueOf(destMaskForCreate);
        }
        int destIpMaskInDb = IpUtils.maskToPrefix(destMaskInACc);

        if(destIpMaskForCreate != destIpMaskInDb) {
            return false;
        }

        String ipForCreate = IpAddressUtil.calcSubnet(destIpForCreate, destIpMaskForCreate);
        String ipInAc = IpAddressUtil.calcSubnet(destIpInAc, destIpMaskInDb);
        if(null == ipForCreate || null == ipInAc || !ipForCreate.equals(ipInAc)) {
            return false;
        }

        return true;
    }

    /**
     * convert the SbiNeStaticRoute to ControllerNbiStaticRoute.
     *
     * @param neStaticRoutes list of SbiNeStaticRoute
     * @param createOrUpdate whether this operation is create of update
     * @return Map of converted ControllerNbiStaticRoute list.
     */
    public static Map<String, List<ControllerNbiStaticRoute>> convert2Route(List<SbiNeStaticRoute> neStaticRoutes,
                                                                            boolean createOrUpdate) {

        Map<String, List<ControllerNbiStaticRoute>> neId2Tunnels =
                new ConcurrentHashMap<String, List<ControllerNbiStaticRoute>>();

        for(SbiNeStaticRoute neStaticRoute : neStaticRoutes) {
            String destIp = neStaticRoute.getDestIp();
            String nextHopData = neStaticRoute.getNextHop();

            ControllerNbiStaticRoute staticRoute = new ControllerNbiStaticRoute();

            staticRoute.setIp(destIp);
            /* staticRoute.setIpMask(destIp.getIpMask()); */
            staticRoute.setOutInterface(neStaticRoute.getOutInterface());
            staticRoute.setDhcp(Boolean.valueOf(neStaticRoute.getEnableDhcp()));
            staticRoute.setPriority(neStaticRoute.getPriority());
            staticRoute.setId(neStaticRoute.getUuid());
            // staticRoute.setIpv6Address(neStaticRoute.get)
            // staticRoute.setBfdName(neStaticRoute.get);

            if(null != nextHopData) {
                staticRoute.setNextHop(nextHopData);
            }

            if(!createOrUpdate) {
                staticRoute.setId(neStaticRoute.getExternalId());
            }

            if(StringUtils.hasLength(neStaticRoute.getPriority())) {
                staticRoute.setPriority(neStaticRoute.getPriority());
            }

            staticRoute.setNqaId(neStaticRoute.getNqa());
            staticRoute.setOutInterface(neStaticRoute.getOutInterface());
            // staticRoute.setVpnId(neStaticRoute.set);
            // staticRoute.setVpnName(neStaticRoute.get);
            groupByNe(neId2Tunnels, neStaticRoute, staticRoute);
        }
        return neId2Tunnels;
    }

    private static void groupByNe(Map<String, List<ControllerNbiStaticRoute>> neId2Tunnels,
            SbiNeStaticRoute neStaticRoute, ControllerNbiStaticRoute staticRoute) {
        String neId = neStaticRoute.getDeviceId(); // neid to device id
        if(!neId2Tunnels.containsKey(neId)) {
            neId2Tunnels.put(neId, new ArrayList<ControllerNbiStaticRoute>());
        }
        neId2Tunnels.get(neId).add(staticRoute);
    }

    public static void backWriteId2NeStaticRoute(List<ControllerNbiStaticRoute> dataList, List<SbiNeStaticRoute> neRouteList, String deviceId,
                                                 List<SbiNeStaticRoute> successedDatas)
    {
        if(CollectionUtils.isEmpty(dataList))
        {
            return;
        }

        for(ControllerNbiStaticRoute tempRoute : dataList)
        {
            if(!StringUtils.hasLength(tempRoute.getId()))
            {
                LOGGER.warn("do not get static router id, outInfer = " + tempRoute.getOutInterface());
                continue;
            }

            for(SbiNeStaticRoute tempNeRouter : neRouteList)
            {
                if(tempRoute.getId().equals(tempNeRouter.getExternalId()))
                {
                    successedDatas.add(tempNeRouter);
                }
            }
        }
    }

    public static void fillFailDataInfo(List<FailData<SbiNeStaticRoute>> failedNbiDatas, List<SbiNeStaticRoute> totalNbiRoutes,
                                  ResultRsp<List<ControllerNbiStaticRoute>> resultRsp)
    {
        for(ControllerNbiStaticRoute staticRoute : resultRsp.getData())
        {
            SbiNeStaticRoute nbiRoute = findCorrespondNbiModel(staticRoute, totalNbiRoutes);
            if(null != nbiRoute)
            {
                FailData<SbiNeStaticRoute> failData = new FailData<SbiNeStaticRoute>(resultRsp.getErrorCode(),resultRsp.getMessage(),nbiRoute);
                failedNbiDatas.add(failData);
            }
        }
    }

    private static SbiNeStaticRoute findCorrespondNbiModel(ControllerNbiStaticRoute sbiStaticRoute, List<SbiNeStaticRoute> nbiRoutes)
    {
        for(SbiNeStaticRoute nbiRoute : nbiRoutes)
        {
            if(nbiRoute.getUuid().equals(sbiStaticRoute.getId()))
            {
                return nbiRoute;
            }
        }

        return null;
    }
}
