package org.openo.sdno.acbranchservice.login;

import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

public class MockedStatusLine implements StatusLine{

	int status = 200;
	@Override
	public ProtocolVersion getProtocolVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStatusCode() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public String getReasonPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

}
