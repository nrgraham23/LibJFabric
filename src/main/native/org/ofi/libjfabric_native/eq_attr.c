/*
 * Copyright (c) 2016 Los Alamos Nat. Security, LLC. All rights reserved.
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

#include "org_ofi_libjfabric_attributes_EventQueueAttr.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_eventQueueAttrInit
	(JNIEnv *env, jobject jthis, jint size, jlong flags, jint waitObjVal, jint signallingVector, jlong waitHandle)
{
	struct fi_eq_attr *eq_attr = (struct fi_eq_attr*)calloc(1, sizeof(struct fi_eq_attr));

	eq_attr->size = size;
	eq_attr->flags = flags;
	eq_attr->wait_obj = waitObjVal;
	eq_attr->signaling_vector = signallingVector;
	eq_attr->wait_set = ((struct fid_wait *)waitHandle);

	eq_attr_list[eq_attr_list_tail] = eq_attr;
	eq_attr_list_tail++;
	return (jlong)eq_attr;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_getSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_eq_attr*)handle)->size;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_getFlags
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_eq_attr*)handle)->flags;
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_getWaitObj
	(JNIEnv *env, jobject jthis, jlong handle)
{
	int waitObj = ((struct fi_eq_attr*)handle)->wait_obj;
	return (*env)->CallObjectMethod(env, lib_globals.WaitObjClass, lib_globals.GetWaitObj, waitObj);
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_getSignalingVector
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_eq_attr*)handle)->signaling_vector;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_getWait
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_eq_attr*)handle)->wait_set);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_setSize
	(JNIEnv *env, jobject jthis, jint size, jlong handle)
{
	((struct fi_eq_attr*)handle)->size = size;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_setFlags
	(JNIEnv *env, jobject jthis, jlong flags, jlong handle)
{
	((struct fi_eq_attr*)handle)->flags = flags;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_setWaitObj
	(JNIEnv *env, jobject jthis, jint waitObjVal, jlong handle)
{
	((struct fi_eq_attr*)handle)->wait_obj = waitObjVal;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_setSignalingVector
	(JNIEnv *env, jobject jthis, jint signalingVector, jlong handle)
{
	((struct fi_eq_attr*)handle)->signaling_vector = signalingVector;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EventQueueAttr_setWait
	(JNIEnv *env, jobject jthis, jlong waitHandle, jlong handle)
{
	((struct fi_eq_attr*)handle)->wait_set = (struct fid_wait *)waitHandle;
}
