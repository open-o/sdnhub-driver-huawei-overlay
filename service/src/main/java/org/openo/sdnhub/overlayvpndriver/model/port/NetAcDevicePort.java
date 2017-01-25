/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.model.port;

/**
 * Model class for port in adapter layer. <br>
 * 
 * @author
 * @version SDNO 0.5 Jul 21, 2016
 */
public class NetAcDevicePort {

    private String id;

    private String name;

    private int runStatus;

    private int mgrStatus;

    private String index;

    private String alias;

    private String ipAddr;

    private String ipv6Addr;

    private String bandWith;

    private int mtu;

    private boolean report;

    private int mode;

    private String iccid;

    private String mac;

    private String terminationMode;

    private String ceLowVlan;

    private String ceHighVlan;

    private String peVlan;

    private String mask;

    private String prefixLength;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
    }

    public int getMgrStatus() {
        return mgrStatus;
    }

    public void setMgrStatus(int mgrStatus) {
        this.mgrStatus = mgrStatus;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getBandWith() {
        return bandWith;
    }

    public void setBandWith(String bandWith) {
        this.bandWith = bandWith;
    }

    public int getMtu() {
        return mtu;
    }

    public void setMtu(int mtu) {
        this.mtu = mtu;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIpv6Addr() {
        return ipv6Addr;
    }

    public void setIpv6Addr(String ipv6Addr) {
        this.ipv6Addr = ipv6Addr;
    }

    public String getTerminationMode() {
        return terminationMode;
    }

    public void setTerminationMode(String terminationMode) {
        this.terminationMode = terminationMode;
    }

    public String getCeLowVlan() {
        return ceLowVlan;
    }

    public void setCeLowVlan(String ceLowVlan) {
        this.ceLowVlan = ceLowVlan;
    }

    public String getCeHighVlan() {
        return ceHighVlan;
    }

    public void setCeHighVlan(String ceHighVlan) {
        this.ceHighVlan = ceHighVlan;
    }

    public String getPeVlan() {
        return peVlan;
    }

    public void setPeVlan(String peVlan) {
        this.peVlan = peVlan;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(String prefixLength) {
        this.prefixLength = prefixLength;
    }
}
