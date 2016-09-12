/*
 * Copyright (c) 2015-2016 Los Alamos Nat. Security, LLC. All rights reserved.
 *
 * This software is available to you under a choice of one of two
 * licenses.  You may choose to be licensed under the terms of the GNU
 * General Public License (GPL) Version 2, available from the file
 * COPYING in the main directory of this source tree, or the
 * BSD license below:
 *
 *     Redistribution and use in source and binary forms, with or
 *     without modification, are permitted provided that the following
 *     conditions are met:
 *
 *      - Redistributions of source code must retain the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer.
 *
 *      - Redistributions in binary form must reproduce the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.enums.EPType;
import org.ofi.libjfabric.enums.Protocol;

public class EndPointAttr {
	private long handle;

	public EndPointAttr(EPType epType, Protocol protocol, int protoVersion, int maxMsgSize, int msgPrefixSize,
			int maxOrderRawSize, int maxOrderWarSize, int maxOrderWawSize, long memTagFormat, int txCtxCnt, int rxCtxCnt) {
		this.handle = initEndPointAttr(epType.getVal(), protocol.getVal(), protoVersion, maxMsgSize, msgPrefixSize,
				maxOrderRawSize, maxOrderWarSize, maxOrderWawSize, memTagFormat, txCtxCnt, rxCtxCnt);
	}
	private native long initEndPointAttr(int epType, int protocol, int protoVersion, int maxMsgSize, int msgPrefixSize,
			int maxOrderRawSize, int maxOrderWarSize, int maxOrderWawSize, long memTagFormat, int txCtxCnt, int rxCtxCnt);
	
	public EndPointAttr() {
		this.handle = initEmpty();
	}
	private native long initEmpty();
	
	public EndPointAttr(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
	
	public EPType getEpType() {
		return getEpType(this.handle);
	}
	private native EPType getEpType(long handle);
	
	public Protocol getProtocol() {
		return getProtocol(this.handle);
	}
	private native Protocol getProtocol(long handle);
	
	public int getProtoVersion() {
		return getProtoVersion(this.handle);
	}
	private native int getProtoVersion(long handle);
	
	public int getMaxMsgSize() {
		return getMaxMsgSize(this.handle);
	}
	private native int getMaxMsgSize(long handle);
	
	public int getMsgPrefixSize() {
		return getMsgPrefixSize(this.handle);
	}
	private native int getMsgPrefixSize(long handle);
	
	public int getMaxOrderRawSize() {
		return getMaxOrderRawSize(this.handle);
	}
	private native int getMaxOrderRawSize(long handle);
	
	public int getMaxOrderWarSize() {
		return getMaxOrderWarSize(this.handle);
	}
	private native int getMaxOrderWarSize(long handle);
	
	public int getMaxOrderWawSize() {
		return getMaxOrderWawSize(this.handle);
	}
	private native int getMaxOrderWawSize(long handle);
	
	public long getMemTagFormat() {
		return getMemTagFormat(this.handle);
	}
	private native long getMemTagFormat(long handle);
	
	public int getTxCtxCnt() {
		return getTxCtxCnt(this.handle);
	}
	private native int getTxCtxCnt(long handle);
	
	public int getRxCtxCnt() {
		return getRxCtxCnt(this.handle);
	}
	private native int getRxCtxCnt(long handle);

	//sets
	public void setEpType(EPType epType) {
		setEpType(epType.getVal(), this.handle);
	}
	private native void setEpType(int epType, long handle);
	
	public void setProtocol(Protocol protocol) {
		setProtocol(protocol.getVal(), this.handle);
	}
	private native void setProtocol(int protocol, long handle);
	
	public void setProtoVersion(int protoVersion) {
		setProtoVersion(protoVersion, this.handle);
	}
	private native void setProtoVersion(int protoVersion, long handle);
	
	public void setMaxMsgSize(int maxMsgSize) {
		setMaxMsgSize(maxMsgSize, this.handle);
	}
	private native void setMaxMsgSize(int maxMdgSize, long handle);
	
	public void setMsgPrefixSize(int msgPrefixSize) {
		setMsgPrefixSize(msgPrefixSize, this.handle);
	}
	private native void setMsgPrefixSize(int msgPrefixSize, long handle);
	
	public void setMaxOrderRawSize(int maxOrderRawSize) {
		setMaxOrderRawSize(maxOrderRawSize, this.handle);
	}
	private native void setMaxOrderRawSize(int maxOrderRawSize, long handle);
	
	public void setMaxOrderWarSize(int maxOrderWarSize) {
		setMaxOrderWarSize(maxOrderWarSize, this.handle);
	}
	private native void setMaxOrderWarSize(int maxOrderWarSize, long handle);
	
	public void setMaxOrderWawSize(int maxOrderWawSize) {
		setMaxOrderWawSize(maxOrderWawSize, this.handle);
	}
	private native void setMaxOrderWawSize(int maxOrderWawSize, long handle);
	
	public void setMemTagFormat(long memTagFormat) {
		setMemTagFormat(memTagFormat, this.handle);
	}
	private native void setMemTagFormat(long memTagFormat, long handle);
	
	public void setTxCtxCnt(int txCtxCnt) {
		setTxCtxCnt(txCtxCnt, this.handle);
	}
	private native void setTxCtxCnt(int txCtxCnt, long handle);
	
	public void setRxCtxCnt(int rxCtxCnt) {
		setRxCtxCnt(rxCtxCnt, this.handle);
	}
	private native void setRxCtxCnt(int rxCtxCnt, long handle);
	
}
