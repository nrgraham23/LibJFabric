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
		assertEquals(Protocol.UNSPEC.getVal(), 0);
		assertEquals(Protocol.RDMA_CM_IB_RC.getVal(), 1);
		assertEquals(Protocol.IWARP.getVal(), 2);
		assertEquals(Protocol.IB_UD.getVal(), 3);
		assertEquals(Protocol.PSMX.getVal(), 4);
		assertEquals(Protocol.UDP.getVal(), 5);
		assertEquals(Protocol.SOCK_TCP.getVal(), 6);
		assertEquals(Protocol.MXM.getVal(), 7);
		assertEquals(Protocol.IWARP_RDM.getVal(), 8);
		assertEquals(Protocol.IB_RDM.getVal(), 9);
		assertEquals(Protocol.GNI.getVal(), 10);
	}

}
