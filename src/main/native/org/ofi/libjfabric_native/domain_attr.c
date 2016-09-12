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

#include "org_ofi_libjfabric_attributes_DomainAttr.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_initDomainAttr
(JNIEnv *env, jobject jthis, jstring name, jint jthreading, jint jcntrlProgress, jint jdataProgress,
		jint jresourceMgmt, jint javType, jint jmrMode, jint jmrKeySize, jint jcqDataSize, jint jcqCnt,
		jint jepCnt, jint jtxCtxCnt, jint jrxCtxCnt, jint jmaxEpTxCtx, jint jmaxEpRxCtx,
		jint jmaxEpStxCtx, jint jmaxEpSrxCtx)
{
	struct fi_domain_attr *domain_attr = (struct fi_domain_attr*)calloc(1, sizeof(struct fi_domain_attr));

	convertJNIString(env, &(domain_attr->name), name);

	domain_attr->threading = jthreading;
	domain_attr->control_progress = jcntrlProgress;
	domain_attr->data_progress = jdataProgress;
	domain_attr->resource_mgmt = jresourceMgmt;
	domain_attr->av_type = javType;
	domain_attr->mr_mode = jmrMode;
	domain_attr->mr_key_size = jmrKeySize;
	domain_attr->cq_data_size = jcqDataSize;
	domain_attr->cq_cnt = jcqCnt;
	domain_attr->ep_cnt = jepCnt;
	domain_attr->tx_ctx_cnt = jtxCtxCnt;
	domain_attr->rx_ctx_cnt = jrxCtxCnt;
	domain_attr->max_ep_tx_ctx = jmaxEpTxCtx;
	domain_attr->max_ep_rx_ctx = jmaxEpRxCtx;
	domain_attr->max_ep_stx_ctx = jmaxEpStxCtx;
	domain_attr->max_ep_srx_ctx = jmaxEpSrxCtx;

	domain_attr_list[domain_attr_list_tail] = domain_attr;
	domain_attr_list_tail++;
	return (jlong)domain_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_initEmpty
(JNIEnv *env, jclass jthis)
{
	domain_attr_list[domain_attr_list_tail] = (struct fi_domain_attr*)calloc(1, sizeof(struct fi_domain_attr));
	domain_attr_list_tail++;
	return (jlong)domain_attr_list[domain_attr_list_tail - 1];
}

JNIEXPORT jstring JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getName
(JNIEnv *env, jobject jthis, jlong handle)
{
	return (*env)->NewStringUTF(env, ((struct fi_domain_attr*)handle)->name);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getThreading
(JNIEnv *env, jobject jthis, jlong handle)
{
	int threading = ((struct fi_domain_attr*)handle)->threading;
	return (*env)->CallObjectMethod(env, lib_globals.ThreadingClass, lib_globals.GetThreading, threading);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getCntrlProgress
(JNIEnv *env, jobject jthis, jlong handle)
{
	int cntrlProgress = ((struct fi_domain_attr*)handle)->control_progress;
	return (*env)->CallObjectMethod(env, lib_globals.ProgressClass, lib_globals.GetProgress, cntrlProgress);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getdataProgress
(JNIEnv *env, jobject jthis, jlong handle)
{
	int dataProgress = ((struct fi_domain_attr*)handle)->data_progress;
	return (*env)->CallObjectMethod(env, lib_globals.ProgressClass, lib_globals.GetProgress, dataProgress);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getResourceMgmt
(JNIEnv *env, jobject jthis, jlong handle)
{
	int resourceMgmt = ((struct fi_domain_attr*)handle)->resource_mgmt;
	return (*env)->CallObjectMethod(env, lib_globals.ResourceMgmtClass, lib_globals.GetResourceMgmt, resourceMgmt);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getAVType
(JNIEnv *env, jobject jthis, jlong handle)
{
	int avType = ((struct fi_domain_attr*)handle)->av_type;
	return (*env)->CallObjectMethod(env, lib_globals.AVTypeClass, lib_globals.GetAVType, avType);
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMRMode
(JNIEnv *env, jobject jthis, jlong handle)
{
	int mrMode = ((struct fi_domain_attr*)handle)->mr_mode;
	return (*env)->CallObjectMethod(env, lib_globals.MRModeClass, lib_globals.GetMRMode, mrMode);
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMrKeySize
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->mr_key_size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getCQDataSize
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->cq_data_size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getCQCnt
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->cq_cnt;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getEndPointCnt
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->ep_cnt;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getTxCtxCnt
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->tx_ctx_cnt;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getRxCtxCnt
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->rx_ctx_cnt;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpTxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->max_ep_tx_ctx;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpRxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->max_ep_rx_ctx;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpStxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->max_ep_stx_ctx;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpSrxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_domain_attr*)handle)->max_ep_srx_ctx;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setName
(JNIEnv *env, jobject jthis, jstring name, jlong handle)
{
	convertJNIString(env, &(((struct fi_domain_attr*)handle)->name), name);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setThreading
(JNIEnv *env, jobject jthis, jint jthreading, jlong handle)
{
	((struct fi_domain_attr*)handle)->threading = jthreading;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setCntrlProgress
(JNIEnv *env, jobject jthis, jint cntrlProgress, jlong handle)
{
	((struct fi_domain_attr*)handle)->control_progress = cntrlProgress;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setDataProgress
(JNIEnv *env, jobject jthis, jint dataProgress, jlong handle)
{
	((struct fi_domain_attr*)handle)->data_progress = dataProgress;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setResourceMgmt
(JNIEnv *env, jobject jthis, jint resourceMgmt, jlong handle)
{
	((struct fi_domain_attr*)handle)->resource_mgmt = resourceMgmt;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setAVType
(JNIEnv *env, jobject jthis, jint avType, jlong handle)
{
	((struct fi_domain_attr*)handle)->av_type = avType;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMRMode
(JNIEnv *env, jobject jthis, jint mrMode, jlong handle)
{
	((struct fi_domain_attr*)handle)->mr_mode = mrMode;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMRKeySize
(JNIEnv *env, jobject jthis, jint mrKeySize, jlong handle)
{
	((struct fi_domain_attr*)handle)->mr_key_size = mrKeySize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setCQDataSize
(JNIEnv *env, jobject jthis, jint cqDataSize, jlong handle)
{
	((struct fi_domain_attr*)handle)->cq_data_size = cqDataSize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setCQCnt
(JNIEnv *env, jobject jthis, jint cqCnt, jlong handle)
{
	((struct fi_domain_attr*)handle)->cq_cnt = cqCnt;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setEndpointCnt
(JNIEnv *env, jobject jthis, jint endpointCnt, jlong handle)
{
	((struct fi_domain_attr*)handle)->ep_cnt = endpointCnt;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setTxCtxCnt
(JNIEnv *env, jobject jthis, jint txCtxCnt, jlong handle)
{
	((struct fi_domain_attr*)handle)->tx_ctx_cnt = txCtxCnt;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setRxCtxCnt
(JNIEnv *env, jobject jthis, jint rxCtxCnt, jlong handle)
{
	((struct fi_domain_attr*)handle)->rx_ctx_cnt = rxCtxCnt;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpTxCtx
(JNIEnv *env, jobject jthis, jint maxEpTxCtx, jlong handle)
{
	((struct fi_domain_attr*)handle)->max_ep_tx_ctx = maxEpTxCtx;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpRxCtx
(JNIEnv *env, jobject jthis, jint maxEpRxCtx, jlong handle)
{
	((struct fi_domain_attr*)handle)->max_ep_rx_ctx = maxEpRxCtx;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpStxCtx
(JNIEnv *env, jobject jthis, jint maxEpStxCtx, jlong handle)
{
	((struct fi_domain_attr*)handle)->max_ep_stx_ctx = maxEpStxCtx;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpSrxCtx
(JNIEnv *env, jobject jthis, jint maxEpSrxCtx, jlong handle)
{
	((struct fi_domain_attr*)handle)->max_ep_srx_ctx = maxEpSrxCtx;
}
