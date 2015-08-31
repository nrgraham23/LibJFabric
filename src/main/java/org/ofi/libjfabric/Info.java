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
}
