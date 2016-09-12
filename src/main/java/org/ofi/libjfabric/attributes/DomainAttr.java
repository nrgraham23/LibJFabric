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

import org.ofi.libjfabric.enums.*;

public class DomainAttr {
	protected long handle;
	
	public DomainAttr(String name, Threading threading, Progress cntrlProgress, Progress dataProgress, 
			ResourceMgmt resourceMgmt, AVType avType, MRMode mrMode, int mrKeySize, int cqDataSize, int cqCnt, 
			int endpointCount, int txCtxCnt, int rxCtxCnt, int maxEpTxCtx, int maxEpRxCtx, int maxEpStxCtx, int maxEpSrxCtx) 
	{
		this.handle = initDomainAttr(name, threading.getVal(), cntrlProgress.getVal(), dataProgress.getVal(), 
				resourceMgmt.getVal(), avType.getVal(), mrMode.getVal(), mrKeySize, cqDataSize, cqCnt, endpointCount, 
				txCtxCnt, rxCtxCnt, maxEpTxCtx, maxEpRxCtx, maxEpStxCtx, maxEpSrxCtx);
	}
	
	private native long initDomainAttr(String name, int threading, int cntrlProgress, int dataProgress, 
			int resourceMgmt, int avType, int mrMode, int mrKeySize, int cqDataSize, int cqCnt, 
			int endpointCount, int txCtxCnt, int rxCtxCnt, int maxEpTxCtx, int maxEpRxCtx, int maxEpStxCtx, int maxEpSrxCtx);
	
	public DomainAttr() {
		this.handle = initEmpty();
	}
	private native static long initEmpty();
	
	public DomainAttr(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
	
	public String getName() {
		return getName(this.handle);
	}
	private native String getName(long handle);
	
	public Threading getThreading() {
		return getThreading(this.handle);
	}
	private native Threading getThreading(long handle);
	
	public Progress getCntrlProgress() {
		return getCntrlProgress(this.handle);
	}
	private native Progress getCntrlProgress(long handle);
	
	public Progress getDataProgress() {
		return getdataProgress(this.handle);
	}
	private native Progress getdataProgress(long handle);
	
	public ResourceMgmt getResourceMgmt() {
		return this.getResourceMgmt(this.handle);
	}
	private native ResourceMgmt getResourceMgmt(long handle);
	
	public AVType getAvType() {
		return getAVType(this.handle);
	}
	private native AVType getAVType(long handle);
	
	public MRMode getMRMode() {
		return this.getMRMode(this.handle);
	}
	private native MRMode getMRMode(long handle);
	
	public int getMrKeySize() {
		return getMrKeySize(this.handle);
	}
	private native int getMrKeySize(long handle);
	
	public int getCQDataSize() {
		return getCQDataSize(this.handle);
	}
	private native int getCQDataSize(long handle);
	
	public int getCQCnt() {
		return getCQCnt(this.handle);
	}
	private native int getCQCnt(long handle);
	
	public int getEndPointCnt() {
		return getEndPointCnt(handle);
	}
	
	private native int getEndPointCnt(long handle);
	
	public int getTxCtxCnt() {
		return getTxCtxCnt(handle);
	}
	
	private native int getTxCtxCnt(long handle);
	
	public int getRxCtxCnt() {
		return getRxCtxCnt(handle);
	}
	
	private native int getRxCtxCnt(long handle);
	
	public int getMaxEpTxCtx() {
		return getMaxEpTxCtx(handle);
	}
	
	private native int getMaxEpTxCtx(long handle);
	
	public int getMaxEpRxCtx() {
		return getMaxEpRxCtx(handle);
	}
	
	private native int getMaxEpRxCtx(long handle);
	
	public int getMaxEpStxCtx() {
		return getMaxEpStxCtx(this.handle);
	}
	private native int getMaxEpStxCtx(long handle);
	
	public int getMaxEpSrxCtx() {
		return getMaxEpSrxCtx(this.handle);
	}
	private native int getMaxEpSrxCtx(long handle);
	
	//sets
	public void setName(String name) {
		setName(name, this.handle);
	}
	private native void setName(String name, long handle);
	
	public void setThreading(Threading threading) {
		setThreading(threading.getVal(), this.handle);
	}
	private native void setThreading(int threading, long handle);
	
	public void setCntrlProgress(Progress cntrlProgress) {
		setCntrlProgress(cntrlProgress.getVal(), this.handle);
	}
	private native void setCntrlProgress(int cntrlProgress, long handle);
	
	public void setDataProgress(Progress dataProgress) {
		setDataProgress(dataProgress.getVal(), this.handle);
	}
	private native void setDataProgress(int dataProgress, long handle);
	
	public void setResourceMgmt(ResourceMgmt resourceMgmt) {
		setResourceMgmt(resourceMgmt.getVal(), this.handle);
	}
	private native void setResourceMgmt(int resourceMgmt, long handle);
	
	public void setAVType(AVType avType) {
		setAVType(avType.getVal(), this.handle);
	}
	private native void setAVType(int avType, long handle);
	
	public void setMRMode(MRMode mrMode) {
		setMRMode(mrMode.getVal(), this.handle);
	}
	private native void setMRMode(int mrMode, long handle);
	
	public void setMRKeySize(int mrKeySize) {
		setMRKeySize(mrKeySize, this.handle);
	}
	private native void setMRKeySize(int mrKeySize, long handle);
	
	public void setCQDataSize(int cqDataSize) {
		setCQDataSize(cqDataSize, this.handle);
	}
	private native void setCQDataSize(int mrKeySize, long handle);
	
	public void setCQCnt(int cqCnt) {
		setCQCnt(cqCnt, this.handle);
	}
	private native void setCQCnt(int cqCnt, long handle);
	
	public void setEndpointCnt(int endpointCnt) {
		setEndpointCnt(endpointCnt, this.handle);
	}
	private native void setEndpointCnt(int endpointCnt, long handle);
	
	public void setTxCtxCnt(int txCtxCnt) {
		setTxCtxCnt(txCtxCnt, this.handle);
	}
	private native void setTxCtxCnt(int txCtxCnt, long handle);
	
	public void setRxCtxCnt(int rxCtxCnt) {
		setRxCtxCnt(rxCtxCnt, this.handle);
	}
	private native void setRxCtxCnt(int rxCtxCnt, long handle);
	
	public void setMaxEpTxCtx(int maxEpTxCtx) {
		setMaxEpTxCtx(maxEpTxCtx, this.handle);
	}
	private native void setMaxEpTxCtx(int maxEpTxCtx, long handle);
	
	public void setMaxEpRxCtx(int maxEpRxCtx) {
		setMaxEpRxCtx(maxEpRxCtx, this.handle);
	}
	private native void setMaxEpRxCtx(int maxEpRxCtx, long handle);
	
	public void setMaxEpStxCtx(int maxEpStxCtx) {
		setMaxEpStxCtx(maxEpStxCtx, this.handle);
	}
	private native void setMaxEpStxCtx(int maxEpStxCtx, long handle);
	
	public void setMaxEpSrxCtx(int maxEpSrxCtx) {
		setMaxEpSrxCtx(maxEpSrxCtx, this.handle);
	}
	private native void setMaxEpSrxCtx(int maxEpSrxCtx, long handle);
}
