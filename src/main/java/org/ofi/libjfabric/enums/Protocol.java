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

public enum Protocol {
	FI_PROTO_UNSPEC(getUNSPEC()),
	FI_PROTO_RDMA_CM_IB_RC(getRDMA()),
	FI_PROTO_IWARP(getIWARP()),
	FI_PROTO_IB_UD(getIBUD()),
	FI_PROTO_PSMX(getPSMX()),
	FI_PROTO_UDP(getUDP()),
	FI_PROTO_SOCK_TCP(getSOCKTCP()),
	FI_PROTO_MXM(getMXM()),
	FI_PROTO_IWARP_RDM(getIWARPRDM()),
	FI_PROTO_IB_RDM(getIBRDM()),
	FI_PROTO_GNI(getGNI()),
	FI_PROTO_RXM(getRXM()),
	FI_PROTO_RXD(getRXD());
	
	private int val;
	
	Protocol(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
	
	//private because it should only be used from the JNI code
	private static Protocol getProtocol(int val) {
		if(val == Protocol.FI_PROTO_UNSPEC.getVal()) {
			return Protocol.FI_PROTO_UNSPEC;
		}
		if(val == Protocol.FI_PROTO_RDMA_CM_IB_RC.getVal()) {
			return Protocol.FI_PROTO_RDMA_CM_IB_RC;
		}
		if(val == Protocol.FI_PROTO_IWARP.getVal()) {
			return Protocol.FI_PROTO_IWARP;
		}
		if(val == Protocol.FI_PROTO_IB_UD.getVal()) {
			return Protocol.FI_PROTO_IB_UD;
		}
		if(val == Protocol.FI_PROTO_PSMX.getVal()) {
			return Protocol.FI_PROTO_PSMX;
		}
		if(val == Protocol.FI_PROTO_UDP.getVal()) {
			return Protocol.FI_PROTO_UDP;
		}
		if(val == Protocol.FI_PROTO_SOCK_TCP.getVal()) {
			return Protocol.FI_PROTO_SOCK_TCP;
		}
		if(val == Protocol.FI_PROTO_MXM.getVal()) {
			return Protocol.FI_PROTO_MXM;
		}
		if(val == Protocol.FI_PROTO_IWARP_RDM.getVal()) {
			return Protocol.FI_PROTO_IWARP_RDM;
		}
		if(val == Protocol.FI_PROTO_IB_RDM.getVal()) {
			return Protocol.FI_PROTO_IB_RDM;
		}
		if(val == Protocol.FI_PROTO_GNI.getVal()) {
			return Protocol.FI_PROTO_GNI;
		}
		if(val == Protocol.FI_PROTO_RXM.getVal()) {
			return Protocol.FI_PROTO_RXM;
		}
		if(val == Protocol.FI_PROTO_RXD.getVal()) {
			return Protocol.FI_PROTO_RXD;
		}
		throw new IllegalArgumentException("Invalid integer value for method getProtocol: " + val);
	}
	
	private static native int getUNSPEC();
	private static native int getRDMA();
	private static native int getIWARP();
	private static native int getIBUD();
	private static native int getPSMX();
	private static native int getUDP();
	private static native int getSOCKTCP();
	private static native int getMXM();
	private static native int getIWARPRDM();
	private static native int getIBRDM();
	private static native int getGNI();
	private static native int getRXM();
	private static native int getRXD();
}
