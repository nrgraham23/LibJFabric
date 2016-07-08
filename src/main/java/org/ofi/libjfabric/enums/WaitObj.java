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

package org.ofi.libjfabric.enums;

public enum WaitObj {
	WAIT_NONE(getNONE()),
	WAIT_UNSPEC(getUNSPEC()),
	WAIT_SET(getSET()),
	WAIT_FD(getFD()),
	WAIT_MUTEX_COND(getMUTEXCOND()),
	WAIT_CRITSEC_COND(getCRITSECCOND());

	private int val;

	WaitObj(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	//private because it should only be used from the JNI code
	private static WaitObj getWaitObj(int val) {
		if(val == WaitObj.WAIT_NONE.getVal()) {
			return WaitObj.WAIT_NONE;
		}
		if(val == WaitObj.WAIT_UNSPEC.getVal()) {
			return WaitObj.WAIT_UNSPEC;
		}
		if(val == WaitObj.WAIT_SET.getVal()) {
			return WaitObj.WAIT_SET;
		}
		if(val == WaitObj.WAIT_FD.getVal()) {
			return WaitObj.WAIT_FD;
		}
		if(val == WaitObj.WAIT_MUTEX_COND.getVal()) {
			return WaitObj.WAIT_MUTEX_COND;
		}
		if(val == WaitObj.WAIT_CRITSEC_COND.getVal()) {
			return WaitObj.WAIT_CRITSEC_COND;
		}
		throw new IllegalArgumentException("Invalid integer value for method getWaitObj!");
	}

	private static native int getNONE();
	private static native int getUNSPEC();
	private static native int getSET();
	private static native int getFD();
	private static native int getMUTEXCOND();
	private static native int getCRITSECCOND();
}
