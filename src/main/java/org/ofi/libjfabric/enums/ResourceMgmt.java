package org.ofi.libjfabric.enums;

public enum ResourceMgmt {
	UNSPEC(getUNSPEC()),
	DISABLED(getDISABLED()),
	ENABLED(getENABLED());
	
	private int val;
	
	ResourceMgmt(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	private static native int getUNSPEC();
	private static native int getDISABLED();
	private static native int getENABLED();
}
