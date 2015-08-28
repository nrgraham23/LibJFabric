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
	
	private static native int getUNSPEC();
	private static native int getMAP();
	private static native int getTABLE();
}
