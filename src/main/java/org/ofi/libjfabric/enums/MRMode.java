package org.ofi.libjfabric.enums;

public enum MRMode {
	FI_MR_MODE_UNSPEC(getUNSPEC()),
	FI_MR_MODE_BASIC(getBASIC()),
	FI_MR_MODE_SCALABLE(getSCALABLE());
	
	private int val;
	
	MRMode(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static MRMode getMRMode(int val) {
		if(val == MRMode.FI_MR_MODE_UNSPEC.getVal()) {
			return MRMode.FI_MR_MODE_UNSPEC;
		}
		if(val == MRMode.FI_MR_MODE_BASIC.getVal()) {
			return MRMode.FI_MR_MODE_BASIC;
		}
		if(val == MRMode.FI_MR_MODE_SCALABLE.getVal()) {
			return MRMode.FI_MR_MODE_SCALABLE;
		}
		throw new IllegalArgumentException("Invalid integer value for method getMRMode!");
	}
	
	private static native int getUNSPEC();
	private static native int getBASIC();
	private static native int getSCALABLE();
}
