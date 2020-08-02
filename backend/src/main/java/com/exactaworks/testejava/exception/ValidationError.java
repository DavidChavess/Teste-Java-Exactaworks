package com.exactaworks.testejava.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String error, Long timestamp) {
		super(status, error, timestamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String field, String message) {
		this.errors.add(new FieldMessage(field, message));
	}	
}
