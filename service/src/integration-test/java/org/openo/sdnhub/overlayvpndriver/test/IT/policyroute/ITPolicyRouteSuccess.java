package org.openo.sdnhub.overlayvpndriver.test.IT.policyroute;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.test.IT.policyroute.moco.PolicyRouteHttpsSuccessServer;
import org.openo.sdnhub.overlayvpndriver.test.IT.policyroute.moco.PolicyRouteSuccessServer;
import org.openo.sdno.testframework.checker.IChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

public class ITPolicyRouteSuccess {

    private PolicyRouteHttpsSuccessServer mocoHttpsServer = new PolicyRouteHttpsSuccessServer();
    private PolicyRouteSuccessServer httpServer = new PolicyRouteSuccessServer();

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
    public void testCreate() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/policyroute/create.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testUpdate() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/policyroute/update.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testQuery() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/policyroute/query.json");
        HttpRquestResponse createHttpObject = HttpModelUtils
                .praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = new TestManager().execTestCase(createFile, new SuccessChecker());
        String response = createResponse.getData();
    }

    @Test
    public void testDelete() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/policyroute/delete.json");
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
}
