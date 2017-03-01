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

import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.model.common.enums.ipsec.IKEVersion;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

@MOResType(infoModelName = "ipsec_sbi_ikepolicy")
public class SbiIkePolicy extends SbiSecurityPolicy {

    @AString(scope = "v1,v2")
    private String ikeVersion = IKEVersion.V2.getName();

    @AString(require = true)
    private String psk;

    @AString(require = true, scope = "md5,sha1,sha2-256,sha2-384,sha2-512,sm3")
    private String authAlgorithm;

    @AString(require = false, scope = "3des,des,aes-128,aes-256,aes-192,sm1")
    private String encryptionAlgorithm;

    /**
     * Ike version(v1,v2)
     *
     * @return ikeVersion
     **/
    public String getIkeVersion() {
        return ikeVersion;
    }

    public void setIkeVersion(String ikeVersion) {
        this.ikeVersion = ikeVersion;
    }

    /**
     * Pre-shared-key
     *
     * @return psk
     **/
    public String getPsk() {
        return psk;
    }

    public void setPsk(String psk) {
        this.psk = psk;
    }

    /**
     * auth hash algorithm (md5,sha1,sha2-256,sh2-384,sh2-512,sm3)
     *
     * @return authAlgorithm
     **/
    public String getAuthAlgorithm() {
        return authAlgorithm;
    }

    public void setAuthAlgorithm(String authAlgorithm) {
        this.authAlgorithm = authAlgorithm;
    }

    /**
     * encryption algorithm (3des,des,aes-128,aes-256,aes-192,sm1)
     *
     * @return encryptionAlgorithm
     **/
    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
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
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (super.getClass() != obj.getClass()) {
            return false;
        }

        SbiIkePolicy other = (SbiIkePolicy) obj;
        if (this.uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!(this.uuid.equals(other.uuid))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ikeVersion != null ? ikeVersion.hashCode() : 0);
        result = 31 * result + (psk != null ? psk.hashCode() : 0);
        result = 31 * result + (authAlgorithm != null ? authAlgorithm.hashCode() : 0);
        result = 31 * result + (encryptionAlgorithm != null ? encryptionAlgorithm.hashCode() : 0);
        return result;
    }
}
