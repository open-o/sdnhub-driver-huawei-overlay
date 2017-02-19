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

package org.openo.sdnhub.overlayvpndriver.sbi.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.consts.CommonConst;
import org.openo.sdnhub.overlayvpndriver.controller.consts.ControllerUrlConst;
import org.openo.sdnhub.overlayvpndriver.controller.model.EthInterfaceConfig;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiIfVlan;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Vlan service implementation.<br>
 *
 * @author
 * @version SDNHUB 0.5 Jan 16, 2017
 */
public class VlanServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(VlanServiceImpl.class);

    /**
     * Queries device ethernet configuration by if-name using a specific Controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param interfaceName if-name
     * @return collection of interface queried configuration status data
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public List<EthInterfaceConfig> queryEthByName(final String ctrlUuid, final String deviceId,
            final String interfaceName) throws ServiceException {

        String queryUrl = MessageFormat.format(ControllerUrlConst.ETH_CONFIG_URL, deviceId);
        if(StringUtils.hasLength(interfaceName)) {
            final StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(queryUrl + "?name=" + interfaceName);
            queryUrl = strBuilder.toString();
        }

        ResultRsp<List<EthInterfaceConfig>> response = EthInterfaceConfigImpl.queryEthConfig(ctrlUuid, queryUrl);
        if(!response.isValid()) {
            LOGGER.error("query eth failed,info :" + response.toString());
            throw new ServiceException("localsite.vlan.ac.error", "query eth config fail in ac");
        }
        return response.getData();
    }

    /**
     * Makes a collection of queried interface configuration by querying single interface at a time.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param ifVlan collection of interface Vlan configuration
     * @return collection of ethernet interface queried configuration
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public List<EthInterfaceConfig> combineCreateLanEthConfig(final String ctrlUuid, final String deviceId,
            final List<SbiIfVlan> ifVlan) throws ServiceException {

        final List<EthInterfaceConfig> lanEthConfig = new ArrayList<EthInterfaceConfig>();
        for(SbiIfVlan inf : ifVlan) {
            lanEthConfig.add(getCreateSingleLanConfig(ctrlUuid, deviceId, inf));
        }
        return lanEthConfig;
    }

    /**
     * Configure interface configuration using a specific controller.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param interfacesConfig collection of interface Vlan configuration
     * @return collection of configured ethernet interface configuration
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public List<EthInterfaceConfig> configEth(final String ctrlUuid, final String deviceId,
            final List<EthInterfaceConfig> interfacesConfig) throws ServiceException {
        final Map<String, List<EthInterfaceConfig>> crtInfoMap = new HashMap<String, List<EthInterfaceConfig>>();
        crtInfoMap.put(CommonConst.ETHCONFIG_LIST, interfacesConfig);

        final String configUrl = MessageFormat.format(ControllerUrlConst.ETH_CONFIG_URL, deviceId);
        ResultRsp<List<EthInterfaceConfig>> response =
                EthInterfaceConfigImpl.configEthInterface(ctrlUuid, configUrl, JsonUtil.toJson(crtInfoMap));
        if(!response.isValid()) {
            LOGGER.error("config eth fail, info: " + response.toString());
            throw new ServiceException("localsite.vlan.ac.error", "eth config fail in ac");
        }
        return response.getData();
    }

    private EthInterfaceConfig getCreateSingleLanConfig(final String ctrlUuid, final String deviceId,
            final SbiIfVlan inf) throws ServiceException {
        List<EthInterfaceConfig> ethConfigList = new ArrayList<EthInterfaceConfig>();
        try {
            ethConfigList = queryEthByName(ctrlUuid, deviceId, inf.getIfName());
        } catch(ServiceException e) {
            LOGGER.error("query eth by name error.", e);
        }
        EthInterfaceConfig ethConfig;
        if(CollectionUtils.isEmpty(ethConfigList)) {
            LOGGER.debug("no eth config, infName :" + inf.getIfName());
            ethConfig = buildEthInterfaceAsNoConfig(inf);
        } else {
            ethConfig = ethConfigList.get(0);
        }
        ethConfig.setDefaultVlan(inf.getDefaultVlan().toString());
        ethConfig.setTrunkVlan(String.valueOf(inf.getDefaultVlan()));

        return ethConfig;
    }

    private EthInterfaceConfig buildEthInterfaceAsNoConfig(final SbiIfVlan inf) {
        EthInterfaceConfig config = new EthInterfaceConfig();
        config.setId(inf.getUuid());
        config.setName(inf.getIfName());
        return config;
    }

    /**
     * Makes a collection of queried interface configuration by querying single interface at a time.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param ifVlanList collection of interface Vlan configuration
     * @return collection of ethernet interface queried configuration
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public List<EthInterfaceConfig> combineLanEthConfig(final String ctrlUuid, final String deviceId,
            final List<SbiIfVlan> ifVlanList) throws ServiceException {

        List<EthInterfaceConfig> lanEthConfig = new ArrayList<EthInterfaceConfig>();
        for(SbiIfVlan inf : ifVlanList) {
            lanEthConfig.add(getSingleLanConfig(ctrlUuid, deviceId, inf));
        }
        return lanEthConfig;
    }

    private EthInterfaceConfig getSingleLanConfig(final String ctrlUuid, final String deviceId, final SbiIfVlan ifVlan)
            throws ServiceException {
        List<EthInterfaceConfig> ethConfigList = queryEthByName(ctrlUuid, deviceId, ifVlan.getIfName());
        if(CollectionUtils.isEmpty(ethConfigList)) {
            LOGGER.error("no eth config in AC");
            throw new ServiceException("localsite.vlan.ac.error", "no config in ac");
        }
        EthInterfaceConfig ethConfig = ethConfigList.get(0);
        ethConfig.setDefaultVlan(ifVlan.getDefaultVlan().toString());
        ethConfig.setTrunkVlan(String.valueOf(ifVlan.getDefaultVlan()));
        return ethConfig;
    }

    /**
     * Queries device for ethernet interface configuration by interface ids.<br>
     *
     * @param ctrlUuid Controller UUID
     * @param deviceId device id
     * @param ids vlan ids
     * @return collection of ethernet interface queried configuration
     * @throws ServiceException when input validation fails
     * @since SDNHUB 0.5
     */
    public List<EthInterfaceConfig> queryEthByIds(final String ctrlUuid, final String deviceId, final String ids)
            throws ServiceException {
        String queryUrl = MessageFormat.format(ControllerUrlConst.ETH_CONFIG_URL, deviceId);
        if(StringUtils.hasLength(ids)) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(queryUrl + "?ids=" + ids);
            queryUrl = strBuilder.toString();
        }

        ResultRsp<List<EthInterfaceConfig>> response = EthInterfaceConfigImpl.queryEthConfig(ctrlUuid, queryUrl);
        if(!response.isValid()) {
            LOGGER.error("query eth failed, info :" + response.toString());
            throw new ServiceException("localsite.vlan.ac.error", "query eth config fail in ac");
        }
        return response.getData();
    }

    /**
     * Creates collection of interface configuration by using controller queried interface response.<br>
     *
     * @param configRsp interface configuration from controller response
     * @param ifVlanReq inteface configuration from service SBI
     * @return collection of vlan interface configuration
     * @since SDNHUB 0.5
     */
    public List<SbiIfVlan> buildCreateIfVlanRsp(final List<EthInterfaceConfig> configRsp,
            final List<SbiIfVlan> ifVlanReq) {
        List<SbiIfVlan> ifVlans = new ArrayList<SbiIfVlan>();
        if(CollectionUtils.isEmpty(ifVlanReq)) {
            return ifVlans;
        }
        for(SbiIfVlan inf : ifVlanReq) {
            for(EthInterfaceConfig ethConfig : configRsp) {
                if(inf.getIfName().equals(ethConfig.getName())) {
                    SbiIfVlan ifVlan = inf;
                    ifVlan.setEthInterfaceConfigId(ethConfig.getId());
                    ifVlans.add(ifVlan);
                }
            }
        }
        return ifVlans;
    }

    /**
     * Creates collection of interface configuration by using controller queried interface response.<br>
     *
     * @param configRsp interface configuration from controller response
     * @return collection of vlan interface configuration
     * @since SDNHUB 0.5
     */
    public List<SbiIfVlan> buildIfVlanRsp(final List<EthInterfaceConfig> configRsp) {
        List<SbiIfVlan> ifVlans = new ArrayList<SbiIfVlan>();
        if(CollectionUtils.isEmpty(configRsp)) {
            return ifVlans;
        }
        for(EthInterfaceConfig ethConfig : configRsp) {
            SbiIfVlan ifVlan = new SbiIfVlan();

            ifVlan.setEthInterfaceConfigId(ethConfig.getId());
            ifVlan.setIfName(ethConfig.getName());
            ifVlan.setDefaultVlan(Integer.valueOf(ethConfig.getDefaultVlan()));
            ifVlan.setVlans(ethConfig.getTrunkVlan());

            ifVlans.add(ifVlan);
        }
        return ifVlans;
    }
}
