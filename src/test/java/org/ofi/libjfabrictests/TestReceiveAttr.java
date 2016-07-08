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
import org.ofi.libjfabric.attributes.ReceiveAttr;

public class TestReceiveAttr {
	ReceiveAttr fullRA, emptyRA;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		fullRA = new ReceiveAttr(2, 3, 4, 5, 6, 7, 8, 9);
		emptyRA = new ReceiveAttr();
	}

	@Test
	public void testGetCaps() {
		assertEquals(fullRA.getCaps(), 2);
	}

	@Test
	public void testGetMode() {
		assertEquals(fullRA.getMode(), 3);
	}

	@Test
	public void testGetOpFlags() {
		assertEquals(fullRA.getOpFlags(), 4);
	}

	@Test
	public void testGetMsgOrder() {
		assertEquals(fullRA.getMsgOrder(), 5);
	}

	@Test
	public void testGetCompOrder() {
		assertEquals(fullRA.getCompOrder(), 6);
	}

	@Test
	public void testGetTotalBufferedRecv() {
		assertEquals(fullRA.getTotalBufferedRecv(), 7);
	}

	@Test
	public void testGetSize() {
		assertEquals(fullRA.getSize(), 8);
	}

	@Test
	public void testGetIovLimit() {
		assertEquals(fullRA.getIovLimit(), 9);
	}

	@Test
	public void testSetCaps() {
		emptyRA.setCaps(5);
		assertEquals(emptyRA.getCaps(), 5);
	}

	@Test
	public void testSetMode() {
		emptyRA.setMode(6);
		assertEquals(emptyRA.getMode(), 6);
	}

	@Test
	public void testSetOpFlags() {
		emptyRA.setOpFlags(9);
		assertEquals(emptyRA.getOpFlags(), 9);
	}

	@Test
	public void testSetMsgOrder() {
		emptyRA.setMsgOrder(11);
		assertEquals(emptyRA.getMsgOrder(), 11);
	}

	@Test
	public void testSetCompOrder() {
		emptyRA.setCompOrder(12);
		assertEquals(emptyRA.getCompOrder(), 12);
	}

	@Test
	public void testSetTotalBufferedRecv() {
		emptyRA.setTotalBufferedRecv(15);
		assertEquals(emptyRA.getTotalBufferedRecv(), 15);
	}

	@Test
	public void testSetSize() {
		emptyRA.setSize(50);
		assertEquals(emptyRA.getSize(), 50);
	}

	@Test
	public void testSetIovLimit() {
		emptyRA.setIovLimit(30);
		assertEquals(emptyRA.getIovLimit(), 30);
	}

}
