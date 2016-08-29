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

package org.openo.sdno.overlayvpndriver.login;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.util.http.HTTPReturnMessage;

/**
 * provides methods for proxy to communicate controller
 * 
 * @author
 * @version SDNO 0.5 Aug 5, 2016
 */
public interface IOverlayVpnDriverProxy {

    /**
     * Send get restful request.<br/>
     *
     * @param url The restful URL path
     * @param body The message body
     * @param ctlrUuid The controller UUID
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    HTTPReturnMessage sendGetMsg(String url, String body, String ctlrUuid) throws ServiceException;

    /**
     * Send post restful request.<br/>
     *
     * @param url The restful URL path
     * @param body The message body
     * @param ctlrUuid The controller UUID
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    HTTPReturnMessage sendPostMsg(String url, String body, String ctlrUuid) throws ServiceException;

    /**
     * Send put restful request.<br/>
     *
     * @param url The restful URL path
     * @param body The message body
     * @param ctlrUuid The controller UUID
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    HTTPReturnMessage sendPutMsg(String url, String body, String ctlrUuid) throws ServiceException;

    /**
     * Send delete restful request.<br/>
     *
     * @param url The restful URL path
     * @param body The message body
     * @param ctlrUuid The controller UUID
     * @return The object of HTTPReturnMessage
     * @since SDNO 0.5
     */
    HTTPReturnMessage sendDeleteMsg(String url, String body, String ctlrUuid) throws ServiceException;
}
