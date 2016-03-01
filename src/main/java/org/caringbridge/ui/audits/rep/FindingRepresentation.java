package org.caringbridge.ui.audits.rep;

import java.time.LocalDateTime;

import org.caringbridge.ui.audits.model.Finding;
import org.caringbridge.ui.audits.util.LocalDateISODeserializer;
import org.caringbridge.ui.audits.util.LocalDateISOSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class FindingRepresentation {
    private String ruleName;
    private Finding.Status status;
    private LocalDateTime lastRunDate;
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
    @JsonSerialize(using=LocalDateISOSerializer.class)
	public LocalDateTime getLastRunDate() {
		return lastRunDate;
	}
    @JsonDeserialize(using=LocalDateISODeserializer.class)
	public void setLastRunDate(LocalDateTime lastRunDate) {
		this.lastRunDate = lastRunDate;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
