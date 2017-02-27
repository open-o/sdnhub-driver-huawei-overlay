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

package org.openo.sdnhub.overlayvpndriver.controller.model;

import java.util.List;

/**
 * Model class for vni filter in adapter layer. <br>
 *
 * @author
 * @version SDNHUB Driver 0.5 Jan 20, 2017
 */
public class VniFilter
{
    private String name;

    private List<Integer> srcPortList;

    private List<Integer> desPortList;

    private boolean action;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Integer> getSrcPortList()
    {
        return srcPortList;
    }

    public void setSrcPortList(List<Integer> srcPortList)
    {
        this.srcPortList = srcPortList;
    }

    public List<Integer> getDesPortList()
    {
        return desPortList;
    }

    public void setDesPortList(List<Integer> desPortList)
    {
        this.desPortList = desPortList;
    }

    public boolean isAction()
    {
        return action;
    }

    public void setAction(boolean action)
    {
        this.action = action;
    }
}
