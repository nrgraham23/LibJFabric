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

#include "org_ofi_libjfabric_EndPointSharedOps.h"
#include "libfabric.h"

//testcode
#include <assert.h>
#include <errno.h>
#include <fcntl.h>
#include <netdb.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <poll.h>
#include <stdarg.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/select.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/time.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <limits.h> //end test code

JNIEXPORT jbyteArray JNICALL Java_org_ofi_libjfabric_EndPointSharedOps_getName
	(JNIEnv *env, jobject jthis, jlong epHandle)
{
	int ret;
	size_t len;
	char *name;
	int i;

	fi_getname((struct fid *)epHandle, name, &len); //TODO: check for an unexpected error.  There is an expected error from this call, but I am not sure what it is.
	name = (char *)calloc(len, sizeof(char));
	
	ret = fi_getname((struct fid *)epHandle, name, &len);
	if (ret) {
        fprintf(stderr, "fi_getname error: %s", fi_strerror(-ret));
        exit(-1);
    }
	jbyteArray ara = (*env)->NewByteArray(env, len);
	jbyte testAra[len];
fprintf(stderr, "START PRINT OF NAME\n");	
	for(i = 0; i < len; i++) {
		testAra[i] = name[i];
fprintf(stderr, "    char[i] value: %d    jbyte[i] value: %d\n", (int)name[i], (int)testAra[i]);
	}
	
	//(*env)->SetByteArrayRegion(env, ara, 0, len, (jbyte *)name);
	(*env)->SetByteArrayRegion(env, ara, 0, len, testAra);
	
	//test code
	struct sockaddr_in sa;
	int lent=20;
	char buffer[lent];
	
	inet_ntop(AF_INET, &(name), buffer, lent);
	printf("address:%s\n",buffer);
	
	fprintf(stderr, "fi_getname returned: 0x%x\n", *(uint32_t *)name); //end test code
	free(name);
	
	return ara;
}