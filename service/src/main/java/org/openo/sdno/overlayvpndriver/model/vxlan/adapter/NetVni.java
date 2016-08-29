/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.overlayvpndriver.model.vxlan.adapter;

import java.util.List;

import org.openo.sdno.overlayvpn.model.uuid.AbstUuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Model class for vni in adapter layer. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jul 20, 2016
 */
public class NetVni extends AbstUuidModel {

    private boolean deleteMode;

    @AInt
    private int vni;

    @AString(require = true)
    private String macLearingMode;

    private String evpnRtMode;

    private String evpnRtExport;

    private String evpnRtImport;

    private List<String> peerAddresslist;

    private List<String> portlist;

    private List<Integer> vlanlist;

    private List<NetPortVlan> portvlanlist;

    public int getVni() {
        return vni;
    }

    public void setVni(int vni) {
        this.vni = vni;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public String getMacLearingMode() {
        return macLearingMode;
    }

    public void setMacLearingMode(String macLearingMode) {
        this.macLearingMode = macLearingMode;
    }

    public String getEvpnRtMode() {
        return evpnRtMode;
    }

    public void setEvpnRtMode(String evpnRtMode) {
        this.evpnRtMode = evpnRtMode;
    }

    public String getEvpnRtExport() {
        return evpnRtExport;
    }

    public void setEvpnRtExport(String evpnRtExport) {
        this.evpnRtExport = evpnRtExport;
    }

    public String getEvpnRtImport() {
        return evpnRtImport;
    }

    public void setEvpnRtImport(String evpnRtImport) {
        this.evpnRtImport = evpnRtImport;
    }

    public List<String> getPeerAddresslist() {
        return peerAddresslist;
    }

    public void setPeerAddresslist(List<String> peerAddresslist) {
        this.peerAddresslist = peerAddresslist;
    }

    public List<String> getPortlist() {
        return portlist;
    }

    public void setPortlist(List<String> portlist) {
        this.portlist = portlist;
    }

    public List<Integer> getVlanlist() {
        return vlanlist;
    }

    public void setVlanlist(List<Integer> vlanlist) {
        this.vlanlist = vlanlist;
    }

    public List<NetPortVlan> getPortvlanlist() {
        return portvlanlist;
    }

    public void setPortvlanlist(List<NetPortVlan> portvlanlist) {
        this.portvlanlist = portvlanlist;
    }
}
