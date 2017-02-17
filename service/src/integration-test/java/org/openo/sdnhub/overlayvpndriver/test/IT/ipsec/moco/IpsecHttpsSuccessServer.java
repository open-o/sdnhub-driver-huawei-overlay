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

public class IpsecHttpsSuccessServer extends MocoHttpsServer{
    public IpsecHttpsSuccessServer() {
        super();
    }

    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/createsuccess.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/querysuccess.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/deletenqasuccess.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/updatesuccess.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/ipsec/moco/deletesuccess.json",
                new MocoResponseHandler());
    }

}
