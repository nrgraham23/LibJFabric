package org.ofi.libjfabric.attributes;

public class RecieveAttr {
	private long caps;
	private long mode;
	private long opFlags;
	private long msgOrder;
	private long compOrder;
	private int totalBufferedRecv;
	private int size;
	private int iovLimit;

	RecieveAttr(long caps, long mode, long opFlags, long msgOrder, long compOrder, int totalBufferedRecv, int size, int iovLimit) {
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
	public int size() {
		return this.size;
	}
	public int getIovLimit() {
		return this.iovLimit;
	}
}
