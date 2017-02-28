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

import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class EthInterfaceConfig {

    @AUuid
    @AString(require = true)
    private String id;

    @AString(require = true)
    private String name;

    private boolean enable;

    private boolean autoNegotiationEnable;

    @AString(require = true, scope = "'1','2'")
    private String ifAttr;

    private Integer defaultVlan;

    private String trunkVlan;

    private String description;

    @AString(require = true, scope = "'auto','half','full'")
    private String duplex;

    @AString(require = true, scope = "'0','1','2'")
    private String nacType;

    @AString(require = true, scope = "'auto','10','100','1000'")
    private String speed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultVlan() {
        return defaultVlan;
    }

    public void setDefaultVlan(Integer defaultVlan) {
        this.defaultVlan = defaultVlan;
    }

    public String getTrunkVlan() {
        return trunkVlan;
    }

    public void setTrunkVlan(String trunkVlan) {
        this.trunkVlan = trunkVlan;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isAutoNegotiationEnable() {
        return autoNegotiationEnable;
    }

    public void setAutoNegotiationEnable(boolean autoNegotiationEnable) {
        this.autoNegotiationEnable = autoNegotiationEnable;
    }

    public String getIfAttr() {
        return ifAttr;
    }

    public void setIfAttr(String ifAttr) {
        this.ifAttr = ifAttr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuplex() {
        return duplex;
    }

    public void setDuplex(String duplex) {
        this.duplex = duplex;
    }

    public String getNacType() {
        return nacType;
    }

    public void setNacType(String nacType) {
        this.nacType = nacType;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

}
