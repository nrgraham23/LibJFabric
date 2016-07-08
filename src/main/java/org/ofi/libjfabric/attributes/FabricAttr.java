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

import org.ofi.libjfabric.Version;

public class FabricAttr {
	protected long handle;

	public FabricAttr(String name, String providerName, Version providerVersion) {
		this.handle = initFabricAttr(name, providerName, providerVersion.getMajorVersion(), providerVersion.getMinorVersion());
	}
	private native long initFabricAttr(String name, String providerName, int majorVersion, int minorVersion);
	
	public FabricAttr() {
		this.handle = initEmpty();
	}
	private native long initEmpty();

	public FabricAttr(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
	
	public String getName() {
		return getName(this.handle);
	}
	private native String getName(long handle);
	
	public String getProviderName() {
		return getProviderName(this.handle);
	}
	private native String getProviderName(long handle);
	
	public Version getProviderVersion() {
		int majorVersion = getMajorProviderVersion(this.handle);
		int minorVersion = getMinorProviderVersion(this.handle);
		
		return new Version(majorVersion, minorVersion);
	}
	private native int getMajorProviderVersion(long handle);
	private native int getMinorProviderVersion(long handle);

	//sets
	public void setName(String name) {
		setName(name, this.handle);
	}
	private native void setName(String name, long handle);
	
	public void setProviderName(String providerName) {
		setProviderName(providerName, this.handle);
	}
	private native void setProviderName(String providerName, long handle);
	
	public void setProviderVersion(Version providerVersion) {
		setProviderVersion(providerVersion.getMajorVersion(), providerVersion.getMinorVersion(), this.handle);
	}
	private native void setProviderVersion(int majorVersion, int minorVersion, long handle);
}
