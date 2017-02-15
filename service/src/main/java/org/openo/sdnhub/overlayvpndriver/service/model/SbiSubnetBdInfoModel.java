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

/**
 * Model class of Subnet Bd Information.<br>
 *
 * @author
 * @version SDNHUB 0.5 2017-1-4
 */
public class SbiSubnetBdInfoModel {

    /**
     * Controller Id
     */
    private String controllerId;

    /**
     * Ne Id
     */
    private String neId;

    /**
     * Device Id
     */
    private String deviceId;

    /**
     * Vni
     */
    private String vni;

    /**
     * Subnet Id
     */
    private String subnetId;

    /**
     * Bd Id
     */
    private String bdId;

    /**
     * Virtual Bd Interface Name
     */
    private String vbdifName;

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public String getNeId() {
        return neId;
    }

    public void setNeId(String neId) {
        this.neId = neId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVni() {
        return vni;
    }

    public void setVni(String vni) {
        this.vni = vni;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getBdId() {
        return bdId;
    }

    public void setBdId(String bdId) {
        this.bdId = bdId;
    }

    public String getVbdifName() {
        return vbdifName;
    }

    public void setVbdifName(String vbdifName) {
        this.vbdifName = vbdifName;
    }
}
