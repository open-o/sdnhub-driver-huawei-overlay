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

import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIp;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class PortServiceImplTest {

	String queryResJson = "{\"errcode\":\"0\",\"errmsg\":null,\"pageIndex\":0,\"pageSize\":0,\"totalRecords\":0,\"data\":{\"ipv4\":\"192.168.1.2\",\"ipv6\":\"\",\"ipMask\":\"\",\"prefixLength\":\"\",\"id\":\"\"},\"success\":[],\"fail\":[],\"sucess\":true}";

	@Test
	public void queryPorts() throws ServiceException {

		PortServiceImpl portServiceImpl = new PortServiceImpl();
		String deviceId = "device";
		String ctrlUuid = "ctrlUuid";

		new MockUp<OverlayVpnDriverProxy>() {
			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage msg = new HTTPReturnMessage();
				OverlayVpnDriverResponse<SbiIp> response = new OverlayVpnDriverResponse<>();
				SbiIp data = new SbiIp();
				data.setIpv4("192.168.2.1");
				response.setErrcode("0");
				response.setData(data);
				msg.setBody(JsonUtil.toJson(response));
				msg.setStatus(200);
				return msg;
			}
		};

		ResultRsp<SbiIp> queryPorts = portServiceImpl.queryPorts(deviceId, ctrlUuid);		
		String errorCode = queryPorts.getErrorCode();		
		assertEquals(true,errorCode.contains("192.168.2.1"));
	}

	@Test(expected = ServiceException.class)
	public void queryPortsFailure() throws ServiceException {

		PortServiceImpl portServiceImpl = new PortServiceImpl();
		String deviceId = "device";
		String ctrlUuid = "ctrlUuid";

		new MockUp<OverlayVpnDriverProxy>() {
			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setBody("");
				msg.setStatus(500);
				return msg;
			}
		};

		portServiceImpl.queryPorts(deviceId, ctrlUuid);

	}

	@Test(expected = ServiceException.class)
	public void queryPortsAcResponseFailure() throws ServiceException {

		PortServiceImpl portServiceImpl = new PortServiceImpl();
		String deviceId = "device";
		String ctrlUuid = "ctrlUuid";

		new MockUp<OverlayVpnDriverProxy>() {
			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setBody(queryResJson);
				msg.setStatus(500);
				return msg;
			}
		};

		portServiceImpl.queryPorts(deviceId, ctrlUuid);

	}
}
