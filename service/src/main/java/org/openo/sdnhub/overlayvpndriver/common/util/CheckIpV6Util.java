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

import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNHUB 0.5 02-Feb-2017
 */
public class CheckIpV6Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckIpV6Util.class);

    private CheckIpV6Util() {

    }

    /**
     * <br/>
     * 
     * @param ip
     * @return
     * @since SDNHUB 0.5
     */
    public static boolean isValidIpV6(String ip) {
        if(StringUtils.isEmpty(ip)) {
            return false;
        }

        return checkIpIllegalChar(ip) && checkIpField(ip);
    }

    private static boolean checkIpIllegalChar(String ip) {
        if((ip.indexOf("//") != -1) || (ip.indexOf("..") != -1) || (ip.indexOf(":::") != -1)
                || (ip.replaceFirst("::", "").indexOf("::") != -1)
                || ((ip.replaceFirst("/", "").indexOf("/") != -1) && ip.endsWith("::"))) {
            LOGGER.error("checkIpIllegalChar ip is invalid.");
            return false;
        } else {
            String regex = "[0-9a-f: ]*";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(ip.toLowerCase(Locale.getDefault()));
            return matcher.matches();
        }
    }

    private static boolean checkIpField(String ip) {
        StringTokenizer stringtokenizer = new StringTokenizer(ip, ":");
        if(((ip.indexOf("::") == -1) && (stringtokenizer.countTokens() != 8))
                || ((ip.indexOf("::") != -1) && (stringtokenizer.countTokens() >= 7))) {
            LOGGER.error("checkIpField ip is invalid.");
            return false;
        }
        while(stringtokenizer.hasMoreTokens()) {
            String s = stringtokenizer.nextToken();
            if((s.trim().length() != s.trim().replaceAll(" ", "").length()) || (s.trim().length() > 4)) {
                return false;
            }

            if((s.length() == 0) || (s.trim().length() == 0)) {
                return false;
            }
        }

        return true;
    }
}
