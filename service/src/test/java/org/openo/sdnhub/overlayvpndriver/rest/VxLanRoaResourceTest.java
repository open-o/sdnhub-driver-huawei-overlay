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
package org.openo.sdnhub.overlayvpndriver.rest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.Vni;
import org.openo.sdnhub.overlayvpndriver.controller.model.VxLanDeviceModel;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.result.ACDelResponse;
import org.openo.sdnhub.overlayvpndriver.result.ConfigCommonResult;
import org.openo.sdnhub.overlayvpndriver.result.OverlayVpnDriverResponse;
import org.openo.sdnhub.overlayvpndriver.sbi.impl.VxLanSvcImpl;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInstance;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanInterface;
import org.openo.sdno.overlayvpn.model.v2.vxlan.SbiNeVxlanTunnel;
import org.openo.sdno.overlayvpn.result.FailData;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpn.util.check.ValidationUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class VxLanRoaResourceTest {
	private static final String CTRL_UUID = "extSysID=81244ad0-b4ea-41ed-969e-d5588b32fd4c";
	VxLanRoaResource vxLanRoaResource = new VxLanRoaResource();
	
	List<SbiNeVxlanInstance> sbiVxLanInstanceList = new ArrayList<SbiNeVxlanInstance>();
	SbiNeVxlanInstance sbiNeVxlanInstance = new SbiNeVxlanInstance();

	@Before
	public void setup() throws Exception{
		
		List<String> vxlanInterfaces = new ArrayList<>();
		vxlanInterfaces.add("interface1");
		vxlanInterfaces.add("interface2");
		List<SbiNeVxlanTunnel> vxlanTunnelList = new ArrayList<>();
		SbiNeVxlanTunnel sbiNeVxlanTunnel = new SbiNeVxlanTunnel();
		vxlanTunnelList.add(sbiNeVxlanTunnel );
		List<String> vxlanTunnels = new ArrayList<>();
		SbiNeVxlanInterface sbiNeVxlanInterface = new SbiNeVxlanInterface();
		sbiNeVxlanInterface.setConnectionId("connectionId");
		sbiNeVxlanInterface.setAccessType("DOT1Q");		
		List<SbiNeVxlanInterface> vxlanInterfaceList = new ArrayList<>();
		vxlanInterfaceList.add(sbiNeVxlanInterface);
		
		sbiNeVxlanInstance.setActiveStatus("active");
		sbiNeVxlanInstance.setAdditionalInfo("additionalInfo");
		sbiNeVxlanInstance.setArpBroadcastSuppress("arpBroadcastSuppress");
		sbiNeVxlanInstance.setArpProxy("arpProxy");
		sbiNeVxlanInstance.setConnectionId("connectionId");
		sbiNeVxlanInstance.setControllerId("controllerId");
		sbiNeVxlanInstance.setCreatetime((long) 1236549);
		sbiNeVxlanInstance.setDescription("description");;
		sbiNeVxlanInstance.setDeployStatus("deployStatus");
		sbiNeVxlanInstance.setDeviceId("device12345");
		sbiNeVxlanInstance.setExternalId("1111");
		sbiNeVxlanInstance.setKeepAlive("keepAlive");
		sbiNeVxlanInstance.setName("name");
		sbiNeVxlanInstance.setNbiVxlanTunnelId("nbiVxlanTunnelId");
		sbiNeVxlanInstance.setOperationStatus("operationStatus");
		sbiNeVxlanInstance.setPeerDeviceId("peerDeviceId");
		sbiNeVxlanInstance.setRunningStatus("runningStatus");
		sbiNeVxlanInstance.setTenantId("tenantId");
		sbiNeVxlanInstance.setUpdatetime((long)13548);
		sbiNeVxlanInstance.setUuid("uuid");
		sbiNeVxlanInstance.setVni("1");
		sbiNeVxlanInstance.setVxlanInterfaces(vxlanInterfaces);
		sbiNeVxlanInstance.setVxlanTunnelList(vxlanTunnelList);
		sbiNeVxlanInstance.setVxlanTunnels(vxlanTunnels);
		sbiNeVxlanInstance.setVxlanInterfaceList(vxlanInterfaceList);
		
		sbiVxLanInstanceList.add(sbiNeVxlanInstance);
		
		
		//Setting the impl service obj using reflection
        Field field = vxLanRoaResource.getClass().getDeclaredField("vxlanService");
        field.setAccessible(true);
        field.set(vxLanRoaResource, new VxLanSvcImpl());
	}

	@Test
    public void testCreateVxlan() throws WebApplicationException, ServiceException {
        
        new MockUp<ValidationUtil>(){
            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };
        
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response = new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel(); 
                
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);             
                
                response.setData(vxLanDeviceModelList);             
                response.setErrcode("overlayvpn.operation.failed");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNeVxlanInstance> createVxlan = vxLanRoaResource.createVxlan(null, CTRL_UUID, sbiVxLanInstanceList);        
        List<FailData<SbiNeVxlanInstance>> fail = createVxlan.getFail();
        SbiNeVxlanInstance sbiNeVxlanInstance = fail.get(0).getData();
        sbiNeVxlanInstance.getDeviceId();
        
        assertEquals("overlayvpn.operation.failed", createVxlan.getErrorCode());
        assertEquals("device12345", sbiNeVxlanInstance.getDeviceId());
        
    }
    
    @Test
    public void testCreateVxlanCreateResultSuccess() throws WebApplicationException, ServiceException {
        

        new MockUp<ValidationUtil>(){
            @Mock
            public void validateModel(Object obj) throws ServiceException {
                return;
            }
        };
        
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response = new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel(); 
                
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);
                
                response.setData(vxLanDeviceModelList);             
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        ResultRsp<SbiNeVxlanInstance> createVxlanResponse = vxLanRoaResource.createVxlan(null, CTRL_UUID, sbiVxLanInstanceList);        
        List<SbiNeVxlanInstance> successed = createVxlanResponse.getSuccessed();        
        
        assertEquals("overlayvpn.operation.success", createVxlanResponse.getErrorCode());
        assertEquals("device12345", successed.get(0).getDeviceId());
    }
	
	@Test
	public void testQueryVxlan() throws ServiceException {
	    
	    new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response = new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel(); 
                vxLanDeviceModel.setUuid("1111");
                
                
                List<Vni> vniList = new ArrayList<>();
                Vni vni = new Vni();
                vni.setVni(1);
                vniList.add(vni);
                
                vxLanDeviceModel.setVniList(vniList);
                vxLanDeviceModelList.add(vxLanDeviceModel);
                
                response.setData(vxLanDeviceModelList);             
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
	    
	     ResultRsp<SbiNeVxlanInstance> result = vxLanRoaResource.queryVxlan(null, CTRL_UUID, sbiVxLanInstanceList);
	     assertEquals("overlayvpn.operation.success", result.getErrorCode());
	     assertEquals("1", result.getSuccessed().get(0).getVni());
	    
	}
	
	@Test
    public void testQueryVxlanEmptyInput() throws ServiceException {
        
	    try {
         vxLanRoaResource.queryVxlan(null, CTRL_UUID, null);
         fail("exception not thrown");
	    }catch(ServiceException e) {
	        assertEquals(e.getMessage(), "overlayvpn.operation.paramter_invalid");
	    }
        
    }
	
	@Test
    public void testQueryVxlan_Noresult() throws ServiceException {
        
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response = new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        
         ResultRsp<SbiNeVxlanInstance> result = vxLanRoaResource.queryVxlan(null, CTRL_UUID, sbiVxLanInstanceList);
         assertEquals("overlayvpn.operation.failed", result.getErrorCode());
        
    }
	
	@Test
    public void testQueryVxlan_NoMatchingFound() throws ServiceException {
        
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response = new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel(); 
                vxLanDeviceModel.setUuid("1111");
                
                
                List<Vni> vniList = new ArrayList<>();
                Vni vni = new Vni();
                vni.setVni(2);
                vniList.add(vni);
                
                vxLanDeviceModel.setVniList(vniList);
                vxLanDeviceModelList.add(vxLanDeviceModel);
                
                response.setData(vxLanDeviceModelList);             
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        
         ResultRsp<SbiNeVxlanInstance> result = vxLanRoaResource.queryVxlan(null, CTRL_UUID, sbiVxLanInstanceList);
         assertEquals("overlayvpn.operation.failed", result.getErrorCode());
        
    }
	
	@Test(expected=ServiceException.class)
    public void testQueryVxlan_getFail() throws ServiceException {
        
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                
                return msg;
            }
        };
        
         vxLanRoaResource.queryVxlan(null, CTRL_UUID, sbiVxLanInstanceList);
        
    }
	
	@Test(expected=ServiceException.class)
    public void testQueryVxlan_getFail2() throws ServiceException {
        
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response = new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                
                response.setErrcode("1");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
        
         vxLanRoaResource.queryVxlan(null, CTRL_UUID, sbiVxLanInstanceList);
    }
	
	@Test
	public void testUpdate() throws ServiceException {
	    new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                OverlayVpnDriverResponse<List<VxLanDeviceModel>> response = new OverlayVpnDriverResponse<List<VxLanDeviceModel>>();
                
                List<VxLanDeviceModel> vxLanDeviceModelList = new ArrayList<VxLanDeviceModel>();
                VxLanDeviceModel vxLanDeviceModel = new VxLanDeviceModel(); 
                
                vxLanDeviceModel.setUuid("uuid");
                vxLanDeviceModelList.add(vxLanDeviceModel);             
                
                response.setData(vxLanDeviceModelList);             
                response.setErrcode("0");
                msg.setBody(JsonUtil.toJson(response));
                return msg;
            }
        };
	    ResultRsp<SbiNeVxlanInstance> result = vxLanRoaResource.updateVxlan(null, CTRL_UUID, sbiVxLanInstanceList);
	    
	    assertEquals("overlayvpn.operation.success", result.getErrorCode());
	    assertEquals(1, result.getSuccessed().size());
	}
	
	@Test
    public void testUpdate_fail() throws ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(500);
                
                return msg;
            }
        };
        ResultRsp<SbiNeVxlanInstance> result = vxLanRoaResource.updateVxlan(null, CTRL_UUID, sbiVxLanInstanceList);
        
        assertEquals("overlayvpn.operation.failed", result.getErrorCode());
        assertEquals(0, result.getSuccessed().size());
    }
	
	@Test
    public void testBatchDeleteVxlanResultSuccess() throws WebApplicationException, ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                ACDelResponse acDelResponse = new ACDelResponse();
                List<String> deleteIdList = new LinkedList<String>();
                deleteIdList.add("externalId12345");
                List<ConfigCommonResult> configCommonResultList = new LinkedList<ConfigCommonResult>();
                ConfigCommonResult configCommonResult = new ConfigCommonResult();
                configCommonResult.setId("externalId12345");
                configCommonResultList.add(configCommonResult);
                acDelResponse.setSuccess(configCommonResultList);
                acDelResponse.setErrocode("0");
                msg.setBody(JsonUtil.toJson(acDelResponse));
                return msg;
            }
        };
        ResultRsp<SbiNeVxlanInstance> batchDeleteVxlanResponse = vxLanRoaResource.batchDeleteVxlan(null, "device12345", CTRL_UUID, sbiVxLanInstanceList);   
        assertEquals("overlayvpn.operation.success", batchDeleteVxlanResponse.getErrorCode());
    }
    
    @Test
    public void testBatchDeleteVxlanResultFailure() throws WebApplicationException, ServiceException {
        new MockUp<OverlayVpnDriverProxy>() {
            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage msg = new HTTPReturnMessage();
                msg.setStatus(200);
                
                ACDelResponse acDelResponse = new ACDelResponse();
                List<String> deleteIdList = new LinkedList<String>();
                deleteIdList.add("externalId12345");
                List<ConfigCommonResult> configCommonResultList = new LinkedList<ConfigCommonResult>();
                ConfigCommonResult configCommonResult = new ConfigCommonResult();
                configCommonResult.setId("externalId12345");
                configCommonResultList.add(configCommonResult);
                acDelResponse.setSuccess(configCommonResultList);
                acDelResponse.setErrocode("failure");
                msg.setBody(JsonUtil.toJson(acDelResponse));
                return msg;
            }
        };
        ResultRsp<SbiNeVxlanInstance> batchDeleteVxlanResponse = vxLanRoaResource.batchDeleteVxlan(null, "device12345", CTRL_UUID, sbiVxLanInstanceList);   
        assertEquals("overlayvpn.operation.failed", batchDeleteVxlanResponse.getErrorCode());
    }
}
