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

import org.openo.sdno.overlayvpn.verify.annotation.AInt;

/**
 * Model class for Action.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class Action {

    private Car car;

    private Queue queue;

    private String desPort;

    @AInt(min = 0, max = 7)
    private String remark8021p;

    @AInt(min = 0, max = 63)
    private String remarkDscp;

    private String actionType;

    private Redirect redirect;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public String getDesPort() {
        return desPort;
    }

    public void setDesPort(String desPort) {
        this.desPort = desPort;
    }

    public String getRemark8021p() {
        return remark8021p;
    }

    public void setRemark8021p(String remark8021p) {
        this.remark8021p = remark8021p;
    }

    public String getRemarkDscp() {
        return remarkDscp;
    }

    public void setRemarkDscp(String remarkDscp) {
        this.remarkDscp = remarkDscp;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    public void setRedirect(Redirect redirect) {
        this.redirect = redirect;
    }

    @Override
    public String toString() {
        return "ClassPojo [car = " + car + ", queue = " + queue + ", desPort = " + desPort + "]";
    }
}
