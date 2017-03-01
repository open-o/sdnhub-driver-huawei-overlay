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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * IP-security connection information.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jan 16, 2017
 */
public class IpsecConnection {

    @JsonIgnore
    private String ipsecId;

    @JsonIgnore
    private String deviceId;

    private int seqNumber = 0;

    private boolean deleteMode;

    private String type;

    private List<RuleList> ruleList;

    private String routeInject;

    @JsonProperty(value ="ipsec")
    private IpSec ipSec;

    private Ike ike;

    private String nqaId;

    private String nqaState;

    private String qosPreClassify;

    /**
     * Initialization with default values.
     */
    public IpsecConnection() {
        // no argument constructor
    }

    /**
     * Initialization with device-id.
     */
    public IpsecConnection(String deviceId)
    {
        super();
        this.setDeviceId(deviceId);
    }

    /**
     * @return Returns the ip-sec id.
     */
    public String getIpsecId()
    {
        return ipsecId;
    }

    /**
     * @param ipsecId ip-sec id.
     */
    public void setIpsecId(String ipsecId)
    {
        this.ipsecId = ipsecId;
    }

    /**
     * @return Returns the device id.
     */
    public String getDeviceId()
    {
        return deviceId;
    }

    /**
     * @param deviceId device id.
     */
    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    /**
     * @return Returns the sequence number.
     */
    public int getSeqNumber()
    {
        return seqNumber;
    }

    /**
     * @param seqNumber sequence number.
     */
    public void setSeqNumber(int seqNumber)
    {
        this.seqNumber = seqNumber;
    }

    /**
     * @return Returns the delete mode.
     */
    public boolean isDeleteMode()
    {
        return deleteMode;
    }

    /**
     * @param deleteMode delete mode.
     */
    public void setDeleteMode(boolean deleteMode)
    {
        this.deleteMode = deleteMode;
    }

    /**
     * @return Returns the type of mode.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type type of mode.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return Returns the rule list.
     */
    public List<RuleList> getRuleList()
    {
        return ruleList;
    }

    /**
     * @param ruleList rule list.
     */
    public void setRuleList(List<RuleList> ruleList)
    {
        this.ruleList = ruleList;
    }

    /**
     * @return Returns the route injection.
     */
    public String getRouteInject()
    {
        return routeInject;
    }

    /**
     * @param routeInject route injection.
     */
    public void setRouteInject(String routeInject)
    {
        this.routeInject = routeInject;
    }

    /**
     * @return Returns the ip-sec.
     */
    public IpSec getIpSec()
    {
        return ipSec;
    }

    /**
     * @param ipSec ip-sec.
     */
    public void setIpSec(IpSec ipSec)
    {
        this.ipSec = ipSec;
    }

    /**
     * @return Returns Ike consultation.
     */
    public Ike getIke()
    {
        return ike;
    }

    /**
     * @param ike Ike consultation.
     */
    public void setIke(Ike ike)
    {
        this.ike = ike;
    }

    /**
     * @return Returns NQA id.
     */
    public String getNqaId() {
        return nqaId;
    }

    /**
     * @param nqaId NQA id.
     */
    public void setNqaId(String nqaId) {
        this.nqaId = nqaId;
    }

    /**
     * @return Returns NQA state.
     */
    public String getNqaState() {
        return nqaState;
    }

    /**
     * @param nqaState NQA state.
     */
    public void setNqaState(String nqaState) {
        this.nqaState = nqaState;
    }

    /**
     * @return Returns the QOS pre classification.
     */
    public String getQosPreClassify() {
        return qosPreClassify;
    }

    /**
     * @param qosPreClassify QOS pre classification.
     */
    public void setQosPreClassify(String qosPreClassify) {
        this.qosPreClassify = qosPreClassify;
    }

    @Override
    public String toString() {
        return "ClassPojo [ipsecId = " + ipsecId + ", device-id = " + deviceId + ",nqaState = " + nqaState +
                ", ipsec = " + ipSec + ", ruleList = " + ruleList + ", route inject = " + routeInject +
                ", ike = " + ike + ", type = " + type + ", nqaId = " + nqaId + ", seqNumber = " + seqNumber +
                ", delete mode = " + deleteMode + ", qosPreClassify = " + qosPreClassify + "]";
    }
}
