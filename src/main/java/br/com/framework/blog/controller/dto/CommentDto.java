package br.com.framework.blog.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.framework.blog.model.Comment;

public class CommentDto {

	private Long id;
	private String content;
	private LocalDateTime creationDate;
	private String nameUser;

	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.creationDate = comment.getCreationDate();
		this.nameUser = comment.getUser().getLogin();
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getNameUser() {
		return nameUser;
	}

	public static Page<CommentDto> toListCommentsDto(Page<Comment> comments) {
		return comments.map(CommentDto::new);
	}

}
