package org.caringbridge.services.reference.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

/**
 * Controller for handling the request by mapping url.
 * 
 * @author guanlun.mu
 *
 */
@RestController
@Api(basePath = "/ping", description = "A Ping controller just to check whether the application works", value = "ping")
@RequestMapping(path = "/ping")
public class PingController {

	/**
	 * The log.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(PingController.class);

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return LOG;
	}

	/**
	 * A Ping controller to check the health of the controller.
	 * 
	 * @return Response entity that only display a message.
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "ping", httpMethod = "GET", notes = "Produces a ping message for the controller.", produces = "application/json")
	@ApiResponse(code = 404, message = "No Information Found for this Provider")
	public ResponseEntity<String> ping() {
		getLog().info("Getting the ping info......");
		return ResponseEntity.ok("Success");
	}
}
