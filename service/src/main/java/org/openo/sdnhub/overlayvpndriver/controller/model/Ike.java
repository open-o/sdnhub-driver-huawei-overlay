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

package org.openo.sdnhub.overlayvpndriver.controller.model;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class Ike {

    private String preSharedKey;

    private String authAlgorithm;

    private PeerId peerId;

    private String localAddress;

    private String peerAddress;

    private LocalId localId;

    private String encryptionAlgorithm;

    private String version;

    private String dh;

    public Ike() {
        super();
    }

    public Ike(String authAlgorithm, String encryptionAlgorithm, String version, String peerAddress,
            String preSharedKey) {
        super();
        this.setAuthAlgorithm(authAlgorithm);
        this.setEncryptionAlgorithm(encryptionAlgorithm);
        this.setVersion(version);
        this.setPreSharedKey(preSharedKey);
    }

    public String getPreSharedKey() {
        return preSharedKey;
    }

    public void setPreSharedKey(String preSharedKey) {
        this.preSharedKey = preSharedKey;
    }

    public String getAuthAlgorithm() {
        return authAlgorithm;
    }

    public void setAuthAlgorithm(String authAlgorithm) {
        this.authAlgorithm = authAlgorithm;
    }

    public PeerId getPeerId() {
        return peerId;
    }

    public void setPeerId(PeerId peerId) {
        this.peerId = peerId;
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

    public LocalId getLocalId() {
        return localId;
    }

    public void setLocalId(LocalId localId) {
        this.localId = localId;
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

    public String getDh() {
        return dh;
    }


    public void setDh(String dh) {
        this.dh = dh;
    }

    @Override
    public String toString() {
        return "ClassPojo [preSharedKey = " + preSharedKey + ", authAlgorithm = " + authAlgorithm + ", peerId = "
                + peerId + ", localAddress = " + localAddress + ", peerAddress = " + peerAddress + ", localId = "
                + localId + ", encryptionAlgorithm = " + encryptionAlgorithm + ", version = " + version + "]";
    }
}
