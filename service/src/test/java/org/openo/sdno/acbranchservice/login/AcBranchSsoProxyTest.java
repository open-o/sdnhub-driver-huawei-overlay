package org.openo.sdno.acbranchservice.login;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.StatusLine;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.model.vxlan.db.VxLanExternalIdMapping;
import org.openo.sdno.acbranchservice.util.db.VxLanDbOper;
import org.openo.sdno.ssl.EncryptionUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.apache.http.impl.execchain.*;
import org.openo.sdno.acbranchservice.login.AcBranchResponse;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import net.sf.json.JSONObject;

public class AcBranchSsoProxyTest {
	
	/*private CloseableHttpResponse getMockClosesableHttpResponse() throws Exception {
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    CloseableHttpResponse closeableHttpResponse = httpClient.execute(new HttpGet("http://localhost:8080"));
	    //closeableHttpResponse.setEntity(response.getEntity());
	    //closeableHttpResponse.setStatusLine(response.getStatusLine());
	    return closeableHttpResponse;
	}*/
	
	@Test
	public void testlogin(){
		new MockUp<CloseableHttpClient>(){
			@Mock
			 public CloseableHttpResponse execute(
			            final HttpUriRequest request) throws Exception{
				return new MockedHttpResponse();
			}
			
		};
		new MockUp<EncryptionUtil>(){
			@Mock
			public char[] decode(char[] cars){
				char[] ch ={'a'};
				return ch;
			}
			
		};
		new MockUp<JSONObject>(){
			@Mock
			public int getInt(String str){
				return 0;
			}
		};
		new MockUp<EntityUtils>(){
			@Mock
			public String toString(HttpEntity entity,String str) {
				return "{\"name\":\"test\"}";
			}
		};
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		boolean isLogin = acBranch.login("1.1.1.1");
		assertTrue(isLogin);
	}
	
	@Test(expected = Exception.class)
	public void testlogin2(){
		new MockUp<CloseableHttpClient>(){
			@Mock
			 public CloseableHttpResponse execute(
			            final HttpUriRequest request) throws Exception{
				return new MockedHttpResponse();
			}
			
		};
		new MockUp<EncryptionUtil>(){
			@Mock
			public char[] decode(char[] cars){
				char[] ch ={'a'};
				return ch;
			}
			
		};
		new MockUp<JSONObject>(){
			@Mock
			public int getInt(String str){
				return 1;
			}
		};
		new MockUp<EntityUtils>(){
			@Mock
			public String toString(HttpEntity entity,String str) {
				return "{}";
			}
		};
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		boolean isLogin = acBranch.login("1.1.1.1");
	}
	@Test
	public void testlogin3(){
		new MockUp<CloseableHttpClient>(){
			@Mock
			 public CloseableHttpResponse execute(
			            final HttpUriRequest request) throws Exception{
				return new MockedHttpResponse();
			}
			
		};
		new MockUp<EncryptionUtil>(){
			@Mock
			public char[] decode(char[] cars){
				char[] ch ={'a'};
				return ch;
			}
			
		};
		new MockUp<JSONObject>(){
			@Mock
			public int getInt(String str){
				return 1;
			}
		};
		new MockUp<EntityUtils>(){
			@Mock
			public String toString(HttpEntity entity,String str) {
				return "{}";
			}
		};
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance(null, "80", "test", "password");
		boolean isLogin = acBranch.login("1.1.1.1");
		assertFalse(isLogin);
	}
	
	@Test
	public void testlogin4(){
		new MockUp<CloseableHttpClient>(){
			@Mock
			 public CloseableHttpResponse execute(
			            final HttpUriRequest request) throws Exception{
				return new MockedHttpResponse();
			}
			
		};
		new MockUp<EncryptionUtil>(){
			@Mock
			public char[] decode(char[] cars){
				char[] ch ={'a'};
				return ch;
			}
			
		};
		new MockUp<JSONObject>(){
			@Mock
			public int getInt(String str){
				return 0;
			}
		};
		new MockUp<EntityUtils>(){
			@Mock
			public String toString(HttpEntity entity,String str) {
				return "{}";
			}
		};
		new MockUp<MockedStatusLine>(){
			@Mock
			public int getStatusCode(){
				return 500;
			}
		};
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		boolean isLogin = acBranch.login("1.1.1.1");
		assertFalse(isLogin);
	}
	
	@Test
	public void testlogin5(){
		new MockUp<CloseableHttpClient>(){
			@Mock
			 public CloseableHttpResponse execute(
			            final HttpUriRequest request) throws Exception{
				return new MockedHttpResponse();
			}
			
		};
		new MockUp<EncryptionUtil>(){
			@Mock
			public char[] decode(char[] cars){
				char[] ch ={'a'};
				return ch;
			}
			
		};
		new MockUp<JSONObject>(){
			@Mock
			public int getInt(String str){
				return 0;
			}
		};
		new MockUp<EntityUtils>(){
			@Mock
			public String toString(HttpEntity entity,String str) {
				AcBranchResponse resp = new AcBranchResponse();
				return "{\"name\":\"test\"}";
			}
		};
		new MockUp<MockedStatusLine>(){
			@Mock
			public int getStatusCode(){
				return 200;
			}
		};
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		boolean isLogin = acBranch.login("1.1.1.1");
		assertTrue(isLogin);
	}
	
	
	@Test
	public void testPost(){
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		HTTPReturnMessage message = acBranch.post("test.com", "test");
	}
	@Test
	public void testPost2(){
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		HTTPReturnMessage message = acBranch.post("test.com", "");
	}
	
	@Test
	public void testPut(){
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		HTTPReturnMessage message = acBranch.put("test.com", "test");
	}
	@Test
	public void testPut2(){
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		HTTPReturnMessage message = acBranch.put("test.com", "");
	}
	
	@Test
	public void testDelete(){
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		HTTPReturnMessage message = acBranch.delete("test.com", "test");
	}
	@Test
	public void testDelete2(){
		AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
		HTTPReturnMessage message = acBranch.delete("test.com", "");
	}
	
	

}
