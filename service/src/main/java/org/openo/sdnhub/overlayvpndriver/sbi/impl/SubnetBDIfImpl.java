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
import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACBDInfo;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSubnetBdInfoModel;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Subnet Vni-related BD service implementation.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jan 16, 2017
 */
public class SubnetBDIfImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubnetBDIfImpl.class);

    /**
     * Queries the Vni-related BD information using a specific Controller.<br>
     *
     * @param model Vni-related BD information
     * @param ctrlId controller UUID
     * @param deviceId device id
     * @return ResultRsp object with Vni-related BD queried information status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<SbiSubnetBdInfoModel> queryBDInfo(SbiSubnetBdInfoModel model, String ctrlId, String deviceId)
            throws ServiceException {

        if(!StringUtils.hasLength(ctrlId) || !StringUtils.hasLength(deviceId) || null == model.getVni()) {
            LOGGER.error("Invalid parameters.");
            throw new ParameterServiceException("Invalid parameters.");
        }
        String queryUrl =
                MessageFormat.format(ControllerUrlConst.SUBNET_BDINFO_CONTROLLER_URL, deviceId, model.getVni());
        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlId);
        String body = httpMsg.getBody();
        LOGGER.debug("BDInfo query. return body={}", JsonUtil.toJson(httpMsg));

        if((!httpMsg.isSuccess()) || (!StringUtils.hasLength(body))) {
            LOGGER.error("BDInfo query : error ");
            throw new ServiceException(DriverErrorCode.ADAPTER_FAILED, "AcBranch BDInfo query.  httpMsg return error.");
        }
        ACResponse<List<ACBDInfo>> acResponse =
                JsonUtil.fromJson(body, new TypeReference<ACResponse<List<ACBDInfo>>>() {
                });
        if(!acResponse.isSucceed()) {
            LOGGER.error("BDInfo query :ac response return error :" + acResponse.getErrmsg());
            throw new ServiceException(DriverErrorCode.ADAPTER_FAILED, acResponse.getErrmsg());
        }
        if(null == acResponse.getData() || acResponse.getData().isEmpty()) {
            LOGGER.error("BDInfo query : response is empty");
            throw new ServiceException(DriverErrorCode.ADAPTER_FAILED, "response is empty");
        }
        ACBDInfo bdInfo = acResponse.getData().get(0);
        model.setBdId(bdInfo.getBdId());
        model.setVbdifName(bdInfo.getVbdifName());
        return new ResultRsp<SbiSubnetBdInfoModel>(ErrorCode.OVERLAYVPN_SUCCESS, model);
    }
}
