package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.*;
import org.ofi.libjfabric.enums.AVType;

public class TestAVType {
	
	@Before
	public void setUp() throws Exception {
		LibFabric lib = new LibFabric();
	}
	
	@Test
	public void test() {
		assertEquals(AVType.UNSPEC.getVal(), 0);
		assertEquals(AVType.MAP.getVal(), 1);
		assertEquals(AVType.TABLE.getVal(), 2);
	}

}
