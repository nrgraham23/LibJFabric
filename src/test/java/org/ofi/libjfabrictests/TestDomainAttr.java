package org.ofi.libjfabrictests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ofi.libjfabric.LibFabric;
import org.ofi.libjfabric.attributes.DomainAttr;
import org.ofi.libjfabric.enums.*;

public class TestDomainAttr {
	DomainAttr domainAttr, emptyDomainAttr;
	
	@Before
	public void setUp() throws Exception {
		LibFabric.load();
		domainAttr = new DomainAttr("testDomain", Threading.FI_THREAD_SAFE, Progress.FI_PROGRESS_AUTO, Progress.FI_PROGRESS_AUTO, ResourceMgmt.FI_RM_ENABLED, 
				AVType.FI_AV_MAP, MRMode.FI_MR_MODE_BASIC, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51);
		emptyDomainAttr = new DomainAttr();
	}

	@Test
	public void testGetName() {
		assertEquals(domainAttr.getName(), "testDomain");
	}

	@Test
	public void testGetThreading() {
		assertEquals(domainAttr.getThreading(), Threading.FI_THREAD_SAFE);
	}

	@Test
	public void testGetCntrlProgress() {
		assertEquals(domainAttr.getCntrlProgress(), Progress.FI_PROGRESS_AUTO);
	}

	@Test
	public void testGetDataProgress() {
		assertEquals(domainAttr.getDataProgress(), Progress.FI_PROGRESS_AUTO);
	}

	@Test
	public void testGetResourceMgmt() {
		assertEquals(domainAttr.getResourceMgmt(), ResourceMgmt.FI_RM_ENABLED);
	}

	@Test
	public void testGetAvType() {
		assertEquals(domainAttr.getAvType(), AVType.FI_AV_MAP);
	}

	@Test
	public void testGetMRMode() {
		assertEquals(domainAttr.getMRMode(), MRMode.FI_MR_MODE_BASIC);
	}

	@Test
	public void testGetMrKeySize() {
		assertEquals(domainAttr.getMrKeySize(), 42);
	}

	@Test
	public void testGetCQDataSize() {
		assertEquals(domainAttr.getCQDataSize(), 43);
	}

	@Test
	public void testGetCQCnt() {
		assertEquals(domainAttr.getCQCnt(), 44);
	}

	@Test
	public void testGetEndPointCnt() {
		assertEquals(domainAttr.getEndPointCnt(), 45);
	}

	@Test
	public void testGetTxCtxCnt() {
		assertEquals(domainAttr.getTxCtxCnt(), 46);
	}

	@Test
	public void testGetRxCtxCnt() {
		assertEquals(domainAttr.getRxCtxCnt(), 47);
	}

	@Test
	public void testGetMaxEpTxCtx() {
		assertEquals(domainAttr.getMaxEpTxCtx(), 48);
	}

	@Test
	public void testGetMaxEpRxCtx() {
		assertEquals(domainAttr.getMaxEpRxCtx(), 49);
	}

	@Test
	public void testGetMaxEpStxCtx() {
		assertEquals(domainAttr.getMaxEpStxCtx(), 50);
	}

	@Test
	public void testGetMaxEpSrxCtx() {
		assertEquals(domainAttr.getMaxEpSrxCtx(), 51);
	}

	@Test
	public void testSetName() {
		emptyDomainAttr.setName("newName");
		assertEquals(emptyDomainAttr.getName(), "newName");
	}

	@Test
	public void testSetThreading() {
		emptyDomainAttr.setThreading(Threading.FI_THREAD_COMPLETION);
		assertEquals(emptyDomainAttr.getThreading(), Threading.FI_THREAD_COMPLETION);
	}

	@Test
	public void testSetCntrlProgress() {
		emptyDomainAttr.setCntrlProgress(Progress.FI_PROGRESS_MANUAL);
		assertEquals(emptyDomainAttr.getCntrlProgress(), Progress.FI_PROGRESS_MANUAL);
	}

	@Test
	public void testSetDataProgress() {
		emptyDomainAttr.setDataProgress(Progress.FI_PROGRESS_MANUAL);
		assertEquals(emptyDomainAttr.getDataProgress(), Progress.FI_PROGRESS_MANUAL);
	}

	@Test
	public void testSetResourceMgmt() {
		emptyDomainAttr.setResourceMgmt(ResourceMgmt.FI_RM_DISABLED);
		assertEquals(emptyDomainAttr.getResourceMgmt(), ResourceMgmt.FI_RM_DISABLED);
	}

	@Test
	public void testSetAVType() {
		emptyDomainAttr.setAVType(AVType.FI_AV_TABLE);
		assertEquals(emptyDomainAttr.getAvType(), AVType.FI_AV_TABLE);
	}

	@Test
	public void testSetMRMode() {
		emptyDomainAttr.setMRMode(MRMode.FI_MR_MODE_SCALABLE);
		assertEquals(emptyDomainAttr.getMRMode(), MRMode.FI_MR_MODE_SCALABLE);
	}

	@Test
	public void testSetMRKeySize() {
		emptyDomainAttr.setMRKeySize(64);
		assertEquals(emptyDomainAttr.getMrKeySize(), 64);
	}

	@Test
	public void testSetCQDataSize() {
		emptyDomainAttr.setCQDataSize(128);
		assertEquals(emptyDomainAttr.getCQDataSize(), 128);
	}

	@Test
	public void testSetCQCnt() {
		emptyDomainAttr.setCQCnt(16);
		assertEquals(emptyDomainAttr.getCQCnt(), 16);
	}

	@Test
	public void testSetEndpointCnt() {
		emptyDomainAttr.setEndpointCnt(8);
		assertEquals(emptyDomainAttr.getEndPointCnt(), 8);
	}

	@Test
	public void testSetTxCtxCnt() {
		emptyDomainAttr.setTxCtxCnt(16);
		assertEquals(emptyDomainAttr.getTxCtxCnt(), 16);
	}

	@Test
	public void testSetRxCtxCnt() {
		emptyDomainAttr.setRxCtxCnt(32);
		assertEquals(emptyDomainAttr.getRxCtxCnt(), 32);
	}

	@Test
	public void testSetMaxEpTxCtx() {
		emptyDomainAttr.setMaxEpTxCtx(64);
		assertEquals(emptyDomainAttr.getMaxEpTxCtx(), 64);
	}

	@Test
	public void testSetMaxEpRxCtx() {
		emptyDomainAttr.setMaxEpRxCtx(8);
		assertEquals(emptyDomainAttr.getMaxEpRxCtx(), 8);
	}

	@Test
	public void testSetMaxEpStxCtx() {
		emptyDomainAttr.setMaxEpStxCtx(128);
		assertEquals(emptyDomainAttr.getMaxEpStxCtx(), 128);
	}

	@Test
	public void testSetMaxEpSrxCtx() {
		emptyDomainAttr.setMaxEpSrxCtx(4);
		assertEquals(emptyDomainAttr.getMaxEpSrxCtx(), 4);
	}

}
