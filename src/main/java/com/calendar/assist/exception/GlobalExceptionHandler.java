package com.calendar.assist.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CalendarAssistBusinessException.class })
	protected ResponseEntity<Object> handleCalendarAssistBusinessException(CalendarAssistBusinessException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getDetails(), HttpStatus.CONFLICT);
	}

}
