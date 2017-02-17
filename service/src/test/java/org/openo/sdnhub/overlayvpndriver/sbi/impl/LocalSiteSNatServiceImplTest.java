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
package org.openo.sdnhub.overlayvpndriver.sbi.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAclRule;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcSNat;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSnatNetModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class LocalSiteSNatServiceImplTest {
	LocalSiteSNatServiceImpl service = new LocalSiteSNatServiceImpl();
	AcSNat acSnat = new AcSNat();	
	
	@Test(expected=ServiceException.class)
	public void testCreateSnat_HTTPError() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(500);			
				return httpReturnMessage;
			}
		};		
		service.createSNat(null, "123", "extSysID=ctrlid123");
	}
	
	@Test(expected=ServiceException.class)
	public void testCreateSnat_HTTPError_EmptyBody() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(200);

				return httpReturnMessage;
			}
		};		
		service.createSNat(null, "123", "extSysID=ctrlid123");
	}
	
	@Test(expected=ServiceException.class)
	public void testUpdateSNat_HTTPError() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(500);			
				return httpReturnMessage;
			}
		};
		acSnat.setId("2467a068795b41ee9676bc79168da7a6");
		service.updateSNat(acSnat, "extSysID=ctrlid123", "device123");
	}
	
	@Test(expected=ServiceException.class)
	public void testUpdateSNat_HTTPError_EmptyBody() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(200);

				return httpReturnMessage;
			}
		};	
		acSnat.setId("2467a068795b41ee9676bc79168da7a6");
		service.updateSNat(acSnat, "extSysID=ctrlid123", "123");
	}
	
	@Test(expected=ServiceException.class)
	public void testQuerySnat_HTTPError() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(500);			
				return httpReturnMessage;
			}
		};		
		service.querySNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
	}
	
	@Test(expected=ServiceException.class)
	public void testQuerySnat_HTTPError_EmptyBody() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(200);

				return httpReturnMessage;
			}
		};		
		service.querySNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
	}
	
	@Test(expected=ServiceException.class)
	public void testDeleteSNat_HTTPError() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(500);			
				return httpReturnMessage;
			}
		};		
		service.deleteSNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
	}
	
	@Test(expected=ServiceException.class)
	public void testDeleteSNat_HTTPError_EmptyBody() throws ServiceException {
		
		new MockUp<OverlayVpnDriverProxy>() {

			@Mock
			public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
				httpReturnMessage.setStatus(200);

				return httpReturnMessage;
			}
		};		
		service.deleteSNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
	}
}
