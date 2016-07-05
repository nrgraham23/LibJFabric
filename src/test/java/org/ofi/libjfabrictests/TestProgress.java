package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.enums.Progress;

public class TestProgress {

	@Before
	public void setUp() throws Exception {
		LibFabric.load();
	}

	@Test
	public void test() {
		assertEquals(Progress.FI_PROGRESS_UNSPEC.getVal(), 0);
		assertEquals(Progress.FI_PROGRESS_AUTO.getVal(), 1);
		assertEquals(Progress.FI_PROGRESS_MANUAL.getVal(), 2);
	}

}
