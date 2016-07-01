package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Fabric;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.Version;
import org.ofi.libjfabric.attributes.SpecifiedFabricAttr;

public class TestSpecifiedFabricAttr {
	private SpecifiedFabricAttr fullSFA, fabricSFA;
	private Version version;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		version = new Version(1, 3);
		fullSFA = new SpecifiedFabricAttr(new Fabric(100025), "testName", "testProvName", version);
		fabricSFA = new SpecifiedFabricAttr(new Fabric(100024));
	}

	@Test
	public void testGetFabric() {
		assertEquals(fullSFA.getFabric().getHandle(), 100025);
		assertEquals(fabricSFA.getFabric().getHandle(), 100024);
	}

}
