package org.caringbridge.ui.audits.repository;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.caringbridge.ui.audits.CbApplication;
import org.caringbridge.ui.audits.model.Site;
import org.caringbridge.ui.audits.model.SiteProfile;
import org.caringbridge.ui.audits.service.AuditService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CbApplication.class)
@WebAppConfiguration
public class SiteRepositoryTest {
	@Autowired
	private SiteRepository siteRepo;
	@Autowired
	private SiteProfileRepository siteProfileRepo;

	@Test
	public void test() {
		assertNotNull(siteProfileRepo);
		assertNotNull(siteRepo);
		long cnt = siteProfileRepo.count();
		assertNotEquals("Expected a seeded database.", 0L, cnt);
		for (SiteProfile sp: siteProfileRepo.findAll()) {
			List<Site> sites = siteRepo.findById(sp.getSiteId());
			assertNotNull(sites);
			assertEquals(1, sites.size());
			System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			System.out.println("role: " + sp.getRole());
			System.out.println("profileId: " + sp.getUserId());
			System.out.println("siteId: " + sp.getSiteId());
			System.out.println("site name: " + sites.get(0).getName());
			
		}
	}
	
}
