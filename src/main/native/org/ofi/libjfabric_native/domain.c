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

#include "org_ofi_libjfabric_Domain.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Domain_cqOpen
	(JNIEnv *env, jobject jthis, jlong domHandle, jlong cqAttrHandle, jlong contextHandle)
{
	struct fid_cq *completionQueue;

	cq_list[cq_list_tail] = completionQueue;
	cq_list_tail++;

	int res = fi_cq_open((struct fid_domain *)domHandle,
			(struct fi_cq_attr *)cqAttrHandle, &completionQueue, (void *)contextHandle);

	if(res) {
		printf("Error opening endPoint: %d\n", res);
		exit(1);
	}
	return (jlong)completionQueue;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Domain_cqOpen2
	(JNIEnv *env, jobject jthis, jlong domHandle, jlong cqAttrHandle)
{
	struct fid_cq *completionQueue;

	cq_list[cq_list_tail] = completionQueue;
	cq_list_tail++;

	int res = fi_cq_open((struct fid_domain *)domHandle,
			(struct fi_cq_attr *)cqAttrHandle, &completionQueue, &completionQueue);

	if(res) {
		printf("Error opening endPoint: %d\n", res);
		exit(1);
	}
	return (jlong)completionQueue;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Domain_epOpen
	(JNIEnv *env, jobject jthis, jlong domHandle, jlong infoHandle, jlong contextHandle)
{
	struct fid_ep *endPoint;

	ep_list[ep_list_tail] = endPoint;
	ep_list_tail++;

	int res = fi_endpoint((struct fid_domain *)domHandle,
			(struct fi_info *)infoHandle, &endPoint, (void *)contextHandle);

	if(res) {
		printf("Error opening endPoint: %d\n", res);
		exit(1);
	}
	return (jlong)endPoint;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Domain_epOpen2
	(JNIEnv *env, jobject jthis, jlong domHandle, jlong infoHandle)
{
	struct fid_ep *endPoint;

	ep_list[ep_list_tail] = endPoint;
	ep_list_tail++;

	int res = fi_endpoint((struct fid_domain *)domHandle,
			(struct fi_info *)infoHandle, &endPoint, NULL);

	if(res) {
		printf("Error opening endPoint: %d\n", res);
		exit(1);
	}
	return (jlong)endPoint;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Domain_mrRegister
	(JNIEnv *env, jobject jthis, jlong domHandle, jobject buf, jlong length, jlong access, jlong requestedKey)
{
	void *ptr = getDirectBufferAddress(env, buf);
	
	struct fid_mr *mr; //TODO: A place for memory management code (not adding now because I will just do the free manually).
	
	int res = ((struct fid_domain *)domHandle)->mr->reg((struct fid *)domHandle, ptr, length, access, 0, 
			requestedKey, 0, &mr, NULL);
	
	if(res) {
		printf("Error registering memory region: %d\n", res);
		exit(1);
	}
	return (jlong)mr;
}
