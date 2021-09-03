package br.com.framework.blog.exception;

public class NotValidException {
	
	private String field;
	private String message;

	public NotValidException(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
