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

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * HTTP Delete request class wrapped with body parameter.
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version     SDNHUB 0.5  03-Mar-2017
 */
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

    public static final String METHOD_NAME = "DELETE";

    @Override
    public String getMethod()
    {
        return METHOD_NAME;
    }
    public HttpDeleteWithBody(final String uri)
    {
        super();
        setURI(URI.create(uri));
    }

    public HttpDeleteWithBody(final URI uri)
    {
        super();
        setURI(uri);
    }
    public HttpDeleteWithBody()
    {
        super();
    }
}
