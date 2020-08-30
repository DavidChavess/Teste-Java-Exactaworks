package com.exactaworks.testejava.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationError extends StandardError{
	
	private List<String> errors = new ArrayList<>();

	public ValidationError(Integer status, String error, Long timestamp, BindingResult e) {
		super(status, error, timestamp);		
		e.getAllErrors().stream().map(ObjectError::getDefaultMessage).forEach(errors::add);
	}

	public List<String> getErrors() {
		return errors;
	}
}
