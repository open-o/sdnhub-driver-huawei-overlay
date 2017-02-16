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

package org.openo.sdnhub.overlayvpndriver.common.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
    public class TrustAllX509TrustManager implements X509TrustManager {

    /**
     *
     * <br/>
     *
     * @return
     * @since   SDNHUB 0.5
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    /**
     *
     * <br/>
     *
     * @param certs
     * @param authType
     * @throws CertificateException
     * @since   SDNHUB 0.5
     */
    @Override
    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
        //Do Nothing
    }

    /**
     *
     * <br/>
     *
     * @param certs
     * @param authType
     * @throws CertificateException
     * @since   SDNHUB 0.5
     */
    @Override
    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
        //Do Nothing
    }
}
