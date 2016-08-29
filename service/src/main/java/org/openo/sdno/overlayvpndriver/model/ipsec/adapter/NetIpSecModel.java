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

package org.openo.sdno.overlayvpndriver.model.ipsec.adapter;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openo.sdno.overlayvpn.model.uuid.AbstUuidModel;

/**
 * Model converting class, converting SDNO model to adapter model. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jul 18, 2016
 */
public class NetIpSecModel extends AbstUuidModel {

    /**
     * Indicates whether it is created or modified, default is created.
     */
    @JsonIgnore
    private boolean createFlag = true;

    private String name;

    private String interfaceName;

    private List<NetIpSecConn> ipsecConnection;

    /**
     * Constructor<br/>
     * 
     * @since SDNO 0.5
     */
    public NetIpSecModel() {
        super();
    }

    /**
     * Constructor<br/>
     * 
     * @param subInterfaceName The subnet interface name
     * @since SDNO 0.5
     */
    public NetIpSecModel(String subInterfaceName) {
        super();
        setInterfaceName(subInterfaceName);
    }

    public boolean isCreateFlag() {
        return createFlag;
    }

    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<NetIpSecConn> getIpsecConnection() {
        return ipsecConnection;
    }

    public void setIpsecConnection(List<NetIpSecConn> ipsecConnection) {
        this.ipsecConnection = ipsecConnection;
    }
}
