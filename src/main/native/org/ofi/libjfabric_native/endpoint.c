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

#include "libfabric.h"
#include "org_ofi_libjfabric_EndPoint.h"

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_send4
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length, jlong destAddress)
{
	void *ptr = getDirectBufferAddress(env, buffer);

	((struct fid_ep *)epHandle)->msg->send((struct fid_ep *)epHandle, &ptr, length, NULL, destAddress, NULL);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_recv5
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length)
{
	void *ptr = getDirectBufferAddress(env, buffer);

	((struct fid_ep *)epHandle)->msg->recv((struct fid_ep *)epHandle, &ptr, length, NULL, FI_ADDR_UNSPEC, NULL);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_enable
	(JNIEnv *env, jobject jthis, jlong epHandle)
{
	fi_enable((struct fid_ep *)epHandle);
}
