#include "org_ofi_libjfabric_enums_EPType.h"
#include "libfabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EPType_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_EP_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EPType_getMSG(JNIEnv *env, jclass jthis) {
	return FI_EP_MSG;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EPType_getDGRAM(JNIEnv *env, jclass jthis) {
	return FI_EP_DGRAM;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EPType_getRDM(JNIEnv *env, jclass jthis) {
	return FI_EP_RDM;
}
