package org.ofi.libjfabric;

import java.util.ArrayList;

public class Fabric {
	
	static {
		System.out.println("LOADING LIBRARY");
		System.loadLibrary("jfab_native");	
	}
	
	public static ArrayList<Info> getInfo() {
		
		
		return null;
	}
}
