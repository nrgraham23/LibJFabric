package org.ofi.libjfabrictests;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Fabric;
import org.ofi.libjfabric.Info;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.Version;

public class TestFabric {
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}

	@Test
	public void testGetInfo() {
		Info hints = new Info();
		hints.setMode(~0);
		Version version = new Version(1, 3);
		
		Info resultInfo[] = LibFabric.getInfo(version, null, "", 0, hints);
		
		assert resultInfo != null;
		
		for(int i = 0; i < resultInfo.length; i++) {
			System.out.println("info object: " + i);
			System.out.println("provider: " + resultInfo[i].getFabricAttr().getProviderName());
			System.out.println("	fabric: " + resultInfo[i].getFabricAttr().getName());
			System.out.println("	domain: " + resultInfo[i].getDomainAttr().getName());
			System.out.println("	version: " + resultInfo[i].getFabricAttr().getProviderVersion());
			System.out.println("	type: " + resultInfo[i].getEndPointAttr().getEpType());
			System.out.println("	protocol: " + resultInfo[i].getEndPointAttr().getProtocol());
		}
		
	}

}
