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

package org.openo.sdnhub.common.services;

import mockit.Mock;
import mockit.MockUp;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DriverRegistrationListenerTest {

    DriverRegistrationListener driverRegLstner = new DriverRegistrationListener();

    private static final String DM_REGISTRATION_FILE = "generalconfig/driver.json";
    // final CountDownLatch latch = new CountDownLatch( 1 );

    @Test
    public void testContextDestroyed_Success() throws Exception {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse delete(String uri, RestfulParametes restParametes) throws ServiceException {

                RestfulResponse response = new RestfulResponse();
                response.setStatus(200);
                return response;
            }
        };

        driverRegLstner.contextDestroyed(null);
    }

    @Test
    public void testContextDestroyed_Failure() throws Exception {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse delete(String uri, RestfulParametes restParametes) throws ServiceException {

                RestfulResponse response = new RestfulResponse();
                response.setStatus(500);
                return response;
            }
        };

        driverRegLstner.contextDestroyed(null);
    }

    @Test
    public void testContextDestroyed_Exception() throws Exception {

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse delete(String uri, RestfulParametes restParametes) throws ServiceException {
                throw new ServiceException("Invalid url param , delete instance not found");
            }
        };

        driverRegLstner.contextDestroyed(null);
    }

    @Test
    public void testContextInitialized_FileNotFound() throws Exception {

        File file = new File(DM_REGISTRATION_FILE);
        if (file.exists()) {
            file.delete();
        }
        driverRegLstner.contextInitialized(null);
    }

    @Test
    public void testContextInitializedIoException() throws Exception {

        File file = new File(DM_REGISTRATION_FILE);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        driverRegLstner.contextInitialized(null);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testContextInitialized_Success() throws Exception {

        File file = new File(DM_REGISTRATION_FILE);
        file.getParentFile().mkdir();
        file.createNewFile();

        Map<String, Map<String, String>> dmRegistrationBodyMap = new HashMap<String, Map<String, String>>();

        Map<String, String> driverInfoMap = new HashMap<String, String>();
        driverInfoMap.put("instanceID", "usb12345");
        dmRegistrationBodyMap.put("driverInfo", driverInfoMap);

        FileOutputStream fileOutStream = new FileOutputStream(file);
        ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
        objOutStream.writeObject(dmRegistrationBodyMap);
        objOutStream.close();

        new MockUp<ObjectMapper>() {

            @SuppressWarnings("unchecked")
            @Mock
            public <T> T readValue(byte[] src, Class<T> valueType) {

                Map<String, Map<String, String>> dmRegistrationBodyMap = new HashMap<String, Map<String, String>>();
                Map<String, String> driverInfoMap = new HashMap<String, String>();
                driverInfoMap.put("instanceID", "usb12345");
                dmRegistrationBodyMap.put("driverInfo", driverInfoMap);
                return (T)dmRegistrationBodyMap;
            }
        };

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse post(String uri, RestfulParametes restParametes) throws ServiceException {

                RestfulResponse response = new RestfulResponse();
                response.setStatus(200);
                return response;
            }
        };

        driverRegLstner.contextInitialized(null);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testContextInitializedWithIp() throws Exception {

        File file = new File(DM_REGISTRATION_FILE);
        file.getParentFile().mkdir();
        file.createNewFile();

        Map<String, Map<String, String>> dmRegistrationBodyMap = new HashMap<String, Map<String, String>>();

        Map<String, String> driverInfoMap = new HashMap<String, String>();
        driverInfoMap.put("instanceID", "usb12345");
        driverInfoMap.put("ip", "10.172.13.12");
        dmRegistrationBodyMap.put("driverInfo", driverInfoMap);

        FileOutputStream fileOutStream = new FileOutputStream(file);
        ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
        objOutStream.writeObject(dmRegistrationBodyMap);
        objOutStream.close();

        new MockUp<ObjectMapper>() {

            @SuppressWarnings("unchecked")
            @Mock
            public <T> T readValue(byte[] src, Class<T> valueType) {

                Map<String, Map<String, String>> dmRegistrationBodyMap = new HashMap<String, Map<String, String>>();
                Map<String, String> driverInfoMap = new HashMap<String, String>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "10.172.13.12");
                dmRegistrationBodyMap.put("driverInfo", driverInfoMap);
                return (T)dmRegistrationBodyMap;
            }
        };

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse post(String uri, RestfulParametes restParametes) throws ServiceException {

                RestfulResponse response = new RestfulResponse();
                response.setStatus(200);
                return response;
            }
        };

        driverRegLstner.contextInitialized(null);
        if (file.exists()) {
            file.delete();
        }
    }
}
