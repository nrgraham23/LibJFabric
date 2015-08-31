package org.ofi.libjfabric.attributes;

public class TransmitAttr {
	private long caps;
	private long mode;
	private long opFlags;
	private long msgOrder;
	private int injectSize;
	private int size;
	private int iovLimit;
	private int rmaIovLimit;

	public TransmitAttr(long caps, long mode, long opFlags, long msgOrder, int injectSize, int size, int iovLimit, int rmaIovLimit) {
		this.caps = caps;
		this.mode = mode;
		this.opFlags = opFlags;
		this.msgOrder = msgOrder;
		this.injectSize = injectSize;
		this.size = size;
		this.iovLimit = iovLimit;
		this.rmaIovLimit = rmaIovLimit;
	}

	//gets
	public long getCaps() {
		return this.caps;
	}
	public long getMode() {
		return this.mode;
	}
	public long getOpFlags() {
		return this.opFlags;
	}
	public long getMsgOrder() {
		return this.msgOrder;
	}
	public int getInjectSize() {
		return this.injectSize;
	}
	public int getSize() {
		return this.size;
	}
	public int getIovLimit() {
		return this.iovLimit;
	}
	public int getRmaIovLimit() {
		return this.rmaIovLimit;
	}

	//sets
	public void setCaps(long caps) {
		this.caps = caps;
	}
	public void setMode(long mode) {
		this.mode = mode;
	}
	public void setOpFlags(long opFlags) {
		this.opFlags = opFlags;
	}
	public void setMsgOrder(long msgOrder) {
		this.msgOrder = msgOrder;
	}
	public void setInjectSize(int injectSize) {
		this.injectSize = injectSize;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setIovLimit(int iovLimit) {
		this.iovLimit = iovLimit;
	}
	public void setRmaIovLimit(int rmaIovLimit) {
		this.rmaIovLimit = rmaIovLimit;
	}
}
