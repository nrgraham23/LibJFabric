package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.Fabric;
import org.ofi.libjfabric.Version;

public class SpecifiedFabricAttr extends FabricAttr {
	
	public SpecifiedFabricAttr(Fabric fabric, String name, String providerName, Version providerVersion) {
		super(name, providerName, providerVersion);
		setFabric(fabric.getHandle(), this.handle);
	}
	
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
