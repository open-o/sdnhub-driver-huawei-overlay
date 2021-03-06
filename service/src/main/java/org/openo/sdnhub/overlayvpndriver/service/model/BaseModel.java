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

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.NONInvField;
import org.openo.sdno.overlayvpn.model.common.enums.ActionStatus;
import org.openo.sdno.overlayvpn.model.common.enums.AdminStatus;
import org.openo.sdno.overlayvpn.model.common.enums.OperStatus;
import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Basic model class.<br>
 *
 * @author
 * @version SDNHUB 0.5 2017-1-6
 */
public class BaseModel extends UuidModel {

    /**
     * Name
     */
    @AString(require = true, min = 0, max = 255)
    private String name;

    /**
     * Tenant Id
     */
    @AString(min = 0, max = 255)
    private String tenantId;

    /**
     * Description
     */
    @AString(min = 0, max = 255)
    private String description;

    /**
     * Status of deploy
     */
    @AString(scope = "deploy,undeploy")
    private String deployStatus;

    /**
     * Action state
     */
    @JsonProperty("actionState")
    @AString(scope = "none,normal,creating,deleting,updating,create_excepion,delete_exception,update_exception,deploying,deploy_exception,undeploying,undeploy_exception")
    private String operationStatus = ActionStatus.NORMAL.getName();

    /**
     * Administrative status
     */
    @AString(scope = "none,active,inactive,partially_inactive")
    private String activeStatus = AdminStatus.NONE.getName();

    /**
     * Running status
     */
    @AString(scope = "none,up,down,partially_down")
    private String runningStatus = OperStatus.NONE.getName();

    /**
     * Create time
     */
    @JsonProperty("createTime")
    @NONInvField
    private Long createtime = 0L;

    /**
     * Update time
     */
    @JsonProperty("updateTime")
    @NONInvField
    private Long updatetime = 0L;

    /**
     * Additional info
     */
    @AString(min = 0, max = 255)
    private String additionalInfo;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(String deployStatus) {
        this.deployStatus = deployStatus;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(String runningStatus) {
        this.runningStatus = runningStatus;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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

        BaseModel other = (BaseModel) obj;

        if (!Objects.equals(name, other.name)) {
            return false;
        }

        if (!Objects.equals(tenantId, other.tenantId)) {
            return false;
        }

        if (!Objects.equals(description, other.description)) {
            return false;
        }

        return checkOther(other);
    }

    private boolean checkOther(BaseModel other) {

        if (!Objects.equals(deployStatus, other.deployStatus)) {
            return false;
        }

        if (!Objects.equals(operationStatus, other.operationStatus)) {
            return false;
        }

        if (!Objects.equals(activeStatus, other.activeStatus)) {
            return false;
        }

        if (!Objects.equals(runningStatus, other.runningStatus)) {
            return false;
        }

        if (!Objects.equals(createtime, other.createtime)) {
            return false;
        }

        if (!Objects.equals(updatetime, other.updatetime)) {
            return false;
        }

        if (!Objects.equals(additionalInfo, other.additionalInfo)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tenantId, description, deployStatus, operationStatus, activeStatus, runningStatus,
                createtime, updatetime, additionalInfo);
    }
}
