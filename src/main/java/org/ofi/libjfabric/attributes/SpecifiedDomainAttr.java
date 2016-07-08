/*
 * Copyright (c) 2015 Los Alamos Nat. Security, LLC. All rights reserved.
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
