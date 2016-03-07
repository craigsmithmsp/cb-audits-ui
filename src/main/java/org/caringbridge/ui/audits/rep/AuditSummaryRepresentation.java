package org.caringbridge.ui.audits.rep;

import java.util.ArrayList;
import java.util.List;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Audit.Status;
import org.caringbridge.ui.audits.model.Finding;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuditSummaryRepresentation {
	private String auditId;
	private String profileName;
	private String profileEmail;
	private String siteId;
	private String siteName;
	private Status status;
	private String lastModifiedUser;
	private List<FindingRepresentation> findings;
	
	public static AuditSummaryRepresentation fromAudit(Audit audit) {
		AuditSummaryRepresentation summary = new AuditSummaryRepresentation();
		summary.setAuditId(audit.getAuditId());
		summary.setSiteId(audit.getTypeId());
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
		return summary;
	}
	
	// Getters and Setters
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileEmail() {
		return profileEmail;
	}
	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	public List<FindingRepresentation> getFindings() {
		return findings;
	}
	public void setFindings(List<FindingRepresentation> findings) {
		this.findings = findings;
	}
}

