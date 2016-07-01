package org.ofi.libjfabric;

public class Version implements Comparable<Version> {
	private int majorVersion;
	private int minorVersion;
	
	public Version(int majorVersion, int minorVersion) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}
	
	@Override
	public String toString() {
		return this.majorVersion + "." + this.minorVersion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!Version.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		Version that = (Version)obj;
		if(this.majorVersion == that.majorVersion && this.minorVersion == that.minorVersion) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Version obj) {
		if(obj == null) {
			throw new NullPointerException();
		}
		Version that = (Version)obj;
		if(this.majorVersion == that.majorVersion) {
			if(this.minorVersion == that.minorVersion) {
				return 0;
			}
			else if(this.minorVersion > that.minorVersion) {
				return 1;
			}
			else { //this.minorVersion < that.minorVersion
				return -1;
			}
		}
		else if(this.majorVersion > that.majorVersion) {
			return 1;
		}
		else { //this.majorVersion < that.majorVersion
			return -1;
		}
	}
	
	public int getMajorVersion() {
		return this.majorVersion;
	}
	public int getMinorVersion() {
		return this.minorVersion;
	}
	
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
}
