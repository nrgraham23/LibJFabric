package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.attributes.EndPointAttr;
import org.ofi.libjfabric.enums.EPType;

public class TestEndPointAttr {
	EndPointAttr fullEP, emptyEP;
	
	@Before
	public void setUp() throws Exception {
		fullEP = new EndPointAttr(EPType.DGRAM, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		emptyEP = new EndPointAttr();
	}

	@Test
	public void testGetEpType() {
		assertEquals(fullEP.getEpType(), EPType.DGRAM);
	}

	@Test
	public void testGetProtocol() {
		assertEquals(fullEP.getProtocol(), 2);
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
		emptyEP.setEpType(EPType.MSG);
		assertEquals(emptyEP.getEpType(), EPType.MSG);
	}

	@Test
	public void testSetProtocol() {
		emptyEP.setProtocol(3);
		assertEquals(emptyEP.getProtocol(), 3);
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
