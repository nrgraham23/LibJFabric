package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.enums.MRMode;

public class TestMRMode {

	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}
	
	@Test
	public void test() {
		assertEquals(MRMode.FI_MR_MODE_UNSPEC.getVal(), 0);
		assertEquals(MRMode.FI_MR_MODE_BASIC.getVal(), 1);
		assertEquals(MRMode.FI_MR_MODE_SCALABLE.getVal(), 2);
	}

}
