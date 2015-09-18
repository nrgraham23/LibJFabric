#include "jfabric.h"
#include "org_ofi_libjfabric_Fabric.h"
#include "libfabric.h"

void *dlhandle;

JNIEXPORT jobjectArray JNICALL Java_org_ofi_libjfabric_Fabric_getInfoJNI(JNIEnv *env, jclass jthis,
		jdouble version, jstring node, jstring service, jlong flags, jlong hintsHandle) {
	int getInfoRet, infoNum = 0;
	char *nodeName, *error, *serviceName;
	struct fi_info *resultInfo, *curInfo;
	jlongArray infoArray;

	int (*get_info_ptr)(uint32_t version, const char *node, const char *service,
			uint64_t flags, struct fi_info *hints, struct fi_info **info);

	convertJNIString(env, &nodeName, node);
	convertJNIString(env, &serviceName, service);



	*(void **) (&get_info_ptr) = dlsym(dlhandle, "fi_getinfo");
	if ((error = dlerror()) != NULL) {
		fprintf (stderr, "%s\n", error);
		exit(1);
	}
	getInfoRet = (*get_info_ptr)(version, nodeName, serviceName, flags, (struct fi_info*)hintsHandle, &resultInfo);
	if (getInfoRet != 0) {
		//printf("fi_getinfo %s\n", fi_strerror(-ret));
		//goto out;
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
