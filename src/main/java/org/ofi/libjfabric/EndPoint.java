package org.ofi.libjfabric;

public class EndPoint {
	private long handle;
	
	public EndPoint(long handle) {
		this.handle = handle;
	}
	
	public long getHandle() {
		return this.handle;
	}
}
