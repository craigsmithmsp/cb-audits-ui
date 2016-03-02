package org.caringbridge.ui.audits.service;

import java.util.ArrayList;
import java.util.List;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Audit.Status;
import org.caringbridge.ui.audits.model.Audit.Type;
import org.caringbridge.ui.audits.model.Finding;
import org.caringbridge.ui.audits.model.Profile;
import org.caringbridge.ui.audits.model.Site;
import org.caringbridge.ui.audits.rep.AuditSummaryRepresentation;
import org.caringbridge.ui.audits.rep.AuditsUpdateCallResponse;
import org.caringbridge.ui.audits.rep.FindingRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuditSummaryService {
	Logger log = LoggerFactory.getLogger(AuditSummaryService.class);

	@Autowired
	private AuditService auditSvc;
	@Autowired
	private SiteService siteSvc;
	@Autowired
	private ProfileService profileSvc;
	
	public List<AuditSummaryRepresentation> getSiteSummariesByStatus(Status status) {
		List<Audit> audits = auditSvc.getAuditsByStatusAndType(status, Type.SITE);
		if (audits == null || audits.size() == 0) {
			log.info("No audits found with status of " + status + " and type of " + Type.SITE);
			return new ArrayList<AuditSummaryRepresentation>();
		}
		List<AuditSummaryRepresentation> summaries = new ArrayList<AuditSummaryRepresentation>();
		for (Audit audit: audits) {
			AuditSummaryRepresentation summary = new AuditSummaryRepresentation();
			summary.setSiteId(Integer.valueOf(audit.getTypeId()));
			summary.setAuditId(audit.getAuditId());
			// Add site details
			Site site = siteSvc.getSiteById(Integer.valueOf(audit.getTypeId()));
			summary.setSiteName(site.getName());
			summary.setStatus(audit.getStatus());
			summary.setLastModifiedUser(audit.getLastModifiedUser());
			
			//Add findings
			List<FindingRepresentation> findingReps = new ArrayList<FindingRepresentation>();
			for (Finding f : audit.getFindings()) {
				FindingRepresentation fr = new FindingRepresentation();
				fr.setDetails(f.getDetails());
				fr.setLastRunDate(f.getLastRunDate());
				fr.setRuleName(f.getRuleName());
				fr.setStatus(f.getStatus());
				findingReps.add(fr);
			}
			summary.setFindings(findingReps);
			
			// Add profile details
			Profile profile = profileSvc.getProfile(site.getPrimaryOrganizerId());
			summary.setProfileEmail(profile.getEmail().getAddress());
			summaries.add(summary);
		}
		return summaries;
	}

    /**
     * Updates the status of one Audit object to the specified status by using 
     * the external update operation of the audits services
     * @param auditIds The String array of id of the audit to update
     * @param status The new status of the audit object to use in the update
     * @return List of AuditsUpdateCallResponse objects with the result of the update operation
     */
    public List<AuditsUpdateCallResponse> updateAuditStatus(String[] auditIds, Status status) {
        return auditSvc.update(auditIds,status);
    }

    /**
     * @return the auditSvc
     */
    public AuditService getAuditSvc() {
        return auditSvc;
    }

    /**
     * @param auditSvc the auditSvc to set
     */
    public void setAuditSvc(AuditService auditSvc) {
        this.auditSvc = auditSvc;
    }
    
}
