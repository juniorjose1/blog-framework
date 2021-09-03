package br.com.framework.blog.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.framework.blog.model.Post;

public class PostDto {

	private Long id;
	private String title;
	private String urlImageFeatured;
	private LocalDateTime creationDate;
	
	public PostDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.urlImageFeatured = post.getUrlImageFeatured();
		this.creationDate = post.getCreationDate();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getUrlImageFeatured() {
		return urlImageFeatured;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public static Page<PostDto> toListPostDto(Page<Post> posts) {
		return posts.map(PostDto::new);
	}

}
