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

import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * Class of SbiNeVxlanInterface Model.<br>
 *
 * @author
 * @version SDNO 0.5 June 6, 2016
 */
@MOResType(infoModelName = "overlayvpn_tenant_sbi_nevxlaninterface")
public class SbiNeVxlanInterface extends SbiVxlanNetModel {

    @AString(min = 1, max = 36)
    private String localName = null;

    /**
     * VxLan Instance Id
     */
    @AUuid(require = true)
    private String vxlanInstanceId = null;

    /**
     * Access type: port/dot1q
     */
    private String accessType = null;

    /**
     * Port Id
     */
    @AUuid(require = true)
    private String portNativeId = null;

    /**
     * VLAN Range
     */
    @AInt(min = 1, max = 4094)
    private String dot1qVlanBitmap = null;

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getVxlanInstanceId() {
        return vxlanInstanceId;
    }

    public void setVxlanInstanceId(String vxlanInstanceId) {
        this.vxlanInstanceId = vxlanInstanceId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getPortNativeId() {
        return portNativeId;
    }

    public void setPortNativeId(String portNativeId) {
        this.portNativeId = portNativeId;
    }

    public String getDot1qVlanBitmap() {
        return dot1qVlanBitmap;
    }

    public void setDot1qVlanBitmap(String dot1qVlanBitmap) {
        this.dot1qVlanBitmap = dot1qVlanBitmap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SbiNeVxlanInterface {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    localName: ").append(toIndentedString(localName)).append("\n");
        sb.append("    vxlanInstanceId: ").append(toIndentedString(vxlanInstanceId)).append("\n");
        sb.append("    accessType: ").append(toIndentedString(accessType)).append("\n");
        sb.append("    portNativeId: ").append(toIndentedString(portNativeId)).append("\n");
        sb.append("    dot1qVlanBitmap: ").append(toIndentedString(dot1qVlanBitmap)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private static String toIndentedString(Object o) {
        if(o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
