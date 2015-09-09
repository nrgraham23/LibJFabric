package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.Fabric;

public class SpecifiedFabricAttr extends FabricAttr {
	
	public SpecifiedFabricAttr(Fabric fabric, String name, String providerName, int providerVersion) {
		this.handle = initSpecifiedFabricAttr(fabric.getHandle(), name, providerName, providerVersion);
	}
	
	private native long initSpecifiedFabricAttr(long fabricHandle, String name, String providerName, int providerVersion);
	
	public SpecifiedFabricAttr(Fabric fabric) {
		this.handle = initWithFabric(fabric.getHandle());
	}
	private native long initWithFabric(long handle);
	
	//get
	public Fabric getFabric() {
		return new Fabric(getFabric(this.handle));
	}
	private native long getFabric(long handle);
	
	//set
	public void setFabric(Fabric fabric) {
		setFabric(fabric.getHandle(), this.handle);
	}
	private native void setFabric(long fabricHandle, long FabricAttrHandle);
}
