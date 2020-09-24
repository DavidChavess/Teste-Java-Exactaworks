package com.exactaworks.testejava.controller.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exactaworks.testejava.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public StandardError objectNotFound(ObjectNotFoundException exc) {
		return new StandardError(HttpStatus.NOT_FOUND.value(), exc);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public StandardError validationError(MethodArgumentNotValidException exc) {
		return new StandardError(HttpStatus.BAD_REQUEST.value(), exc.getBindingResult());
	}
}
