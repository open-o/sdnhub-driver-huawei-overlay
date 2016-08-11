package org.openo.sdno.acbranchservice.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.login.AcBranchProxy;
import org.openo.sdno.acbranchservice.login.AcBranchResponse;
import org.openo.sdno.acbranchservice.model.port.NetAcDevicePort;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.acbranchservice.service.wan.WanInfSvcImpl;
import org.openo.sdno.acbranchservice.util.config.WanInterface;
import org.openo.sdno.acbranchservice.util.controller.ControllerUtil;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.enums.WanInterfaceUsedType;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class WanInterfaceRoaResourceTest {

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
		
		new MockUp<ControllerUtil<WanSubInterface>>() {
			@Mock
			public List<WanSubInterface> checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
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
				netAcDevicePort.setCeLowVlan("123");
				netAcDevicePort.setIpAddr("1.1.1.1");
				list.add(netAcDevicePort);
				return list;
			}
		};
		
		WanInterfaceRoaResource roa = new WanInterfaceRoaResource();
		ResultRsp<List<WanSubInterface>> result = roa.queryWanInterface("123", "123", WanInterfaceUsedType.GRE.getName());
		
		assertEquals("127.0.0.1", result.getData().get(0).getIpAddress());
	}
	
	@Test(expected=ServiceException.class)
	public void testQueryWanInterface_invalid() throws ServiceException {
		WanInterfaceRoaResource roa = new WanInterfaceRoaResource();
		ResultRsp<List<WanSubInterface>> result = roa.queryWanInterface("123@@#", "123", WanInterfaceUsedType.GRE.getName());
		
	}
	
	@Test(expected=ServiceException.class)
	public void testQueryWanInterface_invaliddevice() throws ServiceException {
		WanInterfaceRoaResource roa = new WanInterfaceRoaResource();
		ResultRsp<List<WanSubInterface>> result = roa.queryWanInterface("123", "123*&", WanInterfaceUsedType.GRE.getName());
		
	}
	
	@Test(expected=ServiceException.class)
	public void testQueryWanInterface_invalidtype() throws ServiceException {
		WanInterfaceRoaResource roa = new WanInterfaceRoaResource();
		ResultRsp<List<WanSubInterface>> result = roa.queryWanInterface("123", "123", "");
		
	}

}
