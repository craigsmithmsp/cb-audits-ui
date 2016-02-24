package org.caringbridge.ui.audits.repository;

import java.util.List;

import org.caringbridge.ui.audits.model.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface SiteRepository extends MongoRepository<Site, Integer> {
	@Query("{ '_id' : ?0 }")
	List<Site> findById(int _id);
}
