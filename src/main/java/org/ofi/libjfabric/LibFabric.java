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

public class LibFabric {
	
	public static void loadVerbose() {
		try {
			System.loadLibrary("jfab_native");
			init();
			registerNativeCleanup();
		} catch (UnsatisfiedLinkError e) {
			throw new RuntimeException("Could not load the libfabric native library");
		}
	}
	
	public static boolean load() {
		try {
			System.loadLibrary("jfab_native");
			init();
			registerNativeCleanup();
			return true;
		} catch (UnsatisfiedLinkError e) {
			throw new RuntimeException("Could not load the libfabric native library");
		}
	}
	
	private static native void init();
	
	/* This method adds a shutdown hook to the JVM.
	 * The thread defined below will run as the JVM is
	 * shutting down.  It cleans up any memory that was
	 * allocated from C for the users so they do not have
	 * to free the memory manually.
	 */
	private static void registerNativeCleanup() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				nativeCleanup();
			}
		});
	}
	
	private static void nativeCleanup() {
		deleteCachedVars();
	}
	
	private static native void deleteCachedVars();

	public static Info[] getInfo(Version version, String node, String service, long flags, Info hints) {
		long infoHandleArray[] = null;
		
		if(hints != null) {
			infoHandleArray = getInfoJNI(version.getMajorVersion(), version.getMinorVersion(), node, service, flags, hints.getHandle());
		} else {
			infoHandleArray = getInfoJNI(version.getMajorVersion(), version.getMinorVersion(), node, service, flags, 0);
		}
		if(infoHandleArray == null) {
			return null; //should probably handle this better
		}
		
		Info infoArray[] = new Info[infoHandleArray.length];
		
		for(int i = 0; i < infoHandleArray.length; i++) {
			infoArray[i] = new Info(infoHandleArray[i]);
		}
		
		return infoArray;
	}
	
	private static native long[] getInfoJNI(int majorVersion, int minorVersion, String node, String service, long flags, long hints);
}
