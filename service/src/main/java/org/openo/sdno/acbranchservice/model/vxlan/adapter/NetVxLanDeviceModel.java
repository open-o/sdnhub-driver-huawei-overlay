/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.acbranchservice.model.vxlan.adapter;

import java.util.List;

import org.openo.sdno.overlayvpn.model.uuid.AbstUuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AIp;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Model converting class, converting SDNO model to adapter model. <br>
 * 
 * @author
 * @version SDNO 0.5 Jul 20, 2016
 */
public class NetVxLanDeviceModel extends AbstUuidModel {

    private int vneId;

    @AString(require = true)
    private String name;

    @AIp
    @AString(require = true)
    private String localAddress;

    private List<NetVni> vniilist;

    public int getVneId() {
        return vneId;
    }

    public void setVneId(int vneId) {
        this.vneId = vneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public List<NetVni> getVniilist() {
        return vniilist;
    }

    public void setVniilist(List<NetVni> vniilist) {
        this.vniilist = vniilist;
    }
}
