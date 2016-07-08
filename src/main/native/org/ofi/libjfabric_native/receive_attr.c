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

#include "org_ofi_libjfabric_attributes_ReceiveAttr.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_initReceiveAttr
  (JNIEnv *env, jobject jthis, jlong caps, jlong mode, jlong opFlags, jlong msgOrder,
		  jlong compOrder, jint totalBufferedRecv, jint size, jint iovLimit)
{
	struct fi_rx_attr *rx_attr = (struct fi_rx_attr*)calloc(1, sizeof(struct fi_rx_attr));

	rx_attr->caps = caps;
	rx_attr->mode = mode;
	rx_attr->op_flags = opFlags;
	rx_attr->msg_order = msgOrder;
	rx_attr->comp_order = compOrder;
	rx_attr->total_buffered_recv = totalBufferedRecv;
	rx_attr->size = size;
	rx_attr->iov_limit = iovLimit;

	simple_attr_list[simple_attr_list_tail] = rx_attr;
	simple_attr_list_tail++;
	return (jlong)rx_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_initEmpty
  (JNIEnv *env, jobject jthis)
{
	simple_attr_list[simple_attr_list_tail] = (struct fi_rx_attr*)calloc(1, sizeof(struct fi_rx_attr));
	simple_attr_list_tail++;
	return (jlong)simple_attr_list[simple_attr_list_tail - 1];
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getCaps
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->caps;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getMode
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->mode;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getOpFlags
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->op_flags;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getMsgOrder
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->msg_order;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getCompOrder
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->comp_order;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getTotalBufferedRecv
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->total_buffered_recv;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getSize
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_getIovLimit
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_rx_attr*)handle)->iov_limit;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setCaps
  (JNIEnv *env, jobject jthis, jlong caps, jlong handle)
{
	((struct fi_rx_attr*)handle)->caps = caps;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setMode
  (JNIEnv *env, jobject jthis, jlong mode, jlong handle)
{
	((struct fi_rx_attr*)handle)->mode = mode;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setOpFlags
  (JNIEnv *env, jobject jthis, jlong opFlags, jlong handle)
{
	((struct fi_rx_attr*)handle)->op_flags = opFlags;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setMsgOrder
  (JNIEnv *env, jobject jthis, jlong msgOrder, jlong handle)
{
	((struct fi_rx_attr*)handle)->msg_order = msgOrder;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setCompOrder
  (JNIEnv *env, jobject jthis, jlong compOrder, jlong handle)
{
	((struct fi_rx_attr*)handle)->comp_order = compOrder;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setTotalBufferedRecv
  (JNIEnv *env, jobject jthis, jint totalBufferedRecv, jlong handle)
{
	((struct fi_rx_attr*)handle)->total_buffered_recv = totalBufferedRecv;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setSize
  (JNIEnv *env, jobject jthis, jint size, jlong handle)
{
	((struct fi_rx_attr*)handle)->size = size;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_ReceiveAttr_setIovLimit
  (JNIEnv *env, jobject jthis, jint iovLimit, jlong handle)
{
	((struct fi_rx_attr*)handle)->iov_limit = iovLimit;
}
