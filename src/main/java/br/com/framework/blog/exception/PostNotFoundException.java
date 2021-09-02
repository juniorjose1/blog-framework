package br.com.framework.blog.exception;

public class PostNotFoundException extends RuntimeException implements INotFoundException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public PostNotFoundException(Long id) {
		this.message = String.format("Post with id %d not found", id);
	}

	public String getMessage() {
		return message;
	}

}
