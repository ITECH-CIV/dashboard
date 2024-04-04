package org.itechciv.dashboard.helper;

public class ResponseMessage {
	
	  private String message;
	  private Object content;
	  
	public ResponseMessage(String message, Object content) {
		super();
		this.message = message;
		this.content = content;
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
}
