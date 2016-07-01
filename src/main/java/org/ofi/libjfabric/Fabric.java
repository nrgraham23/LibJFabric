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
}
