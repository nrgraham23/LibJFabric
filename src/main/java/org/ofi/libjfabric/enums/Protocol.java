package org.ofi.libjfabric.enums;

public enum Protocol {
	UNSPEC(getUNSPEC()),
	RDMA_CM_IB_RC(getRDMA()),
	IWARP(getIWARP()),
	IB_UD(getIBUD()),
	PSMX(getPSMX()),
	UDP(getUDP()),
	SOCK_TCP(getSOCKTCP()),
	MXM(getMXM()),
	IWARP_RDM(getIWARPRDM()),
	IB_RDM(getIBRDM()),
	GNI(getGNI()),
	RXM(getRXM());
	
	private int val;
	
	Protocol(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static Protocol getProtocol(int val) {
		if(val == Protocol.UNSPEC.getVal()) {
			return Protocol.UNSPEC;
		}
		if(val == Protocol.RDMA_CM_IB_RC.getVal()) {
			return Protocol.RDMA_CM_IB_RC;
		}
		if(val == Protocol.IWARP.getVal()) {
			return Protocol.IWARP;
		}
		if(val == Protocol.IB_UD.getVal()) {
			return Protocol.IB_UD;
		}
		if(val == Protocol.PSMX.getVal()) {
			return Protocol.PSMX;
		}
		if(val == Protocol.UDP.getVal()) {
			return Protocol.UDP;
		}
		if(val == Protocol.SOCK_TCP.getVal()) {
			return Protocol.SOCK_TCP;
		}
		if(val == Protocol.MXM.getVal()) {
			return Protocol.MXM;
		}
		if(val == Protocol.IWARP_RDM.getVal()) {
			return Protocol.IWARP_RDM;
		}
		if(val == Protocol.IB_RDM.getVal()) {
			return Protocol.IB_RDM;
		}
		if(val == Protocol.GNI.getVal()) {
			return Protocol.GNI;
		}
		if(val == Protocol.RXM.getVal()) {
			return Protocol.RXM;
		}
		throw new IllegalArgumentException("Invalid integer value for method getProtocol!");
	}
	
	private static native int getUNSPEC();
	private static native int getRDMA();
	private static native int getIWARP();
	private static native int getIBUD();
	private static native int getPSMX();
	private static native int getUDP();
	private static native int getSOCKTCP();
	private static native int getMXM();
	private static native int getIWARPRDM();
	private static native int getIBRDM();
	private static native int getGNI();
	private static native int getRXM();
}
