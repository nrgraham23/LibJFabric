package org.ofi.libjfabric.enums;

public enum Progress {
	FI_PROGRESS_UNSPEC(getUNSPEC()),
	FI_PROGRESS_AUTO(getAUTO()),
	FI_PROGRESS_MANUAL(getMANUAL());
	
	private int val;
	
	Progress(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static Progress getProgress(int val) {
		if(val == Progress.FI_PROGRESS_UNSPEC.getVal()) {
			return Progress.FI_PROGRESS_UNSPEC;
		}
		if(val == Progress.FI_PROGRESS_AUTO.getVal()) {
			return Progress.FI_PROGRESS_AUTO;
		}
		if(val == Progress.FI_PROGRESS_MANUAL.getVal()) {
			return Progress.FI_PROGRESS_MANUAL;
		}
		throw new IllegalArgumentException("Invalid integer value for method getProgress!");
	}
	
	private static native int getUNSPEC();
	private static native int getAUTO();
	private static native int getMANUAL();
}
