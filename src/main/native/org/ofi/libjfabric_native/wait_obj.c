#include "org_ofi_libjfabric_enums_WaitObj.h"
#include "libfabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getNONE(JNIEnv *env, jclass jthis) {
	return FI_WAIT_NONE;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_WAIT_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getSET(JNIEnv *env, jclass jthis) {
	return FI_WAIT_SET;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getFD(JNIEnv *env, jclass jthis) {
	return FI_WAIT_FD;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getMUTEXCOND(JNIEnv *env, jclass jthis) {
	return FI_WAIT_MUTEX_COND;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getCRITSECCOND(JNIEnv *env, jclass jthis) {
	return FI_WAIT_CRITSEC_COND;
}
