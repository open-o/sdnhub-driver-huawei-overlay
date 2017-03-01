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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openo.sdno.overlayvpn.model.v2.basemodel.BaseModel;
import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * Class of IpSec Net Model.<br>
 *
 * @author
 * @version SDNHUB 0.5 Feb 14, 2017
 */
public abstract class SbiIpSecNetModel extends BaseModel {

    /**
     * Controller Id
     */
    @AUuid(require = false)
    private String controllerId;

    /**
     * External Id
     */
    @AUuid(require = false)
    private String externalId;

    /**
     * Connection Id
     */
    @AUuid(require = true)
    private String connectionServiceId;

    /**
     * Device Ne Id
     */
    @AUuid(require = true)
    private String neId;

    /**
     * Peer Device Ne Id
     */
    @JsonIgnore
    @AUuid(require = false)
    private String peerNeId;

    /**
     * Device Id
     */
    @AString(require = true, min = 0, max = 255)
    private String deviceId;

    /**
     * Peer device Id
     */
    @AString(require = false, min = 0, max = 255)
    private String peerDeviceId;

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public String getConnectionServiceId() {
        return connectionServiceId;
    }

    public void setConnectionServiceId(String connectionServiceId) {
        this.connectionServiceId = connectionServiceId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getNeId() {
        return neId;
    }

    public void setNeId(String neId) {
        this.neId = neId;
    }

    public String getPeerNeId() {
        return peerNeId;
    }

    public void setPeerNeId(String peerNeId) {
        this.peerNeId = peerNeId;
    }

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

    /**
     * Override equals Function.<br>
     *
     * @param obj other Object
     * @return true if this object equals to other object
     * @since SDNO 0.5
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (super.getClass() != obj.getClass()) {
            return false;
        }

        SbiIpSecNetModel other = (SbiIpSecNetModel) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (controllerId != null ? controllerId.hashCode() : 0);
        result = 31 * result + (externalId != null ? externalId.hashCode() : 0);
        result = 31 * result + (connectionServiceId != null ? connectionServiceId.hashCode() : 0);
        result = 31 * result + (neId != null ? neId.hashCode() : 0);
        result = 31 * result + (peerNeId != null ? peerNeId.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (peerDeviceId != null ? peerDeviceId.hashCode() : 0);
        return result;
    }
}
