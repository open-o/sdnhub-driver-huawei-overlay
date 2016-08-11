/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.acbranchservice.util.convertmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acbranchservice.model.ipsec.adapter.NetIke;
import org.openo.sdno.acbranchservice.model.ipsec.adapter.NetIpSec;
import org.openo.sdno.acbranchservice.model.ipsec.adapter.NetIpSecConn;
import org.openo.sdno.acbranchservice.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdno.acbranchservice.model.ipsec.adapter.NetRule;
import org.openo.sdno.framework.container.util.UuidUtils;
import org.openo.sdno.overlayvpn.model.common.enums.ipsec.IKEVersion;
import org.openo.sdno.overlayvpn.model.netmodel.ipsec.NeIpSecConnection;
import org.openo.sdno.ssl.EncryptionUtil;
import org.openo.sdno.util.ip.IpUtils;

/**
 * Convert IpSec model from service to adapter. <br/>
 * 
 * @author
 * @version SDNO 0.5 Jul 15, 2016
 */
public class IpSecModelConvert {

    private static final int CONST_MASK_32 = 32;

    /**
     * Constructor<br/>
     * 
     * @since SDNO 0.5
     */
    private IpSecModelConvert() {

    }

    /**
     * Convert IpSec model from service to adapter. <br/>
     * 
     * @param neIpSecConnectionList The list of NeIpSecConnection
     * @return The map of NetIpSecModel list
     * @throws ServiceException When convert failed
     * @since SDNO 0.5
     */
    public static Map<String, List<NetIpSecModel>> convertModel(List<NeIpSecConnection> neIpSecConnectionList)
            throws ServiceException {
        Map<String, List<NetIpSecModel>> neIdToNetIpSecModelMap = new ConcurrentHashMap<String, List<NetIpSecModel>>();

        Map<String, List<NeIpSecConnection>> neMap = convert2NeMap(neIpSecConnectionList);

        for(Map.Entry<String, List<NeIpSecConnection>> entry : neMap.entrySet()) {
            List<NetIpSecModel> netIpSecModelList = new ArrayList<NetIpSecModel>();
            NetIpSecModel netIpSecModel = convert2NetIpSecModel(entry.getValue());
            netIpSecModelList.add(netIpSecModel);

            neIdToNetIpSecModelMap.put(entry.getKey(), netIpSecModelList);
        }

        return neIdToNetIpSecModelMap;
    }

    private static Map<String, List<NeIpSecConnection>> convert2NeMap(List<NeIpSecConnection> ipSecNeConnectionList)
            throws ServiceException {
        Map<String, List<NeIpSecConnection>> neMap = new ConcurrentHashMap<String, List<NeIpSecConnection>>();

        for(NeIpSecConnection ipSecConn : ipSecNeConnectionList) {
            if(neMap.containsKey(ipSecConn.getNeId())) {
                neMap.get(ipSecConn.getNeId()).add(ipSecConn);
            } else {
                List<NeIpSecConnection> dataList = new ArrayList<NeIpSecConnection>();
                dataList.add(ipSecConn);
                neMap.put(ipSecConn.getNeId(), dataList);
            }
        }

        return neMap;
    }

    private static NetIpSecModel convert2NetIpSecModel(List<NeIpSecConnection> neIpSecConnectionList)
            throws ServiceException {
        String wanSubIfName = neIpSecConnectionList.get(0).getSoureIfName();
        NetIpSecModel netIpSecModel = new NetIpSecModel(wanSubIfName);

        netIpSecModel.allocateUuid();
        netIpSecModel.setName(UuidUtils.createUuid().substring(0, 8));

        List<NetIpSecConn> connList = new ArrayList<NetIpSecConn>();

        for(NeIpSecConnection ipSecNeConnection : neIpSecConnectionList) {
            NetIpSecConn netIpSecConn = new NetIpSecConn();

            NetRule rule = buildNetRule(ipSecNeConnection);
            NetIke ike = buildNetIke(ipSecNeConnection, IpUtils.getIPFromCIDR(ipSecNeConnection.getPeerAddress()));
            NetIpSec ipSec = buildNetIpSec(ipSecNeConnection);

            netIpSecConn.setIpSecConnectionId(ipSecNeConnection.getUuid());
            netIpSecConn.setRuleList(Arrays.asList(rule));
            netIpSecConn.setIke(ike);
            netIpSecConn.setIpSec(ipSec);
            netIpSecConn.setType("false");

            connList.add(netIpSecConn);
        }

        netIpSecModel.setIpsecConnection(connList);
        return netIpSecModel;
    }

    private static NetRule buildNetRule(final NeIpSecConnection ipSecNeConnection) throws ServiceException {
        String srcIp = IpUtils.getIPFromCIDR(ipSecNeConnection.getSourceAddress());
        String srcIpMask = IpUtils.prefixToMask(CONST_MASK_32);

        String destIp = IpUtils.getIPFromCIDR(ipSecNeConnection.getPeerAddress());
        String destIpMask = IpUtils.prefixToMask(CONST_MASK_32);

        return new NetRule("permit", srcIp, srcIpMask, destIp, destIpMask);
    }

    private static NetIke buildNetIke(final NeIpSecConnection ipSecNeConnection, String peerIp)
            throws ServiceException {
        char[] psk = EncryptionUtil.decode(ipSecNeConnection.getPsk().toCharArray());

        NetIke netIke = new NetIke(ipSecNeConnection.getIkePolicy().getAuthAlgorithm(),
                ipSecNeConnection.getIkePolicy().getEncryptionAlgorithm(), null, peerIp, String.valueOf(psk));
        netIke.setLocalAddress(IpUtils.getIPFromCIDR(ipSecNeConnection.getSourceAddress()));

        EncryptionUtil.clear(psk);

        if(StringUtils.isNotEmpty(ipSecNeConnection.getIkePolicy().getIkeVersion())) {
            netIke.setVersion(ipSecNeConnection.getIkePolicy().getIkeVersion());
        } else {
            netIke.setVersion(IKEVersion.V2.getName());
        }

        return netIke;
    }

    private static NetIpSec buildNetIpSec(final NeIpSecConnection ipSecNeConnection) throws ServiceException {
        return new NetIpSec(ipSecNeConnection.getIpSecPolicy().getAuthAlgorithm(),
                ipSecNeConnection.getIpSecPolicy().getEncryptionAlgorithm());
    }
}
