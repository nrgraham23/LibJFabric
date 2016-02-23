#include "org_ofi_libjfabric_enums_Protocol.h"
#include "libfabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_PROTO_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getRDMA(JNIEnv *env, jclass jthis) {
	return FI_PROTO_RDMA_CM_IB_RC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getIWARP(JNIEnv *env, jclass jthis) {
	return FI_PROTO_IWARP;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getIBUD(JNIEnv *env, jclass jthis) {
	return FI_PROTO_IB_UD;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getPSMX(JNIEnv *env, jclass jthis) {
	return FI_PROTO_PSMX;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getUDP(JNIEnv *env, jclass jthis) {
	return FI_PROTO_UDP;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getSOCKTCP(JNIEnv *env, jclass jthis) {
	return FI_PROTO_SOCK_TCP;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_Protocol_getGNI(JNIEnv *env, jclass jthis) {
	return FI_PROTO_GNI;
}
