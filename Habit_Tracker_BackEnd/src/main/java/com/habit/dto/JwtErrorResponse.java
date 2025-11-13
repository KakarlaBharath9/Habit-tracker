package com.habit.dto;

import java.time.Instant;

public class JwtErrorResponse {
	private String error; //invalid token
	private String message; //explanation
	private int status; //http status code
	private Instant timestamp;
	
	public JwtErrorResponse() {
		this.timestamp=Instant.now();
	}

	public JwtErrorResponse(String error, String message, int status, Instant timestamp) {
		super();
		this.error = error;
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
