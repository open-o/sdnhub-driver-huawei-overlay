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

/**
 * Model class for port vlan in adapter layer. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jul 20, 2016
 */
public class NetPortVlan {

    private String port;

    private int vlan;

    /**
     * Constructor<br/>
     * 
     * @param port The port
     * @param vlan The vlan id
     * @since SDNO 0.5
     */
    public NetPortVlan(String port, int vlan) {
        this.port = port;
        this.vlan = vlan;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getVlan() {
        return vlan;
    }

    public void setVlan(int vlan) {
        this.vlan = vlan;
    }
}
