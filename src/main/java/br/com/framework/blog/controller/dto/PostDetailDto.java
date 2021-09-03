package br.com.framework.blog.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.framework.blog.model.Post;

public class PostDetailDto {

	private Long id;
	private String title;
	private String content;
	private String urlImageFeatured;
	private List<CommentDto> commentsDto = new ArrayList<>();
	private String nameUser;
	private List<String> urlImages = new ArrayList<>();
	private LocalDateTime creationDate;
	
	public PostDetailDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.urlImageFeatured = post.getUrlImageFeatured();
		this.commentsDto.addAll(post.getComments().stream().map(CommentDto::new).collect(Collectors.toList()));
		this.nameUser = post.getUser().getLogin();
		this.urlImages.addAll(post.getImages().stream().map(i -> i.getUrl()).collect(Collectors.toList()));
		this.creationDate = post.getCreationDate();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getUrlImageFeatured() {
		return urlImageFeatured;
	}

	public List<CommentDto> getCommentsDto() {
		return commentsDto;
	}

	public String getNameUser() {
		return nameUser;
	}

	public List<String> getUrlImages() {
		return urlImages;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

}
