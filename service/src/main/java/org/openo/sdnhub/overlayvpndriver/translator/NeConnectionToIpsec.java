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

package org.openo.sdnhub.overlayvpndriver.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.openo.sdnhub.overlayvpndriver.common.consts.IKEVersion;
import org.openo.sdnhub.overlayvpndriver.controller.model.Ike;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpSec;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnList;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnection;
import org.openo.sdnhub.overlayvpndriver.service.model.IpSecConnectionType;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdno.framework.container.util.UuidUtils;
import org.openo.sdno.ssl.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 06-Feb-2017
 */
public class NeConnectionToIpsec {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeConnectionToIpsec.class);

    private NeConnectionToIpsec() {

    }

    /**
     * <br/>
     *
     * @param deviceIdToTpsecConnListMap
     * @param ctrlUuid
     * @return
     * @since SDNHUB 0.5
     */
    @SuppressWarnings({"unchecked"})
    public static Map<String, List<IpsecConnList>>
            convert2Model(final Map<String, List<SbiNeIpSec>> deviceIdToTpsecConnListMap, final String ctrlUuid) {

        Map<String, List<IpsecConnList>> neToIpsecModelMap = new ConcurrentHashMap<>();
        for(Entry<String, List<SbiNeIpSec>> entry : deviceIdToTpsecConnListMap.entrySet()) {
            List<IpsecConnList> ipsecModelList = new ArrayList<>();
            List<SbiNeIpSec> ipSecNeConnectionList = entry.getValue();
            List<SbiNeIpSec> workIpSecConnections =
                    new ArrayList<>(CollectionUtils.select(ipSecNeConnectionList, new Predicate() {
                        @Override
                        public boolean evaluate(Object arg0) {
                            return ((SbiNeIpSec)arg0).getWorkType().equals(IpSecConnectionType.WORK.getName());
                        }
                    }));

            List<SbiNeIpSec> projectIpSecConnections =
                    new ArrayList<>(CollectionUtils.select(ipSecNeConnectionList, new Predicate() {

                        @Override
                        public boolean evaluate(Object arg0) {
                            return ((SbiNeIpSec)arg0).getWorkType().equals(IpSecConnectionType.PROJECT.getName());
                        }
                    }));

            if(!CollectionUtils.isEmpty(workIpSecConnections)) {
                List<IpsecConnList> workIpSecModel = convertWorkIpsecModel(workIpSecConnections);
                ipsecModelList.addAll(workIpSecModel);
            }
            if(!CollectionUtils.isEmpty(projectIpSecConnections)) {
                List<IpsecConnList> projectIpSecModel = convertProtectIpsecModel(projectIpSecConnections);
                ipsecModelList.addAll(projectIpSecModel);
            }
            neToIpsecModelMap.put(entry.getKey(), ipsecModelList);
        }
        return neToIpsecModelMap;
    }

    private static List<IpsecConnList> convertWorkIpsecModel(List<SbiNeIpSec> workIpSecConnections) {
        if(CollectionUtils.isEmpty(workIpSecConnections)) {
            return new ArrayList<>();
        }
        List<IpsecConnList> sbiNeIpSecs = new ArrayList<>();
        for(SbiNeIpSec ipSecaNeConnection : workIpSecConnections) {
            IpsecConnList sbiNeIpSec = new IpsecConnList();
            sbiNeIpSec.setName(UuidUtils.createUuid().substring(0, 8));
            IpsecConnection ipsecConn = new IpsecConnection();
            Ike ike = buildIke(ipSecaNeConnection, ipSecaNeConnection.getPeerAddress());
            IpSec ipSec = buildIpsec(ipSecaNeConnection);
            ipsecConn.setIke(ike);
            ipsecConn.setIpsec(ipSec);
            sbiNeIpSecs.add(sbiNeIpSec);
        }
        return sbiNeIpSecs;
    }

    private static List<IpsecConnList> convertProtectIpsecModel(List<SbiNeIpSec> projectIpSecConnections) {
        List<IpsecConnList> sbiNeIpSecs = new ArrayList<>();

        for(SbiNeIpSec ipSecaNeConnection : projectIpSecConnections) {
            ipSecaNeConnection.setName(UuidUtils.createUuid().substring(0, 8));
            IpsecConnList sbiNeIpSec = new IpsecConnList();
            Ike ike = buildIke(ipSecaNeConnection, ipSecaNeConnection.getPeerAddress());
            IpSec ipSec = buildIpsec(ipSecaNeConnection);
            IpsecConnection conn = new IpsecConnection();
            conn.setIke(ike);
            conn.setIpsec(ipSec);
            sbiNeIpSecs.add(sbiNeIpSec);
        }
        return sbiNeIpSecs;
    }

    private static Ike buildIke(final SbiNeIpSec ipSecaNeConnection, String peerIp) {
        char[] psk = null;
        try {
            psk = EncryptionUtil.decode(ipSecaNeConnection.getIkePolicy().getPsk().toCharArray());
        } catch(Exception e) {
            LOGGER.error("decode psk failed");
        }

        Ike ike = new Ike();

        EncryptionUtil.clear(psk);

        if(StringUtils.isNotEmpty(ipSecaNeConnection.getIkePolicy().getIkeVersion())) {
            ike.setVersion(ipSecaNeConnection.getIkePolicy().getIkeVersion());
        } else {
            ike.setVersion(IKEVersion.V2.getName());
        }

        if(ipSecaNeConnection.getIkePolicy().getAuthAlgorithm() != null) {
            ike.setAuthAlgorithm(ipSecaNeConnection.getIkePolicy().getAuthAlgorithm());
        }

        if(ipSecaNeConnection.getIkePolicy().getEncryptionAlgorithm() != null) {
            ike.setAuthAlgorithm(ipSecaNeConnection.getIkePolicy().getEncryptionAlgorithm());
        }

        return ike;
    }

    private static IpSec buildIpsec(SbiNeIpSec ipSecaNeConnection) {
        IpSec ipsec = new IpSec();

        if(ipSecaNeConnection.getIpSecPolicy() != null) {
            if(ipSecaNeConnection.getIpSecPolicy().getAuthAlgorithm() != null) {
                ipsec.setEspAuthAlgorithm(ipSecaNeConnection.getIpSecPolicy().getAuthAlgorithm());
            }
        }
        return ipsec;
    }
}
