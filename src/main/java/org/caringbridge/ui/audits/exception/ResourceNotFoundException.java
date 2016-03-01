package org.caringbridge.ui.audits.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such resource")
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(Object id) {
		super("Resource not found for id " + id);
	}
}
