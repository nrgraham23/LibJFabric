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
		fullInfo.free();
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
