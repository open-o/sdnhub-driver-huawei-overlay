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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.common.util.ESRutil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Proxy class for AC Branch Controller, providing restful and web socket
 * interface. <br>
 * 
 * @author
 * @version SDNHUB Driver 0.5 Jul 21, 2016
 */
public class OverlayVpnDriverProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverlayVpnDriverProxy.class);

    private static volatile OverlayVpnDriverProxy uniqueInstance = null;

    private HttpClient httpClient;

    private static Map<String, String> controllerPortMap = new HashMap<String, String>();

    private OverlayVpnDriverProxy() {
        this.initHttpClient();
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

    private String getControllerUrl(String url, String ctrlUuid) throws ServiceException {
        return ESRutil.getControllerUrl(ctrlUuid) + url;
    }

    public void initHttpClient() {
        OverlayDriverHttpClient client = new OverlayDriverHttpClient();
        client.login();
        httpClient = client.getHttpClient();
    }

    /**
     * Send get restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctlrUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {
        try {

            String finalurl = getControllerUrl(url, ctrlUuid);

            HttpGet httpget = new HttpGet(finalurl);
            httpget.addHeader("Content-Type", "application/json");
            httpget.addHeader("Accept", "application/json");

            LOGGER.info("@sendGetMsg" + finalurl);
            HttpResponse response = httpClient.execute(httpget);

            HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
            ContentType contentType = ContentType.get(response.getEntity());
            if((contentType == null) || (null == contentType.getCharset())) {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
            } else {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity()));
            }

            httpReturnMessage.setStatus(response.getStatusLine().getStatusCode());
            return httpReturnMessage;
        } catch(IOException e) {

            throw new ServiceException(ErrorCode.ADAPTER_CONNECTOR_RESPONSE_FAIL, e);
        }

    }

    /**
     * Send post restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctlrUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendPostMsg(String url, String body, String ctrlUuid) throws ServiceException {
        try {
            String finalurl = getControllerUrl(url, ctrlUuid);

            HttpPost httppost = new HttpPost(finalurl);
            httppost.addHeader("Content-Type", "application/json");
            httppost.addHeader("Accept", "application/json");
            if(StringUtils.hasLength(body)) {
                StringEntity reqEntity = new StringEntity(body);
                httppost.setEntity(reqEntity);
            }

            LOGGER.info("@sendpostmsg" + finalurl);
            HttpResponse response = httpClient.execute(httppost);
            LOGGER.info("@sendpostmsg" + finalurl + response);

            HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
            ContentType contentType = ContentType.get(response.getEntity());
            if((contentType == null) || (null == contentType.getCharset())) {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
            } else {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity()));
            }

            httpReturnMessage.setStatus(response.getStatusLine().getStatusCode());
            return httpReturnMessage;

        } catch(IOException e) {

            throw new ServiceException(ErrorCode.ADAPTER_CONNECTOR_RESPONSE_FAIL, e);
        }

    }

    /**
     * Send put restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctlrUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
        try {
            String finalurl = getControllerUrl(url, ctrlUuid);

            HttpPut httpput = new HttpPut(finalurl);
            httpput.addHeader("Content-Type", "application/json");
            httpput.addHeader("Accept", "application/json");

            if(StringUtils.hasLength(body)) {
                StringEntity reqEntity = new StringEntity(body);
                httpput.setEntity(reqEntity);
            }

            LOGGER.info("@sendpostmsg" + finalurl);
            HttpResponse response = httpClient.execute(httpput);
            LOGGER.info("@sendpostmsg" + finalurl + response);

            HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
            ContentType contentType = ContentType.get(response.getEntity());
            if((contentType == null) || (null == contentType.getCharset())) {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
            } else {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity()));
            }

            httpReturnMessage.setStatus(response.getStatusLine().getStatusCode());
            return httpReturnMessage;

        } catch(IOException e) {

            throw new ServiceException(ErrorCode.ADAPTER_CONNECTOR_RESPONSE_FAIL, e);
        }
    }

    /**
     * Send delete restful request.<br>
     *
     * @param url
     *            The restful URL path
     * @param body
     *            The message body
     * @param ctlrUuid
     *            The controller UUID
     * @return The object of HTTPReturnMessage
     * @throws ServiceException
     * @since SDNHUB Driver 0.5
     */
    public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {
        try {
            String finalurl = getControllerUrl(url, ctrlUuid);

            HttpDelete httpdelete = new HttpDelete(finalurl);
            httpdelete.addHeader("Content-Type", "application/json");
            httpdelete.addHeader("Accept", "application/json");
            if(StringUtils.hasLength(body)) {
                StringEntity reqEntity = new StringEntity(body);
                // TODO: HttpDelete has no set entity method
                // httpdelete.setEntity(reqEntity);
            }

            LOGGER.info("@sendpostmsg" + finalurl);
            HttpResponse response = httpClient.execute(httpdelete);
            LOGGER.info("@sendpostmsg" + finalurl + response);

            HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
            ContentType contentType = ContentType.get(response.getEntity());
            if((contentType == null) || (null == contentType.getCharset())) {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
            } else {
                httpReturnMessage.setBody(EntityUtils.toString(response.getEntity()));
            }

            httpReturnMessage.setStatus(response.getStatusLine().getStatusCode());
            return httpReturnMessage;

        } catch(IOException e) {

            throw new ServiceException(ErrorCode.ADAPTER_CONNECTOR_RESPONSE_FAIL, e);
        }
    }

}