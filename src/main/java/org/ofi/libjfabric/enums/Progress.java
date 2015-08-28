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
	
	private static native int getUNSPEC();
	private static native int getAUTO();
	private static native int getMANUAL();
}
