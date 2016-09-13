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

#include "org_ofi_libjfabric_enums_EQEvent.h"
#include "libfabric.h"

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EQEvent_getNOTIFY(JNIEnv *env, jclass jthis) {
	return FI_NOTIFY;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EQEvent_getCONNREQ(JNIEnv *env, jclass jthis) {
	return FI_CONNREQ;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EQEvent_getCONNECTED(JNIEnv *env, jclass jthis) {
	return FI_CONNECTED;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EQEvent_getSHUTDOWN(JNIEnv *env, jclass jthis) {
	return FI_SHUTDOWN;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EQEvent_getMRCOMPLETE(JNIEnv *env, jclass jthis) {
	return FI_MR_COMPLETE;
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_enums_EQEvent_getAVCOMPLETE(JNIEnv *env, jclass jthis) {
	return FI_AV_COMPLETE;
}