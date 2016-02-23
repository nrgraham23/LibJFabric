#include "org_ofi_libjfabric_enums_Threading.h"
#include "libfabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Threading_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_THREAD_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Threading_getSAFE(JNIEnv *env, jclass jthis) {
	return FI_THREAD_SAFE;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Threading_getFID(JNIEnv *env, jclass jthis) {
	return FI_THREAD_FID;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Threading_getDOMAIN(JNIEnv *env, jclass jthis) {
	return FI_THREAD_DOMAIN;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Threading_getCOMPLETION(JNIEnv *env, jclass jthis) {
	return FI_THREAD_COMPLETION;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Threading_getENDPOINT(JNIEnv *env, jclass jthis) {
	return FI_THREAD_ENDPOINT;
}
