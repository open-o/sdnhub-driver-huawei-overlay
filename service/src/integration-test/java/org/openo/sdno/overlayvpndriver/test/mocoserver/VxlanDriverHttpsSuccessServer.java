/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
 *   
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   
 *         http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openo.sdno.overlayvpndriver.test.mocoserver;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpndriver.login.OverlayVpnDriverResponse;
import org.openo.sdno.overlayvpndriver.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.MocoHttpsServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VxlanDriverHttpsSuccessServer extends MocoHttpsServer{
	private static final Logger LOGGER = LoggerFactory.getLogger(VxlanDriverHttpsSuccessServer.class);

    public VxlanDriverHttpsSuccessServer() {
        super();
    }
    
    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vxlanCreateSuccess.json",
                new VxLanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vxlanDeletesuccess.json",
                new VxLanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/queryVtepSuccess.json",
                new VxLanQuerySuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/ESRGetController.json",
                new MocoResponseHandler());
    }
    
    private class VxLanSuccessResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();
            OverlayVpnDriverResponse<List<NetVxLanDeviceModel>> inputInstanceList = JsonUtil.fromJson(httpResponse.getData(), new TypeReference<OverlayVpnDriverResponse<List<NetVxLanDeviceModel>>>() {});

            ResultRsp<OverlayVpnDriverResponse<List<NetVxLanDeviceModel>>> newResult = new ResultRsp<OverlayVpnDriverResponse<List<NetVxLanDeviceModel>>>(ErrorCode.OVERLAYVPN_SUCCESS);

            httpResponse.setStatus(200);
            newResult.setData(inputInstanceList);
            httpResponse.setData(JsonUtil.toJson(inputInstanceList));
        }
    }
    
    private class VxLanQuerySuccessResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();
            OverlayVpnDriverResponse<List<WanSubInterface>> inputInstanceList = JsonUtil.fromJson(httpResponse.getData(), new TypeReference<OverlayVpnDriverResponse<List<WanSubInterface>>>() {});

            ResultRsp<OverlayVpnDriverResponse<List<WanSubInterface>>> newResult = new ResultRsp<OverlayVpnDriverResponse<List<WanSubInterface>>>(ErrorCode.OVERLAYVPN_SUCCESS);

            httpResponse.setStatus(200);
            newResult.setData(inputInstanceList);
            httpResponse.setData(JsonUtil.toJson(inputInstanceList));
        }
    }

}
