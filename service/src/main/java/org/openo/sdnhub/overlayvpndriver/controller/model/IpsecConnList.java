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

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class IpsecConnList {

    private String id;

    private String interfaceName;

    private String name;

    private List<IpsecConnection> ipsecConnection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IpsecConnection> getIpsecConnection() {
        return ipsecConnection;
    }

    public void setIpsecConnection(List<IpsecConnection> ipsecConnection) {
        this.ipsecConnection = ipsecConnection;
    }

    @Override
    public String toString() {
        return "ClassPojo [interfaceName = " + interfaceName + ", name = " + name + ", ipsecConnection = "
                + ipsecConnection + "]";
    }
}
