#ifndef _LIB_FABRIC_H_
#define _LIB_FABRIC_H_

#include <stdlib.h>
#include "org_ofi_libjfabric_LibFabric.h"
#include "fi_list.h"

extern struct fi_domain_attr *domain_attr_list[];
extern int domain_attr_list_tail;

typedef struct {
	jclass AVTypeClass;
	jclass EPTypeClass;
	jclass MRModeClass;
	jclass ProgressClass;
	jclass ProtocolClass;
	jclass ResourceMgmtClass;
	jclass ThreadingClass;
	jmethodID GetAVType;
	jmethodID GetEPType;
	jmethodID GetMRMode;
	jmethodID GetProgress;
	jmethodID GetProtocol;
	jmethodID GetResourceMgmt;
	jmethodID GetThreading;
} libfabric_enum_globals_t;

extern libfabric_enum_globals_t lib_enums;

void initEnumMethods(JNIEnv *env);
void deleteEnumMethods(JNIEnv *env);

#endif
