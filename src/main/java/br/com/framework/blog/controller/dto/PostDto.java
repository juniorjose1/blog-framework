package br.com.framework.blog.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	public static List<PostDto> toListPostDto(List<Post> posts) {
		return posts.stream().map(PostDto::new).collect(Collectors.toList());
	}

}
