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

package org.openo.sdnhub.overlayvpndriver.translator;

import org.openo.sdnhub.overlayvpndriver.controller.model.AcAcl;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcSNat;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiSnatNetModel;

/**
 * Utility class to convert SNatNetConvert.<br/>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class SNatNetConvert {

    private SNatNetConvert() {
    }

    /**
     * This method translate AcAcl and SbiSnatNetModel to AcSNat model.<br/>
     *
     * @param acl AcAcL model
     * @param snatNetModel SbiSnatNetModel model
     * @return Translated AcSnat model
     * @since SDNHUB 0.5
     */
    public static AcSNat buildAcSNat(AcAcl acl, SbiSnatNetModel snatNetModel) {
        AcSNat snat = new AcSNat();
        snat.setAclId(acl.getAclId());
        snat.setId(snatNetModel.getNatId());
        snat.setInterfaceName(snatNetModel.getIfName());
        snat.setQosPreNat(snatNetModel.getQosPreNat());
        return snat;
    }

    /**
     * Convert SbiSnatNetModel to AcSNat<br/>
     *
     * @param snat SbiSnatNetModel model to be translated
     * @return AcSNat model translated from SbiSnatNetModel
     * @since SDNHUB 0.5
     */
    public static AcSNat buildAcSNat(SbiSnatNetModel snat) {
        AcSNat acSnat = new AcSNat();
        acSnat.setAclId(snat.getAclId());
        acSnat.setId(snat.getNatId());
        acSnat.setInterfaceName(snat.getIfName());
        acSnat.setQosPreNat(snat.getQosPreNat());
        return acSnat;
    }

}
