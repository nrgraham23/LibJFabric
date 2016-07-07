package org.ofi.libjfabric;

import org.ofi.libjfabric.attributes.EventQueueAttr;
import org.ofi.libjfabric.attributes.FabricAttr;
import org.ofi.libjfabric.attributes.WaitAttr;

public class Fabric {
	private long handle;
	FabricAttr fabricAttr;
	Context context;
	
	public Fabric(FabricAttr fabricAttr, Context context) {
		this.fabricAttr = fabricAttr;
		this.context = context;
		this.handle = initFabric(fabricAttr.getHandle(), context.getHandle());
	}
	private native long initFabric(long fabricAttrHandle, long contextHandle);
	
	public Fabric(long handle) {
		this.handle = handle;
	}
	
	public long getHandle() {
		return this.handle;
	}
	
	public Domain createDomain(Info info, Context context) {
		return new Domain(createDomainJNI(this.handle, info.getHandle(), context.getHandle()));
	}
	private native long createDomainJNI(long fabricHandle, long infoHandle, long contextHandle);
	
	public PassiveEndPoint createPassiveEndPoint(Info info, Context context) {
		return new PassiveEndPoint(createPassiveEP(this.handle, info.getHandle(), context.getHandle()));
	}
	private native long createPassiveEP(long fabricHandle, long infoHandle, long contextHandle);
	
	public EventQueue eventQueueOpen(EventQueueAttr eventQueueAttr, Context context) {
		return new EventQueue(eventQueueOpen(this.handle, eventQueueAttr.getHandle(), context.getHandle()));
	}
	private native long eventQueueOpen(long fabricHandle, long eventQueueAttrHandle, long contextHandle);
	
	public Wait waitOpen(WaitAttr waitAttr) {
		return new Wait(waitOpen(this.handle, waitAttr.getHandle()));
	}
	private native long waitOpen(long fabricHandle, long waitAttrHandle);
	
	/* for trywait, can only send event queues, completion queues, counters, and wait sets
	/  all underlying wait objects must be of the same type, but there is currently no
	/ err checking in place to ensure this. */
	public boolean tryWait(TryWaitable[] tryWaitables, int count) {
		long[] waitablesHandles = new long[tryWaitables.length];
		for(int i = 0; i < tryWaitables.length; i++) {
			waitablesHandles[i] = tryWaitables[i].getHandle();
		}
		return tryWait(this.handle, waitablesHandles, count);
	}
	private native boolean tryWait(long fabricHandle, long[] waitablesHandles, int count);
}
