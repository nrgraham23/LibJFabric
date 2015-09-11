#ifndef _LIB_FABRIC_H_
#define _LIB_FABRIC_H_

#include <stdlib.h>
#include "org_ofi_libjfabric_LibFabric.h"
#include "fi_list.h"

extern struct fi_domain_attr *domain_attr_list[];
extern int domain_attr_list_tail;

extern struct fi_fabric_attr *fabric_attr_list[];
extern int fabric_attr_list_tail;

extern struct fi_info *info_list[];
extern int info_list_tail;

extern  void *simple_attr_list[]; //any struct that can be freed without additional free calls for pointers
extern int simple_attr_list_tail;

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
void convertJNIString(JNIEnv *env, char **charPointer, jstring javaString);
void deleteDomainAttrList();
void deleteFabricAttrList();
void deleteInfoList();
void deleteSimpleAttrList();
void nullListsOut();

#endif
