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

package org.openo.sdnhub.overlayvpndriver.rest;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.DeviceServiceImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.AdapterDeviceCreateBasicInfo;
import org.openo.sdnhub.overlayvpndriver.service.model.AdapterDeviceInfo;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Restful interface for Device configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jun 19, 2017
 */
@Service
@Path("/sbi-localsite/v1")
public class DeviceROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceROAResource.class);

    private static final String INVALID_CONTROLLER_UUID = "Invalid controller UUID.";

    // pattern for the ESN
    private static final String ESN_THIN_CPE = "^[A-Z0-9]{20}$";
    private static final String ESN_CLOUD_CPE= "^21[a-zA-Z0-9]{8}[0-9]{14}[a-zA-Z0-9]{8}$";

    @Autowired
    private DeviceServiceImpl deviceService;

    /**
     * Adds new device configuration using a specific Controller.<br>
     *
     * @param ctrlUuidParam Controller UUID
     * @param aDevCrtInfos collection of device configuration
     * @return ResultRsp object with added device configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/devices")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<AdapterDeviceInfo> createDevices(@HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
                                                      List<AdapterDeviceCreateBasicInfo> aDevCrtInfos) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        if(CollectionUtils.isEmpty(aDevCrtInfos)) {
            LOGGER.error("add device null");
            SvcExcptUtil.throwBadRequestException("add device");
        }

        for(AdapterDeviceCreateBasicInfo adapter : aDevCrtInfos) {
            ValidationUtil.validateModel(adapter);
            validateEsnForAdapterDeviceCreateBasicInfo(adapter);
        }

        for (AdapterDeviceCreateBasicInfo device : aDevCrtInfos) {
            if (!StringUtils.hasLength(device.getUuid())) {
                device.allocateUuid();
            }


        }

        Map<String, List<AdapterDeviceCreateBasicInfo>> crtInfoMap =
                new ConcurrentHashMap<>();
        crtInfoMap.put(CommConst.CREATE_DEVICE_PARAMETER, aDevCrtInfos);

        return deviceService.createDevices(ctrlUuid, JsonUtil.toJson(crtInfoMap));
    }

    /**
     * Queries device configuration using a specific Controller.<br/>
     *
     * @param ctrlUuidParam Controller UUID
     * @param esn esn number
     * @return ResultRsp object with collection of queried device configuration
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/devices")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<List<AdapterDeviceInfo>> queryDeviceByEsn(@HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
                                                               @QueryParam(CommonConst.ESN_QUERY_PARAM) String esn) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        Map<String, String> paramInfo = new HashMap<>();
        paramInfo.put("keyWord", esn);
        return deviceService.queryDevices(ctrlUuid, JsonUtil.toJson(paramInfo));
    }

    /**
     * Deletes device configuration using a specific Controller.<br/>
     *
     * @param deviceIds collection of device id
     * @param ctrlUuidParam Controller UUID
     * @return ResultRsp object with deleted device configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @DELETE
    @Path("/devices")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> deleteDevices(@QueryParam(CommonConst.DEVICE_IDS_PATH_PARAM) String deviceIds,
                                           @HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam)
            throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        List<String> deviceIdList = JsonUtil.fromJson(deviceIds, new TypeReference<List<String>>()
        {
        });

        if(CollectionUtils.isEmpty(deviceIdList)) {
            LOGGER.error("delete device , invalid deviceID.");
            SvcExcptUtil.throwBadRequestException("delete device , invalid deviceID.");
        }

        LOGGER.debug("deviceIdList:" + deviceIdList);
        return deviceService.deleteDevice(ctrlUuid, deviceIdList);
    }

    /**
     * Updates device configuration using a specific Controller.<br/>
     *
     * @param ctrlUuidParam Controller UUID
     * @param deviceId device id
     * @param adapterDeviceInfo device configuration needs to be updated
     * @return ResultRsp object with updated device configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/deviceid/{deviceuuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<String> modifyDevice(@HeaderParam(CommonConst.CTRL_HEADER_PARAM) String ctrlUuidParam,
                                          @PathParam(CommonConst.DEVICE_UUID_PATH_PARAM) String deviceId,
            AdapterDeviceInfo adapterDeviceInfo) throws ServiceException {

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error(INVALID_CONTROLLER_UUID);
            throw new ParameterServiceException(INVALID_CONTROLLER_UUID);
        }

        if(!StringUtils.hasLength(deviceId)) {
            LOGGER.error("modify device , url resource err.");
            SvcExcptUtil.throwBadRequestException("modify device, param err.");
        }

        if(null == adapterDeviceInfo) {
            LOGGER.error("modify device aDeviceInfo is null");
            SvcExcptUtil.throwBadRequestException("modify device param err. ");
        }

        ValidationUtil.validateModel(adapterDeviceInfo);
        validateEsnForAdapterDeviceCreateBasicInfo(adapterDeviceInfo);

        return deviceService.modifyDevice(ctrlUuid, deviceId, adapterDeviceInfo);
    }

    private void validateEsnForAdapterDeviceCreateBasicInfo (
            AdapterDeviceCreateBasicInfo adapterDeviceCreateBasicInfo) throws ServiceException {

        String esn = (adapterDeviceCreateBasicInfo != null) ? adapterDeviceCreateBasicInfo.getEsn() : null;

        // if esn doesn't match the pattern then throw the exception
        if (esn == null || !(esn.matches(ESN_CLOUD_CPE) || esn.matches(ESN_THIN_CPE))) {
            throw new ParameterServiceException("invalid esn");
        }
    }
}
