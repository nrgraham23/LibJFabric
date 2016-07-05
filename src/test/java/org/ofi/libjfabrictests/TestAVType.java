package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.*;
import org.ofi.libjfabric.enums.AVType;

public class TestAVType {
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}
	
	@Test
	public void test() {
		assertEquals(AVType.FI_AV_UNSPEC.getVal(), 0);
		assertEquals(AVType.FI_AV_MAP.getVal(), 1);
		assertEquals(AVType.FI_AV_TABLE.getVal(), 2);
	}

}
