package org.ofi.libjfabric;

public class Domain {
	private long handle;
	
	public Domain() {
		
	}
	
	public  Domain(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
}
