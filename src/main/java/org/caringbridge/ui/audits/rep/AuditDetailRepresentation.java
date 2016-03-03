package org.caringbridge.ui.audits.rep;

import java.time.LocalDateTime;

import org.caringbridge.ui.audits.util.LocalDateISODeserializer;
import org.caringbridge.ui.audits.util.LocalDateISOSerializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_NULL)
public class AuditDetailRepresentation extends AuditSummaryRepresentation {
	private LocalDateTime lastModified;
	private LocalDateTime siteCreatedAt;
	private LocalDateTime profileCreatedAt;
	private int siblingSiteCount;
	
	// Getters and Setters
    @JsonSerialize(using=LocalDateISOSerializer.class)
	public LocalDateTime getLastModified() {
		return lastModified;
	}
    @JsonDeserialize(using=LocalDateISODeserializer.class)
	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}
    @JsonSerialize(using=LocalDateISOSerializer.class)
	public LocalDateTime getSiteCreatedAt() {
		return siteCreatedAt;
	}
    @JsonDeserialize(using=LocalDateISODeserializer.class)
	public void setSiteCreatedAt(LocalDateTime siteCreatedAt) {
		this.siteCreatedAt = siteCreatedAt;
	}
    @JsonSerialize(using=LocalDateISOSerializer.class)
	public LocalDateTime getProfileCreatedAt() {
		return profileCreatedAt;
	}
    @JsonDeserialize(using=LocalDateISODeserializer.class)
	public void setProfileCreatedAt(LocalDateTime profileCreatedAt) {
		this.profileCreatedAt = profileCreatedAt;
	}
	public int getSiblingSiteCount() {
		return siblingSiteCount;
	}
	public void setSiblingSiteCount(int siblingSiteCount) {
		this.siblingSiteCount = siblingSiteCount;
	}
}
