package org.ofi.libjfabric;

public class LibFabric {
	
	public static void loadVerbose() {
		try {
			System.loadLibrary("jfab_native");
			System.loadLibrary("fabric");
			init();
			registerNativeCleanup();
		} catch (UnsatisfiedLinkError e) {
			throw new RuntimeException("Could not load the libfabric native library");
		}
	}
	
	public static boolean load() {
		try {
			System.loadLibrary("jfab_native");
			System.loadLibrary("fabric");
			init();
			registerNativeCleanup();
			return true;
		} catch (UnsatisfiedLinkError e) {
			return false;
		}
	}
	
	private static native void init();
	
	private static void registerNativeCleanup() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				nativeCleanup();
			}
		});
	}
	
	private static void nativeCleanup() {
		deleteCachedVars();
	}
	
	private static native void deleteCachedVars();
}
