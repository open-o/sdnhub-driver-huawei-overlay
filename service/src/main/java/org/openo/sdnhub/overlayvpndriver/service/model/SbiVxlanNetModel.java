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

package org.openo.sdnhub.overlayvpndriver.service.model;

import org.openo.sdno.overlayvpn.model.v2.basemodel.BaseModel;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * Sbi Basic Service Model.<br>
 *
 * @author
 * @version SDNHUB 0.5 March 8, 2017
 */
public class SbiVxlanNetModel extends BaseModel {

    /**
     * Source Device Id
     */
    @AUuid(require = true)
    private String deviceId;

    /**
     * Peer Device Id
     */
    @AUuid(require = false)
    private String peerDeviceId;

    /**
     * Controller Id
     */
    @AUuid(require = false)
    private String controllerId;

    /**
     * Connection Id
     */
    @AUuid(require = true)
    private String connectionId = null;

    /**
     * External Id
     */
    @AUuid(require = false)
    private String externalId = null;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPeerDeviceId() {
        return peerDeviceId;
    }

    public void setPeerDeviceId(String peerDeviceId) {
        this.peerDeviceId = peerDeviceId;
    }

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
