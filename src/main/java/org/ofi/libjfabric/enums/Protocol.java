package org.ofi.libjfabric.enums;

public enum Protocol {
	FI_PROTO_UNSPEC(getUNSPEC()),
	FI_PROTO_RDMA_CM_IB_RC(getRDMA()),
	FI_PROTO_IWARP(getIWARP()),
	FI_PROTO_IB_UD(getIBUD()),
	FI_PROTO_PSMX(getPSMX()),
	FI_PROTO_UDP(getUDP()),
	FI_PROTO_SOCK_TCP(getSOCKTCP()),
	FI_PROTO_MXM(getMXM()),
	FI_PROTO_IWARP_RDM(getIWARPRDM()),
	FI_PROTO_IB_RDM(getIBRDM()),
	FI_PROTO_GNI(getGNI()),
	FI_PROTO_RXM(getRXM());
	
	private int val;
	
	Protocol(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static Protocol getProtocol(int val) {
		if(val == Protocol.FI_PROTO_UNSPEC.getVal()) {
			return Protocol.FI_PROTO_UNSPEC;
		}
		if(val == Protocol.FI_PROTO_RDMA_CM_IB_RC.getVal()) {
			return Protocol.FI_PROTO_RDMA_CM_IB_RC;
		}
		if(val == Protocol.FI_PROTO_IWARP.getVal()) {
			return Protocol.FI_PROTO_IWARP;
		}
		if(val == Protocol.FI_PROTO_IB_UD.getVal()) {
			return Protocol.FI_PROTO_IB_UD;
		}
		if(val == Protocol.FI_PROTO_PSMX.getVal()) {
			return Protocol.FI_PROTO_PSMX;
		}
		if(val == Protocol.FI_PROTO_UDP.getVal()) {
			return Protocol.FI_PROTO_UDP;
		}
		if(val == Protocol.FI_PROTO_SOCK_TCP.getVal()) {
			return Protocol.FI_PROTO_SOCK_TCP;
		}
		if(val == Protocol.FI_PROTO_MXM.getVal()) {
			return Protocol.FI_PROTO_MXM;
		}
		if(val == Protocol.FI_PROTO_IWARP_RDM.getVal()) {
			return Protocol.FI_PROTO_IWARP_RDM;
		}
		if(val == Protocol.FI_PROTO_IB_RDM.getVal()) {
			return Protocol.FI_PROTO_IB_RDM;
		}
		if(val == Protocol.FI_PROTO_GNI.getVal()) {
			return Protocol.FI_PROTO_GNI;
		}
		if(val == Protocol.FI_PROTO_RXM.getVal()) {
			return Protocol.FI_PROTO_RXM;
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
