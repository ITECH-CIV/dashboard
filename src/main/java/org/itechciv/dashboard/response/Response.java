package org.itechciv.dashboard.response;

public class Response {
	
public enum ResponseStatusEnum {SUCCESS,ERROR,WARNING,NO_ACCESS,NO_CONTENT,INTERNAL_ERROR}; 
	
	private ResponseStatusEnum status; 
	private Object datas; 
	private String message; 
	private boolean isSuccess;
	public ResponseStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ResponseStatusEnum status) {
		this.status = status;
	}
	public Object getDatas() {
		return datas;
	}
	public void setDatas(Object datas) {
		this.datas = datas;
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
	public Response(ResponseStatusEnum status, Object datas, String message, boolean isSuccess) {
		super();
		this.status = status;
		this.datas = datas;
		this.message = message;
		this.isSuccess = isSuccess;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	} 
}
