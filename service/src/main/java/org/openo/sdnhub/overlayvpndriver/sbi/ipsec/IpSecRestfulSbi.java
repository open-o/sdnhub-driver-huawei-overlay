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

package org.openo.sdnhub.overlayvpndriver.sbi.ipsec;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.login.OverlayVpnDriverProxy;
import org.openo.sdnhub.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdnhub.overlayvpndriver.util.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.util.controller.ControllerUtil;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.CommConst;
import org.openo.sdno.util.http.HTTPReturnMessage;

/**
 * SBI Class of IpSec Restful Interface.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-7
 */
public class IpSecRestfulSbi {

    /**
     * Update IpSec Instance.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @param netIpSecModelList NetIpSecModel data
     * @return NetIpSecModel Created
     * @throws ServiceException when create IpSec failed
     * @since SDNHUB 0.5
     */
    public List<NetIpSecModel> update(String ctrlUuid, String deviceId, NetIpSecModel NetIpSecModel)
            throws ServiceException {

        List<NetIpSecModel> ipsecModelList = new ArrayList<NetIpSecModel>(1);
        ipsecModelList.add(NetIpSecModel);

        String ipSecUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_IPSEC, deviceId);

        Map<String, List<NetIpSecModel>> ctrlInfoMap = new ConcurrentHashMap<String, List<NetIpSecModel>>();
        ctrlInfoMap.put(CommConst.IP_SEC_LIST, ipsecModelList);

        HTTPReturnMessage httpMsg =
                OverlayVpnDriverProxy.getInstance().sendPutMsg(ipSecUrl, JsonUtil.toJson(ctrlInfoMap), ctrlUuid);

        return new ControllerUtil<NetIpSecModel>().checkRsp(httpMsg);
    }

    /**
     * Query IpSec Instance.<br>
     * 
     * @param ctrlUuid Controller UUid
     * @param deviceId Device Id
     * @param interfaceName Interface Name
     * @param ipSecId IpSec Id
     * @return NetIpSecModel data queried out
     * @throws ServiceException throws when query failed
     * @since SDNHUB 0.5
     */
    public List<NetIpSecModel> query(String ctrlUuid, String deviceId, String interfaceName, String ipSecId)
            throws ServiceException {
        String queryUrl = MessageFormat.format(ControllerUrlConst.CONST_CONFIG_IPSEC, deviceId);
        if(StringUtils.isNotEmpty(interfaceName)) {
            StringBuilder strBuidler = new StringBuilder();
            /*
             * To fix Sonar Isssue,Avoid concatenating nonliterals in a StringBuffer/StringBuilder
             * constructor or append().
             */
            String queryUrlStr = queryUrl + "?interfaceName=" + interfaceName;
            strBuidler.append(queryUrlStr);
            queryUrl = strBuidler.toString();
        }

        if(StringUtils.isNotEmpty(ipSecId)) {
            StringBuilder strBuidler = new StringBuilder();
            /*
             * To fix Sonar Isssue,Avoid concatenating nonliterals in a StringBuffer/StringBuilder
             * constructor or append().
             */
            String queryUrlStr = queryUrl + "?ipsecId=" + ipSecId;
            strBuidler.append(queryUrlStr);
            queryUrl = strBuidler.toString();
        }

        HTTPReturnMessage httpMsg = OverlayVpnDriverProxy.getInstance().sendGetMsg(queryUrl, null, ctrlUuid);
        return new ControllerUtil<NetIpSecModel>().checkRsp(httpMsg);
    }

}
