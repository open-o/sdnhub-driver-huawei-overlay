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

package org.openo.sdnhub.overlayvpndriver.util.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.overlayvpndriver.util.config.WanInterface;

import mockit.Mock;
import mockit.MockUp;

public class WanInterfaceTest {

    @Test
    public void testGetConfig() throws ServiceException {
        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) throws IOException {

                return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();

            }
        };

        new MockUp<Paths>() {

            @Mock
            public Path get(String first, String... more) {
                return null;
            }
        };
        String result = WanInterface.getConfig("123");
        // assertEquals("yes", result);
    }

    @Test
    public void testGetConfigException() throws ServiceException {
        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) throws IOException {

                return "[{}]".getBytes();

            }
        };

        new MockUp<Paths>() {

            @Mock
            public Path get(String first, String... more) {
                return null;
            }
        };
        String result = WanInterface.getConfig("123");
        // assertEquals("yes", result);
    }

    @Test
    public void testGetConfigBranch() throws ServiceException {
        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) throws IOException {

                return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();

            }
        };

        new MockUp<Paths>() {

            @Mock
            public Path get(String first, String... more) {
                return null;
            }
        };
        String result = WanInterface.getConfig("1233");
        // assertEquals("yes", result);
    }

}
