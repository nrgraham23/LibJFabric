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
	
	//private because it should only be used from the JNI code
	private static MRMode getMRMode(int val) {
		if(val == MRMode.UNSPEC.getVal()) {
			return MRMode.UNSPEC;
		}
		if(val == MRMode.BASIC.getVal()) {
			return MRMode.BASIC;
		}
		if(val == MRMode.SCALABLE.getVal()) {
			return MRMode.SCALABLE;
		}
		throw new IllegalArgumentException("Invalid integer value for method getMRMode!");
	}
	
	private static native int getUNSPEC();
	private static native int getBASIC();
	private static native int getSCALABLE();
}
