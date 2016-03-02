package org.caringbridge.ui.audits.rep;

import org.caringbridge.ui.audits.model.Audit;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The representation class that will hold the information
 * for a request to update Audit objects based on the 
 * selected items of the AuditsSummary table
 * 
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 */
public class UpdateAuditSummaryRequest {

    private String[] auditIds;
    
    private Audit.Status status;

    /**
     * @return the auditIds
     */
    @JsonProperty(value="auditIds")
    public String[] getAuditIds() {
        return auditIds;
    }

    /**
     * @param auditIds the auditIds to set
     */
    public void setAuditIds(String[] auditIds) {
        this.auditIds = auditIds;
    }

    /**
     * @return the status
     */
    @JsonProperty(value="status")
    public Audit.Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Audit.Status status) {
        this.status = status;
    }
    
}
