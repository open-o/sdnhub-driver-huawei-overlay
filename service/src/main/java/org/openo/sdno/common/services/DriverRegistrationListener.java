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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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

    private static final int DRIVER_REGISTRATION_DELAY = 60;

    private static final int DRIVER_REGISTRATION_INITIAL_DELAY = 5;

    private String instanceId = "sdnoverlayvpndriver-0-1";

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture<?> scheduler;

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

        } catch(Exception e) {
            LOGGER.warn("Driver failed unregistered from driver manager", e);
        }

        scheduledExecutorService.shutdown();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        String driverDetails = null;

        RestfulParametes restParametes = new RestfulParametes();
        restParametes.putHttpContextHeader("Content-Type", "application/json;charset=UTF-8");

        try {
            driverDetails = IOUtils.toString(this.getClass().getResourceAsStream("/generalconfig/driver.json"));
        } catch(IOException e) {
            LOGGER.info("Driver registration failed with driver manager : {0}", e.toString());
            return;
        }

        Map driverDetailsMap = JsonUtil.fromJson(driverDetails, Map.class);
        Map<String, String> driverInfo = (Map)driverDetailsMap.get(DriverMgrConst.DRIVER_INFO_KEY);
        driverInfo.put(DriverMgrConst.INSTANCE_ID_KEY, this.instanceId);

        restParametes.setRawData(JsonUtil.toJson(driverDetailsMap));

        // If the registration is unsuccessful re-attempt the driver registration.
        // If the registration is successful then finish the task by cancelling it.
        scheduler = scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                RestfulResponse response = RestfulProxy.post(DriverMgrConst.DRIVER_MANAGER_URL, restParametes);
                if(HttpCode.isSucess(response.getStatus())) {
                    LOGGER.info("Driver successfully registered with driver manager. Now Stop the scheduler.");
                    this.scheduler.cancel(false);
                } else {
                    LOGGER.warn("Driver failed registered with driver manager will reattempt the connection after "
                            + DRIVER_REGISTRATION_DELAY + " seconds.");
                }
            } catch(ServiceException e) {
                LOGGER.warn("Driver registration failed with driver manager, connection will be reattempted after "
                        + DRIVER_REGISTRATION_DELAY + "seconds : " + e.toString());
            }
        }, DRIVER_REGISTRATION_INITIAL_DELAY, DRIVER_REGISTRATION_DELAY, TimeUnit.SECONDS);
    }
}
