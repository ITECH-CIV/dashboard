package org.itechciv.dashboard.helper;

import org.itechciv.dashboard.response.Response.ResponseStatusEnum;

public class Reponse { 
	
	public enum ResponseStatusEnum{ SUCCESS,ERROR,WARNING,NO_ACCESS,NO_CONTENT,INTERNAL_ERROR };
	
	private ResponseStatusEnum status; 
	private String message; 
	private boolean isSuccess;
	
	
	public Reponse(ResponseStatusEnum success, Object object, String string, boolean b) {
		super();
	}
	

	public Reponse(ResponseStatusEnum status, String message, boolean isSuccess) {
		super();
		this.status= status;
		this.message= message;
		this.isSuccess = isSuccess;
	}
	
	
	public Reponse() {
		// TODO Auto-generated constructor stub
	}


	public ResponseStatusEnum getStatus() {
		return status;
	}


	public void setStatus(ResponseStatusEnum status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isSuccess() {
		return isSuccess;
	}


	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	@Override
	public String toString() {
		return "Reponse [status=" + status + ", message=" + message + ", isSuccess=" + isSuccess + "]";
	}
	
	
	
	


}
