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

package org.openo.sdnhub.overlayvpndriver.sbi.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiStaticRoute;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.translator.StaticRouteConvert;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * SBI Implementation of staticRoute services.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class StaticRouteImpl {

    private static final  Logger LOGGER = LoggerFactory.getLogger(StaticRouteImpl.class);

    private static final String DELETE_ROUTE_PARAMETER = "ids";

    private static final String DEST_IP_QUERY_PARAM = "?destIp=";

    private static final String LOG_STATIC_ROUTE_CONFIG_FAILED = "static route configuration has failed.";

    /**
     * Query static routes<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId Device UUID
     * @param destIp Destination IP
     * @return ResultRsp object with static route list data.
     * @throws ServiceException ServiceException In case of any query exception
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<ControllerNbiStaticRoute>> queryRouteByDevice(String ctrlUuid, String deviceId,
                                                                        String destIp, String staticRouteId)
            throws ServiceException {
        ResultRsp<List<ControllerNbiStaticRoute>> resultRsp =
                new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);

        String url = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_STATICROUTE, deviceId);

        boolean appendFlg = false;
        if(StringUtils.hasLength(destIp)) {
            StringBuilder builder = new StringBuilder();
            builder.append(url + DEST_IP_QUERY_PARAM + destIp);
            url = builder.toString();
            appendFlg = true;
        }

        if (StringUtils.hasLength(staticRouteId))
        {
            String prefix = appendFlg ? "&staticRouteId=" : "?staticRouteId=";

            StringBuilder strBuidler = new StringBuilder();
            strBuidler.append(url + prefix + staticRouteId);
            url = strBuidler.toString();
        }



        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(url, null, ctrlUuid);
        String body = httpMsg.getBody();

        if(httpMsg.isSuccess() && StringUtils.hasLength(body)) {
            OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>> response = JsonUtil.fromJson(body,
                    new TypeReference<OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>>>() {});

            if(response != null && response.isSucess()) {
                resultRsp.setData(response.getData());
                return resultRsp;
            }

            LOGGER.error("Query route By device:ac response return error");
            throw new ServiceException(ErrorCode.ADAPTER_ROUTER_RESPONSE_FAIL);
        }

        LOGGER.error(LOG_STATIC_ROUTE_CONFIG_FAILED);
        return new ResultRsp<>(ErrorCode.ADAPTER_ROUTER_RESPONSE_FAIL);

    }

    /**
     * Create or update static routes<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId Device UUID
     * @param list List of ControllerNbiStaticRoute objet to be created or updated.
     * @param createOrUpdate True if static route to be created else false if static route to be updated
     * @return ResultRsp object with static route list data.
     * @throws ServiceException ServiceException In case of any create or update exception
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<ControllerNbiStaticRoute>> configStaticRoute(String ctrlUuid, String deviceId,
            List<ControllerNbiStaticRoute> list, boolean createOrUpdate) throws ServiceException {

        ResultRsp<List<ControllerNbiStaticRoute>> resultRsp =
                new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);

        StaticRouteConvert.filterCreatedStaticRouteList(ctrlUuid, deviceId, list);

        List<ControllerNbiStaticRoute> existingRoutes = new ArrayList<>();
        if(createOrUpdate) {
            existingRoutes = filterAcExistStaticRouteList(ctrlUuid, deviceId, list);
        }

        if(CollectionUtils.isEmpty(list)) {
            resultRsp.setData(existingRoutes);
            return resultRsp;
        }

        Map<String, List<ControllerNbiStaticRoute>> reqMap = new HashMap<>();
        reqMap.put("staticRouteConfigs", list);
        String url = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_STATICROUTE, deviceId);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(url, JsonUtil.toJson(reqMap), ctrlUuid);

        String body = httpMsg.getBody();

        if(httpMsg.isSuccess() && StringUtils.hasLength(body)) {
            OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>> acresponse = JsonUtil.fromJson(body,
                    new TypeReference<OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>>>() {});
            if(!acresponse.isSucess()) {
                return new ResultRsp<>(ErrorCode.ADAPTER_ROUTER_RESPONSE_FAIL,
                        acresponse.getErrmsg(), null, null, null);
            }
            resultRsp.setData(acresponse.getData());
            filldata(resultRsp, existingRoutes);
            return resultRsp;
        }

        LOGGER.error(LOG_STATIC_ROUTE_CONFIG_FAILED);
        return new ResultRsp<>(ErrorCode.ADAPTER_ROUTER_RESPONSE_FAIL);

    }

    private void filldata(ResultRsp<List<ControllerNbiStaticRoute>> resultRsp,
            List<ControllerNbiStaticRoute> existingRoutes) {
        if(CollectionUtils.isNotEmpty(existingRoutes)) {
            List<ControllerNbiStaticRoute> rspData = resultRsp.getData();
            if(CollectionUtils.isEmpty(rspData)) {
                resultRsp.setData(new ArrayList<ControllerNbiStaticRoute>());
            }
            rspData.addAll(existingRoutes);
        }

    }

    private List<ControllerNbiStaticRoute> filterAcExistStaticRouteList(String ctrlUuid, String deviceId,
            List<ControllerNbiStaticRoute> sbiStaticRoutes) throws ServiceException {
        List<ControllerNbiStaticRoute> duplicateRoutes = new ArrayList<>();

        ResultRsp<List<ControllerNbiStaticRoute>> staticRouteListRsp = queryRouteByDevice(ctrlUuid, deviceId, null, null);

        if(CollectionUtils.isEmpty(staticRouteListRsp.getData())) {
            return duplicateRoutes;
        }

        List<ControllerNbiStaticRoute> existingAllRoutingList = staticRouteListRsp.getData();
        List<ControllerNbiStaticRoute> routesToIgnore = new ArrayList<>();

        for(int i = sbiStaticRoutes.size() - 1; i >= 0; i--) {
            ControllerNbiStaticRoute tempCreateStaticRoute = sbiStaticRoutes.get(i);
            boolean isCreated = false;

            ControllerNbiStaticRoute similarStaticRoute =
                    StaticRouteConvert.getSameTunnelFromAc(existingAllRoutingList, tempCreateStaticRoute);

            if(null != similarStaticRoute) {
                isCreated = true;
            }

            if(isCreated) {
                duplicateRoutes.add(similarStaticRoute);
                routesToIgnore.add(tempCreateStaticRoute);
            }
        }

        if(CollectionUtils.isNotEmpty(routesToIgnore)) {
            sbiStaticRoutes.removeAll(routesToIgnore);
        }
        return duplicateRoutes;
    }

    /**
     * Delete static routes<br/>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId Device UUID
     * @param idList
     * @return ResultRsp Object for deleted static routes.
     * @throws ServiceException ServiceException In case of any delete exception
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> deleteRouteByDevice(String ctrlUuid, String deviceId, List<String> idList)
            throws ServiceException {
        ResultRsp<String> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        Map<String, List<String>> reqMap = new HashMap<>();
        reqMap.put(DELETE_ROUTE_PARAMETER, idList);
        String url = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_STATICROUTE, deviceId);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(url, JsonUtil.toJson(reqMap), ctrlUuid);

        String body = httpMsg.getBody();

        if(httpMsg.isSuccess() && StringUtils.hasLength(body)) {
            ACDelResponse acdelReponse = JsonUtil.fromJson(body, new TypeReference<ACDelResponse>() {});
            if(!acdelReponse.isSucess()) {
                resultRsp.setErrorCode(ErrorCode.ADAPTER_ROUTER_RESPONSE_FAIL);
                resultRsp.setMessage(acdelReponse.getAllErrmsg());
            }
            return resultRsp;
        }

        LOGGER.error(LOG_STATIC_ROUTE_CONFIG_FAILED);
        resultRsp.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
        resultRsp.setMessage("delete Route by device failed.");
        return resultRsp;
    }
}
