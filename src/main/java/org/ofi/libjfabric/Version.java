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

package org.ofi.libjfabric;

public class Version implements Comparable<Version> {
	private int majorVersion;
	private int minorVersion;
	
	public Version(int majorVersion, int minorVersion) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}
	
	@Override
	public String toString() {
		return this.majorVersion + "." + this.minorVersion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!Version.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		Version that = (Version)obj;
		if(this.majorVersion == that.majorVersion && this.minorVersion == that.minorVersion) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Version obj) {
		if(obj == null) {
			throw new NullPointerException();
		}
		Version that = (Version)obj;
		if(this.majorVersion == that.majorVersion) {
			if(this.minorVersion == that.minorVersion) {
				return 0;
			}
			else if(this.minorVersion > that.minorVersion) {
				return 1;
			}
			else { //this.minorVersion < that.minorVersion
				return -1;
			}
		}
		else if(this.majorVersion > that.majorVersion) {
			return 1;
		}
		else { //this.majorVersion < that.majorVersion
			return -1;
		}
	}
	
	public int getMajorVersion() {
		return this.majorVersion;
	}
	public int getMinorVersion() {
		return this.minorVersion;
	}
	
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
}
