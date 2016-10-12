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

package org.openo.sdno.overlayvpndriver.service.vxlan;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpndriver.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.overlayvpndriver.model.vxlan.db.VxLanExternalIdMapping;
import org.openo.sdno.overlayvpndriver.sbi.vxlan.VxLANCliSbi;
import org.openo.sdno.overlayvpndriver.sbi.vxlan.VxLANRestfulSbi;
import org.openo.sdno.overlayvpndriver.util.config.DeviceCommParamReader;
import org.openo.sdno.overlayvpndriver.util.config.DeviceParam;
import org.openo.sdno.overlayvpndriver.util.config.DeviceType;
import org.openo.sdno.overlayvpndriver.util.db.VxLanDbOper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VxLan service implementation.<br>
 * 
 * @author
 * @version SDNO 0.5 Jun 16, 2016
 */
@Service
public class VxLanSvcImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(VxLanSvcImpl.class);

    @Autowired
    private VxLANRestfulSbi vxLanRestfulSbi;

    @Autowired
    private VxLANCliSbi vxLanCliSbi;

    /**
     * Create VxLan operation. <br>
     * 
     * @param ctrlUuid The controller UUID
     * @param deviceId The device id
     * @param netVxLanDeviceModelList The data that want to crate
     * @return The ResultRsp with the list of NetVxLanDeviceModel
     * @throws ServiceException When create failed
     * @since SDNO 0.5
     */
    public ResultRsp<List<NetVxLanDeviceModel>> createVxLan(String ctrlUuid, String deviceId,
            List<NetVxLanDeviceModel> netVxLanDeviceModelList) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        // check parameters
        if(StringUtils.isEmpty(ctrlUuid) || StringUtils.isEmpty(deviceId)) {
            LOGGER.error("createVxLan: parameter error.");
            SvcExcptUtil.throwBadRequestException("createVxLan: parameter error.");
        }

        // send to controller
        List<NetVxLanDeviceModel> data = null;

        DeviceParam deviceParam = DeviceCommParamReader.getDeviceCommParam(deviceId);
        if(null == deviceParam) {
            LOGGER.error("Current device does not exist");
            throw new ServiceException("Current device does not exist");
        }

        if(DeviceType.THINCPE.getName().equals(deviceParam.getDeviceType())) {
            data = vxLanRestfulSbi.create(ctrlUuid, deviceId, netVxLanDeviceModelList);
        } else if(DeviceType.VCPE.getName().equals(deviceParam.getDeviceType())) {
            data = vxLanCliSbi.create(ctrlUuid, deviceId, netVxLanDeviceModelList);
        } else {
            LOGGER.error("UnKnown Device Type");
            throw new ServiceException("UnKnown Device Type");
        }

        // store to DB
        insertData(deviceId, data);

        LOGGER.info("Create VxLan cost time = " + (System.currentTimeMillis() - beginTime));

        return new ResultRsp<List<NetVxLanDeviceModel>>(ErrorCode.OVERLAYVPN_SUCCESS, netVxLanDeviceModelList);
    }

    /**
     * Delete VxLan operation. <br>
     * 
     * @param ctrlUuid Controller UUID
     * @param instanceId The UUID of VxLan instance
     * @return The object of ResultRsp
     * @throws ServiceException When delete failed
     * @since SDNO 0.5
     */
    public ResultRsp<String> deleteVxLan(String ctrlUuid, String instanceId) throws ServiceException {

        long beginTime = System.currentTimeMillis();

        // query from DB
        VxLanExternalIdMapping vxLanExternalIdMapping = VxLanDbOper.query(instanceId);
        if(null == vxLanExternalIdMapping) {
            return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
        }

        String deviceId = vxLanExternalIdMapping.getDeviceId();
        String vxLanId = vxLanExternalIdMapping.getExternalId();

        // send to controller

        DeviceParam deviceParam = DeviceCommParamReader.getDeviceCommParam(deviceId);
        if(null == deviceParam) {
            LOGGER.error("Current device does not exist");
            throw new ServiceException("Current device does not exist");
        }

        if(DeviceType.THINCPE.getName().equals(deviceParam.getDeviceType())) {
            vxLanRestfulSbi.delete(ctrlUuid, deviceId, vxLanId);
        } else if(DeviceType.VCPE.getName().equals(deviceParam.getDeviceType())) {
            vxLanCliSbi.delete(ctrlUuid, deviceId, vxLanId);
        } else {
            LOGGER.error("UnKnown Device Type");
            throw new ServiceException("UnKnown Device Type");
        }

        // delete DB
        VxLanDbOper.delete(instanceId);

        LOGGER.info("deleteVxLan cost time = " + (System.currentTimeMillis() - beginTime));

        return new ResultRsp<String>(ErrorCode.OVERLAYVPN_SUCCESS);
    }

    private void insertData(String deviceId, List<NetVxLanDeviceModel> netVxLanDeviceModelList)
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
