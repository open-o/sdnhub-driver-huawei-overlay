package org.openo.sdno.acbranchservice.service.ipsec;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdno.acbranchservice.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdno.acbranchservice.model.ipsec.db.IpSecExternalIdMapping;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.acbranchservice.util.controller.ControllerUtil;
import org.openo.sdno.acbranchservice.util.db.IpSecDbOper;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;

public class IpSecSvcImplTest {

	@Test(expected = Exception.class)
	public void testCreateIpSec() {

		new MockUp<IpSecDbOper>() {
			@Mock
			public IpSecExternalIdMapping query(String ipSecConnectionId) {
				IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", "1234", "234", "123");

				return ipSecExtIdMapping;
			}
		};

		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {
				List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
				NetIpSecConn netIpSecConn = new NetIpSecConn();
				netIpSecConn.setSeqNumber(234);
				ipSecConnection.add(netIpSecConn);
				NetIpSecModel mo = new NetIpSecModel();
				mo.setIpsecConnection(ipSecConnection);
				List<NetIpSecModel> mos = new ArrayList<>();
				mos.add(mo);
				return mos;

			}
		};

		try {
			List<NetIpSecModel> netIpSecModelList = new ArrayList<NetIpSecModel>();
			NetIpSecModel ipSecModel = new NetIpSecModel();
			ipSecModel.setInterfaceName("interfaceName");
			netIpSecModelList.add(ipSecModel);
			IpSecSvcImpl.createIpSec("123", "1234", netIpSecModelList);
		} catch (Exception e) {
		}

	}

	@Test
	public void testCreateIpSecThrowsException() {

		try {
			List<NetIpSecModel> netIpSecModelList = new ArrayList<NetIpSecModel>();
			NetIpSecModel ipSecModel = new NetIpSecModel();
			netIpSecModelList.add(ipSecModel);
			IpSecSvcImpl.createIpSec("123", null, netIpSecModelList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteIpSec() {

		new MockUp<IpSecDbOper>() {
			@Mock
			public IpSecExternalIdMapping query(String ipSecConnectionId) {
				IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", "1234", "234", "123");

				return ipSecExtIdMapping;
			}
		};

		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {

				List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
				NetIpSecConn netIpSecConn = new NetIpSecConn();
				netIpSecConn.setSeqNumber(234);
				ipSecConnection.add(netIpSecConn);
				NetIpSecModel mo = new NetIpSecModel();
				mo.setIpsecConnection(ipSecConnection);
				List<NetIpSecModel> mos = new ArrayList<>();
				mos.add(mo);
				return mos;
			}
		};

		try {
			IpSecSvcImpl.deleteIpSec("123", "12345");
		} catch (ServiceException e) {

		}

	}

	@Test
	public void testDeleteIpSecNullSequenceNummber() {

		new MockUp<IpSecDbOper>() {
			@Mock
			public IpSecExternalIdMapping query(String ipSecConnectionId) {
				IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", "1234", "234", "123");

				return ipSecExtIdMapping;
			}
		};

		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {

				List<NetIpSecConn> ipSecConnection = new ArrayList<NetIpSecConn>();
				ipSecConnection.add(new NetIpSecConn());
				NetIpSecModel mo = new NetIpSecModel();
				mo.setIpsecConnection(ipSecConnection);
				List<NetIpSecModel> mos = new ArrayList<>();
				mos.add(mo);
				return mos;
			}
		};

		try {
			IpSecSvcImpl.deleteIpSec("123", "12345");
		} catch (Exception e) {

		}

	}

	@Test
	public void testDeleteIpSecNullResult() {

		new MockUp<IpSecDbOper>() {
			@Mock
			public IpSecExternalIdMapping query(String ipSecConnectionId) {
				IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", "1234", "234", "123");

				return ipSecExtIdMapping;
			}
		};

		new MockUp<ControllerUtil>() {
			@Mock
			public List checkRsp(HTTPReturnMessage httpMsg) throws ServiceException {

				List<NetIpSecModel> mos = null;

				return mos;

			}
		};

		try {
			IpSecSvcImpl.deleteIpSec("123", "12345");
		} catch (Exception e) {

		}

	}

	@Test
	public void testDeleteIpSecExternalIdIsNull() {

		new MockUp<IpSecDbOper>() {
			@Mock
			public IpSecExternalIdMapping query(String ipSecConnectionId) {
				IpSecExternalIdMapping ipSecExtIdMapping = new IpSecExternalIdMapping("123", null, "234", "123");

				return ipSecExtIdMapping;
			}
		};

		try {
			IpSecSvcImpl.deleteIpSec("123", "12345");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteIpSecThrowsNull() {

		new MockUp<IpSecDbOper>() {
			@Mock
			public IpSecExternalIdMapping query(String ipSecConnectionId) {
				IpSecExternalIdMapping ipSecExtIdMapping = null;

				return ipSecExtIdMapping;
			}
		};

		try {
			IpSecSvcImpl.deleteIpSec("123", "12345");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}