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

package org.openo.sdnhub.overlayvpndriver.translator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openo.sdnhub.overlayvpndriver.controller.model.Ip;
import org.openo.sdnhub.overlayvpndriver.service.model.NQADeviceModel;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNeIpSec;
import org.openo.sdnhub.overlayvpndriver.service.model.SbiNqa;
import org.openo.sdno.framework.container.util.JsonUtil;

public class NqaIpSecTranslate {

    private NqaIpSecTranslate()
    {

    }

    public static SbiNqa buildNqa(SbiNeIpSec sbiNeIpSec, SbiNqa nqa)
    {
        nqa.setTestType("ping");

        Ip ip = JsonUtil.fromJson(sbiNeIpSec.getPeerAddress(), Ip.class);

        if(StringUtils.isNotEmpty(ip.getIpv4()))
        {
            nqa.setDstIp(ip.getIpv4());
        }
        else
        {
            nqa.setDstIp(ip.getIpv6());
        }
        return nqa;
    }

    public static List<NQADeviceModel> convertDeviceMode(List<SbiNqa> nqaList)
    {
        List<NQADeviceModel> nqaDeviceModelList = new ArrayList<>();
        for(SbiNqa nqa : nqaList)
        {
            NQADeviceModel nqaDeviceModel = new NQADeviceModel();

            nqaDeviceModel.setId(nqa.getUuid());
            nqaDeviceModel.setTestType(nqa.getTestType());
            nqaDeviceModel.setDstIp(nqa.getDstIp());
            nqaDeviceModel.setSrcIp(nqa.getSrcIp());
            nqaDeviceModel.setSrcport(nqa.getSrcPortName());

            if(null != nqa.getFrequency())
            {
                nqaDeviceModel.setFrequency(Integer.valueOf(nqa.getFrequency()));
            }

            if(null != nqa.getProbeCount())
            {
                nqaDeviceModel.setProveCount(Integer.valueOf(nqa.getProbeCount()));
            }

            if(null != nqa.getTtl())
            {
                nqaDeviceModel.setTtl(Integer.valueOf(nqa.getTtl()));
            }

            if(null != nqa.getTos())
            {
                nqaDeviceModel.setTos(Integer.valueOf(nqa.getTos()));
            }
            nqaDeviceModelList.add(nqaDeviceModel);
        }

        return nqaDeviceModelList;
    }

    public static List<SbiNqa> convertNqa(List<NQADeviceModel> nqaDeviceModelList, List<SbiNqa> nqaList)
    {
        for(SbiNqa nqa : nqaList)
        {
            for(NQADeviceModel nqaDeviceModel : nqaDeviceModelList)
            {
                if(nqaDeviceModel.getId().equals(nqa.getUuid()))
                {
                    nqa.setTestType(nqaDeviceModel.getTestType());
                    nqa.setDstIp(nqaDeviceModel.getDstIp());
                    nqa.setSrcIp(nqaDeviceModel.getSrcIp());
                    nqa.setSrcPortName(nqaDeviceModel.getSrcport());
                    nqa.setProbeCount(String.valueOf(nqaDeviceModel.getProveCount()));
                    nqa.setFrequency(String.valueOf(nqaDeviceModel.getFrequency()));
                    nqa.setTimeout(String.valueOf(nqaDeviceModel.getTimeout()));
                    nqa.setTtl(String.valueOf(nqaDeviceModel.getTtl()));
                    nqa.setTos(String.valueOf(nqaDeviceModel.getTos()));
                    break;
                }
            }
        }

        return nqaList;
    }
}
