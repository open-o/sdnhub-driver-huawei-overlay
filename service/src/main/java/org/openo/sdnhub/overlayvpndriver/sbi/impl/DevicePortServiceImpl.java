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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcDevicePort;
import org.openo.sdnhub.overlayvpndriver.controller.model.LoopBackPort;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIp;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.openo.sdno.util.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * Port service implementation.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jan 16, 2017
 */
public class DevicePortServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevicePortServiceImpl.class);

    private DevicePortServiceImpl(){
    }


    /**
     * Query interface information of the current device using a specific controller.<br>
     *
     * @param deviceId device id
     * @param ctrlUuid controller UUID
     * @return ResultRsp object with IP configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static List<AcDevicePort> queryPorts(String deviceId, String ctrlUuid,
                                              List<String> portNameList) throws ServiceException {

        String queryUrl = MessageFormat.format(ControllerUrlConst.QUERY_DEVICE_PORT_URL, deviceId);

        if (!portNameList.isEmpty()) {
            StringBuilder queryNameUrl = new StringBuilder(queryUrl);
            queryNameUrl.append("?");
            String suffix = portNameList.stream().map(intf -> "interfaceNameList=" + intf)
                    .collect(Collectors.joining("&"));

            queryNameUrl.append(suffix);
            queryUrl = queryNameUrl.toString();
        }

        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);
        String body = httpMsg.getBody();

        LOGGER.debug("Query ports return body : " + body);
        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(body)) {
            OverlayVpnDriverResponse<List<AcDevicePort>> acresponse =
                    JsonUtil.fromJson(body, new TypeReference<OverlayVpnDriverResponse<List<AcDevicePort>>>(){});
            if(acresponse.isSucess()) {
                return acresponse.getData();
            }
            LOGGER.error("Query ports: controller response return error" + acresponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_GRE_QUERY_WAN_INTERFACE_ERROR, acresponse.getErrmsg());
        }
        LOGGER.error("Query ports: httpMsg return error");
        throw new ServiceException(DriverErrorCode.ADAPTER_GRE_QUERY_WAN_INTERFACE_ERROR,
                "Query ports: httpMsg return error");
    }

    private static ResultRsp<List<LoopBackPort>> queryLoopBackFromController(
            String ctrlUuid, String deviceId, String portName) throws ServiceException {

        ResultRsp<List<LoopBackPort>> resultRsp = new ResultRsp<List<LoopBackPort>>(DriverErrorCode.CLOUDVPN_SUCCESS);
        String queryUrl = MessageFormat.format(ControllerUrlConst.QUERY_LOOP_BACK_URL, deviceId);

        if(StringUtils.isNotEmpty(portName)) {
            queryUrl += "?loopbackName=" + portName;
        }

        long beginTime = System.currentTimeMillis();
        LOGGER.info("query loopback begin time = " + beginTime + ", deviceId = " + deviceId);
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);
        LOGGER.info("query loopback end time = " + (System.currentTimeMillis() - beginTime));

        String body = httpMsg.getBody();
        LOGGER.info("query loopback deviceId = " + deviceId + ", return body: " + body);

        if (httpMsg.isSuccess() && StringUtils.isNotEmpty(body))
        {
            ACResponse<List<LoopBackPort>> acresponse = JsonUtil.fromJson(body,
                    new TypeReference<ACResponse<List<LoopBackPort>>>() {});

            if (acresponse.isSucceed())
            {
                LOGGER.info("acresponse success");
                List<LoopBackPort> loopbackPortList = acresponse.getData();
                if(StringUtils.isEmpty(portName)) {
                    resultRsp.setData(loopbackPortList);
                    return resultRsp;
                }

                if(!CollectionUtils.isEmpty(loopbackPortList)) {
                    for(LoopBackPort tempLoopBackPort : loopbackPortList) {
                       if(portName.equals(tempLoopBackPort.getLoopbackName())) {
                           resultRsp.setData(Arrays.asList(tempLoopBackPort));
                           return resultRsp;
                       }
                    }
                }
                return resultRsp;
            }
            LOGGER.error("query loopback interface: acresponse return error, errMsg: " + acresponse.getErrmsg());
            throw new ServiceException(ControllerUrlConst.QUERY_LOOP_BACK_ERROR, acresponse.getErrmsg());
        }
        LOGGER.error("query loopback interface:: httpMsg return error.");
        throw new ServiceException(ControllerUrlConst.QUERY_LOOP_BACK_ERROR,"query loopback interface: httpMsg return error. deviceId: " + deviceId + "error body: " + httpMsg.getBody());
    }

    /**
     * Query loopback ports.
     * <br/>
     *
     * @param ctrlUuid Controller Id
     * @param deviceId Device Id
     * @param portname Port Name
     * @return ResultRsp for loopback query
     * @throws ServiceException In case of operation fails
     * @since  SDNHUB 0.5
     */
    public static ResultRsp<SbiIp> queryLoopBack(String  ctrlUuid,
            String deviceId, String portname)
            throws ServiceException {
        ResultRsp<SbiIp> ipRsp = new ResultRsp<>();
        for (int queryTime = 0; queryTime < CommonConst.QUERY_TIME; queryTime ++)
        {
            ResultRsp<List<LoopBackPort>> queryRsp = queryLoopBackFromController(ctrlUuid, deviceId, portname);
            if (!CollectionUtils.isEmpty(queryRsp.getData()))
            {
                SbiIp tempIp = new SbiIp(queryRsp.getData().get(0));
                if (StringUtils.isNotEmpty(tempIp.getIpMask()))
                {
                    tempIp.setIpMask(String.valueOf(IpUtils.maskToPrefix(tempIp.getIpMask())));
                }
                if(!StringUtils.isNotEmpty(tempIp.getIpv4()) && !StringUtils.isNotEmpty(tempIp.getIpv6()))
                {
                    LOGGER.warn("queryTime:" + queryTime + ", loopback name:{} result has no ipAddress.", portname);
                    sleepSometime(CommonConst.GET_WAN_IP_WAIT_TIME);
                    continue;
                }

                ipRsp.setData(tempIp);
                return ipRsp;
            }

            LOGGER.warn("queryTime:" + queryTime + ", loopback name:{} result has no data.", portname);
            sleepSometime(CommonConst.GET_WAN_IP_WAIT_TIME);
        }
        throw new ServiceException(DriverErrorCode.CLOUDVPN_FAILED, "[LoopBack]" + portname + "has no ip info.");
    }

    /**
     * Translate controller response to NBI model.
     * @param acDevicePort Controller model to represent port
     * @param portName Port name
     * @param deviceId Device ID
     * @param ctrlId Controller ID
     * @return ResultRsp for the port query
     * @throws ServiceException In case of operation fails
     */
    public static ResultRsp<SbiIp> traslateDevicePortToIp(AcDevicePort acDevicePort, String portName,
                                                           String deviceId, String ctrlId) throws ServiceException {

        ResultRsp<SbiIp> ipRsp = new ResultRsp<SbiIp>(DriverErrorCode.CLOUDVPN_SUCCESS);

        if (acDevicePort != null) {

            if(StringUtils.isEmpty(acDevicePort.getIpAddr())) {
                LOGGER.error("query device ports: acresponse return error, errMsg: ip is null!" );
                throw new ServiceException(DriverErrorCode.ADAPTER_GRE_QUERY_WAN_INTERFACE_ERROR, "ip is null");
            }

            SbiIp tempIp = new SbiIp(acDevicePort);

            if(CommonConst.WAN_DEFAULT_IP.equals(tempIp.getIpv4())) {
                for (int queryip = 0; queryip < CommonConst.QUERY_TIME; queryip ++) {
                    List<AcDevicePort> portList = DevicePortServiceImpl.queryPorts(deviceId, ctrlId, Arrays.asList(portName));
                    if(org.apache.commons.collections.CollectionUtils.isEmpty(portList)) {
                        return new ResultRsp<SbiIp>(DriverErrorCode.CLOUDVPN_FAILED);
                    }

                    AcDevicePort queryPort = portList.get(0);

                    if(null == queryPort) {
                        return new ResultRsp<SbiIp>(DriverErrorCode.CLOUDVPN_FAILED);
                    }

                    if(CommonConst.WAN_DEFAULT_IP.equals(queryPort.getIpAddr())) {
                        DevicePortServiceImpl.sleepSometime(CommConst.GET_WAN_IP_WAIT_TIME);
                    } else {
                        tempIp = new SbiIp(queryPort);
                        ipRsp.setData(tempIp);
                        return ipRsp;
                    }
                }
            }
            ipRsp.setData(tempIp);
            return ipRsp;
        }
        return ipRsp;
    }

    private static void sleepSometime(long time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            LOGGER.error("sleep exception =", e);
        }
    }
}
