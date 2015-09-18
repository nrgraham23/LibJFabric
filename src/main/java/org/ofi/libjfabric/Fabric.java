package org.ofi.libjfabric;

public class Fabric {
	private long handle;
	
	public Fabric() {
		
	}
	
	public Fabric(long handle) {
		this.handle = handle;
	}
	
	public long getHandle() {
		return this.handle;
	}
	
	public static Info[] getInfo(double version, String node, String service, long flags, Info hints) {
		long infoHandleArray[] = getInfoJNI(version, node, service, flags, hints.getHandle());
		
		if(infoHandleArray == null) {
			return null; //should probably handle this better
		}
		
		Info infoArray[] = new Info[infoHandleArray.length];
		
		for(int i = 0; i < infoHandleArray.length; i++) {
			infoArray[i] = new Info(infoHandleArray[i]);
		}
		
		return infoArray;
	}
	
	private static native long[] getInfoJNI(double version, String node, String service, long flags, long hints);
}
