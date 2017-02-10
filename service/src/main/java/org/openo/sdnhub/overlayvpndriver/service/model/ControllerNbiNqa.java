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
 * Model for ControllerNbiNqa. <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class ControllerNbiNqa {

    private String testType;

    private String srcPort;

    private String sequency;

    private String frequency;

    private String ttl;

    private String id;

    private String tos;

    private String srcIp6;

    private String dstIp6;

    private String probeCount;

    private String srcIp;

    private String srcIPv6;

    private String timeout;

    private String dstIp;

    private String dstIPv6;

    public String getSrcIPv6() {
        return srcIPv6;
    }

    public void setSrcIPv6(String srcIPv6) {
        this.srcIPv6 = srcIPv6;
    }

    public String getDstIPv6() {
        return dstIPv6;
    }

    public void setDstIPv6(String dstIPv6) {
        this.dstIPv6 = dstIPv6;
    }

    private String srcPortName;

    private String dstPortName;

    private String interval;

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getSrcPortName() {
        return srcPortName;
    }

    public void setSrcPortName(String srcPortName) {
        this.srcPortName = srcPortName;
    }

    public String getDstPortName() {
        return dstPortName;
    }

    public void setDstPortName(String dstPortName) {
        this.dstPortName = dstPortName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public String getSequency() {
        return sequency;
    }

    public void setSequency(String sequency) {
        this.sequency = sequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    public String getSrcIp6() {
        return srcIp6;
    }

    public void setSrcIp6(String srcIp6) {
        this.srcIp6 = srcIp6;
    }

    public String getDstIp6() {
        return dstIp6;
    }

    public void setDstIp6(String dstIp6) {
        this.dstIp6 = dstIp6;
    }

    public String getProbeCount() {
        return probeCount;
    }

    public void setProbeCount(String probeCount) {
        this.probeCount = probeCount;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getDstIp() {
        return dstIp;
    }

    public void setDstIp(String dstIp) {
        this.dstIp = dstIp;
    }

    @Override
    public String toString() {
        return "ClassPojo [testType = " + testType + ", srcPort = " + srcPort + ", sequency = " + sequency
                + ", frequency = " + frequency + ", ttl = " + ttl + ", id = " + id + ", tos = " + tos + ", srcIp6 = "
                + srcIp6 + ", dstIp6 = " + dstIp6 + ", probeCount = " + probeCount + ", srcIp = " + srcIp
                + ", timeout = " + timeout + ", dstIp = " + dstIp + "]";
    }
}
