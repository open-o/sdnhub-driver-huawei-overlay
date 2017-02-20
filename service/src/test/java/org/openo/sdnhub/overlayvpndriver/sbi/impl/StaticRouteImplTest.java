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
import org.openo.sdnhub.overlayvpndriver.controller.model.ControllerNbiStaticRoute;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class StaticRouteImplTest {
	
	String queryResJson =
            "{\"errcode\":\"0\",\"errmsg\":null,\"pageIndex\":0,\"pageSize\":0,\"totalRecords\":0,\"data\":{\"ipv4\":\"192.168.1.2\",\"ipv6\":\"\",\"ipMask\":\"\",\"prefixLength\":\"\",\"id\":\"\"},\"success\":[],\"fail\":[],\"sucess\":true}";
	
	 @Test
	    public void queryRouteByDevice() throws ServiceException{
		 String ctrlUuid="123";
		 String deviceId="111";
         String destIp="10.20.10.30";
         String staticRouteId="staticRouteId";
         
         new MockUp<OverlayVpnDriverProxy>() {
             @Mock
             public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                 HTTPReturnMessage msg = new HTTPReturnMessage();
                 
                 OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>> response=new OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>>();
                 
                 List<ControllerNbiStaticRoute> list=new ArrayList<ControllerNbiStaticRoute>();
                 
                 ControllerNbiStaticRoute route=new ControllerNbiStaticRoute();
                 route.setId("123");
                 list.add(route);
                 response.setData(list);
                 response.setErrcode("success");
                 msg.setBody(JsonUtil.toJson(response));
                 return msg;
             }
         };
         
         new MockUp<HTTPReturnMessage>() {
         	@Mock
             public boolean isSuccess() {
         		return true;
         	}
         };
		 
         new MockUp<OverlayVpnDriverResponse>() {
          	@Mock
              public boolean isSucess() {
          		return true;
          	}
          };
		 StaticRouteImpl impl=new StaticRouteImpl();
		 ResultRsp<List<ControllerNbiStaticRoute>> rsp=impl.queryRouteByDevice(ctrlUuid, deviceId, destIp, staticRouteId);
		 assertTrue("overlayvpn.operation.success".equals(rsp.getErrorCode()));
		 
	 }
	 
	 @Test(expected=ServiceException.class)
	    public void configStaticRoute() throws ServiceException{
		 
		 new MockUp<OverlayVpnDriverProxy>() {
             @Mock
             public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                 HTTPReturnMessage msg = new HTTPReturnMessage();
                 
                 OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>> response=new OverlayVpnDriverResponse<List<ControllerNbiStaticRoute>>();
                 
                 List<ControllerNbiStaticRoute> list=new ArrayList<ControllerNbiStaticRoute>();
                 
                 ControllerNbiStaticRoute route=new ControllerNbiStaticRoute();
                 route.setId("123");
                 list.add(route);
                 response.setData(list);
                 response.setErrcode("0");
                 msg.setBody(JsonUtil.toJson(response));
                 return msg;
             }
         };
         
         new MockUp<HTTPReturnMessage>() {
         	@Mock
             public boolean isSuccess() {
         		return true;
         	}
         };
		 StaticRouteImpl impl=new StaticRouteImpl();
		 List<ControllerNbiStaticRoute> list=new ArrayList();
		 ControllerNbiStaticRoute route=new ControllerNbiStaticRoute();
		 route.setId("123");
		 list.add(route);
		 String ctrlUuid="123";
		 String deviceId="111";
		 impl.configStaticRoute(ctrlUuid, deviceId, list,true);
	 }

}