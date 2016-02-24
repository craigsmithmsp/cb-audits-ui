package org.caringbridge.ui.audits.repository;

import org.caringbridge.ui.audits.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, Integer> {
	
}
