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

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckIpV6UtilTest {

	@Test
	public void isValidIpV6Test() {
		
		boolean r = CheckIpV6Util.isValidIpV6("ip");
		assertEquals(false, r);
	}
	@Test
	public void isValidIpV6Test1() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("");
		assertEquals(false, r);
	}
	@Test
	public void isValidIpV6Test2() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10.10.10.12/123");
		assertEquals(false, r);
	}
	@Test
	public void isValidIpV6Test3() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10.10.10.12");
		assertEquals(false, r);
	}
	@Test
	public void isValidIpV6Test4() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10:::10//10.12 && 10..20.20/12");
		assertEquals(false, r);
	}
	
	@Test
	public void isValidIpV6Test5() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10::10:20//20");
		assertEquals(false, r);
	}
	@Test
	public void isValidIpV6Test6() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10:10:20");
		assertEquals(false, r);
	}
	
	@Test
		public void isValidIpV6Test7() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10//20..30:::30::20");
		assertEquals(false, r);
	}
	
	@Test
	public void isValidIpV6Test8() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10:::20");
		assertEquals(false, r);
	}
	@Test
	public void isValidIpV6Test9() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10/20.30  ::");
		assertEquals(false, r);
	}
	@Test
	public void isValidIpV6Test10() {
		
	    boolean r = CheckIpV6Util.isValidIpV6("10/20/:30");
		assertEquals(false,r);
	}
	
	@Test
    public void isValidIpV6TestValid() {
        
        boolean r = CheckIpV6Util.isValidIpV6("2001:2001:2001:2001:2001:2001:2001:2001");
        assertEquals(true,r);
    }
}
