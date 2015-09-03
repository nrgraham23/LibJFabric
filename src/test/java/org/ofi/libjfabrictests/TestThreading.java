package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.enums.Threading;

public class TestThreading {

	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}
	
	@Test
	public void test() {
		assertEquals(Threading.UNSPEC.getVal(), 0);
		assertEquals(Threading.SAFE.getVal(), 1);
		assertEquals(Threading.FID.getVal(), 2);
		assertEquals(Threading.DOMAIN.getVal(), 3);
		assertEquals(Threading.COMPLETION.getVal(), 4);
		assertEquals(Threading.ENDPOINT.getVal(), 5);
	}

}
