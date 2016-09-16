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

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.IOUtils;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverRegistrarListener implements ServletContextListener {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverRegistrarListener.class);
    
    private String instanceId = "sdnoverlayvpndriver-0-1"; 

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        String uri = "/openoapi/drivermgr/v1/drivers/"+this.instanceId;

        RestfulParametes restParametes = new RestfulParametes();

        try {
            restParametes.putHttpContextHeader("Content-Type", "application/json;charset=UTF-8");

            RestfulResponse response = RestfulProxy.delete(uri, restParametes);
            
            if(response.getStatus()==200) {
                LOGGER.info("Driver successfully unregistered from driver manager");
            } else {
                LOGGER.warn("Driver failed unregistered from driver manager");
            }
            
            
        } catch(Exception e) {
            LOGGER.warn("Driver failed unregistered from driver manager", e);
        }

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        String uri = "/openoapi/drivermgr/v1/drivers";

        RestfulParametes restParametes = new RestfulParametes();

        try {
            restParametes.putHttpContextHeader("Content-Type", "application/json;charset=UTF-8");
            String driverDetails = IOUtils.toString(this.getClass().getResourceAsStream("/generalconfig/driver.json"));
            Map driverDetailsMap=JsonUtil.fromJson(driverDetails, Map.class);
            driverDetailsMap.put("instanceID", this.instanceId);
            restParametes.setRawData(JsonUtil.toJson(driverDetailsMap));

            RestfulResponse response = RestfulProxy.post(uri, restParametes);
            if(response.getStatus()==200) {
                LOGGER.info("Driver successfully registered with driver manager");
            } else {
                LOGGER.warn("Driver failed registered with driver manager");
            }
            
        } catch(Exception e) {
            LOGGER.warn("Driver failed registered with driver manager", e);
        }

    }

}
