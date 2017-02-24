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

package org.openo.sdnhub.overlayvpndriver.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.test.mocoserver.GenericMockServer;
import org.openo.sdnhub.overlayvpndriver.test.mocoserver.GenericMockServerHttps;
import org.openo.sdno.testframework.checker.IChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;

public class ITInterfaceIpSuccess extends TestManager{

    private static final String UPDATE_INTERFACEIP_SUCCESS_TESTCASE =
            "src/integration-test/resources/testcase_jsons/update_interfaceip_success.json";

    private static final String QUERY_INTERFACEIP_SUCCESS_TESTCASE =
            "src/integration-test/resources/testcase_jsons/query_interfaceip_success.json";

    private static String[] mockJsonsController =
            new String[] {"src/integration-test/resources/controller_mock_jsons/interfaceip_update.json",
                    "src/integration-test/resources/controller_mock_jsons/interfaceip_query.json"};

    private static String[] mockJsonsEsrs =
            new String[] {"src/integration-test/resources/esr_mock_jsons/esr_controller.json"};

    private static String[] mockJsonsBrs =
            new String[]{"src/integration-test/resources/brs_mock_jsons/getCommParams.json"};

    private static GenericMockServer mocoServer = new GenericMockServer();
    private static GenericMockServerHttps mocoServerHttps = new GenericMockServerHttps();

    @BeforeClass
    public static void setup() throws ServiceException {
        mocoServer.addMockJsons(mockJsonsEsrs);
        mocoServer.addMockJsons(mockJsonsBrs);
        mocoServerHttps.addMockJsons(mockJsonsController);

        mocoServer.start();
        mocoServerHttps.start();
    }

    @AfterClass
    public static void tearDown() throws ServiceException {
        mocoServer.stop();
        mocoServerHttps.stop();
    }

    @Test
    public void interfaceIpQuerySuccess() throws ServiceException{
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(QUERY_INTERFACEIP_SUCCESS_TESTCASE);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new SuccessChecker());
    }

    @Test
    public void interfaceIpUpdateSuccess() throws ServiceException{
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(UPDATE_INTERFACEIP_SUCCESS_TESTCASE);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new SuccessChecker());
    }

    private class SuccessChecker implements IChecker {

        @Override
        public boolean check(HttpResponse response) {
            if(response.getStatus() >= 200 && response.getStatus() <= 204) {
                return true;
            }
            return false;
        }
    }
}
