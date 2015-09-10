package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Domain;
import org.ofi.libjfabric.attributes.SpecifiedDomainAttr;
import org.ofi.libjfabric.enums.AVType;
import org.ofi.libjfabric.enums.MRMode;
import org.ofi.libjfabric.enums.Progress;
import org.ofi.libjfabric.enums.ResourceMgmt;
import org.ofi.libjfabric.enums.Threading;

public class TestSpecifiedDomainAttr {
	SpecifiedDomainAttr fullSDA, domainSDA;
	
	@Before
	public void setUp() throws Exception {
		fullSDA = new SpecifiedDomainAttr(new Domain(100024), "testDomain", Threading.SAFE, Progress.AUTO, Progress.AUTO, ResourceMgmt.ENABLED, 
				AVType.MAP, MRMode.BASIC, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51);
		domainSDA = new SpecifiedDomainAttr(new Domain(100023));
	}

	@Test
	public void testGetDomain() {
		assertEquals(fullSDA.getDomain().getHandle(), 100024);
		assertEquals(domainSDA.getDomain().getHandle(), 100023);
	}

}
