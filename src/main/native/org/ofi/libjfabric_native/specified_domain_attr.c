#include "org_ofi_libjfabric_attributes_SpecifiedDomainAttr.h"
#include "libfabric.h"

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_SpecifiedDomainAttr_initWithDomain
  (JNIEnv *env, jobject jthis, jlong domainHandle)
{
	struct fi_domain_attr *domain_attr = (struct fi_domain_attr*)calloc(1, sizeof(struct fi_domain_attr));

	domain_attr->domain = ((struct fid_domain*)domainHandle);

	domain_attr_list[domain_attr_list_tail] = domain_attr;
	domain_attr_list_tail++;
	return (jlong)domain_attr;
}

JNIEXPORT jlong JNICALL Java_org_ofi_libjfabric_attributes_SpecifiedDomainAttr_getDomain
  (JNIEnv *env, jobject jthis, jlong handle)
{
	return (jlong)(((struct fi_domain_attr*)handle)->domain);
}

JNIEXPORT void JNICALL Java_org_ofi_libjfabric_attributes_SpecifiedDomainAttr_setDomain
  (JNIEnv *env, jobject jthis, jlong domainHandle, jlong handle)
{
	((struct fi_domain_attr*)handle)->domain = ((struct fid_domain*)domainHandle);
}
