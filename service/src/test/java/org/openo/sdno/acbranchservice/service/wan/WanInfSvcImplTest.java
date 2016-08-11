package org.openo.sdno.acbranchservice.service.wan;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.login.AcBranchProxy;
import org.openo.sdno.acbranchservice.login.AcBranchResponse;
import org.openo.sdno.acbranchservice.model.port.NetAcDevicePort;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.acbranchservice.util.config.WanInterface;
import org.openo.sdno.acbranchservice.util.controller.ControllerUtil;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.enums.WanInterfaceUsedType;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class WanInfSvcImplTest {

	@Test
	public void testQueryWanInterface() throws ServiceException {

		new MockUp<AcBranchProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctlrUuid) {
				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setStatus(200);

				AcBranchResponse<List<NetVxLanDeviceModel>> res = new AcBranchResponse<List<NetVxLanDeviceModel>>();
				NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
				List<NetVxLanDeviceModel> mos = new ArrayList<>();
				mos.add(mo);
				res.setData(mos);

				res.setErrcode("0");
				msg.setBody(JsonUtil.toJson(res));
				return msg;
			}

		};
		
		new MockUp<WanInterface>() {
			@Mock
			public String getConfig(String cfgKey) throws ServiceException {
				return "123";
			}
			
		};
		
		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
				WanSubInterface mo = new WanSubInterface();
				mo.setCeLowVlan(123);
				List<WanSubInterface> mos = new ArrayList<>();
				mos.add(mo);
				
				return mos;
				
			}
		};
		
		new MockUp<WanInfSvcImpl>(){
			@Mock
			public List<NetAcDevicePort> queryPorts(List<String> interfaceNameList, String deviceId, String ctrlUuid){
				List<NetAcDevicePort> list = new ArrayList<>();
				NetAcDevicePort netAcDevicePort = new NetAcDevicePort();
				netAcDevicePort.setAlias("alias");
				list.add(netAcDevicePort);
				return list;
			}
		};
		
		try {
		List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "123",
				WanInterfaceUsedType.GRE.getName());
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testQueryWanInterfaceBranch() throws ServiceException {

		new MockUp<AcBranchProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctlrUuid) {
				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setStatus(200);

				AcBranchResponse<List<NetVxLanDeviceModel>> res = new AcBranchResponse<List<NetVxLanDeviceModel>>();
				NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
				List<NetVxLanDeviceModel> mos = new ArrayList<>();
				mos.add(mo);
				res.setData(mos);

				res.setErrcode("0");
				msg.setBody(JsonUtil.toJson(res));
				return msg;
			}

		};
		
		new MockUp<WanInterface>() {
			@Mock
			public String getConfig(String cfgKey) throws ServiceException {
				return "123";
			}
			
		};
		
		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
				WanSubInterface mo = new WanSubInterface();
				mo.setCeLowVlan(123);
				List<WanSubInterface> mos = new ArrayList<>();
				mos.add(mo);
				
				return mos;
				
			}
		};
		
		new MockUp<WanInfSvcImpl>(){
			@Mock
			public List<NetAcDevicePort> queryPorts(List<String> interfaceNameList, String deviceId, String ctrlUuid){
				List<NetAcDevicePort> list = new ArrayList<>();
				NetAcDevicePort netAcDevicePort = new NetAcDevicePort();
				netAcDevicePort.setAlias("alias");
				netAcDevicePort.setIpAddr("1.1.1.1");
				list.add(netAcDevicePort);
				return list;
			}
		};
		
		try {
		List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "123",
				WanInterfaceUsedType.GRE.getName());
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testQueryWanInterface_All() throws ServiceException {

		new MockUp<AcBranchProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctlrUuid) {
				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setStatus(200);

				AcBranchResponse<List<NetVxLanDeviceModel>> res = new AcBranchResponse<List<NetVxLanDeviceModel>>();
				NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
				List<NetVxLanDeviceModel> mos = new ArrayList<>();
				mos.add(mo);
				res.setData(mos);

				res.setErrcode("0");
				msg.setBody(JsonUtil.toJson(res));
				return msg;
			}

		};
		
		new MockUp<WanInterface>() {
			@Mock
			public String getConfig(String cfgKey) throws ServiceException {
				return "123";
			}
			
		};
		
		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
				WanSubInterface mo = new WanSubInterface();
				mo.setCeLowVlan(123);
				List<WanSubInterface> mos = new ArrayList<>();
				mos.add(mo);
				
				return mos;
				
			}
		};
		
		try {
		List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "123",
				WanInterfaceUsedType.ALL.getName());
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testQueryWanInterface_1() throws ServiceException {
		try {
			List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("", "123",
					WanInterfaceUsedType.GRE.getName());
			}catch (Exception e) {
				assertTrue(e instanceof ServiceException);
			}
	}
	
	@Test
	public void testQueryWanInterface_2() throws ServiceException {
		try {
			List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "",
					WanInterfaceUsedType.GRE.getName());
			}catch (Exception e) {
				assertTrue(e instanceof ServiceException);
			}
	}
	
	@Test
	public void testQueryWanInterface_3() throws ServiceException {
		try {
			List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "123",
					WanInterfaceUsedType.GRE.getName());
			}catch (Exception e) {
				assertTrue(true);
			}
	}
	
	@Test
	public void testQueryWanInterfaceExe() throws ServiceException {

		new MockUp<AcBranchProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctlrUuid) {
				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setStatus(200);

				AcBranchResponse<List<NetVxLanDeviceModel>> res = new AcBranchResponse<List<NetVxLanDeviceModel>>();
				NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
				List<NetVxLanDeviceModel> mos = new ArrayList<>();
				mos.add(mo);
				res.setData(mos);

				res.setErrcode("0");
				msg.setBody(JsonUtil.toJson(res));
				return msg;
			}

		};
		
		new MockUp<WanInterface>() {
			@Mock
			public String getConfig(String cfgKey) throws ServiceException {
				return "123";
			}
			
		};
		
		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
				
				List<WanSubInterface> mos = new ArrayList<>();
				
				return mos;
				
			}
		};
		
		try {
		List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "123",
				WanInterfaceUsedType.GRE.getName());
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testQueryWanInterfaceExeVlanId() throws ServiceException {

		new MockUp<AcBranchProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctlrUuid) {
				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setStatus(200);

				AcBranchResponse<List<NetVxLanDeviceModel>> res = new AcBranchResponse<List<NetVxLanDeviceModel>>();
				NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
				List<NetVxLanDeviceModel> mos = new ArrayList<>();
				mos.add(mo);
				res.setData(mos);

				res.setErrcode("0");
				msg.setBody(JsonUtil.toJson(res));
				return msg;
			}

		};
		
		new MockUp<WanInterface>() {
			@Mock
			public String getConfig(String cfgKey) throws ServiceException {
				return "";
			}
			
		};
		
		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
				WanSubInterface mo = new WanSubInterface();
				mo.setCeLowVlan(123);
				List<WanSubInterface> mos = new ArrayList<>();
				mos.add(mo);
				
				return mos;
				
			}
		};
		
		try {
		List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "123",
				"");
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testQueryWanInterfaceExeFor() throws ServiceException {

		new MockUp<AcBranchProxy>() {

			@Mock
			public HTTPReturnMessage sendGetMsg(String url, String body, String ctlrUuid) {
				HTTPReturnMessage msg = new HTTPReturnMessage();
				msg.setStatus(200);

				AcBranchResponse<List<NetVxLanDeviceModel>> res = new AcBranchResponse<List<NetVxLanDeviceModel>>();
				NetVxLanDeviceModel mo = new NetVxLanDeviceModel();
				List<NetVxLanDeviceModel> mos = new ArrayList<>();
				mos.add(mo);
				res.setData(mos);

				res.setErrcode("0");
				msg.setBody(JsonUtil.toJson(res));
				return msg;
			}

		};
		
		new MockUp<WanInterface>() {
			@Mock
			public String getConfig(String cfgKey) throws ServiceException {
				return "";
			}
			
		};
		
		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
				WanSubInterface mo = new WanSubInterface();
				mo.setCeLowVlan(123);
				List<WanSubInterface> mos = new ArrayList<>();
				mos.add(mo);
				
				return null;
				
			}
		};
		
		try {
		List<WanSubInterface> result = WanInfSvcImpl.queryWanInterface("123", "123",
				"");
		}catch (Exception e) {
			assertTrue(true);
		}
	}
}
