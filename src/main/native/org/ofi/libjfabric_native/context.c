#include "org_ofi_libjfabric_Context.h"
#include "libfabric.h"

//struct libJFab_context {
//	struct fi_context context;
//	long userContext;
//};

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_Context_initContext
	(JNIEnv *env, jobject jthis)
{
	struct fi_context *context = (struct fi_context*)calloc(1, sizeof(struct fi_context)); //add to lists to cleanup

	return (jlong)context;
}


//void my_send_func(fi_endpoint *ep, blah, blah, this.handle)
//{
//	fi_send(ep, blah, blah, (void *)this.handle)
//}
//
//userContext = context->userContext;
