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

package org.openo.sdnhub.overlayvpndriver.translator;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.ControllerNbiNqa;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.model.v2.route.SbiNqa;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Converter class for Nqa.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class NqaConvert {

    private static final Logger LOGGER = LoggerFactory.getLogger(NqaConvert.class);

    private NqaConvert() {

    }

    /**
     * This method will parse the HttpMessage to create ResultRsp.<br/>
     *
     * @param httpMsg
     * @param actionDesc
     * @return
     * @since SDNHUB 0.5
     */
    public static ResultRsp<SbiNqa> parseResponse(HTTPReturnMessage httpMsg, String actionDesc) {
        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(httpMsg.getBody())) {
            OverlayVpnDriverResponse<List<SbiNqa>> acresponse =
                    JsonUtil.fromJson(httpMsg.getBody(), new TypeReference<OverlayVpnDriverResponse<List<SbiNqa>>>() {});
            if(acresponse.isSucess()) {
                ResultRsp<SbiNqa> rsp = new ResultRsp<SbiNqa>();
                rsp.setSuccessed(acresponse.getData());
                return rsp;
            }

            LOGGER.error("createIpSecByDevice: asresponse return error");
            return new ResultRsp<>(DriverErrorCode.ADAPTER_GRE_CREATE_IPSEC_ERROR + acresponse.getErrmsg());
        }

        if(StringUtils.isNotEmpty(httpMsg.getBody())) {
            final Map<String, String> errorMap =
                    JsonUtil.fromJson(httpMsg.getBody(), new TypeReference<Map<String, String>>() {});
            return new ResultRsp<SbiNqa>(errorMap.get("errcode") + errorMap.get("errmsg"));
        }

        LOGGER.error(actionDesc + ": parser msg to ACResponse error, msg : " + httpMsg.getBody());

        return new ResultRsp<SbiNqa>(DriverErrorCode.CLOUDVPN_FAILED);

    }

    /**
     * This method parses the response for result response.<br/>
     *
     * @param httpMsg
     * @param actionDesc
     * @return
     * @since SDNHUB 0.5
     */
    public static ResultRsp<String> parseDeleteResponse(HTTPReturnMessage httpMsg, String actionDesc) {
        final ResultRsp<String> resultRsp = new ResultRsp<>(DriverErrorCode.CLOUDVPN_FAILED);
        if(httpMsg.isSuccess() && StringUtils.isNotEmpty(httpMsg.getBody())) {
            ACDelResponse acresponse = JsonUtil.fromJson(httpMsg.getBody(), new TypeReference<ACDelResponse>() {});
            if(!acresponse.isSucess()) {

                LOGGER.error(actionDesc + ": " + "response return error, eerMsg : " + acresponse.getAllErrmsg());
                resultRsp.setErrorCode(DriverErrorCode.CLOUDVPN_FAILED);
                resultRsp.setMessage(acresponse.getAllErrmsg());
            }

            return resultRsp;

        }

        LOGGER.error(actionDesc + "parse msg to  ACDelResponse error, msg: " + httpMsg.getBody());
        resultRsp.setErrorCode(DriverErrorCode.CLOUDVPN_FAILED);
        resultRsp.setMessage(actionDesc);

        return resultRsp;
    }

    /**
     * This method converts SbiNqa to ControllerNbiNqa.<br/>
     *
     * @param sbiNqaIdList
     * @return
     * @since SDNO 0.5
     */
    public static List<ControllerNbiNqa> convert2Sbi(List<SbiNqa> sbiNqaIdList) {
        List<ControllerNbiNqa> controllerNbiNqaList = new ArrayList<>();
        if(sbiNqaIdList.isEmpty()) {
            return controllerNbiNqaList;
        }

        for(final SbiNqa sbiNqa : sbiNqaIdList) {
            ControllerNbiNqa controllerNbiNqa = new ControllerNbiNqa();
            controllerNbiNqa.setId(sbiNqa.getExternalId());
            controllerNbiNqa.setSrcIp(sbiNqa.getSrcIp());
            controllerNbiNqa.setSrcPortName(sbiNqa.getSrcPortName());
            controllerNbiNqa.setDstIp(sbiNqa.getDstIp());
            controllerNbiNqa.setDstPortName(sbiNqa.getDstPortName());
            controllerNbiNqa.setTestType(sbiNqa.getTestType());
            controllerNbiNqa.setTimeout(sbiNqa.getTimeout());
            controllerNbiNqa.setTtl(sbiNqa.getTtl());
            controllerNbiNqa.setTos(sbiNqa.getTos());
            controllerNbiNqa.setFrequency(sbiNqa.getFrequency());
            controllerNbiNqa.setProbeCount(sbiNqa.getProbeCount());
            controllerNbiNqa.setInterval(sbiNqa.getInterval());
            controllerNbiNqaList.add(controllerNbiNqa);

        }
        return controllerNbiNqaList;
    }

}
