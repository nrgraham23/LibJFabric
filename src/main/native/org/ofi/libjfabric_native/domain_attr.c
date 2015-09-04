#include "org_ofi_libjfabric_attributes_DomainAttr.h"
#include "fabric.h"
#include "lib_fabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_initDomainAttr
(JNIEnv *env, jobject jthis, jstring name, jint jthreading, jint jcntrlProgress, jint jdataProgress,
		jint jresourceMgmt, jint javType, jint jmrMode, jint jmrKeySize, jint jcqDataSize, jint jcqCnt,
		jint jepCnt, jint jtxCtxCnt, jint jrxCtxCnt, jint jmaxEpTxCtx, jint jmaxEpRxCtx,
		jint jmaxEpStxCtx, jint jmaxEpSrxCtx)
{
	struct fi_domain_attr *domain_attr = (struct fi_domain_attr*)malloc(sizeof(struct fi_domain_attr));
	domain_attr->domain = NULL;

	domain_attr->name = (char*)malloc((int)(*env)->GetStringLength(env, name));
	const char *jniName = (*env)->GetStringUTFChars(env, name, NULL);
	strcpy(domain_attr->name, jniName);
	(*env)->ReleaseStringUTFChars(env, name, jniName);

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
	return (jlong)domain_attr_list[domain_attr_list_tail - 1];
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
	//return ((struct fi_domain_attr*)handle)->threading; http://stackoverflow.com/questions/17333530/why-jni-cant-find-self-define-static-in-enum
}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getCntrlProgress
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getdataProgress
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getResourceMgmt
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getAVType
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMRMode
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMrKeySize
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getCQDataSize
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getCQCnt
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getEndPointCnt
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getTxCtxCnt
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getRxCtxCnt
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpTxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpRxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpStxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_getMaxEpSrxCtx
(JNIEnv *env, jobject jthis, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setName
(JNIEnv *env, jobject jthis, jstring name, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setThreading
(JNIEnv *env, jobject jthis, jint threading, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setCntrlProgress
(JNIEnv *env, jobject jthis, jint cntrlProgress, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setDataProgress
(JNIEnv *env, jobject jthis, jint dataProgress, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setResourceMgmt
(JNIEnv *env, jobject jthis, jint resourceMgmt, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setAVType
(JNIEnv *env, jobject jthis, jint avType, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMRMode
(JNIEnv *env, jobject jthis, jint mrMode, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMRKeySize
(JNIEnv *env, jobject jthis, jint mrKeySize, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setCQDataSize
(JNIEnv *env, jobject jthis, jint cqDataSize, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setCQCnt
(JNIEnv *env, jobject jthis, jint cqCnt, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setEndpointCnt
(JNIEnv *env, jobject jthis, jint endpointCnt, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setTxCtxCnt
(JNIEnv *env, jobject jthis, jint txCtxCnt, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setRxCtxCnt
(JNIEnv *env, jobject jthis, jint rxCtxCnt, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpTxCtx
(JNIEnv *env, jobject jthis, jint maxEpTxCtx, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpRxCtx
(JNIEnv *env, jobject jthis, jint maxEpRxCtx, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpStxCtx
(JNIEnv *env, jobject jthis, jint maxEpStxCtx, jlong handle)
{

}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_DomainAttr_setMaxEpSrxCtx
(JNIEnv *env, jobject jthis, jint maxEpSrxCtx, jlong handle)
{

}
