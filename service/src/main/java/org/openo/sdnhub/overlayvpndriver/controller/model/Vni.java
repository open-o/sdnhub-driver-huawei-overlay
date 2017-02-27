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

import java.util.List;

import org.openo.sdno.overlayvpn.model.uuid.AbstUuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Model class for vni in adapter layer. <br>
 *
 * @author
 * @version SDNHUB Driver 0.5 Jan 20, 2017
 */
public class Vni extends AbstUuidModel {

    private boolean deleteMode;

    @AInt
    private int vni;

    @AString(require = true)
    private String macLearingMode;

    private String evpnRtMode;

    private String evpnRtExport;

    private String evpnRtImport;

    private List<String> peerAddresslist;

    private boolean broadCastManager;

    private List<VniFilter> filterList;

    private List<String> portlist;

    private List<Integer> vlanlist;

    private List<PortVlan> portvlanlist;

    private boolean qosPreClassify;

    /**
     * Initialize default values.
     */
    public Vni() {
        super();
        deleteMode = false;
        qosPreClassify = false;
    }

    /**
     * @return Returns mode of delete.
     */
    public boolean isDeleteMode()
    {
        return deleteMode;
    }

    /**
     * @param deleteMode mode of delete.
     */
    public void setDeleteMode(boolean deleteMode)
    {
        this.deleteMode = deleteMode;
    }

    /**
     * @return Returns the vni.
     */
    public int getVni()
    {
        return vni;
    }

    /**
     * @param vni vni.
     */
    public void setVni(int vni)
    {
        this.vni = vni;
    }

    /**
     * @return Returns mac learning mode.
     */
    public String getMacLearingMode()
    {
        return macLearingMode;
    }

    /**
     * @param macLearingMode mac learning mode.
     */
    public void setMacLearingMode(String macLearingMode)
    {
        this.macLearingMode = macLearingMode;
    }

    /**
     * @return Returns evpn rt mode.
     */
    public String getEvpnRtMode()
    {
        return evpnRtMode;
    }

    /**
     * @param evpnRtMode evpn rt mode.
     */
    public void setEvpnRtMode(String evpnRtMode)
    {
        this.evpnRtMode = evpnRtMode;
    }

    /**
     * @return Returns evpn rt export.
     */
    public String getEvpnRtExport()
    {
        return evpnRtExport;
    }

    /**
     * @param evpnRtExport evpn rt export.
     */
    public void setEvpnRtExport(String evpnRtExport)
    {
        this.evpnRtExport = evpnRtExport;
    }

    /**
     * @return Returns evpn rt import.
     */
    public String getEvpnRtImport()
    {
        return evpnRtImport;
    }

    /**
     * @param evpnRtImport evpn rt import.
     */
    public void setEvpnRtImport(String evpnRtImport)
    {
        this.evpnRtImport = evpnRtImport;
    }

    /**
     * @return Returns peer address list.
     */
    public List<String> getPeerAddresslist()
    {
        return peerAddresslist;
    }

    /**
     * @param peerAddresslist peer address list.
     */
    public void setPeerAddresslist(List<String> peerAddresslist)
    {
        this.peerAddresslist = peerAddresslist;
    }

    /**
     * @return Returns broadcast manager.
     */
    public boolean isBroadCastManager()
    {
        return broadCastManager;
    }

    /**
     * @param broadCastManager broadcast manager.
     */
    public void setBroadCastManager(boolean broadCastManager)
    {
        this.broadCastManager = broadCastManager;
    }

    /**
     * @return Returns the filter list.
     */
    public List<VniFilter> getFilterList()
    {
        return filterList;
    }

    /**
     * @param filterList filter list.
     */
    public void setFilterList(List<VniFilter> filterList)
    {
        this.filterList = filterList;
    }

    /**
     * @return Returns the port list.
     */
    public List<String> getPortlist()
    {
        return portlist;
    }

    /**
     * @param portlist port list.
     */
    public void setPortlist(List<String> portlist)
    {
        this.portlist = portlist;
    }

    /**
     * @return Returns the collection of vlan id.
     */
    public List<Integer> getVlanlist()
    {
        return vlanlist;
    }

    /**
     * @param vlanlist collection of vlan id.
     */
    public void setVlanlist(List<Integer> vlanlist)
    {
        this.vlanlist = vlanlist;
    }

    /**
     * @return Returns the collection of port vlan id.
     */
    public List<PortVlan> getPortvlanlist()
    {
        return portvlanlist;
    }

    /**
     * @param portvlanlist collection of port vlan id.
     */
    public void setPortvlanlist(List<PortVlan> portvlanlist)
    {
        this.portvlanlist = portvlanlist;
    }

    /**
     * @return Returns true if quality of service pre classification enabled.
     */
    public boolean isQosPreClassify()
    {
        return qosPreClassify;
    }

    /**
     * @param qosPreClassify quality of service pre classification.
     */
    public void setQosPreClassify(boolean qosPreClassify)
    {
        this.qosPreClassify = qosPreClassify;
    }
}
