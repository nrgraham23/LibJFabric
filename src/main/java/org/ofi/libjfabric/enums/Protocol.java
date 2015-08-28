package org.ofi.libjfabric.enums;

public enum Protocol {
	UNSPEC(getUNSPEC()),
	RDMA_CM_IB_RC(getRDMA()),
	IWARP(getIWARP()),
	IB_UD(getIBUD()),
	PSMX(getPSMX()),
	UDP(getUDP()),
	SOCK_TCP(getSOCKTCP()),
	GNI(getGNI());
	
	private int val;
	
	Protocol(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	private static native int getUNSPEC();
	private static native int getRDMA();
	private static native int getIWARP();
	private static native int getIBUD();
	private static native int getPSMX();
	private static native int getUDP();
	private static native int getSOCKTCP();
	private static native int getGNI();
}
