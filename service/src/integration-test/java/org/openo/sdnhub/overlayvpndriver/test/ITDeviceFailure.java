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

public class ITDeviceFailure extends TestManager {

    private static final String CREATE_DEVICE_FAIL_TESTCASE_1 =
            "src/integration-test/resources/testcase_jsons/create_device_failure1.json";

    private static final String CREATE_DEVICE_FAIL_TESTCASE_2 =
            "src/integration-test/resources/testcase_jsons/create_device_failure2.json";

    private static final String UPDATE_DEVICE_FAIL_TESTCASE_1 =
            "src/integration-test/resources/testcase_jsons/update_device_failure1.json";

    private static final String UPDATE_DEVICE_FAIL_TESTCASE_2 =
            "src/integration-test/resources/testcase_jsons/update_device_failure2.json";

    private static final String DELETE_DEVICE_FAIL_TESTCASE_1 =
            "src/integration-test/resources/testcase_jsons/delete_device_failure1.json";

    private static final String DELETE_DEVICE_FAIL_TESTCASE_2 =
            "src/integration-test/resources/testcase_jsons/delete_device_failure2.json";

    private static final String QUERY_DEVICE_FAIL_TESTCASE_1 =
            "src/integration-test/resources/testcase_jsons/query_device_failure1.json";

    private static final String QUERY_DEVICE_FAIL_TESTCASE_2 =
            "src/integration-test/resources/testcase_jsons/query_device_failure2.json";

    private static String[] mockJsonsController =
            new String[] {"src/integration-test/resources/controller_mock_jsons/device_create.json",
                    "src/integration-test/resources/controller_mock_jsons/device_update.json",
                    "src/integration-test/resources/controller_mock_jsons/device_query_fail.json",
                    "src/integration-test/resources/controller_mock_jsons/device_delete.json"};

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
    public void checkDeviceCreateFailure1() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(CREATE_DEVICE_FAIL_TESTCASE_1);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    @Test
    public void checkDeviceCreateFailure2() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(CREATE_DEVICE_FAIL_TESTCASE_2);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    @Test
    public void checkDeviceQueryFailure1() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(QUERY_DEVICE_FAIL_TESTCASE_1);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    @Test
    public void checkDeviceQueryFailure2() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(QUERY_DEVICE_FAIL_TESTCASE_2);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    @Test
    public void checkDeviceDeleteFailure1() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(DELETE_DEVICE_FAIL_TESTCASE_1);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    @Test
    public void checkDeviceDeleteFailure2() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(DELETE_DEVICE_FAIL_TESTCASE_2);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    @Test
    public void checkDeviceUpdateFailure1() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(UPDATE_DEVICE_FAIL_TESTCASE_1);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    @Test
    public void checkDeviceUpdateFailure2() throws ServiceException {
        HttpRquestResponse httpCreateObject =
                HttpModelUtils.praseHttpRquestResponseFromFile(UPDATE_DEVICE_FAIL_TESTCASE_2);
        HttpRequest createRequest = httpCreateObject.getRequest();
        execTestCase(createRequest, new FailureChecker());
    }

    private class FailureChecker implements IChecker {
        @Override
        public boolean check(HttpResponse response) {
            String responsedata = response.getData();
            if(response.getStatus() > 204  || responsedata.indexOf("cloudvpn.failed") > -1) {
                return true;
            }
            return false;
        }
    }
}
