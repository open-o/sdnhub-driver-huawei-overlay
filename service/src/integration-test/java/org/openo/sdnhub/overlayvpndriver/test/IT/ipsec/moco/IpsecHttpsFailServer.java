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
package org.openo.sdnhub.overlayvpndriver.test.IT.ipsec.moco;

import org.openo.sdno.testframework.moco.MocoHttpsServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class IpsecHttpsFailServer extends MocoHttpsServer{
    public IpsecHttpsFailServer() {
        super();
    }
    
    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/createfail.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/queryfail.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/deletenqasuccess.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/updatefail.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/deletefail.json",
                new MocoResponseHandler());
    }
    
}
