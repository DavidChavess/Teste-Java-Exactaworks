package com.exactaworks.testejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exactaworks.testejava.exception.ObjectNotFoundException;
import com.exactaworks.testejava.exception.StandardError;
import com.exactaworks.testejava.exception.ValidationError;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {


	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public StandardError objectNotFound(ObjectNotFoundException e) {
		return new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
	}
	


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationError validationError(MethodArgumentNotValidException e) {
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erros de valição", System.currentTimeMillis());
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return error;
	}
	
	
}
