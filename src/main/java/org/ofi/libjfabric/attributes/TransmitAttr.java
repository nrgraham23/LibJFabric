package org.ofi.libjfabric.attributes;

public class TransmitAttr {
	private long handle;
	
	public TransmitAttr(long caps, long mode, long opFlags, long msgOrder, int injectSize, int size, int iovLimit, int rmaIovLimit) {
		this.handle = initTransmitAttr(caps, mode, opFlags, msgOrder, injectSize, size, iovLimit, rmaIovLimit);
	}
	private native long initTransmitAttr(long caps, long mode, long opFlags, long msgOrder, int injectSize, int size, 
			int iovLimit, int rmaIovLimit);

	public TransmitAttr() {
		this.handle = initEmpty();
	}
	private native long initEmpty();
	
	//gets
	public long getCaps() {
		return getCaps(this.handle);
	}
	private native long getCaps(long handle);
	
	public long getMode() {
		return getMode(this.handle);
	}
	private native long getMode(long handle);
	
	public long getOpFlags() {
		return getOpFlags(this.handle);
	}
	private native long getOpFlags(long handle);
	
	public long getMsgOrder() {
		return getMsgOrder(this.handle);
	}
	private native long getMsgOrder(long handle);
	
	public int getInjectSize() {
		return getInjectSize(this.handle);
	}
	private native int getInjectSize(long handle);
	
	public int getSize() {
		return getSize(this.handle);
	}
	private native int getSize(long handle);
	
	public int getIovLimit() {
		return getIovLimit(this.handle);
	}
	private native int getIovLimit(long handle);
	
	public int getRmaIovLimit() {
		return getRmaIovLimit(this.handle);
	}
	private native int getRmaIovLimit(long handle);

	//sets
	public void setCaps(long caps) {
		setCaps(caps, this.handle);
	}
	private native void setCaps(long caps, long handle);
	
	public void setMode(long mode) {
		setMode(mode, this.handle);
	}
	private native void setMode(long mode, long handle);
	
	public void setOpFlags(long opFlags) {
		setOpFlags(opFlags, this.handle);
	}
	private native void setOpFlags(long opFlags, long handle);
	
	public void setMsgOrder(long msgOrder) {
		setMsgOrder(msgOrder, this.handle);
	}
	private native void setMsgOrder(long msgOrder, long handle);
	
	public void setInjectSize(int injectSize) {
		setInjectSize(injectSize, this.handle);
	}
	private native void setInjectSize(int injectSize, long handle);
	
	public void setSize(int size) {
		setSize(size, this.handle);
	}
	private native void setSize(int size, long handle);
	
	public void setIovLimit(int iovLimit) {
		setIovLimit(iovLimit, this.handle);
	}
	private native void setIovLimit(int iovLimit, long handle);
	
	public void setRmaIovLimit(int rmaIovLimit) {
		setRmaIovLimit(rmaIovLimit, this.handle);
	}
	private native void setRmaIovLimit(int rmaIovLimit, long handle);
	
}
