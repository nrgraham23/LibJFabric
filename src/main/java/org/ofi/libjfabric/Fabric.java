package org.ofi.libjfabric;

public class Fabric {
	
	static {
		System.loadLibrary("jfab_native");	
	}
	
	public static Info[] getInfo(int version, String node, String service, long flags, Info hints) {
		return getInfoJNI(version, node, service, flags, hints);
	}
	
	private static native Info[] getInfoJNI(int version, String node, String service, long flags, Info hints);
}
