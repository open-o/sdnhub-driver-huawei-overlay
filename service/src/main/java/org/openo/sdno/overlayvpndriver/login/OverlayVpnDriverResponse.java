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

package org.openo.sdno.overlayvpndriver.login;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdno.overlayvpn.result.FailData;

/**
 * The class for AC response. <br>
 * <p>
 * The response format:
 * {
 * "errcode" : "0",
 * "errmsg" : "",
 * "pageIndex" : 1, -- existed when batch response
 * "pageSize" : 20, -- existed when batch response
 * "totalRecords" : 27, -- existed when batch response
 * "data" : [{}]
 * }
 * </p>
 * 
 * @param <T>
 * @author
 * @version SDNO 0.5 Jul 18, 2016
 */
public class OverlayVpnDriverResponse<T> {

    @JsonProperty
    private String errcode;

    @JsonProperty
    private String errmsg;

    @JsonProperty
    private int pageIndex;

    @JsonProperty
    private int pageSize;

    @JsonProperty
    private int totalRecords;

    @JsonProperty
    private T data;

    private T success = null;

    private List<FailData<T>> fail = null;

    public static final String SUCCESS = "0";

    public boolean isSucess() {
        return SUCCESS.equalsIgnoreCase(errcode);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public T getSuccess() {
        return success;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getErrcode() {
        return errcode;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setSuccess(T success) {
        this.success = success;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public List<FailData<T>> getFail() {
        return fail;
    }

    public void setFail(List<FailData<T>> fail) {
        this.fail = fail;
    }
}
