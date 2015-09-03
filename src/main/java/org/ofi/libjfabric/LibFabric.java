package org.ofi.libjfabric;

public class LibFabric {
	
	public static void loadVerbose() {
		try {
			System.loadLibrary("jfab_native");
			init();
		} catch (UnsatisfiedLinkError e) {
			throw new RuntimeException("Could not load the libfabric native library");
		}
	}
	
	public static boolean load() {
		try {
			System.loadLibrary("jfab_native");
			init();
			return true;
		} catch (UnsatisfiedLinkError e) {
			return false;
		}
	}
	
	private static native void init();
	
}
