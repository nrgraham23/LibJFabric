package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.Version;

public class FabricAttr {
	protected long handle;

	public FabricAttr(String name, String providerName, Version providerVersion) {
		this.handle = initFabricAttr(name, providerName, providerVersion.getMajorVersion(), providerVersion.getMinorVersion());
	}
	private native long initFabricAttr(String name, String providerName, int majorVersion, int minorVersion);
	
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
	private native String getProviderName(long handle);
	
	public Version getProviderVersion() {
		int majorVersion = getMajorProviderVersion(this.handle);
		int minorVersion = getMinorProviderVersion(this.handle);
		
		return new Version(majorVersion, minorVersion);
	}
	private native int getMajorProviderVersion(long handle);
	private native int getMinorProviderVersion(long handle);

	//sets
	public void setName(String name) {
		setName(name, this.handle);
	}
	private native void setName(String name, long handle);
	
	public void setProviderName(String providerName) {
		setProviderName(providerName, this.handle);
	}
	private native void setProviderName(String providerName, long handle);
	
	public void setProviderVersion(Version providerVersion) {
		setProviderVersion(providerVersion.getMajorVersion(), providerVersion.getMinorVersion(), this.handle);
	}
	private native void setProviderVersion(int majorVersion, int minorVersion, long handle);
}
