package org.ofi.libjfabric.enums;

public enum EPType {
	FI_EP_UNSPEC(getUNSPEC()),
	FI_EP_MSG(getMSG()),
	FI_EP_DGRAM(getDGRAM()),
	FI_EP_RDM(getRDM());
	
	private int val;
	
	EPType(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static EPType getEPType(int val) {
		if(val == EPType.FI_EP_UNSPEC.getVal()) {
			return EPType.FI_EP_UNSPEC;
		}
		if(val == EPType.FI_EP_MSG.getVal()) {
			return EPType.FI_EP_MSG;
		}
		if(val == EPType.FI_EP_DGRAM.getVal()) {
			return EPType.FI_EP_DGRAM;
		}
		if(val == EPType.FI_EP_RDM.getVal()) {
			return EPType.FI_EP_RDM;
		}
		throw new IllegalArgumentException("Invalid integer value for method getEPType!");
	}
	
	private static native int getUNSPEC();
	private static native int getMSG();
	private static native int getDGRAM();
	private static native int getRDM();
}
