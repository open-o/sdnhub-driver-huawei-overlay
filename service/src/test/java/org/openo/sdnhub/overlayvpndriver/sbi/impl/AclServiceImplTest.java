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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.service.model.ACResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class AclServiceImplTest {
	
	String queryResJson =
            "{\"errcode\":\"0\",\"errmsg\":null,\"pageIndex\":0,\"pageSize\":0,\"totalRecords\":0,\"data\":{\"ipv4\":\"192.168.1.2\",\"ipv6\":\"\",\"ipMask\":\"\",\"prefixLength\":\"\",\"id\":\"\"},\"success\":[],\"fail\":[],\"sucess\":true}";
	
	@Test(expected = ServiceException.class)
	public void createAclCtrlUuidNull() throws ServiceException {
		String sDeviceId="deviceId";
		AclServiceImpl impl=new AclServiceImpl();
		impl.createAcl(null,null,sDeviceId);
	}
	
	@Test(expected = ServiceException.class)
	public void createAclDeviceNull() throws ServiceException {
		String sCtrlUuid="CtrlUuid";
		AclServiceImpl impl=new AclServiceImpl();
		impl.createAcl(null,sCtrlUuid,null);
	}
	
	@Test(expected = ServiceException.class)
	public void deleteAclCtrlUuidNull() throws ServiceException {
		String sDeviceId="deviceId";
		AclServiceImpl impl=new AclServiceImpl();
		impl.deleteAcl(null,null,sDeviceId);
	}
	
	@Test(expected = ServiceException.class)
	public void deleteAclDeviceNull() throws ServiceException {
		String sCtrlUuid="CtrlUuid";
		AclServiceImpl impl=new AclServiceImpl();
		impl.deleteAcl(null,sCtrlUuid,null);
	}
	
	@Test
	public void queryAcl() throws ServiceException{
		
		new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
            	
            	ACResponse<List<AcAcl>> response=new ACResponse<>();
            	List<AcAcl> list=new ArrayList();
            	AcAcl acl=new AcAcl();
            	acl.setAclId("123");
            	list.add(acl);
            	
                HTTPReturnMessage msg = new HTTPReturnMessage();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };
        
		
		AclServiceImpl impl=new AclServiceImpl();
		String sAclId="aclId";
		String sCtrlUuid="CtrlUuid";
		String sDeviceId="deviceId";
		ResultRsp<AcAcl> rsp=impl.queryAcl(sAclId,sCtrlUuid,sDeviceId);
	
		assertEquals("overlayvpn.operation.failed",rsp.getErrorCode());
	}
	
	@Test
	public void updateAcl() throws ServiceException{
		
		new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
            	
            	ACResponse<List<AcAcl>> response=new ACResponse<>();
            	List<AcAcl> list=new ArrayList();
            	AcAcl acl=new AcAcl();
            	acl.setAclId("123");
            	list.add(acl);
            	
                HTTPReturnMessage msg = new HTTPReturnMessage();
                response.setData(list);
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                msg.setStatus(200);
                return msg;
            }
        };
        
		
		AclServiceImpl impl=new AclServiceImpl();
		String sAclId="aclId";
		String sCtrlUuid="CtrlUuid";
		String sDeviceId="deviceId";
		AcAcl acl=new AcAcl();
		acl.setAclName("name");
		ResultRsp<AcAcl> rsp=impl.updateAcl(acl,sCtrlUuid,sDeviceId);
		assertEquals("overlayvpn.operation.success",rsp.getErrorCode());
	}
	
	
	@Test(expected = ServiceException.class)
	public void queryAclCtrlNull() throws ServiceException{
		
		AclServiceImpl impl=new AclServiceImpl();
		
		String sDeviceId="deviceId";
		ResultRsp<AcAcl> rsp=impl.queryAcl(null,null,sDeviceId);
	}
	
	@Test(expected = ServiceException.class)
	public void queryAclDeviceNull() throws ServiceException{
		
		AclServiceImpl impl=new AclServiceImpl();
		
		String sCtrlUuid="CtrlUuid";
		ResultRsp<AcAcl> rsp=impl.queryAcl(null,sCtrlUuid,null);
	}
}
