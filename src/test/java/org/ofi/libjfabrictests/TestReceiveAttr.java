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
