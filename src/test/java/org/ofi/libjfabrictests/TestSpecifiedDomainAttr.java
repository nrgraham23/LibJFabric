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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Domain;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.attributes.SpecifiedDomainAttr;
import org.ofi.libjfabric.enums.AVType;
import org.ofi.libjfabric.enums.MRMode;
import org.ofi.libjfabric.enums.Progress;
import org.ofi.libjfabric.enums.ResourceMgmt;
import org.ofi.libjfabric.enums.Threading;

public class TestSpecifiedDomainAttr {
	SpecifiedDomainAttr fullSDA, domainSDA;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		fullSDA = new SpecifiedDomainAttr(new Domain(100024), "testDomain", Threading.FI_THREAD_SAFE, Progress.FI_PROGRESS_AUTO, Progress.FI_PROGRESS_AUTO, ResourceMgmt.FI_RM_ENABLED, 
				AVType.FI_AV_MAP, MRMode.FI_MR_MODE_BASIC, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51);
		domainSDA = new SpecifiedDomainAttr(new Domain(100023));
	}

	@Test
	public void testGetDomain() {
		assertEquals(fullSDA.getDomain().getHandle(), 100024);
		assertEquals(domainSDA.getDomain().getHandle(), 100023);
	}

}
