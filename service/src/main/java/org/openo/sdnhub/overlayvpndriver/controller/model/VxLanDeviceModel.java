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
import java.util.Objects;

import org.openo.sdno.overlayvpn.model.uuid.AbstUuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AIp;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Model converting class, converting SDNO model to adapter model. <br>
 *
 * @author
 * @version SDNHUB Driver 0.5 Jan 20, 2017
 */
public class VxLanDeviceModel extends AbstUuidModel {

    /**
     * vne id.
     */
    private int vneId;

    /**
     * vxlan tunnel name.
     */
    @AString(require = true)
    private String name;

    /**
     * local address
     */
    @AIp
    @AString(require = true)
    private String localAddress;

    /**
     * Collection of vni.
     */
    private List<Vni> vniList;

    /**
     * @return Returns vne id.
     */
    public int getVneId()
    {
        return vneId;
    }

    /**
     * @return vneId vne id.
     */
    public void setVneId(int vneId)
    {
        this.vneId = vneId;
    }

    /**
     * @return Returns vxlan tunnel name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name vxlan tunnel name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the local address.
     */
    public String getLocalAddress()
    {
        return localAddress;
    }

    /**
     * @param localAddress local address.
     */
    public void setLocalAddress(String localAddress)
    {
        this.localAddress = localAddress;
    }

    /**
     * @return Returns the collection of vni.
     */
    public List<Vni> getVniList()
    {
        return vniList;
    }

    /**
     * @param vniList collection of vni.
     */
    public void setVniList(List<Vni> vniList)
    {
        this.vniList = vniList;
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

        VxLanDeviceModel other = (VxLanDeviceModel)obj;

        if (!Objects.equals(vneId, other.vneId)) {
            return false;
        }

        if (!Objects.equals(name, other.name)) {
            return false;
        }

        if (!Objects.equals(localAddress, other.localAddress)) {
            return false;
        }

        if (!vniList.equals(other.vniList)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vneId, name, localAddress, vniList);
    }

}
