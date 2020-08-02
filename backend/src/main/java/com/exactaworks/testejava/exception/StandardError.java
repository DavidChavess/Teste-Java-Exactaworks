package com.exactaworks.testejava.exception;

public class StandardError {
	
	private Integer status;
	private String error;
	private Long timestamp;
	
	public StandardError() {}
	
	public StandardError(Integer status, String error, Long timestamp) {
		this.status = status;
		this.error = error;
		this.timestamp = timestamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}	
}
