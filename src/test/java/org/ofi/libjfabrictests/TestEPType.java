package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.enums.EPType;

public class TestEPType {

	@Before
	public void setUp() throws Exception {
		LibFabric lib = new LibFabric();
	}
	
	@Test
	public void test() {
		assertEquals(EPType.UNSPEC.getVal(), 0);
		assertEquals(EPType.MSG.getVal(), 1);
		assertEquals(EPType.DGRAM.getVal(), 2);
		assertEquals(EPType.RDM.getVal(), 3);
	}

}
