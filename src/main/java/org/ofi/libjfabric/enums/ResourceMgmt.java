package org.ofi.libjfabric.enums;

public enum ResourceMgmt {
	FI_RM_UNSPEC(getUNSPEC()),
	FI_RM_DISABLED(getDISABLED()),
	FI_RM_ENABLED(getENABLED());
	
	private int val;
	
	ResourceMgmt(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static ResourceMgmt getResourceMgmt(int val) {
		if(val == ResourceMgmt.FI_RM_UNSPEC.getVal()) {
			return ResourceMgmt.FI_RM_UNSPEC;
		}
		if(val == ResourceMgmt.FI_RM_DISABLED.getVal()) {
			return ResourceMgmt.FI_RM_DISABLED;
		}
		if(val == ResourceMgmt.FI_RM_ENABLED.getVal()) {
			return ResourceMgmt.FI_RM_ENABLED;
		}
		throw new IllegalArgumentException("Invalid integer value for method getResourceMgmt!");
	}
	
	private static native int getUNSPEC();
	private static native int getDISABLED();
	private static native int getENABLED();
}
