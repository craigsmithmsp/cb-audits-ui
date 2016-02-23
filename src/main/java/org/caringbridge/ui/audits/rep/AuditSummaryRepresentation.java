package org.caringbridge.ui.audits.rep;

import java.util.List;

public class AuditSummaryRepresentation {
	public enum Status { PASSED, QUESTIONABLE, INVALID, FAILED, DEACTIVATED, PURGED }
	public enum Type { SITE, PROFILE };
	private String profileName;
	private String profileEmail;
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

