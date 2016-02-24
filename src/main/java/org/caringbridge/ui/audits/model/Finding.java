package org.caringbridge.ui.audits.model;

import java.util.Date;

public class Finding {
    public enum Status { INVALID, PASSED, FAILED }
    private String id; 
    private String ruleName;
    private Audit.Type type;
    private String typeId;
    private Finding.Status status;
    private Date lastRunDate;
    private String details;

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getRuleName() {
        return ruleName;
    }
    
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
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
