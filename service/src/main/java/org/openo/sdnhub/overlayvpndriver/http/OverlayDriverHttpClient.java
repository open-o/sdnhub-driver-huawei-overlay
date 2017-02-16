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

package org.openo.sdnhub.overlayvpndriver.http;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class OverlayDriverHttpClient {

    private static final String SSLCONTEST_TLS = "TLSV1.2";

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OverlayDriverHttpClient.class);

    private HttpClient httpClient;

    private static class TrustAllX509TrustManager implements X509TrustManager {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            // Do Nothing
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            // Do Nothing
        }
    }

    /**
     * <br/>
     *
     * @since SDNHUB 0.5
     */
    public void login() {
        try {
            SSLContext sslcontext = SSLContext.getInstance(SSLCONTEST_TLS);
            sslcontext.init(null, new TrustManager[] {new TrustAllX509TrustManager()},
                    new java.security.SecureRandom());

            X509HostnameVerifier hostnameVerifier = new AllowAllHostnameVerifier();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext, hostnameVerifier)).build();

            HttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            httpClient = HttpClients.custom().setConnectionManager(connManager)
                    .setRedirectStrategy(new LaxRedirectStrategy()).build();
        } catch(Exception e) {
            LOGGER.error("Exception in http client", e);
        }
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
