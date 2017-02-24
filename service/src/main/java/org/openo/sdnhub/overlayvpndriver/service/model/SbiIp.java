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

import org.openo.sdnhub.overlayvpndriver.controller.model.AcDevicePort;
import org.openo.sdnhub.overlayvpndriver.controller.model.LoopBackPort;
import org.openo.sdno.overlayvpn.inventory.sdk.model.annotation.MOResType;
import org.openo.sdno.overlayvpn.model.v2.uuid.UuidModel;
import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AIp;

@MOResType(infoModelName = "ipsec_sbi_ip")
public class SbiIp extends UuidModel {

    @AIp(require = true)
    private String ipv4;

    private String ipv6;

    /**
     * Use for Ipv4.
     */
    @AInt(require = true, min = 0, max = 32)
    private String ipMask;

    /**
     * Use for Ipv6.
     */
    @AInt(require = false, min = 0, max = 128)
    private String prefixLength;

    public SbiIp() {
        super();
    }

    public SbiIp(String ipv4, String ipMask) {
        this();
        this.ipv4 = ipv4;
        this.ipMask = ipMask;
    }

    public SbiIp (LoopBackPort loopBackPort) {
        this.setUuid(loopBackPort.getId());
        this.ipv4 = loopBackPort.getIpv4Address();
        this.ipMask = loopBackPort.getIpv4Mask();
        this.ipv6 = loopBackPort.getIpv6Address();
        this.prefixLength = loopBackPort.getPrefixLength();
    }

    public SbiIp (AcDevicePort acDevicePort) {
        this.setUuid(acDevicePort.getId());
        this.ipv4 = acDevicePort.getIpAddr();
        this.ipMask = acDevicePort.getMask();
        this.ipv6 = acDevicePort.getIpv6Addr();
        this.prefixLength = acDevicePort.getPrefixLength();
    }

    /**
     * Ipv4 address
     *
     * @return ipv4
     **/
    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    /**
     * Ipv6 address
     *
     * @return ipv6
     **/
    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    /**
     * Ip mask
     *
     * @return ipMask
     **/
    public String getIpMask() {
        return ipMask;
    }

    public void setIpMask(String ipMask) {
        this.ipMask = ipMask;
    }

    /**
     * Ip prefix length
     *
     * @return prefixLength
     **/
    public String getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(String prefixLength) {
        this.prefixLength = prefixLength;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SbiIp {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    ipv4: ").append(toIndentedString(ipv4)).append("\n");
        sb.append("    ipv6: ").append(toIndentedString(ipv6)).append("\n");
        sb.append("    ipMask: ").append(toIndentedString(ipMask)).append("\n");
        sb.append("    prefixLength: ").append(toIndentedString(prefixLength)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toIndentedString(Object o) {
        if(o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((ipMask == null) ? 0 : ipMask.hashCode());
        result = prime * result + ((ipv4 == null) ? 0 : ipv4.hashCode());
        result = prime * result + ((ipv6 == null) ? 0 : ipv6.hashCode());
        result = prime * result + ((prefixLength == null) ? 0 : prefixLength.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        if(getClass() != obj.getClass()) {
            return false;
        }

        SbiIp other = (SbiIp)obj;

        if(!Objects.equals(ipMask, other.ipMask)) {

            return false;

        }

        if(!Objects.equals(ipv4, other.ipv4)) {
            return false;
        }

        return checkOther(other);
    }

    private boolean checkOther(SbiIp other) {
        if(!Objects.equals(ipv6, other.ipv6)) {
            return false;
        }

        if(!Objects.equals(prefixLength, other.prefixLength)) {
            return false;
        }
        return true;
    }
}
