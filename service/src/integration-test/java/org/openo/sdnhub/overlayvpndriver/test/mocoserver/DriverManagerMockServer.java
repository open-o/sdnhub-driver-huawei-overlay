/*******************************************************************************
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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
 *******************************************************************************/

package org.openo.sdnhub.overlayvpndriver.test.mocoserver;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class DriverManagerMockServer extends MocoHttpServer{
    
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/DMregistration.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/DMUnregistration.json",
                new MocoResponseHandler());
    }
    
    public static void main(String[] args) {
        DriverManagerMockServer dmMock = new DriverManagerMockServer();
        try {
            dmMock.start();
        } catch(ServiceException e) {
            e.printStackTrace();
        }
    }

}
