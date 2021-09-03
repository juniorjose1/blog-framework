package br.com.framework.blog.exception;

public class GalleryNotFoundException extends RuntimeException implements IException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public GalleryNotFoundException(Long id) {
		this.message = String.format("Gallery with id %d not found", id);
	}

	public String getMessage() {
		return message;
	}

}
