package org.caringbridge.ui.audits.service;

import org.caringbridge.ui.audits.exception.ResourceNotFoundException;
import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Profile;
import org.caringbridge.ui.audits.model.Site;
import org.caringbridge.ui.audits.rep.AuditDetailRepresentation;
import org.caringbridge.ui.audits.rep.AuditSummaryRepresentation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditDetailService {
	@Autowired
	private AuditService auditSvc;
	@Autowired
	private SiteService siteSvc;
	@Autowired
	private ProfileService profileSvc;
	
	public AuditDetailRepresentation getDetail(String id) {
		Audit a = auditSvc.getAudit(id);
		if (a == null) {
			throw new ResourceNotFoundException(id);
		}
		return fromAudit(a);
	}
	
	private AuditDetailRepresentation fromAudit(Audit audit) {
		AuditDetailRepresentation detail = new AuditDetailRepresentation();
		BeanUtils.copyProperties(AuditSummaryRepresentation.fromAudit(audit), detail);
		detail.setLastModified(audit.getLastModified());

		// Add site details
		Site site = siteSvc.getSiteById(Integer.valueOf(audit.getTypeId()));
		detail.setSiteName(site.getName());
		detail.setSiteCreatedAt(site.getCreatedAt());
		
		// Add profile details
		Profile profile = profileSvc.getProfile(site.getPrimaryOrganizerId());
		detail.setProfileEmail(profile.getEmail().getAddress());
		detail.setProfileCreatedAt(profile.getCreatedAt());
		detail.setSiblingSiteCount(profile.getSiteCount());
		
		return detail;
	}

	
}
