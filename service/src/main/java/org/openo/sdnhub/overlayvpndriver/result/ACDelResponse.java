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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openo.sdnhub.overlayvpndriver.common.consts.DriverErrorCode;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class ACDelResponse {

    private String errcode;

    private String errmsg;

    private List<ConfigCommonResult> success;

    private List<DataDto> fail;

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

    public List<ConfigCommonResult> getSuccess() {
        return success;
    }

    public void setSuccess(List<ConfigCommonResult> success) {
        this.success = success;
    }

    public List<DataDto> getFail() {
        return fail;
    }

    public void setFail(List<DataDto> fail) {
        this.fail = fail;
    }

    public boolean isSucess() {
        return DriverErrorCode.SUCCESS.equalsIgnoreCase(errcode);
    }

    public String getAllErrmsg() {
        StringBuilder errInfo = new StringBuilder();

        if(StringUtils.isNotEmpty(errmsg)) {
            errInfo.append(errmsg).append("; ");
        }

        if(CollectionUtils.isNotEmpty(fail)) {
            for(DataDto dataDto : fail) {
                if(StringUtils.isNotEmpty(dataDto.getErrmsg())) {
                    errInfo.append(dataDto.getErrmsg()).append("; ");
                }
            }
        }

        return errInfo.toString();
    }
}
