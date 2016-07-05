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
		assertEquals(Threading.FI_THREAD_UNSPEC.getVal(), 0);
		assertEquals(Threading.FI_THREAD_SAFE.getVal(), 1);
		assertEquals(Threading.FI_THREAD_FID.getVal(), 2);
		assertEquals(Threading.FI_THREAD_DOMAIN.getVal(), 3);
		assertEquals(Threading.FI_THREAD_COMPLETION.getVal(), 4);
		assertEquals(Threading.FI_THREAD_ENDPOINT.getVal(), 5);
	}

}
