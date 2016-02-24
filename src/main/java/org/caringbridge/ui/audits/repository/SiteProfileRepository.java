package org.caringbridge.ui.audits.repository;

import java.util.List;

import org.caringbridge.ui.audits.model.SiteProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteProfileRepository extends MongoRepository<SiteProfile, String> {
	public List<SiteProfile> findBySiteId(Integer siteId);
}
