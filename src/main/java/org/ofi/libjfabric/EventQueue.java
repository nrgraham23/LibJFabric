package org.ofi.libjfabric;

public class EventQueue {
	private long handle;
	
	public EventQueue(long handle) {
		this.handle = handle;
	}
	
	public long getHandle() {
		return this.handle;
	}
}
