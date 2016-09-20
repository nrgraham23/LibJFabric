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

package org.ofi.libjfabric;

import org.ofi.libjfabric.attributes.*;

public class Info {
	private long handle;
	/*NOTE!!! IF THIS CONSTRUCTOR NEEDS TO BE USED, IT NEEDS TO BE FIXED FIRST!  SEE NOTE ON FREE METHOD!*/
	public Info(long caps, long mode, int addrFormat, int srcAddrLen, int destAddrLen, TransmitAttr transmitAttr,
			ReceiveAttr receiveAttr, EndPointAttr endpointAttr, DomainAttr domainAttr, FabricAttr fabricAttr) {
		this.handle = initInfo(caps, mode, addrFormat, srcAddrLen, destAddrLen, transmitAttr.getHandle(), 
				receiveAttr.getHandle(), endpointAttr.getHandle(), domainAttr.getHandle(), fabricAttr.getHandle());
	}
	private native long initInfo(long caps, long mode, int addrFormat, int srcAddrLen, int destAddrLen, long transmitAttrHandle,
			long receiveAttrHandle, long endpointAttrHandle, long domainAttrHandle, long fabricAttrHandle);

	public Info() {
		this.handle = initEmpty();
	}
	private native long initEmpty();

	public Info(long handle) {
		this.handle = handle;
	}

	public void free() { //could check here for a handle of 0 and provide protection against freeing something that already was
		free(this.handle);
		this.handle = 0;
	}
	private native void free(long handle);

	//gets
	public long getHandle() {
		return this.handle;
	}
	public long getCaps() {
		return getCaps(this.handle);
	}
	private native long getCaps(long handle);

	public long getMode() {
		return getMode(this.handle);
	}
	private native long getMode(long handle);

	public int getAddrFormat() {
		return getAddrFormat(this.handle);
	}
	private native int getAddrFormat(long handle);

	public int getSrcAddrLen() {
		return getSrcAddrLen(this.handle);
	}
	private native int getSrcAddrLen(long handle);

	public int getDestAddrLen() {
		return getDestAddrLen(this.handle);
	}
	private native int getDestAddrLen(long handle);

	public TransmitAttr getTransmitAttr() {
		return new TransmitAttr(getTransmitAttr(this.handle));
	}
	private native long getTransmitAttr(long handle);

	public ReceiveAttr getReceiveAttr() {
		return new ReceiveAttr(getReceiveAttr(this.handle));
	}
	private native long getReceiveAttr(long handle);

	public EndPointAttr getEndPointAttr() {
		return new EndPointAttr(getEndPointAttr(this.handle));
	}
	private native long getEndPointAttr(long handle);

	public DomainAttr getDomainAttr() {
		return new DomainAttr(getDomainAttr(this.handle));
	}
	private native long getDomainAttr(long handle);

	public FabricAttr getFabricAttr() {
		return new FabricAttr(getFabricAttr(this.handle));
	}
	private native long getFabricAttr(long handle);

	//sets
	public void setCaps(long caps) {
		setCaps(caps, this.handle);
	}
	private native void setCaps(long caps, long handle);

	public void setMode(long mode) {
		setMode(mode, this.handle);
	}
	private native void setMode(long mode, long handle);

	public void setAddrFormat(int addrFormat) {
		setAddrFormat(addrFormat, this.handle);
	}
	private native void setAddrFormat(int addrFormat, long handle);

	public void setSrcAddrLen(int srcAddrLen) {
		setSrcAddrLen(srcAddrLen, this.handle);
	}
	private native void setSrcAddrLen(int srcAddrLen, long handle);

	public void setDestAddrLen(int destAddrLen) {
		setDestAddrLen(destAddrLen, this.handle);
	}
	private native void setDestAddrLen(int destAddrLen, long handle);

	public void setTransmitAttr(TransmitAttr transmitAttr) {
		setTransmitAttr(transmitAttr.getHandle(), this.handle);
	}
	private native void setTransmitAttr(long transmitAttrHandle, long thisHandle);

	public void setReceiveAttr(ReceiveAttr receiveAttr) {
		setReceiveAttr(receiveAttr.getHandle(), this.handle);
	}
	private native void setReceiveAttr(long receiveAttrHandle, long thisHandle);

	public void setEndPointAttr(EndPointAttr endPointAttr) {
		setEndPointAttr(endPointAttr.getHandle(), this.handle);
	}
	private native void setEndPointAttr(long endPointAttrHandle, long thisHandle);

	public void setDomainAttr(DomainAttr domainAttr) {
		setDomainAttr(domainAttr.getHandle(), this.handle);
	}
	private native void setDomainAttr(long domainAttrHandle, long thisHandle);

	public void setFabricAttr(FabricAttr fabricAttr) {
		setFabricAttr(fabricAttr.getHandle(), this.handle);
	}
	private native void setFabricAttr(long fabricAttrHandle, long thisHandle);

}
