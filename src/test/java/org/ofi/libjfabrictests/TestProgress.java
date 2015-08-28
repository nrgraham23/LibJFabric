package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.enums.Progress;

public class TestProgress {

	@Before
	public void setUp() throws Exception {
		LibFabric lib = new LibFabric();
	}

	@Test
	public void test() {
		assertEquals(Progress.UNSPEC.getVal(), 0);
		assertEquals(Progress.AUTO.getVal(), 1);
		assertEquals(Progress.MANUAL.getVal(), 2);
	}

}
