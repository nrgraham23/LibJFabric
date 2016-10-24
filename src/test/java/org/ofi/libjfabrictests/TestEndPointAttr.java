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
import org.ofi.libjfabric.attributes.EndPointAttr;
import org.ofi.libjfabric.enums.EPType;
import org.ofi.libjfabric.enums.Protocol;

public class TestEndPointAttr {
	EndPointAttr fullEP, emptyEP;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		fullEP = new EndPointAttr(EPType.FI_EP_DGRAM, Protocol.FI_PROTO_IB_UD, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		emptyEP = new EndPointAttr();
	}

	@Test
	public void testGetEpType() {
		assertEquals(fullEP.getEpType(), EPType.FI_EP_DGRAM);
	}

	@Test
	public void testGetProtocol() {
		assertEquals(fullEP.getProtocol(), Protocol.FI_PROTO_IB_UD);
	}

	@Test
	public void testGetProtoVersion() {
		assertEquals(fullEP.getProtoVersion(), 3);
	}

	@Test
	public void testGetMaxMsgSize() {
		assertEquals(fullEP.getMaxMsgSize(), 4);
	}

	@Test
	public void testGetMsgPrefixSize() {
		assertEquals(fullEP.getMsgPrefixSize(), 5);
	}

	@Test
	public void testGetMaxOrderRawSize() {
		assertEquals(fullEP.getMaxOrderRawSize(), 6);
	}

	@Test
	public void testGetMaxOrderWarSize() {
		assertEquals(fullEP.getMaxOrderWarSize(), 7);
	}

	@Test
	public void testGetMaxOrderWawSize() {
		assertEquals(fullEP.getMaxOrderWawSize(), 8);
	}

	@Test
	public void testGetMemTagFormat() {
		assertEquals(fullEP.getMemTagFormat(), 9);
	}

	@Test
	public void testGetTxCtxCnt() {
		assertEquals(fullEP.getTxCtxCnt(), 10);
	}

	@Test
	public void testGetRxCtxCnt() {
		assertEquals(fullEP.getRxCtxCnt(), 11);
	}

	@Test
	public void testSetEpType() {
		emptyEP.setEpType(EPType.FI_EP_MSG);
		assertEquals(emptyEP.getEpType(), EPType.FI_EP_MSG);
	}

	@Test
	public void testSetProtocol() {
		emptyEP.setProtocol(Protocol.FI_PROTO_IWARP);
		assertEquals(emptyEP.getProtocol(), Protocol.FI_PROTO_IWARP);
	}

	@Test
	public void testSetProtoVersion() {
		emptyEP.setProtoVersion(4);
		assertEquals(emptyEP.getProtoVersion(), 4);
	}

	@Test
	public void testSetMaxMsgSize() {
		emptyEP.setMaxMsgSize(5);
		assertEquals(emptyEP.getMaxMsgSize(), 5);
	}

	@Test
	public void testSetMsgPrefixSize() {
		emptyEP.setMsgPrefixSize(6);
		assertEquals(emptyEP.getMsgPrefixSize(), 6);
	}

	@Test
	public void testSetMaxOrderRawSize() {
		emptyEP.setMaxOrderRawSize(7);
		assertEquals(emptyEP.getMaxOrderRawSize(), 7);
	}

	@Test
	public void testSetMaxOrderWarSize() {
		emptyEP.setMaxOrderWarSize(8);
		assertEquals(emptyEP.getMaxOrderWarSize(), 8);
	}

	@Test
	public void testSetMaxOrderWawSize() {
		emptyEP.setMaxOrderWawSize(9);
		assertEquals(emptyEP.getMaxOrderWawSize(), 9);
	}

	@Test
	public void testSetMemTagFormat() {
		emptyEP.setMemTagFormat(10);
		assertEquals(emptyEP.getMemTagFormat(), 10);
	}

	@Test
	public void testSetTxCtxCnt() {
		emptyEP.setTxCtxCnt(11);
		assertEquals(emptyEP.getTxCtxCnt(), 11);
	}

	@Test
	public void testSetRxCtxCnt() {
		emptyEP.setRxCtxCnt(12);
		assertEquals(emptyEP.getRxCtxCnt(), 12);
	}

}
