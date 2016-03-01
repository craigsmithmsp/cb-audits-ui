package org.caringbridge.ui.audits.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = ErrorCodes.CONFIG_EXC_REASON_CODE)
public class ConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 9087842933366267610L;

	public ConfigurationException(String msg) {
		super(msg);
	}
}
