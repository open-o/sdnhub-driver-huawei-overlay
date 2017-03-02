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

import java.util.Objects;

/**
 * Class of Configuration Site NetworkElement Model.<br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 June 6, 2016
 */
public class AdapterDeviceInfo extends AdapterDeviceCreateBasicInfo {

    private String id;

    private String showTenant;

    private String serviceIp;

    private String neType;

    private String version;

    private String status;

    private Double gisLon;

    private Double gisLat;

    private String vendor;

    private String tenantId;

    private String tenantName;

    private String orgnizationId;

    private String creator;

    private String createTime;

    private String registerTime;

    private String modifier;

    private String modifyTime;

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the showTenant.
     */
    public String getShowTenant() {
        return showTenant;
    }

    /**
     * @param showTenant The showTenant to set.
     */
    public void setShowTenant(String showTenant) {
        this.showTenant = showTenant;
    }

    /**
     * @return Returns the serviceIp.
     */
    public String getServiceIp() {
        return serviceIp;
    }

    /**
     * @param serviceIp The serviceIp to set.
     */
    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    /**
     * @return Returns the neType.
     */
    public String getNeType() {
        return neType;
    }

    /**
     * @param neType The neType to set.
     */
    public void setNeType(String neType) {
        this.neType = neType;
    }

    /**
     * @return Returns the version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version to set.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Returns the gisLon.
     */
    public Double getGisLon() {
        return gisLon;
    }

    /**
     * @param gisLon The gisLon to set.
     */
    public void setGisLon(Double gisLon) {
        this.gisLon = gisLon;
    }

    /**
     * @return Returns the gisLat.
     */
    public Double getGisLat() {
        return gisLat;
    }

    /**
     * @param gisLat The gisLat to set.
     */
    public void setGisLat(Double gisLat) {
        this.gisLat = gisLat;
    }

    /**
     * @return Returns the vendor.
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @param vendor The vendor to set.
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * @return Returns the tenantId.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId The tenantId to set.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return Returns the tenantName.
     */
    public String getTenantName() {
        return tenantName;
    }

    /**
     * @param tenantName The tenantName to set.
     */
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    /**
     * @return Returns the orgnizationId.
     */
    public String getOrgnizationId() {
        return orgnizationId;
    }

    /**
     * @param orgnizationId The orgnizationId to set.
     */
    public void setOrgnizationId(String orgnizationId) {
        this.orgnizationId = orgnizationId;
    }

    /**
     * @return Returns the creator.
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator The creator to set.
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return Returns the createTime.
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime The createTime to set.
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return Returns the registerTime.
     */
    public String getRegisterTime() {
        return registerTime;
    }

    /**
     * @param registerTime The registerTime to set.
     */
    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * @return Returns the modifier.
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * @param modifier The modifier to set.
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * @return Returns the modifyTime.
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime The modifyTime to set.
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
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

        AdapterDeviceInfo other = (AdapterDeviceInfo) obj;

        if (!Objects.equals(id, other.id)) {
            return false;
        }

        if (!Objects.equals(showTenant, other.showTenant)) {
            return false;
        }
        
        if (!Objects.equals(serviceIp, other.serviceIp)) {
            return false;
        }

        if (!Objects.equals(neType, other.neType)) {
            return false;
        }

        return checkOther(other);
    }

    private boolean checkOther(AdapterDeviceInfo other) {
        if (!Objects.equals(version, other.version)) {
            return false;
        }

        if (!Objects.equals(status, other.status)) {
            return false;
        }
        
        if (!Objects.equals(gisLon, other.gisLon)) {
            return false;
        }
        
        if (!Objects.equals(gisLat, other.gisLat)) {
            return false;
        }

        if (!Objects.equals(vendor, other.vendor)) {
            return false;
        }
        
        if (!Objects.equals(tenantId, other.tenantId)) {
            return false;
        }

        if (!Objects.equals(tenantName, other.tenantName)) {
            return false;
        }
        
        if (!Objects.equals(orgnizationId, other.orgnizationId)) {
            return false;
        }
        
        return checkOtherMembers(other);
    }
    
    private boolean checkOtherMembers(AdapterDeviceInfo other) {
        
        if (!Objects.equals(creator, other.creator)) {
            return false;
        }
        
        if (!Objects.equals(createTime, other.createTime)) {
            return false;
        }

        if (!Objects.equals(registerTime, other.registerTime)) {
            return false;
        }
        
        if (!Objects.equals(modifier, other.modifier)) {
            return false;
        }
        
        if (!Objects.equals(modifyTime, other.modifyTime)) {
            return false;
        }

        if (!Objects.equals(getEsn(), other.getEsn())) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, showTenant, serviceIp, neType, version, status, gisLon,
                gisLat, vendor, tenantId, tenantName, orgnizationId, creator, createTime, 
                registerTime, modifier, modifyTime);
    }
}
