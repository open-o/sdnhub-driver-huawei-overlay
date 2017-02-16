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

import java.util.List;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.EthInterfaceConfigUtil;
import org.openo.sdnhub.overlayvpndriver.controller.model.EthInterfaceConfig;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.result.SvcExcptUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public final class EthInterfaceConfigImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthInterfaceConfigImpl.class);

    private EthInterfaceConfigImpl() {

    }

    public static ResultRsp<List<EthInterfaceConfig>> queryEthConfig(final String cltuuid, final String queryurl)
            throws ServiceException {

        final long beginTime = System.currentTimeMillis();
        LOGGER.debug("query eth config, begin time =" + beginTime);
        final HTTPReturnMessage queryRsp = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryurl, null, cltuuid);

        LOGGER.debug("query eth config, cost time = " + (System.currentTimeMillis() - beginTime));
        LOGGER.debug("body:{}", queryRsp);

        final String queryRetBody = queryRsp.getBody();
        final String actionDesc = "query eth config";

        return EthInterfaceConfigUtil.parseResponse(queryRsp, queryRetBody, actionDesc);
    }

    public static ResultRsp<List<EthInterfaceConfig>> configEthInterface(final String cltuuid, final String configUrl,
            final String ethListJson) throws ServiceException {
        if(!StringUtils.hasLength(cltuuid)) {
            LOGGER.error("config eth : parameter error.");
            SvcExcptUtil.throwBadRequestException("config eth : parameter error.");
        }

        final long beginTime = System.currentTimeMillis();
        LOGGER.debug("config eth, begin time =" + beginTime);

        final HTTPReturnMessage configRsp =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(configUrl, ethListJson, cltuuid);
        LOGGER.debug("config eth , cost time = " + (System.currentTimeMillis() - beginTime));
        LOGGER.debug("body:{}", configRsp);

        final String retBody = configRsp.getBody();
        final String actionDesc = "config eth";

        return EthInterfaceConfigUtil.parseResponse(configRsp, retBody, actionDesc);
    }
}
