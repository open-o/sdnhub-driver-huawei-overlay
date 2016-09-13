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

package org.openo.sdno.overlayvpndriver.model.ipsec.adapter;

/**
 * Model class for IKE policy in adapter layer. <br>
 * 
 * @author
 * @version SDNO 0.5 Jul 18, 2016
 */
public class NetIke {

    private String authAlgorithm;

    private String encryptionAlgorithm;

    private String version;

    private String localAddress;

    private String peerAddress;

    private String preSharedKey;

    /**
     * Constructor<br>
     * 
     * @since SDNO 0.5
     */
    public NetIke() {
        super();
    }

    /**
     * Constructor<br>
     * 
     * @param authAlgorithm The hash algorithm
     * @param encryptionAlgorithm The encrypt algorithm
     * @param version The ike version
     * @param peerAddress The peer address
     * @param preSharedKey pre share key
     * @since SDNO 0.5
     */
    public NetIke(String authAlgorithm, String encryptionAlgorithm, String version, String peerAddress,
            String preSharedKey) {
        super();

        this.setAuthAlgorithm(authAlgorithm);
        this.setEncryptionAlgorithm(encryptionAlgorithm);
        this.setPeerAddress(peerAddress);
        this.setVersion(version);
        this.setPreSharedKey(preSharedKey);
    }

    public String getAuthAlgorithm() {
        return authAlgorithm;
    }

    public void setAuthAlgorithm(String authAlgorithm) {
        this.authAlgorithm = authAlgorithm;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getPeerAddress() {
        return peerAddress;
    }

    public void setPeerAddress(String peerAddress) {
        this.peerAddress = peerAddress;
    }

    public String getPreSharedKey() {
        return preSharedKey;
    }

    public void setPreSharedKey(String preSharedKey) {
        this.preSharedKey = preSharedKey;
    }
}
