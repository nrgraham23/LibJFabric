#include "org_ofi_libjfabric_LibFabric.h"
#include "lib_fabric.h"

struct fi_domain_attr *domain_attr_list[1000];
int domain_attr_list_tail = 0;

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_LibFabric_init(JNIEnv *env, jclass jthis) {

}
