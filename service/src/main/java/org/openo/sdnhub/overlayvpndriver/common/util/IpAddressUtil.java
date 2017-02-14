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

package org.openo.sdnhub.overlayvpndriver.common.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class IpAddress related operations.<br/>
 *
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class IpAddressUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpAddressUtil.class);

    /**
     * Calculate subnet address.<br/>
     *
     * @param ip address
     * @param mask network mask
     * @return Subnet address
     * @since SDNHUB 0.5
     */
    public static String calcSubnet(String ip, int mask) {
        if(!isValidIpAddr(ip)) {
            return null;
        }
        if(mask <= 0) {
            return "0.0.0.0";
        } else if(mask >= 32) {
            return ip;
        }
        long ipv = ipToLong(ip);
        long subnetv = (ipv >> (32 - mask)) << (32 - mask);
        return longToIp(subnetv);
    }

    /**
     * Convert IP address to long equivalent.<br/>
     *
     * @param ipAddr IpAddress
     * @return Long equivalent of IpAddress
     * @since SDNHUB 0.5
     */
    public static long ipToLong(String ipAddr)
    {
        if(!isValidIpAddr(ipAddr)) {
            return -1;
        }

        ipAddr = ipAddr.trim();
        String[] ipArray = StringUtils.split(ipAddr, '.');
        long[] ip = new long[4];

        ip[0] = Long.parseLong(ipArray[0]);
        ip[1] = Long.parseLong(ipArray[1]);
        ip[2] = Long.parseLong(ipArray[2]);
        ip[3] = Long.parseLong(ipArray[3]);

        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * Convert Long to IP address<br/>
     *
     * @param ipAddr long IP Address
     * @return String notation for IP address
     * @since SDNHUB 0.5
     */
    public static String longToIp(long ipAddr) {
        if(ipAddr < 0 || ipAddr > 4294967295L) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        sb.append(String.valueOf(ipAddr >>> 24));
        sb.append('.');

        sb.append(String.valueOf((ipAddr & 0x00FFFFFF) >>> 16));
        sb.append('.');

        sb.append(String.valueOf((ipAddr & 0x0000FFFF) >>> 8));
        sb.append('.');

        sb.append(String.valueOf(ipAddr & 0x000000FF));

        return sb.toString();
    }

    /**
     * Check valid IP address<br/>
     *
     * @param ipAddr IP Address
     * @return boolean result for IP validation
     * @since SDNHUB 0.5
     */
    public static boolean isValidIpAddr(String ipAddr) {
        String regex =
                "^(25[0-5]|2[0-4]\\d?|1\\d{2}|[1-9]\\d?|0)\\.(25[0-5]|2[0-4]\\d?|1\\d{2}|[1-9]\\d?|0)\\.(25[0-5]|2[0-4]\\d?|1\\d{2}|[1-9]\\d?|0)\\.(25[0-5]|2[0-4]\\d?|1\\d{2}|[1-9]\\d?|0)$";

        if(StringUtils.isEmpty(ipAddr)) {
            LOGGER.error("invalid ip addresss: " + ipAddr);
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Normalizer.normalize(ipAddr, Form.NFKC));

        boolean match = matcher.matches();
        if(!match) {
            LOGGER.error("invalid ip addresss:" + ipAddr);
        }

        return match;
    }

}
