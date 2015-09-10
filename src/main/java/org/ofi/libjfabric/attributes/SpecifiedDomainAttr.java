package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.Domain;
import org.ofi.libjfabric.enums.AVType;
import org.ofi.libjfabric.enums.MRMode;
import org.ofi.libjfabric.enums.Progress;
import org.ofi.libjfabric.enums.ResourceMgmt;
import org.ofi.libjfabric.enums.Threading;

public class SpecifiedDomainAttr extends DomainAttr {
	
	public SpecifiedDomainAttr(Domain domain, String name, Threading threading, Progress cntrlProgress, Progress dataProgress, 
			ResourceMgmt resourceMgmt, AVType avType, MRMode mrMode, int mrKeySize, int cqDataSize, int cqCnt, 
			int endpointCount, int txCtxCnt, int rxCtxCnt, int maxEpTxCtx, int maxEpRxCtx, int maxEpStxCtx, int maxEpSrxCtx) {
		super(name, threading, cntrlProgress, dataProgress, resourceMgmt, avType, mrMode, 
				mrKeySize, cqDataSize, cqCnt, endpointCount, txCtxCnt, rxCtxCnt, maxEpTxCtx, maxEpRxCtx, 
				maxEpStxCtx, maxEpSrxCtx);
		setDomain(domain.getHandle(), this.handle);
	}
	
	public SpecifiedDomainAttr(Domain domain) {
		this.handle = initWithDomain(domain.getHandle());
	}
	private native long initWithDomain(long domainHandle);
	
	//get
	public Domain getDomain() {
		return new Domain(getDomain(this.handle));
	}
	private native long getDomain(long handle);
	
	//set
	public void setDomain(Domain domain) {
		setDomain(domain.getHandle(), this.handle);
	}
	private native void setDomain(long domainHandle, long domainAttrHandle);
}
