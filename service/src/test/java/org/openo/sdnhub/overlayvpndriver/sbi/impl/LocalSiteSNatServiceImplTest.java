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

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.controller.model.AcSNat;
import org.openo.sdnhub.overlayvpndriver.http.OverlayVpnDriverProxy;
import org.openo.sdno.exception.ParameterServiceException;
import org.openo.sdno.util.http.HTTPReturnMessage;

public class LocalSiteSNatServiceImplTest {

    LocalSiteSNatServiceImpl service = new LocalSiteSNatServiceImpl();

    AcSNat acSnat = new AcSNat();

    @Test(expected = ServiceException.class)
    public void testCreateSnatHttpError() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        service.createSNat(null, "123", "extSysID=ctrlid123");
    }

    @Test(expected = ServiceException.class)
    public void testCreateSnatHttpErrorEmptyBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        service.createSNat(null, "123", "extSysID=ctrlid123");
    }

    @Test(expected = ParameterServiceException.class)
    public void testCreateSnatDeviceId() throws ServiceException {

        service.createSNat(null, "123", "");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSNatHttpError() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        acSnat.setId("2467a068795b41ee9676bc79168da7a6");
        service.updateSNat(acSnat, "extSysID=ctrlid123", "device123");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSNatDeviceId() throws ServiceException {

        acSnat.setId("2467a068795b41ee9676bc79168da7a6");
        service.updateSNat(acSnat, "extSysID=ctrlid123", "");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSNatIdNull() throws ServiceException {

        acSnat.setId(null);
        service.updateSNat(acSnat, "extSysID=ctrlid123", "");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateSNatHttpErrorEmptyBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendPutMsg(String url, String body, String ctrlUuid) throws ServiceException {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        acSnat.setId("2467a068795b41ee9676bc79168da7a6");
        service.updateSNat(acSnat, "extSysID=ctrlid123", "123");
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnatHttpError() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        service.querySNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
    }

    @Test(expected = ServiceException.class)
    public void testQuerySnatHttpErrorEmptyBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendGetMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        service.querySNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
    }

    @Test(expected = ParameterServiceException.class)
    public void testQuerySnatdeviceId() throws ServiceException {

        service.querySNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "");
    }

    @Test(expected = ParameterServiceException.class)
    public void testQuerySnatNatId() throws ServiceException {

        service.querySNat(null, "extSysID=ctrlid123", "123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSNatHttpError() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(500);
                return httpReturnMessage;
            }
        };
        service.deleteSNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
    }

    @Test(expected = ParameterServiceException.class)
    public void testDeleteSNatDeviceId() throws ServiceException {

        service.deleteSNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "");
    }

    @Test(expected = ParameterServiceException.class)
    public void testDeleteSNatId() throws ServiceException {

        service.deleteSNat(null, "extSysID=ctrlid123", "123");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteSNatHttpErrorEmptyBody() throws ServiceException {

        new MockUp<OverlayVpnDriverProxy>() {

            @Mock
            public HTTPReturnMessage sendDeleteMsg(String url, String body, String ctrlUuid) throws ServiceException {

                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);

                return httpReturnMessage;
            }
        };
        service.deleteSNat("2467a068795b41ee9676bc79168da7a6", "extSysID=ctrlid123", "123");
    }
}
