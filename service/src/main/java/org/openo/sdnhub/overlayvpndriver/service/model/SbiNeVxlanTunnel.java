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

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.NONInvField;
import org.openo.sdno.overlayvpn.verify.annotation.AIp;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * Class of SbiNeVxlanTunnel Model.<br>
 *
 * @author
 * @version SDNO 0.5 June 6, 2016
 */
@MOResType(infoModelName = "overlayvpn_tenant_sbi_nevxlantunnel")
public class SbiNeVxlanTunnel extends SbiVxlanNetModel {

    @JsonIgnore
    private String vni = null;

    @AUuid(require = true)
    private String vxlanInstanceId = null;

    @AIp(require = true)
    private String sourceAddress = null;

    @AIp(require = true)
    private String destAddress = null;

    private String sourceIfId = null;

    private String tunnelIfId = null;

    @NONInvField
    private List<String> vxlanInstances = new ArrayList<String>();

    @NONInvField
    private List<String> vnis = new ArrayList<String>();

    public String getVni() {
        return vni;
    }

    public void setVni(String vni) {
        this.vni = vni;
    }

    public String getVxlanInstanceId() {
        return vxlanInstanceId;
    }

    public void setVxlanInstanceId(String vxlanInstanceId) {
        this.vxlanInstanceId = vxlanInstanceId;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getSourceIfId() {
        return sourceIfId;
    }

    public void setSourceIfId(String sourceIfId) {
        this.sourceIfId = sourceIfId;
    }

    public String getTunnelIfId() {
        return tunnelIfId;
    }

    public void setTunnelIfId(String tunnelIfId) {
        this.tunnelIfId = tunnelIfId;
    }

    public List<String> getVxlanInstances() {
        return vxlanInstances;
    }

    public void setVxlanInstances(List<String> vxlanInstances) {
        this.vxlanInstances = vxlanInstances;
    }

    public List<String> getVnis() {
        return vnis;
    }

    public void setVnis(List<String> vnis) {
        this.vnis = vnis;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SbiNeVxlanTunnel {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    vni: ").append(toIndentedString(vni)).append("\n");
        sb.append("    vxlanInstanceId: ").append(toIndentedString(vxlanInstanceId)).append("\n");
        sb.append("    sourceAddress: ").append(toIndentedString(sourceAddress)).append("\n");
        sb.append("    destAddress: ").append(toIndentedString(destAddress)).append("\n");
        sb.append("    sourceIfId: ").append(toIndentedString(sourceIfId)).append("\n");
        sb.append("    tunnelIfId: ").append(toIndentedString(tunnelIfId)).append("\n");
        sb.append("    vxlanInstances: ").append(toIndentedString(vxlanInstances)).append("\n");
        sb.append("    vnis: ").append(toIndentedString(vnis)).append("\n");
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
