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

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class IpSec {

    private String espAuthAlgorithm;

    private String espEncryptionAlgorithm;

    /**
     * Constructor<br>
     *
     * @since SDNHUB Driver 0.5
     */
    public IpSec() {
        super();

    }

    /**
     * Constructor<br>
     *
     * @param espAuthAlgorithm The authentication algorithm
     * @param espEncryptionAlgorithm The encryption algorithm
     * @since SDNHUB Driver 0.5
     */
    public IpSec(String espAuthAlgorithm, String espEncryptionAlgorithm) {
        super();

        this.setEspAuthAlgorithm(espAuthAlgorithm);
        this.setEspEncryptionAlgorithm(espEncryptionAlgorithm);
    }

    public String getEspAuthAlgorithm() {
        return espAuthAlgorithm;
    }

    public void setEspAuthAlgorithm(String espAuthAlgorithm) {
        this.espAuthAlgorithm = espAuthAlgorithm;
    }

    public String getEspEncryptionAlgorithm() {
        return espEncryptionAlgorithm;
    }

    public void setEspEncryptionAlgorithm(String espEncryptionAlgorithm) {
        this.espEncryptionAlgorithm = espEncryptionAlgorithm;
    }
}
