package br.com.framework.blog.exception;

public class CommentNotFoundException extends RuntimeException implements IException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CommentNotFoundException(Long id) {
		this.message = String.format("Comment with id %d not found", id);
	}

	public String getMessage() {
		return message;
	}

}
