package com.home.ape.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.home.ape.service.exception.DataValidationException;

/**
 * The Class RestResponseEntityExceptionHandler.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle data validation exception.
	 *
	 * @param exception
	 *            the exception
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler({ DataValidationException.class })
	protected ResponseEntity<Object> handleDataValidationException(DataValidationException exception,
			WebRequest request) {

		return new ResponseEntity<>(exception.getStatusMessage().getErrors(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
	}

}
