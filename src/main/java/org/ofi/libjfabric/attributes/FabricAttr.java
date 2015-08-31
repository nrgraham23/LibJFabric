package org.ofi.libjfabric.attributes;

import org.ofi.libjfabric.*;

public class FabricAttr {
	private Fabric fabric;
	private String name;
	private String providerName;
	private int providerVersion;

	public FabricAttr(Fabric fabric, String name, String providerName, int providerVersion) {
		this.fabric = fabric;
		this.name = name;
		this.providerName = providerName;
		this.providerVersion = providerVersion;
	}

	//gets
	public Fabric getFabric() {
		return this.fabric;
	}
	public String getName() {
		return this.name;
	}
	public String getProviderName() {
		return this.providerName;
	}
	public int getProviderVersion() {
		return this.providerVersion;
	}

	//sets
	public void setFabric(Fabric fabric) {
		this.fabric = fabric;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public void setProviderVersion(int providerVersion) {
		this.providerVersion = providerVersion;
	}
}
