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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;

import java.util.Objects;

public class SbiSecurityPolicy extends UuidModel {

    @AString(scope = "Group2,Group5,Group14")
    private String pfs = "Group5";

    @AInt(require = false, min = 0, max = 3600)
    private String lifeTime;

    @AUuid(require = false)
    private String externalId;

    @AUuid(require = false)
    private String sbiServiceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SbiSecurityPolicy that = (SbiSecurityPolicy) o;
        return Objects.equals(pfs, that.pfs) &&
                Objects.equals(lifeTime, that.lifeTime) &&
                Objects.equals(externalId, that.externalId) &&
                Objects.equals(sbiServiceId, that.sbiServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pfs, lifeTime, externalId, sbiServiceId);
    }

    /**
     * perfect forward secrecy(Group2,Group5,Group14)
     *
     * @return pfs
     **/
    public String getPfs() {
        return pfs;
    }

    public void setPfs(String pfs) {
        this.pfs = pfs;
    }

    /**
     * Get lifeTime
     *
     * @return lifeTime
     **/
    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }

    /**
     * Get externalId
     *
     * @return externalId
     **/
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * Get sbiServiceId
     *
     * @return sbiServiceId
     **/
    public String getSbiServiceId() {
        return sbiServiceId;
    }

    public void setSbiServiceId(String sbiServiceId) {
        this.sbiServiceId = sbiServiceId;
    }
}
