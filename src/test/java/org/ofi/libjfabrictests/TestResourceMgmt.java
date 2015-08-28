package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.enums.ResourceMgmt;

public class TestResourceMgmt {

	@Before
	public void setUp() throws Exception {
		LibFabric lib = new LibFabric();
	}

	@Test
	public void test() {
		assertEquals(ResourceMgmt.UNSPEC.getVal(), 0);
		assertEquals(ResourceMgmt.DISABLED.getVal(), 1);
		assertEquals(ResourceMgmt.ENABLED.getVal(), 2);
	}
}
