package org.caringbridge.ui.audits.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.caringbridge.ui.audits.exception.ConfigurationException;
import org.caringbridge.ui.audits.exception.DownstreamException;
import org.caringbridge.ui.audits.model.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProfileService {
	private RestTemplate restTemplate = new RestTemplate();
    @Value("${app.sub-services.profiles}")
    private String profilesHost;
    
    private static final String GET_PROFILE = "%s/profiles/%s";
    
    public Profile getProfile(int profileId) {
		URI url;
		String urlString = String.format(GET_PROFILE, profilesHost, profileId);
		try {
			url = new URI(urlString);
		} catch (URISyntaxException e) {
			throw new ConfigurationException("Unable to construct URI from " + urlString);
		}
		ResponseEntity<Profile> responseEntity = restTemplate.getForEntity(url, Profile.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!statusCode.is2xxSuccessful()) {
			throw new DownstreamException(String.format("Received error %d from call to profiles service endpoint %s.", statusCode.value(), url));
		}
		return responseEntity.getBody();
	}

}
