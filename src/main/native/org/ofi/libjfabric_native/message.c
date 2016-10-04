/*
 * Copyright (c) 2016 Los Alamos Nat. Security, LLC. All rights reserved.
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
#include "org_ofi_libjfabric_Message.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Message_initMessage
	(JNIEnv *env, jobject jthis, jobject buffer, jint length, jint iovCount, jlong addr, jlong contextHandle)
{
	struct iovec iov;
	
	struct fi_msg *message = (struct fi_msg *)calloc(1, sizeof(struct fi_msg));
	
	message_list[message_list_tail] = message;
	message_list_tail++;
	
	void *ptr = getDirectBufferAddress(env, buffer);
	
	iov.iov_base = ptr;
	iov.iov_len = length;
	
	message->msg_iov = &iov;
	message->iov_count = 1;
	message->addr = addr;
	message->context = (void *)contextHandle;
	
	return (jlong)message;
}