#include "org_ofi_libjfabric_ResourceMgmt.h"
#include "fabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_ResourceMgmt_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_RM_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_ResourceMgmt_getDISABLED(JNIEnv *env, jclass jthis) {
	return FI_RM_DISABLED;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_ResourceMgmt_getENABLED(JNIEnv *env, jclass jthis) {
	return FI_RM_ENABLED;
}
