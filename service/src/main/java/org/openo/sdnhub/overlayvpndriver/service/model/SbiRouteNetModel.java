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

import java.util.Objects;

import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

public class SbiRouteNetModel extends BaseModel {

    @AUuid(require = true)
    private String deviceId = null;

    @AUuid(require = false)
    private String controllerId = null;

    @AUuid(require = false)
    private String externalId = null;

    @AUuid(require = true)
    private String nbiNeRouteId = null;

    /**
     * device ID
     *
     * @return deviceId
     **/
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * controller ID
     *
     * @return controllerId
     **/
    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    /**
     * external ID
     *
     * @return externalId
     **/
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * the UUID of nbi model
     *
     * @return nbiNeRouteId
     **/
    public String getNbiNeRouteId() {
        return nbiNeRouteId;
    }

    public void setNbiNeRouteId(String nbiNeRouteId) {
        this.nbiNeRouteId = nbiNeRouteId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SbiBaseNetModel {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
        sb.append("    controllerId: ").append(toIndentedString(controllerId)).append("\n");
        sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
        sb.append("    nbiNeRouteId: ").append(toIndentedString(nbiNeRouteId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toIndentedString(Object o) {
        if(o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
    /**
     * Override equals Function.<br>
     *
     * @param obj other Object
     * @return true if this object equals to other object
     * @since SDNO 0.5
     */

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        SbiRouteNetModel other = (SbiRouteNetModel) obj;

        if (!Objects.equals(deviceId, other.deviceId)) {
            return false;
        }

        if (!Objects.equals(controllerId, other.controllerId)) {
            return false;
        }

        if (!Objects.equals(externalId, other.externalId)) {
            return false;
        }

        if (!Objects.equals(nbiNeRouteId, other.nbiNeRouteId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, controllerId, externalId, nbiNeRouteId);
    }

}
