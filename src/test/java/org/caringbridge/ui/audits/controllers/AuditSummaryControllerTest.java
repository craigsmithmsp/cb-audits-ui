package org.caringbridge.ui.audits.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.rep.AuditsUpdateCallResponse;
import org.caringbridge.ui.audits.service.AuditService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class AuditSummaryControllerTest extends BaseControllerTest {
    
    @Autowired
    AuditSummaryController auditSummaryCtl;
    
    @Mock
    AuditService mockAuditService;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        // Mock for the audit Service that will return 5 AuditUpdateCallResponse objects when called with any
        // array of audit ids and any status
        Mockito.when(mockAuditService.update(Mockito.any(String[].class), Mockito.any(Audit.Status.class)))
        .then(invocation -> {
                List<AuditsUpdateCallResponse> res = IntStream.range(0, 5).mapToObj(i->{
                    return new AuditsUpdateCallResponse(String.valueOf(i), true, null);
                }).collect(Collectors.toList());
                return res;
        });
        
        auditSummaryCtl.getAuditSummarySvc().setAuditSvc(mockAuditService);
        
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateAuditsSummaryStatus() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/ui/audits/summaries/update")
                .contentType(MediaType.APPLICATION_JSON).content("{\"auditIds\":[1,2,3,4,5],\"status\":\"FAILED\"}")
                )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].auditId").isNotEmpty())
        .andExpect(jsonPath("$[0].result").isBoolean())
        .andExpect(jsonPath("$[0].msg").value(Matchers.anyOf(Matchers.nullValue(), Matchers.any(String.class))))
        ;
    }
    
}
