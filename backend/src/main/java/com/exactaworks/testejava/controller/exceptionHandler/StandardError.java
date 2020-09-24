package com.exactaworks.testejava.controller.exceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

public class StandardError {
	
	private Integer statuscode;
	private Long timestamp;
	private List<String> errors = new ArrayList<>();
	
	public StandardError() {}
	
	public StandardError(Integer statuscode, Exception exception) {
		this.statuscode = statuscode;
		this.errors = Arrays.asList(exception.getMessage());
		this.timestamp = System.currentTimeMillis();
	}
	
	public StandardError(Integer statuscode, BindingResult result) {
		this.statuscode = statuscode;
		result.getAllErrors().forEach( error -> errors.add(error.getDefaultMessage()));
		this.timestamp = System.currentTimeMillis();
	}

	public Integer getStatuscode() {
		return statuscode;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public List<String> getErrors() {
		return errors;
	}
}
