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

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_send
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length, jlong mrDesc, jlong destAddr, jlong contextHandle)
{
	void *ptr = getDirectBufferAddress(env, buffer);
	
	fi_send((struct fid_ep *)epHandle, &ptr, length, (void *)mrDesc, destAddr, (void *)contextHandle);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_send2
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length, jlong mrDesc, jlong destAddr)
{
	void *ptr = getDirectBufferAddress(env, buffer);

	fi_send((struct fid_ep *)epHandle, &ptr, length, (void *)mrDesc, destAddr, NULL);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_send4
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length, jlong destAddress)
{
	void *ptr = getDirectBufferAddress(env, buffer);

	fi_send((struct fid_ep *)epHandle, &ptr, length, NULL, destAddress, NULL);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_recv
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length, jlong mrDesc, jlong srcAddress, jlong contextHandle)
{
	void *ptr = getDirectBufferAddress(env, buffer);

	((struct fid_ep *)epHandle)->msg->recv((struct fid_ep *)epHandle, &ptr, length, (void *)mrDesc, srcAddress, (void *)contextHandle);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_recv2
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length, jlong mrDesc, jlong srcAddress)
{
	void *ptr = getDirectBufferAddress(env, buffer);

	fi_recv((struct fid_ep *)epHandle, &ptr, length, (void *)mrDesc, srcAddress, NULL);
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

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_accept
	(JNIEnv *env, jobject jthis, jlong epHandle)
{
	((struct fid_ep *)epHandle)->cm->accept((struct fid_ep *)epHandle, NULL, 0);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_connect
	(JNIEnv *env, jobject jthis, jlong epHandle, jbyteArray addr)
{
	jsize len = (*env)->GetArrayLength(env, addr);
	
	char *cAddr;
	(*env)->GetByteArrayRegion(env, addr, 0, len, (jbyte *)cAddr);
	
	fi_connect((struct fid_ep *)epHandle, cAddr, NULL, 0);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_inject
	(JNIEnv *env, jobject jthis, jlong epHandle, jobject buffer, jint length, jlong destAddr)
{
	void *ptr = getDirectBufferAddress(env, buffer);
	
	((struct fid_ep *)epHandle)->msg->inject((struct fid_ep *)epHandle, &ptr, length, destAddr);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_sendMessage
	(JNIEnv *env, jobject jthis, jlong epHandle, jlong msgHandle, jlong flags)
{
	((struct fid_ep *)epHandle)->msg->sendmsg((struct fid_ep *)epHandle, (const struct fi_msg *)msgHandle, flags);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_EndPoint_shutdown
	(JNIEnv *env, jobject jthis, jlong handle, jlong flags)
{
	fi_shutdown((struct fid_ep *)handle, flags);
}

JNIEXPORT jboolean JNICALL Java_org_ofi_libjfabric_EndPoint_bind
	(JNIEnv *env, jobject jthis, jlong thisHandle, jlong bindToHandle, jlong flags)
{
	int res = fi_ep_bind((struct fid_ep *)thisHandle, (struct fid *)bindToHandle, (uint64_t)flags);

	return FI_SUCCESS == res;
}
