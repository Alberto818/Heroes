package com.assignment.entity;

public class ApiError {

	private Long internalCode;
	private String internalMsg;
	
	public ApiError(Long internalCode, String internalMsg) {
		super();
		this.internalCode = internalCode;
		this.internalMsg = internalMsg;
	}

	public Long getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(Long internalCode) {
		this.internalCode = internalCode;
	}

	public String getInternalMsg() {
		return internalMsg;
	}

	public void setInternalMsg(String internalMsg) {
		this.internalMsg = internalMsg;
	}
	
	
}
