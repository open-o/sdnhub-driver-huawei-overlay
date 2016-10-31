/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.overlayvpndriver.sbi.vxlan;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.overlayvpndriver.login.OverlayVpnDriverProxy;
import org.openo.sdno.overlayvpndriver.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.overlayvpndriver.util.consts.ControllerUrlConst;
import org.openo.sdno.overlayvpndriver.util.controller.ControllerUtil;
import org.openo.sdno.util.http.HTTPReturnMessage;

/**
 * SBI Class of VxLAN Restful Interface.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-7
 */
public class VxLANRestfulSbi {

    private static final String DELETE_VXLAN_PARAMETER = "ids";

    /**
     * Create VxLAN Instance.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @param netVxLanDeviceModelList List of NetVxLanDeviceModel data
     * @return NetVxLanDeviceModel Created
     * @throws ServiceException when create VxLAN failed
     * @since SDNO 0.5
     */
    public List<NetVxLanDeviceModel> create(String ctrlUuid, String deviceId,
            List<NetVxLanDeviceModel> netVxLanDeviceModelList) throws ServiceException {
        String url = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);
        Map<String, List<NetVxLanDeviceModel>> ctrlInfoMap = new ConcurrentHashMap<String, List<NetVxLanDeviceModel>>();
        ctrlInfoMap.put(CommConst.VXLAN_LIST, netVxLanDeviceModelList);
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(url, JsonUtil.toJson(ctrlInfoMap), ctrlUuid);
        return new ControllerUtil<NetVxLanDeviceModel>().checkRsp(httpMsg);
    }

    /**
     * Delete VxLAN Instance.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @param vxLanId VxLAN Id
     * @throws ServiceException when delete VxLAN failed
     * @since SDNO 0.5
     */
    public void delete(String ctrlUuid, String deviceId, String vxLanId) throws ServiceException {
        String url = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_VXLAN, deviceId);
        Map<String, List<String>> crtInfoMap = new HashMap<>();
        crtInfoMap.put(DELETE_VXLAN_PARAMETER, Arrays.asList(vxLanId));
        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendDeleteMsg(url, JsonUtil.toJson(crtInfoMap), ctrlUuid);
        new ControllerUtil<NetVxLanDeviceModel>().checkRsp(httpMsg);
    }
}
