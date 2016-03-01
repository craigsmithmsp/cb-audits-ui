package org.caringbridge.ui.audits.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.caringbridge.ui.audits.exception.ConfigurationException;
import org.caringbridge.ui.audits.exception.DownstreamException;
import org.caringbridge.ui.audits.model.Site;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SiteService {
	private RestTemplate restTemplate = new RestTemplate();
    @Value("${app.sub-services.sites}")
    private String sitesHost;

	private static final String GET_SITE = "%s/sites/%s";
	
	public Site getSiteById(int siteId) {
		URI url;
		String urlString = String.format(GET_SITE, sitesHost, siteId);
		try {
			url = new URI(urlString);
		} catch (URISyntaxException e) {
			throw new ConfigurationException("Unable to construct URI from " + urlString);
		}
		ResponseEntity<Site> responseEntity = restTemplate.getForEntity(url, Site.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!statusCode.is2xxSuccessful()) {
			throw new DownstreamException(String.format("Received error %d from call to sites service endpoint %s.", statusCode.value(), url));
		}
		return responseEntity.getBody();
	}
	
}
