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

public enum Threading {
	FI_THREAD_UNSPEC(getUNSPEC()),
	FI_THREAD_SAFE(getSAFE()),
	FI_THREAD_FID(getFID()),
	FI_THREAD_DOMAIN(getDOMAIN()),
	FI_THREAD_COMPLETION(getCOMPLETION()),
	FI_THREAD_ENDPOINT(getENDPOINT());
	
	private int val;
	
	Threading(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static Threading getThreading(int val) {
		if(val == Threading.FI_THREAD_UNSPEC.getVal()) {
			return Threading.FI_THREAD_UNSPEC;
		}
		if(val == Threading.FI_THREAD_SAFE.getVal()) {
			return Threading.FI_THREAD_SAFE;
		}
		if(val == Threading.FI_THREAD_FID.getVal()) {
			return Threading.FI_THREAD_FID;
		}
		if(val == Threading.FI_THREAD_DOMAIN.getVal()) {
			return Threading.FI_THREAD_DOMAIN;
		}
		if(val == Threading.FI_THREAD_COMPLETION.getVal()) {
			return Threading.FI_THREAD_COMPLETION;
		}
		if(val == Threading.FI_THREAD_ENDPOINT.getVal()) {
			return Threading.FI_THREAD_ENDPOINT;
		}
		throw new IllegalArgumentException("Invalid integer value for method getThreading!");
	}
	
	private static native int getUNSPEC();
	private static native int getSAFE();
	private static native int getFID();
	private static native int getDOMAIN();
	private static native int getCOMPLETION();
	private static native int getENDPOINT();
}
