package org.ofi.libjfabric.attributes;

public class FabricAttr {
	protected long handle;

	public FabricAttr(String name, String providerName, int providerVersion) {
		this.handle = initFabricAttr(name, providerName, providerVersion);
	}
	private native long initFabricAttr(String name, String providerName, int providerVersion);
	
	public FabricAttr() {
		this.handle = initEmpty();
	}
	private native long initEmpty();

	public FabricAttr(long handle) {
		this.handle = handle;
	}
	
	//gets
	public long getHandle() {
		return this.handle;
	}
	
	public String getName() {
		return getName(this.handle);
	}
	private native String getName(long handle);
	
	public String getProviderName() {
		return getProviderName(this.handle);
	}
	private native String getProviderName( long handle);
	
	public int getProviderVersion() {
		return getProviderVersion(this.handle);
	}
	private native int getProviderVersion(long handle);

	//sets
	public void setName(String name) {
		setName(name, this.handle);
	}
	private native void setName(String name, long handle);
	
	public void setProviderName(String providerName) {
		setProviderName(providerName, this.handle);
	}
	private native void setProviderName(String providerName, long handle);
	
	public void setProviderVersion(int providerVersion) {
		setProviderVersion(providerVersion, this.handle);
	}
	private native void setProviderVersion(int providerVersion, long handle);
}
