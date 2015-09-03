package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.enums.*;

public class DomainAttr {
	private long handle;
	
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
		
	}
	
	//gets
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
		setThreading(threading, this.handle);
	}
	private native void setThreading(Threading threading, long handle);
	
	public void setCntrlProgress(Progress cntrlProgress) {
		setCntrlProgress(cntrlProgress, this.handle);
	}
	private native void setCntrlProgress(Progress cntrlProgress, long handle);
	
	public void setDataProgress(Progress dataProgress) {
		setDataProgress(dataProgress, this.handle);
	}
	private native void setDataProgress(Progress dataProgress, long handle);
	
	public void setResourceMgmt(ResourceMgmt resourceMgmt) {
		setResourceMgmt(resourceMgmt, this.handle);
	}
	private native void setResourceMgmt(ResourceMgmt resourceMgmt, long handle);
	
	public void setAVType(AVType avType) {
		setAVType(avType, this.handle);
	}
	private native void setAVType(AVType avType, long handle);
	
	public void setMRMode(MRMode mrMode) {
		setMRMode(mrMode, this.handle);
	}
	private native void setMRMode(MRMode mrMode, long handle);
	
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
