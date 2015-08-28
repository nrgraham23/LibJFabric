package org.ofi.libjfabric.enums;

public enum Threading {
	UNSPEC(getUNSPEC()),
	SAFE(getSAFE()),
	FID(getFID()),
	DOMAIN(getDOMAIN()),
	COMPLETION(getCOMPLETION()),
	ENDPOINT(getENDPOINT());
	
	private int val;
	
	Threading(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	private static native int getUNSPEC();
	private static native int getSAFE();
	private static native int getFID();
	private static native int getDOMAIN();
	private static native int getCOMPLETION();
	private static native int getENDPOINT();
}
