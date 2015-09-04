package org.ofi.libjfabric.enums;

public enum Progress {
	UNSPEC(getUNSPEC()),
	AUTO(getAUTO()),
	MANUAL(getMANUAL());
	
	private int val;
	
	Progress(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static Progress getProgress(int val) {
		if(val == Progress.UNSPEC.getVal()) {
			return Progress.UNSPEC;
		}
		if(val == Progress.AUTO.getVal()) {
			return Progress.AUTO;
		}
		if(val == Progress.MANUAL.getVal()) {
			return Progress.MANUAL;
		}
		throw new IllegalArgumentException("Invalid integer value for method getProgress!");
	}
	
	private static native int getUNSPEC();
	private static native int getAUTO();
	private static native int getMANUAL();
}
