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

package org.openo.sdno.acbranchservice.service.vxlan;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.login.AcBranchProxy;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.acbranchservice.model.vxlan.db.VxLanExternalIdMapping;
import org.openo.sdno.acbranchservice.util.consts.ControllerUrlConst;
import org.openo.sdno.acbranchservice.util.controller.ControllerUtil;
import org.openo.sdno.acbranchservice.util.db.VxLanDbOper;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * VxLan service implementation. <br>
 * 
 * @author
 * @version SDNO 0.5 Jun 16, 2016
 */
@Service
public class VxLanSvcImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(VxLanSvcImpl.class);

    private static final String DELETE_VXLAN_PARAMETER = "ids";

    /**
     * Private constructor added to fix sonar issue
     */
    private VxLanSvcImpl() {

    }

    /**
     * Create VxLan operation. <br>
     * 
     * @param ctrlUuid
     *            The controller UUID
     * @param deviceId
     *            The device id
     * @param netVxLanDeviceModelList
     *            The data that want to crate
     * @return The ResultRsp with the list of NetVxLanDeviceModel
     * @throws ServiceException
     *             When create failed
     * @since SDNO 0.5
     */
    public static ResultRsp<List<NetVxLanDeviceModel>> createVxLan(String ctrlUuid, String deviceId,
            List<NetVxLanDeviceModel> netVxLanDeviceModelList) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        // check parameters
        if(StringUtils.isEmpty(ctrlUuid) || StringUtils.isEmpty(deviceId)) {
            LOGGER.error("createVxLan: parameter error.");
            SvcExcptUtil.throwBadRequestException("createVxLan: parameter error.");
        }

        // send to controller
        String url = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);
        Map<String, List<NetVxLanDeviceModel>> ctrlInfoMap = new ConcurrentHashMap<String, List<NetVxLanDeviceModel>>();
        ctrlInfoMap.put(CommConst.VXLAN_LIST, netVxLanDeviceModelList);
        HTTPReturnMessage httpMsg = AcBranchProxy.getInstance().sendPutMsg(url, JsonUtil.toJson(ctrlInfoMap), ctrlUuid);
        List<NetVxLanDeviceModel> data = new ControllerUtil<NetVxLanDeviceModel>().checkRsp(httpMsg);

        // store to DB
        insertData(deviceId, data);

        LOGGER.info("createVxLan cost time = " + (System.currentTimeMillis() - beginTime));

        return new ResultRsp<List<NetVxLanDeviceModel>>(ErrorCode.OVERLAYVPN_SUCCESS, netVxLanDeviceModelList);
    }

    /**
     * Delete VxLan operation. <br>
     * 
     * @param ctrlUuid
     *            Controller UUID
     * @param instanceId
     *            The UUID of VxLan instance
     * @return The object of ResultRsp
     * @throws ServiceException
     *             When delete failed
     * @since SDNO 0.5
     */
    public static ResultRsp<String> deleteVxLan(String ctrlUuid, String instanceId) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        // query from DB
        VxLanExternalIdMapping vxLanExternalIdMapping = VxLanDbOper.query(instanceId);
        if(null == vxLanExternalIdMapping) {
            return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
        }

        // send to controller
        String deviceId = vxLanExternalIdMapping.getDeviceId();
        String vxLanId = vxLanExternalIdMapping.getExternalId();

        List<String> idList = new ArrayList<>();
        idList.add(vxLanId);

        String url = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);
        Map<String, List<String>> crtInfoMap = new HashMap<>();
        crtInfoMap.put(DELETE_VXLAN_PARAMETER, idList);
        HTTPReturnMessage httpMsg =
                AcBranchProxy.getInstance().sendDeleteMsg(url, JsonUtil.toJson(crtInfoMap), ctrlUuid);
        new ControllerUtil<NetVxLanDeviceModel>().checkRsp(httpMsg);

        // delete DB
        VxLanDbOper.delete(instanceId);

        LOGGER.info("deleteVxLan cost time = " + (System.currentTimeMillis() - beginTime));

        return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
    }

    private static void insertData(String deviceId, List<NetVxLanDeviceModel> netVxLanDeviceModelList)
            throws ServiceException {
        List<NetVxLanDeviceModel> refreshedList = JsonUtil.fromJson(JsonUtil.toJson(netVxLanDeviceModelList),
                new TypeReference<List<NetVxLanDeviceModel>>() {});
        for(NetVxLanDeviceModel netVxLanDeviceModel : refreshedList) {
            String externalId = netVxLanDeviceModel.getUuid();
            String vxLanInstanceId = netVxLanDeviceModel.getName();

            VxLanExternalIdMapping vxLanExternalIdMapping =
                    new VxLanExternalIdMapping(vxLanInstanceId, externalId, deviceId);
            vxLanExternalIdMapping.allocateUuid();
            VxLanDbOper.insert(vxLanExternalIdMapping);
        }
    }
}
