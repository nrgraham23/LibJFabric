package org.ofi.libjfabric.attributes;

public class ReceiveAttr {
	private long handle;

	public ReceiveAttr(long caps, long mode, long opFlags, long msgOrder, long compOrder, int totalBufferedRecv, int size, int iovLimit) {
		this.handle = initReceiveAttr(caps, mode, opFlags, msgOrder, compOrder, totalBufferedRecv, size, iovLimit);
	}
	private native long initReceiveAttr(long caps, long mode, long opFlags, long msgOrder, long compOrder, 
			int totalBufferedRecv, int size, int iovLimit);

	public ReceiveAttr() {
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
	
	public long getCompOrder() {
		return getCompOrder(this.handle);
	}
	private native long getCompOrder(long handle);
	
	public int getTotalBufferedRecv() {
		return getTotalBufferedRecv(this.handle);
	}
	private native int getTotalBufferedRecv(long handle);
	
	public int getSize() {
		return getSize(this.handle);
	}
	private native int getSize(long handle);
	
	public int getIovLimit() {
		return getIovLimit(this.handle);
	}
	private native int getIovLimit(long handle);

	//sets
	public void setCaps(long caps) {
		setCaps(caps, this.handle);
	}
	private native void setCaps(long caps, long handle);
	
	public void setMode(long mode) {
		setMode(mode, this.handle);
	}
	private native void setMode(long mode, long handle);
	
	public void setOpFlags(long  opFlags) {
		setOpFlags(opFlags, this.handle);
	}
	private native void setOpFlags(long opFlags, long handle);
	
	public void setMsgOrder(long msgOrder) {
		setMsgOrder(msgOrder, this.handle);
	}
	private native void setMsgOrder(long msgOrder, long handle);
	
	public void setCompOrder(long compOrder) {
		setCompOrder(compOrder, this.handle);
	}
	private native void setCompOrder(long compOrder, long handle);
	
	public void setTotalBufferedRecv(int totalBufferedRecv) {
		setTotalBufferedRecv(totalBufferedRecv, this.handle);
	}
	private native void setTotalBufferedRecv(int totalBufferedRecv, long handle);
	
	public void setSize(int size) {
		setSize(size, this.handle);
	}
	private native void setSize(int size, long handle);
	
	public void setIovLimit(int iovLimit) {
		setIovLimit(iovLimit, this.handle);
	}
	private native void setIovLimit(int iovLimit, long handle);
}
