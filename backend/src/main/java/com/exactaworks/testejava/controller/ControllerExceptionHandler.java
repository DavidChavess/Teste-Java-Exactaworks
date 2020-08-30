package com.exactaworks.testejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exactaworks.testejava.exception.ObjectNotFoundException;
import com.exactaworks.testejava.exception.StandardError;
import com.exactaworks.testejava.exception.ValidationError;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public StandardError objectNotFound(ObjectNotFoundException e) {
		return new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationError validationError(MethodArgumentNotValidException e) {
		return new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erros de validação", System.currentTimeMillis(), e.getBindingResult());
	}
}
