package org.ofi.libjfabric;

public class LibFabric {
	
	static {
		System.out.println("LOADING LIBRARY");
		System.loadLibrary("jfab_native");
		
	}
	
}
