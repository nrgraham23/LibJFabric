package org.ofi.libjfabric;

//implementing classes: event queues, completion queues, counters, and wait sets.
//used solely as a way to limit the classes that can be sumbitted to Fabric.tryWait
public interface TryWaitable {
	public long getHandle();
}
