package com.stg.makeathon.exception;

public class TechAdvException extends Exception {
	private static final long serialVersionUID = 1L;
	private int status;
	private String errorMsg;

	public TechAdvException() {
		super();
	}

	public TechAdvException(int status, String errorMsg) {
		super(errorMsg);
		this.status = status;
		this.errorMsg = errorMsg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
