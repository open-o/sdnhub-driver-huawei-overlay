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

package org.openo.sdnhub.overlayvpndriver.result;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;
import org.openo.sdno.overlayvpn.result.FailData;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @param <T>
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class OverlayVpnDriverResponse<T> {

    @JsonProperty
    private String errcode;

    @JsonProperty
    private String errmsg;

    @JsonProperty
    private String pageIndex;

    @JsonProperty
    private String pageSize;

    @JsonProperty
    private int totalRecords;

    @JsonProperty
    private T data;

    private List<T> success = null;

    private List<FailData<T>> fail = null;

    public boolean isSucess() {
        return DriverErrorCode.SUCCESS.equalsIgnoreCase(errcode);
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getSuccess() {
        return success;
    }

    public void setSuccess(List<T> success) {
        this.success = success;
    }

    public List<FailData<T>> getFail() {
        return fail;
    }

    public void setFail(List<FailData<T>> fail) {
        this.fail = fail;
    }
}
