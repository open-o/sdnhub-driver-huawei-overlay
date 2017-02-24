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

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.NONInvField;
import org.openo.sdno.overlayvpn.model.v2.basemodel.BaseServiceModel;
import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

/**
 * SBI model class of Nat Data.<br>
 *
 * @author
 * @version SDNHUB 0.5 2017-1-12
 */
@MOResType(infoModelName = "localsite_snatnetmodel")
public class SbiSnatNetModel extends BaseServiceModel {

    /**
     * Ne Uuid
     */
    @JsonProperty(value = "neId")
    @AUuid(require = true)
    private String neId;

    /**
     * AC Device Id
     */
    @JsonProperty(value = "deviceid")
    @AUuid(require = true)
    private String deviceId;

    /**
     * Controller Uuid
     */
    @AUuid(require = true)
    private String controllerId;

    /**
     * Nat Id
     */
    @AUuid(require = false)
    private String natId;

    /**
     * Internet gateway Uuid
     */
    @AUuid(require = true)
    private String internetGatewayId;

    /**
     * Sunbet Uuid
     */
    @AUuid(require = false)
    private String subnetId;

    /**
     * Interface name
     */
    @AString(require = true)
    private String ifName;

    /**
     * Subnet Ip Address
     */
    @JsonProperty(value = "privateCidr")
    @AString(require = false)
    private String privateIpAddress;

    /**
     * Sunbet prefix
     */
    @AString(require = false)
    private String privatePrefix;

    /**
     * Acl number
     */
    @AString(require = true)
    private String aclNumber;

    /**
     * Acl Id
     */
    @AString(require = false)
    private String aclId;

    /**
     * Start of Public Ip
     */
    @AString(require = false)
    private String startPublicIpAddress;

    /**
     * End of Public Ip
     */
    @AString(require = false)
    private String endPublicIpAddress;

    /**
     * QosPreNat or not
     */
    @NONInvField
    private String qosPreNat = "false";

    /**
     * Create time
     */
    @NONInvField
    @AString
    private String createtime;

    /**
     * Update time
     */
    @NONInvField
    @AString
    private String updatetime;

    /**
     * Operation type
     */
    @NONInvField
    @AString(scope = "none,create,updateIp", require = false)
    private String type;

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

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public String getNatId() {
        return natId;
    }

    public void setNatId(String natId) {
        this.natId = natId;
    }

    public String getInternetGatewayId() {
        return internetGatewayId;
    }

    public void setInternetGatewayId(String internetGatewayId) {
        this.internetGatewayId = internetGatewayId;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    public String getPrivatePrefix() {
        return privatePrefix;
    }

    public void setPrivatePrefix(String privatePrefix) {
        this.privatePrefix = privatePrefix;
    }

    public String getAclNumber() {
        return aclNumber;
    }

    public void setAclNumber(String aclNumber) {
        this.aclNumber = aclNumber;
    }

    public String getAclId() {
        return aclId;
    }

    public void setAclId(String aclId) {
        this.aclId = aclId;
    }

    public String getStartPublicIpAddress() {
        return startPublicIpAddress;
    }

    public void setStartPublicIpAddress(String startPublicIpAddress) {
        this.startPublicIpAddress = startPublicIpAddress;
    }

    public String getEndPublicIpAddress() {
        return endPublicIpAddress;
    }

    public void setEndPublicIpAddress(String endPublicIpAddress) {
        this.endPublicIpAddress = endPublicIpAddress;
    }

    public String getQosPreNat() {
        return qosPreNat;
    }

    public void setQosPreNat(String qosPreNat) {
        this.qosPreNat = qosPreNat;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
