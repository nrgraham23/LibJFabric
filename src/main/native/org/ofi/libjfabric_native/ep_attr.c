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

#include "org_ofi_libjfabric_attributes_EndPointAttr.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_initEndPointAttr
	(JNIEnv *env, jobject jthis, jint epType, jint jprotocol, jint protoVersion, jint maxMsgSize,
		jint msgPrefixSize, jint maxOrderRawSize, jint maxOrderWarSize, jint maxOrderWawSize,
		jlong memTagFormat, jint txCtxCnt, jint rxCtxCnt)
{
	struct fi_ep_attr *ep_attr = (struct fi_ep_attr*)calloc(1, sizeof(struct fi_ep_attr));

	ep_attr->type = epType;
	ep_attr->protocol = jprotocol;
	ep_attr->protocol_version = protoVersion;
	ep_attr->max_msg_size = maxMsgSize;
	ep_attr->msg_prefix_size = msgPrefixSize;
	ep_attr->max_order_raw_size = maxOrderRawSize;
	ep_attr->max_order_war_size = maxOrderWarSize;
	ep_attr->max_order_waw_size = maxOrderWawSize;
	ep_attr->mem_tag_format = memTagFormat;
	ep_attr->tx_ctx_cnt = txCtxCnt;
	ep_attr->rx_ctx_cnt = rxCtxCnt;

	simple_attr_list[simple_attr_list_tail] = ep_attr;
	simple_attr_list_tail++;
	return (jlong)ep_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_initEmpty
	(JNIEnv *env, jobject jthis)
{
	simple_attr_list[simple_attr_list_tail] = (struct fi_ep_attr*)calloc(1, sizeof(struct fi_ep_attr));
	simple_attr_list_tail++;
	return (jlong)simple_attr_list[simple_attr_list_tail - 1];
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getEpType
	(JNIEnv *env, jobject jthis, jlong handle)
{
	int epType = ((struct fi_ep_attr*)handle)->type;
	return (*env)->CallObjectMethod(env, lib_globals.EPTypeClass, lib_globals.GetEPType, epType);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getProtocol
	(JNIEnv *env, jobject jthis, jlong handle)
{
	int protocol = ((struct fi_ep_attr*)handle)->protocol;
	return (*env)->CallObjectMethod(env, lib_globals.ProtocolClass, lib_globals.GetProtocol, protocol);
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getProtoVersion
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->protocol_version;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getMaxMsgSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->max_msg_size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getMsgPrefixSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->msg_prefix_size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getMaxOrderRawSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->max_order_raw_size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getMaxOrderWarSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->max_order_war_size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getMaxOrderWawSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->max_order_waw_size;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getMemTagFormat
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->mem_tag_format;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getTxCtxCnt
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->tx_ctx_cnt;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_getRxCtxCnt
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_ep_attr*)handle)->rx_ctx_cnt;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setEpType
	(JNIEnv *env, jobject jthis, jint epType, jlong handle)
{
	((struct fi_ep_attr*)handle)->type = epType;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setProtocol
	(JNIEnv *env, jobject jthis, jint jprotocol, jlong handle)
{
	((struct fi_ep_attr*)handle)->protocol = jprotocol;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setProtoVersion
	(JNIEnv *env, jobject jthis, jint protoVersion, jlong handle)
{
	((struct fi_ep_attr*)handle)->protocol_version = protoVersion;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setMaxMsgSize
	(JNIEnv *env, jobject jthis, jint maxMsgSize, jlong handle)
{
	((struct fi_ep_attr*)handle)->max_msg_size = maxMsgSize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setMsgPrefixSize
	(JNIEnv *env, jobject jthis, jint msgPrefixSize, jlong handle)
{
	((struct fi_ep_attr*)handle)->msg_prefix_size = msgPrefixSize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setMaxOrderRawSize
	(JNIEnv *env, jobject jthis, jint maxOrderRawSize, jlong handle)
{
	((struct fi_ep_attr*)handle)->max_order_raw_size = maxOrderRawSize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setMaxOrderWarSize
	(JNIEnv *env, jobject jthis, jint maxOrderWarSize, jlong handle)
{
	((struct fi_ep_attr*)handle)->max_order_war_size = maxOrderWarSize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setMaxOrderWawSize
	(JNIEnv *env, jobject jthis, jint maxOrderWawSize, jlong handle)
{
	((struct fi_ep_attr*)handle)->max_order_waw_size = maxOrderWawSize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setMemTagFormat
	(JNIEnv *env, jobject jthis, jlong memTagFormat, jlong handle)
{
	((struct fi_ep_attr*)handle)->mem_tag_format = memTagFormat;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setTxCtxCnt
	(JNIEnv *env, jobject jthis, jint txCtxCnt, jlong handle)
{
	((struct fi_ep_attr*)handle)->tx_ctx_cnt = txCtxCnt;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_EndPointAttr_setRxCtxCnt
	(JNIEnv *env, jobject jthis, jint rxCtxCnt, jlong handle)
{
	((struct fi_ep_attr*)handle)->rx_ctx_cnt = rxCtxCnt;
}
