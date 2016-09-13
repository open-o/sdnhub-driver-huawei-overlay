/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.acbranchservice.login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.openo.sdno.ssl.EncryptionUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class AcBranchSsoProxyTest {

    /*
     * private CloseableHttpResponse getMockClosesableHttpResponse() throws Exception {
     * CloseableHttpClient httpClient = HttpClients.createDefault();
     * CloseableHttpResponse closeableHttpResponse = httpClient.execute(new
     * HttpGet("http://localhost:8080"));
     * //closeableHttpResponse.setEntity(response.getEntity());
     * //closeableHttpResponse.setStatusLine(response.getStatusLine());
     * return closeableHttpResponse;
     * }
     */

    @Test
    public void testlogin() {
        new MockUp<CloseableHttpClient>() {

            @Mock
            public CloseableHttpResponse execute(final HttpUriRequest request) throws Exception {
                return new MockedHttpResponse();
            }

        };
        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] cars) {
                char[] ch = {'a'};
                return ch;
            }

        };
        new MockUp<JSONObject>() {

            @Mock
            public int getInt(String str) {
                return 0;
            }
        };
        new MockUp<EntityUtils>() {

            @Mock
            public String toString(HttpEntity entity, String str) {
                return "{\"name\":\"test\"}";
            }
        };
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        boolean isLogin = acBranch.login("1.1.1.1");
        assertTrue(isLogin);
    }

    @Test(expected = Exception.class)
    public void testlogin2() {
        new MockUp<CloseableHttpClient>() {

            @Mock
            public CloseableHttpResponse execute(final HttpUriRequest request) throws Exception {
                return new MockedHttpResponse();
            }

        };
        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] cars) {
                char[] ch = {'a'};
                return ch;
            }

        };
        new MockUp<JSONObject>() {

            @Mock
            public int getInt(String str) {
                return 1;
            }
        };
        new MockUp<EntityUtils>() {

            @Mock
            public String toString(HttpEntity entity, String str) {
                return "{}";
            }
        };
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        boolean isLogin = acBranch.login("1.1.1.1");
    }

    @Test
    public void testlogin3() {
        new MockUp<CloseableHttpClient>() {

            @Mock
            public CloseableHttpResponse execute(final HttpUriRequest request) throws Exception {
                return new MockedHttpResponse();
            }

        };
        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] cars) {
                char[] ch = {'a'};
                return ch;
            }

        };
        new MockUp<JSONObject>() {

            @Mock
            public int getInt(String str) {
                return 1;
            }
        };
        new MockUp<EntityUtils>() {

            @Mock
            public String toString(HttpEntity entity, String str) {
                return "{}";
            }
        };
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance(null, "80", "test", "password");
        boolean isLogin = acBranch.login("1.1.1.1");
        assertFalse(isLogin);
    }

    @Test
    public void testlogin4() {
        new MockUp<CloseableHttpClient>() {

            @Mock
            public CloseableHttpResponse execute(final HttpUriRequest request) throws Exception {
                return new MockedHttpResponse();
            }

        };
        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] cars) {
                char[] ch = {'a'};
                return ch;
            }

        };
        new MockUp<JSONObject>() {

            @Mock
            public int getInt(String str) {
                return 0;
            }
        };
        new MockUp<EntityUtils>() {

            @Mock
            public String toString(HttpEntity entity, String str) {
                return "{}";
            }
        };
        new MockUp<MockedStatusLine>() {

            @Mock
            public int getStatusCode() {
                return 500;
            }
        };
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        boolean isLogin = acBranch.login("1.1.1.1");
        assertFalse(isLogin);
    }

    @Test
    public void testlogin5() {
        new MockUp<CloseableHttpClient>() {

            @Mock
            public CloseableHttpResponse execute(final HttpUriRequest request) throws Exception {
                return new MockedHttpResponse();
            }

        };
        new MockUp<EncryptionUtil>() {

            @Mock
            public char[] decode(char[] cars) {
                char[] ch = {'a'};
                return ch;
            }

        };
        new MockUp<JSONObject>() {

            @Mock
            public int getInt(String str) {
                return 0;
            }
        };
        new MockUp<EntityUtils>() {

            @Mock
            public String toString(HttpEntity entity, String str) {
                AcBranchResponse resp = new AcBranchResponse();
                return "{\"name\":\"test\"}";
            }
        };
        new MockUp<MockedStatusLine>() {

            @Mock
            public int getStatusCode() {
                return 200;
            }
        };
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        boolean isLogin = acBranch.login("1.1.1.1");
        assertTrue(isLogin);
    }

    @Test
    public void testPost() {
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        HTTPReturnMessage message = acBranch.post("test.com", "test");
    }

    @Test
    public void testPost2() {
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        HTTPReturnMessage message = acBranch.post("test.com", "");
    }

    @Test
    public void testPut() {
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        HTTPReturnMessage message = acBranch.put("test.com", "test");
    }

    @Test
    public void testPut2() {
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        HTTPReturnMessage message = acBranch.put("test.com", "");
    }

    @Test
    public void testDelete() {
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        HTTPReturnMessage message = acBranch.delete("test.com", "test");
    }

    @Test
    public void testDelete2() {
        AcBranchSsoProxy acBranch = AcBranchSsoProxy.getInstance("10.10.10.10", "80", "test", "password");
        HTTPReturnMessage message = acBranch.delete("test.com", "");
    }

}
