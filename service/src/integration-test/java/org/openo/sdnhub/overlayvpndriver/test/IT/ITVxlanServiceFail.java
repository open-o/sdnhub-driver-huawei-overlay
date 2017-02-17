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

package org.openo.sdnhub.overlayvpndriver.test.IT;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.test.mocoserver.VxlanDriverHttpsFailServer;
import org.openo.sdnhub.overlayvpndriver.test.mocoserver.VxlanDriverServiceFailServer;
import org.openo.sdno.testframework.checker.IChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

public class ITVxlanServiceFail {



    VxlanDriverServiceFailServer failServer = new VxlanDriverServiceFailServer();
    VxlanDriverHttpsFailServer failServerHttps = new VxlanDriverHttpsFailServer();

    @Before
    public void setup() throws ServiceException {
        failServerHttps.start();
        failServer.start();
    }

    @After
    public void tearDown() throws ServiceException {
        failServerHttps.stop();
        failServer.stop();
    }

    @Test
    public void testCreateFail() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/createvxlanfail.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testCreateInvalidIp() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/createvxlanFailInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testUpdateFail() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/updatevxlan.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testUpdateFail_invalidIp() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/updatevxlanFailInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testQuery() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/queryvxlanFail.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testDelete() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/deletevxlanFail.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testDelete_invalidIp() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/deletevxlanFailInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testDelete_invlidIp() throws ServiceException {

        File createFile = new File("src/integration-test/resources/overlayvpndriver/deletevxlanFailInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    private class SuccessChecker implements IChecker {

        @Override
        public boolean check(HttpResponse response) {
            if (response.getStatus() >= 200 && response.getStatus() <= 204) {
                return true;
            }
            return false;
        }
    }

    private class FailChecker implements IChecker {

        @Override
        public boolean check(HttpResponse response) {
            if (response.getStatus() == 500) {
                return true;
            }
            return false;
        }
    }



}
