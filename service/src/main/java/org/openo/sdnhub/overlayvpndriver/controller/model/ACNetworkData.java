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

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * <br/>
 *
 * @author
 * @version SDNHUB 0.5 26-Feb-2017
 */
public class ACNetworkData
{
    @JsonProperty
    private String mode;

    @JsonProperty
    private int pageIndex;

    @JsonProperty
    private int pageSize;

    @JsonProperty
    private int totalRecords;

    @JsonProperty
    private ACNetworkData data;

    @JsonProperty
    private List<ACNetwork> networkConfigList;

    public int getPageIndex()
    {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalRecords()
    {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords)
    {
        this.totalRecords = totalRecords;
    }

    public ACNetworkData getData()
    {
        return data;
    }

    public void setData(ACNetworkData data)
    {
        this.data = data;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public List<ACNetwork> getNetworkConfigList()
    {
        return networkConfigList;
    }

    public void setNetworkConfigList(List<ACNetwork> networkConfigList)
    {
        this.networkConfigList = networkConfigList;
    }
}
