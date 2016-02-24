package org.caringbridge.ui.audits.rep;

import java.util.Date;

import org.caringbridge.ui.audits.model.Finding;

public class FindingRepresentation {
    private String ruleName;
    private Finding.Status status;
    private Date lastRunDate;
    private String details;
    
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public Finding.Status getStatus() {
		return status;
	}
	public void setStatus(Finding.Status status) {
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
