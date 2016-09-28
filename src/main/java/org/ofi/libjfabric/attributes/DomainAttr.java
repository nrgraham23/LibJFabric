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
			ResourceMgmt resourceMgmt, AVType avType, MRMode mrMode, long mrKeySize, long cqDataSize, long cqCnt, 
			long endpointCount, long txCtxCnt, long rxCtxCnt, long maxEpTxCtx, long maxEpRxCtx, long maxEpStxCtx, long maxEpSrxCtx) 
	{
		this.handle = initDomainAttr(name, threading.getVal(), cntrlProgress.getVal(), dataProgress.getVal(), 
				resourceMgmt.getVal(), avType.getVal(), mrMode.getVal(), mrKeySize, cqDataSize, cqCnt, endpointCount, 
				txCtxCnt, rxCtxCnt, maxEpTxCtx, maxEpRxCtx, maxEpStxCtx, maxEpSrxCtx);
	}
	
	private native long initDomainAttr(String name, long threading, long cntrlProgress, long dataProgress, 
			long resourceMgmt, long avType, long mrMode, long mrKeySize, long cqDataSize, long cqCnt, 
			long endpointCount, long txCtxCnt, long rxCtxCnt, long maxEpTxCtx, long maxEpRxCtx, long maxEpStxCtx, long maxEpSrxCtx);
	
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
	
	public long getMrKeySize() {
		return getMrKeySize(this.handle);
	}
	private native long getMrKeySize(long handle);
	
	public long getCQDataSize() {
		return getCQDataSize(this.handle);
	}
	private native long getCQDataSize(long handle);
	
	public long getCQCnt() {
		return getCQCnt(this.handle);
	}
	private native long getCQCnt(long handle);
	
	public long getEndPointCnt() {
		return getEndPointCnt(handle);
	}
	private native long getEndPointCnt(long handle);
	
	public long getTxCtxCnt() {
		return getTxCtxCnt(handle);
	}
	private native long getTxCtxCnt(long handle);
	
	public long getRxCtxCnt() {
		return getRxCtxCnt(handle);
	}
	private native long getRxCtxCnt(long handle);
	
	public long getMaxEpTxCtx() {
		return getMaxEpTxCtx(handle);
	}
	private native long getMaxEpTxCtx(long handle);
	
	public long getMaxEpRxCtx() {
		return getMaxEpRxCtx(handle);
	}
	private native long getMaxEpRxCtx(long handle);
	
	public long getMaxEpStxCtx() {
		return getMaxEpStxCtx(this.handle);
	}
	private native long getMaxEpStxCtx(long handle);
	
	public long getMaxEpSrxCtx() {
		return getMaxEpSrxCtx(this.handle);
	}
	private native long getMaxEpSrxCtx(long handle);
	
	//sets
	public void setName(String name) {
		setName(name, this.handle);
	}
	private native void setName(String name, long handle);
	
	public void setThreading(Threading threading) {
		setThreading(threading.getVal(), this.handle);
	}
	private native void setThreading(long threading, long handle);
	
	public void setCntrlProgress(Progress cntrlProgress) {
		setCntrlProgress(cntrlProgress.getVal(), this.handle);
	}
	private native void setCntrlProgress(long cntrlProgress, long handle);
	
	public void setDataProgress(Progress dataProgress) {
		setDataProgress(dataProgress.getVal(), this.handle);
	}
	private native void setDataProgress(long dataProgress, long handle);
	
	public void setResourceMgmt(ResourceMgmt resourceMgmt) {
		setResourceMgmt(resourceMgmt.getVal(), this.handle);
	}
	private native void setResourceMgmt(long resourceMgmt, long handle);
	
	public void setAVType(AVType avType) {
		setAVType(avType.getVal(), this.handle);
	}
	private native void setAVType(long avType, long handle);
	
	public void setMRMode(MRMode mrMode) {
		setMRMode(mrMode.getVal(), this.handle);
	}
	private native void setMRMode(long mrMode, long handle);
	
	public void setMRKeySize(long mrKeySize) {
		setMRKeySize(mrKeySize, this.handle);
	}
	private native void setMRKeySize(long mrKeySize, long handle);
	
	public void setCQDataSize(long cqDataSize) {
		setCQDataSize(cqDataSize, this.handle);
	}
	private native void setCQDataSize(long mrKeySize, long handle);
	
	public void setCQCnt(long cqCnt) {
		setCQCnt(cqCnt, this.handle);
	}
	private native void setCQCnt(long cqCnt, long handle);
	
	public void setEndpointCnt(long endpointCnt) {
		setEndpointCnt(endpointCnt, this.handle);
	}
	private native void setEndpointCnt(long endpolongCnt, long handle);
	
	public void setTxCtxCnt(long txCtxCnt) {
		setTxCtxCnt(txCtxCnt, this.handle);
	}
	private native void setTxCtxCnt(long txCtxCnt, long handle);
	
	public void setRxCtxCnt(long rxCtxCnt) {
		setRxCtxCnt(rxCtxCnt, this.handle);
	}
	private native void setRxCtxCnt(long rxCtxCnt, long handle);
	
	public void setMaxEpTxCtx(long maxEpTxCtx) {
		setMaxEpTxCtx(maxEpTxCtx, this.handle);
	}
	private native void setMaxEpTxCtx(long maxEpTxCtx, long handle);
	
	public void setMaxEpRxCtx(long maxEpRxCtx) {
		setMaxEpRxCtx(maxEpRxCtx, this.handle);
	}
	private native void setMaxEpRxCtx(long maxEpRxCtx, long handle);
	
	public void setMaxEpStxCtx(long maxEpStxCtx) {
		setMaxEpStxCtx(maxEpStxCtx, this.handle);
	}
	private native void setMaxEpStxCtx(long maxEpStxCtx, long handle);
	
	public void setMaxEpSrxCtx(long maxEpSrxCtx) {
		setMaxEpSrxCtx(maxEpSrxCtx, this.handle);
	}
	private native void setMaxEpSrxCtx(long maxEpSrxCtx, long handle);
}
