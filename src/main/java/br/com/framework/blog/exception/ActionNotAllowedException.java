package br.com.framework.blog.exception;

public class ActionNotAllowedException extends RuntimeException implements INotFoundException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ActionNotAllowedException() {
		this.message = "Action not allowed, not the author of the Post";
	}

	public String getMessage() {
		return message;
	}

}
