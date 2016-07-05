package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.enums.ResourceMgmt;

public class TestResourceMgmt {

	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}

	@Test
	public void test() {
		assertEquals(ResourceMgmt.FI_RM_UNSPEC.getVal(), 0);
		assertEquals(ResourceMgmt.FI_RM_DISABLED.getVal(), 1);
		assertEquals(ResourceMgmt.FI_RM_ENABLED.getVal(), 2);
	}
}
