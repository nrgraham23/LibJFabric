#include "org_ofi_libjfabric_Info.h"
#include "libfabric.h"

void *dlhandle;

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Info_initInfo
	(JNIEnv *env, jobject jthis, jlong caps, jlong mode, jint addrFormat, jint srcAddrLen, jint destAddrLen,
		jlong transmitAttrHandle, jlong receiveAttrHandle, jlong endpointAttrHandle, jlong domainAttrHandle,
		jlong fabricAttrHandle)
{
	struct fi_info *(*dup_info_ptr)(const struct fi_info *info);
	char *error;

	*(void **) (&dup_info_ptr) = dlsym(dlhandle, "fi_dupinfo");
	if ((error = dlerror()) != NULL) {
		fprintf (stderr, "%s\n", error);
		exit(1);
	}
	struct fi_info *info = (*dup_info_ptr)(NULL);

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
	struct fi_info *(*dup_info_ptr)(const struct fi_info *info);
	char *error;

	*(void **) (&dup_info_ptr) = dlsym(dlhandle, "fi_dupinfo");
	if ((error = dlerror()) != NULL) {
		fprintf (stderr, "%s\n", error);
		exit(1);
	}


	info_list[info_list_tail] = (*dup_info_ptr)(NULL);
	info_list_tail++;
	return (jlong)info_list[info_list_tail - 1];
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Info_freeJNI
	(JNIEnv *env, jobject jthis, jlong handle)
{
	if(handle != 0) {
		((struct fi_info*)handle)->next = NULL;
		((struct fi_info*)handle)->src_addr = NULL;
		((struct fi_info*)handle)->dest_addr = NULL;
		((struct fi_info*)handle)->tx_attr = NULL;
		((struct fi_info*)handle)->rx_attr = NULL;
		((struct fi_info*)handle)->ep_attr = NULL;
		((struct fi_info*)handle)->domain_attr = NULL;
		((struct fi_info*)handle)->fabric_attr = NULL;

		free(((struct fi_info*)handle));
	}
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
