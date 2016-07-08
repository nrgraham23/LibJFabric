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
import org.ofi.libjfabric.Fabric;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.Version;
import org.ofi.libjfabric.attributes.SpecifiedFabricAttr;

public class TestSpecifiedFabricAttr {
	private SpecifiedFabricAttr fullSFA, fabricSFA;
	private Version version;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		version = new Version(1, 3);
		fullSFA = new SpecifiedFabricAttr(new Fabric(100025), "testName", "testProvName", version);
		fabricSFA = new SpecifiedFabricAttr(new Fabric(100024));
	}

	@Test
	public void testGetFabric() {
		assertEquals(fullSFA.getFabric().getHandle(), 100025);
		assertEquals(fabricSFA.getFabric().getHandle(), 100024);
	}

}
