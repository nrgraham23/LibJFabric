package org.ofi.libjfabric.enums;

public enum WaitObj {
	WAIT_NONE(getNONE()),
	WAIT_UNSPEC(getUNSPEC()),
	WAIT_SET(getSET()),
	WAIT_FD(getFD()),
	WAIT_MUTEX_COND(getMUTEXCOND()),
	WAIT_CRITSEC_COND(getCRITSECCOND());

	private int val;

	WaitObj(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	//private because it should only be used from the JNI code
	private static WaitObj getWaitObj(int val) {
		if(val == WaitObj.WAIT_NONE.getVal()) {
			return WaitObj.WAIT_NONE;
		}
		if(val == WaitObj.WAIT_UNSPEC.getVal()) {
			return WaitObj.WAIT_UNSPEC;
		}
		if(val == WaitObj.WAIT_SET.getVal()) {
			return WaitObj.WAIT_SET;
		}
		if(val == WaitObj.WAIT_FD.getVal()) {
			return WaitObj.WAIT_FD;
		}
		if(val == WaitObj.WAIT_MUTEX_COND.getVal()) {
			return WaitObj.WAIT_MUTEX_COND;
		}
		if(val == WaitObj.WAIT_CRITSEC_COND.getVal()) {
			return WaitObj.WAIT_CRITSEC_COND;
		}
		throw new IllegalArgumentException("Invalid integer value for method getWaitObj!");
	}

	private static native int getNONE();
	private static native int getUNSPEC();
	private static native int getSET();
	private static native int getFD();
	private static native int getMUTEXCOND();
	private static native int getCRITSECCOND();
}
