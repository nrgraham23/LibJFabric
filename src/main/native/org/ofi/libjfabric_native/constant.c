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

#include "org_ofi_libjfabric_Constant.h"
#include "constant.h"
#include "libfabric.h"

void setLongField(JNIEnv *env, jclass c, jobject jthis, char *field, jlong value)
{
	jfieldID id = (*env)->GetFieldID(env, c, field, "J");
	(*env)->SetLongField(env, jthis, id, value);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_Constant_setConstant
	(JNIEnv *env, jobject jthis)
{
	jclass c = (*env)->GetObjectClass(env, jthis);
	
	setLongField(env, c, jthis, "FI_CONTEXT", FI_CONTEXT);
	setLongField(env, c, jthis, "FI_LOCAL_MR", FI_LOCAL_MR);
	setLongField(env, c, jthis, "FI_SOURCE", FI_SOURCE);
	setLongField(env, c, jthis, "FI_MSG", FI_MSG);
	setLongField(env, c, jthis, "FI_TRANSMIT", FI_TRANSMIT);
	setLongField(env, c, jthis, "FI_RECV", FI_RECV);
	setLongField(env, c, jthis, "FI_SEND", FI_SEND);
	setLongField(env, c, jthis, "FI_INJECT", FI_INJECT);
	setLongField(env, c, jthis, "FI_TRANSMIT_COMPLETE", FI_TRANSMIT_COMPLETE);
}
