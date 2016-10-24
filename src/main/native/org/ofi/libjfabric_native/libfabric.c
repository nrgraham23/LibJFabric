/*
 * Copyright (c) 2015-2016 Los Alamos Nat. Security, LLC. All rights reserved.
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

#include "libfabric.h"
#include "org_ofi_libjfabric_LibFabric.h"

//testcode
#include <assert.h>
#include <errno.h>
#include <fcntl.h>
#include <netdb.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <poll.h>
#include <stdarg.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/select.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/time.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <limits.h> //end test code

#define LISTSIZE 1000

struct fi_domain_attr *domain_attr_list[LISTSIZE];
int domain_attr_list_tail = 0;

struct fi_fabric_attr *fabric_attr_list[LISTSIZE];
int fabric_attr_list_tail = 0;

struct fi_info *info_list[LISTSIZE];
int info_list_tail = 0;

void *simple_attr_list[LISTSIZE];
int simple_attr_list_tail = 0;

struct fid_fabric *fabric_list[LISTSIZE];
int fabric_list_tail = 0;

struct fid_domain *domain_list[LISTSIZE];
int domain_list_tail = 0;

struct fid_pep *passive_ep_list[LISTSIZE];
int passive_ep_list_tail = 0;

struct fid_eq *event_queue_list[LISTSIZE];
int event_queue_list_tail = 0;

struct fid_wait *wait_list[LISTSIZE];
int wait_list_tail = 0;

struct libjfab_context *context_list[LISTSIZE];
int context_list_tail = 0;

struct fid_ep *ep_list[LISTSIZE];
int ep_list_tail = 0;

struct fi_cq_attr *cq_attr_list[LISTSIZE];
int cq_attr_list_tail = 0;

struct fi_msg *message_list[LISTSIZE];
int message_list_tail = 0;

struct fi_eq_attr *eq_attr_list[LISTSIZE];
int eq_attr_list_tail = 0;

struct fid_cq *cq_list[LISTSIZE];
int cq_list_tail = 0;

libfabric_globals_t lib_globals;

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_init(JNIEnv *env, jclass jthis) {
	initGlobals(env);
	nullListsOut(); //can be removed when we move to a different method of keeping track of C things
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_deleteCachedVars(JNIEnv *env, jclass jthis) {
	deleteGlobals(env);

	deleteDomainAttrList(); //this may be too naive an approach after looking more at libfabric
	deleteFabricAttrList();
	deleteInfoList();
	deleteSimpleAttrList();
	deleteFabricList();
	deleteDomainList();
	deletePassiveEPList();
	deleteEventQueueList();
	deleteContextList();
	deleteEPList();
	deleteCQAttrList();
	deleteMessageList();
	deleteEQAttrList();
	deleteCQList();
}

void initGlobals(JNIEnv *env) {
	lib_globals.AVTypeClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/AVType");
	lib_globals.EPTypeClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/EPType");
	lib_globals.MRModeClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/MRMode");
	lib_globals.ProgressClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/Progress");
	lib_globals.ProtocolClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/Protocol");
	lib_globals.ResourceMgmtClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/ResourceMgmt");
	lib_globals.ThreadingClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/Threading");
	lib_globals.WaitObjClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/WaitObj");
	lib_globals.CQFormatClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/CQFormat");
	lib_globals.CQWaitCondClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/CQWaitCond");
	lib_globals.VersionClass = (*env)->FindClass(env,"org/ofi/libjfabric/Version");
	lib_globals.EQCMEntryClass = (*env)->FindClass(env,"org/ofi/libjfabric/EQCMEntry");
	lib_globals.EQEventClass = (*env)->FindClass(env,"org/ofi/libjfabric/enums/EQEvent");
	lib_globals.GetAVType = (*env)->GetStaticMethodID(env,lib_globals.AVTypeClass,"getAVType","(I)Lorg/ofi/libjfabric/enums/AVType;");
	lib_globals.GetEPType = (*env)->GetStaticMethodID(env,lib_globals.EPTypeClass,"getEPType","(I)Lorg/ofi/libjfabric/enums/EPType;");
	lib_globals.GetMRMode = (*env)->GetStaticMethodID(env,lib_globals.MRModeClass,"getMRMode","(I)Lorg/ofi/libjfabric/enums/MRMode;");
	lib_globals.GetProgress = (*env)->GetStaticMethodID(env,lib_globals.ProgressClass,"getProgress","(I)Lorg/ofi/libjfabric/enums/Progress;");
	lib_globals.GetProtocol = (*env)->GetStaticMethodID(env,lib_globals.ProtocolClass,"getProtocol","(I)Lorg/ofi/libjfabric/enums/Protocol;");
	lib_globals.GetResourceMgmt = (*env)->GetStaticMethodID(env,lib_globals.ResourceMgmtClass,"getResourceMgmt","(I)Lorg/ofi/libjfabric/enums/ResourceMgmt;");
	lib_globals.GetThreading = (*env)->GetStaticMethodID(env,lib_globals.ThreadingClass,"getThreading","(I)Lorg/ofi/libjfabric/enums/Threading;");
	lib_globals.GetWaitObj = (*env)->GetStaticMethodID(env,lib_globals.WaitObjClass,"getWaitObj","(I)Lorg/ofi/libjfabric/enums/WaitObj;");
	lib_globals.VersionConstructor = (*env)->GetMethodID(env, lib_globals.VersionClass, "<init>", "(II)V");
	lib_globals.GetCQFormat = (*env)->GetStaticMethodID(env,lib_globals.CQFormatClass,"getCQFormat","(I)Lorg/ofi/libjfabric/enums/CQFormat;");
	lib_globals.GetCQWaitCond = (*env)->GetStaticMethodID(env,lib_globals.CQWaitCondClass,"getCQWaitCond","(I)Lorg/ofi/libjfabric/enums/CQWaitCond;");
	lib_globals.EQCMEntryConstructor = (*env)->GetMethodID(env, lib_globals.EQCMEntryClass, "<init>", "(Lorg/ofi/libjfabric/enums/EQEvent;JJ[B)V");
	lib_globals.GetEQEvent = (*env)->GetStaticMethodID(env,lib_globals.EQEventClass,"getEQEvent","(I)Lorg/ofi/libjfabric/enums/EQEvent;");
}

void deleteGlobals(JNIEnv *env) {
	(*env)->DeleteGlobalRef(env, lib_globals.AVTypeClass);
	(*env)->DeleteGlobalRef(env, lib_globals.EPTypeClass);
	(*env)->DeleteGlobalRef(env, lib_globals.MRModeClass);
	(*env)->DeleteGlobalRef(env, lib_globals.ProgressClass);
	(*env)->DeleteGlobalRef(env, lib_globals.ProtocolClass);
	(*env)->DeleteGlobalRef(env, lib_globals.ResourceMgmtClass);
	(*env)->DeleteGlobalRef(env, lib_globals.ThreadingClass);
	(*env)->DeleteGlobalRef(env, lib_globals.WaitObjClass);
	(*env)->DeleteGlobalRef(env, lib_globals.VersionClass);
	(*env)->DeleteGlobalRef(env, lib_globals.CQFormatClass);
	(*env)->DeleteGlobalRef(env, lib_globals.CQWaitCondClass);
	(*env)->DeleteGlobalRef(env, lib_globals.VersionClass);
	(*env)->DeleteGlobalRef(env, lib_globals.EQCMEntryClass);
	(*env)->DeleteGlobalRef(env, lib_globals.EQEventClass);
}

void convertJNIString(JNIEnv *env, char **charPointer, jstring javaString) {
	if(*charPointer != NULL) {
		free(*charPointer);
	}
	if(javaString == NULL) {
		return;
	}
	*charPointer = (char*)malloc((int)(*env)->GetStringLength(env, javaString));
	const char *jniName = (*env)->GetStringUTFChars(env, javaString, NULL);
	strcpy(*charPointer, jniName);
	(*env)->ReleaseStringUTFChars(env, javaString, jniName);
}

void deleteDomainAttrList() {
	int i = 0;

	while(i < domain_attr_list_tail) {
		if(domain_attr_list[i] != NULL) {
			domain_attr_list[i]->domain = NULL;
			if(domain_attr_list[i]->name != NULL) {
				free(domain_attr_list[i]->name);
			}
			free(domain_attr_list[i]);
		}
		i++;
	}
}

void deleteFabricAttrList() {
	int i = 0;

	while(i < fabric_attr_list_tail) {
		if(fabric_attr_list[i] != NULL) {
			fabric_attr_list[i]->fabric = NULL;
			if(fabric_attr_list[i]->name != NULL) {
				free(fabric_attr_list[i]->name);
			}
			if(fabric_attr_list[i]->name != NULL) {
				free(fabric_attr_list[i]->prov_name);
			}
			free(fabric_attr_list[i]);
		}
		i++;
	}
}

void deleteInfoList() {
	int i = 0;

	while(i < info_list_tail) {
		if(info_list[i] != NULL) {
			fi_freeinfo(info_list[i]);
		}
		i++;
	}
}

void deleteSimpleAttrList() {
	int i = 0;

	while(simple_attr_list[i] != NULL && i < simple_attr_list_tail) {
		free(simple_attr_list[i]);
		i++;
	}
}

void deleteFabricList() { //TODO: see if there are sub things to free for these
	int i = 0;

	while(i < fabric_list_tail) {
		if(fabric_list[i] != NULL) {
			free(fabric_list[i]);
		}
		i++;
	}
}

void deleteDomainList() {
	int i = 0;

	while(i < domain_list_tail) {
		if(domain_list[i] != NULL) {
			free(domain_list[i]);
		}
		i++;
	}
}

void deletePassiveEPList() {
	int i = 0;

	while(i < passive_ep_list_tail) {
		if(passive_ep_list[i] != NULL) {
			free(passive_ep_list[i]);
		}
		i++;
	}
}

void deleteEventQueueList() {
	int i = 0;

	while(i < event_queue_list_tail) {
		if(event_queue_list[i] != NULL) {
			free(event_queue_list[i]);
		}
		i++;
	}
}

void deleteContextList() {
	int i = 0;

	while(i < context_list_tail) {
		if(context_list[i] != NULL) {
			free(context_list[i]->context);
			free(context_list[i]);
		}
		i++;
	}
}

void deleteEPList() {
	int i = 0;

	while(i < ep_list_tail) {
		if(ep_list[i] != NULL) {
			free(ep_list[i]);
		}
		i++;
	}
}

void deleteCQAttrList() {
	int i = 0;

	while(i < cq_attr_list_tail) {
		if(cq_attr_list[i] != NULL) {
			free(cq_attr_list[i]);
		}
		i++;
	}
}

void deleteMessageList() {
	int i = 0;

	while(i < message_list_tail) {
		if(message_list[i] != NULL) {
			free(message_list[i]);
		}
		i++;
	}
}

void deleteEQAttrList() {
	int i = 0;

	while(i < eq_attr_list_tail) {
		if(eq_attr_list[i] != NULL) {
			if(eq_attr_list[i]->wait_set != NULL) {
				free(eq_attr_list[i]->wait_set);
			}
			free(eq_attr_list[i]);
		}
		i++;
	}
}

void deleteCQList() {
	int i = 0;

	while(i < cq_list_tail) {
		if(cq_list[i] != NULL) {
			free(cq_list[i]);
		}
		i++;
	}
}

void nullListsOut() {
	int i;

	for(i = 0; i < LISTSIZE; i++) {
		domain_attr_list[i] = NULL;
		fabric_attr_list[i] = NULL;
		info_list[i] = NULL;
		simple_attr_list[i] = NULL;
	}
}

JNIEXPORT jobjectArray JNICALL Java_org_ofi_libjfabric_LibFabric_getInfoJNI(JNIEnv *env, jclass jthis,
		jint majorVersion, jint minorVersion, jstring node, jstring service, jlong flags, jlong hintsHandle) {
	int getInfoRet, infoNum = 0;
	char *nodeName = NULL, *error, *serviceName = NULL;
	struct fi_info *resultInfo, *curInfo;
	jlongArray infoArray;

	uint32_t convertedVersion= FI_VERSION((uint32_t)majorVersion, (uint32_t)minorVersion);
if(hintsHandle)
	fprintf(stderr, "EP ATTR TYPE: %d\n", ((struct fi_info*)hintsHandle)->ep_attr->type);
	convertJNIString(env, &nodeName, node);
	convertJNIString(env, &serviceName, service);

	getInfoRet = fi_getinfo(convertedVersion, nodeName, serviceName, flags, (struct fi_info*)hintsHandle, &resultInfo);

	if (getInfoRet != 0) {
		fprintf(stderr, "getInfo error: %s\n", fi_strerror(-getInfoRet));
		exit(1);
	}

	infoNum = getLinkedListLength(&resultInfo);
	if(infoNum == 0) {
		return NULL;
	}

	infoArray = (*env)->NewLongArray(env, infoNum);

	int i;
	jlong filler[infoNum];
	curInfo = resultInfo;
	for(i = 0; i < infoNum; i++) {
		filler[i] = (jlong)curInfo;
		fprintf(stderr, "EP ATTR TYPE: %s\n", ((struct fi_info*)curInfo)->fabric_attr->prov_name);
		info_list[info_list_tail] = curInfo;
		info_list_tail++;

		curInfo = curInfo->next;
	}
	(*env)->SetLongArrayRegion(env, infoArray, 0, infoNum, filler);
	free(nodeName);
	free(serviceName);
	return infoArray;
}

JNIEXPORT jobjectArray JNICALL Java_org_ofi_libjfabric_LibFabric_getInfoJNI2(JNIEnv *env, jclass jthis,
		jint majorVersion, jint minorVersion, jlong flags, jlong hintsHandle) {
	int getInfoRet, infoNum = 0;
	char *error;
	struct fi_info *resultInfo, *curInfo;
	jlongArray infoArray;
	
	if(((struct fi_info*)hintsHandle)->dest_addr != NULL)
		fprintf(stderr, "fi_getname returned: 0x%x\n", *(uint32_t *)((struct fi_info*)hintsHandle)->dest_addr);
	else {
		fprintf(stderr, "DEST_ADDR NULL\n");
	}
	
	int lent=20;
	char buffer[lent];
	
	inet_ntop(AF_INET, &(((struct fi_info*)hintsHandle)->dest_addr), buffer, lent);
	printf("address:%s\n",buffer);
	
	
fprintf(stderr, "FI MSG VALUE: %llu\n", FI_MSG);
	uint32_t convertedVersion= FI_VERSION((uint32_t)majorVersion, (uint32_t)minorVersion);
	if(hintsHandle)
		fprintf(stderr, "EP ATTR TYPE:: %d\n", ((struct fi_info*)hintsHandle)->ep_attr->type);
	getInfoRet = fi_getinfo(convertedVersion, NULL, NULL, flags, (struct fi_info*)hintsHandle, &resultInfo);

	if (getInfoRet != 0) {
		fprintf(stderr, "getInfo error: %s\n", fi_strerror(-getInfoRet));
		exit(1);
	}
	
	infoNum = getLinkedListLength(&resultInfo);
	if(infoNum == 0) {
		return NULL;
	}

	infoArray = (*env)->NewLongArray(env, infoNum);

	int i;
	jlong filler[infoNum];
	curInfo = resultInfo;
	for(i = 0; i < infoNum; i++) {
		filler[i] = (jlong)curInfo;
		fprintf(stderr, "EP ATTR TYPE: %s\n", ((struct fi_info*)curInfo)->fabric_attr->prov_name);
		info_list[info_list_tail] = curInfo;
		info_list_tail++;

		curInfo = curInfo->next;
	}
	(*env)->SetLongArrayRegion(env, infoArray, 0, infoNum, filler);
	return infoArray;
}

int getLinkedListLength(struct fi_info **infoLinkedList) {
	int length = 0;

	struct fi_info *cur = *infoLinkedList;

	while(cur != NULL) {
		length++;
		cur = cur->next;
	}

	return length;
}

void* getDirectBufferAddress(JNIEnv *env, jobject buffer)
{
	/* Allow NULL buffers to send/recv 0 items as control messages. */
	return buffer == NULL ? NULL : (*env)->GetDirectBufferAddress(env, buffer);
}
