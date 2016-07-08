/*
 * Copyright (c) 2015 Los Alamos Nat. Security, LLC. All rights reserved.
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

#include "org_ofi_libjfabric_enums_WaitObj.h"
#include "libfabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getNONE(JNIEnv *env, jclass jthis) {
	return FI_WAIT_NONE;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getUNSPEC(JNIEnv *env, jclass jthis) {
	return FI_WAIT_UNSPEC;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getSET(JNIEnv *env, jclass jthis) {
	return FI_WAIT_SET;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getFD(JNIEnv *env, jclass jthis) {
	return FI_WAIT_FD;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getMUTEXCOND(JNIEnv *env, jclass jthis) {
	return FI_WAIT_MUTEX_COND;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_WaitObj_getCRITSECCOND(JNIEnv *env, jclass jthis) {
	return FI_WAIT_CRITSEC_COND;
}
