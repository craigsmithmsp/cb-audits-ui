package org.caringbridge.services.reference.controllers;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.AbstractMap.SimpleEntry;

import org.apache.commons.collections.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
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
	public ResponseEntity<PingResult> ping() {
		getLog().info("Getting the ping info......");
		return ResponseEntity.ok(new PingResult("success"));

	}

}

// This is just here to provide an example of how to return an object and have it automatically converted to json. Normally, there would be a
// model class to represent the resource being requested. A service call would retrieve or build the model object and the controller would return it.
class PingResult {
    private String result;
    public PingResult(String result) { this.result = result; }
    public String getResult() { return result; }
}
