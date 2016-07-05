package org.ofi.libjfabric.enums;

public enum AVType {
	FI_AV_UNSPEC(getUNSPEC()),
	FI_AV_MAP(getMAP()),
	FI_AV_TABLE(getTABLE());
	
	private int val;
	
	AVType(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static AVType getAVType(int val) {
		if(val == AVType.FI_AV_UNSPEC.getVal()) {
			return AVType.FI_AV_UNSPEC;
		}
		if(val == AVType.FI_AV_MAP.getVal()) {
			return AVType.FI_AV_MAP;
		}
		if(val == AVType.FI_AV_TABLE.getVal()) {
			return AVType.FI_AV_TABLE;
		}
		throw new IllegalArgumentException("Invalid integer value for method getAVType!");
	}
	
	private static native int getUNSPEC();
	private static native int getMAP();
	private static native int getTABLE();
}
