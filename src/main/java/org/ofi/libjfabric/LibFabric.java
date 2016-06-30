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
	
	/* This method adds a shutdown hook to the JVM.
	 * The thread defined below will run as the JVM is
	 * shutting down.  It cleans up any memory that was
	 * allocated from C for the users so they do not have
	 * to free the memory manually.
	 */
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
