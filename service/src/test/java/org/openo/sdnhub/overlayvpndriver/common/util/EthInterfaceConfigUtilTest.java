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

package org.openo.sdnhub.overlayvpndriver.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.EthInterfaceConfig;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.ArrayList;
import java.util.List;

public class EthInterfaceConfigUtilTest {

    @Test
    public void parseResponse() throws ServiceException {
        ACResponse<List<EthInterfaceConfig>> response = new ACResponse<>();
        List<EthInterfaceConfig> list = new ArrayList<>();
        EthInterfaceConfig eth = new EthInterfaceConfig();
        eth.setDescription("des");
        list.add(eth);

        HTTPReturnMessage msg = new HTTPReturnMessage();
        response.setData(list);
        response.setErrcode("01");
        msg.setBody(JsonUtil.toJson(response));
        msg.setStatus(200);
        String actionDesc = "actionDesc";

        ResultRsp<List<EthInterfaceConfig>> rsp = EthInterfaceConfigUtil.parseResponse(msg, msg.getBody(), actionDesc);
        assertEquals("overlayvpn.operation.success", rsp.getErrorCode());
    }
}
