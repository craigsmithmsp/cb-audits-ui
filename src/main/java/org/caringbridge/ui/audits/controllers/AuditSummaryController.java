package org.caringbridge.ui.audits.controllers;

import java.util.List;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.rep.AuditSummaryRepresentation;
import org.caringbridge.ui.audits.rep.AuditsUpdateCallResponse;
import org.caringbridge.ui.audits.rep.UpdateAuditSummaryRequest;
import org.caringbridge.ui.audits.service.AuditSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(basePath = "/ui/audits", consumes=MediaType.APPLICATION_JSON_VALUE+","+MediaType.APPLICATION_JSON_UTF8_VALUE,
    description = "Provides audit summary information.", value = "ui/audits")
@RequestMapping(path = "/ui/audits", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
public class AuditSummaryController {
	private final Logger log = LoggerFactory.getLogger(AuditSummaryController.class);
	
	@Autowired
	private AuditSummaryService auditSummarySvc;
	
	@RequestMapping(path="/summaries", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "summaries", httpMethod = "GET", notes = "Returns audit summaries.", produces = "application/json")
	public ResponseEntity<List<AuditSummaryRepresentation>> getSummaries(@RequestParam Audit.Status status, @RequestParam Audit.Type type) {
		List<AuditSummaryRepresentation> summaries = null;
		if (status != null) {
			summaries = auditSummarySvc.getSiteSummariesByStatus(status);
		}
		return ResponseEntity.ok(summaries);

	}
	
	@RequestMapping(path="/summaries/update", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "/summaries/update", httpMethod = "POST", 
	    notes = "Update the audit objects that have the specified ids with the specified status.", produces = "application/json")
        public ResponseEntity<List<AuditsUpdateCallResponse>> validateAuditSummary(@RequestBody UpdateAuditSummaryRequest request){
	    log.info("Updating the status of audit {} to {}", request.getAuditIds(), request.getStatus());
	    List<AuditsUpdateCallResponse> updated = auditSummarySvc.updateAuditStatus(request.getAuditIds(), request.getStatus());
	    return ResponseEntity.ok(updated);
	}

    /**
     * @return the auditSummarySvc
     */
    public AuditSummaryService getAuditSummarySvc() {
        return auditSummarySvc;
    }

    /**
     * @param auditSummarySvc the auditSummarySvc to set
     */
    public void setAuditSummarySvc(AuditSummaryService auditSummarySvc) {
        this.auditSummarySvc = auditSummarySvc;
    }
	
}
