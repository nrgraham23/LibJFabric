package org.ofi.libjfabric.attributes;

public class ReceiveAttr {
	private long caps;
	private long mode;
	private long opFlags;
	private long msgOrder;
	private long compOrder;
	private int totalBufferedRecv;
	private int size;
	private int iovLimit;

	public ReceiveAttr(long caps, long mode, long opFlags, long msgOrder, long compOrder, int totalBufferedRecv, int size, int iovLimit) {
		this.caps = caps;
		this.mode = mode;
		this.opFlags = opFlags;
		this.msgOrder = msgOrder;
		this.size = size;
		this.iovLimit = iovLimit;
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
	public long getCompOrder() {
		return this.compOrder;
	}
	public int getTotalBufferedRecv() {
		return this.totalBufferedRecv;
	}
	public int getSize() {
		return this.size;
	}
	public int getIovLimit() {
		return this.iovLimit;
	}

	//sets
	public void setCaps(long caps) {
		this.caps = caps;
	}
	public void setMode(long mode) {
		this.mode = mode;
	}
	public void setOpFlags(long  opFlags) {
		this.opFlags = opFlags;
	}
	public void setMsgOrder(long msgOrder) {
		this.msgOrder = msgOrder;
	}
	public void setCompOrder(long compOrder) {
		this.compOrder = compOrder;
	}
	public void setTotalBufferedRecv(int totalBufferedRecv) {
		this.totalBufferedRecv = totalBufferedRecv;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setIovLimit(int iovLimit) {
		this.iovLimit = iovLimit;
	}
}
