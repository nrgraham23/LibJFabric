package org.ofi.libjfabric;

import org.ofi.libjfabric.EventEntry;
import org.ofi.libjfabric.enums.EQEvent;

public class EQEntry extends EventEntry {
	
	public EQEntry(EQEvent event, long handle) {
		super(event);
	}
}
