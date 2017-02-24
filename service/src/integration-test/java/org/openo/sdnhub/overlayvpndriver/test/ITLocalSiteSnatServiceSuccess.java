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




import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.test.mocoserver.LocalSNatMockServerHttps;
import org.openo.sdnhub.overlayvpndriver.test.mocoserver.LocalSNatMockServerSuccess;
import org.openo.sdno.testframework.checker.IChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

/**
 *
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version     SDNHUB 0.5  Feb 19, 2017
 */
public class ITLocalSiteSnatServiceSuccess {

    private LocalSNatMockServerSuccess subnetMockServer = new LocalSNatMockServerSuccess();

    private LocalSNatMockServerHttps subnetMockServerHttps = new LocalSNatMockServerHttps();

    @Before
    public void setup() throws ServiceException {
        subnetMockServer.start();
        subnetMockServerHttps.start();
    }

    @After
    public void tearDown() throws ServiceException {
        subnetMockServer.stop();
        subnetMockServerHttps.stop();
    }

    @Test
    public void test_create() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/createLocalSnat.json");
        HttpRquestResponse createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void test_delete() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/deleteLocalSnat.json");
        HttpRquestResponse createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void test_update() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/updateLocalSnat.json");
        HttpRquestResponse createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void test_query() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/queryLocalSnat.json");
        HttpRquestResponse createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
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
