package br.com.framework.blog.exception;

public class ActionNotAllowedException extends RuntimeException implements IException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ActionNotAllowedException() {
		this.message = "Action not allowed, you aren`t the autor.";
	}

	public String getMessage() {
		return message;
	}

}
