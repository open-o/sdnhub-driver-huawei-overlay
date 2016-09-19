/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.overlayvpndriver.service.wan;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.enums.WanInterfaceUsedType;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpndriver.login.OverlayVpnDriverProxy;
import org.openo.sdno.overlayvpndriver.model.port.NetAcDevicePort;
import org.openo.sdno.overlayvpndriver.model.wan.NetWanSubInterfaceIp;
import org.openo.sdno.overlayvpndriver.util.config.WanInterface;
import org.openo.sdno.overlayvpndriver.util.consts.ControllerUrlConst;
import org.openo.sdno.overlayvpndriver.util.controller.ControllerUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * WanInterface service implementation. <br>
 * 
 * @author
 * @version SDNO 0.5 Jun 16, 2016
 */
@Service
public class WanInfSvcImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(WanInfSvcImpl.class);

    private static final String MODE_DHCP = "dhcp";

    private static final int QUERY_TIME = 10;

    /**
     * Private constructor added to fix sonar issue
     */
    private WanInfSvcImpl() {
    }

    /**
     * Query WanInterface information. <br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId The device id
     * @param type The WanInterface type that want to get
     * @return ResultRsp list with WanSubInterface
     * @throws ServiceException When query failed
     * @since SDNO 0.5
     */
    public static List<WanSubInterface> queryWanInterface(String ctrlUuid, String deviceId, String type)
            throws ServiceException {

        // check parameters
        if(StringUtils.isEmpty(ctrlUuid)) {
            LOGGER.error("queryWanInterface: parameter error");
            SvcExcptUtil.throwBadRequestException("queryWanInterface, param ctrlUuid err");
        }

        if(StringUtils.isEmpty(deviceId)) {
            LOGGER.error("queryWanInterface: parameter error");
            SvcExcptUtil.throwBadRequestException("queryWanInterface, param deviceId err");
        }

        // get from controller firstly
        ResultRsp<List<WanSubInterface>> queryRsp = queryWanSubIf(ctrlUuid, deviceId);
        if(CollectionUtils.isEmpty(queryRsp.getData())) {
            LOGGER.error("queryWanInterface from AC error");
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, "queryWanInterface from AC error",
                    null, null, null);
        }

        // return directly if type is ALL
        if(WanInterfaceUsedType.ALL.getName().equals(type)) {
            return queryRsp.getData();
        }

        // get configuration by type
        String vlanId = WanInterface.getConfig(type);

        LOGGER.info("queryWanInterface type: " + type + ", usedVlan: " + vlanId);

        if(StringUtils.isEmpty(vlanId)) {
            String errInfo = "queryWanInterface can't get type[" + type + "] from configuration";
            LOGGER.error(errInfo);
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, errInfo, null, null, null);
        }

        List<WanSubInterface> refreshedList =
                JsonUtil.fromJson(JsonUtil.toJson(queryRsp.getData()), new TypeReference<List<WanSubInterface>>() {});

        WanSubInterface wanSubInterface = null;
        for(WanSubInterface tempWanSubInterface : refreshedList) {
            if(vlanId.equals(String.valueOf(tempWanSubInterface.getCeLowVlan()))) {
                wanSubInterface = tempWanSubInterface;
                break;
            }
        }

        if(null == wanSubInterface) {
            String errInfo = "queryWanInterface can't match the WanInterface type: " + type;
            LOGGER.error(errInfo);
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, errInfo, null, null, null);
        } else {
            // Added, to fix Sonar issue, NullPointerException might be thrown as 'wanSubInterface'
            // is nullable here

            // enable the WanSubInterface
            if(StringUtils.isEmpty(wanSubInterface.getIpAddress())) {
                ResultRsp<WanSubInterface> resultRsp = getWanSubInterfaceByEnableDhcp(ctrlUuid, deviceId, type);
                if(!resultRsp.isSuccess() || StringUtils.isEmpty(resultRsp.getData().getIpAddress())) {
                    String errInfo = "queryWanInterface enable failed";
                    LOGGER.error(errInfo);
                    SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, errInfo, null, null,
                            null);
                }

                wanSubInterface = resultRsp.getData();
            }
        }

        List<WanSubInterface> wanSubInterfaceList = new ArrayList<WanSubInterface>();
        wanSubInterfaceList.add(wanSubInterface);

        return wanSubInterfaceList;
    }

    private static ResultRsp<List<WanSubInterface>> queryWanSubIf(String ctrlUuid, String deviceId)
            throws ServiceException {

        long beginTime = System.currentTimeMillis();

        String url = MessageFormat.format(ControllerUrlConst.QUERY_WAN_SUBINTERFACE, deviceId);
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(url, null, ctrlUuid);
        List<WanSubInterface> data = new ControllerUtil<WanSubInterface>().checkRsp(httpMsg);

        LOGGER.info("queryWanSubIf cost time = " + (System.currentTimeMillis() - beginTime));

        return new ResultRsp<List<WanSubInterface>>(ErrorCode.OVERLAYVPN_SUCCESS, data);
    }

    private static ResultRsp<WanSubInterface> getWanSubInterfaceByEnableDhcp(String ctrlUuid, String deviceId,
            String type) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        // query port from AC
        NetAcDevicePort netAcDevicePort = queryPorts(Arrays.asList(type), deviceId, ctrlUuid).get(0);
        if((null != netAcDevicePort) && (StringUtils.isNotEmpty(netAcDevicePort.getIpv6Addr())
                || StringUtils.isNotEmpty(netAcDevicePort.getIpAddr()))) {
            return convertPortToWanSub(netAcDevicePort);
        }

        // enable dhcp
        enableDhcp(ctrlUuid, deviceId, type);

        // wait for 500ms
        sleep(CommConst.GET_WAN_IP_WAIT_TIME);

        boolean bExistedIp = false;
        NetAcDevicePort queryPort = null;

        for(int i = 0; i < QUERY_TIME; i++) {
            List<NetAcDevicePort> portList = queryPorts(Arrays.asList(type), deviceId, ctrlUuid);
            if(CollectionUtils.isNotEmpty(portList) && StringUtils.isNotEmpty(portList.get(0).getIpAddr())) {
                bExistedIp = true;
                break;
            }

            sleep(CommConst.GET_WAN_IP_WAIT_TIME);
        }

        if(!bExistedIp) {
            String errMsg = "WanInterfaceDhcp failed";
            LOGGER.info(errMsg);
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, errMsg, null, null, null);
        }

        LOGGER.info("getWanSubInterfaceByEnableDhcp cost time = " + (System.currentTimeMillis() - beginTime));

        return convertPortToWanSub(queryPort);
    }

    private static List<NetAcDevicePort> queryPorts(List<String> interfaceNameList, String deviceId, String ctrlUuid)
            throws ServiceException {

        long beginTime = System.currentTimeMillis();

        String url = MessageFormat.format(ControllerUrlConst.QUERY_DEVICE_PORT, deviceId);

        if(CollectionUtils.isNotEmpty(interfaceNameList)) {
            StringBuilder queryNameUrl = new StringBuilder(url);
            queryNameUrl.append('?');
            int num = interfaceNameList.size();

            for(int i = 0; i < num; i++) {
                queryNameUrl.append("interfaceNameList=" + interfaceNameList.get(i) + "&");
            }

            url = queryNameUrl.toString();
            url = url.substring(0, url.length() - 1);
        }

        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(url, null, ctrlUuid);
        List<NetAcDevicePort> data = new ControllerUtil<NetAcDevicePort>().checkRsp(httpMsg);

        LOGGER.info("queryPorts cost time = " + (System.currentTimeMillis() - beginTime));

        return data;
    }

    private static ResultRsp<WanSubInterface> convertPortToWanSub(NetAcDevicePort netAcDevicePort)
            throws ServiceException {

        WanSubInterface wanSubInterface = new WanSubInterface();
        wanSubInterface.setName(netAcDevicePort.getName());
        wanSubInterface.setIpAddress(netAcDevicePort.getIpAddr());
        wanSubInterface.setIpv6address(netAcDevicePort.getIpv6Addr());
        wanSubInterface.setMac(netAcDevicePort.getMac());
        wanSubInterface.setMask(netAcDevicePort.getMask());
        wanSubInterface.setCeLowVlan(Integer.valueOf(netAcDevicePort.getCeLowVlan()));
        wanSubInterface.setCeHighVlan(Integer.valueOf(netAcDevicePort.getCeHighVlan()));

        return new ResultRsp<WanSubInterface>(ErrorCode.OVERLAYVPN_SUCCESS, wanSubInterface);
    }

    private static void enableDhcp(String ctrlUuid, String deviceId, String type) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        String url = MessageFormat.format(ControllerUrlConst.ENABLE_WAN_SUBINTERFACE, deviceId);

        NetWanSubInterfaceIp netWanSubInterfaceIp = new NetWanSubInterfaceIp();

        netWanSubInterfaceIp.setInterfaceName(type);
        netWanSubInterfaceIp.setMode(MODE_DHCP);

        List<NetWanSubInterfaceIp> reqList = Arrays.asList(netWanSubInterfaceIp);
        Map<String, List<NetWanSubInterfaceIp>> updateIpMap = new HashMap<String, List<NetWanSubInterfaceIp>>();
        updateIpMap.put("interfaceIpConfigList", reqList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(url, JsonUtil.toJson(updateIpMap), ctrlUuid);
        new ControllerUtil<NetAcDevicePort>().checkRsp(httpMsg);

        LOGGER.info("enableDhcp cost time = " + (System.currentTimeMillis() - beginTime));
    }

    private static void sleep(long time) throws ServiceException {
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
            LOGGER.error("sleep exception =", e);
            SvcExcptUtil.throwInnerErrSvcExptionWithInfo(ErrorCode.OVERLAYVPN_FAILED, "sleep exception", null, null,
                    null);
        }
    }
}
