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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;

import java.util.List;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class IpsecConnList extends UuidModel {

    @JsonIgnore
    private boolean createFlag = true;

    private String name;

    private String interfaceName;

    private List<IpsecConnection> ipsecConnection;

    @JsonIgnore
    private String serviceId;

    /**
     * Initialization by default values.
     */
    public IpsecConnList()
    {
        super();
    }

    /**
     * Initialization by interface name.
     */
    public IpsecConnList(String subInterfaceName)
    {
        super();
        setInterfaceName(subInterfaceName);
    }

    /**
     * @return Returns the create flag.
     */
    public boolean isCreateFlag()
    {
        return createFlag;
    }

    /**
     * @param createFlag create flag.
     */
    public void setCreateFlag(boolean createFlag)
    {
        this.createFlag = createFlag;
    }

    /**
     * @return Returns ip-sec name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name ip-sec name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the interface name.
     */
    public String getInterfaceName()
    {
        return interfaceName;
    }

    /**
     * @param interfaceName interface name.
     */
    public void setInterfaceName(String interfaceName)
    {
        this.interfaceName = interfaceName;
    }

    /**
     * @return Returns ip-sec connection.
     */
    public List<IpsecConnection> getIpsecConnection()
    {
        return ipsecConnection;
    }

    /**
     * @param ipsecConnection ip-sec connection.
     */
    public void setIpsecConnection(List<IpsecConnection> ipsecConnection)
    {
        this.ipsecConnection = ipsecConnection;
    }

    /**
     * @return Returns the service id..
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId service id.
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "ClassPojo [interfaceName = " + interfaceName + ", name = " + name + ", ipsecConnection = "
                + ipsecConnection + "]";
    }
    /**
     * overriding super class equals method
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

        IpsecConnList other = (IpsecConnList) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }

}
