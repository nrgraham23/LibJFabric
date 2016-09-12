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

#include "org_ofi_libjfabric_Fabric.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_initFabric
	(JNIEnv *env, jobject jthis, jlong fabricAttrHandle, jlong contextHandle)
{
	struct fid_fabric *fabric = (struct fid_fabric *)calloc(1, sizeof(struct fid_fabric));

	fabric_list[fabric_list_tail] = fabric;
	fabric_list_tail++;

	int res = fi_fabric((struct fi_fabric_attr *)fabricAttrHandle, &fabric, (void *)contextHandle);

	if(res) {
		printf("Error creating fabric: %d\n", res);
		exit(1);
	}
	return (jlong)fabric;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_initFabric2
	(JNIEnv *env, jobject jthis, jlong fabricAttrHandle)
{
	struct fid_fabric *fabric = (struct fid_fabric *)calloc(1, sizeof(struct fid_fabric));

	fabric_list[fabric_list_tail] = fabric;
	fabric_list_tail++;

	int res = fi_fabric((struct fi_fabric_attr *)fabricAttrHandle, &fabric, NULL);

	if(res) {
		printf("Error creating fabric: %d\n", res);
		exit(1);
	}
	return (jlong)fabric;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_createDomainJNI
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlong infoHandle, jlong contextHandle)
{
	struct fid_domain *domain = (struct fid_domain *)calloc(1, sizeof(struct fid_domain));

	domain_list[domain_list_tail] = domain;
	domain_list_tail++;

	int res = ((struct fid_fabric *)fabricHandle)->ops->domain((struct fid_fabric *)fabricHandle,
			(struct fi_info *)infoHandle, &domain, (void *)contextHandle);

	if(res) {
			printf("Error creating domain: %d\n", res);
			exit(1);
		}
	return (jlong)domain;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_createPassiveEP
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlong infoHandle, jlong contextHandle)
{
	struct fid_pep *passive_ep = (struct fid_pep *)calloc(1, sizeof(struct fid_pep));

	passive_ep_list[passive_ep_list_tail] = passive_ep;
	passive_ep_list_tail++;

	int res = ((struct fid_fabric *)fabricHandle)->ops->passive_ep((struct fid_fabric *)fabricHandle,
			(struct fi_info *)infoHandle, &passive_ep, (void *)contextHandle);

	if(res) {
		printf("Error creating passive endpoint: %d\n", res);
		exit(1);
	}
	return (jlong)passive_ep;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_createPassiveEP2
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlong infoHandle)
{
	struct fid_pep *passive_ep = (struct fid_pep *)calloc(1, sizeof(struct fid_pep));

	passive_ep_list[passive_ep_list_tail] = passive_ep;
	passive_ep_list_tail++;

	int res = ((struct fid_fabric *)fabricHandle)->ops->passive_ep((struct fid_fabric *)fabricHandle,
			(struct fi_info *)infoHandle, &passive_ep, NULL);

	if(res) {
		printf("Error creating passive endpoint: %d\n", res);
		exit(1);
	}
	return (jlong)passive_ep;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_eventQueueOpen
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlong eqAttrHandle, jlong contextHandle)
{
	struct fid_eq *event_queue = (struct fid_eq *)calloc(1, sizeof(struct fid_eq));

	event_queue_list[event_queue_list_tail] = event_queue;
	event_queue_list_tail++;

	int res = ((struct fid_fabric *)fabricHandle)->ops->eq_open((struct fid_fabric *)fabricHandle,
			(struct fi_eq_attr *)eqAttrHandle, &event_queue, (void *)contextHandle);

	if(res) {
		printf("Error opening event queue: %d\n", res);
		exit(1);
	}
	return (jlong)event_queue;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_eventQueueOpen2
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlong eqAttrHandle)
{
	struct fid_eq *event_queue = (struct fid_eq *)calloc(1, sizeof(struct fid_eq));

	event_queue_list[event_queue_list_tail] = event_queue;
	event_queue_list_tail++;

	int res = ((struct fid_fabric *)fabricHandle)->ops->eq_open((struct fid_fabric *)fabricHandle,
			(struct fi_eq_attr *)eqAttrHandle, &event_queue, NULL);

	if(res) {
		printf("Error opening event queue: %d\n", res);
		exit(1);
	}
	return (jlong)event_queue;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Fabric_waitOpen
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlong waitAttrHandle)
{
	struct fid_wait *wait = (struct fid_wait *)calloc(1, sizeof(struct fid_wait));

	wait_list[wait_list_tail] = wait;
	wait_list_tail++;

	int res = ((struct fid_fabric *)fabricHandle)->ops->wait_open((struct fid_fabric *)fabricHandle,
			(struct fi_wait_attr *)waitAttrHandle, &wait);

	if(res) {
		printf("Error opening wait: %d\n", res);
		exit(1);
	}
	return (jlong)wait;
}

//TODO: need a test to verify this works.
JNIEXPORT jboolean JNICALL Java_org_ofi_libjfabric_Fabric_tryWait
	(JNIEnv *env, jobject jthis, jlong fabricHandle, jlongArray waitableHandles, jint count)
{
	int i;
	jsize length = (*env)->GetArrayLength(env, waitableHandles);

	long *cWaitableHandles = (long*)calloc(length, sizeof(long));

	jlong *vals = (*env)->GetLongArrayElements(env, waitableHandles, 0);

	for(i = 0; i < length; i++) {
		cWaitableHandles[i] = vals[i];
	}

	int res = ((struct fid_fabric *)fabricHandle)->ops->trywait((struct fid_fabric *)fabricHandle,
			(struct fid **)&cWaitableHandles, count);

	free(cWaitableHandles);
	(*env)->ReleaseLongArrayElements(env, waitableHandles, vals, 0);

	return FI_SUCCESS == res;
}
