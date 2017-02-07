/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.test.mocoserver.VxlanDriverServiceFailServer;
import org.openo.sdno.testframework.checker.RegularExpChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

import net.sf.json.JSONObject;

public class VxlanDriverServiceFail extends TestManager {

    private VxlanDriverServiceFailServer vxlanServer = new VxlanDriverServiceFailServer();

    @Before
    public void setup() throws ServiceException {
        vxlanServer.start();
    }

    @After
    public void tearDown() {
        vxlanServer.stop();
    }

    @Test
    public void test() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/createFail.json");
        HttpRquestResponse createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = execTestCase(createFile, new RegularExpChecker2(createHttpObject.getResponse()));
        String response = createResponse.getData();

        createFile = new File("src/integration-test/resources/overlayvpndriver/deleteFail.json");
        createHttpObject = HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        createResponse = execTestCase(createFile, new RegularExpChecker2(createHttpObject.getResponse()));
        response = createResponse.getData();

        createFile = new File("src/integration-test/resources/overlayvpndriver/queryFail.json");
        createHttpObject = HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        createResponse = execTestCase(createFile, new RegularExpChecker2(createHttpObject.getResponse()));
        response = createResponse.getData();
        
        createFile = new File("src/integration-test/resources/overlayvpndriver/createFailInvalidIp.json");
        createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        createResponse = execTestCase(createFile, new RegularExpChecker2(createHttpObject.getResponse()));
        response = createResponse.getData();
    }

    // created this class for a workaround as expectedResponse.getData() produce result "null"
    public class RegularExpChecker2 extends RegularExpChecker {

        private HttpResponse expectedResponse;

        public RegularExpChecker2(HttpResponse response) {
            super(response);
            expectedResponse = response;

        }

        // Regular expression checker
        @Override
        public boolean check(HttpResponse response) {
            if(response.getStatus() != expectedResponse.getStatus()) {
                return false;
            }

            // If expected response is null -- no need to match anything, only check status
            if("null".equals(expectedResponse.getData())) {
                return true;
            }

            // Something is expected but nothing came, some problem, test case failed
            if(null == response.getData()) {
                return false;
            }

            return new RegularExpChecker(response).check(JSONObject.fromObject(expectedResponse.getData()),
                    JSONObject.fromObject(response.getData()));
        }
    }
}
