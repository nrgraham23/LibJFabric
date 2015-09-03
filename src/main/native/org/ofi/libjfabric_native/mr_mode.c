#include "org_ofi_libjfabric_enums_MRMode.h"
#include "fabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_MRMode_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_MR_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_MRMode_getBASIC(JNIEnv *env, jclass jthis) {
	return FI_MR_BASIC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_MRMode_getSCALABLE(JNIEnv *env, jclass jthis) {
	return FI_MR_SCALABLE;
}
