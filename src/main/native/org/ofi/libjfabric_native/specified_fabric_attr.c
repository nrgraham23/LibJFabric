#include "org_ofi_libjfabric_attributes_SpecifiedFabricAttr.h"
#include "fabric.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_SpecifiedFabricAttr_initWithFabric
	(JNIEnv *env, jobject jthis, jlong fabricHandle)
{
	struct fi_fabric_attr *fabric_attr = (struct fi_fabric_attr*)calloc(1, sizeof(struct fi_fabric_attr));

	fabric_attr->fabric = ((struct fid_fabric*)fabricHandle);

	fabric_attr_list[fabric_attr_list_tail] = fabric_attr;
	fabric_attr_list_tail++;
	return (jlong)fabric_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_SpecifiedFabricAttr_getFabric
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_fabric_attr*)handle)->fabric);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_SpecifiedFabricAttr_setFabric
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlong handle)
{
	((struct fi_fabric_attr*)handle)->fabric = ((struct fid_fabric*)fabricHandle);
}
