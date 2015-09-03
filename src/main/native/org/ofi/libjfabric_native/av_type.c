#include "org_ofi_libjfabric_enums_AVType.h"
#include "fabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_AVType_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_AV_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_AVType_getMAP(JNIEnv *env, jclass jthis) {
	return FI_AV_MAP;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_AVType_getTABLE(JNIEnv *env, jclass jthis) {
	return FI_AV_TABLE;
}
