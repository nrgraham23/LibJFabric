#include "org_ofi_libjfabric_LibFabric.h"
#include "lib_fabric.h"

struct fi_domain_attr *domain_attr_list[1000];
int domain_attr_list_tail = 0;
libfabric_enum_globals_t lib_enums;

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_init(JNIEnv *env, jclass jthis) {
	initEnumMethods(env);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_deleteCachedVars(JNIEnv *env, jclass jthis) {
	deleteEnumMethods(env);
}

void initEnumMethods(JNIEnv *env) {
	lib_enums.AVTypeClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/AVType");
	lib_enums.EPTypeClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/EPType");
	lib_enums.MRModeClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/MRMode");
	lib_enums.ProgressClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/Progress");
	lib_enums.ProtocolClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/Protocol");
	lib_enums.ResourceMgmtClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/ResourceMgmt");
	lib_enums.ThreadingClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/Threading");
	lib_enums.GetAVType = (*env)->GetStaticMethodID(env,lib_enums.AVTypeClass,"getAVType","(I)Lorg/ofi/libjfabric/enums/AVType;");
	lib_enums.GetEPType = (*env)->GetStaticMethodID(env,lib_enums.EPTypeClass,"getEPType","(I)Lorg/ofi/libjfabric/enums/EPType;");
	lib_enums.GetMRMode = (*env)->GetStaticMethodID(env,lib_enums.MRModeClass,"getMRMode","(I)Lorg/ofi/libjfabric/enums/MRMode;");
	lib_enums.GetProgress = (*env)->GetStaticMethodID(env,lib_enums.ProgressClass,"getProgress","(I)Lorg/ofi/libjfabric/enums/Progress;");
	lib_enums.GetProtocol = (*env)->GetStaticMethodID(env,lib_enums.ProtocolClass,"getProtocol","(I)Lorg/ofi/libjfabric/enums/Protocol;");
	lib_enums.GetResourceMgmt = (*env)->GetStaticMethodID(env,lib_enums.ResourceMgmtClass,"getResourceMgmt","(I)Lorg/ofi/libjfabric/enums/ResourceMgmt;");
	lib_enums.GetThreading = (*env)->GetStaticMethodID(env,lib_enums.ThreadingClass,"getThreading","(I)Lorg/ofi/libjfabric/enums/Threading;");
}

void deleteEnumMethods(JNIEnv *env) {
	(*env)->DeleteGlobalRef(env, lib_enums.AVTypeClass);
	(*env)->DeleteGlobalRef(env, lib_enums.EPTypeClass);
	(*env)->DeleteGlobalRef(env, lib_enums.MRModeClass);
	(*env)->DeleteGlobalRef(env, lib_enums.ProgressClass);
	(*env)->DeleteGlobalRef(env, lib_enums.ProtocolClass);
	(*env)->DeleteGlobalRef(env, lib_enums.ResourceMgmtClass);
	(*env)->DeleteGlobalRef(env, lib_enums.ThreadingClass);
}
