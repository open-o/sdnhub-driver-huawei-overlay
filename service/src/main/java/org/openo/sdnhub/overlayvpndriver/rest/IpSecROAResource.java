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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.RequestHeaderUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnList;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnection;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.IpsecImpl;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdnhub.overlayvpndriver.translator.NeConnectionToIpsec;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.errorcode.ErrorCodeInfo;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.CheckStrUtil;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.Map.Entry;

import static org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst.CTRL_HEADER_PARAM;

/**
 * Restful interface for IP-security VPN configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jun 19, 2017
 */
@Service
@Path("/sbi-ipsec/v1")
public class IpSecROAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpSecROAResource.class);

    @Autowired
    private IpsecImpl ipsecService;

    /**
     * Adds new IPSec VPN configuration using a specific controller.<br>
     *
     * @param request HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param ipSecNeConnectionList collection of IPSec VPN configuration
     * @return ResultRsp object with IPSec VPN added configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/batch-create-ipsecs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNeIpSec> ipsecCreate(@Context HttpServletRequest request,
            @HeaderParam(CTRL_HEADER_PARAM) String ctrlUuidParam, List<SbiNeIpSec> ipSecNeConnectionList)
            throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.info("Ipsec create begin time = " + beginTime);
        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        ResultRsp<SbiNeIpSec> totalResult = new ResultRsp<>(ErrorCode.OVERLAYVPN_SUCCESS);
        totalResult.setSuccessed(new ArrayList<SbiNeIpSec>());
        totalResult.setFail(new ArrayList<FailData<SbiNeIpSec>>());

        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(CollectionUtils.isEmpty(ipSecNeConnectionList)) {
            LOGGER.error("Request body parameter is null or empty.");
            throw new ParameterServiceException("Request body parameter is null or empty.");
        }

        for(SbiNeIpSec ipsecModel : ipSecNeConnectionList){
            ValidationUtil.validateModel(ipsecModel);
            IpsecImpl.checkRuleDataForLte(ipsecModel);
        }

        Map<String, List<SbiNeIpSec>> deviceIdToTpsecConnListMap =
                ipsecService.devideIpsecConnByDevice(ipSecNeConnectionList);
        Map<String, List<IpsecConnList>> deviceIdToIpsecModelMap =
                NeConnectionToIpsec.convert2Model(deviceIdToTpsecConnListMap, ctrlUuid);
        List<ErrorCodeInfo> errorCodeInfoLst = new ArrayList<>();
        for(Entry<String, List<IpsecConnList>> entry : deviceIdToIpsecModelMap.entrySet()) {
            ResultRsp<List<IpsecConnList>> resultRsp =
                    ipsecService.configIpsec(ctrlUuid, entry.getKey(), entry.getValue());
            if(resultRsp.isSuccess()) {
                totalResult.getSuccessed().addAll(deviceIdToTpsecConnListMap.get(entry.getKey()));
            } else {
                ipsecService.fillSmallErrorInfo(errorCodeInfoLst, entry, resultRsp);
                for(SbiNeIpSec SbiNeIpSec : deviceIdToTpsecConnListMap.get(entry.getKey())) {
                    FailData<SbiNeIpSec> failData =
                            new FailData<SbiNeIpSec>(resultRsp.getErrorCode(), resultRsp.getMessage(), SbiNeIpSec);
                    totalResult.getFail().add(failData);
                }
            }
        }
        LOGGER.info("createIpSec cost time = " + (System.currentTimeMillis() - beginTime));
        return totalResult;
    }

    /**
     * Deletes IPSec VPN configuration using a specific controller.<br>
     *
     * @param request HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param deviceId device id
     * @param ipsecList collection of IPSec VPN configuration
     * @return ResultRsp object with IPSec VPN deleted configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/{deviceid}/ipsecs/{extipsecid}/batch-delete-ipsec")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("unchecked")
    public ResultRsp<SbiNeIpSec> deleteIpSec(@Context HttpServletRequest request,
            @HeaderParam(CTRL_HEADER_PARAM) String ctrlUuidParam, @PathParam("deviceid") String deviceId,
            List<SbiNeIpSec> ipsecList) throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Ipsec delete begin time = " + beginTime);

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(CollectionUtils.isEmpty(ipsecList)) {
            LOGGER.error("Request body parameter is null or empty.");
            throw new ParameterServiceException("Request body parameter is null or empty.");
        }

        ValidationUtil.validateModel(ipsecList);
        CheckStrUtil.checkUuidStr(deviceId);

        List<String> externalIds = new ArrayList<>(CollectionUtils.collect(ipsecList, new Transformer() {

            @Override
            public Object transform(Object arg0) {
                return ((SbiNeIpSec)arg0).getExternalId();
            }
        }));

        ipsecService.checkSeqNumber(externalIds);
        ResultRsp<SbiNeIpSec> response = ipsecService.batchDeleteIpsecConn(ctrlUuid, ipsecList);
        LOGGER.debug("delete Ipsec cost time = " + (System.currentTimeMillis() - beginTime));
        return response;
    }

    /**
     * Queries IPSec VPN configuration using a specific controller.<br>
     *
     * @param request HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param ipSecNeConnectionList collection of IPSec VPN configuration
     * @return ResultRsp object with IPSec VPN queried configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/device/batch-query-ipsecs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNeIpSec> query(@Context HttpServletRequest request,
            @HeaderParam(CTRL_HEADER_PARAM) String ctrlUuidParam, List<SbiNeIpSec> ipSecNeConnectionList)
            throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Ipsec query begin time = " + beginTime);

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);
        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(CollectionUtils.isEmpty(ipSecNeConnectionList)) {
            LOGGER.error("Request body parameter is null or empty.");
            throw new ParameterServiceException("Request body parameter is null or empty.");
        }
        ValidationUtil.validateModel(ipSecNeConnectionList);

        ResultRsp<SbiNeIpSec> totalResult = new ResultRsp<SbiNeIpSec>(ErrorCode.OVERLAYVPN_SUCCESS);
        for(SbiNeIpSec neIpSec : ipSecNeConnectionList) {
            try {
                ResultRsp<List<IpsecConnList>> rsp = ipsecService.queryIpsecByDevice(ctrlUuid, neIpSec.getDeviceId(),
                        neIpSec.getSoureIfName());

                totalResult.setSuccessed(new ArrayList<SbiNeIpSec>());
                for(IpsecConnList ipsecModel : rsp.getData()) {
                    Set<String> seqNumSet = new HashSet<String>();
                    for(IpsecConnection ipsecCon : ipsecModel.getIpsecConnection())
                    {
                        seqNumSet.add(String.valueOf(ipsecCon.getSeqNumber()));
                    }

                    if(seqNumSet.contains(neIpSec.getExternalId()))
                    {
                        totalResult.getSuccessed().add(neIpSec);
                    }
                    else
                    {
                        FailData<SbiNeIpSec> failData = new FailData<SbiNeIpSec>(ErrorCode.OVERLAYVPN_FAILED, "can not find", neIpSec);
                        totalResult.getFail().add(failData);
                    }
                }
            } catch(ServiceException e) {
                LOGGER.error("query failed!", e);
                FailData<SbiNeIpSec> failData =
                        new FailData<SbiNeIpSec>(ErrorCode.OVERLAYVPN_FAILED, "query failed", neIpSec);
                totalResult.getFail().add(failData);
            }
        }
        LOGGER.debug("query Ipsec cost time = " + (System.currentTimeMillis() - beginTime));
        return totalResult;
    }

    /**
     * Updates IPSec VPN configuration using a specific controller.<br>
     *
     * @param request HTTP request
     * @param ctrlUuidParam Controller UUID
     * @param ipSecNeConnectionList collection of IPSec VPN configuration
     * @return ResultRsp object with IPSec VPN updated configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/device/batch-update-ipsecs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultRsp<SbiNeIpSec> ipsecUpdate(@Context HttpServletRequest request,
            @HeaderParam(CTRL_HEADER_PARAM) String ctrlUuidParam, List<SbiNeIpSec> ipSecNeConnectionList)
            throws ServiceException {

        long beginTime = System.currentTimeMillis();
        LOGGER.debug("Ipsec update begin time = " + beginTime);

        ResultRsp<SbiNeIpSec> totalResult = new ResultRsp<SbiNeIpSec>(ErrorCode.OVERLAYVPN_SUCCESS);
        totalResult.setSuccessed(new ArrayList<SbiNeIpSec>());
        totalResult.setFail(new ArrayList<FailData<SbiNeIpSec>>());

        String ctrlUuid = RequestHeaderUtil.readControllerUUID(ctrlUuidParam);

        if(!UuidUtil.validate(ctrlUuid)) {
            LOGGER.error("Invalid controller UUID.");
            throw new ParameterServiceException("Invalid controller UUID.");
        }

        if(CollectionUtils.isEmpty(ipSecNeConnectionList)) {
            LOGGER.error("Request body parameter is null or empty.");
            throw new ParameterServiceException("Request body parameter is null or empty.");
        }

        ValidationUtil.validateModel(ipSecNeConnectionList);

        for(SbiNeIpSec neIpSec : ipSecNeConnectionList)
        {
            ipsecService.update(ctrlUuid, neIpSec.getDeviceId(), neIpSec, totalResult);
        }

        LOGGER.info("AcBranch ipsec update cost time = " + (System.currentTimeMillis() - beginTime));

        return totalResult;
    }
}
