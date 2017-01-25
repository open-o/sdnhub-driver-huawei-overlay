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

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * AC branch context helper class.<br>
 * 
 * @author
 * @version SDNO 0.5 Aug 16, 2016
 */
public class OverlayVpnDriverContextHelper implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        appCtx = arg0;
    }

    /**
     * Get bean object.<br>
     * 
     * @param beanName bean name
     * @return bean object
     * @since SDNHUB 0.5
     */
    public static Object getBean(String beanName) {
        if(null != appCtx) {
            return appCtx.getBean(beanName);
        }
        return null;
    }

}
