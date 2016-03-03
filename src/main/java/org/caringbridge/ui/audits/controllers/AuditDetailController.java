package org.caringbridge.ui.audits.controllers;

import java.util.List;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.rep.AuditDetailRepresentation;
import org.caringbridge.ui.audits.rep.AuditSummaryRepresentation;
import org.caringbridge.ui.audits.service.AuditDetailService;
import org.caringbridge.ui.audits.service.AuditSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(basePath = "/ui/audits", description = "Provides audit summary information.", value = "ui/audits")
@RequestMapping(path = "/ui/audits")
public class AuditDetailController {
	@Autowired
	AuditDetailService auditDetailSvc;
	@Autowired
	private AuditSummaryService auditSummarySvc;

	@RequestMapping(path="/details/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "details", httpMethod = "GET", notes = "Returns audit details.", produces = "application/json")
	AuditDetailRepresentation getAuditDetails(@PathVariable String id) {
		return auditDetailSvc.getDetail(id); 
	}
}
