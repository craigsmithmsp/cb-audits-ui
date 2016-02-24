package org.caringbridge.ui.audits.service;

import java.util.List;

import org.caringbridge.ui.audits.model.Site;
import org.caringbridge.ui.audits.model.SiteProfile;
import org.caringbridge.ui.audits.repository.SiteProfileRepository;
import org.caringbridge.ui.audits.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteService {
	@Autowired
	SiteRepository siteRepo;
	@Autowired
	SiteProfileRepository siteProfileRepo;

	public Site getSiteById(int siteId) {
		List<Site> sites = siteRepo.findById(siteId);
		if (sites.size() != 1) {
			// TODO: not found or something worse
			throw new RuntimeException("No sites found for site " + siteId);
		}
		addProfileId(sites);
		return sites.get(0);
	}
	
	private void addProfileId(List<Site> sites) {
		for (Site site : sites) {
			List<SiteProfile> siteProfiles = siteProfileRepo.findBySiteId(site.get_id());
			for (SiteProfile siteProfile : siteProfiles) {
				if (SiteProfile.Role.Organizer == siteProfile.getRole()) {
					site.setProfileId(siteProfile.getUserId());
				}
			}
		}
	}
}
