/*
 * Copyright (c) 2015 Los Alamos Nat. Security, LLC. All rights reserved.
 *
 * This software is available to you under a choice of one of two
 * licenses.  You may choose to be licensed under the terms of the GNU
 * General Public License (GPL) Version 2, available from the file
 * COPYING in the main directory of this source tree, or the
 * BSD license below:
 *
 *     Redistribution and use in source and binary forms, with or
 *     without modification, are permitted provided that the following
 *     conditions are met:
 *
 *      - Redistributions of source code must retain the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer.
 *
 *      - Redistributions in binary form must reproduce the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.ofi.libjfabrictests;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Info;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.Version;

public class TestLibFabric {
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}

	@Test
	public void testGetInfo() {
		Version version = new Version(1, 3);
		
		Info resultInfo[] = LibFabric.getInfo(version, null, null, 0);
		
		assert resultInfo != null;
		
		for(int i = 0; i < resultInfo.length; i++) {
			System.out.println("provider: " + resultInfo[i].getFabricAttr().getProviderName());
			System.out.println("	fabric: " + resultInfo[i].getFabricAttr().getName());
			System.out.println("	domain: " + resultInfo[i].getDomainAttr().getName());
			System.out.println("	version: " + resultInfo[i].getFabricAttr().getProviderVersion());
			System.out.println("	type: " + resultInfo[i].getEndPointAttr().getEpType());
			System.out.println("	protocol: " + resultInfo[i].getEndPointAttr().getProtocol());
		}
		
	}

}
