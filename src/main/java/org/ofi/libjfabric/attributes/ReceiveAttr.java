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

public class ReceiveAttr {
	private long handle;

	public ReceiveAttr(long caps, long mode, long opFlags, long msgOrder, long compOrder, int totalBufferedRecv, int size, int iovLimit) {
		this.handle = initReceiveAttr(caps, mode, opFlags, msgOrder, compOrder, totalBufferedRecv, size, iovLimit);
	}
	private native long initReceiveAttr(long caps, long mode, long opFlags, long msgOrder, long compOrder, 
			int totalBufferedRecv, int size, int iovLimit);

	public ReceiveAttr() {
		this.handle = initEmpty();
	}
	private native long initEmpty();
	
	public ReceiveAttr(long handle) {
		this.handle = handle;
	}
	
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
	
	public long getOpFlags() {
		return getOpFlags(this.handle);
	}
	private native long getOpFlags(long handle);
	
	public long getMsgOrder() {
		return getMsgOrder(this.handle);
	}
	private native long getMsgOrder(long handle);
	
	public long getCompOrder() {
		return getCompOrder(this.handle);
	}
	private native long getCompOrder(long handle);
	
	public int getTotalBufferedRecv() {
		return getTotalBufferedRecv(this.handle);
	}
	private native int getTotalBufferedRecv(long handle);
	
	public int getSize() {
		return getSize(this.handle);
	}
	private native int getSize(long handle);
	
	public int getIovLimit() {
		return getIovLimit(this.handle);
	}
	private native int getIovLimit(long handle);

	//sets
	public void setCaps(long caps) {
		setCaps(caps, this.handle);
	}
	private native void setCaps(long caps, long handle);
	
	public void setMode(long mode) {
		setMode(mode, this.handle);
	}
	private native void setMode(long mode, long handle);
	
	public void setOpFlags(long  opFlags) {
		setOpFlags(opFlags, this.handle);
	}
	private native void setOpFlags(long opFlags, long handle);
	
	public void setMsgOrder(long msgOrder) {
		setMsgOrder(msgOrder, this.handle);
	}
	private native void setMsgOrder(long msgOrder, long handle);
	
	public void setCompOrder(long compOrder) {
		setCompOrder(compOrder, this.handle);
	}
	private native void setCompOrder(long compOrder, long handle);
	
	public void setTotalBufferedRecv(int totalBufferedRecv) {
		setTotalBufferedRecv(totalBufferedRecv, this.handle);
	}
	private native void setTotalBufferedRecv(int totalBufferedRecv, long handle);
	
	public void setSize(int size) {
		setSize(size, this.handle);
	}
	private native void setSize(int size, long handle);
	
	public void setIovLimit(int iovLimit) {
		setIovLimit(iovLimit, this.handle);
	}
	private native void setIovLimit(int iovLimit, long handle);
}
