package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.enums.WaitObj;

public class WaitAttr {
	private long handle;
	
	public WaitAttr(WaitObj waitObj, long flags) {
		this.handle = waitAttrInit(waitObj.getVal(), flags);
	}
	private native long waitAttrInit(int waitObj, long flags);
	
	public WaitAttr(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
	
	public WaitObj getWaitObj() {
		return getWaitObj(this.handle);
	}
	private native WaitObj getWaitObj(long handle);
	
	public long getFlags() {
		return getFlags(this.handle);
	}
	private native long getFlags(long handle);
	
	//sets
	public void setWaitObj(WaitObj waitObj) {
		setWaitObj(waitObj.getVal(), this.handle);
	}
	private native void setWaitObj(int waitObj, long handle);
	
	public void setFlags(long flags) {
		setFlags(flags, this.handle);
	}
	private native void setFlags(long flags, long handle);
}
