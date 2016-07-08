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

import org.ofi.libjfabric.Wait;
import org.ofi.libjfabric.enums.WaitObj;

public class EventQueueAttr {
	private long handle;

	public EventQueueAttr(int size, long flags, WaitObj waitObj, int signalingVector, Wait wait) {
		this.handle = eventQueueAttrInit(size, flags, waitObj.getVal(), signalingVector, wait.getHandle());
	}
	private native long eventQueueAttrInit(int size, long flags, int waitObjVal, int signallingVector, long waitHandle);

	public EventQueueAttr(long handle) {
		this.handle = handle;
	}

	//gets
	public long getHandle() {
		return this.handle;
	}
	public int getSize() {
		return getSize(this.handle);
	}
	private native int getSize(long handle);

	public long getFlags() {
		return getFlags(this.handle);
	}
	private native long getFlags(long handle);

	public WaitObj getWaitObj() {
		return getWaitObj(this.handle);
	}
	private native WaitObj getWaitObj(long handle);

	public int getSignalingVector() {
		return getSignalingVector(this.handle);
	}
	private native int getSignalingVector(long handle);

	public Wait getWait() {
		return new Wait(getWait(this.handle));
	}
	private native long getWait(long handle);

	//sets
	public void setSize(int size) {
		setSize(size, this.handle);
	}
	private native void setSize(int size, long handle);

	public void setFlags(long flags) {
		setFlags(flags, this.handle);
	}
	private native void setFlags(long flags, long handle);

	public void setWaitObj(WaitObj waitObj) {
		setWaitObj(waitObj.getVal(), handle);
	}
	private native void setWaitObj(int waitObjVal, long handle);

	public void setSignalingVector(int signalingVector) {
		setSignalingVector(signalingVector, this.handle);
	}
	private native void setSignalingVector(int signalingVector, long handle);

	public void setWait(Wait wait) {
		setWait(wait.getHandle(), this.handle);
	}
	private native void setWait(long waitHandle, long handle);
}
