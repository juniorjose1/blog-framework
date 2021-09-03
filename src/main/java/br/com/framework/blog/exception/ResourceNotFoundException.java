package br.com.framework.blog.exception;

public class ResourceNotFoundException extends RuntimeException implements IException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ResourceNotFoundException() {
		this.message = "No records found";
	}

	public String getMessage() {
		return message;
	}

}
