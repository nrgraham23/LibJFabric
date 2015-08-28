package org.ofi.libjfabric.enums;

public enum MRMode {
	UNSPEC(getUNSPEC()),
	BASIC(getBASIC()),
	SCALABLE(getSCALABLE());
	
	private int val;
	
	MRMode(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	private static native int getUNSPEC();
	private static native int getBASIC();
	private static native int getSCALABLE();
}
