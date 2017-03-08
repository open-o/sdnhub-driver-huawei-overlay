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
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Class of SbiNeVxlanInstance Model.<br>
 *
 * @author
 * @version SDNHUB 0.5 March 8, 2017
 */
@MOResType(infoModelName = "overlayvpn_tenant_sbi_vxlaninstance")
public class SbiNeVxlanInstance extends SbiVxlanNetModel {

    private String nbiVxlanTunnelId = null;

    /**
     * VxLAN Network Identifier
     */
    @AInt(require = true, min = 1, max = 16777215)
    private String vni = null;

    @AString(scope = "true,false")
    private String qosPreClassify;

    /**
     * ArpProxy Enable or not, default is false
     */
    @AString(scope = "true,false")
    private String arpProxy = null;

    /**
     * ArpBroadcast Suppress Enable or not,default is false
     */
    @AString(scope = "true,false")
    private String arpBroadcastSuppress = null;

    private String keepAlive = null;

    /**
     * Related VxLan Interfaces
     */
    @NONInvField
    @JsonIgnore
    private List<String> vxlanInterfaces = new ArrayList<String>();

    /**
     * Related VxLan Tunnels
     */
    @NONInvField
    @JsonIgnore
    private List<String> vxlanTunnels = new ArrayList<String>();

    @NONInvField
    private List<SbiNeVxlanInterface> vxlanInterfaceList = new ArrayList<SbiNeVxlanInterface>();

    @NONInvField
    private List<SbiNeVxlanTunnel> vxlanTunnelList = new ArrayList<SbiNeVxlanTunnel>();

    public String getNbiVxlanTunnelId() {
        return nbiVxlanTunnelId;
    }

    public void setNbiVxlanTunnelId(String nbiVxlanTunnelId) {
        this.nbiVxlanTunnelId = nbiVxlanTunnelId;
    }

    public String getVni() {
        return vni;
    }

    public void setVni(String vni) {
        this.vni = vni;
    }

    public String getArpProxy() {
        return arpProxy;
    }

    public void setArpProxy(String arpProxy) {
        this.arpProxy = arpProxy;
    }

    public String getArpBroadcastSuppress() {
        return arpBroadcastSuppress;
    }

    public void setArpBroadcastSuppress(String arpBroadcastSuppress) {
        this.arpBroadcastSuppress = arpBroadcastSuppress;
    }

    public String getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(String keepAlive) {
        this.keepAlive = keepAlive;
    }

    public List<String> getVxlanInterfaces() {
        return vxlanInterfaces;
    }

    public void setVxlanInterfaces(List<String> vxlanInterfaces) {
        this.vxlanInterfaces = vxlanInterfaces;
    }

    public List<String> getVxlanTunnels() {
        return vxlanTunnels;
    }

    public void setVxlanTunnels(List<String> vxlanTunnels) {
        this.vxlanTunnels = vxlanTunnels;
    }

    public List<SbiNeVxlanInterface> getVxlanInterfaceList() {
        return vxlanInterfaceList;
    }

    public void setVxlanInterfaceList(List<SbiNeVxlanInterface> vxlanInterfaceList) {
        this.vxlanInterfaceList = vxlanInterfaceList;
    }

    public List<SbiNeVxlanTunnel> getVxlanTunnelList() {
        return vxlanTunnelList;
    }

    public void setVxlanTunnelList(List<SbiNeVxlanTunnel> vxlanTunnelList) {
        this.vxlanTunnelList = vxlanTunnelList;
    }

    public String getQosPreClassify() {
        return qosPreClassify;
    }

    public void setQosPreClassify(String qosPreClassify) {
        this.qosPreClassify = qosPreClassify;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SbiNeVxlanInstance {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    nbiVxlanTunnelId: ").append(toIndentedString(nbiVxlanTunnelId)).append("\n");
        sb.append("    vni: ").append(toIndentedString(vni)).append("\n");
        sb.append("    arpProxy: ").append(toIndentedString(arpProxy)).append("\n");
        sb.append("    arpBroadcastSuppress: ").append(toIndentedString(arpBroadcastSuppress)).append("\n");
        sb.append("    keepAlive: ").append(toIndentedString(keepAlive)).append("\n");
        sb.append("    vxlanInterfaces: ").append(toIndentedString(vxlanInterfaces)).append("\n");
        sb.append("    vxlanTunnels: ").append(toIndentedString(vxlanTunnels)).append("\n");
        sb.append("    vxlanInterfaceList: ").append(toIndentedString(vxlanInterfaceList)).append("\n");
        sb.append("    vxlanTunnelList: ").append(toIndentedString(vxlanTunnelList)).append("\n");
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
