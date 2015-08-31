package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.enums.EPType;

public class EndPointAttr {
	private EPType epType;
	private int protocol;
	private int protoVersion;
	private int maxMsgSize;
	private int msgPrefixSize;
	private int maxOrderWawSize;
	private long memTagFormat;
	private int txCtxCnt;
	private int rxCtxCnt;

	public EndPointAttr(EPType epType, int protocol, int protoVersion, int maxMsgSize, int msgPrefixSize,
			int maxOrderWawSize, long memTagFormat, int txCtxCnt, int rxCtxCnt) {
		this.epType = epType;
		this.protocol = protocol;
		this.protoVersion = protoVersion;
		this.maxMsgSize = maxMsgSize;
		this.msgPrefixSize = msgPrefixSize;
		this.maxOrderWawSize = maxOrderWawSize;
		this.memTagFormat = memTagFormat;
		this.txCtxCnt = txCtxCnt;
		this.rxCtxCnt = rxCtxCnt;
	}

	//gets
	public EPType getEpType() {
		return this.epType;
	}
	public int getProtocol() {
		return this.protocol;
	}
	public int getProtoVersion() {
		return this.protoVersion;
	}
	public int getMaxMsgSize() {
		return this.maxMsgSize;
	}
	public int getMsgPrefixSize() {
		return this.msgPrefixSize;
	}
	public int getMaxOrderWawSize() {
		return this.maxOrderWawSize;
	}
	public long getMemTagFormat() {
		return this.memTagFormat;
	}
	public int getTxCtxCnt() {
		return this.txCtxCnt;
	}
	public int getRxCtxCnt() {
		return this.rxCtxCnt;
	}

	//sets
	public void setEpType(EPType epType) {
		this.epType = epType;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	public void setProtoVersion(int protoVersion) {
		this.protoVersion = protoVersion;
	}
	public void setMaxMsgSize(int maxMsgSize) {
		this.maxMsgSize = maxMsgSize;
	}
	public void setMsgPrefixSize(int msgPrefixSize) {
		this.msgPrefixSize = msgPrefixSize;
	}
	public void setMaxOrderWawSize(int maxOrderWawSize) {
		this.maxOrderWawSize = maxOrderWawSize;
	}
	public void setMemTagFormat(long memTagFormat) {
		this.memTagFormat = memTagFormat;
	}
	public void setTxCtxCnt(int txCtxCnt) {
		this.txCtxCnt = txCtxCnt;
	}
	public void setRxCtxCnt(int rxCtxCnt) {
		this.rxCtxCnt = rxCtxCnt;
	}
}
