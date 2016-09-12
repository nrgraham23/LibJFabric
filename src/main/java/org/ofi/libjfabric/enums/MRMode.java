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

public enum MRMode {
	FI_MR_MODE_UNSPEC(getUNSPEC()),
	FI_MR_MODE_BASIC(getBASIC()),
	FI_MR_MODE_SCALABLE(getSCALABLE());
	
	private int val;
	
	MRMode(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static MRMode getMRMode(int val) {
		if(val == MRMode.FI_MR_MODE_UNSPEC.getVal()) {
			return MRMode.FI_MR_MODE_UNSPEC;
		}
		if(val == MRMode.FI_MR_MODE_BASIC.getVal()) {
			return MRMode.FI_MR_MODE_BASIC;
		}
		if(val == MRMode.FI_MR_MODE_SCALABLE.getVal()) {
			return MRMode.FI_MR_MODE_SCALABLE;
		}
		throw new IllegalArgumentException("Invalid integer value for method getMRMode!");
	}
	
	private static native int getUNSPEC();
	private static native int getBASIC();
	private static native int getSCALABLE();
}
