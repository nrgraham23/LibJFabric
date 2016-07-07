#ifndef _LIB_FABRIC_H_
#define _LIB_FABRIC_H_

#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>

#include "rdma/fabric.h"
#include "rdma/fi_domain.h"
#include "rdma/fi_endpoint.h"
#include "rdma/fi_errno.h"

#include "org_ofi_libjfabric_LibFabric.h"

/*NOTE: The intent is to eventually replace these lists with linked lists!*/
extern struct fi_domain_attr *domain_attr_list[];
extern int domain_attr_list_tail;

extern struct fi_fabric_attr *fabric_attr_list[];
extern int fabric_attr_list_tail;

extern struct fi_info *info_list[];
extern int info_list_tail;

extern void *simple_attr_list[]; //any attribute struct that can be freed without additional free calls for pointers
extern int simple_attr_list_tail;

extern struct fid_fabric *fabric_list[];
extern int fabric_list_tail;

extern struct fid_domain *domain_list[];
extern int domain_list_tail;

extern struct fid_pep *passive_ep_list[];
extern int passive_ep_list_tail;

extern struct fid_eq *event_queue_list[];
extern int event_queue_list_tail;

extern struct fid_wait *wait_list[];
extern int wait_list_tail;

extern void *dlhandle;

typedef struct {
	jclass AVTypeClass;
	jclass EPTypeClass;
	jclass MRModeClass;
	jclass ProgressClass;
	jclass ProtocolClass;
	jclass ResourceMgmtClass;
	jclass ThreadingClass;
	jclass VersionClass;
	jclass WaitObjClass;
	jmethodID GetAVType;
	jmethodID GetEPType;
	jmethodID GetMRMode;
	jmethodID GetProgress;
	jmethodID GetProtocol;
	jmethodID GetResourceMgmt;
	jmethodID GetThreading;
	jmethodID GetWaitObj;
	jmethodID VersionConstructor;
} libfabric_globals_t;

extern libfabric_globals_t lib_globals;

void initGlobals(JNIEnv *env);
void deleteGlobals(JNIEnv *env);
void convertJNIString(JNIEnv *env, char **charPointer, jstring javaString);
void deleteDomainAttrList();
void deleteFabricAttrList();
void deleteInfoList();
void deleteSimpleAttrList();
void deleteFabricList();
void deleteDomainList();
void deletePassiveEPList();
void deleteEventQueueList();
void nullListsOut();
int getLinkedListLength(struct fi_info **resultInfo);

#endif
