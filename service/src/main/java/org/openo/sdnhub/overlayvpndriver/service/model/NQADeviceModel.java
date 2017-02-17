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
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class NQADeviceModel {

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    private String id;

    private String testType;

    private String dstIp;

    private String srcIp;

    private String dstIp6;

    private String srcport;

    private Integer frequency;

    private Integer proveCount;

    private Integer timeout;

    private Integer ttl;

    private Integer tos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getDstIp() {
        return dstIp;
    }

    public void setDstIp(String dstIp) {
        this.dstIp = dstIp;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getDstIp6() {
        return dstIp6;
    }

    public void setDstIp6(String dstIp6) {
        this.dstIp6 = dstIp6;
    }

    public String getSrcport() {
        return srcport;
    }

    public void setSrcport(String srcport) {
        this.srcport = srcport;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getProveCount() {
        return proveCount;
    }

    public void setProveCount(Integer proveCount) {
        this.proveCount = proveCount;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getTos() {
        return tos;
    }

    public void setTos(Integer tos) {
        this.tos = tos;
    }
}
