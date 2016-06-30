package org.ofi.libjfabrictests;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Fabric;
import org.ofi.libjfabric.Info;
import org.ofi.libjfabric.LibFabric;

public class TestFabric {
	private final static double VERSION = 1.1;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}

	@Test
	public void testGetInfo() {
		Info hints = new Info();
		hints.setMode(~0);
		
		Info resultInfo[] = Fabric.getInfo(VERSION, null, "", 0, hints);
		
		assert resultInfo != null;
	}

}
