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
import org.ofi.libjfabric.enums.CQFormat;
import org.ofi.libjfabric.enums.CQWaitCond;
import org.ofi.libjfabric.enums.WaitObj;

public class CQAttr {
	private long handle;
	
	public CQAttr(long size, long flags, CQFormat format, WaitObj waitObj, int signalingVector, CQWaitCond cqWaitCond, Wait wait) 
	{
		this.handle = initCQAttr(size, flags, format.getVal(), waitObj.getVal(), signalingVector, cqWaitCond.getVal(), wait.getHandle());
	}
	
	private native long initCQAttr(long size, long flags, int format, int waitObj, int signalingVector, int cqWaitCond, long waitHandle);
	
	public CQAttr() {
		this.handle = initEmpty();
	}
	private native long initEmpty();
	
	public CQAttr(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
	
	public long getSize() {
		return getSize(this.handle);
	}
	private native long getSize(long handle);
	
	public long getFlags() {
		return getFlags(this.handle);
	}
	private native long getFlags(long handle);
	
	public CQFormat getCQFormat() {
		return getCQFormat(this.handle);
	}
	private native CQFormat getCQFormat(long handle);
	
	public WaitObj getWaitObj() {
		return getWaitObj(this.handle);
	}
	private native WaitObj getWaitObj(long handle);
	
	public int getSignalingVector() {
		return getSignalingVector(this.handle);
	}
	private native int getSignalingVector(long handle);
	
	public CQWaitCond getCQWaitCond() {
		return getCQWaitCond(this.handle);
	}
	private native CQWaitCond getCQWaitCond(long handle);
	
	public Wait getWait() {
		return new Wait(this.handle);
	}
	private native long getWait(long handle);
	
	//sets
	public void setSize(long size) {
		setSize(this.handle, size);
	}
	private native void setSize(long handle, long size);
	
	public void setFlags(long flags) {
		 setFlags(this.handle, flags);
	}
	private native void setFlags(long handle, long flags);
	
	public void setCQFormat(CQFormat cqFormat) {
		setCQFormat(this.handle, cqFormat.getVal());
	}
	private native void setCQFormat(long handle, int cqFormat);
	
	public void setWaitObj(WaitObj waitObj) {
		setWaitObj(this.handle, waitObj.getVal());
	}
	private native void setWaitObj(long handle, int waitObj);
	
	public void setSignalingVector(int signalingVector) {
		setSignalingVector(this.handle, signalingVector);
	}
	private native void setSignalingVector(long handle, int signalingVector);
	
	public void setCQWaitCond(CQWaitCond cqWaitCond) {
		setCQWaitCond(this.handle, cqWaitCond.getVal());
	}
	private native void setCQWaitCond(long handle, int cqWaitCond);
	
	public void setWait(Wait wait) {
		setWait(this.handle, wait.getHandle());
	}
	private native void setWait(long handle, long waitHandle);
}
