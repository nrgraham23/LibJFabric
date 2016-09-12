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

#include "org_ofi_libjfabric_Context.h"
#include "libfabric.h"

libjfab_context_t libjfab_context;

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Context_initContext
	(JNIEnv *env, jobject jthis, jlong userContext)
{
	struct libjfab_context *lib_cont = (struct libjfab_context *)calloc(1, sizeof(struct libjfab_context));
	libjfab_context.context = (struct fi_context *)calloc(1, sizeof(struct fi_context));

	context_list[context_list_tail] = lib_cont;
	context_list_tail++;

	lib_cont->userContext = (long)userContext;

	return (jlong)lib_cont->context;
}

//void my_send_func(fi_endpoint *ep, blah, blah, this.handle)
//{
//	fi_send(ep, blah, blah, (void *)this.handle)
//}
//
//userContext = context->userContext;


//container_of(ptr, type, field) ((type *) ((char *)ptr - offsetof(type, field)))
