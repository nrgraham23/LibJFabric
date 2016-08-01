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

#include "org_ofi_libjfabric_attributes_CQAttr.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_initCQAttr
	(JNIEnv *env, jobject jthis, jlong jsize, jlong jflags, jint jformat, jint jwaitObj, jint jsignalingVector, jint jcqWaitCond, jlong jwaitHandle)
{
	struct fi_cq_attr *cq_attr = (struct fi_cq_attr*)calloc(1, sizeof(struct fi_cq_attr));

	cq_attr->size = jsize;
	cq_attr->flags = jflags;
	cq_attr->format = jformat;
	cq_attr->wait_obj = jwaitObj;
	cq_attr->signaling_vector = jsignalingVector;
	cq_attr->wait_cond = jcqWaitCond;
	cq_attr->wait_set = ((struct fid_wait *)jwaitHandle);

	cq_attr_list[cq_attr_list_tail] = cq_attr;
	cq_attr_list_tail++;
	return (jlong)cq_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_initEmpty
	(JNIEnv *env, jobject jthis)
{
	cq_attr_list[cq_attr_list_tail] = (struct fi_cq_attr*)calloc(1, sizeof(struct fi_cq_attr));
	cq_attr_list_tail++;
	return (jlong)cq_attr_list[cq_attr_list_tail - 1];
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_getSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_cq_attr*)handle)->size;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_getFlags
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_cq_attr*)handle)->flags;
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_getCQFormat
	(JNIEnv *env, jobject jthis, jlong handle)
{
	int cqFormat = ((struct fi_cq_attr*)handle)->format;
	return (*env)->CallObjectMethod(env, lib_globals.CQFormatClass, lib_globals.GetCQFormat, cqFormat);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_getWaitObj
	(JNIEnv *env, jobject jthis, jlong handle)
{
	int waitObj = ((struct fi_cq_attr*)handle)->wait_obj;
	return (*env)->CallObjectMethod(env, lib_globals.WaitObjClass, lib_globals.GetWaitObj, waitObj);
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_getSignalingVector
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_cq_attr*)handle)->signaling_vector;
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_getCQWaitCond
	(JNIEnv *env, jobject jthis, jlong handle)
{
	int waitCond = ((struct fi_cq_attr*)handle)->wait_cond;
	return (*env)->CallObjectMethod(env, lib_globals.CQWaitCondClass, lib_globals.GetCQWaitCond, waitCond);
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_getWait
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)((struct fi_cq_attr*)handle)->wait_set;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_setSize
	(JNIEnv *env, jobject jthis, jlong handle, jlong size)
{
	((struct fi_cq_attr*)handle)->size = size;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_setFlags
	(JNIEnv *env, jobject jthis, jlong handle, jlong flags)
{
	((struct fi_cq_attr*)handle)->flags = flags;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_setCQFormat
(JNIEnv *env, jobject jthis, jlong handle, jint cqFormat)
{
	((struct fi_cq_attr*)handle)->format = cqFormat;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_setWaitObj
	(JNIEnv *env, jobject jthis, jlong handle, jint waitObj)
{
	((struct fi_cq_attr*)handle)->wait_obj = waitObj;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_setSignalingVector
	(JNIEnv *env, jobject jthis, jlong handle, jint signalingVector)
{
	((struct fi_cq_attr*)handle)->signaling_vector = signalingVector;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_setCQWaitCond
	(JNIEnv *env, jobject jthis, jlong handle, jint cqWaitCond)
{
	((struct fi_cq_attr*)handle)->wait_cond = cqWaitCond;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_CQAttr_setWait
	(JNIEnv *env, jobject jthis, jlong handle, jlong waitHandle)
{
	((struct fi_cq_attr*)handle)->wait_set = (struct fid_wait *)waitHandle;
}
