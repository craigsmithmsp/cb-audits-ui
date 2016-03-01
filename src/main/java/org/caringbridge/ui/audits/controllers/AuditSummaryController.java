package org.caringbridge.ui.audits.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Finding;
import org.caringbridge.ui.audits.rep.AuditSummaryRepresentation;
import org.caringbridge.ui.audits.rep.FindingRepresentation;
import org.caringbridge.ui.audits.service.AuditSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(basePath = "/ui/audit", description = "Provides audit summary information.", value = "ui/audit")
@RequestMapping(path = "/ui/audit")
public class AuditSummaryController {
	private final Logger log = LoggerFactory.getLogger(AuditSummaryController.class);
	@Autowired
	private AuditSummaryService auditSummarySvc;
	
	private static final AuditSummaryRepresentation mockAuditSummary = new AuditSummaryRepresentation();
	static {
		mockAuditSummary.setProfileEmail("amos.author@gmail.com");
		mockAuditSummary.setProfileName("Amos");
		mockAuditSummary.setSiteName("Support Patty Patient");
		mockAuditSummary.setStatus(Audit.Status.QUESTIONABLE);
		FindingRepresentation finding = new FindingRepresentation();
		finding.setDetails("Javascript was disabled when site was created.");
		finding.setLastRunDate(LocalDateTime.now());
		finding.setRuleName("JSDISABLED");
		finding.setStatus(Finding.Status.FAILED);
		mockAuditSummary.setFindings(Arrays.asList(finding));
	}
	
	@RequestMapping(path="/summaries", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "summaries", httpMethod = "GET", notes = "Returns audit summaries.", produces = "application/json")
	public ResponseEntity<List<AuditSummaryRepresentation>> getSummaries(@RequestParam Audit.Status status, @RequestParam Audit.Type type) {
		List<AuditSummaryRepresentation> summaries = null;
		if (status != null) {
			summaries = auditSummarySvc.getSiteSummariesByStatus(status);
		}
		return ResponseEntity.ok(summaries);

	}
}
