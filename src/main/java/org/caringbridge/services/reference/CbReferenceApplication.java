package org.caringbridge.services.reference;

import org.caringbridge.common.services.annotations.EnableCorrelationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class to run the application.
 * 
 * @author guanlun.mu
 *
 */

@SpringBootApplication
@ComponentScan("org.caringbridge.services.reference")
@EnableCorrelationFilter
public class CbReferenceApplication {
	
	/**
	 * Main method to run the Spring Boot Application.
	 * @param args arguments used when running on command line.
	 */
	public static void main(final String[] args) {
		SpringApplication.run(CbReferenceApplication.class, args);
	}
}
