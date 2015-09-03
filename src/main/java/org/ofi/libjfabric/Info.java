package org.ofi.libjfabric;

import org.ofi.libjfabric.attributes.*;

public class Info {
	private long caps;
	private long mode;
	private int addrFormat;
	private int srcAddrLen;
	private int destAddrLen;
	private TransmitAttr transmitAttr;
	private ReceiveAttr receiveAttr;
	private EndPointAttr endpointAttr;
	private DomainAttr domainAttr;
	private FabricAttr fabricAttr;
	
	public Info(long caps, long mode, int addrFormat, int srcAddrLen, int destAddrLen, TransmitAttr transmitAttr,
			ReceiveAttr receiveAttr, EndPointAttr endpointAttr, DomainAttr domainAttr, FabricAttr fabricAttr) {
		this.caps = caps;
		this.mode = mode;
		this.addrFormat = addrFormat;
		this.srcAddrLen = srcAddrLen;
		this.destAddrLen = destAddrLen;
		this.transmitAttr = transmitAttr;
		this.receiveAttr = receiveAttr;
		this.endpointAttr = endpointAttr;
		this.domainAttr = domainAttr;
		this.fabricAttr = fabricAttr;
	}
	
	public Info() {
		
	}
	
	//gets
	public long getCaps() {
		return this.caps;
	}
	public long getMode() {
		return this.mode;
	}
	public int getAddrFormat() {
		return this.addrFormat;
	}
	public int getSrcAddrLen() {
		return this.srcAddrLen;
	}
	public int getDestAddrLen() {
		return this.destAddrLen;
	}
	public TransmitAttr getTransmitAttr() {
		return this.transmitAttr;
	}
	public ReceiveAttr getRecieveAttr() {
		return this.receiveAttr;
	}
	public EndPointAttr getEndPointAttr() {
		return this.endpointAttr;
	}
	public DomainAttr getDomainAttr() {
		return this.domainAttr;
	}
	public FabricAttr getFabricAttr() {
		return this.fabricAttr;
	}
	
	//sets
	public void setCaps(long caps) {
		this.caps = caps;
	}
	public void setMode(long mode) {
		this.mode = mode;
	}
	public void setAddrFormat(int addrFormat) {
		this.addrFormat = addrFormat;
	}
	public void setSrcAddrLen(int srcAddrLen) {
		this.srcAddrLen = srcAddrLen;
	}
	public void setDestAddrLen(int destAddrLen) {
		this.destAddrLen = destAddrLen;
	}
	public void setTransmitAttr(TransmitAttr transmitAttr) {
		this.transmitAttr = transmitAttr;
	}
	public void setRecieveAttr(ReceiveAttr receiveAttr) {
		this.receiveAttr = receiveAttr;
	}
	public void setEndPointAttr(EndPointAttr endPointattr) {
		this.endpointAttr = endPointattr;
	}
	public void setDomainAttr(DomainAttr domainAttr) {
		this.domainAttr = domainAttr;
	}
	public void setFabricAttr(FabricAttr fabricAttr) {
		this.fabricAttr = fabricAttr;
	}
}
