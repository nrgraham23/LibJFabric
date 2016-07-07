package org.ofi.libjfabric;

public class Context {
	private long handle;
	private long userContext;
	
	public Context(long userContext) {
		this.handle = initContext();
		this.userContext = userContext;
	}
	private native long initContext();
	
	public long getTag() {
		return this.userContext;
	}
	public long getHandle() {
		return this.handle;
	}
}
