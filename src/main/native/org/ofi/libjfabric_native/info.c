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

#include "org_ofi_libjfabric_Info.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_initInfo
	(JNIEnv *env, jobject jthis, jlong caps, jlong mode, jint addrFormat, jint srcAddrLen, jint destAddrLen,
		jlong transmitAttrHandle, jlong receiveAttrHandle, jlong endpointAttrHandle, jlong domainAttrHandle,
		jlong fabricAttrHandle)
{
	char *error;

	struct fi_info *info = fi_allocinfo();

	info->next = NULL;
	info->caps = caps;
	info->mode = mode;
	info->addr_format = addrFormat;
	info->src_addrlen = srcAddrLen;
	info->dest_addrlen = destAddrLen;
	info->src_addr = NULL;
	info->dest_addr = NULL;
	info->tx_attr = (struct fi_tx_attr*)transmitAttrHandle;
	info->rx_attr = (struct fi_rx_attr*)receiveAttrHandle;
	info->ep_attr = (struct fi_ep_attr*)endpointAttrHandle;
	info->domain_attr = (struct fi_domain_attr*)domainAttrHandle;
	info->fabric_attr = (struct fi_fabric_attr*)fabricAttrHandle;

	info_list[info_list_tail] = info;
	info_list_tail++;
	return (jlong)info;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_initEmpty
	(JNIEnv *env, jobject jthis)
{
	char *error;

	info_list[info_list_tail] = fi_allocinfo();
	info_list_tail++;
	return (jlong)info_list[info_list_tail - 1];
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_free
	(JNIEnv *env, jobject jthis, jlong handle)
{
	struct fi_info* info = (struct fi_info*)handle;
	fi_freeinfo(info);
	info = NULL;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_getCaps
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_info*)handle)->caps;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_getMode
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_info*)handle)->mode;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_Info_getAddrFormat
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_info*)handle)->addr_format;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_Info_getSrcAddrLen
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_info*)handle)->src_addrlen;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_Info_getDestAddrLen
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_info*)handle)->dest_addrlen;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_getTransmitAttr
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_info*)handle)->tx_attr);
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_getReceiveAttr
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_info*)handle)->rx_attr);
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_getEndPointAttr
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_info*)handle)->ep_attr);
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_getDomainAttr
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_info*)handle)->domain_attr);
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_getFabricAttr
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_info*)handle)->fabric_attr);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setCaps
	(JNIEnv *env, jobject jthis, jlong caps, jlong handle)
{
	((struct fi_info*)handle)->caps = caps;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setMode
	(JNIEnv *env, jobject jthis, jlong mode, jlong handle)
{
	((struct fi_info*)handle)->mode = mode;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setAddrFormat
	(JNIEnv *env, jobject jthis, jint addrFormat, jlong handle)
{
	((struct fi_info*)handle)->addr_format = addrFormat;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setSrcAddrLen
	(JNIEnv *env, jobject jthis, jint srcAddrLen, jlong handle)
{
	((struct fi_info*)handle)->src_addrlen = srcAddrLen;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setDestAddrLen
	(JNIEnv *env, jobject jthis, jint destAddrLen, jlong handle)
{
	((struct fi_info*)handle)->dest_addrlen = destAddrLen;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setTransmitAttr
	(JNIEnv *env, jobject jthis, jlong transmitAttrHandle, jlong thisHandle)
{
	((struct fi_info*)thisHandle)->tx_attr = (struct fi_tx_attr*)transmitAttrHandle;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setReceiveAttr
	(JNIEnv *env, jobject jthis, jlong receiveAttrHandle, jlong thisHandle)
{
	((struct fi_info*)thisHandle)->rx_attr = (struct fi_rx_attr*)receiveAttrHandle;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setEndPointAttr
	(JNIEnv *env, jobject jthis, jlong endpointAttrHandle, jlong thisHandle)
{
	((struct fi_info*)thisHandle)->ep_attr = (struct fi_ep_attr*)endpointAttrHandle;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setDomainAttr
	(JNIEnv *env, jobject jthis, jlong domainAttrHandle, jlong thisHandle)
{
	((struct fi_info*)thisHandle)->domain_attr = (struct fi_domain_attr*)domainAttrHandle;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setFabricAttr
	(JNIEnv *env, jobject jthis, jlong fabricAttrHandle, jlong thisHandle)
{
	((struct fi_info*)thisHandle)->fabric_attr = (struct fi_fabric_attr*)fabricAttrHandle;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_setDestAddr  //TODO: Ask Howard about this
	(JNIEnv *env, jobject jthis, jstring destAddr, jlong thisHandle)
{
	const char *addr = (*env)->GetStringUTFChars(env, destAddr, NULL);
	((struct fi_info*)thisHandle)->dest_addr = (void *)*addr;
}
