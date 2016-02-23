package org.caringbridge.ui.audits.rep;

import java.util.Date;

public class FindingRepresentation {
    private String ruleName;
    private AuditSummaryRepresentation.Status status;
    private Date lastRunDate;
    private String details;
    
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public AuditSummaryRepresentation.Status getStatus() {
		return status;
	}
	public void setStatus(AuditSummaryRepresentation.Status status) {
		this.status = status;
	}
	public Date getLastRunDate() {
		return lastRunDate;
	}
	public void setLastRunDate(Date lastRunDate) {
		this.lastRunDate = lastRunDate;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
