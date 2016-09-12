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
#include "org_ofi_libjfabric_EventQueue.h"
#include "event_queue.h"

JNIEXPORT jobject JNICALL Java_org_ofi_libjfabric_EventQueue_sread
	(JNIEnv *env, jobject jthis, jlong eqHandle, jint timeOut, jlong flags)
{
	uint32_t event;
	uint8_t *buf;
	ssize_t length, ret;
	struct fi_eq_entry entry;
	
	length = fi_eq_sread((struct fid_eq *)eqHandle, &event, &entry, sizeof(entry), -1, FI_PEEK);
	
	buf = (uint8_t *)calloc(length, sizeof(uint8_t));
	
	ret = fi_eq_sread((struct fid_eq *)eqHandle, &event, buf, length, timeOut, flags);
	if(ret < 0) { //TODO: should throw an exception back to the user.  can get from 
		fprintf(stderr, "Error in sread: %ld\n", ret);
		free(buf);
		exit(-1);
	}
	
	switch(event) {
		case FI_MR_COMPLETE:
		case FI_AV_COMPLETE:
			return createEQEntry(env, buf, (int)event);
		case FI_CONNREQ:
		case FI_CONNECTED:
		case FI_SHUTDOWN:
			return createEQCMEntry(env, buf, (int)event, length);
		default:
			return createEQErrEntry(env, buf, (int)event);
	}
}

jobject createEQEntry(JNIEnv *env, uint8_t *buf, int event) {
	free(buf);
	fprintf(stderr, "createEQEntry in event_queue.c not implemented!\n");
	exit(-1);
}

jobject createEQCMEntry(JNIEnv *env, uint8_t *buf, int event, long length) {
	long araLength = length - sizeof(fid_t) - sizeof(struct fi_info *);
	struct fi_eq_cm_entry *cm_entry = (struct fi_eq_cm_entry *)buf;
	
	info_list[info_list_tail] = cm_entry->info; //add info for cleanup later
	info_list_tail++;
	
	jobject eqEvent = (*env)->CallObjectMethod(env, lib_globals.EQEventClass, lib_globals.GetEQEvent, event);
	
	jbyteArray ara = (*env)->NewByteArray(env, araLength);
	(*env)->SetByteArrayRegion(env, ara, 0, araLength, (jbyte *)(cm_entry->data));
	
	jobject eqCMEntry = (*env)->NewObject(env, lib_globals.EQCMEntryClass, lib_globals.EQCMEntryConstructor,
			eqEvent, (jlong)&(cm_entry->fid), (jlong)(cm_entry->info), ara);
	
	free(buf);
			
	return eqCMEntry;
}

jobject createEQErrEntry(JNIEnv *env, uint8_t *buf, int event) {
	free(buf);
	fprintf(stderr, "createEQErrEntry in event_queue.c not implemented!\n");
	exit(-1);
}
