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

package org.ofi.libjfabric;

import org.ofi.libjfabric.enums.EQEvent;

/* This class is setup as a read only class.  If the user should be able to modify
 * or use an EQCMEntry object to interact directly with libfabric, the design will
 * need to be different.  Specifically, the object should be stored on the C side
 * like most of the rest of the objects in these bindings.
 */
public class EQCMEntry extends EventEntry {
	private PassiveEndPoint endPoint;
	private Info info;
	private byte[] data;
	
	//private because it is only called from the C code.  A user is not allowed to make one
	private EQCMEntry(EQEvent event, long epHandle, long infoHandle, byte[] data) {
		super(event);
		this.endPoint = new PassiveEndPoint(epHandle);
		this.info = new Info(infoHandle);
		this.data = data;
	}
	
	//gets
	public EndPoint getEndPoint() { //TODO: This will need to be able to return either a pep or regular ep in a more complete implementation
		return this.endPoint;
	}
	
	public Info getInfo() {
		return this.info;
	}
	
	public byte[] getData() {
		return this.data;
	}
}
