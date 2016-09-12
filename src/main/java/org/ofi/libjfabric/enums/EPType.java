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

public enum EPType {
	FI_EP_UNSPEC(getUNSPEC()),
	FI_EP_MSG(getMSG()),
	FI_EP_DGRAM(getDGRAM()),
	FI_EP_RDM(getRDM());
	
	private int val;
	
	EPType(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static EPType getEPType(int val) {
		if(val == EPType.FI_EP_UNSPEC.getVal()) {
			return EPType.FI_EP_UNSPEC;
		}
		if(val == EPType.FI_EP_MSG.getVal()) {
			return EPType.FI_EP_MSG;
		}
		if(val == EPType.FI_EP_DGRAM.getVal()) {
			return EPType.FI_EP_DGRAM;
		}
		if(val == EPType.FI_EP_RDM.getVal()) {
			return EPType.FI_EP_RDM;
		}
		throw new IllegalArgumentException("Invalid integer value for method getEPType!");
	}
	
	private static native int getUNSPEC();
	private static native int getMSG();
	private static native int getDGRAM();
	private static native int getRDM();
}
