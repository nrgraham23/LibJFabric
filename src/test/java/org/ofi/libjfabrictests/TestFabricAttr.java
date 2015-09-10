package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.attributes.FabricAttr;

public class TestFabricAttr {
	private FabricAttr fullFA, emptyFA;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		fullFA = new FabricAttr("testName", "testProvName", 5);
		emptyFA = new FabricAttr();
	}

	@Test
	public void testGetName() {
		assertEquals(fullFA.getName(), "testName");
	}

	@Test
	public void testGetProviderName() {
		assertEquals(fullFA.getProviderName(), "testProvName");
	}

	@Test
	public void testGetProviderVersion() {
		assertEquals(fullFA.getProviderVersion(), 5);
	}

	@Test
	public void testSetName() {
		emptyFA.setName("test2");
		assertEquals(emptyFA.getName(), "test2");
	}

	@Test
	public void testSetProviderName() {
		emptyFA.setProviderName("prov2");
		assertEquals(emptyFA.getProviderName(), "prov2");
	}

	@Test
	public void testSetProviderVersion() {
		emptyFA.setProviderVersion(6);
		assertEquals(emptyFA.getProviderVersion(), 6);
	}

}
