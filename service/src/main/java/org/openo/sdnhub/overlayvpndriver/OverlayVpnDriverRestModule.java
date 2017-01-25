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

package org.openo.sdnhub.overlayvpndriver;

import org.openo.sdno.overlayvpn.inventory.sdk.DbOwerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OverlayVpnDriverRestModule Rest Module Class. <br>
 * 
 * @author
 * @version SDNO 0.5 July 14, 2016
 */
public class OverlayVpnDriverRestModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverlayVpnDriverRestModule.class);

    public void start() {
        LOGGER.info("Start OverlayVpnDriverRestModule adapter roa module.");
        DbOwerInfo.init("acBranchSvc", "acbranchdb");
    }

    public void stop() {
        LOGGER.info("Stop OverlayVpnDriverRestModule adapter roa module.");
    }
}
