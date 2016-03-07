package org.caringbridge.ui.audits.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Finding;
import org.caringbridge.ui.audits.rep.AuditDetailRepresentation;
import org.caringbridge.ui.audits.rep.FindingRepresentation;
import org.caringbridge.ui.audits.service.AuditDetailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JUnit for the AuditDetailController controller methods
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 *
 */
public class AuditDetailControllerTest extends BaseControllerTest {

    //String dateMAtcher = Matchers.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}[.]\\d+");
    
    @Autowired
    AuditDetailController auditDetailController;
    
    @Mock
    AuditDetailService mockAuditDetailSvc;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        
        Mockito.when(mockAuditDetailSvc.getDetail(idCapture.capture()))
        .thenAnswer((invokation)->{
            
            FindingRepresentation finding = new FindingRepresentation();
            finding.setDetails("Test Finding");
            finding.setLastRunDate(LocalDateTime.now());
            finding.setRuleName("Test Rule");
            finding.setStatus(Finding.Status.FAILED);
            
            List<FindingRepresentation> findings = Arrays.asList(finding);
            
            AuditDetailRepresentation rep = new AuditDetailRepresentation();
            rep.setAuditId(idCapture.getValue());
            rep.setFindings(findings);
            rep.setLastModified(LocalDateTime.now());
            rep.setLastModifiedUser("testUser");
            rep.setProfileCreatedAt(LocalDateTime.now());
            rep.setProfileEmail("test@test.com");
            rep.setProfileName("test");
            rep.setSiblingSiteCount(1);
            rep.setSiteCreatedAt(LocalDateTime.now());
            rep.setSiteId("1");
            rep.setSiteName("site1");
            rep.setStatus(Audit.Status.QUESTIONABLE);
            return rep;
        });
        
        
        auditDetailController.setAuditDetailSvc(mockAuditDetailSvc);
    }
    
    @Test
    public void testGetAuditDetails() throws Exception{
        mockMvc.perform(get("/ui/audits/details/12332"))
        .andDo(print())
        .andExpect(jsonPath("$.auditId").value("12332"))
        .andExpect(jsonPath("$.profileName").value("test"))
        .andExpect(jsonPath("$.profileEmail").value("test@test.com"))
        .andExpect(jsonPath("$.siteId").value(1))
        .andExpect(jsonPath("$.siteName").value("site1"))
        .andExpect(jsonPath("$.status").value("QUESTIONABLE"))
        .andExpect(jsonPath("$.lastModifiedUser").value("testUser"))
        .andExpect(jsonPath("$.findings").isArray())
//        .andExpect(jsonPath("$.findings[0]",Matchers.allOf(
//                    Matchers.hasProperty("ruleName"),Matchers.hasProperty("status"),
//                    Matchers.hasProperty("lastRunDate"),Matchers.hasProperty("details")
//                    )
//                ))
        .andExpect(jsonPath("$.lastModified").isNotEmpty())
        .andExpect(jsonPath("$.siteCreatedAt").isNotEmpty())
        .andExpect(jsonPath("$.profileCreatedAt").isNotEmpty())
        .andExpect(jsonPath("$.siblingSiteCount").value(1))
        ;
    }
    
}
