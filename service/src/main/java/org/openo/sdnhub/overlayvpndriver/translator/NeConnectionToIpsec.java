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
import org.codehaus.jackson.type.TypeReference;
import org.openo.sdnhub.overlayvpndriver.common.consts.IKEVersion;
import org.openo.sdnhub.overlayvpndriver.controller.model.Ike;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpSec;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnList;
import org.openo.sdnhub.overlayvpndriver.controller.model.IpsecConnection;
import org.openo.sdnhub.overlayvpndriver.controller.model.LocalId;
import org.openo.sdnhub.overlayvpndriver.controller.model.RuleList;
import org.openo.sdnhub.overlayvpndriver.service.model.IpSecConnectionType;
import org.openo.sdnhub.overlayvpndriver.service.model.NeRoleType;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdnhub.overlayvpndriver.service.model.Ip;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.ssl.EncryptionUtil;
import org.openo.sdno.util.ip.IpUtils;
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

    private static final String TRUE = "true";

    private static final String FALSE = "false";

    private static final int CONST_MASK_32 = 32;

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

        Map<String, List<IpsecConnList>> neIdToIpsecModelMap = new ConcurrentHashMap<>();
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

            IpsecConnList workIpsecModel = convertWorkIpsecModel(workIpSecConnections);
            ipsecModelList.add(workIpsecModel);
            if(!CollectionUtils.isEmpty(projectIpSecConnections)) {
                IpsecConnList protectIpsecModel = convertProtectIpsecModel(projectIpSecConnections);
                ipsecModelList.add(protectIpsecModel);
            }

            neIdToIpsecModelMap.put(entry.getKey(), ipsecModelList);
        }
        return neIdToIpsecModelMap;
    }

    private static IpsecConnList convertWorkIpsecModel(List<SbiNeIpSec> workIpSecConnections) {
        if(CollectionUtils.isEmpty(workIpSecConnections))
        {
            return new IpsecConnList();
        }

        String wanSunIfName = workIpSecConnections.get(0).getSoureIfName();
        IpsecConnList ipsecModel = new IpsecConnList(wanSunIfName);

        ipsecModel.setUuid(workIpSecConnections.get(0).getExternalIpSecId());
        ipsecModel.setName(ipsecModel.getUuid().substring(0, 8));

        List<IpsecConnection> connList= new ArrayList<IpsecConnection>();

        for (SbiNeIpSec ipSecNeConnection : workIpSecConnections)
        {
            IpsecConnection ipsecConnection = new IpsecConnection(ipSecNeConnection.getNeId());

            ipsecConnection.setSeqNumber(Integer.valueOf(ipSecNeConnection.getExternalId()));
            Ike ike = buildIke(ipSecNeConnection, ipSecNeConnection.getPeerAddress());


            IpSec ipSec = buildIpsec(ipSecNeConnection);
            if(FALSE.equals(ipSecNeConnection.getIsTemplateType()))
            {
                ipsecConnection.setType(FALSE);
                ipsecConnection.setRuleList(buildRuleList(ipSecNeConnection));
            }
            else
            {
                ipsecConnection.setType(TRUE);
            }

            ipsecConnection.setIke(ike);
            ipsecConnection.setIpSec(ipSec);

            if((!StringUtils.isEmpty(ipSecNeConnection.getNqa())) && NeRoleType.LOCALCPE.getName()
                    .equals(ipSecNeConnection.getLocalNeRole()))
            {
                ipsecConnection.setNqaId(ipSecNeConnection.getNqa());
                ipsecConnection.setNqaState("up");
            }

            ipsecConnection.setQosPreClassify(ipSecNeConnection.getQosPreClassify());

            connList.add(ipsecConnection);

            if(TRUE.equals(ipSecNeConnection.getIsTemplateType()))
            {
                break;
            }
        }

        ipsecModel.setIpsecConnection(connList);
        return ipsecModel;
    }

    private static IpsecConnList convertProtectIpsecModel(List<SbiNeIpSec> projectIpSecConnections) {
        String lteName = projectIpSecConnections.get(0).getSoureIfName();
        IpsecConnList ipsecModel = new IpsecConnList(lteName);

        ipsecModel.setUuid(projectIpSecConnections.get(0).getExternalIpSecId());
        ipsecModel.setName(ipsecModel.getUuid().substring(0, 8));

        List<IpsecConnection> connList= new ArrayList<IpsecConnection>();

        for (SbiNeIpSec ipSecNeConnection : projectIpSecConnections)
        {
            IpsecConnection ipsecConnection = new IpsecConnection(ipSecNeConnection.getNeId());
            ipsecConnection.setSeqNumber(Integer.valueOf(ipSecNeConnection.getExternalId()));

            Ike ike = buildIke(ipSecNeConnection, ipSecNeConnection.getPeerAddress());

            LocalId localIdInfo = new LocalId("fqdn",ipSecNeConnection.getTenantName());
            ike.setLocalId(localIdInfo);

            IpSec ipSec = buildIpsec(ipSecNeConnection);

            if(FALSE.equals(ipSecNeConnection.getIsTemplateType()))
            {
                ipsecConnection.setType(FALSE);
                ipsecConnection.setRuleList(buildRuleList(ipSecNeConnection));
            }
            else
            {
                ipsecConnection.setType(TRUE);
            }

            ipsecConnection.setIpSec(ipSec);
            ipsecConnection.setIke(ike);

            if((!StringUtils.isEmpty(ipSecNeConnection.getNqa())) && NeRoleType.LOCALCPE.getName()
                    .equals(ipSecNeConnection.getLocalNeRole()))
            {
                ipsecConnection.setNqaId(ipSecNeConnection.getNqa());
                ipsecConnection.setNqaState("down");
            }

            ipsecConnection.setQosPreClassify(ipSecNeConnection.getQosPreClassify());
            connList.add(ipsecConnection);
        }

        ipsecModel.setIpsecConnection(connList);
        return ipsecModel;
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

    private static List<RuleList> buildRuleList(final SbiNeIpSec ipSecNeConnection)
    {
        List<RuleList> ruleList = new ArrayList<RuleList>();

        List<Ip> sourceLanIps = JsonUtil.fromJson(ipSecNeConnection.getSourceLanCidrs(), new TypeReference<List<Ip>>(){});
        List<Ip> peerLanIps = JsonUtil.fromJson(ipSecNeConnection.getPeerLanCidrs(), new TypeReference<List<Ip>>(){});

        for(Ip sourceLanIp : sourceLanIps)
        {
            for(Ip peerLanIp : peerLanIps)
            {
                RuleList rule = buildRule(ipSecNeConnection);
                rule.setSrcIp(sourceLanIp.getIpv4());
                rule.setDesIp(peerLanIp.getIpv4());

                rule.setSrcNetMask(IpUtils.prefixToMask(Integer.valueOf(sourceLanIp.getIpMask())));
                rule.setDesNetMask(IpUtils.prefixToMask(Integer.valueOf(peerLanIp.getIpMask())));

                ruleList.add(rule);
            }
        }

        return ruleList;
    }

    private static RuleList buildRule(final SbiNeIpSec ipSecNeConnection)
    {
        String srcIp = ipSecNeConnection.getSourceAddress();
        //String srcIpMask = IpUtils.prefixToMask(IpUtils.getIPMaskFromCIDR(ipSecNeConnection.getSourceAddress()));
        String srcIpMask = IpUtils.prefixToMask(CONST_MASK_32);

        String destIp = ipSecNeConnection.getPeerAddress();
        //String destIpMask = IpUtils.prefixToMask(IpUtils.getIPMaskFromCIDR(ipSecNeConnection.getPeerAddress()));
        String destIpMask = IpUtils.prefixToMask(CONST_MASK_32);

        return new RuleList("permit", srcIp, srcIpMask, destIp, destIpMask);
    }
}
