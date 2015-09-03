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
		assertEquals(MRMode.UNSPEC.getVal(), 0);
		assertEquals(MRMode.BASIC.getVal(), 1);
		assertEquals(MRMode.SCALABLE.getVal(), 2);
	}

}
