#include "org_ofi_libjfabric_attributes_TransmitAttr.h"
#include "fabric.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_initTransmitAttr
  (JNIEnv *env, jobject jthis, jlong caps, jlong mode, jlong opFlags, jlong msgOrder, jlong compOrder,
		  jint injectSize, jint size, jint iovLimit, jint rmaIovLimit)
{
	struct fi_tx_attr *tx_attr = (struct fi_tx_attr*)calloc(1, sizeof(struct fi_tx_attr));

	tx_attr->caps = caps;
	tx_attr->mode = mode;
	tx_attr->op_flags = opFlags;
	tx_attr->msg_order = msgOrder;
	tx_attr->comp_order = compOrder;
	tx_attr->inject_size = injectSize;
	tx_attr->size = size;
	tx_attr->iov_limit = iovLimit;
	tx_attr->rma_iov_limit = rmaIovLimit;

	simple_attr_list[simple_attr_list_tail] = tx_attr;
	simple_attr_list_tail++;
	return (jlong)tx_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_initEmpty
	(JNIEnv *env, jobject jthis)
{
	simple_attr_list[simple_attr_list_tail] = (struct fi_tx_attr*)calloc(1, sizeof(struct fi_tx_attr));
	simple_attr_list_tail++;
	return (jlong)simple_attr_list[simple_attr_list_tail - 1];
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getCaps
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->caps;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getMode
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->mode;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getOpFlags
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->op_flags;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getMsgOrder
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->msg_order;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getCompOrder
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->comp_order;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getInjectSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->inject_size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getSize
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->size;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getIovLimit
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->iov_limit;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_getRmaIovLimit
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return ((struct fi_tx_attr*)handle)->rma_iov_limit;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setCaps
	(JNIEnv *env, jobject jthis, jlong caps, jlong handle)
{
	((struct fi_tx_attr*)handle)->caps = caps;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setMode
	(JNIEnv *env, jobject jthis, jlong mode, jlong handle)
{
	((struct fi_tx_attr*)handle)->mode = mode;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setOpFlags
	(JNIEnv *env, jobject jthis, jlong opFlags, jlong handle)
{
	((struct fi_tx_attr*)handle)->op_flags = opFlags;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setMsgOrder
	(JNIEnv *env, jobject jthis, jlong msgOrder, jlong handle)
{
	((struct fi_tx_attr*)handle)->msg_order = msgOrder;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setCompOrder
	(JNIEnv *env, jobject jthis, jlong compOrder, jlong handle)
{
	((struct fi_tx_attr*)handle)->comp_order = compOrder;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setInjectSize
	(JNIEnv *env, jobject jthis, jint injectSize, jlong handle)
{
	((struct fi_tx_attr*)handle)->inject_size = injectSize;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setSize
	(JNIEnv *env, jobject jthis, jint size, jlong handle)
{
	((struct fi_tx_attr*)handle)->size = size;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setIovLimit
	(JNIEnv *env, jobject jthis, jint iovLimit, jlong handle)
{
	((struct fi_tx_attr*)handle)->iov_limit = iovLimit;
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_TransmitAttr_setRmaIovLimit
	(JNIEnv *env, jobject jthis, jint rmaIovLimit, jlong handle)
{
	((struct fi_tx_attr*)handle)->rma_iov_limit = rmaIovLimit;
}
