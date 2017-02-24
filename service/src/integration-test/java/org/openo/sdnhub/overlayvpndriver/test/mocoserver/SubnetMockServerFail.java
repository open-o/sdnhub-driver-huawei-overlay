/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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
package org.openo.sdnhub.overlayvpndriver.test.mocoserver;

import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class SubnetMockServerFail extends MocoHttpServer {


    public SubnetMockServerFail() {
        super();
    }

    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/subnetCreateFail.json",
                new SubnetSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/subnetDeleteFail.json",
                new SubnetSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/subnetUpdateFail.json",
                new SubnetSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/subnetQueryFail.json",
                new SubnetSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/ESRGetController.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/getCommParams.json",
                new MocoResponseHandler());

    }

    private class SubnetSuccessResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();
            //ACResponse<ACNetwork> inputInstanceList = JsonUtil.fromJson(httpResponse.getData(), new TypeReference<ACResponse<ACNetwork>>() {});

            //ResultRsp<ACResponse<ACNetwork>> newResult = new ResultRsp<ACResponse<ACNetwork>>(ErrorCode.OVERLAYVPN_SUCCESS);

            httpResponse.setStatus(200);
            //newResult.setData(inputInstanceList);
            //httpResponse.setData(JsonUtil.toJson(inputInstanceList));
        }
    }

}
