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

import org.ofi.libjfabric.enums.WaitObj;

public class WaitAttr {
	private long handle;
	
	public WaitAttr(WaitObj waitObj, long flags) {
		this.handle = waitAttrInit(waitObj.getVal(), flags);
	}
	private native long waitAttrInit(int waitObj, long flags);
	
	public WaitAttr(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
	
	public WaitObj getWaitObj() {
		return getWaitObj(this.handle);
	}
	private native WaitObj getWaitObj(long handle);
	
	public long getFlags() {
		return getFlags(this.handle);
	}
	private native long getFlags(long handle);
	
	//sets
	public void setWaitObj(WaitObj waitObj) {
		setWaitObj(waitObj.getVal(), this.handle);
	}
	private native void setWaitObj(int waitObj, long handle);
	
	public void setFlags(long flags) {
		setFlags(flags, this.handle);
	}
	private native void setFlags(long flags, long handle);
}
