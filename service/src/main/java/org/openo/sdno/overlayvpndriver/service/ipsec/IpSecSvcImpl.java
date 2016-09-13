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

package org.openo.sdno.overlayvpndriver.service.ipsec;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpndriver.login.OverlayVpnDriverProxy;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdno.overlayvpndriver.model.ipsec.db.IpSecExternalIdMapping;
import org.openo.sdno.overlayvpndriver.util.consts.ControllerUrlConst;
import org.openo.sdno.overlayvpndriver.util.controller.ControllerUtil;
import org.openo.sdno.overlayvpndriver.util.db.IpSecDbOper;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * IpSec service implementation. <br>
 * 
 * @author
 * @version SDNO 0.5 Jun 16, 2016
 */
@Service
public class IpSecSvcImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpSecSvcImpl.class);

    private static final int MAX_SEQ_NUMBER = 10000;

    /**
     * Private constructor added to fix sonar issue
     */
    private IpSecSvcImpl() {

    }

    /**
     * Create IPSec operation. <br>
     * 
     * @param ctrlUuid The controller UUID
     * @param deviceId The device id
     * @param netIpSecModelList The data that want to crate
     * @return The ResultRsp with the list of NetIpSecModel
     * @throws ServiceException When create failed
     * @since SDNO 0.5
     */
    public static ResultRsp<List<NetIpSecModel>> createIpSec(String ctrlUuid, String deviceId,
            List<NetIpSecModel> netIpSecModelList) throws ServiceException {

        if(StringUtils.isEmpty(ctrlUuid) || StringUtils.isEmpty(deviceId)) {
            LOGGER.error("createIpSec: parameter error.");
            SvcExcptUtil.throwBadRequestException("createIpSec: parameter error.");
        }

        for(NetIpSecModel netIpSecModel : netIpSecModelList) {
            // query from controller
            ResultRsp<List<NetIpSecModel>> result =
                    queryIpSecFromController(ctrlUuid, deviceId, netIpSecModel.getInterfaceName(), null);

            // generate seqNumber
            generateIpSecSeqNumber(netIpSecModel, result.getData());

            // send to controller
            ResultRsp<NetIpSecModel> handleRsp = handleIpSecByController(ctrlUuid, deviceId, netIpSecModel, false);
            if(!handleRsp.isSuccess()) {
                return new ResultRsp<List<NetIpSecModel>>(handleRsp);
            }
        }

        // store to DB
        insertData(deviceId, netIpSecModelList);

        return new ResultRsp<List<NetIpSecModel>>(ErrorCode.OVERLAYVPN_SUCCESS, netIpSecModelList);
    }

    /**
     * Delete IPSec operation. <br>
     * 
     * @param ctrlUuid Controller UUID
     * @param ipSecConnectionId The UUID of IPSec connection
     * @return The object of ResultRsp
     * @throws ServiceException When delete failed
     * @since SDNO 0.5
     */
    public static ResultRsp<String> deleteIpSec(String ctrlUuid, String ipSecConnectionId) throws ServiceException {

        // query from DB
        IpSecExternalIdMapping ipSecExternalIdMapping = IpSecDbOper.query(ipSecConnectionId);
        if(null == ipSecExternalIdMapping) {
            LOGGER.warn("This Ipsec data not exist in database!!");
            return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
        }

        String deviceId = ipSecExternalIdMapping.getDeviceId();
        String ipSecId = ipSecExternalIdMapping.getExternalId();
        String seqNumber = ipSecExternalIdMapping.getSeqNumber();

        // query from controller
        ResultRsp<List<NetIpSecModel>> result = queryIpSecFromController(ctrlUuid, deviceId, null, ipSecId);

        if(CollectionUtils.isEmpty(result.getData())) {
            LOGGER.warn("This Ipsec data not exist in database!!");
            // delete DB
            IpSecDbOper.delete(ipSecConnectionId);
            return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
        }

        List<NetIpSecConn> netIpSecConnList = new ArrayList<NetIpSecConn>();
        NetIpSecModel netIpSecModel = JsonUtil.fromJson(JsonUtil.toJson(result.getData().get(0)), NetIpSecModel.class);
        for(NetIpSecConn netIpSecConn : netIpSecModel.getIpsecConnection()) {
            if(seqNumber.equals(String.valueOf(netIpSecConn.getSeqNumber()))) {
                netIpSecConnList.add(netIpSecConn);
                break;
            }
        }

        if(CollectionUtils.isEmpty(netIpSecConnList)) {
            LOGGER.warn("This Ipsec data not exist in controller!!");
            // delete DB
            IpSecDbOper.delete(ipSecConnectionId);
            return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
        }

        netIpSecModel.setIpsecConnection(netIpSecConnList);

        // send to controller
        ResultRsp<NetIpSecModel> handleRsp = handleIpSecByController(ctrlUuid, deviceId, netIpSecModel, true);
        if(!handleRsp.isSuccess()) {
            return new ResultRsp<String>(handleRsp);
        }

        // delete DB
        IpSecDbOper.delete(ipSecConnectionId);

        return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
    }

    private static ResultRsp<List<NetIpSecModel>> queryIpSecFromController(String ctrlUuid, String deviceId,
            String interfaceName, String ipSecId) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        String queryUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_IPSEC, deviceId);
        if(StringUtils.isNotEmpty(interfaceName)) {
            StringBuilder strBuidler = new StringBuilder();
            /*
             * To fix Sonar Isssue,Avoid concatenating nonliterals in a StringBuffer/StringBuilder
             * constructor or append().
             */
            String queryUrlStr = queryUrl + "?interfaceName=" + interfaceName;
            strBuidler.append(queryUrlStr);
            queryUrl = strBuidler.toString();
        }

        if(StringUtils.isNotEmpty(ipSecId)) {
            StringBuilder strBuidler = new StringBuilder();
            /*
             * To fix Sonar Isssue,Avoid concatenating nonliterals in a StringBuffer/StringBuilder
             * constructor or append().
             */
            String queryUrlStr = queryUrl + "?ipsecId=" + ipSecId;
            strBuidler.append(queryUrlStr);
            queryUrl = strBuidler.toString();
        }

        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);
        List<NetIpSecModel> data = new ControllerUtil<NetIpSecModel>().checkRsp(httpMsg);

        ResultRsp<List<NetIpSecModel>> resultRsp = new ResultRsp<List<NetIpSecModel>>(ErrorCode.OVERLAYVPN_SUCCESS);
        resultRsp.setData(data);

        LOGGER.info("queryIpSecFromController cost time = " + (System.currentTimeMillis() - beginTime));

        return resultRsp;
    }

    private static void generateIpSecSeqNumber(NetIpSecModel ipsecModel, List<NetIpSecModel> acExistedNetIpSecModels)
            throws ServiceException {
        if(CollectionUtils.isEmpty(acExistedNetIpSecModels)) {
            int index = 1;
            for(NetIpSecConn ipsecConnection : ipsecModel.getIpsecConnection()) {
                ipsecConnection.setSeqNumber(index++);
            }
        } else {
            Map<Integer, NetIpSecConn> existNumberMap = new ConcurrentHashMap<Integer, NetIpSecConn>();

            for(NetIpSecConn ipsecConnection : acExistedNetIpSecModels.get(0).getIpsecConnection()) {
                existNumberMap.put(ipsecConnection.getSeqNumber(), ipsecConnection);
            }

            for(NetIpSecConn ipsecConnection : ipsecModel.getIpsecConnection()) {
                for(int i = 1; i <= MAX_SEQ_NUMBER; i++) {
                    if(!existNumberMap.containsKey(i)) {
                        ipsecConnection.setSeqNumber(i);
                        existNumberMap.put(i, ipsecConnection);
                        break;
                    } else if(MAX_SEQ_NUMBER == i) {
                        throw new ServiceException(ErrorCode.OVERLAYVPN_FAILED, "out of seqNumber range.");
                    }
                }
            }
        }
    }

    private static ResultRsp<NetIpSecModel> handleIpSecByController(String ctrlUuid, String deviceId,
            NetIpSecModel netIpSecModel, boolean deleteMode) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        if(CollectionUtils.isEmpty(netIpSecModel.getIpsecConnection())) {
            LOGGER.error("handleIpSecByController error, ipsecConnection is null.");
            throw new ServiceException(ErrorCode.OVERLAYVPN_FAILED, "ipsecConnection is null.");
        }

        if(deleteMode) {
            for(NetIpSecConn netIpSecConn : netIpSecModel.getIpsecConnection()) {
                netIpSecConn.setDeleteMode(deleteMode);
            }
        }

        List<NetIpSecModel> ipsecModelList = new ArrayList<NetIpSecModel>(1);
        ipsecModelList.add(netIpSecModel);
        String ipSecUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_IPSEC, deviceId);

        Map<String, List<NetIpSecModel>> ctrlInfoMap = new ConcurrentHashMap<String, List<NetIpSecModel>>();
        ctrlInfoMap.put(CommConst.IP_SEC_LIST, ipsecModelList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(ipSecUrl, JsonUtil.toJson(ctrlInfoMap), ctrlUuid);
        List<NetIpSecModel> data = new ControllerUtil<NetIpSecModel>().checkRsp(httpMsg);
        ResultRsp<NetIpSecModel> resultRsp = new ResultRsp<NetIpSecModel>(ErrorCode.OVERLAYVPN_SUCCESS);
        if(CollectionUtils.isNotEmpty(data)) {
            NetIpSecModel retNetIpSecModel = JsonUtil.fromJson(JsonUtil.toJson(data.get(0)), NetIpSecModel.class);
            netIpSecModel.setUuid(retNetIpSecModel.getUuid());
        }
        resultRsp.setData(netIpSecModel);

        LOGGER.info("handleIpSecByController cost time = " + (System.currentTimeMillis() - beginTime));

        return resultRsp;
    }

    private static void insertData(String deviceId, List<NetIpSecModel> netIpSecModelList) throws ServiceException {
        for(NetIpSecModel netIpSecModel : netIpSecModelList) {
            String externalId = netIpSecModel.getUuid();

            for(NetIpSecConn netIpSecConn : netIpSecModel.getIpsecConnection()) {
                String ipSecConnectionId = netIpSecConn.getIpSecConnectionId();
                String seqNumber = String.valueOf(netIpSecConn.getSeqNumber());

                IpSecExternalIdMapping ipSecExternalIdMapping =
                        new IpSecExternalIdMapping(ipSecConnectionId, externalId, seqNumber, deviceId);
                ipSecExternalIdMapping.allocateUuid();
                IpSecDbOper.insert(ipSecExternalIdMapping);
            }
        }
    }
}
