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

import java.nio.ByteBuffer;

public class EndPoint extends EndPointSharedOps {
	
	public EndPoint(long handle) {
		super(handle);
	}
	
	public void recv(ByteBuffer buffer, long mrDesc, long srcAddress, Context context) { //TODO: mrDesc would be replaced with something cleaner when the todo in MemoryRegion.java is fixed
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void recv(ByteBuffer buffer, long mrDesc, long srcAddress) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void recv(ByteBuffer buffer, long srcAddress, Context context) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void recv(ByteBuffer buffer, long srcAddress) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void recv(ByteBuffer buffer) {
		recv5(this.getHandle(), buffer,buffer.capacity());
	}
	private native void recv5(long handle, ByteBuffer buffer, int length);
	
	public void send(ByteBuffer buffer, long mrDesc, long destAddress, Context context) {
		send(this.handle, buffer, buffer.capacity(), mrDesc, destAddress, context.getHandle());
	}
	private native void send(long thisHandle, ByteBuffer buffer, int length, long mrDesc, long destAddress, long contextHandle);
	
	public void send(ByteBuffer buffer, long mrDesc, long destAddress) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void send(ByteBuffer buffer, long destAddress, Context context) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void send(ByteBuffer buffer, long destAddress) {
		send4(this.handle, buffer, buffer.capacity(), destAddress);
	}
	private native void send4(long handle, ByteBuffer buffer, int length, long destAddress);
	
	public void enable() {
		enable(this.handle);
	}
	private native void enable(long handle);
	
	public void accept() {
		accept(this.handle);
	}
	private native void accept(long handle);
	
	public void connect(String addr) { //would want a version that had the option to enter parameters (see libfabric definition)
		connect(this.handle, addr);
	}
	private native void connect(long handle, String addr);
	
	public void inject(ByteBuffer buffer, long destAddr) {
		inject(this.handle, buffer, buffer.capacity(), destAddr);
	}
	private native void inject(long epHandle, ByteBuffer buffer, int length, long destAddr);
}
