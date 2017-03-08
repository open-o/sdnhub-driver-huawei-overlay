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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openo.sdnhub.overlayvpndriver.controller.model.LoopBackPort;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.NONInvField;
import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AUuid;
import org.openo.sdno.util.ip.IpUtils;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */

public class Ip extends UuidModel {

    private String ipv4;

    private String ipv6;

    @AInt(max = 32)
    private String ipMask;

    @AInt(max = 32)
    private String prefixLength;

    @AUuid
    private String neId;

    @AUuid
    private String deviceId;

    @AUuid
    private String routeId;

    @JsonIgnore
    @AUuid
    private String vxlanTunnelId;

    @JsonIgnore
    @NONInvField
    private boolean isTypeV4;

    public Ip() {
        //Non-parameter constructor
    }

    public Ip(String deviceId, LoopBackPort loopBackPort){
        super();
        this.deviceId = deviceId;
        this.setUuid(loopBackPort.getId());
        this.ipv4 = loopBackPort.getIpv4Address();
        this.ipMask = loopBackPort.getIpv4Mask();
        this.ipv6 = loopBackPort.getIpv6Address();
        this.prefixLength = loopBackPort.getPrefixLength();
    }

    public Ip(String deviceId, AcDevicePort tempPort){
        super();
        this.deviceId = deviceId;
        this.setUuid(tempPort.getId());
        this.ipv4 = tempPort.getIpAddr();
        this.ipMask = String.valueOf(IpUtils.maskToPrefix(tempPort.getMask()));
        this.ipv6 = tempPort.getIpv6Addr();
        this.prefixLength = tempPort.getPrefixLength();
    }

    public Ip(String ipv4, String ipMask) {
        super();
        this.ipv4 = ipv4;
        this.ipMask = ipMask;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getIpMask() {
        return ipMask;
    }

    public void setIpMask(String ipMask) {
        this.ipMask = ipMask;
    }

    public String getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(String prefixLength) {
        this.prefixLength = prefixLength;
    }

    public String getNeId() {
        return neId;
    }

    public void setNeId(String neId) {
        this.neId = neId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public boolean isTypeV4() {
        return isTypeV4;
    }

    public void setIsTypeV4(boolean isTypeV4) {
        this.isTypeV4 = isTypeV4;
    }

    public String getVxlanTunnelId() {
        return vxlanTunnelId;
    }

    public void setVxlanTunnelId(String vxlanTunnelId) {
        this.vxlanTunnelId = vxlanTunnelId;
    }

    public void setTypeV4(boolean isTypeV4) {
        this.isTypeV4 = isTypeV4;
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

        Ip other = (Ip) obj;

        if (!Objects.equals(ipv4, other.ipv4)) {
            return false;
        }

        if (!Objects.equals(ipv6, other.ipv6)) {
            return false;
        }

        if (!Objects.equals(ipMask, other.ipMask)) {
            return false;
        }

        return checkOther(other);
    }

    private boolean checkOther(Ip other) {
        if (!Objects.equals(prefixLength, other.prefixLength)) {
            return false;
        }

        if (!Objects.equals(neId, other.neId)) {
            return false;
        }

        if (!Objects.equals(deviceId, other.deviceId)) {
            return false;
        }

        if (!Objects.equals(routeId, other.routeId)) {
            return false;
        }

        if (!Objects.equals(isTypeV4, other.isTypeV4)) {
            return false;
        }

        return true;
    }

    public void copyBasicData(Ip data){
        this.neId = data.getNeId();
        this.deviceId = data.getDeviceId();
        this.ipv4 = data.getIpv4();
        this.ipMask = data.getIpMask();
        this.ipv6 = data.getIpv6();
        this.prefixLength = data.getPrefixLength();
        this.vxlanTunnelId = data.getVxlanTunnelId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipv4, ipv6, ipMask, prefixLength, neId, deviceId, routeId,
                isTypeV4);
    }
}
