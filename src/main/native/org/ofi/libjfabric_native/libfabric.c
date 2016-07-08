#include "libfabric.h"
#include "org_ofi_libjfabric_LibFabric.h"

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

void * dlhandle;

libfabric_globals_t lib_globals;

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_init(JNIEnv *env, jclass jthis) {
	dlhandle = dlopen("/Users/ngraham/libfab_install/lib/libfabric.dylib", RTLD_LAZY);
	if ( dlhandle == NULL ){
		fprintf(stdout,"dlopen failure: %s\n",dlerror());
		exit(1);
	}
	initGlobals(env);

	nullListsOut(); //can be removed when we move to a different method of keeping track of C things
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_deleteCachedVars(JNIEnv *env, jclass jthis) {
	deleteGlobals(env);

	deleteDomainAttrList();
	deleteFabricAttrList();
	deleteInfoList();
	deleteSimpleAttrList();
	deleteFabricList();
	deleteDomainList();
	deletePassiveEPList();
	deleteEventQueueList();
	deleteContextList();
	dlclose(dlhandle);
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
	lib_globals.VersionClass = (*env)->FindClass(env,"org/ofi/libjfabric/Version");
	lib_globals.GetAVType = (*env)->GetStaticMethodID(env,lib_globals.AVTypeClass,"getAVType","(I)Lorg/ofi/libjfabric/enums/AVType;");
	lib_globals.GetEPType = (*env)->GetStaticMethodID(env,lib_globals.EPTypeClass,"getEPType","(I)Lorg/ofi/libjfabric/enums/EPType;");
	lib_globals.GetMRMode = (*env)->GetStaticMethodID(env,lib_globals.MRModeClass,"getMRMode","(I)Lorg/ofi/libjfabric/enums/MRMode;");
	lib_globals.GetProgress = (*env)->GetStaticMethodID(env,lib_globals.ProgressClass,"getProgress","(I)Lorg/ofi/libjfabric/enums/Progress;");
	lib_globals.GetProtocol = (*env)->GetStaticMethodID(env,lib_globals.ProtocolClass,"getProtocol","(I)Lorg/ofi/libjfabric/enums/Protocol;");
	lib_globals.GetResourceMgmt = (*env)->GetStaticMethodID(env,lib_globals.ResourceMgmtClass,"getResourceMgmt","(I)Lorg/ofi/libjfabric/enums/ResourceMgmt;");
	lib_globals.GetThreading = (*env)->GetStaticMethodID(env,lib_globals.ThreadingClass,"getThreading","(I)Lorg/ofi/libjfabric/enums/Threading;");
	lib_globals.GetWaitObj = (*env)->GetStaticMethodID(env,lib_globals.WaitObjClass,"getWaitObj","(I)Lorg/ofi/libjfabric/enums/WaitObj;");
	lib_globals.VersionConstructor = (*env)->GetMethodID(env, lib_globals.VersionClass, "<init>", "(II)V");
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
			info_list[i]->next = NULL;
			info_list[i]->src_addr = NULL;
			info_list[i]->dest_addr = NULL;
			info_list[i]->tx_attr = NULL;
			info_list[i]->rx_attr = NULL;
			info_list[i]->ep_attr = NULL;
			info_list[i]->domain_attr = NULL;
			info_list[i]->fabric_attr = NULL;

			free(info_list[i]);
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

	int (*get_info_ptr)(uint32_t convertedVersion, const char *node, const char *service,
			uint64_t flags, struct fi_info *hints, struct fi_info **info);

	convertJNIString(env, &nodeName, node);
	convertJNIString(env, &serviceName, service);



	*(void **) (&get_info_ptr) = dlsym(dlhandle, "fi_getinfo");
	if ((error = dlerror()) != NULL) {
		fprintf (stderr, "%s\n", error);
		exit(1);
	}

	getInfoRet = (*get_info_ptr)(convertedVersion, nodeName, serviceName, flags, (struct fi_info*)hintsHandle, &resultInfo);

	if (getInfoRet != 0) {
		//printf("fi_getinfo %s\n", fi_strerror(-ret));
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

		info_list[info_list_tail] = curInfo;
		info_list_tail++;

		curInfo = curInfo->next;
	}
	(*env)->SetLongArrayRegion(env, infoArray, 0, infoNum, filler);
	free(nodeName);
	free(serviceName);
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
