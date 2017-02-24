package org.openo.sdnhub.overlayvpndriver.test.IT.ipsec;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.test.IT.ipsec.moco.IpsecHttpsFailServer;
import org.openo.sdnhub.overlayvpndriver.test.IT.ipsec.moco.IpsecHttpsSuccessServer;
import org.openo.sdnhub.overlayvpndriver.test.IT.ipsec.moco.IpsecSuccessServer;
import org.openo.sdno.testframework.checker.IChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

public class ITIPSecServiceFailCases {

    IpsecHttpsFailServer mocoHttpsServer = new IpsecHttpsFailServer();
    IpsecSuccessServer httpServer = new IpsecSuccessServer();

    @Before
    public void setup() throws ServiceException {
        httpServer.start();
        mocoHttpsServer.start();
    }

    @After
    public void tearDown() throws ServiceException {
        httpServer.stop();
        mocoHttpsServer.stop();
    }

    @Test
    public void testIpsecCreate() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/createInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testIpsecQuery() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/queryInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testIpsecDelete() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/deleteInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testIpsecUpdate() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/updateInvalidIp.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new FailChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testIpsecCreate_Fail() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/create.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testIpsecQuery_Fail() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/query.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testIpsecDelete_Fail() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/delete.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testIpsecUpdate_Fail() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/ipsec/update.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
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
