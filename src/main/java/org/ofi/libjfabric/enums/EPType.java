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
	
	private static native int getUNSPEC();
	private static native int getMSG();
	private static native int getDGRAM();
	private static native int getRDM();
}
