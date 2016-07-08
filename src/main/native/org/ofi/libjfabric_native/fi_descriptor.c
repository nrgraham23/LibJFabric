#include "org_ofi_libjfabric_FIDescriptor.h"
#include "libfabric.h"

JNIEXPORT jboolean JNICALL Java_org_ofi_libjfabric_FIDescriptor_close
	(JNIEnv *env, jobject jthis, jlong handle)
{
	int res = ((struct fid *)handle)->ops->close((struct fid *)handle);

	return FI_SUCCESS == res;
}

JNIEXPORT jboolean JNICALL Java_org_ofi_libjfabric_FIDescriptor_bind
	(JNIEnv *env, jobject jthis, jlong thisHandle, jlong bindToHandle, jlong flags)
{
	int res = ((struct fid *)thisHandle)->ops->bind((struct fid *)thisHandle,
			(struct fid *)bindToHandle, (uint64_t)flags);

		return FI_SUCCESS == res;
}
