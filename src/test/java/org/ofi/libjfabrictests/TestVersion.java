package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.Version;

public class TestVersion {
	private Version oneOne, oneTwo, twoOne, twoTwo, secondOneOne;
	
	@Before
	public void setUp() throws Exception {
		oneOne = new Version(1, 1);
		secondOneOne = new Version(1, 1);;
		oneTwo = new Version(1, 2);
		twoOne = new Version(2, 1);
		twoTwo = new Version(2, 2);
	}

	@Test
	public void testEqualsObject() {
		assertEquals(oneOne, secondOneOne);
	}

	@Test
	public void testCompareTo() {
		assert oneOne.compareTo(oneTwo) < 0;
		assert oneOne.compareTo(twoOne) < 0;
		assert oneTwo.compareTo(oneOne) > 0;
		assert oneOne.compareTo(oneOne) == 0;
		assert twoOne.compareTo(oneTwo) > 0;
		assert twoTwo.compareTo(twoOne) > 0;
	}

}
