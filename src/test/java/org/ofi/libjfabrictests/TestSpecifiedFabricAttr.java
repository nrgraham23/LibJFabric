package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Fabric;
import org.ofi.libjfabric.attributes.SpecifiedFabricAttr;

public class TestSpecifiedFabricAttr {
	SpecifiedFabricAttr fullSFA, fabricSFA;
	
	@Before
	public void setUp() throws Exception {
		fullSFA = new SpecifiedFabricAttr(new Fabric(100025), "testName", "testProvName", 5);
		fabricSFA = new SpecifiedFabricAttr(new Fabric(100024));
	}

	@Test
	public void testGetFabric() {
		assertEquals(fullSFA.getFabric().getHandle(), 100025);
		assertEquals(fabricSFA.getFabric().getHandle(), 100024);
	}

}
