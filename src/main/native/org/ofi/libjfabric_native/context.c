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
