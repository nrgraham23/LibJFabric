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
import org.ofi.libjfabric.Info;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.attributes.*;

public class TestInfo {
	Info fullInfo, emptyInfo;
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		fullInfo = new Info(2, 3, 4, 5, 6, new TransmitAttr(100), new ReceiveAttr(101), new EndPointAttr(102), 
				new DomainAttr(103), new FabricAttr(104));
		emptyInfo = new Info();
	}

	@Test
	public void testFree() {
		//fullInfo.free(); TODO: failing because i do not malloc all the sub parts of fi_info.  they all get freed in fabric.c.  Do I even want this constructor?
		emptyInfo.free();
	}
	@Test
	public void testGetCaps() {
		assertEquals(fullInfo.getCaps(), 2);
	}

	@Test
	public void testGetMode() {
		assertEquals(fullInfo.getMode(), 3);
	}

	@Test
	public void testGetAddrFormat() {
		assertEquals(fullInfo.getAddrFormat(), 4);
	}

	@Test
	public void testGetSrcAddrLen() {
		assertEquals(fullInfo.getSrcAddrLen(), 5);
	}

	@Test
	public void testGetDestAddrLen() {
		assertEquals(fullInfo.getDestAddrLen(), 6);
	}

	@Test
	public void testGetTransmitAttr() {
		assertEquals(fullInfo.getTransmitAttr().getHandle(), 100);
	}

	@Test
	public void testGetReceiveAttr() {
		assertEquals(fullInfo.getReceiveAttr().getHandle(), 101);
	}

	@Test
	public void testGetEndPointAttr() {
		assertEquals(fullInfo.getEndPointAttr().getHandle(), 102);
	}

	@Test
	public void testGetDomainAttr() {
		assertEquals(fullInfo.getDomainAttr().getHandle(), 103);
	}

	@Test
	public void testGetFabricAttr() {
		assertEquals(fullInfo.getFabricAttr().getHandle(), 104);
	}

	@Test
	public void testSetCaps() {
		emptyInfo.setCaps(4);
		assertEquals(emptyInfo.getCaps(), 4);
	}

	@Test
	public void testSetMode() {
		emptyInfo.setMode(5);
		assertEquals(emptyInfo.getMode(), 5);
	}

	@Test
	public void testSetAddrFormat() {
		emptyInfo.setAddrFormat(6);
		assertEquals(emptyInfo.getAddrFormat(), 6);
	}

	@Test
	public void testSetSrcAddrLen() {
		emptyInfo.setSrcAddrLen(7);
		assertEquals(emptyInfo.getSrcAddrLen(), 7);
	}

	@Test
	public void testSetDestAddrLen() {
		emptyInfo.setDestAddrLen(8);
		assertEquals(emptyInfo.getDestAddrLen(), 8);
	}

	@Test
	public void testSetTransmitAttr() {
		emptyInfo.setTransmitAttr(new TransmitAttr(1000));
		assertEquals(emptyInfo.getTransmitAttr().getHandle(), 1000);
	}

	@Test
	public void testSetReceiveAttr() {
		emptyInfo.setReceiveAttr(new ReceiveAttr(1001));
		assertEquals(emptyInfo.getReceiveAttr().getHandle(), 1001);
	}

	@Test
	public void testSetEndPointAttr() {
		emptyInfo.setEndPointAttr(new EndPointAttr(1002));
		assertEquals(emptyInfo.getEndPointAttr().getHandle(), 1002);
	}

	@Test
	public void testSetDomainAttr() {
		emptyInfo.setDomainAttr(new DomainAttr(1003));
		assertEquals(emptyInfo.getDomainAttr().getHandle(), 1003);
	}

	@Test
	public void testSetFabricAttr() {
		emptyInfo.setFabricAttr(new FabricAttr(1004));
		assertEquals(emptyInfo.getFabricAttr().getHandle(), 1004);
	}

}
