#include "org_ofi_libjfabric_Progress.h"
#include "fabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Progress_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_PROGRESS_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Progress_getAUTO(JNIEnv *env, jclass jthis) {
	return FI_PROGRESS_AUTO;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Progress_getMANUAL(JNIEnv *env, jclass jthis) {
	return FI_PROGRESS_MANUAL;
}
