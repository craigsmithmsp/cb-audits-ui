package org.caringbridge.ui.audits.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.caringbridge.ui.audits.util.LocalDateISODeserializer;
import org.caringbridge.ui.audits.util.LocalDateISOSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Finding {
    public enum Status { INVALID, PASSED, FAILED }
    private String id; 
    private String ruleName;
    private Audit.Type type;
    private String typeId;
    private Finding.Status status;
    private LocalDateTime lastRunDate;
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
