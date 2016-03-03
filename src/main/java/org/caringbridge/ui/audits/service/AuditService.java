package org.caringbridge.ui.audits.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.caringbridge.ui.audits.exception.ConfigurationException;
import org.caringbridge.ui.audits.exception.DownstreamException;
import org.caringbridge.ui.audits.model.Audit;
import org.caringbridge.ui.audits.model.Audit.Status;
import org.caringbridge.ui.audits.model.Audit.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuditService {
//	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
    @Value("${app.sub-services.audits}")
    private String auditsHost;
	
	private static final String GET_AUDIT = "%s/audits/%s";
	private static final String GET_AUDITS_BY_STATUS_AND_TYPE = "%s/audits?status=%s&type=%s";
	
	public List<Audit> getAuditsByStatusAndType(Status status, Type type) {
		URI url;
		String urlString = String.format(GET_AUDITS_BY_STATUS_AND_TYPE, auditsHost, status, type);
		try {
			url = new URI(urlString);
		} catch (URISyntaxException e) {
			throw new ConfigurationException("Unable to construct URI from " + urlString);
		}
		ResponseEntity<Audit[]> responseEntity = restTemplate.getForEntity(url, Audit[].class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!statusCode.is2xxSuccessful()) {
			throw new DownstreamException(String.format("Received error %d from call to audit service endpoint %s.", statusCode.value(), url));
		}
		Audit[] audits = responseEntity.getBody();
		return Arrays.asList(audits);
	}

	public Audit getAudit(String id) {
		URI url;
		String urlString = String.format(GET_AUDIT, auditsHost, id);
		try {
			url = new URI(urlString);
		} catch (URISyntaxException e) {
			throw new ConfigurationException("Unable to construct URI from " + urlString);
		}
		ResponseEntity<Audit> responseEntity = restTemplate.getForEntity(url, Audit.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!statusCode.is2xxSuccessful()) {
			throw new DownstreamException(String.format("Received error %d from call to audit service endpoint %s.", statusCode.value(), url));
		}
		return responseEntity.getBody();
	}
	
}
