package org.ofi.libjfabric.enums;

public enum EPType {
	UNSPEC(getUNSPEC()),
	MSG(getMSG()),
	DGRAM(getDGRAM()),
	RDM(getRDM());
	
	private int val;
	
	EPType(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static EPType getEPType(int val) {
		if(val == EPType.UNSPEC.getVal()) {
			return EPType.UNSPEC;
		}
		if(val == EPType.MSG.getVal()) {
			return EPType.MSG;
		}
		if(val == EPType.DGRAM.getVal()) {
			return EPType.DGRAM;
		}
		if(val == EPType.RDM.getVal()) {
			return EPType.RDM;
		}
		throw new IllegalArgumentException("Invalid integer value for method getEPType!");
	}
	
	private static native int getUNSPEC();
	private static native int getMSG();
	private static native int getDGRAM();
	private static native int getRDM();
}
