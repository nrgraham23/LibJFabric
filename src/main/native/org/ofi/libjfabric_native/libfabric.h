/*
 * Copyright (c) 2015 Los Alamos Nat. Security, LLC. All rights reserved.
 *
 * This software is available to you under a choice of one of two
 * licenses.  You may choose to be licensed under the terms of the GNU
 * General Public License (GPL) Version 2, available from the file
 * COPYING in the main directory of this source tree, or the
 * BSD license below:
 *
 *     Redistribution and use in source and binary forms, with or
 *     without modification, are permitted provided that the following
 *     conditions are met:
 *
 *      - Redistributions of source code must retain the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer.
 *
 *      - Redistributions in binary form must reproduce the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

#ifndef _LIB_FABRIC_H_
#define _LIB_FABRIC_H_

#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>

#include "rdma/fabric.h"
#include "rdma/fi_domain.h"
#include "rdma/fi_endpoint.h"
#include "rdma/fi_errno.h"
#include "rdma/fi_eq.h"
#include "rdma/fi_cm.h"
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

extern struct libjfab_context *context_list[];
extern int context_list_tail;

extern struct fid_ep *ep_list[];
extern int ep_list_tail;

extern struct fi_cq_attr *cq_attr_list[];
extern int cq_attr_list_tail;

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
	jclass CQFormatClass;
	jclass CQWaitCondClass;
	jclass EQCMEntryClass;
	jclass EQEventClass;
	jmethodID GetAVType;
	jmethodID GetEPType;
	jmethodID GetMRMode;
	jmethodID GetProgress;
	jmethodID GetProtocol;
	jmethodID GetResourceMgmt;
	jmethodID GetThreading;
	jmethodID GetWaitObj;
	jmethodID VersionConstructor;
	jmethodID GetCQFormat;
	jmethodID GetCQWaitCond;
	jmethodID EQCMEntryConstructor;
	jmethodID GetEQEvent;
} libfabric_globals_t;

extern libfabric_globals_t lib_globals;

typedef struct libjfab_context {
	struct fi_context *context;
	long userContext;
} libjfab_context_t;

extern libjfab_context_t libjfab_context;

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
void deleteContextList();
void deleteEPList();
void deleteCQAttrList();
void nullListsOut();
int getLinkedListLength(struct fi_info **resultInfo);
void* getDirectBufferAddress(JNIEnv *env, jobject buffer);

#endif
