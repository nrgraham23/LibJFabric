package org.ofi.libjfabric;

public class Wait {
	private long handle;
	
	public Wait(long handle) {
		this.handle = handle;
	}
	
	public long getHandle() {
		return this.handle;
	}
}
