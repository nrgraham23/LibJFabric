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
	
	//private because it should only be used from the JNI code
	private static ResourceMgmt getResourceMgmt(int val) {
		if(val == ResourceMgmt.UNSPEC.getVal()) {
			return ResourceMgmt.UNSPEC;
		}
		if(val == ResourceMgmt.DISABLED.getVal()) {
			return ResourceMgmt.DISABLED;
		}
		if(val == ResourceMgmt.ENABLED.getVal()) {
			return ResourceMgmt.ENABLED;
		}
		throw new IllegalArgumentException("Invalid integer value for method getResourceMgmt!");
	}
	
	private static native int getUNSPEC();
	private static native int getDISABLED();
	private static native int getENABLED();
}
