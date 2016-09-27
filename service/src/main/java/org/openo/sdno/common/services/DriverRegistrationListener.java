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

package org.openo.sdno.common.services;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.IOUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.HttpCode;
import org.openo.sdno.overlayvpn.consts.HttpConst;
import org.openo.sdno.overlayvpn.drivermgr.DriverMgrConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverRegistrationListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverRegistrationListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        String uri = DriverMgrConst.DRIVER_MANAGER_URL + "/" + DriverMgrConst.OVERLAYVPN_DRIVER_INSTANCEID;

        RestfulParametes restParametes = new RestfulParametes();

        try {
            restParametes.putHttpContextHeader(HttpConst.CONTEXT_TYPE_HEADER, HttpConst.MEDIA_TYPE_JSON);
            RestfulResponse response = RestfulProxy.delete(uri, restParametes);
            if(HttpCode.isSucess(response.getStatus())) {
                LOGGER.info("Driver successfully un-registration with driver manager");
            } else {
                LOGGER.warn("Driver failed un-registration with driver manager");
            }
        } catch(ServiceException e) {
            LOGGER.warn("Driver failed un-registration with driver manager", e);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        RestfulParametes restParametes = new RestfulParametes();
        try {
            restParametes.putHttpContextHeader(HttpConst.CONTEXT_TYPE_HEADER, HttpConst.MEDIA_TYPE_JSON);
            @SuppressWarnings("deprecation")
            String driverDetails = IOUtils.toString(this.getClass().getResourceAsStream("/generalconfig/driver.json"));
            Map driverDetailsMap = JsonUtil.fromJson(driverDetails, Map.class);
            @SuppressWarnings("unchecked")
            Map<String, String> driverInfo = (Map<String, String>)driverDetailsMap.get(DriverMgrConst.DRIVER_INFO_KEY);
            driverInfo.put(DriverMgrConst.INSTANCE_ID_KEY, DriverMgrConst.OVERLAYVPN_DRIVER_INSTANCEID);
            restParametes.setRawData(JsonUtil.toJson(driverDetailsMap));
            RestfulResponse response = RestfulProxy.post(DriverMgrConst.DRIVER_MANAGER_URL, restParametes);
            if(HttpCode.isSucess(response.getStatus())) {
                LOGGER.info("Driver successfully registration with driver manager");
            } else {
                LOGGER.warn("Driver failed registration with driver manager");
            }
        } catch(ServiceException | IOException e) {
            LOGGER.warn("Driver failed registration with driver manager", e);
        }
    }

}
