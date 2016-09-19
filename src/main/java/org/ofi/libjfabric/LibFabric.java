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

import java.nio.*;

public class LibFabric {
	private static final ByteOrder nativeOrder = ByteOrder.nativeOrder();
	public static final long FI_CONTEXT;
	public static final long FI_LOCAL_MR;
	public static final long FI_SOURCE;
	public static final long FI_MSG;

	static {
		System.loadLibrary("jfab_native");
		
		Constant c = new Constant();
		
		FI_CONTEXT = c.FI_CONTEXT;
		FI_LOCAL_MR = c.FI_LOCAL_MR;
		FI_SOURCE = c.FI_SOURCE;
		FI_MSG = c.FI_MSG;
	}
	
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
		
		infoHandleArray = getInfoJNI(version.getMajorVersion(), version.getMinorVersion(), node, service, flags, hints.getHandle());
		
		if(infoHandleArray == null) {
			return null; //should probably handle this better
		}

		Info infoArray[] = new Info[infoHandleArray.length];

		for(int i = 0; i < infoHandleArray.length; i++) {
			infoArray[i] = new Info(infoHandleArray[i]);
		}

		return infoArray;
	}
	
	public static Info[] getInfo(Version version, String node, String service, long flags) {
		long infoHandleArray[] = null;

		infoHandleArray = getInfoJNI(version.getMajorVersion(), version.getMinorVersion(), node, service, flags, 0);

		if(infoHandleArray == null) {
			return null; //should probably handle this better
		}

		Info infoArray[] = new Info[infoHandleArray.length];

		for(int i = 0; i < infoHandleArray.length; i++) {
			infoArray[i] = new Info(infoHandleArray[i]);
		}

		return infoArray;
	}
	
	public static Info[] getInfo(Version version, long flags, Info hints) {
		long infoHandleArray[] = null;

		infoHandleArray = getInfoJNI2(version.getMajorVersion(), version.getMinorVersion(), flags, 0);

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
	private static native long[] getInfoJNI2(int majorVersion, int minorVersion, long flags, long hints);

	/**
	 * Allocates a new direct byte buffer.
	 * @param capacity The new buffer's capacity, in bytes
	 * @return The new byte buffer
	 */
	public static ByteBuffer newByteBuffer(int capacity) {
		ByteBuffer buf = ByteBuffer.allocateDirect(capacity);
		buf.order(nativeOrder);
		return buf;
	}

	/**
	 * Allocates a new direct char buffer.
	 * @param capacity The new buffer's capacity, in chars
	 * @return The new char buffer
	 */
	public static CharBuffer newCharBuffer(int capacity) {
		assert capacity <= Integer.MAX_VALUE / 2;
		ByteBuffer buf = ByteBuffer.allocateDirect(capacity * 2);
		buf.order(nativeOrder);
		return buf.asCharBuffer();
	}

	/**
	 * Allocates a new direct short buffer.
	 * @param capacity The new buffer's capacity, in shorts
	 * @return The new short buffer
	 */
	public static ShortBuffer newShortBuffer(int capacity) {
		assert capacity <= Integer.MAX_VALUE / 2;
		ByteBuffer buf = ByteBuffer.allocateDirect(capacity * 2);
		buf.order(nativeOrder);
		return buf.asShortBuffer();
	}

	/**
	 * Allocates a new direct int buffer.
	 * @param capacity The new buffer's capacity, in ints
	 * @return The new int buffer
	 */
	public static IntBuffer newIntBuffer(int capacity) {
		assert capacity <= Integer.MAX_VALUE / 4;
		ByteBuffer buf = ByteBuffer.allocateDirect(capacity * 4);
		buf.order(nativeOrder);
		return buf.asIntBuffer();
	}

	/**
	 * Allocates a new direct long buffer.
	 * @param capacity The new buffer's capacity, in longs
	 * @return The new long buffer
	 */
	public static LongBuffer newLongBuffer(int capacity) {
		assert capacity <= Integer.MAX_VALUE / 8;
		ByteBuffer buf = ByteBuffer.allocateDirect(capacity * 8);
		buf.order(nativeOrder);
		return buf.asLongBuffer();
	}

	/**
	 * Allocates a new direct float buffer.
	 * @param capacity The new buffer's capacity, in floats
	 * @return The new float buffer
	 */
	public static FloatBuffer newFloatBuffer(int capacity) {
		assert capacity <= Integer.MAX_VALUE / 4;
		ByteBuffer buf = ByteBuffer.allocateDirect(capacity * 4);
		buf.order(nativeOrder);
		return buf.asFloatBuffer();
	}

	/**
	 * Allocates a new direct double buffer.
	 * @param capacity The new buffer's capacity, in doubles
	 * @return The new double buffer
	 */
	public static DoubleBuffer newDoubleBuffer(int capacity) {
		assert capacity <= Integer.MAX_VALUE / 8;
		ByteBuffer buf = ByteBuffer.allocateDirect(capacity * 8);
		buf.order(nativeOrder);
		return buf.asDoubleBuffer();
	}

	/**
	 * Asserts that a buffer is direct.
	 * @param buf buffer
	 */
	protected static void assertDirectBuffer(Buffer buf) {
		if(!buf.isDirect())
			throw new IllegalArgumentException("The buffer must be direct.");
	}
}
