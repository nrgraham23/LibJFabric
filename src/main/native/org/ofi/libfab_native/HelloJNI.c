#include <jni.h>
#include <stdio.h>
#include "org_ofi_libfabjavabindings_HelloJNI.h"

// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT void JNICALL Java_org_ofi_libfabjavabindings_HelloJNI_sayHello(JNIEnv *env, jclass jthis) {
   printf("Hello World!!\n");
   return;
}
