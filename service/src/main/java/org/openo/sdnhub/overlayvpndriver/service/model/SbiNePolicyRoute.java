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
import org.openo.sdno.overlayvpn.verify.annotation.AString;

@MOResType(infoModelName = "sbinepolicyroute")
public class SbiNePolicyRoute extends SbiRouteNetModel {

    @AString(require = true, min = 1, max = 26)
    private String trafficPolicyName;

    @AString(require = true, min = 1, max = 255)
    private String interfaceName;

    @AString(require = true, scope = "inbound,outbound,all")
    private String direction;

    @AString(require = true, min = 1, max = 1024)
    private String filterAction;

    @AString(require = true, scope = "policy")
    private String type = "policy";

    private String  srcDeviceId;

    /**
     * Constructor<br/>
     *
     * @since SDNHUB 0.5
     */
    public SbiNePolicyRoute() {
        super();
    }

    /**
     * traffic policy name
     *
     * @return trafficPolicyName
     **/
    public String getTrafficPolicyName() {
        return trafficPolicyName;
    }

    public void setTrafficPolicyName(String trafficPolicyName) {
        this.trafficPolicyName = trafficPolicyName;
    }

    /**
     * interface name
     *
     * @return interfaceName
     **/
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    /**
     * the scope is inbound, outbound, all
     *
     * @return direction
     **/
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * filter action
     *
     * @return filterAction
     **/
    public String getFilterAction() {
        return filterAction;
    }

    public void setFilterAction(String filterAction) {
        this.filterAction = filterAction;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SbiNePolicyRoute {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    trafficPolicyName: ").append(toIndentedString(trafficPolicyName)).append("\n");
        sb.append("    interfaceName: ").append(toIndentedString(interfaceName)).append("\n");
        sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
        sb.append("    filterAction: ").append(toIndentedString(filterAction)).append("\n");
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

    /**
     * Override equals Function.<br>
     *
     * @param obj other Object
     * @return true if this object equals to other object
     * @since SDNO 0.5
     */
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

        SbiNePolicyRoute other = (SbiNePolicyRoute) obj;
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
        result = 31 * result + (trafficPolicyName != null ? trafficPolicyName.hashCode() : 0);
        result = 31 * result + (interfaceName != null ? interfaceName.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (filterAction != null ? filterAction.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (srcDeviceId != null ? srcDeviceId.hashCode() : 0);
        return result;
    }
}
