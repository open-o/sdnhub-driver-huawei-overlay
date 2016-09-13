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

package org.openo.sdno.acbranchservice.login;

import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

public class MockedStatusLine implements StatusLine {

    int status = 200;

    @Override
    public ProtocolVersion getProtocolVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getStatusCode() {
        // TODO Auto-generated method stub
        return status;
    }

    @Override
    public String getReasonPhrase() {
        // TODO Auto-generated method stub
        return null;
    }

}
