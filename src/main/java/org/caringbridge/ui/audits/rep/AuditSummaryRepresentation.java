package org.caringbridge.ui.audits.rep;

import java.util.List;

import org.caringbridge.ui.audits.model.Audit.Status;

public class AuditSummaryRepresentation {
	private String profileName;
	private String profileEmail;
	private int siteId;
	private String siteName;
	private Status status;
	private List<FindingRepresentation> findings;
	
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
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
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
	public List<FindingRepresentation> getFindings() {
		return findings;
	}
	public void setFindings(List<FindingRepresentation> findings) {
		this.findings = findings;
	}
	
}

