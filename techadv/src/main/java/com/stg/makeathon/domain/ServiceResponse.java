package com.stg.makeathon.domain;

public class ServiceResponse {
	private int status;
	private String message;
	private Object content;

	public ServiceResponse() {
		status = -1;
		message = "Unexpected Error occurred.";
		content = null;
	}

	public ServiceResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public ServiceResponse(int status, String message, Object content) {
		super();
		this.status = status;
		this.message = message;
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public static ServiceResponse getSuccessResponse() {
		return new ServiceResponse(1, "Request Successful.");
	}

	public static ServiceResponse getSuccessResponse(String message) {
		return new ServiceResponse(1, message);
	}

	public static ServiceResponse getSuccessResponse(Object content) {
		return new ServiceResponse(1, "Request Successful.", content);
	}

	public static ServiceResponse getSuccessResponse(String message, Object content) {
		return new ServiceResponse(1, message, content);
	}

	public static ServiceResponse getErrorResponse() {
		return new ServiceResponse(-1, "Unexpected Error Occured.");
	}

	public static ServiceResponse getErrorResponse(String message) {
		return new ServiceResponse(-1, message);
	}

	public static ServiceResponse getErrorResponse(String message, Object content) {
		return new ServiceResponse(-1, message, content);
	}
}
