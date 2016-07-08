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

#include "org_ofi_libjfabric_attributes_FabricAttr.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_initFabricAttr
(JNIEnv *env, jobject jthis, jstring jname, jstring providerName, jint majorVersion, jint minorVersion)
{
	struct fi_fabric_attr *fabric_attr = (struct fi_fabric_attr*)calloc(1, sizeof(struct fi_fabric_attr));
	uint32_t version = FI_VERSION((uint32_t)majorVersion, (uint32_t)minorVersion);

	convertJNIString(env, &(fabric_attr->name), jname);
	convertJNIString(env, &(fabric_attr->prov_name), providerName);
	fabric_attr->prov_version = version;

	fabric_attr_list[fabric_attr_list_tail] = fabric_attr;
	fabric_attr_list_tail++;
	return (jlong)fabric_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_initEmpty
	(JNIEnv *env, jobject jthis)
{
	fabric_attr_list[fabric_attr_list_tail] = (struct fi_fabric_attr*)calloc(1, sizeof(struct fi_fabric_attr));
	fabric_attr_list_tail++;
	return (jlong)fabric_attr_list[fabric_attr_list_tail - 1];
}

JNIEXPORT jstring JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_getName
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (*env)->NewStringUTF(env, ((struct fi_fabric_attr*)handle)->name);
}

JNIEXPORT jstring JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_getProviderName
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return (*env)->NewStringUTF(env, ((struct fi_fabric_attr*)handle)->prov_name);
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_getMajorProviderVersion
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return FI_MAJOR(((struct fi_fabric_attr*)handle)->prov_version);
}

JNIEXPORT jint JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_getMinorProviderVersion
	(JNIEnv *env, jobject jthis, jlong handle)
{
	return FI_MINOR(((struct fi_fabric_attr*)handle)->prov_version);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_setName
	(JNIEnv *env, jobject jthis, jstring jname, jlong handle)
{
	convertJNIString(env, &(((struct fi_fabric_attr*)handle)->name), jname);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_setProviderName
	(JNIEnv *env, jobject jthis, jstring providerName, jlong handle)
{
	convertJNIString(env, &(((struct fi_fabric_attr*)handle)->prov_name), providerName);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_FabricAttr_setProviderVersion
	(JNIEnv *env, jobject jthis, jint majorVersion, jint minorVersion, jlong handle)
{
	((struct fi_fabric_attr*)handle)->prov_version = FI_VERSION((uint32_t)majorVersion, (uint32_t)minorVersion);
}
