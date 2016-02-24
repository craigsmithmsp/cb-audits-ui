package org.caringbridge.ui.audits.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Sets;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Audit.Status;
import org.caringbridge.ui.audits.model.Audit.Type;
import org.caringbridge.ui.audits.model.Finding;
import org.caringbridge.ui.audits.rep.FindingRepresentation;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
	private static List<Audit> mockAudits = new ArrayList<>();
	static {
		Audit a = new Audit();
		a.setAuditId("1");
		a.setDetails("Javascript was disabled when site was created.");
		a.setLastModified(new Date());
		a.setLastModifiedUser("csmith");
		a.setStatus(Audit.Status.QUESTIONABLE);
		a.setType(Type.SITE);
		a.setTypeId("968");
		Finding f = new Finding();
		f.setDetails("Javascript was disabled when site was created.");
		f.setLastRunDate(new Date());
		f.setRuleName("JSDISABLED");
		f.setStatus(Finding.Status.FAILED);
		a.setFindings(Sets.newHashSet(f));
		mockAudits.add(a);
	}
	public List<Audit> getAuditsByStatusAndType(Status status, Type type) {
		return mockAudits;
	}

}
