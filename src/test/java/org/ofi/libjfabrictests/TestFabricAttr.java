package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.Version;
import org.ofi.libjfabric.attributes.FabricAttr;

public class TestFabricAttr {
	private FabricAttr fullFA, emptyFA;
	private Version version;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		this.version = new Version(1, 3);
		this.fullFA = new FabricAttr("testName", "testProvName", version);
		this.emptyFA = new FabricAttr();
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
		assertEquals(fullFA.getProviderVersion(), version);
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
		emptyFA.setProviderVersion(version);
		assertEquals(emptyFA.getProviderVersion(), version);
	}

}
