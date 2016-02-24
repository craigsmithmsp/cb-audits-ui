package org.caringbridge.ui.audits.service;

import org.caringbridge.ui.audits.model.Profile;
import org.caringbridge.ui.audits.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	@Autowired
	private ProfileRepository profileRepo;
	
	public Profile getProfileBySiteId(int id) {
		return profileRepo.findOne(id);
	}

}
