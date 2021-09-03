package br.com.framework.blog.controller.form;

import javax.validation.constraints.NotEmpty;

public class CommentForm {
	
	@NotEmpty
	private Long idPost;
	
	@NotEmpty
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}
	
	

}
