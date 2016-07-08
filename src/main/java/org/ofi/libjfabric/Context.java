package org.ofi.libjfabric;


//Right now this stores a handle to a context object.  Could possibly store the
//handle to the libjfab_context instead.  Ask Howard about that.
public class Context {
	private long handle;
	private long userContext;
	
	public Context(long userContext) {
		this.handle = initContext(userContext);
		this.userContext = userContext;
	}
	private native long initContext(long userContext);
	
	public long getUserContext() {
		return this.userContext;
	}
	public long getHandle() {
		return this.handle;
	}
}
