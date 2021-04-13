package com.calendar.assist.exception;

import lombok.Getter;

@Getter
public class CalendarAssistBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorDetail details;

	public CalendarAssistBusinessException(final String message) {
		super(message);
	}

	public CalendarAssistBusinessException(final ErrorDetail details) {
		this(details, null);
	}

	public CalendarAssistBusinessException(final ErrorDetail details, final Throwable cause) {
		super(cause);
		this.details = details;

	}
}
