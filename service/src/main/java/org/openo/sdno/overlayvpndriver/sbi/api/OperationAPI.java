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

package org.openo.sdno.overlayvpndriver.sbi.api;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.cli.protocol.ProtocolParameter;
import org.openo.sdno.cli.protocol.SshProtocol;
import org.openo.sdno.overlayvpndriver.util.config.DeviceCommParamReader;
import org.openo.sdno.overlayvpndriver.util.config.DeviceParam;

/**
 * Base class of Operation API.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-10-8
 */
public class OperationAPI {

    protected SshProtocol sshProtocol;

    /**
     * Constructor<br>
     * 
     * @param ipAddress Device IpAddress
     * @since SDNO 0.5
     */
    public OperationAPI(String deviceId) throws ServiceException {
        DeviceParam commParam = DeviceCommParamReader.getDeviceCommParam(deviceId);
        ProtocolParameter param = new ProtocolParameter(commParam.getIpAddress(), Integer.parseInt(commParam.getPort()),
                commParam.getUserName(), commParam.getPassword());
        sshProtocol = new SshProtocol(param);
    }

}
