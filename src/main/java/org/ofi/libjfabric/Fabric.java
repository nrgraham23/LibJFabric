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

package org.ofi.libjfabric;

import org.ofi.libjfabric.attributes.EventQueueAttr;
import org.ofi.libjfabric.attributes.FabricAttr;
import org.ofi.libjfabric.attributes.WaitAttr;

public class Fabric extends FIDescriptor {
	FabricAttr fabricAttr;
	Context context;
	
	public Fabric(FabricAttr fabricAttr, Context context) {
		this.fabricAttr = fabricAttr;
		this.context = context;
		this.handle = initFabric(fabricAttr.getHandle(), context.getHandle());
	}
	private native long initFabric(long fabricAttrHandle, long contextHandle);
	
	public Fabric(FabricAttr fabricAttr) {
		this.fabricAttr = fabricAttr;
		this.handle = initFabric2(fabricAttr.getHandle());
	}
	private native long initFabric2(long fabricAttrHandle);
	
	public Fabric(long handle) {
		super(handle);
	}
	
	public Domain createDomain(Info info, Context context) {
		return new Domain(createDomainJNI(this.handle, info.getHandle(), context.getHandle()));
	}
	private native long createDomainJNI(long fabricHandle, long infoHandle, long contextHandle);
	
	public PassiveEndPoint createPassiveEndPoint(Info info, Context context) {
		return new PassiveEndPoint(createPassiveEP(this.handle, info.getHandle(), context.getHandle()));
	}
	private native long createPassiveEP(long fabricHandle, long infoHandle, long contextHandle);
	
	public EventQueue eventQueueOpen(EventQueueAttr eventQueueAttr, Context context) {
		return new EventQueue(eventQueueOpen(this.handle, eventQueueAttr.getHandle(), context.getHandle()));
	}
	private native long eventQueueOpen(long fabricHandle, long eventQueueAttrHandle, long contextHandle);
	
	public Wait waitOpen(WaitAttr waitAttr) {
		return new Wait(waitOpen(this.handle, waitAttr.getHandle()));
	}
	private native long waitOpen(long fabricHandle, long waitAttrHandle);
	
	/* for trywait, can only send event queues, completion queues, counters, and wait sets
	/  all underlying wait objects must be of the same type, but there is currently no
	/ err checking in place to ensure this. */
	public boolean tryWait(TryWaitable[] tryWaitables, int count) {
		long[] waitablesHandles = new long[tryWaitables.length];
		for(int i = 0; i < tryWaitables.length; i++) {
			waitablesHandles[i] = tryWaitables[i].getHandle();
		}
		return tryWait(this.handle, waitablesHandles, count);
	}
	private native boolean tryWait(long fabricHandle, long[] waitablesHandles, int count);
}
