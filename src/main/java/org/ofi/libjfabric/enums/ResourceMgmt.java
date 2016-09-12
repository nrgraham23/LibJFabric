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

package org.ofi.libjfabric.enums;

public enum ResourceMgmt {
	FI_RM_UNSPEC(getUNSPEC()),
	FI_RM_DISABLED(getDISABLED()),
	FI_RM_ENABLED(getENABLED());
	
	private int val;
	
	ResourceMgmt(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static ResourceMgmt getResourceMgmt(int val) {
		if(val == ResourceMgmt.FI_RM_UNSPEC.getVal()) {
			return ResourceMgmt.FI_RM_UNSPEC;
		}
		if(val == ResourceMgmt.FI_RM_DISABLED.getVal()) {
			return ResourceMgmt.FI_RM_DISABLED;
		}
		if(val == ResourceMgmt.FI_RM_ENABLED.getVal()) {
			return ResourceMgmt.FI_RM_ENABLED;
		}
		throw new IllegalArgumentException("Invalid integer value for method getResourceMgmt!");
	}
	
	private static native int getUNSPEC();
	private static native int getDISABLED();
	private static native int getENABLED();
}
