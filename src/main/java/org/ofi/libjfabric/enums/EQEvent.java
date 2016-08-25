package org.ofi.libjfabric.enums;

public enum EQEvent {
	FI_NOTIFY(getNOTIFY()),
	FI_CONNREQ(getCONNREQ()),
	FI_CONNECTED(getCONNECTED()),
	FI_SHUTDOWN(getSHUTDOWN()),
	FI_MR_COMPLETE(getMRCOMPLETE()),
	FI_AV_COMPLETE(getAVCOMPLETE());
	
	
	private int val;
	
	EQEvent(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static EQEvent getEQEvent(int val) {
		if(val == EQEvent.FI_NOTIFY.getVal()) {
			return EQEvent.FI_NOTIFY;
		}
		if(val == EQEvent.FI_CONNREQ.getVal()) {
			return EQEvent.FI_CONNREQ;
		}
		if(val == EQEvent.FI_CONNECTED.getVal()) {
			return EQEvent.FI_CONNECTED;
		}
		if(val == EQEvent.FI_SHUTDOWN.getVal()) {
			return EQEvent.FI_SHUTDOWN;
		}
		if(val == EQEvent.FI_MR_COMPLETE.getVal()) {
			return EQEvent.FI_MR_COMPLETE;
		}
		if(val == EQEvent.FI_AV_COMPLETE.getVal()) {
			return EQEvent.FI_AV_COMPLETE;
		}
		throw new IllegalArgumentException("Invalid integer value for method getEQEvent!");
	}
	
	private static native int getNOTIFY();
	private static native int getCONNREQ();
	private static native int getCONNECTED();
	private static native int getSHUTDOWN();
	private static native int getMRCOMPLETE();
	private static native int getAVCOMPLETE();
}
