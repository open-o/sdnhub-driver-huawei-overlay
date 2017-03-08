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
import java.util.Arrays;
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
import org.openo.sdnhub.overlayvpndriver.service.model.Ip;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeStaticRoute;
import org.openo.sdnhub.overlayvpndriver.translator.StaticRouteConvert;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
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
            OverlayVpnDriverResponse<ControllerNbiStaticRoute> response = JsonUtil.fromJson(body,
                    new TypeReference<OverlayVpnDriverResponse<ControllerNbiStaticRoute>>() {});

            if(response != null && response.isSucess()) {
                if (null != response.getData()) {
                    resultRsp.setData(Arrays.asList(response.getData()));
                }
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

    /**
     * check Ip data and fill it if required.
     * @param neStaticRoutes List of static routes
     * @param failDatas List of fail data.
     * @param checkOkRouteList list of valid static routes.
     * @throws ServiceException If invalid data found in model.
     */
    public void checkInputData(List<SbiNeStaticRoute> neStaticRoutes, List<FailData<SbiNeStaticRoute>> failDatas,
            List<SbiNeStaticRoute> checkOkRouteList) throws ServiceException {
        for(SbiNeStaticRoute route : neStaticRoutes){
            try {
                checkInputStaticType(route);
                checkOkRouteList.add(route);
            } catch (ServiceException e){
                LOGGER.error("check failed.",e);
                FailData<SbiNeStaticRoute> tempFailData = new FailData<>();
                tempFailData.setData(route);
                failDatas.add(tempFailData);

                tempFailData.setErrcode(String.valueOf(e.getHttpCode()));
                tempFailData.setErrmsg(e.getMessage());
            }
        }

    }

    private void checkInputStaticType(SbiNeStaticRoute route) throws ServiceException {
        try {

            if(StringUtils.hasLength(route.getDestIp())){
                route.setDestIpData(JsonUtil.fromJson(route.getDestIp(),Ip.class));
            }

            if(StringUtils.hasLength(route.getNextHop())){
                route.setNextHopData(JsonUtil.fromJson(route.getNextHop(),Ip.class));
            }
        } catch (IllegalArgumentException e){
            throw new ParameterServiceException("null destIp.");
        }

        checkIpFormat(route.getDestIpData());

        if(null != route.getNextHopData()) {
            checkIpFormat(route.getNextHopData());
        }

        checkDestIpAndNextHop(route.getDestIpData(), route.getNextHopData());

        if(null == route.getNextHopData() && !StringUtils.hasLength(route.getOutInterface())){
            throw new ParameterServiceException("Both NextHop and OutInterfaceName are null for static type");
        }

    }

    private void checkDestIpAndNextHop(Ip destIp, Ip nextHop) throws ServiceException {

        if(null == nextHop){
            return;
        }

        if(destIp.isTypeV4() && nextHop.isTypeV4()){
            return;
        }

        if(!destIp.isTypeV4() && !nextHop.isTypeV4()){
            return;
        }
        throw new ParameterServiceException("ip version not afford for destIp and nextHop");
    }

    private void checkIpFormat(Ip destIp) throws ServiceException {
        ValidationUtil.validateModel(destIp);

        String ipv4 = destIp.getIpv4();
        String ipv6 = destIp.getIpv6();
        String ipv4Mask = destIp.getIpMask();
        String prefixLength = destIp.getPrefixLength();

        if(StringUtils.hasLength(ipv4) && !StringUtils.hasLength(ipv4Mask)){
            throw new ParameterServiceException("ipv4 format need mask");
        }

        if(!StringUtils.hasLength(ipv4) && StringUtils.hasLength(ipv4Mask)){
            throw new ParameterServiceException("ipv4 format need ipv4");
        }

        if(!StringUtils.hasLength(ipv6) && StringUtils.hasLength(prefixLength)){
            throw new ParameterServiceException("ipv4 format need ipv6");
        }

        if(StringUtils.hasLength(ipv6) && !StringUtils.hasLength(prefixLength)){
            throw new ParameterServiceException("ipv6 format need prefix");
        }

        StringBuilder ipv4StringBUilder = new StringBuilder();
        if(StringUtils.hasLength(ipv4) || StringUtils.hasLength(ipv4Mask)){
            ipv4StringBUilder.append(destIp.getIpv4());
            ipv4StringBUilder.append(destIp.getIpMask());
        }

        check(destIp, ipv6, prefixLength, ipv4StringBUilder);
    }

    private void check(Ip destIp, String ipv6, String prefixLength,
            StringBuilder ipv4StringBUilder) throws ServiceException{
        StringBuilder  ipv6StringBUilder = new StringBuilder();
        if(StringUtils.hasLength(ipv6) || StringUtils.hasLength(prefixLength)){
            ipv6StringBUilder.append(destIp.getIpv6());
            ipv6StringBUilder.append(destIp.getPrefixLength());
        }
        if( ! StringUtils.hasLength(ipv4StringBUilder.toString()) &&
                !StringUtils.hasLength(ipv6StringBUilder.toString())){
            throw new ParameterServiceException("no ipv4 or ipv6");
        }
        if(StringUtils.hasLength(ipv4StringBUilder.toString()) &&
                StringUtils.hasLength(ipv6StringBUilder.toString())){
            throw new ParameterServiceException("both ipv4 and ipv6 are not null");
        }

        if(StringUtils.hasLength(ipv4StringBUilder.toString()) && (!StringUtils.hasLength(destIp.getIpv6()) ||
                !StringUtils.hasLength(destIp.getPrefixLength()))){
            throw new ParameterServiceException("parameter error for ipv6");
        }

        if(StringUtils.hasLength(ipv4StringBUilder.toString())){
            destIp.setTypeV4(true);
            return;
        }
        destIp.setTypeV4(false);
    }
}
