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
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.Version;
import org.ofi.libjfabric.attributes.FabricAttr;

public class TestFabricAttr {
	private FabricAttr fullFA, emptyFA;
	private Version version;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		this.version = new Version(1, 3);
		this.fullFA = new FabricAttr("testName", "testProvName", version);
		this.emptyFA = new FabricAttr();
	}

	@Test
	public void testGetName() {
		assertEquals(fullFA.getName(), "testName");
	}

	@Test
	public void testGetProviderName() {
		assertEquals(fullFA.getProviderName(), "testProvName");
	}

	@Test
	public void testGetProviderVersion() {
		assertEquals(fullFA.getProviderVersion(), version);
	}

	@Test
	public void testSetName() {
		emptyFA.setName("test2");
		assertEquals(emptyFA.getName(), "test2");
	}

	@Test
	public void testSetProviderName() {
		emptyFA.setProviderName("prov2");
		assertEquals(emptyFA.getProviderName(), "prov2");
	}

	@Test
	public void testSetProviderVersion() {
		emptyFA.setProviderVersion(version);
		assertEquals(emptyFA.getProviderVersion(), version);
	}

}
