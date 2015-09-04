package org.ofi.libjfabric.enums;

public enum AVType {
	UNSPEC(getUNSPEC()),
	MAP(getMAP()),
	TABLE(getTABLE());
	
	private int val;
	
	AVType(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static AVType getAVType(int val) {
		if(val == AVType.UNSPEC.getVal()) {
			return AVType.UNSPEC;
		}
		if(val == AVType.MAP.getVal()) {
			return AVType.MAP;
		}
		if(val == AVType.TABLE.getVal()) {
			return AVType.TABLE;
		}
		throw new IllegalArgumentException("Invalid integer value for method getAVType!");
	}
	
	private static native int getUNSPEC();
	private static native int getMAP();
	private static native int getTABLE();
}
