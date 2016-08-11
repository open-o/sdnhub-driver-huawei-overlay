package org.openo.sdno.acbranchservice.test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.test.mocoserver.VxlanDriverServiceSuccessServer;
import org.openo.sdno.testframework.checker.RegularExpChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

public class VxlanserviceSuccessTest extends TestManager{
	
	private VxlanDriverServiceSuccessServer vxlanServer = new VxlanDriverServiceSuccessServer();
	
	@Before
	public void setup() throws ServiceException {
		vxlanServer.start();
	}

	@After
	public void tearDown() {
		vxlanServer.stop();
	}

	@Test
	public void test_create() throws ServiceException {
		File createFile = new File("src/integration-test/resources/ACBranchDriver/create.json");
	    HttpRquestResponse createHttpObject =
	            HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
	    HttpResponse createResponse = execTestCase(createFile,
	            new RegularExpChecker(createHttpObject.getResponse()));
	    String response = createResponse.getData();
	}
	
	@Test
	public void test_delete() throws ServiceException {
		File createFile = new File("src/integration-test/resources/ACBranchDriver/delete.json");
	    HttpRquestResponse createHttpObject =
	            HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
	    HttpResponse createResponse = execTestCase(createFile,
	            new RegularExpChecker(createHttpObject.getResponse()));
	    String response = createResponse.getData();
	}
	
	@Test
	public void test_query() throws ServiceException {
		File createFile = new File("src/integration-test/resources/ACBranchDriver/query.json");
	    HttpRquestResponse createHttpObject =
	            HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
	    HttpResponse createResponse = execTestCase(createFile,
	            new RegularExpChecker(createHttpObject.getResponse()));
	    String response = createResponse.getData();
	}
}
