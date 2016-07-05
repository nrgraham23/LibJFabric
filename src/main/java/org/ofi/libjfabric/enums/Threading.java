package org.ofi.libjfabric.enums;

public enum Threading {
	FI_THREAD_UNSPEC(getUNSPEC()),
	FI_THREAD_SAFE(getSAFE()),
	FI_THREAD_FID(getFID()),
	FI_THREAD_DOMAIN(getDOMAIN()),
	FI_THREAD_COMPLETION(getCOMPLETION()),
	FI_THREAD_ENDPOINT(getENDPOINT());
	
	private int val;
	
	Threading(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static Threading getThreading(int val) {
		if(val == Threading.FI_THREAD_UNSPEC.getVal()) {
			return Threading.FI_THREAD_UNSPEC;
		}
		if(val == Threading.FI_THREAD_SAFE.getVal()) {
			return Threading.FI_THREAD_SAFE;
		}
		if(val == Threading.FI_THREAD_FID.getVal()) {
			return Threading.FI_THREAD_FID;
		}
		if(val == Threading.FI_THREAD_DOMAIN.getVal()) {
			return Threading.FI_THREAD_DOMAIN;
		}
		if(val == Threading.FI_THREAD_COMPLETION.getVal()) {
			return Threading.FI_THREAD_COMPLETION;
		}
		if(val == Threading.FI_THREAD_ENDPOINT.getVal()) {
			return Threading.FI_THREAD_ENDPOINT;
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
