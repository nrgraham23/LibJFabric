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
import org.ofi.libjfabric.attributes.TransmitAttr;

public class TestTransmitAttr {
	TransmitAttr fullTA, emptyTA;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		fullTA = new TransmitAttr(3, 4, 5, 6, 7, 8, 9, 10, 11);
		emptyTA = new TransmitAttr();
	}

	@Test
	public void testGetCaps() {
		assertEquals(fullTA.getCaps(), 3);
	}

	@Test
	public void testGetMode() {
		assertEquals(fullTA.getMode(), 4);
	}

	@Test
	public void testGetOpFlags() {
		assertEquals(fullTA.getOpFlags(), 5);
	}

	@Test
	public void testGetMsgOrder() {
		assertEquals(fullTA.getMsgOrder(), 6);
	}

	@Test
	public void testGetCompOrder() {
		assertEquals(fullTA.getCompOrder(), 7);
	}

	@Test
	public void testGetInjectSize() {
		assertEquals(fullTA.getInjectSize(), 8);
	}

	@Test
	public void testGetSize() {
		assertEquals(fullTA.getSize(), 9);
	}

	@Test
	public void testGetIovLimit() {
		assertEquals(fullTA.getIovLimit(), 10);
	}

	@Test
	public void testGetRmaIovLimit() {
		assertEquals(fullTA.getRmaIovLimit(), 11);
	}

	@Test
	public void testSetCaps() {
		emptyTA.setCaps(4);
		assertEquals(emptyTA.getCaps(), 4);
	}

	@Test
	public void testSetMode() {
		emptyTA.setMode(5);
		assertEquals(emptyTA.getMode(), 5);
	}

	@Test
	public void testSetOpFlags() {
		emptyTA.setOpFlags(6);
		assertEquals(emptyTA.getOpFlags(), 6);
	}

	@Test
	public void testSetMsgOrder() {
		emptyTA.setMsgOrder(7);
		assertEquals(emptyTA.getMsgOrder(), 7);
	}

	@Test
	public void testSetCompOrder() {
		emptyTA.setCompOrder(8);
		assertEquals(emptyTA.getCompOrder(), 8);
	}

	@Test
	public void testSetInjectSize() {
		emptyTA.setInjectSize(9);
		assertEquals(emptyTA.getInjectSize(), 9);
	}

	@Test
	public void testSetSize() {
		emptyTA.setSize(10);
		assertEquals(emptyTA.getSize(), 10);
	}

	@Test
	public void testSetIovLimit() {
		emptyTA.setIovLimit(11);
		assertEquals(emptyTA.getIovLimit(), 11);
	}

	@Test
	public void testSetRmaIovLimit() {
		emptyTA.setRmaIovLimit(12);
		assertEquals(emptyTA.getRmaIovLimit(), 12);
	}

}
