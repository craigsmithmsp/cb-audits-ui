package org.caringbridge.services.reference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class to run the application.
 * 
 * @author guanlun.mu
 *
 */

@SpringBootApplication
@ComponentScan("org.caringbridge.services.reference")
@EnableConfigurationProperties
public class CbReferenceApplication {
	
	/**
	 * Main method to run the Spring Boot Application
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(CbReferenceApplication.class, args);
	}
}
