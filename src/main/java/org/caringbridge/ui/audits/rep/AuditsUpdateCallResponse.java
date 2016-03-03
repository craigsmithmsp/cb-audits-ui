package org.caringbridge.ui.audits.rep;

/**
 * Representation class that will hold the results of the
 * update operations for the audit summary controller.
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 *
 */
public class AuditsUpdateCallResponse{
    
    private String auditId;
    private boolean result;
    private String msg;
    
    /**
     * @param auditId
     * @param result
     * @param msg
     */
    public AuditsUpdateCallResponse(String auditId, boolean result, String msg) {
        this.auditId = auditId;
        this.result = result;
        this.msg = msg;
    }
    /**
     * @return the auditId
     */
    public String getAuditId() {
        return auditId;
    }
    /**
     * @param auditId the auditId to set
     */
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }
    /**
     * @return the result
     */
    public boolean isResult() {
        return result;
    }
    /**
     * @param result the result to set
     */
    public void setResult(boolean result) {
        this.result = result;
    }
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
