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

import java.nio.Buffer;
import org.ofi.libjfabric.attributes.AVAttr;
import org.ofi.libjfabric.attributes.CQAttr;
import org.ofi.libjfabric.attributes.CntrAttr;

public class Domain extends FIDescriptor {
	
	public  Domain(long handle) {
		super(handle);
	}
	
	public AddressVector avOpen(AVAttr avAttr, Context context) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public CompletionQueue cqOpen(CQAttr cqAttr, Context context) {
		return new CompletionQueue(cqOpen(this.handle, cqAttr.getHandle(), context.getHandle()));
	}
	private native long cqOpen(long handle, long cqAttrHandle, long contextHandle);
	
	public CompletionQueue cqOpen(CQAttr cqAttr) {
		return new CompletionQueue(cqOpen2(this.handle, cqAttr.getHandle()));
	}
	private native long cqOpen2(long handle, long cqAttrHandle);
	
	public EndPoint epOpen(Info info, Context context) {
		return new EndPoint(epOpen(this.handle, info.getHandle(), context.getHandle()));
	}
	private native long epOpen(long domHandle, long infoHandle, long contextHandle);
	
	public EndPoint epOpen(Info info) {
		return new EndPoint(epOpen2(this.handle, info.getHandle()));
	}
	private native long epOpen2(long domHandle, long infoHandle);
	
	public ScalableEP scalableEPOpen(Info info, Context context) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public Counter cntrOpen(CntrAttr cntrAttr, Context context) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	//NOTE: should be able to duplicate offset with the slice method of ByteBuffer
	public MemoryRegion mrRegister(Buffer buf, long access, long offset, long requestedKey, long flags, Context context) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public MemoryRegion mrRegister(Buffer buf, long access, long offset, long requestedKey, long flags) {
			throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public MemoryRegion mrRegister(Buffer buf, long access, long offset, long flags) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public MemoryRegion mrRegister(Buffer buf, long access, long requestedKey) {
		return new MemoryRegion(mrRegister(this.handle, buf, buf.capacity(), access, requestedKey));
	}
	private native long mrRegister(long domainHandle, Buffer buf, long length, long access, long requestedKey);
}
