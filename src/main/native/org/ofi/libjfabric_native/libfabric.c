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

void * dlhandle;

libfabric_enum_globals_t lib_enums;

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_init(JNIEnv *env, jclass jthis) {
	dlhandle = dlopen("/Users/ngraham/libfab_install/lib/libfabric.dylib", RTLD_LAZY);
	if ( dlhandle == NULL ){
		fprintf(stdout,"dlopen failure: %s\n",dlerror());
		exit(1);
	}
	initEnumMethods(env);

	nullListsOut(); //can be removed when we move to a different method of keeping track of C things
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_deleteCachedVars(JNIEnv *env, jclass jthis) {
	deleteEnumMethods(env);

	deleteDomainAttrList();
	deleteFabricAttrList();
	deleteInfoList();
	deleteSimpleAttrList();
	dlclose(dlhandle);
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

	while(simple_attr_list[i] != NULL && i < LISTSIZE) {
		free(simple_attr_list[i]);
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
