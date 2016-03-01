package org.caringbridge.ui.audits.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = ErrorCodes.DOWNSTREAM_EXC_REASON_CODE)
public class DownstreamException extends RuntimeException {
	private static final long serialVersionUID = 4916525306222332032L;

	public DownstreamException(String msg) {
		super(msg);
	}
}
