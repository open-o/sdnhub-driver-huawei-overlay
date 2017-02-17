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

package org.openo.sdnhub.overlayvpndriver.service.model;

import org.openo.sdno.overlayvpn.verify.annotation.AInt;
import org.openo.sdno.overlayvpn.verify.annotation.AString;

/**
 * Class of Time Configuration.<br>
 *
 * @author
 * @version SDNHUB 0.5 February 14, 2017
 */
public class TimeConfig {

    /**
     * Unlimited Time or not
     */
    @AString(require = true, scope = "true,false")
    private boolean unlimit;

    @AInt(min = 0, max = 999)
    private int day;

    @AInt(min = 0, max = 23)
    private int hour;

    @AInt(min = 0, max = 59)
    private int minute;

    /**
     * @return Returns the unlimit.
     */
    public boolean getUnlimit() {
        return unlimit;
    }

    /**
     * @param unlimit The unlimit to set.
     */
    public void setUnlimit(boolean unlimit) {
        this.unlimit = unlimit;
    }

    /**
     * @return Returns the day.
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day The day to set.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return Returns the hour.
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour The hour to set.
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return Returns the minute.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * @param minute The minute to set.
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

}
