#include <jni.h>
#include <stdio.h>
#include "org_ofi_libfab_java_bindings_HelloJNI.h"

// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT void JNICALL Java_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Hello World!!\n");
   return;
}
