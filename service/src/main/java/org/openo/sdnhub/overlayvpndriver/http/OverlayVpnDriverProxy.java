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

package org.openo.sdnhub.overlayvpndriver.http;

import java.util.HashMap;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.ESRutil;
import org.openo.sdno.exception.HttpCode;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Proxy class for AC Branch Controller, providing restful and web socket
 * interface. <br>
 *
 * @author
 * @version SDNHUB Driver 0.5 Jun 10, 2017
 */
public class OverlayVpnDriverProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverlayVpnDriverProxy.class);

    private static volatile OverlayVpnDriverProxy uniqueInstance = null;

    private static final String SSO_LOGIN = "/sso/login";

    private static final String SSO_REDIRECT = "ssoRedirect";

    private static final String SSO_LOGOUT = "sso/logout";

    private OverlayVpnDriverProxy() {
    }

    /**
     * Get synchronized instance. <br>
     *
     * @return synchronized instance
     * @since SDNHUB Driver 0.5
     */
    public static synchronized OverlayVpnDriverProxy getInstance() {
        if(null == uniqueInstance) {
            uniqueInstance = new OverlayVpnDriverProxy();
        }
        return uniqueInstance;
    }

    /**
     * Send get restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctrlUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
        OverlayVpnDriverSsoProxy acSSOLogin = createACSSOProxy(ctrlUuid);
        if(null == acSSOLogin) {
            return new HTTPReturnMessage();
        }

        HTTPReturnMessage get = acSSOLogin.get(url);
        if(isNeedRelogin(get)) {
            LOGGER.info("Not Login, try to login.");
            if(acSSOLogin.login(SSO_LOGIN)) {
                get = acSSOLogin.get(url);
            } else {
                get.setStatus(HttpCode.ERR_FAILED);
            }
        }

        return get;
    }

    /**
     * Send post restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctrlUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
        OverlayVpnDriverSsoProxy acSSOLogin = createACSSOProxy(ctrlUuid);
        if(null == acSSOLogin) {
            return new HTTPReturnMessage();
        }

        HTTPReturnMessage post = acSSOLogin.post(url, body);
        if(isNeedRelogin(post)) {
            LOGGER.info("Not Login, try to login.");
            if(acSSOLogin.login(SSO_LOGIN)) {
                post = acSSOLogin.post(url, body);
            } else {
                post.setStatus(HttpCode.ERR_FAILED);
            }
        }
        return post;
    }

    /**
     * Send put restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctrlUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
        OverlayVpnDriverSsoProxy acSSOLogin = createACSSOProxy(ctrlUuid);
        if(null == acSSOLogin) {
            return new HTTPReturnMessage();
        }

        HTTPReturnMessage put = acSSOLogin.put(url, body);
        if(isNeedRelogin(put)) {
            LOGGER.info("Not Login, try to login.");
            if(acSSOLogin.login(SSO_LOGIN)) {
                put = acSSOLogin.put(url, body);
            } else {
                put.setStatus(HttpCode.ERR_FAILED);
            }
        }

        return put;
    }

    /**
     * Send delete restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctrlUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
        OverlayVpnDriverSsoProxy acSSOLogin = createACSSOProxy(ctrlUuid);
        if(null == acSSOLogin) {
            return new HTTPReturnMessage();
        }

        HTTPReturnMessage delete = acSSOLogin.delete(url, body);
        if(isNeedRelogin(delete)) {
            LOGGER.info("Not Login, try to login.");
            if(acSSOLogin.login(SSO_LOGIN)) {
                delete = acSSOLogin.delete(url, body);
            } else {
                delete.setStatus(HttpCode.ERR_FAILED);
            }
        }

        return delete;
    }

    /*
     * private OverlayVpnDriverSsoProxy createACSSOProxy(String ctlrUuid) {
     * OverlayVpnDriverSsoProxy ssoProxy = null;
     * try {
     * SdnControllerDao controllerDao = new SdnControllerDao();
     * SdnController sdnController = controllerDao.querySdnControllerById(ctlrUuid);
     * URL url = new URL(sdnController.getUrl());
     * ssoProxy = OverlayVpnDriverSsoProxy.getInstance(url.getHost(), String.valueOf(url.getPort()),
     * sdnController.getUserName(), sdnController.getPassword());
     * } catch(ServiceException | MalformedURLException e) {
     * LOGGER.error("create OverlayVpnDriverSsoProxy faied", e);
     * }
     * return ssoProxy;
     * }
     */

    private OverlayVpnDriverSsoProxy createACSSOProxy(String ctlrUuid) {

        OverlayVpnDriverSsoProxy acSSOLogin = null;
        try {
            Map<String, Object> controllerMap = ESRutil.getControllerDetails(ctlrUuid);

            if(CollectionUtils.isEmpty(controllerMap)) {
                return null;
            }

            String url = (String)controllerMap.get("url");

            String controllerIp = readIpPortMapFromUrl(url).get("ip");
            String controllerPort = readIpPortMapFromUrl(url).get("port");
            if(!StringUtils.hasLength(controllerIp)) {
                return null;
            }

            String userName = controllerMap.get("userName").toString();
            String pwd = controllerMap.get("password").toString();

            acSSOLogin = OverlayVpnDriverSsoProxy.getInstance(controllerIp, controllerPort, userName, pwd);

        } catch(ServiceException e) {
            LOGGER.error("controller comm parameters are not exist, uuid: " + ctlrUuid);
        }

        return acSSOLogin;
    }

    private boolean isNeedRelogin(HTTPReturnMessage httpRspMsg) {
        return isRedirectResponse(httpRspMsg) || isLogoutResponse(httpRspMsg);
    }

    private boolean isLogoutResponse(HTTPReturnMessage httpRspMsg) {
        return (null != httpRspMsg.getBody()) && (httpRspMsg.getBody().indexOf(SSO_LOGOUT) > 0);
    }

    private boolean isRedirectResponse(HTTPReturnMessage httpRspMsg) {
        return (null != httpRspMsg.getBody()) && (httpRspMsg.getBody().indexOf(SSO_REDIRECT) > 0);
    }

    private Map<String, String> readIpPortMapFromUrl(String url) {

        Map<String, String> urlSplitMap = new HashMap<>();
        if(StringUtils.hasLength(url)) {
            String[] urlSplit = url.split("//");
            String httpType = urlSplit[0];
            String ip = null;
            String port = null;
            if(urlSplit.length > 1) {
                String[] ipPort = urlSplit[1].split(":");
                ip = ipPort[0];
                port = (ipPort.length > 1) ? ipPort[1] : null;
            }
            urlSplitMap.put("ip", ip);
            urlSplitMap.put("port", port);
            urlSplitMap.put("httpType", httpType);
        }
        return urlSplitMap;
    }
}
