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

package org.openo.sdnhub.overlayvpndriver.common.consts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;

public class HttpCodeTest {

    @Test
    public void isSuccess() throws Exception {
        boolean bvalue = HttpCode.isSucess(200);
        assertEquals(true, bvalue);
    }

    @Test
    public void isFail() throws ServiceException {
        boolean bvalue = HttpCode.isSucess(100);
        assertEquals(false, bvalue);
    }

}
