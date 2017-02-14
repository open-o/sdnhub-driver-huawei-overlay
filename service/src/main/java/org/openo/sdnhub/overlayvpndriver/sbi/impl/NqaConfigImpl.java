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

import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.translator.NqaConvert;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNqa;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is implementation for NQA operation.<br>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class NqaConfigImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(NqaConfigImpl.class);

    /**
     * NqaConfig query implementation.<br/>
     *
     * @param cltuuid Controller UUID
     * @param queryurl NQA query URL for controller
     * @return Result for query operation
     * @throws ServiceException In case of query operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<SbiNqa> queryNqaConfig(String cltuuid, String queryurl) throws ServiceException {
        if(StringUtils.isEmpty(cltuuid)) {
            LOGGER.error("queryNqaConfig, parameter error");
            throw new ParameterServiceException("invalid controller UUID.");
        }

        final long beginTime = System.currentTimeMillis();
        LOGGER.info("queryNqaConfig, nqa config begin time = " + beginTime);

        final HTTPReturnMessage queryRsp = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryurl, null, cltuuid);

        LOGGER.info("query nqa config cost time = " + (System.currentTimeMillis() - beginTime));
        LOGGER.info("body:{}", queryRsp);

        final String actionDesc = "query nqa config";

        return NqaConvert.parseResponse(queryRsp, actionDesc);
    }

    /**
     * NqaConfig create implementation.<br/>
     *
     * @param cltuuid Controller UUID
     * @param createUrl NQA create URL for controller
     * @param nqaListJson List of NQA to be created
     * @return Result for create operation
     * @throws ServiceException In case of create operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<SbiNqa> createNqaConfig(String cltuuid, String createUrl, String nqaListJson)
            throws ServiceException {
        if(StringUtils.isEmpty(cltuuid)) {
            LOGGER.error("createNqaConfig, parameter error");
            throw new ParameterServiceException("invalid controller UUID.");
        }

        final long beginTime = System.currentTimeMillis();
        LOGGER.info("createNqaConfig, nqa config begin time = " + beginTime);

        final HTTPReturnMessage createRsp =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(createUrl, nqaListJson, cltuuid);

        LOGGER.info("query nqa config cost time = " + (System.currentTimeMillis() - beginTime));
        LOGGER.info("body:{}", createRsp);

        final String actionDesc = "create nqa config";

        return NqaConvert.parseResponse(createRsp, actionDesc);
    }

    /**
     * NqaConfig update implementation.<br/>
     *
     * @param cltuuid Controller UUID
     * @param updateUrl NQA update URL for controller
     * @param nqaListJson List of NQA to be updated
     * @return Result for update operation
     * @throws ServiceException In case of update operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<SbiNqa> updateNqaConfig(String cltuuid, String updateUrl, String nqaListJson)
            throws ServiceException {
        if(StringUtils.isEmpty(cltuuid)) {
            LOGGER.error("updateNqaConfig, parameter error");
            throw new ParameterServiceException("invalid controller UUID.");
        }

        final long beginTime = System.currentTimeMillis();
        LOGGER.info("updateNqaConfig, nqa config begin time = " + beginTime);

        final HTTPReturnMessage updateRsp =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(updateUrl, nqaListJson, cltuuid);

        LOGGER.info("update nqa config cost time = " + (System.currentTimeMillis() - beginTime));
        LOGGER.info("body:{}", updateRsp);

        final String actionDesc = "update nqa config";

        return NqaConvert.parseResponse(updateRsp, actionDesc);
    }

    /**
     * NqaConfig delete implementation.<br/>
     *
     * @param cltuuid Controller UUID
     * @param deleteUrl NQA delete URL for controller
     * @param nqaListJson List of NQA to be deleted
     * @return Result for delete operation
     * @throws ServiceException In case of delete operation fails
     * @since SDNHUB 0.5
     */
    public ResultRsp<String> deleteNqaConfig(String cltuuid, String deleteUrl, String nqaListJson)
            throws ServiceException {
        if(StringUtils.isEmpty(cltuuid)) {
            LOGGER.error("deleteNqaConfig, parameter error");
            throw new ParameterServiceException("invalid controller UUID.");
        }

        final long beginTime = System.currentTimeMillis();
        LOGGER.info("deleteNqaConfig, nqa config delete begin time = " + beginTime);

        final HTTPReturnMessage deleteRsp =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(deleteUrl, nqaListJson, cltuuid);

        LOGGER.info("delete nqa config cost time = " + (System.currentTimeMillis() - beginTime));
        LOGGER.info("body:{}", deleteRsp);

        final String actionDesc = "update nqa config";

        return NqaConvert.parseDeleteResponse(deleteRsp, actionDesc);
    }
}
