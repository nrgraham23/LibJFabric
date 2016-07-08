package org.ofi.libjfabric;

//Fabric Interface Descriptor
public abstract class FIDescriptor {
	protected long handle;
	
	protected FIDescriptor() {
		
	}
	
	public FIDescriptor(long handle) {
		this.handle = handle;
	}
	
	public boolean close() {
		return close(this.handle);
	}
	private native boolean close(long handle);
	
	public boolean bind(FIDescriptor bindTo, long flags) {
		return bind(this.handle, bindTo.getHandle(), flags);
	}
	private native boolean bind(long thisHandle, long bindToHandle, long flags);
	
	public void control() {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void opsOpen() {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public long getHandle() {
		return this.handle;
	}
}
