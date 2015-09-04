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
	
	//private because it should only be used from the JNI code
	private static Threading getThreading(int val) {
		if(val == Threading.UNSPEC.getVal()) {
			return Threading.UNSPEC;
		}
		if(val == Threading.SAFE.getVal()) {
			return Threading.SAFE;
		}
		if(val == Threading.FID.getVal()) {
			return Threading.FID;
		}
		if(val == Threading.DOMAIN.getVal()) {
			return Threading.DOMAIN;
		}
		if(val == Threading.COMPLETION.getVal()) {
			return Threading.COMPLETION;
		}
		if(val == Threading.ENDPOINT.getVal()) {
			return Threading.ENDPOINT;
		}
		throw new IllegalArgumentException("Invalid integer value for method getThreading!");
	}
	
	private static native int getUNSPEC();
	private static native int getSAFE();
	private static native int getFID();
	private static native int getDOMAIN();
	private static native int getCOMPLETION();
	private static native int getENDPOINT();
}
