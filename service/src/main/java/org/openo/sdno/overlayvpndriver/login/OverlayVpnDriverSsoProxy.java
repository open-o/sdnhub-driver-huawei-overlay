/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.overlayvpndriver.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openo.sdno.exception.ErrorCode;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpndriver.util.consts.ControllerUrlConst;
import org.openo.sdno.ssl.EncryptionUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import net.sf.json.JSONObject;

/**
 * Class for login and sending restful requests to AC Branch. <br/>
 *
 * @author
 * @version SDNO 0.5 2016-6-22
 */
public class OverlayVpnDriverSsoProxy {

    private static final String SSLCONTEST_TLS = "TLSV1.2";

    private static final String APPLICATION_JSON = "application/json";

    private static final Logger LOGGER = LoggerFactory.getLogger(OverlayVpnDriverSsoProxy.class);

    private static final int FAILED = -1;

    private HttpClient httpClient;

    private String acIp;

    private String acPort;

    private String acLoginName;

    private String acLoginPassword = null;

    private static Map<String, OverlayVpnDriverSsoProxy> instanceCache = new HashMap<String, OverlayVpnDriverSsoProxy>();

    private OverlayVpnDriverSsoProxy(final String acIp, final String acPort, final String acLoginName, String acLoginPassword) {
        this.acIp = acIp;
        this.acPort = acPort;
        this.acLoginName = acLoginName;
        this.acLoginPassword = acLoginPassword;
        try {
            SSLContext sslcontext = SSLContext.getInstance(SSLCONTEST_TLS);
            sslcontext.init(null, new TrustManager[] {new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    // unimplemented
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    // unimplemented
                }
            }}, new java.security.SecureRandom());

            X509HostnameVerifier hostnameVerifier = new AllowAllHostnameVerifier();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("https", new SSLConnectionSocketFactory(sslcontext, hostnameVerifier)).build();
            HttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            httpClient = HttpClients.custom().setConnectionManager(connManager)
                    .setRedirectStrategy(new LaxRedirectStrategy()).build();
        } catch(Exception e) {
            LOGGER.error("ACSSOProxy: throw exception.", e);
        }
    }

    /**
     * Get instance. <br/>
     * 
     * @param acIp The IP address
     * @param acPort The port
     * @param acLoginName The login name
     * @param acLoginPassword The login password
     * @return the OverlayVpnDriverSsoProxy instance
     * @since SDNO 0.5
     */
    public static OverlayVpnDriverSsoProxy getInstance(final String acIp, final String acPort, final String acLoginName,
            String acLoginPassword) {
        synchronized(instanceCache) {
            String acSsoProxyUniqueId = acIp + acPort + acLoginName + acLoginPassword;
            if(instanceCache.get(acSsoProxyUniqueId) != null) {
                return instanceCache.get(acSsoProxyUniqueId);
            } else {
                OverlayVpnDriverSsoProxy proxy = new OverlayVpnDriverSsoProxy(acIp, acPort, acLoginName, acLoginPassword);
                instanceCache.put(acSsoProxyUniqueId, proxy);
                return proxy;
            }
        }
    }

    /**
     * Login AC Branch. <br/>
     *
     * @param url The URL path
     * @return true is success, false is failure.
     * @since SDNO 0.5
     */
    @SuppressWarnings({"deprecation", "rawtypes"})
    public boolean login(String url) {
        if(!isParamValide()) {
            LOGGER.info("AC Login Configuration is inValide, Login failed.");
            return false;
        }

        try {
            final String httpsUrl = getHttpsUrl();

            HttpGet request = new HttpGet(httpsUrl + url);
            request.addHeader("Accept", APPLICATION_JSON);
            request.addHeader("Content-Type", APPLICATION_JSON);

            LOGGER.info(request.toString());
            HttpResponse resp = httpClient.execute(request);
            LOGGER.info(resp.toString());

            String respContent = EntityUtils.toString(resp.getEntity());
            this.release(resp);
            LOGGER.info("PreLogin AC response.");
            JSONObject json = JSONObject.fromObject(respContent);

            HttpPost loginPost = new HttpPost(httpsUrl + url);
            loginPost.addHeader("Accept", APPLICATION_JSON);
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("username", acLoginName));

            char[] passwd = EncryptionUtil.decode(acLoginPassword.toCharArray());
            urlParameters.add(new BasicNameValuePair("password", new String(passwd)));
            EncryptionUtil.clear(passwd);

            if(ErrorCode.SUCCESS != json.getInt("errCode")) {
                JSONObject data = json.getJSONObject("data");
                urlParameters.add(new BasicNameValuePair("_eventId", data.getString("_eventId")));
                urlParameters.add(new BasicNameValuePair("lt", data.getString("lt")));
                urlParameters.add(new BasicNameValuePair("execution", data.getString("execution")));
            }

            loginPost.setEntity(new UrlEncodedFormEntity(urlParameters));

            LOGGER.info(loginPost.getURI().toString());
            resp = httpClient.execute(loginPost);
            LOGGER.info(resp.toString());

            respContent = EntityUtils.toString(resp.getEntity(), HTTP.UTF_8);
            if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK
                    && MapUtils.isEmpty((Map)(parserAcResponse(respContent).getData()))) {
                this.release(resp);
                return this.isLogin(url);
            }

            if(resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                LOGGER.error("Login Failed:  " + resp.getStatusLine());
                LOGGER.error("   " + EntityUtils.toString(resp.getEntity()));
                return false;
            }
            this.release(resp);
            LOGGER.info("Login AC response:  " + respContent);
            json = JSONObject.fromObject(respContent);
            if(json.getInt("errCode") != 0) {
                LOGGER.info("Login failed:  " + respContent);
                return false;
            }

        } catch(IOException e) {
            LOGGER.error("Login Failed. ", e);
        }

        return this.isLogin(url);
    }

    @SuppressWarnings("rawtypes")
    private static OverlayVpnDriverResponse parserAcResponse(String content) {
        if(!StringUtils.hasLength(content)) {
            return null;
        }

        JSONObject respoenJO = JSONObject.fromObject(content);
        if(null == respoenJO) {
            return null;
        }

        return JsonUtil.fromJson(respoenJO.toString(), OverlayVpnDriverResponse.class);
    }

    private String getHttpsUrl() {
        return "https://" + acIp + ":" + acPort;
    }

    private void release(HttpResponse resp) throws IOException {
        if(resp.getEntity() != null) {
            LOGGER.info("Release response.");
            EntityUtils.consume(resp.getEntity());
        }
    }

    /**
     * <br/>
     * 
     * @param url URL for the login
     * @return
     * @since SDNO 0.5
     */
    @SuppressWarnings("deprecation")
    public boolean isLogin(String url) {
        try {
            final String httpsUrl = getHttpsUrl();

            HttpGet request = new HttpGet(httpsUrl + ControllerUrlConst.EXTERNAL_REDIRECT);
            request.addHeader("Content-Type", APPLICATION_JSON);
            request.addHeader("X-External-Redirect", "true");

            LOGGER.info(request.toString());
            HttpResponse resp = httpClient.execute(request);
            LOGGER.info(resp.toString());

            String respContent = null;
            if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                respContent = EntityUtils.toString(resp.getEntity(), HTTP.UTF_8);

                LOGGER.info("IsLogin Response: " + respContent);
                this.release(resp);
                return true;
            }
            this.release(resp);
        } catch(ClientProtocolException e) {
            LOGGER.error("Login Failed. ", e);
        } catch(ParseException e) {
            LOGGER.error("Login Failed. ", e);
        } catch(IOException e) {
            LOGGER.error("Login Failed. ", e);
        }
        return false;
    }

    /**
     * Send post restful request. <br/>
     *
     * @param restUrl restful URL path
     * @param body message body
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    @SuppressWarnings("deprecation")
    public HTTPReturnMessage post(final String restUrl, final String body) {
        String postUrl = this.getHttpsUrl() + restUrl;
        LOGGER.info("Post Request url: " + restUrl);

        HttpPost httpPost = new HttpPost(postUrl);

        if(StringUtils.hasLength(body)) {
            StringEntity reqEntity = new StringEntity(body, HTTP.UTF_8);
            httpPost.setEntity(reqEntity);
        }

        return commonRequest(httpPost);
    }

    /**
     * Send get restful request. <br/>
     *
     * @param restUrl restful URL path
     * @param body message body
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    public HTTPReturnMessage get(final String restUrl) {
        LOGGER.info("Get Request url: " + restUrl);
        return commonRequest(new HttpGet(this.getHttpsUrl() + restUrl));
    }

    /**
     * Send put restful request. <br/>
     *
     * @param restUrl restful URL path
     * @param body message body
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    @SuppressWarnings("deprecation")
    public HTTPReturnMessage put(final String restUrl, final String body) {
        HttpPut httpPut = new HttpPut(this.getHttpsUrl() + restUrl);

        if(StringUtils.hasLength(body)) {
            StringEntity reqEntity = new StringEntity(body, HTTP.UTF_8);
            httpPut.setEntity(reqEntity);
        }

        return commonRequest(httpPut);
    }

    /**
     * Send delete restful request. <br/>
     *
     * @param restUrl restful URL path
     * @param body message body
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    @SuppressWarnings("deprecation")
    public HTTPReturnMessage delete(final String restUrl, final String body) {
        String postUrl = this.getHttpsUrl() + restUrl;
        LOGGER.info("Put Request url for AC: " + restUrl);
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(postUrl);

        if(StringUtils.hasLength(body)) {
            StringEntity reqEntity = new StringEntity(body, HTTP.UTF_8);
            httpDelete.setEntity(reqEntity);
        }
        return commonRequest(httpDelete);
    }

    @SuppressWarnings("deprecation")
    private HTTPReturnMessage commonRequest(HttpRequestBase requestBase) {
        HTTPReturnMessage msg = new HTTPReturnMessage();
        msg.setStatus(FAILED);
        if(!isParamValide()) {
            LOGGER.warn("AC Login commonRequest is inValide, Login failed.");
            return msg;
        }

        requestBase.addHeader("Content-Type", APPLICATION_JSON);
        requestBase.addHeader("Accept", APPLICATION_JSON);

        try {
            LOGGER.info(requestBase.toString());
            HttpResponse resp = httpClient.execute(requestBase);
            LOGGER.info(resp.toString());

            String respContent = EntityUtils.toString(resp.getEntity(), HTTP.UTF_8);
            msg.setBody(respContent);
            msg.setStatus(resp.getStatusLine().getStatusCode());
            this.release(resp);
        } catch(UnsupportedEncodingException e) {
            LOGGER.warn("Do Post Request Failed.", e);
        } catch(ClientProtocolException e) {
            LOGGER.warn("Do Post Request Failed.", e);
        } catch(ParseException e) {
            LOGGER.warn("Do Post Request Failed.", e);
        } catch(IOException e) {
            LOGGER.warn("Do Post Request Failed.", e);
        } catch(IllegalStateException e) {
            LOGGER.warn("Do Post Request Failed.", e);
        }

        return msg;
    }

    private boolean isParamValide() {
        return (null != this.acIp) && (null != this.acLoginName) && (null != this.acLoginPassword)
                && (null != this.acPort);
    }

    @Override
    public String toString() {
        return "OverlayVpnDriverSsoProxy [acIp=" + acIp + ", acPort=" + acPort + ", acLoginName=" + acLoginName + "]";
    }
}

class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

    private static final String METHOD_NAME = "DELETE";

    /**
     * Constructor<br/>
     * <p>
     * </p>
     * 
     * @param uri Creates a URI by parsing the given URI string.
     * @since SDNO 0.5
     */
    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    /**
     * Constructor<br/>
     * <p>
     * </p>
     * 
     * @param uri URI of the body.
     * @since SDNO 0.5
     */
    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }

    /**
     * Constructor<br/>
     * <p>
     * </p>
     * 
     * @since SDNO 0.5
     */
    public HttpDeleteWithBody() {
        super();
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}
