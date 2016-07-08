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
import org.ofi.libjfabric.enums.Protocol;

public class TestProtocol {

	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}
	
	@Test
	public void test() {
		assertEquals(Protocol.FI_PROTO_UNSPEC.getVal(), 0);
		assertEquals(Protocol.FI_PROTO_RDMA_CM_IB_RC.getVal(), 1);
		assertEquals(Protocol.FI_PROTO_IWARP.getVal(), 2);
		assertEquals(Protocol.FI_PROTO_IB_UD.getVal(), 3);
		assertEquals(Protocol.FI_PROTO_PSMX.getVal(), 4);
		assertEquals(Protocol.FI_PROTO_UDP.getVal(), 5);
		assertEquals(Protocol.FI_PROTO_SOCK_TCP.getVal(), 6);
		assertEquals(Protocol.FI_PROTO_MXM.getVal(), 7);
		assertEquals(Protocol.FI_PROTO_IWARP_RDM.getVal(), 8);
		assertEquals(Protocol.FI_PROTO_IB_RDM.getVal(), 9);
		assertEquals(Protocol.FI_PROTO_GNI.getVal(), 10);
		assertEquals(Protocol.FI_PROTO_RXM.getVal(), 11);
	}

}
