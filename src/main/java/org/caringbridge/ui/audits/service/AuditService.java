package org.caringbridge.ui.audits.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.caringbridge.ui.audits.exception.ConfigurationException;
import org.caringbridge.ui.audits.exception.DownstreamException;
import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Audit.Status;
import org.caringbridge.ui.audits.model.Audit.Type;
import org.caringbridge.ui.audits.rep.AuditStatusUpdateRepresentation;
import org.caringbridge.ui.audits.rep.AuditsUpdateCallResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuditService {
//	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
        @Value("${app.sub-services.audits}")
        private String auditsHost;
	
        private static final Logger log = LoggerFactory.getLogger(AuditService.class);
        
	private static final String GET_AUDITS_BY_STATUS_AND_TYPE = "%s/audits?status=%s&type=%s";
	
	private static final String PATCH_AUDITS_STATUS = "%s/audits/%s";
        
	public List<Audit> getAuditsByStatusAndType(Status status, Type type) {
	        URI url = buildURI(String.format(GET_AUDITS_BY_STATUS_AND_TYPE, auditsHost, status, type));
		ResponseEntity<Audit[]> responseEntity = restTemplate.getForEntity(url, Audit[].class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!statusCode.is2xxSuccessful()) {
			throw new DownstreamException(String.format("Received error %d from call to audit service endpoint %s.", statusCode.value(), url));
		}
		Audit[] audits = responseEntity.getBody();
		return Arrays.asList(audits);
	}

	/**
	 * Sends a http patch request to the audit micro-services in order
	 * to update the status of an Audit object in the remote services. 
	 * @param auditIds The String array of ids of the Audit object to update 
	 * in the services
	 * @param status The Audit.Status status to use in the update 
	 * @return List of AuditsUpdateCallResponse objects with the results of the update.
	 */
    public List<AuditsUpdateCallResponse> update(String[] auditIds, Status status) {
        
        List<AuditsUpdateCallResponse> results = Arrays.stream(auditIds).map((String auditId)->{
            return sendUpdateCall(auditId, status);
        }).collect(Collectors.toList());
        
        return results;
    }
	
    /**
     * Builds an URI object using the String value in the parameters.
     * @param urlString The String to parse in order to get the URI
     * @return URI object if build correctly
     * @throws ConfigurationException if the URI can't be built or parsed
     */
    private URI buildURI(String urlString){
        URI url;
        try {
                url = new URI(urlString);
                return url;
        } catch (URISyntaxException e) {
                throw new ConfigurationException("Unable to construct URI from " + urlString);
        }
    }
    
    /**
     * Sends the actual http request to update one audit id to a specific status. 
     * Audits microservice only provides operation to update one audit at the time
     * This method contacts the endpoint directly and return the result
     * @param auditId The string audit id for the object to update
     * @param status The Status status to use in the update call
     * @return AuditsUpdateCallResponse object with the result of the operation
     */
    private AuditsUpdateCallResponse sendUpdateCall(String auditId, Status status){
        boolean res = true;
        String msg = null;
        try{
            URI url = buildURI(String.format(PATCH_AUDITS_STATUS, auditsHost , auditId));
            RequestEntity<AuditStatusUpdateRepresentation> ent = new RequestEntity<AuditStatusUpdateRepresentation>
                (new AuditStatusUpdateRepresentation(status),HttpMethod.PUT,url);
            
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, ent,String.class);
            
            if (!result.getStatusCode().is2xxSuccessful()){
                res = false;
                msg = String.format("Received error %d for audit update at %s.", result.getStatusCode().value(), url);
            }
        }catch(Exception e){
            log.error("Exception while updating the status of audit {}. msg: {}",auditId, e.getMessage());
            res = false;
            msg = e.getMessage();
        }
        return new AuditsUpdateCallResponse(auditId, res, msg);
    }
    
}
