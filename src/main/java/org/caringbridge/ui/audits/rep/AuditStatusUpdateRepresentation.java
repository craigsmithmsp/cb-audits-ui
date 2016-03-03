package org.caringbridge.ui.audits.rep;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Audit.Status;

public class AuditStatusUpdateRepresentation {
    
    private Audit.Status status;

    /**
     * @param status
     */
    public AuditStatusUpdateRepresentation(Status status) {
        this.status = status;
    }
    
    /**
     * 
     */
    public AuditStatusUpdateRepresentation() {
    }



    public Audit.Status getStatus() {
        return status;
    }

    public void setStatus(Audit.Status status) {
        this.status = status;
    }

}
