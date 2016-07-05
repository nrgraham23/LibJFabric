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
