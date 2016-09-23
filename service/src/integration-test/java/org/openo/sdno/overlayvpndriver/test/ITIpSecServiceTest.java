
package org.openo.sdno.overlayvpndriver.test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.overlayvpndriver.test.mocoserver.IpSecDriverHttpsServiceServer;
import org.openo.sdno.overlayvpndriver.test.mocoserver.IpSecDriverServiceSuccessServer;
import org.openo.sdno.testframework.checker.IChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

public class ITIpSecServiceTest extends TestManager {

    private IpSecDriverServiceSuccessServer ipsecServer = new IpSecDriverServiceSuccessServer();
    private IpSecDriverHttpsServiceServer httpsServer = new IpSecDriverHttpsServiceServer();

    public ITIpSecServiceTest() {
        super();
    }
    @Before
    public void setup() throws ServiceException {
        ipsecServer.start();
        httpsServer.start();
    }

    @After
    public void tearDown() {
        ipsecServer.stop();
        httpsServer.stop();
    }

    @Test
    public void test_create() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/createIpSec.json");
        HttpRquestResponse createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = execTestCase(createFile, new SuccessChecker());

    }

    @Test
    public void test_delete() throws ServiceException {
        File createFile = new File("src/integration-test/resources/overlayvpndriver/deleteIpSec.json");
        HttpRquestResponse createHttpObject =
                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
        HttpResponse createResponse = execTestCase(createFile, new SuccessChecker());
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
