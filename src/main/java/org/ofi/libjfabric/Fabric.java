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
	
	public static Info[] getInfo(int version, String node, String service, long flags, Info hints) {
		return getInfoJNI(version, node, service, flags, hints);
	}
	
	private static native Info[] getInfoJNI(int version, String node, String service, long flags, Info hints);
}
