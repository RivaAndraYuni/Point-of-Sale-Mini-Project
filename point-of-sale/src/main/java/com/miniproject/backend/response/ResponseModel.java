package com.miniproject.backend.response;

public class ResponseModel<T> {
    private String status;
    
    private String message;
    
   
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
