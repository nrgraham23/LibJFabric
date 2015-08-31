package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.Domain;
import org.ofi.libjfabric.enums.*;

public class DomainAttr {
	private Domain domain;
	private String name;
	private Threading threading;
	private Progress cntrlProgress;
	private Progress dataProgress;
	private ResourceMgmt resourceMgmt;
	private AVType avType;
	private MRMode mrMode;
	private int mrKeySize;
	private int cqDataSize;
	private int cqCnt;
	private int endpointCount;
	private int txCtxCnt;
	private int rxCtxCnt;
	private int maxEpTxCtx;
	private int maxEpRxCtx;
	private int maxEpStxCtx;
	private int maxEpSrxCtx;
	
	public DomainAttr(Domain domain, String name, Threading threading, Progress cntrlProgress, Progress dataProgress, 
			ResourceMgmt resourceMgmt, AVType avType, MRMode mrMode, int mrKeySize, int cqDataSize, int cqCnt, 
			int endpointCount, int txCtxCnt, int rxCtxCnt, int maxEpTxCtx, int maxEpRxCtx, int maxEpStxCtx, int maxEpSrxCtx) {
		this.domain = domain;
		this.name = name;
		this.threading = threading;
		this.cntrlProgress = cntrlProgress;
		this.dataProgress = dataProgress;
		this.resourceMgmt = resourceMgmt;
		this.avType = avType;
		this.mrMode = mrMode;
		this.mrKeySize = mrKeySize;
		this.cqDataSize = cqDataSize;
		this.cqCnt = cqCnt;
		this.endpointCount = endpointCount;
		this.txCtxCnt = txCtxCnt;
		this.rxCtxCnt = rxCtxCnt;
		this.maxEpTxCtx = maxEpTxCtx;
		this.maxEpRxCtx = maxEpRxCtx;
		this.maxEpStxCtx = maxEpStxCtx;
		this.maxEpSrxCtx = maxEpSrxCtx;
	}
	
	public DomainAttr() {
		this.threading = Threading.UNSPEC;
		this.cntrlProgress = Progress.UNSPEC;
		this.dataProgress = Progress.UNSPEC;
		this.resourceMgmt = ResourceMgmt.UNSPEC;
		this.avType = AVType.UNSPEC;
		this.mrMode = MRMode.UNSPEC;
	}
	
	//gets
	public Domain getDomain() {
		return this.domain;
	}
	public String getName() {
		return this.name;
	}
	public Threading getThreading() {
		return this.threading;
	}
	public Progress getCntrlProgress() {
		return this.cntrlProgress;
	}
	public Progress getDataProgress() {
		return this.dataProgress;
	}
	public ResourceMgmt getResourceMgmt() {
		return this.resourceMgmt;
	}
	public AVType getAvType() {
		return this.avType;
	}
	public MRMode getMRMode() {
		return this.mrMode;
	}
	public int getMrKeySize() {
		return this.mrKeySize;
	}
	public int getCqDataSize() {
		return this.cqDataSize;
	}
	public int getCqCnt() {
		return this.cqCnt;
	}
	public int getEndpointCount() {
		return this.endpointCount;
	}
	public int getTxCtxCnt() {
		return this.txCtxCnt;
	}
	public int getRxCtxCnt() {
		return this.rxCtxCnt;
	}
	public int getMaxEpTxCtx() {
		return this.maxEpTxCtx;
	}
	public int getMaxEpRxCtx() {
		return this.maxEpRxCtx;
	}
	public int getMaxEpStxCtx() {
		return this.maxEpStxCtx;
	}
	public int getMaxEpSrxCtx() {
		return this.maxEpSrxCtx;
	}
	
	//sets
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setThreading(Threading threading) {
		this.threading = threading;
	}
	public void setCntrlProgress(Progress cntrlProgress) {
		this.cntrlProgress = cntrlProgress;
	}
	public void setDataProgress(Progress dataProgress) {
		this.dataProgress = dataProgress;
	}
	public void setResourceMgmt(ResourceMgmt resourceMgmt) {
		this.resourceMgmt = resourceMgmt;
	}
	public void setAvType(AVType avType) {
		this.avType = avType;
	}
	public void setMRMode(MRMode mrMode) {
		this.mrMode = mrMode;
	}
	public void setMrKeySize(int mrKeySize) {
		this.mrKeySize = mrKeySize;
	}
	public void setCqDataSize(int cqDataSize) {
		this.cqDataSize = cqDataSize;
	}
	public void setCqCnt(int cqCnt) {
		this.cqCnt = cqCnt;
	}
	public void setEndpointCount(int endpointCount) {
		this.endpointCount = endpointCount;
	}
	public void setTxCtxCnt(int txCtxCnt) {
		this.txCtxCnt = txCtxCnt;
	}
	public void setRxCtxCnt(int rxCtxCnt) {
		this.rxCtxCnt = rxCtxCnt;
	}
	public void setMaxEpTxCtx(int mapEpTxCtx) {
		this.maxEpTxCtx = mapEpTxCtx;
	}
	public void setMaxEpRxCtx(int mapEpRxCtx) {
		this.maxEpRxCtx = mapEpRxCtx;
	}
	public void setMaxEpStxCtx(int maxEpStxCtx) {
		this.maxEpStxCtx = maxEpStxCtx;
	}
	public void setMaxEpSrxCtx(int maxEpSrxCtx) {
		this.maxEpSrxCtx = maxEpSrxCtx;
	}
}
