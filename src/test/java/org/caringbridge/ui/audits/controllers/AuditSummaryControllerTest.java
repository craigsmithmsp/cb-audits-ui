package org.caringbridge.ui.audits.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.caringbridge.ui.audits.CbApplication;
import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.service.AuditService;
import org.caringbridge.ui.audits.rep.AuditsUpdateCallResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CbApplication.class)
@WebAppConfiguration
public class AuditSummaryControllerTest {
    
    @Autowired
    AuditSummaryController auditSummaryCtl;
    
    @Mock
    AuditService mockAuditService;
    
    MockMvc mockMvc;
    
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
        
        mockMvc = MockMvcBuilders.standaloneSetup(auditSummaryCtl).build();
        
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
