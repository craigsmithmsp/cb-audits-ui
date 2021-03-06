package org.caringbridge.ui.audits.model;

import java.time.LocalDateTime;
import java.util.Set;


public class Audit {
	public enum Status { PASSED, QUESTIONABLE, INVALID, FAILED, DEACTIVATED, PURGED }
	public enum Type { SITE, PROFILE };
    private String auditId;
    private Audit.Type type;
    private String typeId;
    private Audit.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private String lastModifiedUser;
    private String details;
    private Set<Finding> findings;
    
    public String getAuditId() {
        return auditId;
    }
    
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }
    
    public Audit.Type getType() {
        return type;
    }
    
    public void setType(Audit.Type type) {
        this.type = type;
    }
    
    public String getTypeId() {
        return typeId;
    }
    
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Audit.Status getStatus() {
        return status;
    }
    
    public void setStatus(Audit.Status status) {
        this.status = status;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }
    

    public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
    

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }
    

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }
    

    public String getDetails() {
        return details;
    }
    

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<Finding> getFindings() {
        return findings;
    }

    public void setFindings(Set<Finding> findings) {
        this.findings = findings;
    }
    
}
