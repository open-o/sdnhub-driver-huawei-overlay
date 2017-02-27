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
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.ctc.wstx.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst;
import org.openo.sdnhub.overlayvpndriver.common.consts.HttpCode;
import org.openo.sdnhub.overlayvpndriver.common.util.NqaConfigUtil;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.Ip;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnList;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnection;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.IpSecConnectionType;
import org.openo.sdnhub.overlayvpndriver.service.model.NQADeviceModel;
import org.openo.sdnhub.overlayvpndriver.service.model.NeRoleType;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNqa;
import org.openo.sdnhub.overlayvpndriver.translator.NqaIpSecTranslate;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.errorcode.ErrorCodeInfo;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IP-security service implementation.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jan 16, 2017
 */
public class IpsecImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpsecImpl.class);

    private static final String FALSE = "false";

    /**
     * Deletes IPSec VPN configuration using a specific controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param ipSecList collection of sequence number
     * @return ResultRsp object with IPSec VPN deleted configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<SbiNeIpSec> batchDeleteIpsecConn(String ctrlUuid, List<SbiNeIpSec> ipSecList)
            throws ServiceException {

        ResultRsp<SbiNeIpSec> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        for(SbiNeIpSec neIpSec : ipSecList) {
            String deviceId = neIpSec.getDeviceId();
            ResultRsp<List<IpsecConnList>> result = queryIpsecByDevice(ctrlUuid, deviceId, neIpSec.getSoureIfName());
            if(CollectionUtils.isEmpty(result.getData())) {
                resultRsp.getSuccessed().add(neIpSec);
                continue;
            }

            for(IpsecConnList ipsecModel : result.getData()) {
                if(neIpSec.getExternalIpSecId().equals(ipsecModel.getUuid())) {
                    List<IpsecConnection> connList = new ArrayList<IpsecConnection>();
                    for(IpsecConnection ipsecConn : ipsecModel.getIpsecConnection()) {
                        if(neIpSec.getExternalId().equals(String.valueOf(ipsecConn.getSeqNumber()))) {
                            connList.add(ipsecConn);
                            break;
                        }
                    }

                    ipsecModel.setIpsecConnection(new ArrayList<IpsecConnection>());
                    ipsecModel.getIpsecConnection().addAll(connList);
                    ResultRsp<IpsecConnList> rsp = handleIpsecByDevice(ctrlUuid, deviceId, ipsecModel, true);
                    if(!rsp.isSuccess()) {
                        FailData<SbiNeIpSec> failData = new FailData<>(rsp.getErrorCode(), rsp.getMessage(), neIpSec);
                        resultRsp.getFail().add(failData);
                    } else {
                        resultRsp.getSuccessed().add(neIpSec);
                    }
                }
            }
        }

        return resultRsp;
    }

    /**
     * Queries IPSec VPN configuration by device using a specific controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param interfaceName interface name
     * @return ResultRsp object with IPSec VPN queried configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static ResultRsp<List<IpsecConnList>> queryIpsecByDevice(String ctrlUuid, String deviceId, String interfaceName)
            throws ServiceException {

        ResultRsp<List<IpsecConnList>> resultRsp = new ResultRsp<List<IpsecConnList>>(ErrorCode.OVERLAYVPN_SUCCESS);
        if ((StringUtils.isEmpty(ctrlUuid)) || (StringUtils.isEmpty(deviceId)))
        {
            LOGGER.error("queryIpsecByDevice: parameter error.");
            throw new ServiceException(ErrorCode.OVERLAYVPN_PARAMETER_INVALID, "queryIpsecByDevice: parameter error.");
        }

        String queryUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_IPSEC, deviceId);
        if (StringUtils.isNotEmpty(interfaceName))
        {
            StringBuilder strBuidler = new StringBuilder();
            strBuidler.append(queryUrl + "?interfaceName=" + interfaceName);
            queryUrl = strBuidler.toString();
        }

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Ipsec query begin time = " + beginTime);

        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);

        LOGGER.debug("Ipsec query cost time = " + (System.currentTimeMillis() - beginTime));

        String body = httpMsg.getBody();

        if ((!httpMsg.isSuccess()) || (StringUtils.isEmpty(body)))
        {
            LOGGER.error("queryIpsecByDevice: httpMsg return error. body is " + body);
            throw new ServiceException(ErrorCode.OVERLAYVPN_FAILED,
                    "queryIpsecByDevice: httpMsg return error.");
        }

        ACResponse acresponse = JsonUtil.fromJson(body, new TypeReference<ACResponse<List<IpsecConnList>>>()
        {
        });

        if (acresponse.isSucceed())
        {
            List<IpsecConnList> queryDatas = (List<IpsecConnList>) acresponse.getData();

            resultRsp.setData(new ArrayList<IpsecConnList>());
            if(CollectionUtils.isNotEmpty(queryDatas))
            {
                if (StringUtils.isNotEmpty(interfaceName))
                {
                    for(IpsecConnList ipsecModel : queryDatas)
                    {
                        if(interfaceName.equalsIgnoreCase(ipsecModel.getInterfaceName()))
                        {
                            resultRsp.getData().add(ipsecModel);
                        }
                    }
                }
                else
                {
                    resultRsp.setData(queryDatas);
                }
            }

            return resultRsp;
        }

        LOGGER.error("queryIpsecByDevice: acresponse return error. errMsg: " + acresponse.getErrmsg());
        LOGGER.error("queryIpsecByDevice: acresponse body is " + body);

        if(HttpCode.TIMEOUT == httpMsg.getStatus())
        {
            // Need to throw error as controller timeout
        }

        throw new ServiceException(ErrorCode.OVERLAYVPN_FAILED, acresponse.getErrmsg());
    }

    /**
     * Adds IPSec VPN configuration using a specific controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param list collection of ip-security connection
     * @return ResultRsp object with collection of ip-security connection configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<List<IpsecConnList>> configIpsec(String ctrlUuid, String deviceId, List<IpsecConnList> list)
            throws ServiceException {

        ResultRsp<List<IpsecConnList>> resultRsp =
                new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, new ArrayList<IpsecConnList>());
        if(StringUtils.isEmpty(deviceId)) {
            LOGGER.error("Device id is null or empty.");
            throw new ParameterServiceException("Device id is null or empty.");
        }

        for(IpsecConnList ipSecModel : list) {
            ResultRsp<List<IpsecConnList>> result =
                    queryIpsecByDevice(ctrlUuid, deviceId, ipSecModel.getInterfaceName());

            if(CollectionUtils.isEmpty(result.getData())) {
                ipSecModel.setName(result.getData().get(0).getName());
                IpsecConnection ipsecConn = ipSecModel.getIpsecConnection().get(0);
                if ("true".equals(ipsecConn.getType())) {
                    continue;
                }
            } else {
                int randomNumber = (int) Math.round(Math.random() * (99-1)+1);
                String hexTime = Long.toHexString(System.nanoTime());
                int length = hexTime.length();
                if (length > 13) {
                    hexTime = hexTime.substring(length - 13, length);
                }

                ipSecModel.setName(hexTime + randomNumber);
            }

            ResultRsp<IpsecConnList> rsp = handleIpsecByDevice(ctrlUuid, deviceId, ipSecModel, false);
            if(!rsp.isSuccess()) {
                resultRsp.setErrorCode(ErrorCode.OVERLAYVPN_FAILED);
                return resultRsp;
            }
            resultRsp.getData().add(rsp.getData());

            if (CollectionUtils.isNotEmpty(ipSecModel.getIpsecConnection()) && "true".equals(ipSecModel.getIpsecConnection().get(0).getType())) {
                break;
            }
        }
        return resultRsp;
    }

    /**
     * Deletes NQA information using a specific controller.<br>
     *
     * @param cltuuid Controller UUID
     * @param deleteUrl controller url used to delete NQA information
     * @param nqaListJson NQA information json body
     * @return ResultRsp object with NQA deleted configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> deleteNqaConfigForIpSec(String cltuuid, String deleteUrl, String nqaListJson)
            throws ServiceException {

        final HTTPReturnMessage deleteRsp =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(deleteUrl, nqaListJson, cltuuid);
        LOGGER.debug("body:{}", deleteRsp);
        final String retBody = deleteRsp.getBody();
        final String actionDesc = "update nqa config";
        return NqaConfigUtil.parseDeleteResponse(deleteRsp, retBody, actionDesc);
    }

    /**
     * Updates IPSec VPN configuration using a specific controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param sbiNeIpSec IPSec VPN configuration
     * @param updateRsp updated response from controller
     * @return ResultRsp object with IPSec VPN updated connection status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public static void update(String ctrlUuid, String deviceId, SbiNeIpSec sbiNeIpSec, ResultRsp<SbiNeIpSec> updateRsp)
            throws ServiceException {

        ResultRsp<List<IpsecConnList>> result = IpsecImpl.queryIpsecByDevice(ctrlUuid, deviceId, sbiNeIpSec.getSoureIfName());

        if(CollectionUtils.isEmpty(result.getData()))
        {
            FailData<SbiNeIpSec> failData = new FailData<SbiNeIpSec>();
            failData.setData(sbiNeIpSec);
            updateRsp.getFail().add(failData);
            return;
        }

        IpsecConnList existIpsecModel = result.getData().get(0);

        IpsecConnection tempIpsecConn = null;
        for(IpsecConnection ipsecConn : existIpsecModel.getIpsecConnection())
        {
            if(ipsecConn.getSeqNumber() == Integer.valueOf(sbiNeIpSec.getExternalId()))
            {
                tempIpsecConn = ipsecConn;
                break;
            }
        }

        if(null == tempIpsecConn)
        {
            FailData<SbiNeIpSec> failData = new FailData<SbiNeIpSec>();
            failData.setData(sbiNeIpSec);
            updateRsp.getFail().add(failData);
            return;
        }

        tempIpsecConn.setDeleteMode(false);

        if(FALSE.equals(sbiNeIpSec.getIsTemplateType()))
        {
            tempIpsecConn.getIke().setLocalAddress(sbiNeIpSec.buildSourceIp());

            Ip ip = JsonUtil.fromJson(sbiNeIpSec.getPeerAddress(), Ip.class);
            tempIpsecConn.getIke().setPeerAddress(ip.getIpv4());
        }

        existIpsecModel.setIpsecConnection(Arrays.asList(tempIpsecConn));

        ResultRsp<IpsecConnList> rsp = IpsecImpl.handleIpsecByDevice(ctrlUuid, deviceId, existIpsecModel, false);

        if(rsp.isSuccess())
        {
            updateRsp.getSuccessed().add(sbiNeIpSec);
        }
        else
        {
            FailData<SbiNeIpSec> failData = new FailData<SbiNeIpSec>(rsp.getErrorCode(), rsp.getMessage(), sbiNeIpSec);
            updateRsp.getFail().add(failData);
        }
    }

    /**
     * Adds new NQA configuration using a specific controller.<br>
     *
     * @param deviceIdToTpsecConnListMap collection of device id to ip-security connection
     * @param ctrlUuid Controller UUID
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public void createNQA(Map<String, List<SbiNeIpSec>> deviceIdToTpsecConnListMap, String ctrlUuid)
            throws ServiceException {

        for(Map.Entry<String, List<SbiNeIpSec>> entry : deviceIdToTpsecConnListMap.entrySet()) {
            List<SbiNqa> nqaList = new ArrayList<SbiNqa>();
            for(SbiNeIpSec SbiNeIpSec : entry.getValue()) {
                if("nqa".equals(SbiNeIpSec.getProtectionPolicy())
                        && NeRoleType.LOCALCPE.getName().equals(SbiNeIpSec.getLocalNeRole())) {
                    nqaList.add(NqaIpSecTranslate.buildNqa(SbiNeIpSec, /* SbiNeIpSec.getNqa() */ null));
                }
            }

            List<NQADeviceModel> nqlDeviceModelList = NqaIpSecTranslate.convertDeviceMode(nqaList);
            if(!CollectionUtils.isEmpty(nqlDeviceModelList)) {
                final Map<String, List<NQADeviceModel>> crtInfoMap = new HashMap<String, List<NQADeviceModel>>();
                crtInfoMap.put(CommonConst.NQA_LIST, nqlDeviceModelList);
                final String createUrl = MessageFormat.format(ControllerUrlConst.NQA_CONFIG_URL, entry.getKey());

                ResultRsp<List<NQADeviceModel>> rsp =
                        createNqaConfigForIPSec(ctrlUuid, createUrl, JsonUtil.toJson(crtInfoMap));
                NqaIpSecTranslate.convertNqa(rsp.getData(), nqaList);
            }
        }
    }

    /**
     * Prepares collection of device id to ip-security connection configuration.<br>
     *
     * @param ipSecNeConnectionList collection of IPSec VPN configuration
     * @return collection of device id to ip-security configuration
     * @since SDNHUB 0.5
     */
    public Map<String, List<SbiNeIpSec>> devideIpsecConnByDevice(List<SbiNeIpSec> ipSecNeConnectionList) {

        Map<String, List<SbiNeIpSec>> deviceIdToIpsecConnMap = new ConcurrentHashMap<>();
        for(SbiNeIpSec ipSecConn : ipSecNeConnectionList) {
            if(deviceIdToIpsecConnMap.containsKey(ipSecConn.getDeviceId())) {
                deviceIdToIpsecConnMap.get(ipSecConn.getDeviceId()).add(ipSecConn);
            } else {
                List<SbiNeIpSec> dataList = new ArrayList<SbiNeIpSec>();
                dataList.add(ipSecConn);
                deviceIdToIpsecConnMap.put(ipSecConn.getDeviceId(), dataList);
            }
        }
        return deviceIdToIpsecConnMap;
    }

    /**
     * Prepares collection of error code information from controller response.<br>
     *
     * @param errorCodeInfoLst collection of error code information
     * @param entry single information of ip-security connection
     * @param resultRsp controler response with ip-security connection
     * @since SDNHUB 0.5
     */
    public void fillSmallErrorInfo(List<ErrorCodeInfo> errorCodeInfoLst, Entry<String, List<IpsecConnList>> entry,
            ResultRsp<List<IpsecConnList>> resultRsp) {

        errorCodeInfoLst.add(new org.openo.sdno.overlayvpn.errorcode.ErrorCodeInfo(resultRsp.getErrorCode(),
                resultRsp.getMessage(), "deviceId:" + entry.getKey()));
    }

    /**
     * Updates UUID and external id in collection of ip-security configuration.<br>
     *
     * @param dataList ip-security configuration
     * @return list ip-security connection
     * @param deviceId device id
     * @since SDNHUB 0.5
     */
    public void backWriteId(List<SbiNeIpSec> dataList, List<IpsecConnList> list, String deviceId) {
        for(SbiNeIpSec ipSecNeConnection : dataList) {

            if(deviceId.equals(ipSecNeConnection.getDeviceId())) {
                //External id's if in controller model and are available in SBI, needs to be set in future.
            }
        }
    }

    /**
     * Validates sequence number.<br>
     *
     * @param seqNumberList collection of sequence number
     * @since SDNHUB 0.5
     */
    public void checkSeqNumber(List<String> seqNumberList) throws ServiceException {

        if(CollectionUtils.isEmpty(seqNumberList)) {
            SvcExcptUtil.throwBadRequestException("Ipsec delete, sequence number is null.");
        }

        for(String seqNumber : seqNumberList) {
            if(StringUtils.isEmpty(seqNumber)) {
                LOGGER.error("ipsec delete, sequence number is null.");
                SvcExcptUtil.throwBadRequestException("ipsec delete, sequence number is null.");
            }
            try {
                int number = Integer.parseInt(seqNumber);
                if((number < 1) || (number > 10000)) {
                    LOGGER.error("ipsec delete, seqNumber not in range 1~10000 type. seqNumber = " + seqNumber);
                    SvcExcptUtil.throwBadRequestException("ipsec delete, seqNumber not in range.");
                }
            } catch(NumberFormatException e) {
                LOGGER.error("ipsec delete, seqNumber not int type. seqNumber = " + seqNumber);
                SvcExcptUtil.throwBadRequestException("ipsec delete, seqNumber not int type.");
            }
        }
    }

    private static ResultRsp<IpsecConnList> handleIpsecByDevice(String ctrlUuid, String deviceId, IpsecConnList ipsecModel,
            boolean deleteMode) throws ServiceException {

        ResultRsp<IpsecConnList> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        if(CollectionUtils.isEmpty(ipsecModel.getIpsecConnection())) {
            LOGGER.error("handleIpsecByDevice : ipsecConnection null");
            return new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS, ipsecModel);
        }

        for (IpsecConnection ipsecConnection : ipsecModel.getIpsecConnection()) {
            ipsecConnection.setDeleteMode(deleteMode);
        }

        List<IpsecConnList> ipsecModelList = new ArrayList();
        ipsecModelList.add(ipsecModel);
        String ipsecUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_IPSEC, deviceId);

        Map<String, List<IpsecConnList>> crtInfoMap = new ConcurrentHashMap();
        crtInfoMap.put(CommConst.IP_SEC_LIST, ipsecModelList);

        LOGGER.debug("ipsec create send body " + JsonUtil.toJson(crtInfoMap));
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(ipsecUrl, JsonUtil.toJson(crtInfoMap), ctrlUuid);
        String body = httpMsg.getBody();
        LOGGER.debug("ipsec create return body : " + body);

        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(body)) {
            OverlayVpnDriverResponse acresponse =
                    JsonUtil.fromJson(body, new TypeReference<OverlayVpnDriverResponse<List<IpsecConnList>>>() {});
            if(acresponse.isSucess()) {
                ipsecModel.setUuid(((List<IpsecConnList>)acresponse.getData()).get(0).getUuid());
                resultRsp.setData(ipsecModel);
                return resultRsp;
            }

            LOGGER.error("createIpSecByDevice: asresponse return error");
            return new ResultRsp<IpsecConnList>(ErrorCode.OVERLAYVPN_FAILED + acresponse.getErrmsg());
        }

        LOGGER.error("createIpSecByDevice: httpMsg return error");

        if (HttpCode.TIMEOUT == httpMsg.getStatus()) {
            // Need to return specific time out message
        }

        return new ResultRsp<IpsecConnList>(ErrorCode.OVERLAYVPN_FAILED + " createIpSecByDevice: httpMsg return error");
    }

    private ResultRsp<IpsecConnList> deleteIpsecConn(String ctrlUuid, String deviceId, IpsecConnList ipSecModel)
            throws ServiceException {

        ResultRsp<IpsecConnList> resultRsp = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        String deleteUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_IPSEC, deviceId);

        Map<String, List<IpsecConnList>> crtInfoMap = new ConcurrentHashMap<>();
        List<IpsecConnList> list = new ArrayList<>();
        list.add(ipSecModel);
        crtInfoMap.put(CommConst.IP_SEC_LIST, list);

        final HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(deleteUrl, JsonUtil.toJson(crtInfoMap), ctrlUuid);

        String body = httpMsg.getBody();

        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(body)) {
            ACDelResponse acresponse = JsonUtil.fromJson(body, new TypeReference<ACDelResponse>() {});
            if(acresponse.isSucess()) {
                //May need to set data or successed part of response in future.
                return resultRsp;
            }

            LOGGER.error("createIpSecByDevice: asresponse return error");
            return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED + acresponse.getErrmsg());
        }
        LOGGER.error("createIpSecByDevice: httpMsg return error");
        return new ResultRsp<>(ErrorCode.OVERLAYVPN_FAILED + " createIpSecByDevice: httpMsg return error");
    }

    private IpsecConnList compareData4Create(IpsecConnList existIpSecModel, IpsecConnList ipSecModel) {

        ipSecModel.setUuid(existIpSecModel.getUuid());
        IpsecConnList newIpSecModel = new IpsecConnList();
        newIpSecModel.setName(existIpSecModel.getName());
        newIpSecModel.setUuid(existIpSecModel.getUuid());
        newIpSecModel.setIpsecConnection(ipSecModel.getIpsecConnection());
        return newIpSecModel;
    }

    private ResultRsp<List<NQADeviceModel>> createNqaConfigForIPSec(String cltuuid, String createUrl, String nqaListJson)
            throws ServiceException {

        final HTTPReturnMessage createRsp =
                OverlayVpnDriverProxy.getInstance().sendGetMsg(createUrl, nqaListJson, cltuuid);
        final String retBody = createRsp.getBody();
        final String actionDesc = "create nqa config";
        return NqaConfigUtil.parseResponse(createRsp, retBody, actionDesc);
    }

    public static void checkRuleDataForLte(SbiNeIpSec neIpSecConnection) throws ServiceException {
        if (IpSecConnectionType.PROJECT.getName().equals(neIpSecConnection.getWorkType())
                && (StringUtils.isEmpty(neIpSecConnection.getSourceLanCidrs())
                || StringUtils.isEmpty(neIpSecConnection.getPeerLanCidrs()))) {
            LOGGER.error("param error for protect ipsec.");
            SvcExcptUtil.throwBadRequestException("null lan cidrs.");
        }
    }
}
